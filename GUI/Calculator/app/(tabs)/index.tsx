import {Image, StyleSheet, Platform, View, KeyboardAvoidingView} from 'react-native';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import Calculator from "@/components/Calculator";
import Display from "@/components/Display";
import {OperationProvider} from "@/components/OperationContext";

export default function HomeScreen() {
  return (
    <ThemedView style={ styles.mainView}>
        <OperationProvider>
            <ThemedView style={styles.titleContainer}>
                <ThemedText type="title">Cucumber Calculator</ThemedText>
            </ThemedView>

            <ThemedView style={styles.calculatorView}>
                <KeyboardAvoidingView style={{flex:1}}
                                      behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
                >
                    <Display/>
                    <Calculator/>
                </KeyboardAvoidingView>

            </ThemedView>
        </OperationProvider>

    </ThemedView>
  );
}

const styles = StyleSheet.create({
    mainView:{
        flex:1,
        alignItems:'center',
        justifyContent:'center'
    },
  titleContainer: {
    alignItems: 'center',

  },
    calculatorView:{
        width:"80%",
        height:"80%",
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
});
