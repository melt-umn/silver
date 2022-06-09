grammar silver:compiler:extension:do_notation;

concrete production do_c
top::Expr ::= 'do' '{' body::DoBody '}'
{
  top.unparse = s"do {${body.unparse}}";

  body.bindingFreeVars = mempty;
  body.isApplicative = false;
  body.applicativeParams = error("not needed");
  body.applicativeArgs = error("not needed");
  body.applicativeResult = error("not needed");
  body.resultTransform = nothing();
  
  forwards to unsafeTracePrint(body.transform, top.location.unparse ++ " : " ++ top.unparse ++ " : " ++ body.transform.unparse ++ "\n\n\n");
}

synthesized attribute bindingNames::[String];
synthesized attribute bindingTypes::[TypeExpr];

inherited attribute bindingFreeVars::ts:Set<String>;
inherited attribute isApplicative::Boolean;
inherited attribute applicativeParams::[ProductionRHSElem];
inherited attribute applicativeArgs::[Expr];
inherited attribute applicativeResult::Expr;

inherited attribute resultTransform::Maybe<Expr>;
synthesized attribute transform::Expr;

nonterminal DoBody with
  location, unparse, frame, bindingNames, bindingTypes, bindingFreeVars,
  isApplicative, applicativeParams, applicativeArgs, applicativeResult,
  resultTransform, transform;

concrete production bindExprDoBody
top::DoBody ::= init::DoBody n::Name DoDoubleColon_t t::TypeExpr '<-' e::Expr ';'
{
  top.unparse = s"${init.unparse} ${n.unparse}::${t.unparse} <- ${e.unparse};";
  top.bindingNames = n.name :: init.bindingNames;
  top.bindingTypes = t :: init.bindingTypes;

  init.bindingFreeVars = e.freeVars ++ ts:removeAll([n.name], top.bindingFreeVars);
  init.isApplicative = !ts:contains(n.name, top.bindingFreeVars) && top.isApplicative;
  init.applicativeParams =
    productionRHSElem(n, terminal(ColonColon_t, "::"), t, location=top.location) ::
    top.applicativeParams;
  init.applicativeArgs = e :: top.applicativeArgs;
  init.applicativeResult = top.applicativeResult;

  local cont :: Expr =
    lambdap(
      productionRHSCons(
        productionRHSElem(n, terminal(ColonColon_t, "::"), t, location=top.location),
        productionRHSNil(location=top.location),
        location=top.location),
      case top.resultTransform of
      | nothing() -> errorExpr([err(n.location, "Bind cannot be final statement in do body")], location=top.location)
      | just(res) -> res
      end,
      location=top.location);
  init.resultTransform = just(
    if init.isApplicative
    then applicativeTransform(init.applicativeParams, init.applicativeArgs, init.applicativeResult, top.location)
    else mkStrFunctionInvocation(top.location, "silver:core:bind", [e, cont]));
  top.transform = init.transform;
}

concrete production sequenceDoBody
top::DoBody ::= init::DoBody e::Expr ';'
{
  top.unparse = s"${init.unparse} ${e.unparse};";
  top.bindingNames = init.bindingNames;
  top.bindingTypes = init.bindingTypes;

  init.bindingFreeVars = e.freeVars ++ top.bindingFreeVars;
  init.isApplicative = top.isApplicative;
  init.applicativeParams =
    productionRHSElemType(typerepTypeExpr(freshType(), location=top.location), location=top.location) ::
    top.applicativeParams;
  init.applicativeArgs = e :: top.applicativeArgs;
  init.applicativeResult = top.applicativeResult;

  init.resultTransform = just(
    case top.resultTransform of
    | nothing() -> e
    | just(_) when top.isApplicative ->
        applicativeTransform(init.applicativeParams, init.applicativeArgs, init.applicativeResult, top.location)
    | just(res) -> mkStrFunctionInvocation(top.location, "silver:core:applySecond", [e, res])
    end);
  top.transform = init.transform;
}

concrete production letExprDoBody
top::DoBody ::= init::DoBody 'let' n::Name '::' t::TypeExpr '=' e::Expr ';'
{
  top.unparse = s"${init.unparse} let ${n.unparse}::${t.unparse} = ${e.unparse};";
  top.bindingNames = n.name :: init.bindingNames;
  top.bindingTypes = t :: init.bindingTypes;

  init.bindingFreeVars = e.freeVars ++ ts:removeAll([n.name], top.bindingFreeVars);
  init.isApplicative = false;
  init.applicativeParams = error("Not applicative");
  init.applicativeArgs = error("Not applicative");
  init.applicativeResult = error("Not applicative");

  init.resultTransform = just(letp(
    assignExpr(n, terminal(ColonColon_t, "::"), t, '=', e, location=top.location),
    case top.resultTransform of
    | nothing() -> errorExpr([err(n.location, "let cannot be final statement in do body")], location=top.location)
    | just(res) -> res
    end,
    location=top.location));
  top.transform = init.transform;
}

