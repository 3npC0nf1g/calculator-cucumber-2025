grammar ExpressionParser;

// Entry point
start : expression ;

// Expression entry for all types
expression : infixExpr
           | prefixExpr
           | postfixExpr
           ;


// Infix expressions

infixExpr
    : infixExpr (MULT | DIV) infixExpr     # InfixMulDiv
    | infixExpr (PLUS | MINUS) infixExpr     # InfixAddSub
    | MINUS? NUM                             # InfixInteger
    | '(' infixExpr ')'                      # InfixGrouped
    ;


// Prefix expressions

prefixExpr
    : (MULT | DIV) '(' prefixExpr ((',' prefixExpr)+)? ')'   # PrefixMulDiv
    | (PLUS | MINUS) '(' prefixExpr ((',' prefixExpr)+)? ')'   # PrefixAddSub
    | MINUS? NUM                                               # PrefixInteger
    | '(' prefixExpr ')'                                       # PrefixGrouped
    ;



// Postfix expressions

postfixExpr
    : '(' postfixExpr ((',' postfixExpr)+)? ')' (MULT | DIV)   # PostfixMulDiv
    | '(' postfixExpr ((',' postfixExpr)+)? ')' (PLUS | MINUS)   # PostfixAddSub
    | MINUS? NUM                                                 # PostfixInteger
    | '(' postfixExpr ')'                                        # PostfixGrouped
    ;

// Tokens
MULT     : '*' ;
DIV     : '/' ;
PLUS    : '+' ;
MINUS   : '-' ;
NUM     : [0-9]+ ;
SPACE   : [ \t\r\n]+ -> skip ;