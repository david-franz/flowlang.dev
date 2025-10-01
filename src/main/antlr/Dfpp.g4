grammar Dfpp;

// ===================================================================
// PARSER
// ===================================================================

program
    : moduleDecl? importDecl* topDecl* EOF
    ;

moduleDecl
    : MODULE qid
    ;

importDecl
    : IMPORT qid AS ID
    ;

qid
    : ident (DOT ident)*
    ;

ident
    : ID
    | BTICKID
    ;

// Allow declarations and orchestration statements at top level
topDecl
    : constDecl
    | letDecl
    | typeDecl
    | fnDecl
    | taskDecl
    | runStmt
    | parallelStmt
    ;

// ---------------- Declarations ----------------

constDecl
    : CONST ident (COLON typeRef)? EQ expr
    ;

letDecl
    : (LET | MUT) ident (COLON typeRef)? EQ expr
    ;

// type Option<T> = None | Some(T)
typeDecl
    : TYPE ident typeParamsDecl? EQ sumType
    ;

typeParamsDecl
    : LT typeParam (COMMA typeParam)* GT
    ;

typeParam
    : ident
    ;

sumType
    : variant ('|' variant)*
    ;

variant
    : ident (LP typeRef (COMMA typeRef)* RP)?
    ;

// fn f(x: A, y: B): C = expr  |  fn f(...) { ... }
fnDecl
    : FN ident LP paramList? RP (COLON typeRef)? EQ expr
    | FN ident LP paramList? RP (COLON typeRef)? block
    ;

paramList
    : param (COMMA param)*
    ;

param
    : ident (COLON typeRef)?
    ;

// task 'name' { pre ... act { ... } pos ... }
taskDecl
    : TASK STRING LB (PRE expr)? ACT block (POS expr)? RB
    ;

// ---------------- Blocks & Statements ----------------

block
    : LB stmt* expr? RB
    ;

stmt
    : constDecl
    | letDecl
    | runStmt
    | parallelStmt
    | expr                 // allow expression statements inside blocks
    ;

// run('A').then('B')...
runStmt
    : runCall (DOT ident LP args? RP)*
    ;

// parallel { 'T1', 'T2', ... }
parallelStmt
    : PARALLEL LB stringList RB
    ;

stringList
    : STRING (COMMA STRING)*
    ;

// ===================================================================
// EXPRESSIONS (precedence ladder + postfix chains)
// ===================================================================

expr
    : conditional
    ;

// lowest precedence ternary
conditional
    : logicalOr (QMARK expr COLON expr)?
    ;

logicalOr
    : logicalAnd (OR logicalAnd)*
    ;

logicalAnd
    : equality (AND equality)*
    ;

equality
    : relational ((EE | NE) relational)*
    ;

relational
    : additive ((LT | LE | GT | GE) additive)*
    ;

additive
    : multiplicative ((PLUS | MINUS) multiplicative)*
    ;

multiplicative
    : unary ((STAR | SLASH | PERC) unary)*
    ;

unary
    : NOT unary
    | MINUS unary
    | postfix
    ;

// Postfix chains on any primary: calls, members, indexing
postfix
    : primary (postfixOp)*
    ;

postfixOp
    : LP args? RP                   // f(...) or (x)(...)
    | DOT ident                     // x.y
    | DOT ident LP args? RP         // x.y(...)
    | LSB expr RSB                  // x[i]  (keep if you want indexing)
    ;

// Primary expressions
// Order matters: put lambda before bare ident so "x -> x" parses as lambda.
primary
    : literal
    | LP expr RP
    | recordLit
    | arrayLit
    | matchExpr
    | runCall
    | lambdaExpr                    // optional in v1; remove if not needed
    | ident
    ;

literal
    : INTLIT
    | DECLIT
    | STRING
    | TRUE
    | FALSE
    | NULL
    ;

args
    : expr (COMMA expr)*
    ;

