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
    : infixExpr op=(MULT | DIV) infixExpr     # InfixMulDiv
    | infixExpr op=(PLUS | MINUS) infixExpr     # InfixAddSub
    | MINUS? NUM                             # InfixInteger
    | '(' infixExpr ')'                      # InfixGrouped
    ;

// Prefix expressions

prefixExpr
    : op=(MULT | DIV) '(' prefixExpr ((',' prefixExpr)+)? ')'   # PrefixMulDiv
    | op=(PLUS | MINUS) '(' prefixExpr ((',' prefixExpr)+)? ')'   # PrefixAddSub
    | MINUS? NUM                                               # PrefixInteger
    | '(' prefixExpr ')'                                       # PrefixGrouped
    ;



// Postfix expressions

postfixExpr
    : '(' postfixExpr ((',' postfixExpr)+)? ')' op=(MULT | DIV)   # PostfixMulDiv
    | '(' postfixExpr ((',' postfixExpr)+)? ')' op=(PLUS | MINUS)   # PostfixAddSub
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
