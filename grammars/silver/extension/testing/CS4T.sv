grammar silver:extension:testing ;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:modification:collection ;
import silver:extension:list ;

import silver:composed:Default only rParse with rParse as extCSParse;

-- A truly hacky but still useful version of concrete syntax for trees...

{- Importing instead!
parser extCSParse::Root {
  silver:host;

  silver:extension:convenience;
  silver:extension:list;
  silver:extension:easyterminal;

  silver:extension:testing;

  silver:modification:let_fix;
  silver:modification:collection;
  silver:modification:patternmatching;
  silver:modification:autocopyattr;
  silver:modification:autocopyattr:convenience; 

  silver:modification:ffi;
}
-}


--------------------------------------------------
-- Generic constructs
--------------------------------------------------
function getParseTree
Root ::= str::String flag::String id::Integer 
{ local s::String = flag ++ " " ++ str ;
  local pr::ParseResult<Root> = extCSParse (s, 
                  "file EqTestwCST.sv, in Silver code" ++ toString(id) ++ "\n\n" ) ;
  return if ! pr.parseSuccess 
         then error ("\n\nInternal error: can't parse CS4T\n" ++ pr.parseErrors ++
                     "\n\nin attempting to parse:\n" ++ s ) 
         else pr.parseTree ;
}

function getParseTreeBrkt
Root ::= str::String flag::String lb::String id::Integer rb::String
{ local s::String = flag ++ " " ++ lb ++ str ++ rb;
  local pr::ParseResult<Root> = extCSParse (s, 
                  "file EqTestwCST.sv, in Silver code" ++ toString(id) ++ "\n\n" ) ;
  return if ! pr.parseSuccess 
         then error ("\n\nInternal error: can't parse CS4T\n" ++ pr.parseErrors ++
                     "\n\nin attempting to parse:\n" ++ s ) 
         else pr.parseTree ;
}

nonterminal CS_env ;
autocopy attribute tree_name :: String occurs on CS_env ;
synthesized attribute tree_found :: Boolean occurs on CS_env ;
synthesized attribute tree_value :: TreeValue occurs on CS_env ;

abstract production empty_CS_env
cs_env::CS_env ::=
{ cs_env.tree_found = false ;
  cs_env.tree_value = wrapNotFound ("TREE value for name " ++ cs_env.tree_name ++ 
                                    " not found.\n\n");
}
abstract production cons_CS_env
cs_env::CS_env ::= n::String v::TreeValue rest::CS_env
{ cs_env.tree_found = n == cs_env.tree_name || rest.tree_found ;
  cs_env.tree_value = if n == cs_env.tree_name then v else rest.tree_value ;
}

nonterminal TreeValue ;
abstract production wrapNotFound  t::TreeValue ::= msg::String  { }

autocopy attribute cs_env :: CS_env ;

-- add this for all CS nonterminals ;
attribute cs_env occurs on Root, AGDcls, AGDcl,
 ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
 AspectProductionSignature, AspectProductionLHS, AspectRHS, AspectRHSElem,
 Type, Signature, TypeList,

 ProductionBody, ProductionStmts, ProductionStmt,
-- ProductionDclStmt, ProductionDclStmts,

 Expr, Exprs,
 Name, QName, QNameUpper, NameList ;

--------------------------------------------------
-- Nonterminal Specific things
--------------------------------------------------
-- Name
terminal AsName_t '%%%Name' lexer classes {KEYWORD};
concrete production rootAs_Name 
r::Root ::= AsName_t Name { }

abstract production asName   
top::Name ::= str::String cenv::CS_env id::Integer 
{ forwards to case getParseTree(str,"%%%Name",id) of
                rootAs_Name(_,l) -> l
              | _ -> error("Should NEVER access this in EqTestwCST.sv ... " ++ 
                           toString(id))  end 
    with {cs_env = cenv ;} ;
}

abstract production wrapName av::TreeValue ::= Name { }
concrete production embedNameCS_c
e::Name ::= flag::AsName_t name::IdLower_t
{ forwards to case cenv.tree_value of
                wrapName ( e_tree ) -> e_tree
              | wrapNotFound ( m ) -> error ( m )
              | _ -> error ("incorrect type - expected " ++ name.lexeme ++ 
                            " to be an Name.\n\n" )
               end ;
  local attribute cenv :: CS_env ;
  cenv = e.cs_env ;
  cenv.tree_name = name.lexeme ;
}


-- Expr
terminal AsExpr_t '%%%Expr' lexer classes {KEYWORD};
concrete production rootAs_Expr 
r::Root ::= AsExpr_t Expr { }

