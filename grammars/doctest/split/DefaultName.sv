@@{-Content should be in the grammar file-}

@{-
	Some doc text for the Typeclass
-}
class SomeTypeclass f {
  @{- Doc for member signature foo -}
  foo :: (f ::= f); 
}

@{-
	Some doc text for the instance on String
-}
instance SomeTypeclass String {
  @{- Doc for implementation -}
  foo = \x::String -> x;
}