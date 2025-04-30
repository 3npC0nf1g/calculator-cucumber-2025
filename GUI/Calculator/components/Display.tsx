import { Button, Text, ScrollView, StyleSheet, TextInput, TextInputKeyPressEventData, Touchable, TouchableOpacity } from 'react-native';

import { useOperation } from "@/context/OperationContext";
import { ThemedText } from './ThemedText';
import { ThemedView } from './ThemedView';
import { useEffect } from 'react';




export default function Display() {
    // Access the operation value from the context.
    const { operation, updateOperation, operationHistory } = useOperation();

    const handleChangeText = (text: string) => {
        const filtered = text.replace(/^[0-9+\-*/.()×]/g, '');
        updateOperation(filtered);
    };



    return (
        <ThemedView style={styles.mainView}>
            {
                operationHistory.length > 0 &&

                <ScrollView style={styles.scrollView} contentContainerStyle={styles.contentView}>

                    {
                        operationHistory.map((elem, index) => {
                            return (
                                <TouchableOpacity
                                    style={styles.historyBtn}
                                    key={index}

                                >
                                    <Text
                                        style={styles.historyBtnText}
                                    >
                                        {"=> " + elem.operation + `\n = ` + elem.result}
                                    </Text>
                                </TouchableOpacity>
                            )
                        }
                        )
                    }

                </ScrollView>
            }
            <TextInput
                style={styles.textInput}
                value={operation}
                placeholder="0"
                editable={true}
                multiline={true}
                onChangeText={handleChangeText} />
        </ThemedView>


    );
}
const styles = StyleSheet.create({
    mainView: {
        borderRadius: 10,
        borderWidth: 3,
        maxHeight: 300,
        width: "100%",
        gap: 10,
        alignSelf: "center",
        marginVertical: 10,
    },
    scrollView: {
        width: "100%",
        height: "100%",
    },
    contentView: {
        justifyContent: "flex-start",
        flexGrow: 1,
        alignItems: "flex-start",

    },
    textInput: {
        minHeight: 60,
        width: "100%",
        height: "100%",
        paddingHorizontal: 20,
        paddingVertical: 10,
        backgroundColor: "white",
        fontSize: 30,
        fontWeight: "bold",
        textAlign: "left",
        textAlignVertical: "top"
    },
    historyBtn: {
        width: '100%',
        backgroundColor: "white",
        borderBottomWidth: 2
    },
    historyBtnText: {
        fontSize: 30,
        fontWeight: "bold",
        textAlign: "left",
        textAlignVertical: "top"
    }
});