import { NativeSyntheticEvent, StyleSheet, TextInput, TextInputKeyPressEventData } from 'react-native';

import { useOperation } from "@/components/OperationContext";




export default function Display() {
    // Access the operation value from the context.
    const { operation, updateOperation } = useOperation();

    const handleChangeText = (text: string) => {
        const filtered = text.replace(/^[0-9+\-*/.()×]/g, '');

        updateOperation(filtered);
    };

    return (
        <TextInput
            style={styles.textInput}
            value={operation}
            placeholder="0"
            onChangeText={handleChangeText}
        />
    );
}
const styles = StyleSheet.create({
    textInput: {
        minHeight: 60,
        height: "10%",
        width: "100%",
        borderRadius: 10,
        borderWidth: 3,
        backgroundColor: "white",
        fontSize: 25,
        textAlign: "left",
        textAlignVertical: 'top'
    }
})