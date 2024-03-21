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
     else [errFromOrigin(top, monadToString(attr.typerep) ++
               " is not an instance of MonadFail and cannot " ++
               "be used in an empty equation")]) ++
     ( if attr.found && dl.found
       then case attr.attrDcl of
            | implicitInhDcl(_, _, _) -> []
            | implicitSynDcl(_, _, _) -> []
            | _ -> [errFromOrigin(top, "Implicit equations can only be used for " ++
                                      "attributes declared to be implicit; " ++
                                      attr.unparse ++ " is not implicit")]
            end
       else dl.errors ++ attr.errors );

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to
     if null(merrors)
     then attr.attrDcl.attrDefDispatcher(dl, attr, monadFail())
     else errorProductionStmt(merrors);
}


global partialDefaultAttributeDef::(ProductionStmt ::= Decorated! DefLHS  Decorated! QNameAttrOccur  Expr) =
  \ dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Expr ->
    attributeDef(newUnique(dl), '.', newUnique(attr), '=', e, ';');

concrete production implicitAttributeDef
top::ProductionStmt ::= 'implicit' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.unparse = "\timplicit" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";
  propagate grammarName, compiledGrammars, config, frame, env, flowEnv, finalSubst, originRules;

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
            | _ -> [errFromOrigin(top, "Implicit equations can only be used for " ++
                                      "attributes declared to be implicit; " ++
                                      attr.unparse ++ " is not implicit")]
            end
       else [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to
           (if null(merrors)
            then if attr.found
                 then attr.attrDcl.attrDefDispatcher
                      --if not found, let the normal dispatcher handle it
                 else partialDefaultAttributeDef
            else errorAttributeDef(merrors, _, _, _))(dl, attr, e);
}




concrete production restrictedAttributeDef
top::ProductionStmt ::= 'restricted' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  e.downSubst = top.downSubst;
  top.unparse = "\trestricted" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";
  propagate grammarName, compiledGrammars, config, frame, env, flowEnv, finalSubst, originRules;

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
         | _ -> [errFromOrigin(top, "Restricted equations can only be used for " ++
                                   "attributes declared to be restricted; " ++
                                   attr.unparse ++ " is not restricted")]
         end
    else [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to
           (if null(merrors)
            then if attr.found
                 then attr.attrDcl.attrDefDispatcher
                      --if not found, let the normal dispatcher handle it
                 else partialDefaultAttributeDef
            else errorAttributeDef(merrors, _, _, _))(dl, attr, e);
}




concrete production unrestrictedAttributeDef
top::ProductionStmt ::= 'unrestricted' dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.unparse = "\tunrestricted" ++ dl.unparse ++ "." ++ attr.unparse ++ " = ;";
  propagate grammarName, compiledGrammars, config, frame, env, flowEnv, finalSubst, originRules;

  top.productionAttributes := [];
  top.defs := [];
  top.forwardExpr := [];
  top.returnExpr := [];
  top.undecorateExpr := [];

  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  top.containsPluck = false;

  local restrictedErr::[Message] =
           [errFromOrigin(top,
                "Unrestricted equations can only be used for attributes " ++
                "not declared to be restricted or implicit; " ++ attr.unparse ++ " is restricted")];
  local implicitErr::[Message] =
           [errFromOrigin(top,
                "Unrestricted equations can only be used for attributes " ++
                "not declared to be restricted or implicit; " ++ attr.unparse ++ " is implicit")];
  forwards to
            (if attr.found
             then case attr.attrDcl of
                  | restrictedSynDcl(_, _, _) -> errorAttributeDef(restrictedErr, _, _, _)
                  | restrictedInhDcl(_, _, _) -> errorAttributeDef(restrictedErr, _, _, _)
                  | implicitSynDcl(_, _, _) -> errorAttributeDef(implicitErr, _, _, _)
                  | implicitInhDcl(_, _, _) -> errorAttributeDef(implicitErr, _, _, _)
                  | _ -> partialDefaultAttributeDef
                  end
                 --if not found, let the normal dispatcher handle it
             else partialDefaultAttributeDef)(dl, attr, e);
}






--take a list of unallowed attributes and generate error messages for them
fun buildExplicitAttrErrors [Message] ::= l::[Decorated QNameAttrOccur] =
  case l of
  | [] -> []
  | a::t ->
    errFromOrigin(a, "Attributes accessed in restricted equations must be restricted; " ++
              a.name ++ " is not")::buildExplicitAttrErrors(t)
  end;



--productions for error checking on restricted attributes
abstract production restrictedSynAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Expr
{
  undecorates to attributeDef(dl, '.', attr, '=', e, ';');
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
    (if null(merrors)
     then synthesizedAttributeDef(_, _, _)
     else errorAttributeDef(merrors, _, _, _))(dl, attr, e);
}


abstract production restrictedInhAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Expr
{
  undecorates to attributeDef(dl, '.', attr, '=', e, ';');
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
    (if null(merrors)
     then inheritedAttributeDef(_, _, _)
     else errorAttributeDef(merrors, _, _, _))(dl, attr, e);
}




--productions for error checking on implicit attributes
abstract production implicitSynAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Expr
{
  undecorates to attributeDef(dl, '.', attr, '=', e, ';');
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

  forwards to
         (if null(e.merrors)
          then if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
               then synthesizedAttributeDef(_, _, e.monadRewritten)
               else synthesizedAttributeDef(_, _, Silver_Expr {
                                                    $Expr {monadReturn()}
                                                        ($Expr {e.monadRewritten})
                                                  })
          else errorAttributeDef(e.merrors, _, _, e.monadRewritten))(dl, attr);
}


abstract production implicitInhAttributeDef
top::ProductionStmt ::= dl::Decorated! DefLHS attr::Decorated! QNameAttrOccur e::Expr
{
  undecorates to attributeDef(dl, '.', attr, '=', e, ';');
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

  forwards to
         (if null(e.merrors)
          then if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
               then inheritedAttributeDef(_, _, e.monadRewritten)
               else inheritedAttributeDef(_, _, Silver_Expr {
                                                  $Expr {monadReturn()}
                                                      ($Expr {e.monadRewritten})
                                                })
          else errorAttributeDef(e.merrors, _, _, e.monadRewritten))(dl, attr);
}

