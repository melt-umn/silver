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

  top.productionAttributes := [];
  top.defs := [];
  top.uniqueSignificantExpression := [];

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

  forwards to
     if null(merrors)
     then attr.attrDcl.attrDefDispatcher(dl, attr, monadFail(top.location), top.location)
     else errorProductionStmt(merrors, location=top.location);
}



concrete production implicitAttributeDef
top::ProductionStmt ::= 'implicit' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.unparse = "\timplicit" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";

  top.productionAttributes := [];
  top.defs := [];
  top.uniqueSignificantExpression := [];

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

  local fwd::ProductionStmt =
           if null(merrors)
           then if attr.found
                then attr.attrDcl.attrDefDispatcher(dl, attr, e, top.location)
                     --if not found, let the normal dispatcher handle it
                else attributeDef(dl, '.', attr, '=', e, ';', location=top.location)
           else errorAttributeDef(merrors, dl, attr, e, location=top.location);
  forwards to fwd;
}




concrete production restrictedAttributeDef
top::ProductionStmt ::= 'restricted' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  e.downSubst = top.downSubst;
  top.unparse = "\trestricted" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";

  top.productionAttributes := [];
  top.defs := [];
  top.uniqueSignificantExpression := [];

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

  local fwd::ProductionStmt =
           if null(merrors)
           then if attr.found
                then attr.attrDcl.attrDefDispatcher(dl, attr, e, top.location)
                     --if not found, let the normal dispatcher handle it
                else attributeDef(dl, '.', attr, '=', e, ';', location=top.location)
           else errorAttributeDef(merrors, dl, attr, e, location=top.location);

  forwards to fwd;
}




concrete production unrestrictedAttributeDef
top::ProductionStmt ::= 'unrestricted' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.unparse = "\tunrestricted" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";

  top.productionAttributes := [];
  top.defs := [];
  top.uniqueSignificantExpression := [];

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
  local fwd::ProductionStmt =
            if attr.found
            then case attr.attrDcl of
                 | restrictedSynDcl(_, _, _) -> errorAttributeDef(restrictedErr, dl, attr, e, location=top.location)
                 | restrictedInhDcl(_, _, _) -> errorAttributeDef(restrictedErr, dl, attr, e, location=top.location)
                 | implicitSynDcl(_, _, _) -> errorAttributeDef(implicitErr, dl, attr, e, location=top.location)
                 | implicitInhDcl(_, _, _) -> errorAttributeDef(implicitErr, dl, attr, e, location=top.location)
                 | _ -> attributeDef(dl, '.', attr, '=', e, ';', location=top.location)
                 end
                 --if not found, let the normal dispatcher handle it
            else attributeDef(dl, '.', attr, '=', e, ';', location=top.location);
  forwards to fwd;
}






--take a list of unallowed attributes and generate error messages for them
function buildExplicitAttrErrors
[Message] ::= l::[Pair<String Location>]
{
  return case l of
         | [] -> []
         | pair(name, loca)::t ->
           err(loca, "Attributes accessed in restricted equations must be restricted; " ++
                     name ++ " is not")::buildExplicitAttrErrors(t)
         end;
}



--productions for error checking on restricted attributes
abstract production restrictedSynAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr
{
  top.unparse = dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";

  e.downSubst = top.downSubst;
  e.originRules = top.originRules;
  e.isRoot = true;

  top.containsPluck = false;
  top.uniqueSignificantExpression := [];

  local merrors::[Message] =
     --gives errors for implicit/unrestricted attributes used
     buildExplicitAttrErrors(e.notExplicitAttributes);

  local fwd::ProductionStmt = if null(merrors)
                              then synthesizedAttributeDef(dl, attr, e, location=top.location)
                              else errorAttributeDef(merrors, dl, attr, e, location=top.location);
  forwards to fwd;
}


abstract production restrictedInhAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr
{
  top.unparse = dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";

  e.downSubst = top.downSubst;
  e.originRules = top.originRules;
  e.isRoot = true;

  top.containsPluck = false;
  top.uniqueSignificantExpression := [];

  local merrors::[Message] =
     --gives errors for implicit/unrestricted attributes used
     buildExplicitAttrErrors(e.notExplicitAttributes);

  local fwd::ProductionStmt = if null(merrors)
                              then inheritedAttributeDef(dl, attr, e, location=top.location)
                              else errorAttributeDef(merrors, dl, attr, e, location=top.location);
  forwards to fwd;
}




--productions for error checking on implicit attributes
abstract production implicitSynAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr
{
  top.unparse = dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";

  e.downSubst = top.downSubst;
  e.mDownSubst = top.downSubst;
  e.finalSubst = e.mUpSubst;
  e.env = top.env;
  e.originRules = top.originRules;
  e.isRoot = true;

  e.expectedMonad = attr.typerep;

  top.containsPluck = false;
  top.uniqueSignificantExpression := [];

  local fwd::ProductionStmt =
          if null(e.merrors)
          then if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
               then synthesizedAttributeDef(dl, attr, e.monadRewritten, location=top.location)
               else synthesizedAttributeDef(dl, attr, Silver_Expr {
                                                        $Expr {monadReturn(top.location)}
                                                            ($Expr {e.monadRewritten})
                                                      }, location=top.location)
          else errorAttributeDef(e.merrors, dl, attr, e.monadRewritten, location=top.location);
  forwards to fwd;
}


abstract production implicitInhAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr
{
  top.unparse = dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";

  e.downSubst = top.downSubst;
  e.mDownSubst = top.downSubst;
  e.finalSubst = e.mUpSubst;
  e.env = top.env;
  e.originRules = top.originRules;
  e.isRoot = true;

  e.expectedMonad = attr.typerep;

  top.containsPluck = false;
  top.uniqueSignificantExpression := [];

  local fwd::ProductionStmt =
          if null(e.merrors)
          then if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
               then synthesizedAttributeDef(dl, attr, e.monadRewritten, location=top.location)
               else synthesizedAttributeDef(dl, attr, Silver_Expr {
                                                        $Expr {monadReturn(top.location)}
                                                            ($Expr {e.monadRewritten})
                                                      }, location=top.location)
          else errorAttributeDef(e.merrors, dl, attr, e.monadRewritten, location=top.location);
  forwards to fwd;
}

