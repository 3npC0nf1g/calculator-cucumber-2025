

import { Platform, StyleSheet, TouchableOpacity } from 'react-native';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import { useOperation } from "@/context/OperationContext";

type CalculatorTileProps = {
    type: "number" | "special" | "operation",
    text: string,
    value: string
    form: "scientific" | "standard"
}

export default function CalculatorTile({ type, text, value, form }: Readonly<CalculatorTileProps>) {
    const { appendOperation, clearOperation, operation, sendOperation, clearOperationRequest, appendOperationRequest, clearAll, backPress, operationRequest } = useOperation();

    const big: boolean = Platform.OS !== "web" && form === "scientific";
    const isWeb: boolean = Platform.OS === "web";

    const handlePress = () => {

        console.log("text", operation, "request", operationRequest);
        switch (type) {
            case "number":
                if (operation === "0" && value !== ".") {
                    clearOperation();
                    clearOperationRequest();
                }
                appendOperation(value);
                appendOperationRequest(value);
                break;
            case "special":
                if (value === "C") {
                    clearAll();
                } else if (value === "=") {
                    sendOperation();
                };
                if (value === "backspace()") {
                    backPress();
                }
                break;
            case "operation":
                if (value == "%") {
                    appendOperation("%")
                    appendOperationRequest("/100");
                    break;
                }
                if (value == "*") {
                    appendOperation(text)
                    appendOperationRequest(value);
                    break;
                } if (value === "^2") {
                    console.log("operation", operation);
                    // Match the last numeric value or closing parenthesis for power(x,2)
                    const match = operation.match(/(?:\d+|\))$/);

                    if (match) {
                        const lastOperand = match[0];
                        const start = operation.length - lastOperand.length;

                        // Update display (e.g., turn 5 into 5^2)
                        const newDisplay = operation.slice(0, start) + `${lastOperand}^2`;
                        clearOperation();
                        appendOperation(newDisplay);

                        // Update request string (e.g., turn 5 into power(5,2))
                        const newRequest = operation.slice(0, start) + `power(${lastOperand},2)`;
                        clearOperationRequest();
                        appendOperationRequest(newRequest);
                    }

                    return;
                }


                appendOperation(value);
                appendOperationRequest(value);
                break;
            default:
                break;

        };
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
                <ThemedText type={!big ? 'title' : 'default'} style={isWeb ? styles.tileText : [styles.tileText, { lineHeight: 40 }]}>
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
        alignSelf: 'center',
        textAlign: 'center',
        fontSize: 30,
        justifyContent: 'center',
    },
    btn: {
        flex: 1,
        width: '100%',
        height: '100%',
        alignItems: "center",
        justifyContent: "center",
        alignContent: 'center',

    },

})