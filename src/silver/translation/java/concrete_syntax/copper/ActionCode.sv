grammar silver:translation:java:concrete_syntax:copper;

import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:type:anytype;
import silver:definition:env;
import silver:translation:java:core;
import silver:translation:java:concrete_syntax;
import silver:analysis:typechecking:core;

synthesized attribute hasActionCode :: Boolean;
synthesized attribute actionCode :: String;

nonterminal ActionCode_c with actionCode,env,defs,grammarName,localsEnv,signature,signatureEnv, file;
attribute actionCode occurs on ProductionStmts,ProductionStmt,AttributeDef,LocalAttributeDcl, LHSExpr,Expr,Exprs;

synthesized attribute actionErrors :: [Decorated Message];
synthesized attribute actionTypeErrors :: [Decorated Message];

attribute actionErrors occurs on ActionCode_c, ProductionStmts,ProductionStmt,AttributeDef, LocalAttributeDcl, LHSExpr,Expr,Exprs;
attribute actionTypeErrors occurs on ActionCode_c, ProductionStmts,ProductionStmt,AttributeDef, LocalAttributeDcl,LHSExpr,Expr,Exprs;

terminal Action_kwd 'action' lexer classes {KEYWORD};

concrete production concreteProductionDclAction
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature body::ProductionBody 'action' acode::ActionCode_c
{
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.ruleDcls = [ruleSpec(ns.outputElement.typerep.typeName, [rhsSpecAction(top.grammarName, fName, getTypeNamesSignature(ns.inputElements), [], acode.actionCode)])];

  top.pp = "concrete production " ++ id.name ++  "\n" ++ 
	     ns.pp  ++ "  \n{\n" ++ body.pp ++ "\n}\naction\n  {@" ++
             acode.actionCode ++ "\n@}";

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);

  acode.signature = namedSig;

  acode.actionCodeType = productionActionType();
  acode.env = appendDefsEnv(appendDefs(acode.defs, appendDefs(ns.defs, addThisDcl(fName, emptyDefs()))), top.env);

  acode.signatureEnv = toEnv(ns.defs);
  acode.localsEnv = toEnv(acode.defs);

--TODO
--  top.errors := body.errors ++ acode.actionErrors;
--  top.typeErrors = body.typeErrors ++ acode.actionTypeErrors;

  forwards to concreteProductionDcl($1, $2, id, ns, body);
}

concrete production concreteProductionDclModifiersAction
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 'action' acode::ActionCode_c
{
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.ruleDcls = [ruleSpec(ns.outputElement.typerep.typeName, [rhsSpecAction(top.grammarName, fName, getTypeNamesSignature(ns.inputElements), pm.productionModifiers, acode.actionCode)])];

  top.pp = "concrete production " ++ id.name ++  "\n" ++ 
	     ns.pp  ++ "  \n{\n" ++ body.pp ++ "\n}\naction\n  {@" ++
             acode.actionCode ++ "\n@}";

  acode.actionCodeType = productionActionType();

  acode.env = appendDefsEnv(appendDefs(acode.defs, appendDefs(ns.defs, addThisDcl(fName, emptyDefs()))), top.env);

  acode.signatureEnv = toEnv(ns.defs);
  acode.localsEnv = toEnv(acode.defs);

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);

  acode.signature = namedSig;

--TODO
--  top.errors := body.errors ++ acode.actionErrors;
--  top.typeErrors = body.typeErrors ++ acode.actionTypeErrors;

  forwards to concreteProductionDclModifiers($1, $2, id, ns, pm, body);
}

