grammar silver:compiler:extension:abella_compilation:abella;


--Whether parentheses are possibly needed in printing something
synthesized attribute isAtomic::Boolean;
--Because we print a lot of sequences of conjunctions
synthesized attribute isAnd::Boolean;