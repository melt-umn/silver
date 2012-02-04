grammar silver:extension:convenience;

import silver:modification:copper;

synthesized attribute proddcls :: AGDcl;
autocopy attribute lhsdcl :: ProductionLHS;
nonterminal ProductionDclStmts with proddcls, lhsdcl, file, grammarName;
nonterminal ProductionDclStmt with proddcls, lhsdcl, file, grammarName;

terminal Productions_kwd 'productions' lexer classes {KEYWORD};
terminal ProdVBar '|';

concrete production productionDclC
top::AGDcl ::= 'concrete' 'productions' lhs::ProductionLHS stmts::ProductionDclStmts 
{
  stmts.lhsdcl = lhs;
  forwards to stmts.proddcls;
}

concrete production productionDclStmtsOne
top::ProductionDclStmts ::= s::ProductionDclStmt
{
  top.proddcls = s.proddcls;
}
concrete production productionDclStmtsCons
top::ProductionDclStmts ::= s::ProductionDclStmt ss::ProductionDclStmts
{
  top.proddcls = appendAGDcl(s.proddcls, ss.proddcls);
}

concrete production productionDclStmt
top::ProductionDclStmt ::= optn::OptionalName v::ProdVBar
                           rhs::ProductionRHS
                           mods::ProductionModifiers
                           body::ProductionBody
                           opta::OptionalAction
{
  -- Either we have a name, or we generate an appropriate one.
  local attribute nme :: Name;
  nme = case optn of
        | noOptionalName() -> nameIdLower(terminal(IdLower_t, 
                                                   "P_"
                                                   ++ substitute(":", "_", top.grammarName)
                                                   ++ substitute(".", "_", top.file)
                                                   -- substitute(":", "_", top.lhsdcl.outputElement.typerep.typeName) TODO
                                                   ++ "_" ++ toString(v.line) ++ "_" ++ toString(v.column),
                                                   v.line, v.column))
        | anOptionalName(_, n, _) -> n
        end;

  top.proddcls = 
    let ct :: Concrete_kwd = terminal(Concrete_kwd, "concrete", v.line, v.column),
        pt :: Production_kwd = terminal(Production_kwd, "production", v.line, v.column)
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