abstract production asExpr   
top::Expr ::= str::String cenv::CS_env id::Integer 
{ forwards to case getParseTree(str,"%%%Expr",id) of
                rootAs_Expr(_,l) -> l
              | _ -> error("Should NEVER access this in EqTestwCST.sv ... " ++ 
                           toString(id))  end 
    with {cs_env = cenv ;} ;
}

abstract production wrapExpr av::TreeValue ::= Expr { }
concrete production embedExprCS_c
e::Expr ::= flag::AsExpr_t name::IdLower_t
{ forwards to case cenv.tree_value of
                wrapExpr ( e_tree ) -> e_tree
              | wrapNotFound ( m ) -> error ( m )
              | _ -> error ("incorrect type - expected " ++ name.lexeme ++ 
                            " to be an Expr.\n\n" )
               end ;
  local attribute cenv :: CS_env ;
  cenv = e.cs_env ;
  cenv.tree_name = name.lexeme ;
}

-- Type
terminal AsType_t '%%%Type' lexer classes {KEYWORD};
concrete production rootAs_Type 
r::Root ::= AsType_t Type { }

abstract production asType   
top::Type ::= str::String cenv::CS_env id::Integer 
{ forwards to case getParseTree(str,"%%%Type",id) of
                rootAs_Type(_,l) -> l
              | _ -> error("Should NEVER access this in EqTestwCST.sv ... " ++ 
                           toString(id))  end 
    with {cs_env = cenv ;} ;
}

abstract production wrapType av::TreeValue ::= Type { }
concrete production embedTypeCS_c
e::Type ::= flag::AsType_t name::IdLower_t
{ forwards to case cenv.tree_value of
                wrapType ( e_tree ) -> e_tree
              | wrapNotFound ( m ) -> error ( m )
              | _ -> error ("incorrect type - expected " ++ name.lexeme ++ 
                            " to be an Type.\n\n" )
               end ;
  local attribute cenv :: CS_env ;
  cenv = e.cs_env ;
  cenv.tree_name = name.lexeme ;
}

-- ProductionStmt
terminal AsProductionStmt_t '%%%ProductionStmt' lexer classes {KEYWORD};
concrete production rootAs_ProductionStmt 
r::Root ::= AsProductionStmt_t ProductionStmt { }

abstract production asProductionStmt   
top::ProductionStmt ::= str::String cenv::CS_env id::Integer 
{ forwards to case getParseTree(str,"%%%ProductionStmt",id) of
                rootAs_ProductionStmt(_,l) -> l
              | _ -> error("Should NEVER access this in EqTestwCST.sv ... " ++ 
                           toString(id))  end 
    with {cs_env = cenv ;} ;
}

abstract production wrapProductionStmt av::TreeValue ::= ProductionStmt { }
concrete production embedProductionStmtCS_c
e::ProductionStmt ::= flag::AsProductionStmt_t name::IdLower_t
{ forwards to case cenv.tree_value of
                wrapProductionStmt ( e_tree ) -> e_tree
              | wrapNotFound ( m ) -> error ( m )
              | _ -> error ("incorrect type - expected " ++ name.lexeme ++ 
                            " to be an ProductionStmt.\n\n" )
               end ;
  local attribute cenv :: CS_env ;
  cenv = e.cs_env ;
  cenv.tree_name = name.lexeme ;
}

-- ProductionStmts
terminal AsProductionStmts_t '%%%ProductionStmts' lexer classes {KEYWORD};
concrete production rootAs_ProductionStmts 
r::Root ::= AsProductionStmts_t ProductionStmts { }

abstract production asProductionStmts   
top::ProductionStmts ::= str::String cenv::CS_env id::Integer 
{ forwards to case getParseTree(str,"%%%ProductionStmts",id) of
                rootAs_ProductionStmts(_,l) -> l
              | _ -> error("Should NEVER access this in EqTestwCST.sv ... " ++ 
                           toString(id))  end 
    with {cs_env = cenv ;} ;
}

abstract production wrapProductionStmts av::TreeValue ::= ProductionStmts { }
concrete production embedProductionStmtsCS_c
e::ProductionStmts ::= flag::AsProductionStmts_t name::IdLower_t
{ forwards to case cenv.tree_value of
                wrapProductionStmts ( e_tree ) -> e_tree
              | wrapNotFound ( m ) -> error ( m )
              | _ -> error ("incorrect type - expected " ++ name.lexeme ++ 
                            " to be an ProductionStmts.\n\n" )
               end ;
  local attribute cenv :: CS_env ;
  cenv = e.cs_env ;
  cenv.tree_name = name.lexeme ;
}

