grammar silver:analysis:typechecking:core;

imports silver:definition:core;
imports silver:definition:type:syntax;

imports silver:definition:env;
imports silver:definition:type;



{-- The resulting substitution context -}
synthesized attribute upSubst   :: Substitution;

{-- The initial substitution context -}
inherited attribute downSubst :: Substitution;

{-- The complete, final substitution context -}
autocopy attribute finalSubst :: Substitution;

-- We also use typerep.
-- Such that performSubstitution(e.typerep, e.upSubst) is the expression's real type (as of that moment)

{- A NOTE ABOUT THREADING:
   The pair of attributes (upSubst/downSubst) represent the state of unification.
   downSubst tells a node its current state, and upSubst represents the final state.
   finalSubst gives information from elsewhere in the tree.
   
   The scope of that threading is not universal.
   That is, the threading BEGINS generally just above expressions, or individual
   production STATEMENTS. Not Production bodies.
   
   There is a slight hack to this... because aspects need to do type unification
   (actually, not sure if we need to preserve this, but let's assume we do!)
   ProductionBody and ProductionStmts (plural, *NOT* ProductionStmt)
   do not have an upSubst and finalSubst. Only downSubst.
   These will take that initial state, but otherwise confine things to each Stmt.
   -}


