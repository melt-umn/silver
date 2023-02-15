grammar silver:compiler:extension:implicit_monads;

--whether an expression needs to be bound into its immediate parent
--I think this is for let insertion, but I'll leave it here anyway
inherited attribute monadicallyUsed::Boolean occurs on Expr;
--a collection of names/attribute accesses that are monadically used
--it's a list of expressions for attribute accesses
--I think this is for let insertion too
synthesized attribute monadicNames::[Expr] occurs on Expr, AppExpr, AppExprs;

attribute monadRewritten<Expr>, merrors, mtyperep, mDownSubst, mUpSubst, expectedMonad occurs on Expr;
propagate expectedMonad on Expr;


type MonadInhs = {
  downSubst, finalSubst, frame, grammarName, isRoot, originRules,
  compiledGrammars, config, env, flowEnv, expectedMonad, mDownSubst
};


--list of the attributes accessed in an explicit expression not allowed there
--this is turned into a list of appropriate error messages at the equation
monoid attribute notExplicitAttributes::[Pair<String Location>];
attribute notExplicitAttributes occurs on Expr, AppExprs, AnnoAppExprs, MRuleList, Exprs, MatchRule, AbstractMatchRule, AssignExpr;
propagate notExplicitAttributes on Expr, AppExprs, AnnoAppExprs, MRuleList, Exprs, AssignExpr excluding forwardAccess;


aspect default production
top::Expr ::=
{
  top.merrors := [];
}


aspect production errorExpr
top::Expr ::= e::[Message]
{
  top.merrors := e;
  propagate mDownSubst, mUpSubst;
  top.mtyperep = errorType();
  top.monadicNames = [];
  top.monadRewritten = errorExpr(e, location=top.location);
}

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated! QName
{
  top.merrors := msg;
  propagate mDownSubst, mUpSubst;
  top.mtyperep = errorType();
  top.monadicNames = [];
  top.monadRewritten = baseExpr(q, location=top.location);
}

aspect production childReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = if isDecorable(q.lookupValue.typeScheme.typerep, top.env)
                 then q.lookupValue.typeScheme.asNtOrDecType
                 else q.lookupValue.typeScheme.monoType;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production lhsReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = q.lookupValue.typeScheme.asNtOrDecType;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production localReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = if isDecorable(q.lookupValue.typeScheme.typerep, top.env)
                 then q.lookupValue.typeScheme.asNtOrDecType
                 else q.lookupValue.typeScheme.monoType;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production forwardReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  -- An LHS (and thus, forward) is *always* a decorable (nonterminal) type.
  top.mtyperep = q.lookupValue.typeScheme.asNtOrDecType;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production productionReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = q.lookupValue.typeScheme.typerep;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production functionReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = q.lookupValue.typeScheme.typerep;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production classMemberReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = q.lookupValue.typeScheme.typerep;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production globalValueReference
top::Expr ::= q::Decorated! QName
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = q.lookupValue.typeScheme.typerep;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production application
top::Expr ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
  {-
    We bind e in here because this would otherwise forward to an error.
    Everything else will work out fine by rewriting in a forward other than this.
    Errors might not be great if we have different monads here and in arguments;
       once partial application works, we could just do the whole rewriting here
  -}
  local ne::Expr = new(e);
  ne.mDownSubst = top.mDownSubst;
  ne.env = top.env;
  ne.flowEnv = top.flowEnv;
  ne.config = top.config;
  ne.compiledGrammars = top.compiledGrammars;
  ne.grammarName = top.grammarName;
  ne.frame = top.frame;
  ne.finalSubst = top.finalSubst;
  ne.downSubst = top.downSubst;
  ne.originRules = top.originRules;
  ne.isRoot = false;
  local nes::AppExprs = new(es);
  nes.mDownSubst = ne.mUpSubst;
  nes.flowEnv = top.flowEnv;
  nes.env = top.env;
  nes.config = top.config;
  nes.compiledGrammars = top.compiledGrammars;
  nes.grammarName = top.grammarName;
  nes.frame = top.frame;
  nes.finalSubst = top.finalSubst;
  nes.downSubst = top.downSubst;
  nes.originRules = top.originRules;
  nes.appExprTypereps = reverse(performSubstitution(ne.mtyperep, ne.mUpSubst).inputTypes);
  nes.appExprApplied = ne.unparse;
  nes.monadArgumentsAllowed = acceptableMonadFunction(e);

  ne.expectedMonad = top.expectedMonad;
  nes.expectedMonad = top.expectedMonad;

  top.merrors := ne.merrors ++ nes.merrors;
  top.mUpSubst = nes.mUpSubst;

  top.merrors <-
      case anns of
      | emptyAnnoAppExprs() ->
        []
      | _ ->
        if null(nes.monadTypesLocations)
        then []
        else [err(top.location, "Monad Rewriting not defined with annotated " ++
                                "expressions in a function application")]
      end;

  local substTy::Type = performSubstitution(ne.mtyperep, top.mUpSubst);
  local ety :: Type =
        if isMonad(substTy, top.env) &&
           monadsMatch(top.expectedMonad, substTy, top.mDownSubst).fst
        then monadInnerType(substTy, top.location)
        else substTy;

  --needs to add a monad to the result if there are monadic args or the function is monadic
  top.mtyperep =
      if null(nes.monadTypesLocations)
      then if isMonad(substTy, top.env) && monadsMatch(top.expectedMonad, substTy, top.mDownSubst).fst
           then monadOfType(top.expectedMonad, ety.outputType)
           else ety.outputType
      else if isMonad(ety.outputType, top.env) && fst(monadsMatch(ety.outputType, top.expectedMonad, top.mUpSubst))
           then ety.outputType
           else monadOfType(top.expectedMonad, ety.outputType);

  ne.monadicallyUsed = isMonad(ne.mtyperep, top.env) && fst(monadsMatch(ne.mtyperep, top.expectedMonad, top.mUpSubst));
  top.monadicNames = ne.monadicNames ++ nes.monadicNames;

  --whether we need to wrap the ultimate function call in monadRewritten in a Return
  local wrapReturn::Boolean =
        --monadic args                  or  monadic function
        (!null(nes.monadTypesLocations) || (isMonad(substTy, top.env) && monadsMatch(substTy, top.expectedMonad, top.mUpSubst).fst)) &&
        --not monadic result               or  not the right monad
        (!isMonad(ety.outputType, top.env) || !fst(monadsMatch(ety.outputType, top.expectedMonad, top.mUpSubst)));

  {-
    Monad translation creates a lambda to apply to all the arguments
    plus the function (to get fresh names for everything), then
    creates a body that binds all the monadic arguments into the final
    function application.

    For example, if we have
       fun(a, b, c, d)
    where a and d are monadic, then we translate into
       (\a1 a2 a3 a4 f. a1 >>= (\a1. a4 >>= (\a4. f(a1, a2, a3, a4))))(a, b, c, d, fun)
    Reusing ai in the bind for the ith argument simplifies doing the
    application inside all the binds.
  -}
  local lambda_fun::Expr = buildMonadApplicationLambda(nes.realTypes, nes.monadTypesLocations, ety, wrapReturn, top.location);
  local expanded_args::AppExprs = snocAppExprs(nes.monadRewritten, ',', presentAppExpr(ne.monadRewritten, location=top.location),
                                               location=top.location);
  local bind_name::String = "__bindFun_" ++ toString(genInt());
  -- fun >>= \ bind_name -> lambda_fun(args, bind_name)
  local bind_fun_in::Expr =
        Silver_Expr {
          bind($Expr {if ne.mtyperep.isDecorated then mkStrFunctionInvocation(top.location, "silver:core:new", [ne.monadRewritten]) else ne.monadRewritten},
               $Expr {buildLambda(bind_name, monadInnerType(ne.mtyperep, top.location), applicationExpr(lambda_fun, '(', expanded_name_args, ')', location=top.location), top.location) })
        };
  local expanded_name_args::AppExprs =
        snocAppExprs(nes.monadRewritten, ',', presentAppExpr(baseExpr(qNameId(name(bind_name, top.location), location=top.location),
                     location=top.location), location=top.location), location=top.location);
  --haven't done monadRewritten on annotated ones, so ignore them
  top.monadRewritten =
      if isMonad(substTy, top.env) && monadsMatch(top.expectedMonad, substTy, top.mDownSubst).fst
      then bind_fun_in
      else if null(nes.monadTypesLocations)
           then applicationExpr(ne.monadRewritten, '(', nes.monadRewritten, ')', location=top.location)
           else applicationExpr(lambda_fun, '(', expanded_args, ')', location=top.location);
}

