grammar ComplexExpr;


// Entrée
prog : formInfix | formPrefix | formPosfix;

// Forme infixée (renommée)
formInfix
    : 'sqrt' '(' formInfix ')' ADD 'sqrt' '(' formInfix ')' I             # ComboRootInfix
    | (NUMBER MUL)? 'cis' '(' formInfix ')'                           # ArgCisInfix
    | (NUMBER MUL)? 'e' '(' I MUL formInfix ')'                       # EulerExpInfix
    | 'sqrt' '(' formInfix ')'                                        # RootOnlyInfix
    | formInfix op1=(MUL | DIV) formInfix                                 # BinaryMulDivInfix
    | formInfix op2=(ADD | SUB) formInfix                                 # BinaryAddSubInfix
    | '|' formInfix '|'                                               # AbsValInfix
    | SUB? NUMBER? I                                              # ImagInfix
    | SUB? NUMBER                                                 # NumInfix
    | '(' formInfix ')'                                               # GroupInfix
    | 'asCoord' '(' formInfix ')'                                     # CartesianInfix
    | 'asAngle' '(' formInfix ')'                                     # PolarInfix
    | 'asExp' '(' formInfix ')'                                       # ExpoInfix
    ;

// Forme préfixée
formPrefix
    : 'sqrt' '(' formPrefix ')' ADD 'sqrt' '(' formPrefix ')' I             # ComboRootPrefix
    | 'sqrt' '(' formPrefix ')'                                        # RootOnlyPrefix
    | (NUMBER MUL)? 'cis' '(' formPrefix ')'                           # ArgCisPrefix
    | (NUMBER MUL)? 'e' '(' I MUL formPrefix ')'                     # EulerExpPrefix
    | op=(MUL | DIV) '(' formPrefix ((',' formPrefix)*) ')'                 # MultiOpMulDivPrefix
    | op=(ADD | SUB) '(' formPrefix ((',' formPrefix)*) ')'                 # MultiOpAddSubPrefix
    | '|' formPrefix '|'                                               # AbsValPrefix
    | SUB? NUMBER? I                                              # ImagPrefix
    | SUB? NUMBER                                                 # NumPrefix
    | '(' formPrefix ')'                                               # GroupPrefix
    | 'asCoord' '(' formPrefix ')'                                     # CartesianPrefix
    | 'asAngle' '(' formPrefix ')'                                     # PolarPrefix
    | 'asExp' '(' formPrefix ')'                                       # ExpoPrefix
    ;

// Forme postfixée
formPosfix
    : 'sqrt' '(' formPosfix ')' ADD 'sqrt' '(' formPosfix ')' I             # ComboRootPosfix
    | 'sqrt' '(' formPosfix ')' I?                                   # RootMaybeIPosfix
    | (NUMBER MUL)? 'cis' '(' formPosfix ')'                           # ArgCisPosfix
    | (NUMBER MUL)? 'e' '(' I MUL formPosfix ')'                     # EulerExpPosfix
    | '(' formPosfix ((',' formPosfix)*) ')' op=(MUL | DIV)                 # MultiOpMulDivPosfix
    | '(' formPosfix ((',' formPosfix)*) ')' op=(ADD | SUB)                 # MultiOpAddSubPosfix
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
