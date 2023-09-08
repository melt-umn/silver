grammar silver:compiler:extension:implicit_monads;


terminal Implicit_kwd    'implicit'     lexer classes {KEYWORD,RESERVED};
terminal Restricted_kwd    'restricted'     lexer classes {KEYWORD,RESERVED};
terminal Unrestricted_kwd    'unrestricted'     lexer classes {KEYWORD,RESERVED};



--Write an empty equation filled in by an appropriate fail
--We want to keep the 'implicit' keyword here so people don't accidentally write empty equations
concrete production emptyAttributeDef
top::ProductionStmt ::= 'implicit' dl::DefLHS '.' attr::QNameAttrOccur '=' ';'
{
  top.unparse = "\timplicit " ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";
  propagate grammarName, compiledGrammars, config, frame, env, flowEnv, finalSubst, originRules;

  top.productionAttributes := [];
  top.defs := [];
  top.forwardExpr := [];
  top.returnExpr := [];
  top.undecorateExpr := [];

  top.containsPluck = false;

  local merrors::[Message] =
    (if isMonadFail(attr.typerep, top.env)
     then []
     else [err(top.location, monadToString(attr.typerep) ++
               " is not an instance of MonadFail and cannot " ++
               "be used in an empty equation")]) ++
     ( if attr.found && dl.found
       then case attr.attrDcl of
            | implicitInhDcl(_, _, _) -> []
            | implicitSynDcl(_, _, _) -> []
            | _ -> [err(top.location, "Implicit equations can only be used for " ++
                                      "attributes declared to be implicit; " ++
                                      attr.unparse ++ " is not implicit")]
            end
       else dl.errors ++ attr.errors );

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  production fwrdE::Expr = monadFail(top.location);

  forwards to
     if null(merrors)
     then attr.attrDcl.attrDefDispatcher(dl, attr, fwrdE, top.location)
     else errorProductionStmt(merrors, location=top.location);
}


global partialDefaultAttributeDef::(ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Decorated! Expr with {}  Location) =
  \ dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Decorated! Expr with {} loc::Location ->
    attributeDef(newUnique(dl), '.', newUnique(attr), '=', @e, ';', location=loc);

concrete production implicitAttributeDef
top::ProductionStmt ::= 'implicit' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.unparse = "\timplicit" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";
  dl.grammarName = top.grammarName;
  dl.config = top.config;
  dl.frame = top.frame;
  dl.env = top.env;
  dl.flowEnv = top.flowEnv;
  dl.originRules = top.originRules;
  dl.compiledGrammars = top.compiledGrammars;
  attr.grammarName = top.grammarName;
  attr.config = top.config;
  attr.env = top.env;

  top.productionAttributes := [];
  top.defs := [];
  top.forwardExpr := [];
  top.returnExpr := [];
  top.undecorateExpr := [];

  top.containsPluck = false;

  local merrors::[Message] =
       if attr.found && dl.found
       then case attr.attrDcl of
            | implicitSynDcl(_, _, _) -> []
            | implicitInhDcl(_, _, _) -> []
            | _ -> [err(top.location, "Implicit equations can only be used for " ++
                                      "attributes declared to be implicit; " ++
                                      attr.unparse ++ " is not implicit")]
            end
       else [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to
    if null(merrors)
    then if attr.found
         then attr.attrDcl.attrDefDispatcher(dl, attr, e, top.location)
           --if not found, let the normal dispatcher handle it
         else partialDefaultAttributeDef(dl, attr, e, top.location)
     else errorAttributeDef(merrors, dl, attr, e, location=top.location);
}




concrete production restrictedAttributeDef
top::ProductionStmt ::= 'restricted' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.unparse = "\trestricted" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";
  dl.grammarName = top.grammarName;
  dl.config = top.config;
  dl.frame = top.frame;
  dl.env = top.env;
  dl.flowEnv = top.flowEnv;
  dl.originRules = top.originRules;
  dl.compiledGrammars = top.compiledGrammars;
  attr.grammarName = top.grammarName;
  attr.config = top.config;
  attr.env = top.env;

  top.productionAttributes := [];
  top.defs := [];
  top.forwardExpr := [];
  top.returnExpr := [];
  top.undecorateExpr := [];

  top.containsPluck = false;

  local merrors::[Message] =
    if attr.found && dl.found
    then case attr.attrDcl of
         | restrictedSynDcl(_, _, _) -> []
         | restrictedInhDcl(_, _, _) -> []
         | _ -> [err(top.location, "Restricted equations can only be used for " ++
                                   "attributes declared to be restricted; " ++
                                   attr.unparse ++ " is not restricted")]
         end
    else [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to
    if null(merrors)
    then if attr.found
         then attr.attrDcl.attrDefDispatcher(dl, attr, e, top.location)
           --if not found, let the normal dispatcher handle it
         else partialDefaultAttributeDef(dl, attr, e, top.location)
     else errorAttributeDef(merrors, dl, attr, e, location=top.location);
}




