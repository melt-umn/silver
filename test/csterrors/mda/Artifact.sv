grammar mda;

imports test:nonterm_b;

nonterminal A;

parser extendedParser :: B {
}

--undeclared value, should be an error about A not being a parser
--copper_mda nonParser(A){
--}

-- this reports that B in the extendedParser is missing, but it says
-- so in the context of the copper_mda
copper_mda parserWithMissingNt(extendedParser) {
}


