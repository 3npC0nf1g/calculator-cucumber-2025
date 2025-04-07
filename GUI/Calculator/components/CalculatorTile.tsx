

import {StyleSheet, TouchableOpacity} from 'react-native';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import {useOperation} from "@/components/OperationContext";

type CalculatorTileProps={
    type:"number"|"special"|"operation",
    text:string,
}

export default function CalculatorTile({type,text}: Readonly<CalculatorTileProps>) {
    const { appendOperation, clearOperation,operation} = useOperation();

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
            appendOperation(text);
        }
    };
    return (
        <ThemedView style={[styles.mainVIew, type=="number" && styles.numberTileBg,
            type=="special" && styles.specialTileBg,
            type=="operation" && styles.operationTileBg
        ]}
        >
            <TouchableOpacity
            activeOpacity={0.2}
            style={styles.btn}
            onPress={handlePress}
            >
                <ThemedText type={'title'} style={styles.tileText}>
                    {text}
                </ThemedText>
            </TouchableOpacity>

        </ThemedView>
    )
}

const styles = StyleSheet.create({
    mainVIew:{
        flex:1,
        borderWidth:3,
        borderRadius:10,
        alignItems:'center',
        justifyContent:'center',
    },
    specialTileBg:{
        backgroundColor:'orange'
    },
    numberTileBg:{
        backgroundColor: "black"
    },
    operationTileBg:{
        backgroundColor:"grey"
    },
    tileText:{
        color:'white',
        alignSelf:'center'
    },
    btn:{
       width:"100%",height:"100%",alignItems:"center",justifyContent:"center"
    }
})