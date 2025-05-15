

import { Platform, StyleSheet, TouchableOpacity } from 'react-native';

import { ThemedText } from '@/components/ThemedText';
import { ThemedView } from '@/components/ThemedView';
import { useOperation } from "@/context/OperationContext";

type CalculatorTileProps = {
    type: "number" | "special" | "operation",
    text: string,
    value: string
    form: "scientific" | "standard"
}

export default function CalculatorTile({ type, text, value, form }: Readonly<CalculatorTileProps>) {
    const { appendOperation, clearOperation, operation, sendOperation, clearOperationRequest, toggleDegRad, toggleFracDec, appendOperationRequest, clearAll, backPress, operationRequest } = useOperation();

    const big: boolean = Platform.OS !== "web" && form === "scientific";
    const isWeb: boolean = Platform.OS === "web";

    const handlePress = () => {

        console.log("text", operation, "request", operationRequest);
        switch (type) {
            case "number":
                if (operation === "0" && value !== ".") {
                    clearOperation();
                    clearOperationRequest();
                    return;
                }
                if (value === "e") {
                    appendOperation(value);
                    appendOperationRequest("exp(1)");
                    return
                }
                if (value === "π") {
                    appendOperation("π");
                    appendOperationRequest("pi");
                    return
                }
                if (value === "1/x") {
                    appendOperationRequest("1/(");
                    appendOperation("1/(");
                    return
                }
                appendOperation(value);
                appendOperationRequest(value);
                break;
            case "special":
                if (value === "C") {
                    clearAll();
                } else if (value === "=") {
                    sendOperation();
                };
                if (value === "backspace()") {
                    backPress();
                }
                if (value === "toggledeg") {
                    toggleDegRad();
                }
                if (value === "togglefrac") {
                    toggleFracDec();
                }
                break;
            case "operation":
                if (value == "%") {
                    appendOperation("%")
                    appendOperationRequest("/100");
                    break;
                }
                if (value == "*") {
                    appendOperation(text)
                    appendOperationRequest(value);
                    break;
                } if (value === "^2") {
                    console.log("operation", operation);
                    const match = operation.match(/(?:\d+|\))$/);

                    if (match) {
                        const lastOperand = match[0];
                        const start = operation.length - lastOperand.length;

                        const newDisplay = operation.slice(0, start) + `${lastOperand}^2`;
                        clearOperation();
                        appendOperation(newDisplay);

                        const newRequest = operation.slice(0, start) + `power(${lastOperand},2)`;
                        clearOperationRequest();
                        appendOperationRequest(newRequest);
                    }

                    return;
                }
                if (value === "log") {
                    appendOperation("log(");

                    appendOperationRequest("log(10,");

                    return;
                }
                if (value === "ln") {
                    appendOperation("ln(");

                    appendOperationRequest("ln(");

                    return;
                }
                if (value === "exp") {
                    appendOperation("e^(");

                    appendOperationRequest("exp(");

                    return;
                }
                if (value === "^") {
                    const match = operation.match(/(?:\d+(?:\.\d+)?|\))$/);

                    if (match) {
                        const base = match[0];
                        const start = operation.length - base.length;

                        const newDisplay = operation.slice(0, start) + `${base}^(`;
                        clearOperation();
                        appendOperation(newDisplay);

                        const newRequest = operationRequest.slice(0, start) + `power(${base},`;
                        clearOperationRequest();
                        appendOperationRequest(newRequest);
                    } else {

                        appendOperation("^(");
                        appendOperationRequest("power(");
                    }

                    return;
                }
                if (value === "√") {
                    appendOperation("√(");
                    appendOperationRequest("sqrt(");
                    return;
                }
                appendOperation(value);
                appendOperationRequest(value);
                break;
            default:
                break;

        };
    };

    return (
        <ThemedView style={[!big ? styles.mainView : styles.mainViewSmall, type == "number" && styles.numberTileBg,
        type == "special" && styles.specialTileBg,
        type == "operation" && styles.operationTileBg,
        text === "=" && { flexGrow: !big ? 1 : 1 }

        ]}
        >
            <TouchableOpacity
                activeOpacity={0.2}
                style={styles.btn}
                onPress={handlePress}
            >
                <ThemedText type={!big ? 'title' : 'default'} style={isWeb ? styles.tileText : [styles.tileText, { lineHeight: 40 }]}>
                    {text}
                </ThemedText>
            </TouchableOpacity>

        </ThemedView>
    )
}

const styles = StyleSheet.create({
    mainView: {
        flex: 1,
        borderWidth: 1,
        borderRadius: Platform.OS === "web" ? 10 : 30,
        alignItems: 'center',
        justifyContent: 'center',
        borderColor: "white",
        margin: 1
    },
    mainViewSmall: {
        flex: 0.5,
        borderWidth: 1,
        borderRadius: Platform.OS === "web" ? 10 : 30,
        alignItems: 'center',
        justifyContent: 'center',
        borderColor: "white"
    },
    specialTileBg: {
        backgroundColor: 'orange'
    },
    numberTileBg: {
        backgroundColor: "black"
    },
    operationTileBg: {
        backgroundColor: "grey"
    },
    tileText: {
        color: 'white',
        alignSelf: 'center',
        textAlign: 'center',
        fontSize: 30,
        justifyContent: 'center',
    },
    btn: {
        flex: 1,
        width: '100%',
        height: '100%',
        alignItems: "center",
        justifyContent: "center",
        alignContent: 'center',

    },

})