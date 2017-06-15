grammar mda;

imports test:nonterm_b;

nonterminal A;

parser extendedParser :: B {
}

--undeclared value
--copper_mda badParser(missingParser) {
--}

--undeclared value, should be an error about A not being a parser
copper_mda nonParser(A){
}

-- really a parser error, for some reason this error is separate
--copper_mda parserWithMissingNt(extendedParser) {
--}


