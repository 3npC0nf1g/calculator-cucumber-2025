grammar RationalCore;

entry : form ;

form : modeInfix | modePrefix | modePosfix ;

modeInfix
    : modeInfix sym=(MUL | DIV) modeInfix                         # BinOpInfix
    | modeInfix sym=(ADD | SUB) modeInfix                         # AddSubInfix
    | unit                                                 # AtomicInfix
    | SUB? grouping(modeInfix)                                 # WrappedInfix
    ;

modePrefix
    : sym=(MUL | DIV) '(' modePrefix ((',' modePrefix)+) ')'        # BinOpPrefix
    | sym=(ADD | SUB) '(' modePrefix ((',' modePrefix)+) ')'        # AddSubPrefix
    | unit                                                 # AtomicPrefix
    | SUB? grouping(modePrefix)                                 # WrappedPrefix
    ;

modePosfix
    : groupingList(modePosfix) sym=(MUL | DIV)                 # BinOpPosfix
    | groupingList(modePosfix) sym=(ADD | SUB)                 # AddSubPosfix
    | unit                                                 # AtomicPosfix
    | grouping(modePosfix) SUB?                                 # WrappedPosfix
    ;

// fragment pour l’encapsulation
grouping : '(' form ')' ;
groupingList : '(' form ((',' form)+) ')' ;

// Unités numériques (rationnels)
unit : num (FRAC num)?                                      # RationalUnit ;

num : SUB? DIGIT+                                          # IntUnit ;

// Lexèmes
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
FRAC : ':' ;
DIGIT : [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;