aspect production functionInvocation
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  local t::Expr = application(e, '(', es, ',', anns, ')', location=top.location);
  t.mDownSubst = top.mDownSubst;
  t.env = top.env;
  t.flowEnv = top.flowEnv;
  t.config = top.config;
  t.compiledGrammars = top.compiledGrammars;
  t.grammarName = top.grammarName;
  t.frame = top.frame;
  t.finalSubst = top.finalSubst;
  t.downSubst = top.downSubst;
  t.isRoot = top.isRoot;
  t.originRules = top.originRules;
  t.expectedMonad = top.expectedMonad;

  t.monadicallyUsed = top.monadicallyUsed;

  top.merrors := t.merrors;
  top.mUpSubst = t.mUpSubst;
  top.mtyperep = t.mtyperep;
  top.monadRewritten = t.monadRewritten;

  top.monadicNames = t.monadicNames;
}
--build the lambda to apply to all the original arguments plus the function
--we're going to assume this is only called if monadTysLocs is non-empty
function buildMonadApplicationLambda
Expr ::= realtys::[Type] monadTysLocs::[Pair<Type Integer>] funType::Type wrapReturn::Boolean loc::Location
{
  local funargs::AppExprs = buildFunArgs(length(realtys), loc);
  local params::ProductionRHS = buildMonadApplicationParams(realtys, 1, funType, loc);
  local body::Expr = buildMonadApplicationBody(monadTysLocs, funargs, head(monadTysLocs).fst, wrapReturn, loc);
  return lambdap(params, body, location=loc);
}
--build the parameters for the lambda applied to all the original arguments plus the function
function buildMonadApplicationParams
ProductionRHS ::= realtys::[Type] currentLoc::Integer funType::Type loc::Location
{
  return if null(realtys)
         then productionRHSCons(productionRHSElem(name("f", loc),
                                                  '::',
                                                  typerepTypeExpr(funType, location=loc),
                                                  location=loc),
                                productionRHSNil(location=loc),
                                location=loc)
         else productionRHSCons(productionRHSElem(name("a"++toString(currentLoc), loc),
                                                  '::',
                                                  typerepTypeExpr(dropDecorated(head(realtys)), location=loc),
                                                  --typerepTypeExpr(head(realtys), location=loc),
                                                  location=loc),
                                buildMonadApplicationParams(tail(realtys), currentLoc+1, funType, loc),
                                location=loc);
}
--build the arguments for the application inside all the binds
function buildFunArgs
AppExprs ::= currentIndex::Integer loc::Location
{
  return if currentIndex == 0
         then emptyAppExprs(location=loc)
         else snocAppExprs(buildFunArgs(currentIndex - 1, loc), ',',
                           presentAppExpr(baseExpr(qName(loc,
                                                         "a"++toString(currentIndex)),
                                                   location=loc),
                                          location=loc), location=loc);
}
--build the body of the lambda which includes all the binds
function buildMonadApplicationBody
Expr ::= monadTysLocs::[Pair<Type Integer>] funargs::AppExprs monadType::Type wrapReturn::Boolean loc::Location
{
  local sub::Expr = buildMonadApplicationBody(tail(monadTysLocs), funargs, monadType, wrapReturn, loc);
  local argty::Type = head(monadTysLocs).fst;
  local bind::Expr = monadBind(loc);
  local binding::ProductionRHS =
        productionRHSCons(productionRHSElem(name("a"++toString(head(monadTysLocs).snd),
                                                 loc),
                                            '::', 
                                            typerepTypeExpr(monadInnerType(argty, loc),
                                                            location=loc),
                                            location=loc),
                          productionRHSNil(location=loc),
                          location=loc);
  local bindargs::AppExprs =
        snocAppExprs(
           oneAppExprs(presentAppExpr(
                          baseExpr(qName(loc,"a"++toString(head(monadTysLocs).snd)),
                                   location=loc),
                          location=loc),
                       location=loc),
           ',',
            presentAppExpr(lambdap(binding, sub, location=loc),
                           location=loc),
            location=loc);

  local step::Expr = applicationExpr(bind, '(', bindargs, ')', location=loc);

  --the function is always going to be bound into the name "f", so we hard code that here
  local baseapp::Expr = applicationExpr(baseExpr(qName(loc, "f"), location=loc),
                                        '(', funargs, ')', location=loc);
  local funapp::Expr = if wrapReturn
                       then Silver_Expr { $Expr {monadReturn(loc)}($Expr {baseapp}) }
                       else baseapp;

  return if null(monadTysLocs)
         then funapp
         else step;
}


aspect production partialApplication
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  top.merrors := error("merrors not defined on partial applications");
  top.mUpSubst = error("mUpSubst not defined on partial applications");

  top.monadicNames = error("monadicNames not defined on partial applications");

  top.mtyperep = error("mtyperep not defined on partial applications, but sholud be in the future");
  top.monadRewritten = error("monadRewritten not defined on partial applications, but should be in the future");
}

aspect production errorApplication
top::Expr ::= e::Decorated! Expr es::Decorated! AppExprs anns::Decorated! AnnoAppExprs
{
  top.merrors := [];

  top.monadicNames = [];

  top.mUpSubst = top.mDownSubst;
  top.mtyperep = errorType();
  top.monadRewritten = top;
}

