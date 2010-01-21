grammar silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;

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
  top.pp = "concrete production " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp; 
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);
  ns.env = appendDefsEnv(ns.defs, pushScope(top.env));

  top.terminalDcls = [];
  top.ruleDcls = [ruleSpec(ns.outputElement.typerep.typeName, [rhsSpec(top.grammarName, fName, getTypeNamesSignature(ns.inputElements), [])])];
  
  forwards to productionDcl(terminal(Abstract_kwd, "abstract", $2.line, $2.column), $2, id, ns, body);
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
  ns.env = appendDefsEnv(ns.defs, pushScope(top.env));

  top.terminalDcls = [];
  top.ruleDcls = [ruleSpec(ns.outputElement.typerep.typeName, [rhsSpec(top.grammarName, fName, getTypeNamesSignature(ns.inputElements), pm.productionModifiers)])];
  
  top.errors <- pm.errors;

  forwards to productionDcl(terminal(Abstract_kwd, "abstract", $2.line, $2.column), $2, id, ns, body);
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

  top.productionModifiers = [operatorProductionModifierSpec(fName)];

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(n.name,top.env);

  production attribute fName :: String;
  fName = if null(fNames) then n.name else head(fNames).fullName;

  production attribute typeItem :: [Decorated EnvItem];
  typeItem = getTypeDcl(fName, top.env);

  top.errors := if null(typeItem) then [err(top.location, "Unknown terminal " ++ n.pp)] else
               if !head(typeItem).typerep.isTerminal then [err(top.location, n.pp ++ " is not a terminal.")] else
               [];
}

