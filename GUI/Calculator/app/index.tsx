import { Image, StyleSheet, Platform, View, KeyboardAvoidingView, TouchableOpacity, Button } from 'react-native';

import { ThemedView } from '@/components/ThemedView';
import Calculator from "@/components/Calculator";
import Display from "@/components/Display";
import { OperationProvider } from "@/context/OperationContext";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import React, { useState } from "react";
import DisplayScience from "@/components/DsiplayScience.web";

import * as ScreenOrientation from 'expo-screen-orientation';
import CalculatorScienceMobile from '@/components/CalculatorScience';
import CalculatorScienceWeb from '@/components/CalculatorScience.web';
export default function HomeScreen() {
    const isWeb = Platform.OS === 'web';
    const [type, setType] = useState<boolean>(true)




    // choose container component & props
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
                                // ScreenOrientation.lockAsync(ScreenOrientation.OrientationLock.PORTRAIT_UP);
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
                                // ScreenOrientation.lockAsync(ScreenOrientation.OrientationLock.LANDSCAPE_RIGHT);
                            }}
                        >
                            <MaterialCommunityIcons name={"square-root"} size={isWeb ? 60 : 30} style={styles.icon} />
                        </TouchableOpacity>
                        {!type &&
                            <View style={styles.underline} />
                        }
                    </ThemedView>


                </ThemedView>

                <ThemedView style={styles.calculatorView}>
                    {/* on web: simple View; on mobile: KeyboardAvoidingView */}
                    <Container
                        style={{ flex: 1 }}
                        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}  // ← invalid on View
                    >

                        {type ?
                            <>
                                <Display />
                                <Calculator />
                            </>

                            :
                            <>
                                <DisplayScience />
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