concrete production recDoBody
top::DoBody ::= init::DoBody 'rec' '{' recBody::DoBody '}'
{
  top.unparse = s"${init.unparse} rec { ${recBody.unparse} }";
  top.bindingNames = init.bindingNames ++ recBody.bindingNames;
  top.bindingTypes = init.bindingTypes ++ recBody.bindingTypes;

  init.bindingFreeVars = ts:removeAll(recBody.bindingNames, recBody.bindingFreeVars ++ top.bindingFreeVars);
  init.isApplicative = false;
  init.applicativeParams = error("Not applicative");
  init.applicativeArgs = error("Not applicative");
  init.applicativeResult = error("Not applicative");

  local recItemsName::String = s"_rec_items_${toString(genInt())}";
  local recItemsType::TypeExpr =
    if null(recBody.bindingTypes)
    then Silver_TypeExpr { silver:core:Unit }
    else foldr1(
      \ t1::TypeExpr t2::TypeExpr ->
        Silver_TypeExpr { silver:core:Pair<$TypeExpr{t1} $TypeExpr{t2}> },
      recBody.bindingTypes);
  local recItemsPattern::Pattern =
    if null(recBody.bindingNames)
    then Silver_Pattern { silver:core:unit() }
    else foldr1(
      \ p1::Pattern p2::Pattern ->
        Silver_Pattern { silver:core:pair($Pattern{p1}, $Pattern{p2}) },
      map(\ item::String -> Silver_Pattern { $name{item} }, recBody.bindingNames));
  local recItemsResult::Expr =
    if null(recBody.bindingNames)
    then Silver_Expr { silver:core:unit() }
    else foldr1(
      \ e1::Expr e2::Expr -> Silver_Expr { silver:core:pair($Expr{e1}, $Expr{e2}) },
      map(\ item::String -> Silver_Expr { $name{item} }, recBody.bindingNames));

  recBody.bindingFreeVars = mempty;
  recBody.isApplicative = true;
  recBody.applicativeParams = [];
  recBody.applicativeArgs = [];
  recBody.applicativeResult = recItemsResult;
  recBody.resultTransform = just(mkStrFunctionInvocation(top.location, "silver:core:pure", [recItemsResult]));

  init.resultTransform = just(
    case top.resultTransform of
    | nothing() -> errorExpr([err(recBody.location, "rec cannot be final statement in do body")], location=top.location)
    | just(res) -> Silver_Expr {
      silver:core:bind(
        mfix(
          \ $name{recItemsName}::$TypeExpr{recItemsType} ->
            case $name{recItemsName} of
            | $Pattern{recItemsPattern} -> $Expr{recBody.transform}
            end),
        \ $name{recItemsName}::$TypeExpr{recItemsType} ->
          case $name{recItemsName} of
          | $Pattern{recItemsPattern} -> $Expr{res}
          end)
    }
    end);
  top.transform = init.transform;
}

concrete production returnDoBody
top::DoBody ::= init::DoBody 'return' e::Expr ';'
{
  top.unparse = s"${init.unparse} return ${e.unparse};";
  top.bindingNames = init.bindingNames;
  top.bindingTypes = init.bindingTypes;

  init.bindingFreeVars = mempty;
  init.isApplicative = !top.resultTransform.isJust;
  init.applicativeParams = [];
  init.applicativeArgs = [];
  init.applicativeResult = e;

  init.resultTransform = just(
    case top.resultTransform of
    | nothing() -> mkStrFunctionInvocation(top.location, "silver:core:pure", [e])
    | just(_) -> errorExpr([err(e.location, "return must be final statement in do body")], location=top.location)
    end);
  top.transform = init.transform;
}

concrete production emptyDoBody
top::DoBody ::=
{
  top.unparse = s"";
  top.bindingNames = [];
  top.bindingTypes = [];
  top.transform =
    case top.resultTransform of
    | nothing() -> errorExpr([err(top.location, "do body cannot be empty")], location=top.location)
    | just(res) -> res
    end;
}

function applicativeTransform
Expr ::= params::[ProductionRHSElem] args::[Expr] res::Expr loc::Location
{
  return
    foldl(
      \ trans::Expr e::Expr -> mkStrFunctionInvocation(loc, "silver:core:ap", [trans, e]),
      mkStrFunctionInvocation(loc, "silver:core:map", [
        foldr(
          \ el::ProductionRHSElem trans::Expr ->
            lambdap(
              productionRHSCons(el, productionRHSNil(location=loc), location=loc),
              trans, location=loc),
          res, params),
        head(args)]),
      tail(args));
}
