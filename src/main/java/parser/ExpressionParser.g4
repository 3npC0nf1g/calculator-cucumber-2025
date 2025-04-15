grammar ExpressionParser;

start : expression ;

expression : infixExpr
           | prefixExpr
           | postfixExpr
           ;

// Enhanced number definitions
NUMBER : INT | FLOAT | SCI_NOTATION;
fragment INT : [0-9]+;
fragment FLOAT : [0-9]+ '.' [0-9]* | '.' [0-9]+;
fragment SCI_NOTATION : (INT | FLOAT) [e|E] [+|-]? INT;

// Complex number support
COMPLEX : NUMBER? (PLUS|MINUS)? NUMBER ('i'|'j');

// Angle support
ANGLE : NUMBER ('deg'|'rad');

// Enhanced infix expressions
infixExpr
    : infixExpr op=(MUL | DIV) infixExpr     # InfixMulDiv
    | infixExpr op=(PLUS | MINUS) infixExpr     # InfixAddSub
    | MINUS? (NUMBER | COMPLEX | ANGLE)      # InfixNumber
    | '(' infixExpr ')'                      # InfixGrouped
    | functionCall                          # InfixFunction
    ;

// Enhanced prefix expressions with functions
prefixExpr
    : op=(MUL | DIV) '(' prefixExpr ((',' prefixExpr)+)? ')'   # PrefixMulDiv
    | op=(PLUS | MINUS) '(' prefixExpr ((',' prefixExpr)+)? ')'   # PrefixAddSub
    | MINUS? (NUMBER | COMPLEX | ANGLE)                        # PrefixNumber
    | '(' prefixExpr ')'                                       # PrefixGrouped
    | functionCall                                            # PrefixFunction
    ;

// Enhanced postfix expressions
postfixExpr
    : '(' postfixExpr ((',' postfixExpr)+)? ')' op=(MUL | DIV)   # PostfixMulDiv
    | '(' postfixExpr ((',' postfixExpr)+)? ')' op=(PLUS | MINUS)   # PostfixAddSub
    | MINUS? (NUMBER | COMPLEX | ANGLE)                          # PostfixNumber
    | '(' postfixExpr ')'                                        # PostfixGrouped
    | functionCall                                              # PostfixFunction
    ;

// Function calls
functionCall
    : ID '(' (expression (',' expression)*)? ')'
    ;

// Tokens
MUL     : '*' ;
DIV     : '/' ;
PLUS    : '+' ;
MINUS   : '-' ;
ID      : [a-zA-Z]+ ;  // For function names
SPACE   : [ \t\r\n]+ -> skip ;