aspect production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  top.merrors := e.merrors;

  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;

  top.mtyperep = e.mtyperep;

  e.monadicallyUsed = top.monadicallyUsed;
  top.monadicNames = e.monadicNames;

  top.monadRewritten = noteAttachment('attachNote', note, 'on', e.monadRewritten, 'end', location=top.location);
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  local ne::Expr = e;
  ne.downSubst = top.mDownSubst;
  ne.mDownSubst = top.mDownSubst;
  top.mUpSubst = ne.mUpSubst;
  ne.finalSubst = top.finalSubst;
  ne.expectedMonad = top.expectedMonad;
  ne.frame = top.frame;
  ne.grammarName = top.grammarName;
  ne.compiledGrammars = top.compiledGrammars;
  ne.config = top.config;
  ne.env = top.env;
  ne.flowEnv = top.flowEnv;
  ne.originRules = top.originRules;
  ne.isRoot = false;
  ne.monadicallyUsed = false; --this needs to change when we decorated monadic trees

  --apparently there isn't a downSubst equation normally?
  local res_e::Expr = e;
  res_e.downSubst = top.downSubst;
  res_e.finalSubst = top.finalSubst;
  res_e.frame = top.frame;
  res_e.grammarName = top.grammarName;
  res_e.compiledGrammars = top.compiledGrammars;
  res_e.config = top.config;
  res_e.env = top.env;
  res_e.flowEnv = top.flowEnv;
  res_e.isRoot = false;
  res_e.originRules = top.originRules;
  top.notExplicitAttributes := res_e.notExplicitAttributes;

  top.merrors := ne.errors;
  top.mtyperep = ne.mtyperep;

  top.monadicNames = ne.monadicNames;
  top.monadRewritten = forwardAccess(ne.monadRewritten, '.', 'forward', location=top.location);
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.mDownSubst = top.mDownSubst;
  e.expectedMonad = top.expectedMonad;
  e.monadicallyUsed = false; --this needs to change when we decorate monadic trees
  top.monadicNames = if top.monadicallyUsed
                     then [access(e, '.', q, location=top.location)] ++ e.monadicNames
                     else e.monadicNames;

  propagate mDownSubst, mUpSubst;
  top.merrors := [];
  top.merrors <- case q.attrDcl of
                 | restrictedSynDcl(_, _, _) -> []
                 | restrictedInhDcl(_, _, _) -> []
                 | implicitSynDcl(_, _, _) -> []
                 | implicitInhDcl(_, _, _) -> []
                 | _ -> [err(top.location, "Attributes accessed in implicit equations must " ++
                                           "be either implicit or restricted; " ++ q.unparse ++
                                           " is neither")]
                 end;

  --Why do we rewrite here, in an error production?  We can get here from the basic access
  --   production based on normal typechecking failing even though our typechecking will
  --   succeed, and we then need to be able to go back.
  local eUnDec::Expr =
        if e.mtyperep.isDecorated
        then Silver_Expr{ silver:core:new($Expr {e.monadRewritten}) }
        else e.monadRewritten;
  local noMonad::Expr = access(e.monadRewritten, '.', q, location=top.location);
  local isEMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          $Expr {monadReturn(top.location)}
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  local isBothMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  top.monadRewritten = if isMonad(e.mtyperep, top.env) &&
                          fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                       then if isMonad(q.typerep, top.env) &&
                               fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                            then isBothMonad
                            else isEMonad
                       else noMonad;

  top.mtyperep = if isMonad(e.mtyperep, top.env) &&
                    fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                 then if isMonad(q.typerep, top.env) &&
                         fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                      then q.typerep
                      else monadOfType(top.expectedMonad, q.typerep)
                 else q.typerep;

  top.notExplicitAttributes <- e.notExplicitAttributes ++
                               if q.found
                               then case q.attrDcl of
                                    | restrictedSynDcl(_, _, _) -> []
                                    | restrictedInhDcl(_, _, _) -> []
                                    | _ -> [pair(q.unparse, top.location)]
                                    end
                               else [];
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.mDownSubst = top.mDownSubst;
  e.expectedMonad = top.expectedMonad;
  e.monadicallyUsed = false; --this needs to change when we decorate monadic trees
  top.monadicNames = if top.monadicallyUsed
                     then [access(e, '.', q, location=top.location)] ++ e.monadicNames
                     else e.monadicNames;

  local eUnDec::Expr =
        if e.mtyperep.isDecorated
        then Silver_Expr{ silver:core:new($Expr {e.monadRewritten}) }
        else e.monadRewritten;
  local noMonad::Expr = access(e.monadRewritten, '.', q, location=top.location);
  local isEMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          $Expr {monadReturn(top.location)}
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  local isBothMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  top.monadRewritten = if isMonad(e.mtyperep, top.env) &&
                          fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                       then if isMonad(q.typerep, top.env) &&
                               fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                            then isBothMonad
                            else isEMonad
                       else noMonad;

  top.mtyperep = if isMonad(e.mtyperep, top.env) &&
                    fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                 then if isMonad(q.typerep, top.env) &&
                         fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                      then q.typerep
                      else monadOfType(top.expectedMonad, q.typerep)
                 else q.typerep;

  top.mUpSubst = top.mDownSubst;
  top.merrors := [];
  top.merrors <- case q.attrDcl of
                 | restrictedSynDcl(_, _, _) -> []
                 | restrictedInhDcl(_, _, _) -> []
                 | implicitSynDcl(_, _, _) -> []
                 | implicitInhDcl(_, _, _) -> []
                 | _ -> [err(top.location, "Attributes accessed in implicit equations must " ++
                                           "be either implicit or restricted; " ++ q.unparse ++
                                           " is neither")]
                 end;

  top.notExplicitAttributes <- e.notExplicitAttributes ++
                               if q.found
                               then case q.attrDcl of
                                    | restrictedSynDcl(_, _, _) -> []
                                    | restrictedInhDcl(_, _, _) -> []
                                    | _ -> [pair(q.unparse, top.location)]
                                    end
                               else [];
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.mDownSubst = top.mDownSubst;
  e.expectedMonad = top.expectedMonad;

  top.merrors := e.merrors;
  top.mUpSubst = top.mDownSubst;

  e.monadicallyUsed = false; --this needs to change when we decorate monadic trees
  top.monadicNames = if top.monadicallyUsed
                     then [access(e, '.', q, location=top.location)] ++ e.monadicNames
                     else e.monadicNames;

  local eUnDec::Expr =
        if e.mtyperep.isDecorated
        then Silver_Expr{ silver:core:new($Expr {e.monadRewritten}) }
        else e.monadRewritten;
  local noMonad::Expr = access(e.monadRewritten, '.', q, location=top.location);
  local isEMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          $Expr {monadReturn(top.location)}
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  top.monadRewritten = if isMonad(e.mtyperep, top.env) &&
                          fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                       then isEMonad
                       else noMonad;

  top.mtyperep = if isMonad(e.mtyperep, top.env) &&
                    fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                 then monadOfType(top.expectedMonad, baseType)
                 else baseType;

  local baseType::Type =
    if q.name == "lexeme" || q.name == "filename"
    then stringType()
    else if q.name == "line" || q.name == "column"
    then intType()
    else if q.name == "location"
    then nonterminalType("silver:core:Location", [], false)
    else errorType();
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.mDownSubst = top.mDownSubst;
  e.expectedMonad = top.expectedMonad;
  e.monadicallyUsed = false; --this needs to change when we decorate monadic trees
  top.monadicNames = if top.monadicallyUsed
                     then [access(e, '.', q, location=top.location)] ++ e.monadicNames
                     else e.monadicNames;

  local eUnDec::Expr =
        if e.mtyperep.isDecorated
        then Silver_Expr{ silver:core:new($Expr {e.monadRewritten}) }
        else e.monadRewritten;
  local noMonad::Expr = access(e.monadRewritten, '.', q, location=top.location);
  local isEMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          $Expr {monadReturn(top.location)}
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  local isBothMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  top.monadRewritten = if isMonad(e.mtyperep, top.env) &&
                          fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                       then if isMonad(q.typerep, top.env) &&
                               fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                            then isBothMonad
                            else isEMonad
                       else noMonad;

  top.mtyperep = if isMonad(e.mtyperep, top.env) &&
                    fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                 then if isMonad(q.typerep, top.env) &&
                         fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                      then q.typerep
                      else monadOfType(top.expectedMonad, q.typerep)
                 else q.typerep;

  top.mUpSubst = top.mDownSubst;
  top.merrors := e.merrors;
  top.merrors <- case q.attrDcl of
                 | restrictedSynDcl(_, _, _) -> []
                 | restrictedInhDcl(_, _, _) -> []
                 | implicitSynDcl(_, _, _) -> []
                 | implicitInhDcl(_, _, _) -> []
                 | _ -> [err(top.location, "Attributes accessed in implicit equations must " ++
                                           "be either implicit or restricted; " ++ q.unparse ++
                                           " is neither")]
                 end;

  top.notExplicitAttributes <- e.notExplicitAttributes ++
                               if q.found
                               then case q.attrDcl of
                                    | restrictedSynDcl(_, _, _) -> []
                                    | restrictedInhDcl(_, _, _) -> []
                                    | _ -> [pair(q.unparse, top.location)]
                                    end
                               else [];
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.mDownSubst = top.mDownSubst;
  e.expectedMonad = top.expectedMonad;
  e.monadicallyUsed = false; --this needs to change when we decorate monadic trees
  top.monadicNames = if top.monadicallyUsed
                     then [access(e, '.', q, location=top.location)] ++ e.monadicNames
                     else e.monadicNames;

  local eUnDec::Expr =
        if e.mtyperep.isDecorated
        then Silver_Expr{ silver:core:new($Expr {e.monadRewritten}) }
        else e.monadRewritten;
  local noMonad::Expr = access(e.monadRewritten, '.', q, location=top.location);
  local isEMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          $Expr {monadReturn(top.location)}
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  local isBothMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  top.monadRewritten = if isMonad(e.mtyperep, top.env) &&
                          fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                       then if isMonad(q.typerep, top.env) &&
                               fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                            then isBothMonad
                            else isEMonad
                       else noMonad;

  top.mtyperep = if isMonad(e.mtyperep, top.env) &&
                    fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                 then if isMonad(q.typerep, top.env) &&
                         fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                      then q.typerep
                      else monadOfType(top.expectedMonad, q.typerep)
                 else q.typerep;

  top.mUpSubst = top.mDownSubst;
  top.merrors := e.merrors;
  top.merrors <- case q.attrDcl of
                 | restrictedSynDcl(_, _, _) -> []
                 | restrictedInhDcl(_, _, _) -> []
                 | implicitSynDcl(_, _, _) -> []
                 | implicitInhDcl(_, _, _) -> []
                 | _ -> [err(top.location, "Attributes accessed in implicit equations must " ++
                                           "be either implicit or restricted; " ++ q.unparse ++
                                           " is neither")]
                 end;

  top.notExplicitAttributes <- e.notExplicitAttributes ++
                               if q.found
                               then case q.attrDcl of
                                    | restrictedSynDcl(_, _, _) -> []
                                    | restrictedInhDcl(_, _, _) -> []
                                    | _ -> [pair(q.unparse, top.location)]
                                    end
                               else [];
}

aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated! Expr  q::Decorated! QNameAttrOccur
{
  e.mDownSubst = top.mDownSubst;
  e.expectedMonad = top.expectedMonad;

  top.monadicNames = [];

   --Why do we rewrite here, in an error production?  We can get here from the basic access
  --   production based on normal typechecking failing even though our typechecking will
  --   succeed, and we then need to be able to go back.
  local eUnDec::Expr =
        if e.mtyperep.isDecorated
        then Silver_Expr{ silver:core:new($Expr {e.monadRewritten}) }
        else e.monadRewritten;
  local noMonad::Expr = access(e.monadRewritten, '.', q, location=top.location);
  local isEMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          $Expr {monadReturn(top.location)}
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  local isBothMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {eUnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
          (x.$QName {qName(q.location, q.name)})
       )
      )
    };
  top.monadRewritten = if isMonad(e.mtyperep, top.env) &&
                          fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                       then if isMonad(q.typerep, top.env) &&
                               fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                            then isBothMonad
                            else isEMonad
                       else noMonad;

  top.mtyperep = if isMonad(e.mtyperep, top.env) &&
                    fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                 then if isMonad(q.typerep, top.env) &&
                         fst(monadsMatch(q.typerep, top.expectedMonad, top.mUpSubst))
                      then q.typerep
                      else monadOfType(top.expectedMonad, q.typerep)
                 else q.typerep;

  top.merrors := e.merrors;
  top.merrors <- case q.attrDcl of
                 | restrictedSynDcl(_, _, _) -> []
                 | restrictedInhDcl(_, _, _) -> []
                 | implicitSynDcl(_, _, _) -> []
                 | implicitInhDcl(_, _, _) -> []
                 | _ -> [err(top.location, "Attributes accessed in implicit equations must " ++
                                           "be either implicit or restricted; " ++ q.unparse ++
                                           " is neither")]
                 end;
  top.mUpSubst = top.mDownSubst;

  top.notExplicitAttributes <- e.notExplicitAttributes ++
                               if q.found
                               then case q.attrDcl of
                                    | restrictedSynDcl(_, _, _) -> []
                                    | restrictedInhDcl(_, _, _) -> []
                                    | _ -> [pair(q.unparse, top.location)]
                                    end
                               else [];
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  {-
    We assume no one is both using monadic stuff and explicitly decorating
    monads, so anything that is a monad gets bound in to have its insides
    decorated.
  -}
  propagate mDownSubst, mUpSubst;
  top.merrors := e.merrors;

  e.monadicallyUsed = if isMonad(e.mtyperep, top.env) && monadsMatch(e.mtyperep, top.expectedMonad, top.mDownSubst).fst
                      then true
                      else false;
  top.monadicNames = e.monadicNames ++ inh.monadicNames;

  top.mtyperep = if isMonad(e.mtyperep, top.env) && monadsMatch(e.mtyperep, top.expectedMonad, top.mDownSubst).fst
                 then monadOfType(
                   e.mtyperep,
                   decoratedType(
                     performSubstitution(monadInnerType(e.mtyperep, top.location), e.mUpSubst),
                     inhSetType(sort(nub(inh.suppliedInhs)))))
                 else decoratedType(performSubstitution(e.mtyperep, e.mUpSubst), inhSetType(sort(nub(inh.suppliedInhs))));

  local newname::String = "__sv_bind_" ++ toString(genInt());
  local params::ProductionRHS =
     productionRHSCons(productionRHSElem(name(newname, top.location),
                                         '::',
                                         typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location),
                                         location=top.location),
                       productionRHSNil(location=top.location),
                       location=top.location);
  local eUnDec::Expr =
        if e.mtyperep.isDecorated
        then Silver_Expr {new($Expr {e.monadRewritten})}
        else e.monadRewritten;
  top.monadRewritten =
     if isMonad(e.mtyperep, top.env) && monadsMatch(e.mtyperep, top.expectedMonad, top.mDownSubst).fst
     then Silver_Expr {
            $Expr{monadBind(top.location)}
              ($Expr{eUnDec},
               $Expr{lambdap(params,
                      Silver_Expr{
                        $Expr{monadReturn(top.location)}
                        ($Expr{decorateExprWith('decorate',
                               baseExpr(qName(top.location, newname), location=top.location),
                               'with', '{', inh.monadRewritten, '}', location=top.location)})
                      }, location=top.location)})
          }
     else decorateExprWith('decorate', e.monadRewritten, 'with',
                           '{', inh.monadRewritten, '}', location=top.location);
}

attribute monadRewritten<ExprInhs>, merrors, mDownSubst, mUpSubst, monadicNames, expectedMonad occurs on ExprInhs;
attribute monadRewritten<ExprInh>, merrors, mDownSubst, mUpSubst, monadicNames, expectedMonad occurs on ExprInh;

propagate mDownSubst, mUpSubst, expectedMonad on ExprInhs, ExprInh;

aspect production exprInhsEmpty
top::ExprInhs ::= 
{
  top.merrors := [];

  top.monadicNames = [];

  top.monadRewritten = exprInhsEmpty(location=top.location);
}

aspect production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.merrors := lhs.merrors;

  top.monadicNames = lhs.monadicNames;

  top.monadRewritten = exprInhsOne(lhs.monadRewritten, location=top.location);
}

aspect production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.merrors := lhs.merrors ++ inh.merrors;

  top.monadicNames = lhs.monadicNames ++ inh.monadicNames;

  top.monadRewritten = exprInhsCons(lhs.monadRewritten, inh.monadRewritten, location=top.location);
}

aspect production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.merrors := e.merrors;

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.monadRewritten = exprInh(lhs, '=', e.monadRewritten, ';', location=top.location);
}


aspect production decorationSiteExpr
top::Expr ::= '@' e::Expr
{
  top.mtyperep = e.mtyperep.decoratedType;
  top.merrors := e.merrors;
  top.monadicNames = e.monadicNames;
  top.monadRewritten = decorationSiteExpr('@', e.monadRewritten, location=top.location);
  e.monadicallyUsed = false;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  e.mDownSubst = top.mDownSubst;
  errCheck1.downSubst = e.mUpSubst;
  top.mUpSubst = errCheck1.upSubst;
  errCheck1 = check(e.typerep, uniqueDecoratedType(freshType(), inhSetType([])));
  top.merrors <-
       if errCheck1.typeerror
       then [err(top.location, "Operand to @ must be a unique reference with no inherited attributes.  Instead it is of type " ++ errCheck1.leftpp)]
       else [];
}

