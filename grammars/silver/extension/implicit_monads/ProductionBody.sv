grammar silver:extension:implicit_monads;

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
    (if !isMonad(attr.typerep)
     then [err(top.location, "Empty equations can only be used for " ++
                             "monad-typed attributes, not attributes of type " ++
                             prettyType(attr.typerep))]
     else []);

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to if null(merrors)
              then attributeDef(dl, '.', attr, '=',
                                case fail of | right(e) -> e end,
                                ';', location=top.location)
              else errorProductionStmt(merrors, location=top.location);
}


--Write an equation that allows implicit use of monads
terminal Implicit_kwd    'implicit'     lexer classes {KEYWORD,RESERVED};

concrete production implicitAttributeDef
top::ProductionStmt ::= 'implicit' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  e.downSubst = top.downSubst;
  e.mDownSubst = top.downSubst;
  e.finalSubst = e.mUpSubst;
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";

  e.expectedMonad = attr.typerep;

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
    (if !isMonad(attr.typerep)
     then [err(top.location, "Implicit equations can only be used for " ++
                             "monad-typed attributes, not attributes of type " ++
                             prettyType(attr.typerep))]
     else []) ++
    e.merrors;

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  local fwd::ProductionStmt =
              case attr.typerep of
              | implicitType(t) -> if null(merrors)
                                   then if isMonad(attr.typerep)
                                        then if isMonad(e.mtyperep) && fst(monadsMatch(attr.typerep, e.mtyperep, top.downSubst))
                                             then attributeDef(dl, '.', attr, '=', e.monadRewritten, ';', location=top.location)
                                             else synthesizedAttributeDef(dl, attr,
                                                    Silver_Expr {
                                                      $Expr {monadReturn(attr.typerep, top.location)}
                                                              ($Expr {e.monadRewritten})
                                                     }, location=top.location)
                                        else errorProductionStmt([err(top.location, "Implicit attributes must have " ++
                                                 "a monad type")], location=top.location)
                                   else errorProductionStmt(merrors, location=top.location)
              | _ -> errorProductionStmt([err(top.location,
                                              "Implicit equations can only be used for attributes " ++
                                              "declared to be implicit; " ++ attr.unparse ++ " is not implicit")],
                                              location=top.location)
              end;

  forwards to fwd; --unsafeTrace(fwd, print(top.location.unparse ++ ": " ++ fwd.unparse ++ "\n\n", unsafeIO()));
}


--Write an equation that only allows accessing explicit monads
terminal Explicit_kwd    'explicit'     lexer classes {KEYWORD,RESERVED};

concrete production explicitAttributeDef
top::ProductionStmt ::= 'explicit' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  e.downSubst = top.downSubst;
  e.mDownSubst = top.downSubst;
  e.finalSubst = e.mUpSubst;
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";

  top.productionAttributes := [];
  top.defs := [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to case attr.typerep of
              | explicitType(t) -> if null(e.notExplicitAttributes)
                                   then attributeDef(dl, '.', attr, '=', e, ';', location=top.location)
                                   else errorProductionStmt(buildExplicitAttrErrors(e.notExplicitAttributes), location=top.location)
              | _ -> errorProductionStmt([err(top.location,
                                              "Explicit equations can only be used for attributes " ++
                                              "declared to be explicit; " ++ attr.unparse ++ " is not explicit")],
                                              location=top.location)
              end;
}

function buildExplicitAttrErrors
[Message] ::= l::[Pair<String Location>]
{
  return case l of
         | [] -> []
         | pair(name, loca)::t ->
           err(loca, "Attributes accessed in explicit equations must be explicit; " ++
                     name ++ " is not")::buildExplicitAttrErrors(t)
         end;
}