function generateRHSDecls
String ::= sNames::[String] sTypes::[Decorated TypeRep] count::Integer
{
  local attribute elemType :: String;
  elemType = if head(sTypes).typeName == "String"
             then "String " ++ makeCopperName(head(sNames))
             else "common.Terminal " ++ makeCopperName(head(sNames));

  local attribute elemRetrievalCode :: String;
  elemRetrievalCode = if head(sTypes).typeName == "String"
                      then ""
                      else if count == -1
                           then " = _parseTree"
                           else " = (common.Terminal)" ++ "_children[" ++ toString(count) ++ "]";

  return if null(sNames)
         then ""
         else elemType ++ elemRetrievalCode ++";\n" ++ generateRHSDecls(tail(sNames),tail(sTypes),count + 1);
}

concrete production actionCode_c
top::ActionCode_c ::= '{' stmts::ProductionStmts '}'
{
  top.defs = stmts.defs;

  local attribute preambleCode :: String;
  preambleCode = if top.actionCodeType.isProductionAction
                 then generateRHSDecls(getNamesSignature(top.signature.inputElements),getTypesSignature(top.signature.inputElements),0)
                 else "";

  top.actionCode = preambleCode ++ stmts.actionCode;

  top.actionErrors = stmts.actionErrors;
  top.actionTypeErrors = stmts.actionTypeErrors;
}

concrete production actionCodeEmpty_c
top::ActionCode_c ::= '{' '}'
{
  top.actionCode = "";
  forwards to actionCode_c($1,productionStmtsNone(),$2);
}

aspect production productionStmtsNone
top::ProductionStmts ::= 
{
  top.actionCode = "";
  top.actionErrors = [err(top.location, "Construct 'productionStmtsNone' not allowed in parser actions")];
  top.actionTypeErrors = [];
}
aspect production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.actionCode = stmt.actionCode;
  top.actionTypeErrors = stmt.actionTypeErrors;
  top.actionErrors = stmt.actionErrors;
}

aspect production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt t::ProductionStmts
{
  top.actionCode = h.actionCode ++ t.actionCode;
  top.actionTypeErrors = h.actionTypeErrors ++ t.actionTypeErrors;
  top.actionErrors = h.actionErrors ++ t.actionErrors;
}

aspect production productionStmtForwardsTo
top::ProductionStmt ::= a::ForwardsToDcl
{
  top.actionCode = "";
  top.actionErrors = [err(top.location, "Construct 'forwardsDcl' not allowed in parser actions")];
  top.actionTypeErrors = [];
}


aspect production productionStmtForwardingWith
top::ProductionStmt ::= a::ForwardingWithDcl
{
  top.actionCode = "";
  top.actionErrors = [err(top.location, "Construct 'forwardingDclWith' not allowed in parser actions")];
  top.actionTypeErrors = [];
}

aspect production productionStmtLocalAttribute
top::ProductionStmt ::= a::LocalAttributeDcl
{
  top.actionCode = a.actionCode;
  top.actionTypeErrors = a.actionTypeErrors;
  top.actionErrors = a.actionErrors;
}

aspect production localAttributeDcl
top::LocalAttributeDcl ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.actionCode = te.typerep.parserAttrType ++ " " ++ makeCopperName(fName) ++ ";\n";

  top.actionErrors = [];
  top.actionTypeErrors = if te.typerep.isParserAttrType 
		    then [] 
		    else[err(top.location, "Type " ++ te.typerep.typeName ++ " is not valid for a local attribute declaration in a parser action")];
}

aspect production productionStmtAttributeDef
top::ProductionStmt ::= a::AttributeDef
{
  top.actionCode = a.actionCode;
  top.actionTypeErrors = a.actionTypeErrors;
  top.actionErrors = a.actionErrors;
}

aspect production attributeDef
top::AttributeDef ::= lhs::LHSExpr '=' e::Expr ';'
{
  local attribute translatedTopName :: String;
  translatedTopName = translatePassiveTopName(lhs.actionCode);

  top.actionCode = translatedTopName ++ "(" ++ e.actionCode ++ ");\n";

  top.actionTypeErrors =lhs.actionTypeErrors ++ e.actionTypeErrors;
  top.actionErrors = lhs.actionErrors ++ e.actionErrors;
}

