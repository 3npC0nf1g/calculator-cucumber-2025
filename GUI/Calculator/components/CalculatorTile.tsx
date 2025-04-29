

import { Platform, StyleSheet, TouchableOpacity } from 'react-native';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import { useOperation } from "@/components/OperationContext";

type CalculatorTileProps = {
    type: "number" | "special" | "operation",
    text: string,
    value: string
    form: "scientific" | "standard"
}

export default function CalculatorTile({ type, text, value, form }: Readonly<CalculatorTileProps>) {
    const { appendOperation, clearOperation, operation } = useOperation();

    const big: boolean = Platform.OS !== "web" && form === "scientific";

    const handlePress = () => {

        if (type === "special") {
            if (text === "C") {
                clearOperation();
            } else if (text === "=") {
                // Optionally trigger evaluation here or leave it for another component.
                // For now, we'll do nothing.
            }
        } else {
            console.log(operation)
            appendOperation(value);
        }
    };

    return (
        <ThemedView style={[!big ? styles.mainView : styles.mainViewSmall, type == "number" && styles.numberTileBg,
        type == "special" && styles.specialTileBg,
        type == "operation" && styles.operationTileBg,
        text === "=" && { flexGrow: !big ? 1 : 1 }

        ]}
        >
            <TouchableOpacity
                activeOpacity={0.2}
                style={styles.btn}
                onPress={handlePress}
            >
                <ThemedText type={!big ? 'title' : 'default'} style={styles.tileText}>
                    {text}
                </ThemedText>
            </TouchableOpacity>

        </ThemedView>
    )
}

const styles = StyleSheet.create({
    mainView: {
        flex: 1,
        borderWidth: 1,
        borderRadius: Platform.OS === "web" ? 10 : 30,
        alignItems: 'center',
        justifyContent: 'center',
        borderColor: "white",
        margin: 1
    },
    mainViewSmall: {
        flex: 0.5,
        borderWidth: 1,
        borderRadius: Platform.OS === "web" ? 10 : 30,
        alignItems: 'center',
        justifyContent: 'center',
        borderColor: "white"
    },
    specialTileBg: {
        backgroundColor: 'orange'
    },
    numberTileBg: {
        backgroundColor: "black"
    },
    operationTileBg: {
        backgroundColor: "grey"
    },
    tileText: {
        color: 'white',
        alignSelf: 'center'
    },
    btn: {
        flex: 1,
        width: '100%',
        height: '100%',
        alignItems: "center", justifyContent: "center"
    },

})