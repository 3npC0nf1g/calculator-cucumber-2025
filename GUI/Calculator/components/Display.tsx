import { Button, Text, ScrollView, StyleSheet, TextInput, TextInputKeyPressEventData, Touchable, TouchableOpacity, Platform, NativeSyntheticEvent } from 'react-native';

import { useOperation } from "@/context/OperationContext";
import { ThemedText } from './ThemedText';
import { ThemedView } from './ThemedView';
import { useEffect, useRef, useState } from 'react';
import { Ionicons } from '@expo/vector-icons';




export default function Display() {
    const isWeb = Platform.OS === 'web';



    const { operation, updateOperation, operationHistory, updateOperationRequest, operationRequest, toastMessage } = useOperation();
    const handleChangeText = (text: string) => {
        const filtered = text.replace(/^[0-9+\-*/.()×]/g, '');
        updateOperation(filtered);
    };
    const handleKeyPress = (e: NativeSyntheticEvent<TextInputKeyPressEventData>) => {
        const key = e.nativeEvent.key;

        if (key === 'Backspace') {
            updateOperation(operation.slice(0, -1));
            updateOperationRequest(operationRequest.slice(0, -1));
        }


    };


    const scrollRef = useRef<ScrollView | null>(null);


    return (
        <ThemedView style={isWeb ? styles.mainView : [styles.mainView]}>


            <ThemedView style={isWeb ? styles.historyView : [styles.historyView, { borderRadius: 10, padding: 10, height: "40%", backgroundColor: 'white' }]}>
                <Ionicons name="time-outline" size={35} color="orange" style={styles.icon} />

                {
                    operationHistory.length > 0 &&

                    <ScrollView style={styles.scrollView} contentContainerStyle={isWeb ? styles.contentView : [styles.contentView, { backgroundColor: 'white' }]}

                        ref={scrollRef}
                        onContentSizeChange={() => {
                            scrollRef.current?.scrollToEnd({ animated: true });
                        }}
                        onLayout={() => {
                            scrollRef.current?.scrollToEnd({ animated: true });
                        }}
                        showsVerticalScrollIndicator={false}
                        showsHorizontalScrollIndicator={false}
                        keyboardShouldPersistTaps="handled"
                        keyboardDismissMode="on-drag"

                    >

                        {
                            operationHistory.map((elem, index) => {
                                return (
                                    <TouchableOpacity
                                        style={isWeb ? styles.historyBtn : [styles.historyBtn, { borderWidth: 0, borderBottomWidth: 1, width: "90%", height: "100%" }]}
                                        key={index}
                                        onPress={() => {
                                            updateOperation(elem.operation);
                                            updateOperationRequest(elem.request);
                                        }}

                                    >
                                        <Text
                                            style={styles.historyBtnText}
                                        >
                                            {elem.operation + `\n = ` + elem.result}
                                        </Text>
                                    </TouchableOpacity>
                                )
                            }
                            )
                        }

                    </ScrollView>
                }
            </ThemedView>
            <TextInput
                style={isWeb ? styles.textInput : [styles.textInput, { borderRadius: 30, height: "50%" }]}
                value={operation}
                placeholder="0"
                editable={isWeb}
                multiline={true}
                onKeyPress={handleKeyPress}
                onChangeText={handleChangeText} />
        </ThemedView>


    );
}
const styles = StyleSheet.create({
    mainView: {
        minHeight: 300,
        maxHeight: 300,
        width: "100%",
        gap: 10,
        alignSelf: "center",
        marginVertical: 10,
    },
    icon: {
        position: 'absolute',
        right: 5,
        top: 5,
        zIndex: 3,
    },
    historyView: {
        width: "100%",
        height: "50%",
        backgroundColor: "#ECECEC",
        borderColor: "black",
    },
    scrollView: {
        width: "100%",
        height: "100%",
    },
    contentView: {
        justifyContent: "flex-start",
        flexGrow: 1,
        alignItems: "flex-start",
        marginTop: 10,
        marginRight: 10,

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
        borderWidth: 1,
        borderColor: "grey",
        width: '100%',
        backgroundColor: "white",

        margin: 5,
        padding: 10,
    },
    historyBtnText: {
        fontSize: 30,
        fontWeight: "bold",
        textAlign: "left",
        textAlignVertical: "top"
    }
});