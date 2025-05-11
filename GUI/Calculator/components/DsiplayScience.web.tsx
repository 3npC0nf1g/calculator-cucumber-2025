import { Button, Text, ScrollView, StyleSheet, TextInput, TextInputKeyPressEventData, Touchable, TouchableOpacity, Platform } from 'react-native';

import { useOperation } from "@/context/OperationContext";
import { ThemedText } from './ThemedText';
import { ThemedView } from './ThemedView';
import { useEffect, useRef, useState } from 'react';
import { Ionicons } from '@expo/vector-icons';




export default function DisplayScience({ showHistory }: { showHistory: boolean }) {
    // Access the operation value from the context.
    const { operation, updateOperation, operationHistory, updateOperationRequest } = useOperation();

    const handleChangeText = (text: string) => {
        const filtered = text.replace(/^[0-9+\-*/.()×]/g, '');
        updateOperation(filtered);
    };

    const isWeb: boolean = Platform.OS === 'web';
    const scrollRef = useRef<ScrollView | null>(null);


    return (
        <>

            {isWeb ?
                <>
                    <ThemedView style={isWeb ? styles.mainView : styles.mainViewSmall}>

                        <ThemedView style={styles.historyView}>
                            <Ionicons name="time-outline" size={35} color="orange" style={styles.icon} />

                            {
                                operationHistory.length > 0 &&

                                <ScrollView style={styles.scrollView} contentContainerStyle={styles.contentView}

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
                                                    style={styles.historyBtn}
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
                            style={styles.textInput}
                            value={operation}
                            placeholder="0"
                            editable={true}
                            multiline={true}
                            onChangeText={handleChangeText} />
                    </ThemedView>
                </> :
                <>
                    <ThemedView style={isWeb ? styles.mainView : styles.mainViewSmall}>
                        {showHistory ?

                            <ThemedView style={[styles.historyView, { height: "100%" }]}>
                                <Ionicons name="time-outline" size={35} color="orange" style={styles.icon} />

                                {
                                    operationHistory.length > 0 &&

                                    <ScrollView style={styles.scrollView} contentContainerStyle={styles.contentView}

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
                                                        style={styles.historyBtn}
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
                            </ThemedView> :
                            <TextInput
                                style={styles.textInput}
                                value={operation}
                                placeholder="0"
                                editable={true}
                                multiline={true}
                                onChangeText={handleChangeText} />
                        }
                    </ThemedView>

                </>

            }

        </>

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
    mainViewSmall: {
        height: '35%',
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