aspect production trueConst
top::Expr ::= 'true'
{
  propagate mDownSubst, mUpSubst;
  top.mtyperep = boolType();
  top.merrors := [];
  top.monadicNames = [];
  top.monadRewritten = trueConst('true', location=top.location);
}

aspect production falseConst
top::Expr ::= 'false'
{
  propagate mDownSubst, mUpSubst;
  top.mtyperep = boolType();
  top.merrors := [];
  top.monadicNames = [];
  top.monadRewritten = falseConst('false', location=top.location);
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;
  top.merrors <-
      if isMonad(e1.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '&&', not " ++ monadToString(e1.mtyperep))]
      else [];
  top.merrors <-
      if isMonad(e2.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '&&', not " ++ monadToString(e2.mtyperep))]
      else [];

  local ec1::TypeCheck = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                         then check(monadInnerType(e1.mtyperep, top.location), boolType())
                         else check(e1.mtyperep, boolType());
  local ec2::TypeCheck = if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                         then check(monadInnerType(e2.mtyperep, top.location), boolType())
                         else check(e2.mtyperep, boolType());
  ec1.finalSubst = top.finalSubst;
  ec2.finalSubst = top.finalSubst;
  e1.mDownSubst = top.mDownSubst;
  e2.mDownSubst = e1.mUpSubst;
  ec1.downSubst = e2.mUpSubst;
  ec2.downSubst = ec1.upSubst;
  top.mUpSubst = ec2.upSubst;
  top.mtyperep = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                 then e1.mtyperep --assume it will be well-typed
                 else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                      then e2.mtyperep
                      else boolType();

  e1.monadicallyUsed = isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst;
  e2.monadicallyUsed = isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst;
  top.monadicNames = e1.monadicNames ++ e2.monadicNames;

  local e1UnDec::Expr =
        if e1.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e1.monadRewritten})}
        else e1.monadRewritten;
  local e2UnDec::Expr =
        if e2.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e2.monadRewritten})}
        else e2.monadRewritten;
  --e1 >>= ( (\x y -> if x then y else Return(false))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
          if x then y else $Expr {monadReturn(top.location)}(false)) (_, $Expr {e2UnDec}))
    };
  --e1 >>= ( (\x y -> Return(x && y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x && y))(_, $Expr {e2UnDec}))
    };
  --if e1 then e2 else Return(false)
  local bind2::Expr =
    Silver_Expr {
      if $Expr {e1UnDec} then $Expr {e2UnDec} else $Expr {monadReturn(top.location)}(false)
    };
  top.monadRewritten = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                       then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bind2
                            else and(e1.monadRewritten, '&&', e2.monadRewritten, location=top.location);
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;
  top.merrors <-
      if isMonad(e1.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '||', not " ++ monadToString(e1.mtyperep))]
      else [];
  top.merrors <-
      if isMonad(e2.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '||', not " ++ monadToString(e2.mtyperep))]
      else [];

  local ec1::TypeCheck = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                         then check(monadInnerType(e1.mtyperep, top.location), boolType())
                         else check(e1.mtyperep, boolType());
  local ec2::TypeCheck = if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                         then check(monadInnerType(e2.mtyperep, top.location), boolType())
                         else check(e2.mtyperep, boolType());
  ec1.finalSubst = top.finalSubst;
  ec2.finalSubst = top.finalSubst;
  e1.mDownSubst = top.mDownSubst;
  e2.mDownSubst = e1.mUpSubst;
  ec1.downSubst = e2.mUpSubst;
  ec2.downSubst = ec1.upSubst;
  top.mUpSubst = ec2.upSubst;
  top.mtyperep = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                then e1.mtyperep --assume it will be well-typed
                else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                     then e2.mtyperep
                     else boolType();

  e1.monadicallyUsed = isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst;
  e2.monadicallyUsed = isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst;
  top.monadicNames = e1.monadicNames ++ e2.monadicNames;

  local e1UnDec::Expr =
        if e1.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e1.monadRewritten})}
        else e1.monadRewritten;
  local e2UnDec::Expr =
        if e2.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e2.monadRewritten})}
        else e2.monadRewritten;
  --e1 >>= ( (\x y -> if x then Return(true) else y)(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
          if x then $Expr {monadReturn(top.location)}(true) else y) (_, $Expr {e2UnDec}))
    };
  --e1 >>= ( (\x y -> Return(x || y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x || y))(_, $Expr {e2UnDec}))
    };
  --if e1 then Return(true) else e2
  local bind2::Expr =
    Silver_Expr {
      if $Expr {e1UnDec} then $Expr {monadReturn(top.location)}(true) else $Expr {e2UnDec}
    };
  top.monadRewritten = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                       then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bind2
                            else or(e1.monadRewritten, '||', e2.monadRewritten, location=top.location);
}

