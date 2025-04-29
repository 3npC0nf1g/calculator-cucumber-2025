import { StyleSheet } from 'react-native';

import { ThemedView } from '@/components/ThemedView';
import CalculatorTile from "@/components/CalculatorTile";
import { SCI_ROWS } from '@/constants/SciButtons';






export default function CalculatorScienceWeb() {

    return (
        <ThemedView style={styles.mainView}>
            {SCI_ROWS.map((row, rowIdx) => (
                <ThemedView style={styles.row} key={rowIdx}>
                    {row.map((txt) => (
                        <CalculatorTile
                            key={txt.key}
                            text={txt.text}
                            type={txt.type}
                            value={txt.value}
                            form='scientific'
                        />
                    ))}
                </ThemedView>
            ))}
        </ThemedView>
    );
}

const styles = StyleSheet.create({

    mainView: {
        flex: 1
    },
    row: {
        flex: 1,
        flexDirection: 'row',
    },

})