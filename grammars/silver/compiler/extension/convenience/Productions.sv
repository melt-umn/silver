grammar silver:compiler:extension:convenience;

import silver:compiler:modification:copper;

-- "production" short for "abstract production"
concrete production productionDclImplicitAbs
top::AGDcl ::= 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  forwards to productionDcl('abstract', $1, id, ns, body, location=top.location);
}

-- "concrete productions" syntax
nonterminal ProductionDclStmts with unparse, location, proddcls, lhsdcl, grammarName;
nonterminal ProductionDclStmt with unparse, location, proddcls, lhsdcl, grammarName;

synthesized attribute proddcls :: AGDcl;
autocopy attribute lhsdcl :: ProductionLHS;

terminal Productions_kwd 'productions' lexer classes {KEYWORD};
terminal ProdVBar '|';

concrete production productionDclC
top::AGDcl ::= 'concrete' 'productions' lhs::ProductionLHS stmts::ProductionDclStmts 
{
  top.unparse = "concrete productions " ++ lhs.unparse ++ stmts.unparse;
  propagate moduleNames, jarName; -- Avoid dependency on forward
  
  stmts.lhsdcl = lhs;
  
  forwards to stmts.proddcls;
}

concrete production productionDclStmtsOne
top::ProductionDclStmts ::= s::ProductionDclStmt
{
  top.unparse = s.unparse;
  top.proddcls = s.proddcls;
}
concrete production productionDclStmtsCons
top::ProductionDclStmts ::= s::ProductionDclStmt ss::ProductionDclStmts
{
  top.unparse = s.unparse ++ ss.unparse;
  top.proddcls = appendAGDcl(s.proddcls, ss.proddcls, location=top.location);
}

concrete production productionDclStmt
top::ProductionDclStmt ::= optn::OptionalName v::ProdVBar
                           rhs::ProductionRHS
                           mods::ProductionModifiers
                           body::ProductionBody
                           opta::OptionalAction
{
  top.unparse = "| " ++ rhs.unparse ++ mods.unparse ++ body.unparse; -- TODO missing some here...
  -- Either we have a name, or we generate an appropriate one.
  local nme :: Name =
    case optn of
    | noOptionalName() ->
        name(
          "p_"
           ++ substitute(":", "_", top.grammarName)
           ++ "_" ++ substitute(".", "_", top.location.filename)
           ++ "_" ++ toString(v.line) ++ "_" ++ toString(v.column),
           v.location)
    | anOptionalName(_, n, _) -> n
    end;

  local newSig :: ProductionSignature =
    productionSignature(nilConstraint(location=top.location), '=>', top.lhsdcl, '::=', rhs, location=rhs.location);

  top.proddcls = 
    case opta of
    | noOptionalAction() -> 
        concreteProductionDcl('concrete', 'production', nme, newSig, mods, body, location=top.location)
    | anOptionalAction(a,c) ->
        concreteProductionDclAction('concrete', 'production', nme, newSig, mods, body, a, c, location=top.location)
    end;
}

nonterminal OptionalName with location;
concrete production noOptionalName
optn::OptionalName ::=
{
}
concrete production anOptionalName
optn::OptionalName ::= '(' id::Name ')'
{
}

nonterminal OptionalAction with location;
concrete production noOptionalAction
opta::OptionalAction ::=
{
}
concrete production anOptionalAction
opta::OptionalAction ::= 'action' acode::ActionCode_c
{
}

