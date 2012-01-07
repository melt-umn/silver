-- Function manipulations


-- Attribute sections (.pp)

nonterminal Section;

synthesized attribute sec_valid :: Integer occurs on Section;
synthesized attribute sec_inv<a> :: a; attribute sec_inv<String> occurs on Section;
synthesized attribute sec_inv2 :: String; -- no occurs
inherited attribute sec_inv3 :: Integer occurs on Section; -- inh

abstract production section
top::Section ::=
{
  top.sec_valid = 2;
  top.sec_inv = "hi";
}

equalityTest( (.sec_valid)(decorate section() with {}), 2, Integer, silver_tests );
equalityTest( (.sec_valid)(section()), 2, Integer, silver_tests );

global sections :: [Section] = [section(), section()];

equalityTest( map((.sec_valid), sections), [2,2], [Integer], silver_tests );

wrongCode "attribute sections currently do not work with parameterized attributes" {
 -- Valid, but for the moment does not work! TODO
 global s :: [String] = map((.sec_inv), sections);
}

wrongCode "does not occur on" {
 global s :: [String] = map((.sec_inv2), sections);
}

wrongCode "Only synthesized attributes are currently supported" {
 -- Valid, but for the moment does not work! TODO
 global s :: [Integer] = map((.sec_inv3), sections);
}