aspect production notOp
top::Expr ::= '!' e::Expr
{
  top.merrors := e.merrors;
  top.merrors <-
      if isMonad(e.mtyperep, top.env) && monadsMatch(top.expectedMonad, e.mtyperep, top.mDownSubst).fst
      then if monadsMatch(top.expectedMonad, e.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '!', not " ++ monadToString(e.mtyperep))]
      else [];

  local ec::TypeCheck = if isMonad(e.mtyperep, top.env)
                        then check(monadInnerType(e.mtyperep, top.location), boolType())
                        else check(e.mtyperep, boolType());
  e.mDownSubst = top.mDownSubst;
  ec.downSubst = e.mUpSubst;
  top.mUpSubst = ec.upSubst;
  ec.finalSubst = top.finalSubst;
  top.mtyperep = e.mtyperep; --assume it will be well-typed

  e.monadicallyUsed = isMonad(e.mtyperep, top.env) && monadsMatch(top.expectedMonad, e.mtyperep, top.mDownSubst).fst;
  top.monadicNames = e.monadicNames;

  local eUnDec::Expr =
        if e.mtyperep.isDecorated
        then Silver_Expr {silver:core:new($Expr {e.monadRewritten})}
        else e.monadRewritten;
  top.monadRewritten =
    if isMonad(e.mtyperep, top.env) && monadsMatch(top.expectedMonad, e.mtyperep, top.mDownSubst).fst
    then Silver_Expr {
           $Expr {monadBind(top.location)}
            ($Expr {eUnDec},
             \x::Boolean -> 
              $Expr {monadReturn(top.location)}(!x))
         }
    else notOp('!', e.monadRewritten, location=top.location);
}

concrete production ifThen
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'end' --this is easier than anything else to do
{
  top.unparse = "if " ++ e1.unparse  ++ " then " ++ e2.unparse ++ " end";
  propagate config, grammarName, compiledGrammars, frame, env, flowEnv, finalSubst, originRules;

  top.merrors <-
      if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
      then if monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this 'if-then', not " ++ monadToString(e1.mtyperep))]
      else [];
  top.merrors <-
      if isMonadFail(top.expectedMonad, top.env)
      then []
      else [err(top.location, monadToString(top.expectedMonad) ++
                " is not an instance of MonadFail and cannot be used with if-then")];

  local ec::TypeCheck = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                        then check(monadInnerType(e1.mtyperep, top.location), boolType())
                        else check(e1.mtyperep, boolType());
  ec.finalSubst = top.finalSubst;
  e1.mDownSubst = top.mDownSubst;
  e2.mDownSubst = e1.mUpSubst;
  ec.downSubst = e2.mUpSubst;
  top.mUpSubst = ec.upSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  top.upSubst = e2.upSubst;

  top.mtyperep = if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                 then e2.mtyperep
                 else if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                      then monadOfType(e1.mtyperep, e2.mtyperep)
                      else monadOfType(top.expectedMonad, e2.mtyperep);

  e1.monadicallyUsed = isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst;
  e2.monadicallyUsed = false;
  top.monadicNames = e1.monadicNames ++ e2.monadicNames;

  e1.expectedMonad = top.expectedMonad;
  e2.expectedMonad = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                     then e1.mtyperep
                     else top.expectedMonad;
  
  e1.isRoot = false;
  e2.isRoot = false;

  forwards to ifThenElse('if', e1, 'then', e2, 'else', monadFail(top.location), location=top.location);
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.merrors := e1.merrors ++ e2.merrors ++ e3.merrors;
  top.merrors <-
      if isMonad(e1.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this 'if-then-els', not " ++ monadToString(e1.mtyperep))]
      else [];

  local ec1::TypeCheck = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                         then check(monadInnerType(e1.mtyperep, top.location), boolType())
                         else check(e1.mtyperep, boolType());
  local ec2::TypeCheck = if isMonad(e3.mtyperep, top.env) && monadsMatch(top.expectedMonad, e3.mtyperep, top.mDownSubst).fst
                        then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e3.mtyperep, e2.mtyperep)
                             else check(monadInnerType(e3.mtyperep, top.location), e2.mtyperep)
                        else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e3.mtyperep, monadInnerType(e2.mtyperep, top.location))
                             else check(e3.mtyperep, e2.mtyperep);
  ec1.finalSubst = top.finalSubst;
  ec2.finalSubst = top.finalSubst;
  e1.mDownSubst = top.mDownSubst;
  e2.mDownSubst = e1.mUpSubst;
  e3.mDownSubst = e2.mUpSubst;
  ec1.downSubst = e3.mUpSubst;
  ec2.downSubst = ec1.upSubst;
  top.mUpSubst = ec2.upSubst;

  top.mtyperep = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                     then e2.mtyperep
                     else if isMonad(e3.mtyperep, top.env) && monadsMatch(top.expectedMonad, e3.mtyperep, top.mDownSubst).fst
                          then e3.mtyperep
                          else monadOfType(top.expectedMonad, e3.mtyperep)
                else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                     then e2.mtyperep
                     else e3.mtyperep;

  e1.monadicallyUsed = isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst;
  e2.monadicallyUsed = false;
  e3.monadicallyUsed = false;
  top.monadicNames = e1.monadicNames ++ e2.monadicNames ++ e3.monadicNames;

  --To deal with the case where one type or the other might be "generic" (e.g. Maybe<a>),
  --   we want to do substitution on the types before putting them into the monadRewritten
  local e2Type::Type = performSubstitution(e2.mtyperep, top.finalSubst);
  local e3Type::Type = performSubstitution(e3.mtyperep, top.finalSubst);
  --
  local e1UnDec::Expr =
        if e1.mtyperep.isDecorated
        then Silver_Expr {silver:core:new($Expr {e1.monadRewritten})}
        else e1.monadRewritten;
  --We assume that if e2 or e3 are monads, they are the same as e1 if that is a
  --   monad and we don't allow monads to become nested.
  local cMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\c::Boolean
         x::$TypeExpr {typerepTypeExpr(dropDecorated(e2Type), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e3Type), location=top.location)} ->
         --x::$TypeExpr {typerepTypeExpr(e2Type, location=top.location)}
         --y::$TypeExpr {typerepTypeExpr(e3Type, location=top.location)} ->
         if c
         then $Expr { if isMonad(e2.mtyperep, top.env)
                      then Silver_Expr {x}
                      else Silver_Expr {$Expr {monadReturn(top.location)}(x)} }
         else $Expr { if isMonad(e3.mtyperep, top.env)
                      then Silver_Expr {y}
                      else Silver_Expr {$Expr {monadReturn(top.location)}(y)} })
       (_, $Expr {e2.monadRewritten}, $Expr {e3.monadRewritten}))
    };
  local cBool::Expr =
    Silver_Expr {
      if $Expr {e1.monadRewritten}
      then $Expr {if isMonad(e2.mtyperep, top.env)
                  then e2.monadRewritten
                  else if isMonad(e3.mtyperep, top.env)
                       then Silver_Expr { $Expr {monadReturn(top.location)}($Expr {e2.monadRewritten}) }
                       else e2.monadRewritten}
      else $Expr {if isMonad(e3.mtyperep, top.env)
                  then e3.monadRewritten
                  else if isMonad(e2.mtyperep, top.env)
                       then Silver_Expr { $Expr {monadReturn(top.location)}($Expr {e3.monadRewritten}) }
                       else e3.monadRewritten}
    };
  top.monadRewritten = if isMonad(e1.mtyperep, top.env)
                       then cMonad
                       else cBool;
} 

aspect production intConst
top::Expr ::= i::Int_t
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = intType();
  top.monadicNames = [];
  top.monadRewritten = intConst(i, location=top.location);
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = floatType();
  top.monadicNames = [];
  top.monadRewritten = floatConst(f, location=top.location);
} 

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;
  top.merrors <-
      if isMonad(e1.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '+', not " ++ monadToString(e1.mtyperep))]
      else [];
  top.merrors <-
      if isMonad(e2.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '+', not " ++ monadToString(e2.mtyperep))]
      else [];

  e1.mDownSubst = top.mDownSubst;
  e2.mDownSubst = e1.mUpSubst;
  ec.downSubst = e2.mUpSubst;
  top.mUpSubst = ec.upSubst;

  local ec::TypeCheck = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                        then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, e2.mtyperep)
                             else check(monadInnerType(e1.mtyperep, top.location), e2.mtyperep)
                        else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, monadInnerType(e2.mtyperep, top.location))
                             else check(e1.mtyperep, e2.mtyperep);
  ec.finalSubst = top.mUpSubst;
  top.mtyperep = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                 then e1.mtyperep
                 else e2.mtyperep;

  e1.monadicallyUsed = isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst;
  e2.monadicallyUsed = isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst;
  top.monadicNames = e1.monadicNames ++ e2.monadicNames;

  local e1UnDec::Expr =
        if e1.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e1.monadRewritten})}
        else e1.monadRewritten;
  local e2UnDec::Expr =
        if e2.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e2.monadRewritten})}
        else e2.monadRewritten;
  --e1 >>= ( (\x y -> y >>= \z -> Return(x + z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
          $Expr {monadBind(top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
            $Expr {monadReturn(top.location)}
            (x + z))) (_, $Expr {e2UnDec}))
    };
  --e1 >>= ( (\x y -> Return(x + y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x + y))(_, $Expr {e2UnDec}))
    };
  --e2 >>= ( (\x y -> Return(x + y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e2UnDec},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x + y))($Expr {e1UnDec}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                       then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bind2
                            else plus(e1.monadRewritten, '+', e2.monadRewritten, location=top.location);
}

aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;
  top.merrors <-
      if isMonad(e1.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '-', not " ++ monadToString(e1.mtyperep))]
      else [];
  top.merrors <-
      if isMonad(e2.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '-', not " ++ monadToString(e2.mtyperep))]
      else [];

  e1.mDownSubst = top.mDownSubst;
  e2.mDownSubst = e1.mUpSubst;
  ec.downSubst = e2.mUpSubst;
  top.mUpSubst = ec.upSubst;

  local ec::TypeCheck = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                        then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, e2.mtyperep)
                             else check(monadInnerType(e1.mtyperep, top.location), e2.mtyperep)
                        else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, monadInnerType(e2.mtyperep, top.location))
                             else check(e1.mtyperep, e2.mtyperep);
  ec.finalSubst = top.mUpSubst;
  top.mtyperep = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                then e1.mtyperep
                else e2.mtyperep;

  e1.monadicallyUsed = isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst;
  e2.monadicallyUsed = isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst;
  top.monadicNames = e1.monadicNames ++ e2.monadicNames;

  local e1UnDec::Expr =
        if e1.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e1.monadRewritten})}
        else e1.monadRewritten;
  local e2UnDec::Expr =
        if e2.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e2.monadRewritten})}
        else e2.monadRewritten;
  --e1 >>= ( (\x y -> y >>= \z -> Return(x - z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
          $Expr {monadBind(top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
            $Expr {monadReturn(top.location)}
            (x - z))) (_, $Expr {e2UnDec}))
    };
  --e1 >>= ( (\x y -> Return(x - y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x - y))(_, $Expr {e2UnDec}))
    };
  --e2 >>= ( (\x y -> Return(x - y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e2UnDec},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x - y))($Expr {e1UnDec}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                       then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bind2
                            else minus(e1.monadRewritten, '-', e2.monadRewritten, location=top.location);
}

aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;
  top.merrors <-
      if isMonad(e1.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '*', not " ++ monadToString(e1.mtyperep))]
      else [];
  top.merrors <-
      if isMonad(e2.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '*', not " ++ monadToString(e2.mtyperep))]
      else [];

  e1.mDownSubst = top.mDownSubst;
  e2.mDownSubst = e1.mUpSubst;
  ec.downSubst = e2.mUpSubst;
  top.mUpSubst = ec.upSubst;

  local ec::TypeCheck = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                        then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, e2.mtyperep)
                             else check(monadInnerType(e1.mtyperep, top.location), e2.mtyperep)
                        else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, monadInnerType(e2.mtyperep, top.location))
                             else check(e1.mtyperep, e2.mtyperep);
  ec.finalSubst = top.mUpSubst;
  top.mtyperep = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                then e1.mtyperep
                else e2.mtyperep;

  e1.monadicallyUsed = isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst;
  e2.monadicallyUsed = isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst;
  top.monadicNames = e1.monadicNames ++ e2.monadicNames;

  local e1UnDec::Expr =
        if e1.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e1.monadRewritten})}
        else e1.monadRewritten;
  local e2UnDec::Expr =
        if e2.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e2.monadRewritten})}
        else e2.monadRewritten;
  --e1 >>= ( (\x y -> y >>= \z -> Return(x * z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
          $Expr {monadBind(top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
            $Expr {monadReturn(top.location)}
            (x * z))) (_, $Expr {e2UnDec}))
    };
  --e1 >>= ( (\x y -> Return(x * y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x * y))(_, $Expr {e2UnDec}))
    };
  --e2 >>= ( (\x y -> Return(x * y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e2UnDec},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x * y))($Expr {e1UnDec}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                       then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bind2
                            else multiply(e1.monadRewritten, '*', e2.monadRewritten, location=top.location);
}

aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;
  top.merrors <-
      if isMonad(e1.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '/', not " ++ monadToString(e1.mtyperep))]
      else [];
  top.merrors <-
      if isMonad(e2.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '/', not " ++ monadToString(e2.mtyperep))]
      else [];

  e1.mDownSubst = top.mDownSubst;
  e2.mDownSubst = e1.mUpSubst;
  ec.downSubst = e2.mUpSubst;
  top.mUpSubst = ec.upSubst;

  local ec::TypeCheck = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                        then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, e2.mtyperep)
                             else check(monadInnerType(e1.mtyperep, top.location), e2.mtyperep)
                        else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, monadInnerType(e2.mtyperep, top.location))
                             else check(e1.mtyperep, e2.mtyperep);
  ec.finalSubst = top.mUpSubst;
  top.mtyperep = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                then e1.mtyperep
                else e2.mtyperep;

  e1.monadicallyUsed = isMonad(e1.mtyperep, top.env);
  e2.monadicallyUsed = isMonad(e2.mtyperep, top.env);
  top.monadicNames = e1.monadicNames ++ e2.monadicNames;

  local e1UnDec::Expr =
        if e1.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e1.monadRewritten})}
        else e1.monadRewritten;
  local e2UnDec::Expr =
        if e2.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e2.monadRewritten})}
        else e2.monadRewritten;
  --e1 >>= ( (\x y -> y >>= \z -> Return(x / z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
          $Expr {monadBind(top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
            $Expr {monadReturn(top.location)}
            (x / z))) (_, $Expr {e2UnDec}))
    };
  --e1 >>= ( (\x y -> Return(x / y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x / y))(_, $Expr {e2UnDec}))
    };
  --e2 >>= ( (\x y -> Return(x / y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e2UnDec},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x / y))($Expr {e1UnDec}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                       then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bind2
                            else divide(e1.monadRewritten, '/', e2.monadRewritten, location=top.location);
}

aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;
  top.merrors <-
      if isMonad(e1.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '%', not " ++ monadToString(e1.mtyperep))]
      else [];
  top.merrors <-
      if isMonad(e2.mtyperep, top.env)
      then if monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
           then []
           else [err(top.location, "Can only use " ++ monadToString(top.expectedMonad) ++
                           " implicitly in this '%', not " ++ monadToString(e2.mtyperep))]
      else [];

  e1.mDownSubst = top.mDownSubst;
  e2.mDownSubst = e1.mUpSubst;
  ec.downSubst = e2.mUpSubst;
  top.mUpSubst = ec.upSubst;

  local ec::TypeCheck = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                        then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, e2.mtyperep)
                             else check(monadInnerType(e1.mtyperep, top.location), e2.mtyperep)
                        else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                             then check(e1.mtyperep, monadInnerType(e2.mtyperep, top.location))
                             else check(e1.mtyperep, e2.mtyperep);
  ec.finalSubst = top.mUpSubst;
  top.mtyperep = if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                 then e1.mtyperep
                 else e2.mtyperep;

  e1.monadicallyUsed = isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst;
  e2.monadicallyUsed = isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst;
  top.monadicNames = e1.monadicNames ++ e2.monadicNames;

  local e1UnDec::Expr =
        if e1.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e1.monadRewritten})}
        else e1.monadRewritten;
  local e2UnDec::Expr =
        if e2.mtyperep.isDecorated
        then Silver_Expr {silver:core:new( $Expr {e2.monadRewritten})}
        else e2.monadRewritten;
  --e1 >>= ( (\x y -> y >>= \z -> Return(x % z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
          $Expr {monadBind(top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
            $Expr {monadReturn(top.location)}
            (x % z))) (_, $Expr {e2UnDec}))
    };
  --e1 >>= ( (\x y -> Return(x % y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e1UnDec},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep, top.location), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x % y))(_, $Expr {e2UnDec}))
    };
  --e2 >>= ( (\x y -> Return(x % y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(top.location)}
      ($Expr {e2UnDec},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep, top.location), location=top.location)} ->
        $Expr {monadReturn(top.location)}
        (x % y))($Expr {e1UnDec}, _))
    };
  top.monadRewritten =  if isMonad(e1.mtyperep, top.env) && monadsMatch(top.expectedMonad, e1.mtyperep, top.mDownSubst).fst
                       then if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep, top.env) && monadsMatch(top.expectedMonad, e2.mtyperep, top.mDownSubst).fst
                            then bind2
                            else modulus(e1.monadRewritten, '%', e2.monadRewritten, location=top.location);
}

