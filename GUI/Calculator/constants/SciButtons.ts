type SciButtonType = "special" | "operation" | "number";
interface SciButton {
    key: string;             // unique React key / lookup id
    value: string;             // what goes into your eval engine
    text: string;             // what the user sees
    type: SciButtonType;      // category for styling / logic
}

export const SCI_ROWS: SciButton[][] = [
    [
        { key: "toggleDegRad", value: "toggleDegRad()", text: "↔", type: "special" },
        { key: "sqrt", value: "√(", text: "√", type: "operation" },
        { key: "clear", value: "C", text: "C", type: "special" },
        { key: "parensOpen", value: "(", text: "(", type: "operation" },
        { key: "parensClosed", value: ")", text: ")", type: "operation" },
        { key: "percent", value: "%", text: "%", type: "operation" },
        { key: "divide", value: "/", text: "÷", type: "operation" },

    ],
    [
        { key: "sin", value: "sin(", text: "sin", type: "operation" },
        { key: "cos", value: "cos(", text: "cos", type: "operation" },
        { key: "tan", value: "tan(", text: "tan", type: "operation" },
        { key: "7", value: "7", text: "7", type: "number" },
        { key: "8", value: "8", text: "8", type: "number" },
        { key: "9", value: "9", text: "9", type: "number" },
        { key: "times", value: "*", text: "×", type: "operation" },
    ],
    [
        { key: "ln", value: "ln(", text: "ln", type: "operation" },
        { key: "log", value: "log(", text: "log", type: "operation" },
        { key: "recip", value: "1/(", text: "1/x", type: "operation" },
        { key: "4", value: "4", text: "4", type: "number" },
        { key: "5", value: "5", text: "5", type: "number" },
        { key: "6", value: "6", text: "6", type: "number" },
        { key: "minus", value: "-", text: "−", type: "operation" },
    ],
    [
        { key: "exp", value: "e^(", text: "eˣ", type: "operation" },
        { key: "square", value: "^2", text: "x²", type: "operation" },
        { key: "power", value: "^", text: "xʸ", type: "operation" },
        { key: "1", value: "1", text: "1", type: "number" },
        { key: "2", value: "2", text: "2", type: "number" },
        { key: "3", value: "3", text: "3", type: "number" },
        { key: "plus", value: "+", text: "+", type: "operation" },
    ],
    [
        { key: "abs", value: "abs(", text: "|x|", type: "operation" },
        { key: "comma", value: ".", text: ",", type: "operation" },
        { key: "pi", value: "π", text: "π", type: "number" },
        { key: "e", value: "e", text: "e", type: "number" },
        { key: "0", value: "0", text: "0", type: "number" },
        { key: "equals", value: "=", text: "=", type: "special" },
    ],
];

export const SCI_ROWS_MOBILE: SciButton[][] = [
    [
        { key: "toggleDegRad", value: "toggleDegRad()", text: "↔", type: "special" },
        { key: "sqrt", value: "√(", text: "√", type: "operation" },
        { key: "clear", value: "C", text: "C", type: "special" },
        { key: "parensOpen", value: "(", text: "(", type: "operation" },
        { key: "parensClosed", value: ")", text: ")", type: "operation" },
        { key: "percent", value: "%", text: "%", type: "operation" },
        { key: "divide", value: "/", text: "÷", type: "operation" },

    ],
    [
        { key: "sin", value: "sin(", text: "sin", type: "operation" },
        { key: "cos", value: "cos(", text: "cos", type: "operation" },
        { key: "tan", value: "tan(", text: "tan", type: "operation" },
        { key: "7", value: "7", text: "7", type: "number" },
        { key: "8", value: "8", text: "8", type: "number" },
        { key: "9", value: "9", text: "9", type: "number" },
        { key: "times", value: "*", text: "×", type: "operation" },
    ],
    [
        { key: "ln", value: "ln(", text: "ln", type: "operation" },
        { key: "log", value: "log(", text: "log", type: "operation" },
        { key: "recip", value: "1/(", text: "1/x", type: "operation" },
        { key: "4", value: "4", text: "4", type: "number" },
        { key: "5", value: "5", text: "5", type: "number" },
        { key: "6", value: "6", text: "6", type: "number" },
        { key: "minus", value: "-", text: "−", type: "operation" },
    ],
    [
        { key: "exp", value: "e^(", text: "eˣ", type: "operation" },
        { key: "square", value: "^2", text: "x²", type: "operation" },
        { key: "power", value: "^", text: "xʸ", type: "operation" },
        { key: "1", value: "1", text: "1", type: "number" },
        { key: "2", value: "2", text: "2", type: "number" },
        { key: "3", value: "3", text: "3", type: "number" },
        { key: "plus", value: "+", text: "+", type: "operation" },
    ],
    [
        { key: "abs", value: "abs(", text: "|x|", type: "operation" },
        { key: "equals", value: "=", text: "=", type: "special" },
        { key: "0", value: "0", text: "0", type: "number" },
        { key: "pi", value: "π", text: "π", type: "number" },
        { key: "e", value: "e", text: "e", type: "number" },
        { key: "comma", value: ".", text: ",", type: "operation" },


    ],

];
