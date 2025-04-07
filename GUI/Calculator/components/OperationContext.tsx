import React, { createContext, useContext, useMemo, useState, ReactNode } from 'react';

// Define the shape of our context
type OperationContextType = {
    operation: string;
    updateOperation: (newOperation: string) => void;
    appendOperation: (value: string) => void;
    clearOperation: () => void;
};

// Create the context. We use undefined as the default value.
const OperationContext = createContext<OperationContextType | undefined>(undefined);

type OperationProviderProps = {
    children: ReactNode;
};

// Create a functional provider component
export const OperationProvider: React.FC<OperationProviderProps> = ({ children }) => {
    const [operation, setOperation] = useState<string>('');

    const updateOperation = (newOperation: string) => setOperation(newOperation);
    const appendOperation = (value: string) => setOperation(prev => prev + value);
    const clearOperation = () => setOperation('');

    // Memoize the context value so that it only changes when 'operation' changes.
    const contextValue = useMemo(() => ({
        operation,
        updateOperation,
        appendOperation,
        clearOperation,
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
