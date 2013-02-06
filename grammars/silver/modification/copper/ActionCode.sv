grammar silver:modification:copper;

terminal Action_kwd 'action' lexer classes {KEYWORD};

concrete production concreteProductionDclAction
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody 'action' acode::ActionCode_c
{
  top.pp = forward.pp ++ "action " ++ acode.pp;

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production namedSig :: NamedSignature = ns.namedSignature;

  top.syntaxAst = [
    syntaxProduction(fName, namedSig.outputElement.typerep, namedSig.inputTypes,
      foldr(consProductionMod, nilProductionMod(), 
        prodAction(acode.actionCode) :: pm.productionModifiers))];

  ns.signatureName = fName;
  acode.blockContext = actionContext();
  acode.signature = namedSig;
  acode.env = newScopeEnv(
                addTerminalAttrDefs(
                 acode.defs ++ ns.actionDefs), top.env);

  top.errors <- acode.errors;

  -- note that we're not merging the typing contexts between action blocks and productions
  -- this seems reasonable since inference should never have effects across this border...

  forwards to concreteProductionDcl($1, $2, id, ns, pm, body);
}


nonterminal ActionCode_c with config,pp,actionCode,env,defs,grammarName,signature,file,errors,blockContext, compiledGrammars, flowEnv;

synthesized attribute actionCode :: String;

concrete production actionCode_c
top::ActionCode_c ::= '{' stmts::ProductionStmts '}'
{
  top.pp = "{\n" ++ stmts.pp ++ "}\n";
  top.defs = hackTransformLocals(stmts.defs);

  top.actionCode = hacklocaldeclarations(stmts.defs) ++ stmts.translation;

  top.errors := stmts.errors;
  
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
String ::= l::[Def]
{
  return if null(l) then "" else head(l).dcl.typerep.transType ++ " " ++ makeCopperName(head(l).dcl.fullName) ++ ";\n" ++ hacklocaldeclarations(tail(l));
}

function hackTransformLocals
[Def] ::= l::[Def]
{
  return if null(l) then []
         else case head(l).dcl of
              | localDcl(sg,sl,fn,ty) -> parserLocalDef(sg,sl,fn,ty) :: hackTransformLocals(tail(l))
              | _ -> hackTransformLocals(tail(l)) -- TODO: possibly error??
              end;
}

--------------------------------------------------------------------------------
-- Making children available in production action blocks

-- We don't care about the LHS.

synthesized attribute actionDefs :: [Def] occurs on ProductionSignature, ProductionRHS, ProductionRHSElem;

aspect production productionSignature
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
{
  top.actionDefs = rhs.actionDefs;
}

aspect production productionRHSNil
top::ProductionRHS ::= 
{
  top.actionDefs = [];
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.actionDefs = h.actionDefs ++ t.actionDefs;
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::Type
{
  top.actionDefs = [actionChildDef(top.grammarName, t.location, id.name, t.typerep)];
}

