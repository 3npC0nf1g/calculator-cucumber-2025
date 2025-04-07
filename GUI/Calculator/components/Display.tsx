import {KeyboardAvoidingView, StyleSheet, TextInput} from 'react-native';

import { ThemedView } from '@/components/ThemedView';
import {useOperation} from "@/components/OperationContext";




export default function Display() {
    // Access the operation value from the context.
    const { operation ,appendOperation} = useOperation();

    return (
        <TextInput
            style={styles.textInput}
            value={operation}
            placeholder="0"
           editable={false}
        />
    );
}
const styles= StyleSheet.create({
    textInput:{
        minHeight:60,
        height:"10%",
        width:"100%",
        borderRadius:10,
        borderWidth:3,
        backgroundColor:"white",
        fontSize:25,
        textAlign:"left",
        textAlignVertical:'center'
    }
})