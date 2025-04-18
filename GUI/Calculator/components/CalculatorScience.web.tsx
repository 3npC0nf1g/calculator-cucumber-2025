import { StyleSheet} from 'react-native';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import {useState} from "react";
import CalculatorTile from "@/components/CalculatorTile";

const SCI_ROWS: string[][] = [
    ['↔',  'Rad',  '√',   'C',   '()',  '%',   '÷'  ],
    ['sin','cos',  'tan', '7',   '8',   '9',   '×'  ],
    ['ln', 'log',  '1/x', '4',   '5',   '6',   '−'  ],
    ['eˣ', 'x²',   'xʸ',  '1',   '2',   '3',   '+'  ],
    ['|x|','π',    'e',   '0',   ',',   '='  ],
];

function classify(text: string): 'number' | 'operation' | 'special' {
    if (/^[0-9]$/.test(text)) return 'number';
    if (['C','=', '±','()','↔','Rad'].includes(text)) return 'special';
    return 'operation';
}

export default function CalculatorScience() {
    const [numbers,setNumbers]=useState<number[]>([])

    return (
        <ThemedView style={styles.mainView}>
            {SCI_ROWS.map((row, rowIdx) => (
                <ThemedView style={styles.row} key={rowIdx}>
                    {row.map((txt) => (
                        <CalculatorTile
                            key={txt}
                            text={txt}
                            type={classify(txt)}
                        />
                    ))}
                </ThemedView>
            ))}
        </ThemedView>
    );
}

const styles = StyleSheet.create({

    mainView:{
        flex:1
    },
    row: {
        flex: 1,
        flexDirection: 'row',
    },

})