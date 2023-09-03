#ifndef ENUM_H
#define ENUM_H

// define node type
typedef enum nodeType {
    TOKEN_INT,
    TOKEN_FLOAT,
    TOKEN_ID,
    TOKEN_TYPE,
    TOKEN_OTHER,
    NOT_A_TOKEN

} NodeType;

typedef enum symbolKind {
    BASIC, ARRAY, STRUCTURE, FUNCTION
} SymbolKind;
typedef enum basicType {
    INT_TYPE, FLOAT_TYPE
} BasicType;
typedef enum errorType {
    UNDEF_VAR = 1,         // Undefined Variable
    UNDEF_FUNC,            // Undefined Function
    RE_DEF_VAR,             // Redefined Variable
    RE_DEF_FUNC,            // Redefined Function
    TYPE_MISMATCH_ASSIGN,  // Type mismatched for assignment.
    LEFT_VAR_ASSIGN,  // The left-hand side of an assignment must be a variable.
    TYPE_MISMATCH_OP,      // Type mismatched for operands.
    TYPE_MISMATCH_RETURN,  // Type mismatched for return.
    FUNC_ARGC_MISMATCH,    // Function is not applicable for arguments
    NOT_A_ARRAY,           // Variable is not a Array
    NOT_A_FUNC,            // Variable is not a Function
    NOT_A_INT,             // Variable is not a Integer
    ILLEGAL_USE_DOT,       // Illegal use of "."
    NON_EXIST_FIELD,         // Non-existent field
    RE_DEF_FIELD,           // Redefined field
    DUPLICATED_NAME,       // Duplicated name
    UNDEF_STRUCT           // Undefined structure
} ErrorType;
typedef enum operandKind {
    OP_VARIABLE,
    OP_CONSTANT,
    OP_ADDRESS,
    OP_LABEL,
    OP_FUNCTION,
    OP_RELOP
} OperandKind;
typedef enum interCodeKind {
    IR_LABEL,
    IR_FUNCTION,
    IR_ASSIGN,
    IR_ADD,
    IR_SUB,
    IR_MUL,
    IR_DIV,
    IR_GET_ADDR,
    IR_READ_ADDR,
    IR_WRITE_ADDR,
    IR_GOTO,
    IR_IF_GOTO,
    IR_RETURN,
    IR_DEC,
    IR_ARG,
    IR_CALL,
    IR_PARAM,
    IR_READ,
    IR_WRITE
} InterCodeKind;
#endif