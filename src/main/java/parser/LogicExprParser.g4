grammar LogicExprParser;

// Entry rule
start : logicExpr ;

// Entry for all notations
logicExpr : infixForm
          | prefixForm
          | postfixForm
          ;


// Infix logical expressions

infixForm
    : NEG infixForm                     # InfixNegation
    | infixForm CONJ infixForm         # InfixAnd
    | infixForm DISJ infixForm         # InfixOr
    | infixForm IMPLY infixForm        # InfixImplies
    | infixForm XORR infixForm         # InfixXor
    | infixForm EQUIV infixForm        # InfixEquiv
    | TRUTH                            # InfixLiteral
    | '(' infixForm ')'                # InfixGroup
    ;


// Prefix logical expressions

prefixForm
    : NEG prefixForm                                  # PrefixNegation
    | CONJ '(' prefixForm ((',' prefixForm)+)? ')'    # PrefixAnd
    | DISJ '(' prefixForm ((',' prefixForm)+)? ')'    # PrefixOr
    | IMPLY '(' prefixForm ((',' prefixForm)+)? ')'   # PrefixImplies
    | XORR '(' prefixForm ((',' prefixForm)+)? ')'    # PrefixXor
    | EQUIV '(' prefixForm ((',' prefixForm)+)? ')'   # PrefixEquiv
    | TRUTH                                           # PrefixLiteral
    | '(' prefixForm ')'                              # PrefixGroup
    ;

// Postfix logical expressions

postfixForm
    : postfixForm NEG                                  # PostfixNegation
    | '(' postfixForm ((',' postfixForm)+)? ')' CONJ   # PostfixAnd
    | '(' postfixForm ((',' postfixForm)+)? ')' DISJ   # PostfixOr
    | '(' postfixForm ((',' postfixForm)+)? ')' IMPLY  # PostfixImplies
    | '(' postfixForm ((',' postfixForm)+)? ')' XORR   # PostfixXor
    | '(' postfixForm ((',' postfixForm)+)? ')' EQUIV  # PostfixEquiv
    | TRUTH                                            # PostfixLiteral
    | '(' postfixForm ')'                              # PostfixGroup
    ;


// Tokens
NEG    : 'NOT' ;
CONJ   : 'AND' ;
DISJ   : 'OR' ;
IMPLY  : 'IMPL' ;
XORR   : 'XOR' ;
EQUIV  : 'EQ' ;
TRUTH  : [0|1] ;
SPACE  : [ \t\r\n]+ -> skip ;