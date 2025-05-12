import axios from 'axios';
import React, { createContext, useContext, useMemo, useState, ReactNode } from 'react';
import { Alert } from 'react-native';
import { Platform } from 'react-native';


type HistoryEntry = { operation: string, result: string, request: string };
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
    toggleFracDec: () => Promise<void>;
    toggleDegRad: () => Promise<void>;
    toastMessage: string | null;

};

const OperationContext = createContext<OperationContextType | undefined>(undefined);

type OperationProviderProps = {
    children: ReactNode;
};

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


    const [toastMessage, setToastMessage] = useState<string | null>(null);

    const showToast = (message: string) => {
        setToastMessage(message);
        setTimeout(() => setToastMessage(null), 2000);
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
        const encoded = encodeURIComponent(operationRequest);
        try {
            const result = await axios.get(`http://localhost:8080/api/evaluate?expression=${encoded}`);
            setResult(result.data);
            addOperationToHistory({ operation, result: result.data, request: operationRequest });

            setOperation((String(result.data)));
            setOperationRequest(String(result.data));

            return result.data;
        } catch (error: any) {
            console.error("Error in sendOperation:", error);
            const errorMessage = error.response?.data || "Unexpected error";

            if (Platform.OS === 'web') {
                window.alert("Calculation failed:\n" + errorMessage);
            } else {
                Alert.alert("Calculation failed", errorMessage);
            }
            clearOperation();
            clearOperationRequest();
            return "Error";
        }
    };

    const toggleFracDec = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/toggle-display");
        } catch (error) {
            showToast("Error toggling display mode");
        }
    };

    const toggleDegRad = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/toggle-angle-mode");
        } catch (error) {
            showToast("Error toggling angle mode");
        }
    };

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
        toggleFracDec,
        toggleDegRad,
        toastMessage,
    }), [operation]);

    return (
        <OperationContext.Provider value={contextValue}>
            {children}
        </OperationContext.Provider>
    );
};

export const useOperation = (): OperationContextType => {
    const context = useContext(OperationContext);
    if (!context) {
        throw new Error('useOperation must be used within an OperationProvider');
    }
    return context;
};
