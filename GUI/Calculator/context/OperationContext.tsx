import axios from 'axios';
import React, { createContext, useContext, useMemo, useState, ReactNode } from 'react';
import { Alert } from 'react-native';

type HistoryEntry = { operation: string, result: string, request: string };
// Define the shape of our context
type OperationContextType = {
    operation: string;
    operationRequest: string;
    updateOperation: (newOperation: string) => void;
    updateOperationRequest: (newOperation: string) => void;
    appendOperation: (value: string) => void;
    appendOperationRequest: (value: string) => void;
    clearOperation: () => void;
    clearOperationRequest: () => void;
    sendOperation: () => Promise<string>;
    operationHistory: HistoryEntry[];
    clearOperationHistory: () => void;
    clearAll: () => void;
    result: string;
    backPress: () => void;
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
        setOperationHistory([])
    };

    const removeOneOperation = () => {
        setOperation(prev => prev.slice(0, -1));
    }
    const removeOneOperationRequest = () => {
        setOperationRequest(prev => prev.slice(0, -1));
    }
    const backPress = () => {
        if (operation.length > 0) {
            removeOneOperation();
            removeOneOperationRequest();
        }
    }
    const clearAll = (): void => {
        setOperation('');
        setOperationRequest('');
        setOperationHistory([]);
    };
    const sendOperation = async () => {
        console.log("Sending operation:", operationRequest);
        const encoded = encodeURIComponent(operationRequest);

        let result;
        try {
            result = await axios.get(`http://localhost:8080/api/evaluate?expression=${encoded}`)
        } catch (error) {
            Alert.alert("API error:", error instanceof Error ? error.message : String(error));
        };

        console.log(result?.data);
        setResult(result?.data);
        addOperationToHistory({ operation: operation, result: result?.data, request: operationRequest });
        clearOperation();
        setOperation(result?.data);
        clearOperationRequest();
        setOperationRequest(result?.data);
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
        operationRequest,
        updateOperationRequest,
        appendOperationRequest,
        clearOperationRequest,
        clearAll,
        backPress,
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
