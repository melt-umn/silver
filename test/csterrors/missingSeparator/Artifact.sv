grammar missingSeparator;

imports test:nonterm_b;

-- Mangle_t does not have a prefix separator
parser extendedParser :: B {
  test:nonterm_b prefix Mangle_t with "foo";
}