concrete production unrestrictedAttributeDef
top::ProductionStmt ::= 'unrestricted' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.unparse = "\tunrestricted" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";
  dl.grammarName = top.grammarName;
  dl.config = top.config;
  dl.frame = top.frame;
  dl.env = top.env;
  dl.flowEnv = top.flowEnv;
  dl.originRules = top.originRules;
  dl.compiledGrammars = top.compiledGrammars;
  attr.grammarName = top.grammarName;
  attr.config = top.config;
  attr.env = top.env;

  top.productionAttributes := [];
  top.defs := [];
  top.forwardExpr := [];
  top.returnExpr := [];
  top.undecorateExpr := [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  top.containsPluck = false;

  local restrictedErr::[Message] =
           [err(top.location,
                "Unrestricted equations can only be used for attributes " ++
                "not declared to be restricted or implicit; " ++ attr.unparse ++ " is restricted")];
  local implicitErr::[Message] =
           [err(top.location,
                "Unrestricted equations can only be used for attributes " ++
                "not declared to be restricted or implicit; " ++ attr.unparse ++ " is implicit")];
  forwards to
    if attr.found
    then case attr.attrDcl of
         | restrictedSynDcl(_, _, _) -> errorAttributeDef(restrictedErr, dl, attr, e, location=top.location)
         | restrictedInhDcl(_, _, _) -> errorAttributeDef(restrictedErr, dl, attr, e, location=top.location)
         | implicitSynDcl(_, _, _) -> errorAttributeDef(implicitErr, dl, attr, e, location=top.location)
         | implicitInhDcl(_, _, _) -> errorAttributeDef(implicitErr, dl, attr, e, location=top.location)
         | _ -> partialDefaultAttributeDef(dl, attr, e, top.location)
         end
         --if not found, let the normal dispatcher handle it
    else partialDefaultAttributeDef(dl, attr, e, top.location);
}






--take a list of unallowed attributes and generate error messages for them
function buildExplicitAttrErrors
[Message] ::= l::[Pair<String Location>]
{
  return case l of
         | [] -> []
         | (name, loca)::t ->
           err(loca, "Attributes accessed in restricted equations must be restricted; " ++
                     name ++ " is not")::buildExplicitAttrErrors(t)
         end;
}



--productions for error checking on restricted attributes
abstract production restrictedSynAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Decorated! Expr with {}
{
  undecorates to attributeDef(dl, '.', attr, '=', e, ';', location=top.location);
  top.unparse = dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";
  propagate grammarName, compiledGrammars, config, frame, env, flowEnv, finalSubst, originRules;

  e.downSubst = top.downSubst;
  e.alwaysDecorated = false;
  e.isRoot = true;

  top.containsPluck = false;
  top.forwardExpr := [];
  top.returnExpr := [];
  top.undecorateExpr := [];

  local merrors::[Message] =
     --gives errors for implicit/unrestricted attributes used
     buildExplicitAttrErrors(e.notExplicitAttributes);

  forwards to
    if null(merrors)
    then synthesizedAttributeDef(dl, attr, e, location=top.location)
    else errorAttributeDef(merrors, dl, attr, e, location=top.location);
}


abstract production restrictedInhAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Decorated! Expr with {}
{
  undecorates to attributeDef(dl, '.', attr, '=', e, ';', location=top.location);
  top.unparse = dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";
  propagate grammarName, compiledGrammars, config, frame, env, flowEnv, finalSubst, originRules;

  e.downSubst = top.downSubst;
  e.alwaysDecorated = false;
  e.isRoot = true;

  top.containsPluck = false;
  top.forwardExpr := [];
  top.returnExpr := [];
  top.undecorateExpr := [];

  local merrors::[Message] =
     --gives errors for implicit/unrestricted attributes used
     buildExplicitAttrErrors(e.notExplicitAttributes);

  forwards to
    if null(merrors)
    then inheritedAttributeDef(dl, attr, e, location=top.location)
    else errorAttributeDef(merrors, dl, attr, e, location=top.location);
}




--productions for error checking on implicit attributes
abstract production implicitSynAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Decorated! Expr with {}
{
  undecorates to attributeDef(dl, '.', attr, '=', e, ';', location=top.location);
  top.unparse = dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";
  propagate grammarName, compiledGrammars, config, frame, env, flowEnv, originRules;

  e.downSubst = top.downSubst;
  e.mDownSubst = top.downSubst;
  e.finalSubst = e.mUpSubst;
  e.alwaysDecorated = false;
  e.isRoot = true;

  e.expectedMonad = attr.typerep;

  top.containsPluck = false;
  top.forwardExpr := [];
  top.returnExpr := [];
  top.undecorateExpr := [];

  production fwrdE::Expr =
    if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
    then e.monadRewritten
    else Silver_Expr {
      $Expr {monadReturn(top.location)}($Expr {e.monadRewritten})
    };

  forwards to
    if null(e.merrors)
    then synthesizedAttributeDef(dl, attr, fwrdE, location=top.location)
    else errorAttributeDef(e.merrors, dl, attr, e.monadRewritten, location=top.location);
}


abstract production implicitInhAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Decorated! Expr with {}
{
  undecorates to attributeDef(dl, '.', attr, '=', e, ';', location=top.location);
  top.unparse = dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";
  propagate grammarName, compiledGrammars, config, frame, env, flowEnv, originRules;

  e.downSubst = top.downSubst;
  e.mDownSubst = top.downSubst;
  e.finalSubst = e.mUpSubst;
  e.alwaysDecorated = false;
  e.isRoot = true;

  e.expectedMonad = attr.typerep;

  top.containsPluck = false;
  top.forwardExpr := [];
  top.returnExpr := [];
  top.undecorateExpr := [];

  production fwrdE::Expr =
    if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
    then e.monadRewritten
    else Silver_Expr {
      $Expr {monadReturn(top.location)}($Expr {e.monadRewritten})
    };

  forwards to
    if null(e.merrors)
    then inheritedAttributeDef(dl, attr, fwrdE, location=top.location)
    else errorAttributeDef(e.merrors, dl, attr, e.monadRewritten, location=top.location);
}

