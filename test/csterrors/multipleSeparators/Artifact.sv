grammar multipleSeparators;

imports test:nonterm_b;

lexer class Sep1 prefix separator ":";
lexer class Sep2 prefix separator "$";

marking terminal A_t 'A' lexer classes Sep1;
marking terminal B_t 'B' lexer classes Sep2;

-- A_t and B_t have different prefix separators, and prefixes must be specified separately
parser extendedParser :: B {
  test:nonterm_b;
  multipleSeparators prefix with "foo";
}
