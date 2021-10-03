grammar silver:langutil:pp;

import silver:langutil only pp;

@{-
Show represents types that can be rendered as formatted (Document) strings.

For nonterminals, this class should typically be implemented by defining the pp attribute.

Minimal complete definition: pp
-}
class Show a {
  pp :: (Document ::= a);

  show :: (String ::= Integer a) =
    \ width::Integer x::a -> showDoc(width, pp(x));
}

-- Default instances using the pp attribute on undecorated and decorated nonterminal types
instance attribute pp {} occurs on a => Show a {
  pp = (.pp);
}

instance attribute pp i occurs on a => Show Decorated a with i {
  pp = (.pp);
}

-- Instance provided for backwards compatability, so that show(80, foo.pp) is still equivalent to show(80, foo).
instance Show Document {
  pp = id;
}

-- Instances for primitive types
instance Show Integer {
  pp = \ x::Integer -> text(toString(x));
}

instance Show Float {
  pp = \ x::Float -> text(toString(x));
}

instance Show Boolean {
  pp = \ x::Boolean -> text(toString(x));
}

instance Show String {
  pp = \ x::String -> text(escapeString(x));
}

instance Show a => Show [a] {
  pp = \ xs::[a] -> pp"[${ppImplode(pp", ", map(pp, xs))}]";
}

instance Show a, ShowTuple b => Show (a, b) {
  pp = \ x::(a, b) -> pp"(${pp(x.fst)}, ${ppTuple(x.snd)})";
}

class ShowTuple a {
  ppTuple :: (Document ::= a);
}

instance Show a, ShowTuple b => ShowTuple (a, b) {
  ppTuple = \ x::(a, b) -> pp"${pp(x.fst)}, ${ppTuple(x.snd)}";
}

instance Show a => ShowTuple a {
  ppTuple = pp;
}

instance Show () {
  pp = \ () -> pp"()";
}
