import { Image, StyleSheet, Platform, View, KeyboardAvoidingView, TouchableOpacity } from 'react-native';
import * as ScreenOrientation from 'expo-screen-orientation';

import { ThemedView } from '@/components/ThemedView';
import Calculator from "@/components/Calculator";
import Display from "@/components/Display";
import { OperationProvider } from "@/context/OperationContext";
import { Ionicons, MaterialCommunityIcons } from "@expo/vector-icons";
import React, { useState } from "react";
import DisplayScience from "@/components/DsiplayScience.web";

import CalculatorScienceMobile from '@/components/CalculatorScience';
import CalculatorScienceWeb from '@/components/CalculatorScience.web';
export default function HomeScreen() {
    const isWeb = Platform.OS === 'web';
    const [type, setType] = useState<boolean>(true)
    const [historyBtn, setHistoryBtn] = useState<boolean>(false)



    const Container = isWeb ? View : KeyboardAvoidingView;
    return (
        <ThemedView style={styles.mainView}>
            <OperationProvider>

                <ThemedView style={[styles.btnRow, !type && { flexDirection: "column", position: "absolute" }]}>

                    <ThemedView>
                        <TouchableOpacity
                            disabled={type}
                            onPress={() => {
                                setType(true)
                                if (!isWeb)
                                    ScreenOrientation.lockAsync(ScreenOrientation.OrientationLock.PORTRAIT_UP);
                            }}>
                            <MaterialCommunityIcons name={"calculator-variant-outline"} size={isWeb ? 60 : 30} style={styles.icon} />
                        </TouchableOpacity>
                        {type &&
                            <View style={styles.underline} />
                        }
                    </ThemedView>

                    <ThemedView>
                        <TouchableOpacity
                            disabled={!type}
                            onPress={() => {
                                setType(false)
                                setHistoryBtn(false)
                                if (!isWeb)
                                    ScreenOrientation.lockAsync(ScreenOrientation.OrientationLock.LANDSCAPE_RIGHT);
                            }}
                        >
                            <MaterialCommunityIcons name={"square-root"} size={isWeb ? 60 : 30} style={styles.icon} />
                        </TouchableOpacity>
                        {!type &&
                            <View style={styles.underline} />
                        }
                    </ThemedView>

                    {!isWeb && !type
                        &&
                        <ThemedView>
                            <TouchableOpacity
                                onPress={() => {
                                    setHistoryBtn(!historyBtn)
                                }}
                            >
                                {historyBtn ?
                                    <Ionicons name="calculator-outline" size={35} color="orange" style={styles.icon} />
                                    :
                                    <Ionicons name="time-outline" size={35} color="orange" style={styles.icon} />
                                }

                            </TouchableOpacity>
                            <View style={styles.underline} />

                        </ThemedView>}



                </ThemedView>

                <ThemedView style={styles.calculatorView}>
                    {/* on web: simple View; on mobile: KeyboardAvoidingView */}
                    <Container
                        style={{ flex: 1 }}
                        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
                    >

                        {type ?
                            <>
                                <Display />
                                <Calculator />
                            </>

                            :
                            <>
                                <DisplayScience showHistory={historyBtn} />
                                {isWeb ?
                                    <CalculatorScienceWeb />
                                    :
                                    <CalculatorScienceMobile />
                                }

                            </>
                        }

                    </Container>
                </ThemedView>
            </OperationProvider>
        </ThemedView>
    );
}

const styles = StyleSheet.create({
    mainView: {
        flex: 1,
        alignItems: 'center',
        gap: 20,
    },
    titleContainer: {
        alignItems: 'center',

    },
    calculatorView: {
        width: "80%",
        height: "80%",
    },
    stepContainer: {
        gap: 8,
        marginBottom: 8,
    },
    reactLogo: {
        height: 178,
        width: 290,
        bottom: 0,
        left: 0,
        position: 'absolute',
    },
    btnRow: {
        flexDirection: 'row',
        justifyContent: 'center',
        alignSelf: 'flex-start',
        marginLeft: '2%',
        gap: 20
    },
    icon: {
        color: 'orange'
    },
    underline: {
        width: "100%",
        backgroundColor: 'orange',
        height: 5

    }
});
