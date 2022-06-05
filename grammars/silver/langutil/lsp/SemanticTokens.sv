grammar silver:langutil:lsp;

@@{-
 - These lexer classes correspond to the semantic token types and modifiers as
 - specified in the language server protocol.
 - See https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/#textDocument_semanticTokens
 -}

-- Token types
lexer class Namespace;
lexer class Type;
lexer class Class;
lexer class Enum;
lexer class Interface;
lexer class Struct;
lexer class TypeParameter;
lexer class Parameter;
lexer class Variable;
lexer class Property;
lexer class EnumMember;
lexer class Event;
lexer class Function;
lexer class Method;
lexer class Macro;
lexer class Keyword;
lexer class Modifier;
lexer class Comment;
lexer class String_;
lexer class Number;
lexer class Regexp;
lexer class Operator;
lexer class Decorator;

-- Token modifiers
lexer class Declaration;
lexer class Definition;
lexer class Readonly;
lexer class Static;
lexer class Deprecated;
lexer class Abstract;
lexer class Async;
lexer class Modification;
lexer class Documentation;
lexer class DefaultLibrary;