aspect production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
  top.actionCode = "( " ++ e.actionCode ++ " )";
  top.actionTypeErrors = e.actionTypeErrors;
  top.actionErrors = e.actionErrors;
}

aspect production productionReference
top::Expr ::= q::QName
{
  top.actionCode = makeName(q.name);
  top.actionTypeErrors = [];
  top.actionErrors = [];
}


aspect production baseExpr
top::Expr ::= q::QName
{
  local attribute translatedTopName :: String;
  translatedTopName = translateActiveTopName(fName);

  top.actionCode = makeCopperName(translatedTopName);
  top.actionTypeErrors = [];
  top.actionErrors = [];
}

aspect production dontDecorateExpr
top::Expr ::= q::QName
{
  top.actionCode = "";
  top.actionErrors = [err(top.location, "Construct 'dontDecorateExpr' not allowed in parser actions")];
  top.actionTypeErrors = [];
}

aspect production decorateExpr
top::Expr ::= q::QName
{
  top.actionCode = "";
  top.actionErrors = [err(top.location, "Construct 'decorateExpr' not allowed in parser actions")];
  top.actionTypeErrors = [];
}


aspect production productionApp
top::Expr ::= e::Expr '(' es::Exprs ')'
{
  top.actionCode = e.actionCode ++ " ( " ++ es.actionCode ++ " ) ";
  top.actionTypeErrors = e.actionTypeErrors ++ es.actionTypeErrors;
  top.actionErrors = e.actionErrors ++ es.actionErrors;
}

--aspect production emptyList
--top::Expr ::= lb::LSqr_t rb::RSqr_t
--{
--  top.hasValidActionCode = false;
--  top.actionCode = actionCodeError(top.location ++ " Error: Untyped lists not allowed in parser action blocks; use [::type] notation instead.\n");
--  --top.actionCode = actionCodeCode("new java.util.LinkedList()");
--}
--
--aspect production emptyListWType
--top::Expr ::= lb::LSqr_t ht::HasType_t t::Type rb::RSqr_t
--{
--  top.hasValidActionCode = t.typerep.isParserAttrType;
--  top.actionCode = if t.typerep.isParserAttrType
--                   then actionCodeCode("new java.util.LinkedList< " ++ t.typerep.parserAttrType ++ " >()")
--                   else actionCodeError(top.location ++ " Error: Type " ++ t.typerep.typeName ++ " is not valid as a list element type in a parser action\n");
--}
--
--aspect production fullList
--top::Expr ::= lb::LSqr_t es::Exprs rb::RSqr_t
--{
--  top.hasValidActionCode = es.hasValidActionCode;
--  top.actionCode = actionCodeConcat(actionCodeCode("newList ( "),
--                    actionCodeConcat(es.actionCode,actionCodeCode(" ) ")));
--}
--
--aspect production consList
--top::Expr ::= ck::Cons_t lp::LParen_t h::Expr c::Comma_t t::Expr rp::RParen_t 
--{
--  top.hasValidActionCode = h.hasValidActionCode && t.hasValidActionCode;
--  top.actionCode = actionCodeConcat(actionCodeCode("listCons ( "),
--                    actionCodeConcat(h.actionCode,
--                     actionCodeConcat(actionCodeCode(","),
--                      actionCodeConcat(t.actionCode,actionCodeCode(" ) ")))));
--}


aspect production emptyProductionApp
top::Expr ::= e::Expr '(' ')'
{
  top.actionCode = e.actionCode ++ " () ";
  top.actionTypeErrors = e.actionTypeErrors;
  top.actionErrors = e.actionErrors;               
}

aspect production cast_t
top::Expr ::= e::Expr t_typerep::Decorated TypeRep
{
  top.actionCode = e.actionCode;    
  top.actionTypeErrors = e.actionTypeErrors;
  top.actionErrors = e.actionErrors;               
}


