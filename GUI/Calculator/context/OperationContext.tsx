import axios from 'axios';
import React, { createContext, useContext, useMemo, useState, ReactNode } from 'react';
import { Alert } from 'react-native';

type HistoryEntry = { operation: string, result: string }
// Define the shape of our context
type OperationContextType = {
    operation: string;
    operationRequest: string;
    updateOperation: (newOperation: string) => void;
    appendOperation: (value: string) => void;
    clearOperation: () => void;
    sendOperation: () => Promise<string>;
    operationHistory: HistoryEntry[];
    clearOperationHistory: () => void;
    result: string;
};

// Create the context. We use undefined as the default value.
const OperationContext = createContext<OperationContextType | undefined>(undefined);

type OperationProviderProps = {
    children: ReactNode;
};

// Create a functional provider component
export const OperationProvider: React.FC<OperationProviderProps> = ({ children }) => {
    const [operation, setOperation] = useState<string>('');
    const [operationRequest, setOperationRequest] = useState<string>('')

    const updateOperation = (newOperation: string) => setOperation(newOperation);
    const updateOperationRequest = (newOperation: string) => setOperationRequest(newOperation);

    const appendOperation = (value: string) => setOperation(prev => prev + value);
    const appendOperationRequest = (value: string) => setOperationRequest(prev => prev + value);

    const clearOperation = () => setOperation('');
    const clearOperationRequest = () => setOperationRequest('');


    const [result, setResult] = useState<string>('0');
    const [operationHistory, setOperationHistory] = useState<HistoryEntry[]>([]);
    const addOperationToHistory = (entry: HistoryEntry) => {
        setOperationHistory(prev => [...prev, entry]);
    };
    const clearOperationHistory = () => {
        console.log("ééééé");
        setOperationHistory([])
    };

    const sendOperation = async () => {
        const encoded = encodeURIComponent(operation);

        let result;
        try {
            result = await axios.get(`http://localhost:8080/api/evaluate?expression=${encoded}`)
        } catch (error) {
            Alert.alert("API error:", error instanceof Error ? error.message : String(error));
        };

        console.log(result?.data);
        setResult(result?.data);
        addOperationToHistory({ operation: operation, result: result?.data })
        clearOperation();
        return result?.data;

    }

    const contextValue = useMemo(() => ({
        operation,
        updateOperation,
        appendOperation,
        clearOperation,
        result,
        sendOperation,
        operationHistory,
        clearOperationHistory,
        operationRequest
    }), [operation]);

    return (
        <OperationContext.Provider value={contextValue}>
            {children}
        </OperationContext.Provider>
    );
};

// Custom hook to consume the context
export const useOperation = (): OperationContextType => {
    const context = useContext(OperationContext);
    if (!context) {
        throw new Error('useOperation must be used within an OperationProvider');
    }
    return context;
};
