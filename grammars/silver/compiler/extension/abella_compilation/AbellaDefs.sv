grammar silver:compiler:extension:abella_compilation;


nonterminal DefClause with unparse;

abstract production ruleClause
top::DefClause ::= hd::Metaterm body::Metaterm
{
  top.unparse = "   " ++ hd.unparse ++ " :=\n      " ++ body.unparse;
}


abstract production factClause
top::DefClause ::= hd::Metaterm
{
  top.unparse = "   " ++ hd.unparse;
}



nonterminal Defs with unparse;

abstract production singleAbellaDefs
top::Defs ::= clause::DefClause
{
  top.unparse = clause.unparse ++ ".\n";
}

abstract production consAbellaDefs
top::Defs ::= clause::DefClause rest::Defs
{
  top.unparse = clause.unparse ++ ";\n" ++ rest.unparse;
}

abstract production snocAbellaDefs
top::Defs ::= rest::Defs clause::DefClause
{
  top.unparse = rest.unparse ++ ";\n" ++ clause.unparse;
}



nonterminal Definition with unparse;

abstract production definition
top::Definition ::= relations::[(String, AbellaType)] clauses::Defs
{
  local relString::String =
        implode(",\n       ",
                map(\ p::(String, AbellaType) ->
                      p.1 ++ " : " ++ p.2.unparse,
                    relations));
  top.unparse = "Define " ++ relString ++ " by\n" ++ clauses.unparse;
}