aspect production neg
top::Expr ::= '-' e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = e.mtyperep;

  e.monadicallyUsed = isMonad(e.mtyperep, top.env) && monadsMatch(top.expectedMonad, e.mtyperep, top.mDownSubst).fst;
  top.monadicNames = e.monadicNames;

  propagate mDownSubst, mUpSubst;

  local eUnDec::Expr =
        if e.mtyperep.isDecorated
        then Silver_Expr {silver:core:new($Expr {e.monadRewritten})}
        else e.monadRewritten;
  top.monadRewritten =
    if isMonad(e.mtyperep, top.env)
    then Silver_Expr {
           $Expr {monadBind(top.location)}
            ($Expr {eUnDec},
             \x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep, top.location), location=top.location)} ->
              $Expr {monadReturn(top.location)}(-x))
         }
    else neg('-', e.monadRewritten, location=top.location);
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  es.mDownSubst = top.mDownSubst;
  el.mDownSubst = es.mUpSubst;
  top.merrors := es.merrors ++ el.merrors;
  top.mUpSubst = el.mUpSubst;
  top.mtyperep =
     if ( isMonad(es.mtyperep, top.env) && monadsMatch(es.mtyperep, top.expectedMonad, top.mUpSubst).fst ) ||
        ( isMonad(el.mtyperep, top.env) && monadsMatch(el.mtyperep, top.expectedMonad, top.mUpSubst).fst )
     then monadOfType(top.expectedMonad, t.typerep)
     else t.typerep;
  top.monadicNames = [];

  local bind::Expr = monadBind(top.location);
  local ret::Expr = monadReturn(top.location);
  local esty::TypeExpr =
              typerepTypeExpr(if isMonad(es.mtyperep, top.env) then es.mtyperep
                              else monadInnerType(es.mtyperep, top.location), location=top.location);
  local elty::TypeExpr =
              typerepTypeExpr(if isMonad(es.mtyperep, top.env) then es.mtyperep
                              else monadInnerType(es.mtyperep, top.location), location=top.location);
  local bindes::Expr =
    Silver_Expr {
      $Expr {bind}
      ($Expr {es.monadRewritten},
       (\x::$TypeExpr {esty}
         y::$TypeExpr {elty} ->
            $Expr {ret}
            (terminal($TypeExpr {t}, x, y))) (_, $Expr {el.monadRewritten}))
    };
  local bindel::Expr =
    Silver_Expr {
      $Expr {bind}
      ($Expr {el.monadRewritten},
       (\x::$TypeExpr {esty}
         y::$TypeExpr {elty} ->
            $Expr {ret}
            (terminal($TypeExpr {t}, x, y))) ($Expr {es.monadRewritten}, _))
    };
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {bind}
      ($Expr {es.monadRewritten},
       (\x::$TypeExpr {elty}
         y::$TypeExpr {typerepTypeExpr(es.mtyperep, location=top.location)} ->
          $Expr {bind}
          (y,
           \z::$TypeExpr {elty} ->
            $Expr {ret}
            (terminal($TypeExpr {t}, x, z))) (_, $Expr {el.monadRewritten})))
    };
  top.monadRewritten =
      if isMonad(es.mtyperep, top.env) && monadsMatch(es.mtyperep, top.expectedMonad, top.mUpSubst).fst
      then if isMonad(el.mtyperep, top.env) && monadsMatch(el.mtyperep, top.expectedMonad, top.mUpSubst).fst
           then bindBoth
           else bindes
      else if isMonad(el.mtyperep, top.env) && monadsMatch(el.mtyperep, top.expectedMonad, top.mUpSubst).fst
           then bindel
           else top;
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.mtyperep = stringType();
  top.monadicNames = [];

  top.monadRewritten = stringConst(s, location=top.location);
}


--A list of the locations where arguments are monads used implicitly
synthesized attribute monadTypesLocations::[Pair<Type Integer>] occurs on AppExpr, AppExprs;
--A list of the actual types of arguments
synthesized attribute realTypes::[Type] occurs on AppExpr, AppExprs;
--The only monad banned from being used as an actual argument
attribute expectedMonad occurs on AppExpr, AppExprs;
propagate expectedMonad on AppExpr, AppExprs;
--Whether we're in a special case where monad arguments are allowed, despite the normal prohibition
inherited attribute monadArgumentsAllowed::Boolean occurs on AppExpr, AppExprs;

attribute monadRewritten<AppExpr>, merrors, mDownSubst, mUpSubst occurs on AppExpr;
attribute monadRewritten<AppExprs>, merrors, mDownSubst, mUpSubst occurs on AppExprs;

aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.merrors := [];
  propagate mDownSubst, mUpSubst;
  top.monadRewritten = missingAppExpr('_', location=top.location);
  top.realTypes = [];
  top.monadTypesLocations = [];
  top.monadicNames = [];
}
aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.merrors := e.merrors;

  top.realTypes = [e.mtyperep];
  top.monadTypesLocations = if isMonadic
                            then [pair(e.mtyperep, top.appExprIndex+1)]
                            else [];
  e.monadicallyUsed = isMonadic;
  top.monadicNames = e.monadicNames;

  --these have an 'a' at the end of their names because of a bug where local names are not local to their grammars
  local attribute errCheck1a::TypeCheck; errCheck1a.finalSubst = top.mUpSubst;
  local attribute errCheck2a::TypeCheck; errCheck2a.finalSubst = top.mUpSubst;

  e.mDownSubst = top.mDownSubst;
  errCheck1a.downSubst = e.mUpSubst;
  errCheck2a.downSubst = e.mUpSubst;
  top.mUpSubst = if isMonadic
                 then errCheck2a.upSubst
                 else errCheck1a.upSubst;
  --determine whether it appears that this is supposed to take
  --   advantage of implicit monads based on types matching the
  --   expected and being monads
  local isMonadic::Boolean =
           isMonad(e.mtyperep, top.env) &&
           fst(monadsMatch(e.mtyperep, top.expectedMonad, e.mUpSubst)) &&
          !fst(monadsMatch(e.mtyperep, top.appExprTyperep, e.mUpSubst));

  errCheck1a = check(if top.appExprTyperep.isDecorated then e.mtyperep else dropDecorated(e.mtyperep), top.appExprTyperep);
  errCheck2a = check(monadInnerType(e.mtyperep, top.location), top.appExprTyperep);
  top.merrors <-
    if isMonadic
    then if !errCheck2a.typeerror
         then []
         else [err(top.location, "Argument " ++ toString(top.appExprIndex+1) ++ " of function '" ++
                top.appExprApplied ++ "' expected " ++ errCheck1a.rightpp ++
                " or a monad of " ++ errCheck1a.rightpp ++
                " but argument is of type " ++ errCheck1a.leftpp)]
    else
      if !errCheck1a.typeerror
      then []
      else [err(top.location, "Argument " ++ toString(top.appExprIndex+1) ++ " of function '" ++
                top.appExprApplied ++ "' expected " ++ errCheck1a.rightpp ++
                " or a monad of " ++ errCheck1a.rightpp ++
                " but argument is of type " ++ errCheck1a.leftpp)];
  --Functions are not allowed to take monad-typed arguments
  top.merrors <-
    if fst(monadsMatch(top.appExprTyperep, top.expectedMonad, top.mDownSubst)) && !top.monadArgumentsAllowed
    then [err(top.location, "Implicit equations may not use functions with " ++
                            "monad-typed arguments, specifically " ++ errCheck2a.rightpp)]
    else [];

  top.monadRewritten = presentAppExpr(e.monadRewritten, location=top.location);
}

propagate monadArgumentsAllowed, mDownSubst, mUpSubst on AppExprs;

aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  top.merrors := es.merrors ++ e.merrors;

  top.realTypes = es.realTypes ++ e.realTypes;

  top.monadTypesLocations = es.monadTypesLocations ++ e.monadTypesLocations;

  top.monadicNames = es.monadicNames ++ e.monadicNames;

  top.monadRewritten = snocAppExprs(es.monadRewritten, ',', e.monadRewritten, location=top.location);
}
aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  top.merrors := e.merrors;

  top.realTypes = e.realTypes;

  top.monadTypesLocations = e.monadTypesLocations;

  top.monadicNames = e.monadicNames;

  top.monadRewritten = oneAppExprs(e.monadRewritten, location=top.location);
}
aspect production emptyAppExprs
top::AppExprs ::=
{
  top.merrors := [];

  top.realTypes = [];

  top.monadTypesLocations = [];

  top.monadicNames = [];

  top.monadRewritten = emptyAppExprs(location=top.location);
}

--Copper Expressions
aspect production failureTerminalIdExpr
top::Expr ::= 'disambiguationFailure'
{
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = terminalIdType();
  top.monadRewritten = top;

  top.monadicNames = [];
}


aspect production lexerClassReference
top::Expr ::= q::Decorated! QName
{
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typeScheme.typerep;
  top.monadRewritten = top;

  top.monadicNames = [];
}

