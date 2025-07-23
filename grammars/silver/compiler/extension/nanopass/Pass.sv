grammar silver:compiler:extension:nanopass;

terminal Pass_t 'pass' lexer classes {KEYWORD};

concrete production translationPassDcl
top::AGDcl ::= 'translation' 'pass' a::Name 'to' target::QName ';'
{
  top.unparse = "translation pass " ++ a.unparse ++ " to " ++ target.unparse ++ ";";
  top.moduleNames := [];

  production fName :: String = top.grammarName ++ ":" ++ a.name;
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [errFromOrigin(a, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];
  top.errors <-
    if null(searchEnvTree(target.name, top.compiledGrammars))
    then [errFromOrigin(target, "Target grammar '" ++ target.name ++ "' not found.")]
    else [];
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(passDcl(fName, target.name, sourceGrammar=top.grammarName, sourceLocation=a.nameLoc)))]);
}

production passDcl
top::AttributeDclInfo ::= fn::String target::String
{
  top.fullName = fn;
  propagate compareKey, isEqual;

  production tyVar::TyVar = freshTyVar(starKind());
  top.typeScheme = polyType([tyVar], varType(tyVar));
  top.isSynthesized = true;
  top.isTranslation = true;
  
  top.decoratedAccessHandler = transDecoratedAccessHandler;
  top.undecoratedAccessHandler = transUndecoratedAccessErrorHandler;
  top.dataAccessHandler = transUndecoratedAccessErrorHandler;
  top.attrDefDispatcher = synthesizedAttributeDef; -- Allow normal syn equations
  top.attributionDispatcher = passAttributionDcl(target);
  top.propagateDispatcher = propagatePass(target);
}

production passAttributionDcl implements AttributionDcl
top::AGDcl ::= at::QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs target::String
{
  top.unparse = "attribute " ++ at.unparse ++ attl.unparse ++ " occurs on " ++ nt.unparse ++ nttl.unparse ++ ";";
  top.moduleNames := [];

  local ntShortName::String = shortNameOf(nt.lookupType.fullName);
  local targetNT::String = target ++ ":" ++ ntShortName;

  nondecorated local newAttl::BracketedOptTypeExprs =
    botlSome(bTypeList(
      '<', typeListSingle(
        case getTypeDcl(targetNT, top.env) of
        | dcl :: _ ->
          case nttl of
          | botlNone() -> typerepTypeExpr(dcl.typeScheme.typerep)
          | botlSome(btl) -> appTypeExpr(typerepTypeExpr(dcl.typeScheme.typerep), ^btl)
          end
        | [] -> errorTypeExpr(
            [errFromOrigin(nt, s"Type declaration for nonterminal '${ntShortName}' not found in target grammar '${target}' of pass '${at.lookupAttribute.fullName}'")])
        end),
      '>'));

  top.errors <-
    if length(attl.types) > 0
    then [errFromOrigin(attl, "Unexpected type parameters for translation pass '" ++ at.unparse ++ "'.")]
    else [];

  forwards to altParamAttributionDcl(@at, @attl, @nt, @nttl, defaultAttributionDcl, newAttl);
}

production propagatePass implements Propagate
top::ProductionStmt ::= includeShared::Boolean @attr::QName target::String
{
  top.unparse = s"propagate ${if includeShared then "@" else ""}${attr.unparse};";

  local prodShortName::String = shortNameOf(top.frame.fullName);
  local targetProd::String = target ++ ":" ++ prodShortName;

  local inputs :: [Expr] = 
    map(\ input::NamedSignatureElement ->
      case getOccursDcl(attr.lookupAttribute.dcl.fullName, input.typerep.typeName, top.env) of
      | dcl :: _ -> Silver_Expr { @$name{input.elementName}.$QName{^attr} }
      -- TODO: Currently, we don't do any special error checking in case
      -- the attribute doesn't occur on a nonterminal child when it should;
      -- we just get a type error.
      | [] ->
        if isDecorable(input.typerep, top.env)
        then Silver_Expr { @$name{input.elementName} }
        else Silver_Expr { $name{input.elementName} }
      end,
      top.frame.signature.inputElements);
  local annotations :: [Pair<String Expr>] =
    -- TODO: Support annotated attributes
    map(
      makeAnnoArg(top.frame.signature.outputElement.elementName, _),
      top.frame.signature.namedInputElements);
  nondecorated local result::Expr =
    mkFullFunctionInvocation(baseExpr(qName(targetProd)), inputs, annotations);

  forwards to propagateImpl(includeShared, attr,
    case getValueDcl(targetProd, top.env) of
    | dcl :: _ ->
      Silver_ProductionStmt {
        $name{top.frame.signature.outputElement.elementName}.$QName{^attr} = $Expr{result};
      }
    | [] -> errorProductionStmt([errFromOrigin(attr, s"Production '${prodShortName}' not found in target grammar '${target}' of pass '${attr.lookupAttribute.fullName}'")])
    end);
}