-- ProductionBody
terminal AsProductionBody_t '%%%ProductionBody' lexer classes {KEYWORD};
concrete production rootAs_ProductionBody 
r::Root ::= AsProductionBody_t ProductionBody { }

abstract production asProductionBody   
top::ProductionBody ::= str::String cenv::CS_env id::Integer 
{ forwards to case getParseTree(str,"%%%ProductionBody",id) of
                rootAs_ProductionBody(_,l) -> l
              | _ -> error("Should NEVER access this in EqTestwCST.sv ... " ++ 
                           toString(id))  end 
    with {cs_env = cenv ;} ;
}

abstract production wrapProductionBody av::TreeValue ::= ProductionBody { }
concrete production embedProductionBodyCS_c
e::ProductionBody ::= flag::AsProductionBody_t name::IdLower_t
{ forwards to case cenv.tree_value of
                wrapProductionBody ( e_tree ) -> e_tree
              | wrapNotFound ( m ) -> error ( m )
              | _ -> error ("incorrect type - expected " ++ name.lexeme ++ 
                            " to be an ProductionBody.\n\n" )
               end ;
  local attribute cenv :: CS_env ;
  cenv = e.cs_env ;
  cenv.tree_name = name.lexeme ;
}

-- ProductionLHS
terminal AsProductionLHS_t '%%%ProductionLHS' lexer classes {KEYWORD};
concrete production rootAs_ProductionLHS 
r::Root ::= AsProductionLHS_t ProductionLHS { }

abstract production asProductionLHS   
top::ProductionLHS ::= str::String cenv::CS_env id::Integer 
{ forwards to case getParseTree(str,"%%%ProductionLHS",id) of
                rootAs_ProductionLHS(_,l) -> l
              | _ -> error("Should NEVER access this in EqTestwCST.sv ... " ++ 
                           toString(id))  end 
    with {cs_env = cenv ;} ;
}

abstract production wrapProductionLHS av::TreeValue ::= ProductionLHS { }
concrete production embedProductionLHSCS_c
e::ProductionLHS ::= flag::AsProductionLHS_t name::IdLower_t
{ forwards to case cenv.tree_value of
                wrapProductionLHS ( e_tree ) -> e_tree
              | wrapNotFound ( m ) -> error ( m )
              | _ -> error ("incorrect type - expected " ++ name.lexeme ++ 
                            " to be an ProductionLHS.\n\n" )
               end ;
  local attribute cenv :: CS_env ;
  cenv = e.cs_env ;
  cenv.tree_name = name.lexeme ;
}

-- AGDcl
terminal AsAGDcl_t '%%%AGDcl' lexer classes {KEYWORD};
concrete production rootAs_AGDcl 
r::Root ::= AsAGDcl_t AGDcl { }

abstract production asAGDcl   
top::AGDcl ::= str::String cenv::CS_env id::Integer 
{ forwards to case getParseTree(str,"%%%AGDcl",id) of
                rootAs_AGDcl(_,l) -> l
              | _ -> error("Should NEVER access this in EqTestwCST.sv ... " ++ 
                           toString(id))  end 
    with {cs_env = cenv ;} ;
}

-- AGDcls
terminal AsAGDcls_t '%%%AGDcls' lexer classes {KEYWORD};
concrete production rootAs_AGDcls 
r::Root ::= AsAGDcls_t AGDcls { }

abstract production asAGDcls   
top::AGDcls ::= str::String cenv::CS_env id::Integer 
{ forwards to case getParseTree(str,"%%%AGDcls",id) of
                rootAs_AGDcls(_,l) -> l
              | _ -> error("Should NEVER access this in EqTestwCST.sv ... " ++ 
                           toString(id))  end 
    with {cs_env = cenv ;} ;
}

{-
abstract production wrapAGDcl av::TreeValue ::= AGDcl { }
concrete production embedAGDclCS_c
e::AGDcl ::= flag::AsAGDcl_t name::IdLower_t
  THIS FAILS ...
{ forwards to case cenv.tree_value of
                wrapAGDcl ( e_tree ) -> e_tree
              | wrapNotFound ( m ) -> error ( m )
              | _ -> error ("incorrect type - expected " ++ name.lexeme ++ 
                            " to be an AGDcl.\n\n" )
               end ;
  local attribute cenv :: CS_env ;
  cenv = e.cs_env ;
  cenv.tree_name = name.lexeme ;
}
-}
