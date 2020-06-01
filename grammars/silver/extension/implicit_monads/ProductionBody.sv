grammar silver:extension:implicit_monads;

--Write an empty equation filled in by an appropriate fail
concrete production emptyAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '=' ';'
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
     else []);

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to if null(merrors)
              then if isMonad(attr.typerep)
                   then if isMonad(e.mtyperep)
                        then attributeDef(dl, '.', attr, '=', e.monadRewritten, ';', location=top.location)
                        else synthesizedAttributeDef(dl, attr,
                                   Silver_Expr {
                                     $Expr {monadReturn(attr.typerep, top.location)}
                                      ($Expr {e.monadRewritten})
                                   }, location=top.location)
                   else errorProductionStmt([err(top.location, "Implicit equations can only be used " ++
                                                 "for monad-typed attributes")], location=top.location)
              else errorProductionStmt(merrors, location=top.location);
}

