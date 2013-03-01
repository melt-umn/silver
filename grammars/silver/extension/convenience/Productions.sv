grammar silver:extension:convenience;

import silver:modification:copper;

nonterminal ProductionDclStmts with pp, proddcls, lhsdcl, file, grammarName;
nonterminal ProductionDclStmt with pp, proddcls, lhsdcl, file, grammarName;

synthesized attribute proddcls :: AGDcl;
autocopy attribute lhsdcl :: ProductionLHS;

terminal Productions_kwd 'productions' lexer classes {KEYWORD};
terminal ProdVBar '|';

concrete production productionDclC
top::AGDcl ::= 'concrete' 'productions' lhs::ProductionLHS stmts::ProductionDclStmts 
{
  top.pp = "concrete productions " ++ lhs.pp ++ stmts.pp;
  stmts.lhsdcl = lhs;
  forwards to stmts.proddcls;
}

concrete production productionDclStmtsOne
top::ProductionDclStmts ::= s::ProductionDclStmt
{
  top.pp = s.pp;
  top.proddcls = s.proddcls;
}
concrete production productionDclStmtsCons
top::ProductionDclStmts ::= s::ProductionDclStmt ss::ProductionDclStmts
{
  top.pp = s.pp ++ ss.pp;
  top.proddcls = appendAGDcl(s.proddcls, ss.proddcls);
}

concrete production productionDclStmt
top::ProductionDclStmt ::= optn::OptionalName v::ProdVBar
                           rhs::ProductionRHS
                           mods::ProductionModifiers
                           body::ProductionBody
                           opta::OptionalAction
{
  top.pp = "| " ++ rhs.pp ++ mods.pp ++ body.pp; -- TODO missing some here...
  -- Either we have a name, or we generate an appropriate one.
  local attribute nme :: Name;
  nme = case optn of
        | noOptionalName() -> nameIdLower(terminal(IdLower_t, 
                                                   "P_"
                                                   ++ substitute(":", "_", top.grammarName)
                                                   ++ substitute(".", "_", top.file)
                                                   -- substitute(":", "_", top.lhsdcl.outputElement.typerep.typeName) TODO
                                                   ++ "_" ++ toString(v.line) ++ "_" ++ toString(v.column),
                                                   v.location))
        | anOptionalName(_, n, _) -> n
        end;

  top.proddcls = 
    let ct :: Concrete_kwd = terminal(Concrete_kwd, "concrete", v.location),
        pt :: Production_kwd = terminal(Production_kwd, "production", v.location)
    in
    case opta of
    | noOptionalAction() -> concreteProductionDcl(ct, pt, nme, productionSignature(top.lhsdcl, '::=', rhs), mods, body)
    | anOptionalAction(a,c) -> concreteProductionDclAction(ct, pt, nme, productionSignature(top.lhsdcl, '::=', rhs), mods, body, a, c)
    end end;
}

nonterminal OptionalName;
concrete production noOptionalName
optn::OptionalName ::=
{
}
concrete production anOptionalName
optn::OptionalName ::= '(' id::Name ')'
{
}

nonterminal OptionalAction;
concrete production noOptionalAction
opta::OptionalAction ::=
{
}
concrete production anOptionalAction
opta::OptionalAction ::= 'action' acode::ActionCode_c
{
}

