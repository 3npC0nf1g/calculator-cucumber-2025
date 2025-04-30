import { useOperation } from '@/context/OperationContext';
import { KeyboardAvoidingView, StyleSheet, TextInput } from 'react-native';





export default function DisplayScience() {
    // Access the operation value from the context.
    const { operation, appendOperation } = useOperation();

    return (
        <TextInput
            style={styles.textInput}
            value={operation}
            placeholder="0"
            multiline={true}
            numberOfLines={4}
            editable={false}
        />
    );
}
const styles = StyleSheet.create({
    textInput: {
        minHeight: 60,
        height: "20%",
        width: "100%",
        borderRadius: 10,
        borderWidth: 3,
        backgroundColor: "white",
        fontSize: 25,
        textAlign: "left",
        textAlignVertical: 'center'
    }
})