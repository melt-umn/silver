grammar silver:analysis:typechecking:core;

imports silver:definition:core;
imports silver:definition:type:syntax;

imports silver:definition:env;
imports silver:definition:type;



{- The resulting substitution context -}
synthesized attribute upSubst   :: Substitution;

{- The initial substitution context -}
inherited attribute   downSubst :: Substitution;

{- The complete, final substitution context -}
autocopy attribute   finalSubst :: Substitution;

-- We also use typerep.