aspect production functionApplicationDispatcher
top::Expr ::= e::Expr es::Exprs
{
  top.actionCode = e.actionCode ++ " ( " ++ es.actionCode ++ " ) ";
  top.actionTypeErrors = e.actionTypeErrors ++ es.actionTypeErrors;
  top.actionErrors = e.actionErrors ++ es.actionErrors;
}


aspect production genericApplicationDispatcher
top::Expr ::= e::Expr es::Exprs
{
  top.actionCode = e.actionCode ++ " " ++ es.actionCode;
  top.actionTypeErrors = e.actionTypeErrors ++ es.actionTypeErrors;
  top.actionErrors = e.actionErrors ++ es.actionErrors;
}

function translatePassiveTopName
String ::= orig::String
{
   return if orig == "lexeme"
          then ""
          else if orig == "filename"
               then "virtualLocation.setFileName"
               else if orig == "line"
                    then "virtualLocation.setLine"
                    else if orig == "column"
                         then "virtualLocation.setColumn"
                         else orig;
}

function translateActiveTopName
String ::= orig::String
{
   return if orig == "lexeme"
          then "lexeme"
          else if orig == "filename"
               then "virtualLocation.getFileName()"
               else if orig == "line"
                    then "virtualLocation.getLine()"
                    else if orig == "column"
                         then "virtualLocation.getColumn()"
                         else orig;
}


function translateFieldName
String ::= orig::String te::[Decorated TypeRep]
{
   return if !null(te) && head(te).isTerminal
          then if orig == "lexeme"
               then "lexeme"
               else if orig == "filename"
                    then "virtualLocation.getFileName()"
                    else if orig == "line"
                         then "virtualLocation.getLine()"
                         else if orig == "column"
                              then "virtualLocation.getColumn()"
                              else ""
          else if orig == "listContains"
               then "listContains"
               else "";
}

aspect production atAccess
top::Expr ::= e::Expr '@' q::QName
{
  local attribute fieldTrans :: String;
  fieldTrans = translateFieldName(makeCopperName(fName),[e.typerep]);

  top.actionCode = e.actionCode ++  "." ++ fieldTrans;
  top.actionTypeErrors = e.actionTypeErrors;
  top.actionErrors = e.actionErrors;
}

aspect production trueConst
top::Expr ::= 'true'
{
  top.actionCode = "true";
  top.actionTypeErrors = [];
  top.actionErrors = [];
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.actionCode = "false";
  top.actionTypeErrors = [];
  top.actionErrors = [];
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.actionCode = e1.actionCode ++ " && " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.actionCode = e1.actionCode ++ " || " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}

aspect production not
top::Expr ::= '!' e::Expr
{
  top.actionCode = "! " ++ e.actionCode;
  top.actionTypeErrors = e.actionTypeErrors;
  top.actionErrors = e.actionErrors;
}

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.actionCode = e1.actionCode ++ " > " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.actionCode = e1.actionCode ++ " < " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}


aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.actionCode = e1.actionCode ++ " <= " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}


aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.actionCode = e1.actionCode ++ " <= " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}


aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.actionCode = e1.actionCode ++ ".equals(" ++ e2.actionCode ++ ")";
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}


aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.actionCode = "!" ++ e1.actionCode ++ ".equals(" ++ e2.actionCode ++ ")";
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}


aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.actionCode = "((" ++ e1.actionCode ++") ? (" ++ e2.actionCode ++ "):(" ++ e3.actionCode ++ "))";
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors ++ e3.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors ++ e3.actionErrors;
}