// run("A") can participate in postfix chains
runCall
    : RUN LP STRING RP
    ;

// ---------------- Pattern Matching ----------------

// match (e) { P1 -> e1, P2 -> e2, ... }
matchExpr
    : MATCH LP expr RP LB matchArm (COMMA matchArm)* COMMA? RB
    ;

matchArm
    : pattern ARROW expr
    ;

pattern
    : ident (LP (ident (COMMA ident)*)? RP)?   // Constructor / ADT tag with binders
    | LB patField (COMMA patField)* RB         // Record subset pattern {k = x, ...}
    | literal
    | UNDERSCORE                               // wildcard _
    ;

patField
    : ident EQ ident
    ;

// ---------------- Literals: records & arrays ----------------

recordLit
    : LB recField (COMMA recField)* RB
    ;

recField
    : ident EQ expr
    ;

arrayLit
    : LSB expr FOR ident IN expr (IF expr)? RSB        // list comprehension
    | LSB (expr (COMMA expr)*)? RSB                    // list literal
    ;

// ---------------- Types ----------------

// qid typeArgs?        e.g., Option<Int>, Map<String,Int>, Foo
// { k: T, ... }        record types
// T -> U               function types (right-assoc)
typeRef
    : qid typeArgs?                               #NamedOrAppliedType
    | LB typeField (COMMA typeField)* RB          #RecordType
    | LP typeRef (COMMA typeRef)+ RP              #TupleType
    | typeRef ARROW typeRef                       #FunctionType
    ;

typeArgs
    : LT typeRef (COMMA typeRef)* GT
    ;

typeField
    : ident COLON typeRef
    ;

// ---------------- Lambdas (optional) ----------------

// u -> expr
// (x, y) -> expr
lambdaExpr
    : lambdaHead ARROW expr
    ;

lambdaHead
    : ident
    | LP (ident (COMMA ident)*)? RP
    ;

// ===================================================================
// LEXER
// ===================================================================

MODULE      : 'module';
IMPORT      : 'import';
AS          : 'as';
TYPE        : 'type';
FN          : 'fn';
TASK        : 'task';
PRE         : 'pre';
ACT         : 'act';
POS         : 'pos';
RUN         : 'run';
PARALLEL    : 'parallel';
MATCH       : 'match';
CONST       : 'const';
LET         : 'let';
MUT         : 'mut';
TRUE        : 'true';
FALSE       : 'false';
NULL        : 'null';
FOR         : 'for';
IN          : 'in';
IF          : 'if';

// Put UNDERSCORE before ID so "_" is a wildcard token, but "_x" still matches ID.
UNDERSCORE  : '_' ;

INTLIT      : [0-9]+;
DECLIT      : [0-9]+'.'[0-9]+;

// Both "..." and '...' strings are allowed
STRING
    : '"' ( ~["\\] | '\\' . )* '"'
    | '\'' ( ~['\\] | '\\' . )* '\''
    ;

// Back-ticked identifiers allow spaces and punctuation inside names
ID          : [a-zA-Z_][a-zA-Z_0-9]*;
BTICKID     : '`' ( ~[`\\] | '\\' . )+ '`';

LP          : '(';
RP          : ')';
LB          : '{';
RB          : '}';
LSB         : '[';
RSB         : ']';
COMMA       : ',';
DOT         : '.';
COLON       : ':';
EQ          : '=';
QMARK       : '?';
ARROW       : '->';

PLUS        : '+';
MINUS       : '-';
STAR        : '*';
SLASH       : '/';
PERC        : '%';
AND         : '&&';
OR          : '||';
NOT         : '!';
EE          : '==';
NE          : '!=';
LT          : '<';
LE          : '<=';
GT          : '>';
GE          : '>=';

WS              : [ \t\r\n]+ -> skip ;
LINE_COMMENT    : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT   : '/*' .*? '*/' -> skip ;
