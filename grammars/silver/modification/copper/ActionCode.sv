grammar silver:modification:copper;

terminal Action_kwd 'action' lexer classes {KEYWORD};

concrete production concreteProductionDclAction
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 'action' acode::ActionCode_c
{
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.syntaxAst = [
    syntaxProduction(fName, ns.outputElement.typerep, getTypesSignature(ns.inputElements),
      foldr_p(consProductionMod, nilProductionMod(), 
        prodAction(acode.actionCode) :: pm.productionModifiers))];

  top.pp = forward.pp ++ "action " ++ acode.pp;

  acode.blockContext = actionContext();

  acode.env = newScopeEnv(
                addTerminalAttrDefs(
                 appendDefs(acode.defs, ns.actionDefs)), top.env);

  production attribute namedSig :: Decorated NamedSignature;
  namedSig = namedSignatureDcl(fName, ns.inputElements, ns.outputElement);

  acode.signature = namedSig;

  top.errors <- acode.errors;
  top.warnings <- acode.warnings;

  -- note that we're not merging the typing contexts between action blocks and productions
  -- this seems reasonable since inference should never have effects across this border...

  forwards to concreteProductionDcl($1, $2, id, ns, pm, body);
}


nonterminal ActionCode_c with pp,actionCode,env,defs,grammarName,signature,file,errors,blockContext,warnings;

synthesized attribute actionCode :: String;

concrete production actionCode_c
top::ActionCode_c ::= '{' stmts::ProductionStmts '}'
{
  top.pp = "{\n" ++ stmts.pp ++ "}\n";
  top.defs = hackTransformLocals(stmts.defs.valueList);

  top.actionCode = hacklocaldeclarations(stmts.defs.valueList) ++ stmts.translation;

  top.errors := stmts.errors;
  top.warnings := stmts.warnings;
  
  stmts.downSubst = emptySubst();
  stmts.finalSubst = stmts.upSubst;
}

concrete production actionCodeEmpty_c
top::ActionCode_c ::= '{' '}'
{
  top.actionCode = "";
  forwards to actionCode_c($1,productionStmtsNone(),$2);
}

-- TODO hacky. ideally we'd do this where local attributes are declared, not here.
function hacklocaldeclarations
String ::= l::[Decorated EnvItem]
{
  return if null(l) then "" else head(l).dcl.typerep.transType ++ " " ++ makeCopperName(head(l).dcl.fullName) ++ ";\n" ++ hacklocaldeclarations(tail(l));
}

function hackTransformLocals
Defs ::= l::[Decorated EnvItem]
{
  return if null(l) then emptyDefs()
         else case head(l).dcl of
                localDcl(sg,sl,fn,ty) -> addParserLocalDcl(sg,sl,fn,ty, hackTransformLocals(tail(l)))
              | _ -> hackTransformLocals(tail(l)) -- TODO: possibly error??
              end;
}

--------------------------------------------------------------------------------
-- Making children available in production action blocks

-- We don't care about the LHS.

synthesized attribute actionDefs :: Defs occurs on ProductionSignature, ProductionRHS, ProductionRHSElem;

aspect production productionSignature
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  top.actionDefs = rhs.actionDefs;
}

aspect production productionRHSNil
top::ProductionRHS ::= 
{
  top.actionDefs = emptyDefs();
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.actionDefs = appendDefs(h.actionDefs, t.actionDefs);
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::Type
{
  top.actionDefs = addActionChildDcl(top.grammarName, t.location, fName, t.typerep, emptyDefs());
}

