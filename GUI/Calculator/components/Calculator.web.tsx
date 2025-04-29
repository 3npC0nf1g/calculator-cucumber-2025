import { StyleSheet } from 'react-native';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import { useState } from "react";
import CalculatorTile from "@/components/CalculatorTile";
import { BASE_ROWS } from '@/constants/BaseButtons';



export default function Calculator() {

    const WEB_ROWS = BASE_ROWS.map((item) => item.filter((txt) => txt.text !== "⌫"))
    return (
        <ThemedView style={styles.mainView}>
            {WEB_ROWS.map((row, rowIdx) => (
                <ThemedView style={styles.row} key={rowIdx}>

                    {row.map((txt) => (
                        <CalculatorTile
                            key={txt.key}
                            text={txt.text}
                            type={txt.type}
                            value={txt.value}
                            form="standard"
                        />
                    ))}
                </ThemedView>
            ))}
        </ThemedView>
    )

};

const styles = StyleSheet.create({


    mainView: {
        flex: 1
    },
    row: {
        flex: 1,
        flexDirection: 'row',
    },

})