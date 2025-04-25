grammar ComplexExpr;


// Entrée
entry : formInfix | formPrefix | formPosfix;

// Infix form
formInfix
    : 'sqrt' '(' formInfix ')' op=(ADD|SUB)? ('sqrt' '(' formInfix ')' I)?   # ComboRootInfix
    | (NUMBER MUL)? 'cis' '(' formInfix ')'                           # ArgCisInfix
    | (NUMBER MUL)? 'e' '(' I MUL formInfix ')'                       # EulerExpInfix
    | formInfix op=(MUL | DIV) formInfix                                 # BinaryMulDivInfix
    | formInfix op=(ADD | SUB) formInfix                                 # BinaryAddSubInfix
    | '|' formInfix '|'                                               # AbsValInfix
    | 'asCoord' '(' formInfix ')'                                     # CartesianInfix
    | 'asAngle' '(' formInfix ')'                                     # PolarInfix
    | 'asExp' '(' formInfix ')'                                       # ExpoInfix
    | SUB? NUMBER? I                                              # ImagInfix
    | SUB? NUMBER                                                 # NumInfix
    | '(' formInfix ')'                                               # GroupInfix

    ;

// Prefix form
formPrefix
    : op = (ADD|SUB)?  '(' 'sqrt' '(' formPrefix ')' (',' 'sqrt' '(' formPrefix ')' I)? ')'   # ComboRootPrefix
    | (NUMBER MUL)? 'cis' '(' formPrefix ')'                           # ArgCisPrefix
    | (NUMBER MUL)? 'e' '(' I MUL formPrefix ')'                     # EulerExpPrefix
    | op=(MUL | DIV) '(' formPrefix ((',')? formPrefix)* ')'                 # MultiOpMulDivPrefix
    | op=(ADD | SUB) '(' formPrefix ((',')? formPrefix)* ')'                 # MultiOpAddSubPrefix
    | '|' formPrefix '|'                                               # AbsValPrefix
    | '(' formPrefix ')'                                               # GroupPrefix
    | SUB? NUMBER? I                                              # ImagPrefix
    | SUB? NUMBER                                                 # NumPrefix
    | 'asCoord' '(' formPrefix ')'                                     # CartesianPrefix
    | 'asAngle' '(' formPrefix ')'                                     # PolarPrefix
    | 'asExp' '(' formPrefix ')'                                       # ExpoPrefix
    ;

//  Postfix form
formPosfix
    : '(' 'sqrt' '(' formPosfix ')' (',' 'sqrt' '(' formPosfix ')' I)?')'  op=(ADD|SUB)?    # ComboRootPosfix
    | (NUMBER MUL)? 'cis' '(' formPosfix ')'                           # ArgCisPosfix
    | (NUMBER MUL)? 'e' '(' I MUL formPosfix ')'                     # EulerExpPosfix
    | '(' formPosfix ((',')? formPosfix)* ')' op=(MUL | DIV)                 # MultiOpMulDivPosfix
    | '(' formPosfix ((',')? formPosfix)* ')' op=(ADD | SUB)                 # MultiOpAddSubPosfix
    | '|' formPosfix '|'                                               # AbsValPosfix
    | SUB? NUMBER? I                                              # ImagPosfix
    | SUB? NUMBER                                                 # NumPosfix
    | '(' formPosfix ')'                                               # GroupPosfix
    | 'asCoord' '(' formPosfix ')'                                     # CartesianPosfix
    | 'asAngle' '(' formPosfix ')'                                     # PolarPosfix
    | 'asExp' '(' formPosfix ')'                                       # ExpoC
    ;

// Lexèmes
I       : 'i' |'j' ;
MUL     : '*' ;
DIV     : '/' ;
ADD     : '+' ;
SUB     : '-' ;
NUMBER  : [0-9]+ ;

// Espaces ignorés
WS : [ \t\r\n]+ -> skip ;