--aspect production appendList
--top::Expr ::= l::Decorated Expr o::AppendOp_t r::Decorated Expr
--{
--  top.hasValidActionCode = l.hasValidActionCode && r.hasValidActionCode;
--  top.actionCode = actionCodeConcat(actionCodeCode("listcat ( "),
--                    actionCodeConcat(l.actionCode,
--                     actionCodeConcat(actionCodeCode(", "),
--                      actionCodeConcat(r.actionCode,actionCodeCode(" ) ")))));
--}
--
--aspect production listLength
--top::Expr ::= e::Decorated Expr
--{
--  top.hasValidActionCode = e.hasValidActionCode;
--  top.actionCode = actionCodeConcat(e.actionCode,actionCodeCode(".length()"));
--}
--
--aspect production nullList
--top::Expr ::= n::Null_t lp::LParen_t l::Expr rp::RParen_t 
--{
--  top.hasValidActionCode = l.hasValidActionCode;
--  top.actionCode = actionCodeConcat(actionCodeCode(" ( "),
--                    actionCodeConcat(l.actionCode,actionCodeCode(".isEmpty() )")));
--}
--
--aspect production headListNoCast
--top::Expr ::= l::Expr 
--{
--  top.hasValidActionCode = l.hasValidActionCode;
--  top.actionCode = actionCodeConcat(actionCodeCode(" ( "),
--                    actionCodeConcat(l.actionCode,actionCodeCode(".getFirst() )")));
--}
--
--aspect production tailList
--top::Expr ::= t::Tail_t lp::LParen_t l::Expr rp::RParen_t 
--{
--  top.hasValidActionCode = l.hasValidActionCode;
--  top.actionCode = actionCodeConcat(actionCodeCode("listCdr ( "),
--                    actionCodeConcat(l.actionCode,actionCodeCode(" )")));
--}

aspect production intConst
top::Expr ::= i::Int_t
{
  top.actionCode = i.lexeme;
  top.actionTypeErrors = [];
  top.actionErrors = [];
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.actionCode = f.lexeme;
  top.actionTypeErrors = [];
  top.actionErrors = [];
} 

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.actionCode = e1.actionCode ++ " + " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}
aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.actionCode = e1.actionCode ++ " - " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}
aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.actionCode = e1.actionCode ++ " * " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}
aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.actionCode = e1.actionCode ++ " / " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}
aspect production neg
top::Expr ::= '-' e::Expr
{
  top.actionCode = "- " ++ e.actionCode;
  top.actionTypeErrors = e.actionTypeErrors;
  top.actionErrors = e.actionErrors;
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.actionCode = s.lexeme;
  top.actionTypeErrors = [];
  top.actionErrors = [];
}

aspect production defaultPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.actionCode = e1.actionCode ++ " + " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.actionCode = e1.actionCode ++ " + " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}

aspect production exprsEmpty
top::Exprs ::=
{
  top.actionCode = "";
  top.actionTypeErrors = [];
  top.actionErrors = [];
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.actionCode = e.actionCode;
  top.actionTypeErrors = e.actionTypeErrors;
  top.actionErrors = e.actionErrors;
}

aspect production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.actionCode = e1.actionCode ++ ", " ++ e2.actionCode;
  top.actionTypeErrors = e1.actionTypeErrors ++ e2.actionTypeErrors;
  top.actionErrors = e1.actionErrors ++ e2.actionErrors;
}

aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.actionCode = "";
  top.actionErrors = [err(top.location, "Construct 'decorate (...) with {...}' not allowed in parser actions")];
  top.actionTypeErrors = [];
}

aspect production fakeLHSExpr
top::LHSExpr ::= c1::QName c2::Decorated TypeRep
{
  top.actionCode = makeCopperName(fName);
  top.actionTypeErrors = [];
  top.actionErrors = [];
}

aspect production lhsExprOne
top::LHSExpr ::= id::Name
{
  top.actionCode = makeCopperName(fName);
  top.actionTypeErrors = [];
  top.actionErrors = [];
}

aspect production lhsExprTwo
top::LHSExpr ::= id::Name '.' q::QName
{
  top.actionCode = makeCopperName(fName1) ++ "." ++ makeCopperName(fName2);
  top.actionTypeErrors = [];
  top.actionErrors = [];
}