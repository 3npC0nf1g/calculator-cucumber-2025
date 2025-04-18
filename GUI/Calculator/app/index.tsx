import {Image, StyleSheet, Platform, View, KeyboardAvoidingView, TouchableOpacity} from 'react-native';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import Calculator from "@/components/Calculator";
import Display from "@/components/Display";
import {OperationProvider} from "@/components/OperationContext";
import {Ionicons, MaterialCommunityIcons} from "@expo/vector-icons";
import {useState} from "react";
import DisplayScience from "@/components/DsiplayScience.web";
import CalculatorScience from "@/components/CalculatorScience.web";

export default function HomeScreen() {
    const isWeb = Platform.OS === 'web';
    const [type,setType]=useState<boolean>(false)

    // choose container component & props
    const Container = isWeb ? View : KeyboardAvoidingView;
    return (
        <ThemedView style={styles.mainView}>
            <OperationProvider>
                {Platform.OS==="web" &&

                <ThemedView style={styles.btnRow}>

                    <ThemedView>
                        <TouchableOpacity
                            disabled={type}
                            onPress={()=>{
                                setType(true)
                            }}>
                            <MaterialCommunityIcons name={"calculator-variant-outline"} size={60} style={styles.icon}/>
                        </TouchableOpacity>
                        {type &&
                            <View style={styles.underline}/>
                        }
                    </ThemedView>

                    <ThemedView>
                        <TouchableOpacity
                            disabled={!type}
                            onPress={()=>{
                                setType(false)
                            }}
                        >
                            <MaterialCommunityIcons name={"square-root"} size={60} style={styles.icon}/>
                        </TouchableOpacity>
                        {!type &&
                            <View style={styles.underline}/>
                        }
                    </ThemedView>


                </ThemedView>
                }
                <ThemedView style={styles.titleContainer}>
                    <ThemedText type="title">Cucumber Calculator</ThemedText>
                </ThemedView>

                <ThemedView style={styles.calculatorView}>
                    {/* on web: simple View; on mobile: KeyboardAvoidingView */}
                    <Container
                        style={{ flex: 1 }}
                        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}  // ← invalid on View
                    >

                        {type?
                            <>
                                <Display />
                                <Calculator />
                            </>

                            :
                            <>
                            <DisplayScience/>
                                <CalculatorScience/>
                            </>
               }

                    </Container>
                </ThemedView>
            </OperationProvider>
        </ThemedView>
    );
}

const styles = StyleSheet.create({
    mainView:{
        flex:1,
        alignItems:'center',
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
    btnRow:{
        flexDirection:'row',
        justifyContent:'center',
        alignSelf:'flex-start',
        marginLeft:'2%',
        gap:20
    },
    icon:{
    color:'orange'
    },
    underline:{
        width:"100%",
        backgroundColor:'orange',
        height:5

    }
});
