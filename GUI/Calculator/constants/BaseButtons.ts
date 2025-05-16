type SciButtonType = "special" | "operation" | "number";
interface SciButton {
    key: string;
    value: string;
    text: string;
    type: SciButtonType;
}

export const BASE_ROWS: SciButton[][] = [
    [
        { key: "clear", value: "C", text: "C", type: "special" },
        { key: "parensOpen", value: "(", text: "(", type: "operation" },
        { key: "parensClosed", value: ")", text: ")", type: "operation" },
        { key: "backspace", value: "backspace()", text: "⌫", type: "special" },
    ],
    [

        { key: "7", value: "7", text: "7", type: "number" },
        { key: "8", value: "8", text: "8", type: "number" },
        { key: "9", value: "9", text: "9", type: "number" },
        { key: "square", value: "^2", text: "x²", type: "operation" },


    ],
    [
        { key: "4", value: "4", text: "4", type: "number" },
        { key: "5", value: "5", text: "5", type: "number" },
        { key: "6", value: "6", text: "6", type: "number" },
        { key: "divide", value: "/", text: "÷", type: "operation" },


    ],
    [

        { key: "1", value: "1", text: "1", type: "number" },
        { key: "2", value: "2", text: "2", type: "number" },
        { key: "3", value: "3", text: "3", type: "number" },
        { key: "times", value: "*", text: "×", type: "operation" },


    ],
    [
        { key: "0", value: "0", text: "0", type: "number" },
        { key: "percent", value: "%", text: "%", type: "operation" },
        { key: "plus", value: "+", text: "+", type: "operation" },
        { key: "minus", value: "-", text: "−", type: "operation" },

    ],
    [{ key: "equals", value: "=", text: "=", type: "special" },
    { key: "comma", value: ".", text: ",", type: "operation" },

    ]
];
