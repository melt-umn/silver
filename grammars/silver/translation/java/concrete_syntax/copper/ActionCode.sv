grammar silver:translation:java:concrete_syntax:copper;

import silver:translation:java:env;
import silver:definition:core;
import silver:definition:concrete_syntax;
--import silver:definition:type:anytype;
import silver:definition:env;
import silver:translation:java:core;
import silver:translation:java:concrete_syntax;
import silver:analysis:typechecking:core;

synthesized attribute actionCode :: String;

nonterminal ActionCode_c with pp,actionCode,env,defs,grammarName,localsEnv,signature,signatureEnv,file,errors,typeErrors;

terminal Action_kwd 'action' lexer classes {KEYWORD};

attribute actionCode occurs on RHSSpec, ProductionModifierSpec;

function actionProductionModifierSpec
Decorated ProductionModifierSpec ::= s::String
{
  return decorate i_actionProductionModifierSpec(s) with {};
}

abstract production i_actionProductionModifierSpec
top::ProductionModifierSpec ::= s::String
{
  top.unparse = "action \"" ++ escapeString(s) ++ "\"";
  top.actionCode = s;
  forwards to defaultProductionModifierSpec();
}

aspect production defaultProductionModifierSpec
top::ProductionModifierSpec ::={
  top.actionCode = "";
}

aspect production i_rhsSpec
top::RHSSpec ::= gn::String fn::String ns::[String] pm::[Decorated ProductionModifierSpec]
{
  top.actionCode = findProductionAction(pm);
}

function findProductionAction
String ::= l::[Decorated ProductionModifierSpec]{
  return if null(l) then "" else if head(l).actionCode != "" then head(l).actionCode else findProductionAction(tail(l));
}

concrete production concreteProductionDclAction
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature body::ProductionBody 'action' acode::ActionCode_c
{
  forwards to concreteProductionDclModifiersAction($1, $2, id, ns, productionModifiersNone(), body, $6, acode);
}

concrete production concreteProductionDclModifiersAction
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 'action' acode::ActionCode_c
{
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.ruleDcls = [ruleSpec(ns.outputElement.typerep.typeName,
                           [rhsSpec(top.grammarName, fName, getTypeNamesSignature(ns.inputElements),
                                    cons(actionProductionModifierSpec(acode.actionCode), pm.productionModifiers))])];

  top.pp = forward.pp ++ "action " ++ acode.pp;

  acode.actionCodeType = productionActionType();

  acode.signatureEnv = toEnv(ns.defs);
  acode.localsEnv = toEnv(acode.defs);
  acode.env = newScopeEnv(
                addTerminalAttrDefs(
                 appendDefs(acode.defs, ns.defs)), top.env);

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);

  acode.signature = namedSig;

  top.errors <- acode.errors;
  top.typeErrors = forward.typeErrors ++ acode.typeErrors;

  forwards to concreteProductionDclModifiers($1, $2, id, ns, pm, body);
}

concrete production actionCode_c
top::ActionCode_c ::= '{' stmts::ProductionStmts '}'
{
  top.pp = "{\n" ++ stmts.pp ++ "}\n";
  top.defs = stmts.defs;

  top.actionCode = localdeclarations(stmts.defs.valueList) ++ stmts.translation;

  top.errors := stmts.errors;
  top.typeErrors = stmts.typeErrors;
}

concrete production actionCodeEmpty_c
top::ActionCode_c ::= '{' '}'
{
  top.actionCode = "";
  forwards to actionCode_c($1,productionStmtsNone(),$2);
}

-- TODO hacky. ideally we'd do this where local attributes are declared, not here.
function localdeclarations
String ::= l::[Decorated EnvItem]
{
  return if null(l) then "" else head(l).typerep.transType ++ " " ++ makeCopperName(head(l).itemName) ++ ";\n" ++ localdeclarations(tail(l));
}

