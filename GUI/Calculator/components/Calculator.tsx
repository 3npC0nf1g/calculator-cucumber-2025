import { StyleSheet} from 'react-native';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import {useState} from "react";
import CalculatorTile from "@/components/CalculatorTile";



export default function Calculator() {
    const [numbers,setNumbers]=useState<number[]>([])


    return(
        <ThemedView style={styles.mainView}>
            <ThemedView style={styles.tileRow}>
                <CalculatorTile type={"number"} text={"7"}/>
                <CalculatorTile type={"number"} text={"8"}/>
                <CalculatorTile type={"number"} text={"9"}/>
                <CalculatorTile type={"operation"} text={"/"}/>
            </ThemedView>
            <ThemedView style={styles.tileRow}>
                <CalculatorTile type={"number"} text={"4"}/>
                <CalculatorTile type={"number"} text={"5"}/>
                <CalculatorTile type={"number"} text={"6"}/>
                <CalculatorTile type={"operation"} text={"*"}/>
            </ThemedView>
            <ThemedView style={styles.tileRow}>
                <CalculatorTile type={"number"} text={"1"}/>
                <CalculatorTile type={"number"} text={"2"}/>
                <CalculatorTile type={"number"} text={"3"}/>
                <CalculatorTile type={"operation"} text={"-"}/>
            </ThemedView>
            <ThemedView style={styles.tileRow}>
                <CalculatorTile type={"number"} text={"0"}/>
                <CalculatorTile type={"operation"} text={"."}/>
                <CalculatorTile type={"special"} text={"C"}/>
                <CalculatorTile type={"operation"} text={"+"}/>
            </ThemedView>
            <ThemedView style={styles.tileRow}>
                <CalculatorTile type={"special"} text={"="}/>

            </ThemedView>


        </ThemedView>
    )

};

const styles = StyleSheet.create({

mainView:{
    flex:1
},
tileRow:{
    flexDirection:'row',
  flex:1
}

})