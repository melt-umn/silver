grammar silver:definition:concrete_syntax;

aspect production productionDcl
top ::= _ _ _ _ _{

  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.ruleDcls = [];
  top.terminalDcls = [];
}
concrete production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  forwards to concreteProductionDclModifiers($1, $2, id, ns, productionModifiersNone(), body);
}

concrete production concreteProductionDclModifiers
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.pp = "concrete production " ++ id.pp ++ "\n" ++ ns.pp ++ " " ++ pm.pp ++ "\n" ++ body.pp; 
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);
  ns.env = newScopeEnv(ns.defs, top.env);

  top.terminalDcls = [];
  top.ruleDcls = [ruleSpec(ns.outputElement.typerep.typeName, 
                           [rhsSpec(top.grammarName, fName, getTypeNamesSignature(ns.inputElements), pm.productionModifiers)])];
  
  top.errors <- pm.errors;

  forwards to productionDcl(terminal(Abstract_kwd, "abstract", $1.line, $1.column), $2, id, ns, body);
}

nonterminal ProductionModifiers with location, file, pp, unparse, productionModifiers, errors, env;
nonterminal ProductionModifier with location, file, pp, unparse, productionModifiers, errors, env;

abstract production productionModifiersNone
top::ProductionModifiers ::=
{
  top.pp = "";
  top.location = loc("", -1, -1);

  top.productionModifiers = [];
  top.errors := [];
}

concrete production productionModifierSingle
top::ProductionModifiers ::= pm::ProductionModifier
{
  top.pp = pm.pp;
  top.location = pm.location;
  
  top.productionModifiers = pm.productionModifiers;
  top.errors := pm.errors;
}

concrete production productionModifiersCons
top::ProductionModifiers ::= h::ProductionModifier ',' t::ProductionModifiers
{
  top.pp = h.pp ++ ", " ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.productionModifiers = h.productionModifiers ++ t.productionModifiers;
  top.errors := h.errors ++ t.errors;
}

concrete production productionModifierPrecedence
top::ProductionModifier ::= 'precedence' '=' i::Int_t
{
  top.pp = "precedence = " ++ i.lexeme;
  top.location = loc(top.file, $1.line, $1.column);

  top.productionModifiers = [precedenceProductionModifierSpec(toInt(i.lexeme))];
  top.errors := [];
}

terminal Operator_kwd /operator/ lexer classes {KEYWORD};

concrete production productionModifierOperator
top::ProductionModifier ::= 'operator' '=' n::QName
{
  top.pp = "operator = " ++ n.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.productionModifiers = [operatorProductionModifierSpec(n.lookupType.fullName)];

  top.errors := n.lookupType.errors ++
                if !n.lookupType.typerep.isTerminal
                then [err(n.location, n.pp ++ " is not a terminal.")]
                else [];
}

