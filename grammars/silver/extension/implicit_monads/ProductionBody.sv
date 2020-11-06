grammar silver:extension:implicit_monads;


terminal Implicit_kwd    'implicit'     lexer classes {KEYWORD,RESERVED};
terminal Restricted_kwd    'restricted'     lexer classes {KEYWORD,RESERVED};
terminal Unrestricted_kwd    'unrestricted'     lexer classes {KEYWORD,RESERVED};



--Write an empty equation filled in by an appropriate fail
concrete production emptyAttributeDef
top::ProductionStmt ::= 'implicit' dl::DefLHS '.' attr::QNameAttrOccur '=' ';'
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";

  top.productionAttributes := [];
  top.defs := [];

  local fail::Either<String Expr> = monadFail(attr.typerep, top.location);

  local merrors::[Message] =
    (if isMonad(attr.typerep)
     then case fail of
          | right(_) -> []
          | left(e) -> [err(top.location, e ++ "; this monad cannot be used in an empty equation")]
          end
     else []) ++
     ( if attr.found && dl.found
       then case attr.typerep of
            | implicitType(t) -> []
            | _ -> [err(top.location, "Implicit equations can only be used for " ++
                                      "attributes declared to be implicit; " ++
                                      attr.unparse ++ " is not implicit")]
            end
       else [] );

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to if null(merrors)
              then attributeDef(dl, '.', attr, '=',
                                case fail of | right(e) -> e end,
                                ';', location=top.location)
              else errorProductionStmt(merrors, location=top.location);
}



concrete production implicitAttributeDef
top::ProductionStmt ::= 'implicit' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.unparse = "\timplicit" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";

  local merrors::[Message] =
       if attr.found && dl.found
       then case attr.typerep of
            | implicitType(t) -> []
            | _ -> [err(top.location, "Implicit equations can only be used for " ++
                                      "attributes declared to be implicit; " ++
                                      attr.unparse ++ " is not implicit")]
            end
       else [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  local fwd::ProductionStmt = if null(merrors)
                              then attributeDef(dl, '.', attr, '=', e, ';', location=top.location)
                              else errorAttributeDef(merrors, dl, attr, e, location=top.location);
  forwards to fwd;
}




concrete production restrictedAttributeDef
top::ProductionStmt ::= 'restricted' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  e.downSubst = top.downSubst;
  top.unparse = "\trestricted" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";

  local merrors::[Message] =
    if attr.found && dl.found
    then case attr.typerep of
         | explicitType(t) -> []
         | _ -> [err(top.location, "Restricted equations can only be used for " ++
                                   "attributes declared to be restricted; " ++
                                   attr.unparse ++ "(" ++ attr.typerep.typepp ++ ")" ++ " is not restricted")]
         end
    else [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  local fwd::ProductionStmt =
           if null(merrors)
                --forward to this, which will delegate to the restricted syn or inh production
           then attributeDef(dl, '.', attr, '=', e, ';', location=top.location)
           else errorAttributeDef(merrors, dl, attr, e, location=top.location);

  forwards to fwd;
}




concrete production unrestrictedAttributeDef
top::ProductionStmt ::= 'unrestricted' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  e.downSubst = top.downSubst;
  e.mDownSubst = top.downSubst;
  e.finalSubst = e.mUpSubst;
  top.unparse = "\tunrestricted" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";

  top.productionAttributes := [];
  top.defs := [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to case attr.typerep of
              | explicitType(t) -> errorProductionStmt([err(top.location,
                                              "Unrestricted equations can only be used for attributes " ++
                                              "not declared to be restricted or implicit; " ++ attr.unparse ++ " is restricted (" ++
                                              attr.typerep.typepp ++ ")")],
                                              location=top.location)
              | implicitType(t) -> errorProductionStmt([err(top.location,
                                              "Unrestricted equations can only be used for attributes " ++
                                              "not declared to be restricted or implicit; " ++ attr.unparse ++ " is implicit (" ++
                                              attr.typerep.typepp ++ ")")],
                                              location=top.location)
              | _ -> attributeDef(dl, '.', attr, '=', e, ';', location=top.location)
              end;
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
  e.downSubst = top.downSubst;

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
  e.downSubst = top.downSubst;

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
  e.downSubst = top.downSubst;
  e.mDownSubst = top.downSubst;
  e.finalSubst = e.mUpSubst;

  e.expectedMonad = attr.typerep;

  local fwd::ProductionStmt =
          if null(e.merrors)
          then if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
               then synthesizedAttributeDef(dl, attr, e.monadRewritten, location=top.location)
               else synthesizedAttributeDef(dl, attr, Silver_Expr {
                                                        $Expr {monadReturn(attr.typerep, top.location)}
                                                            ($Expr {e.monadRewritten})
                                                      }, location=top.location)
          else errorAttributeDef(e.merrors, dl, attr, e.monadRewritten, location=top.location);
  forwards to fwd;
}


abstract production implicitInhAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS attr::Decorated QNameAttrOccur e::Expr
{
  e.downSubst = top.downSubst;
  e.mDownSubst = top.downSubst;
  e.finalSubst = e.mUpSubst;

  e.expectedMonad = attr.typerep;

  local fwd::ProductionStmt =
          if null(e.merrors)
          then if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
               then synthesizedAttributeDef(dl, attr, e.monadRewritten, location=top.location)
               else synthesizedAttributeDef(dl, attr, Silver_Expr {
                                                        $Expr {monadReturn(attr.typerep, top.location)}
                                                            ($Expr {e.monadRewritten})
                                                      }, location=top.location)
          else errorAttributeDef(e.merrors, dl, attr, e.monadRewritten, location=top.location);
  forwards to fwd;
}

