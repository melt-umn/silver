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
          if null(merrors)
          then if attr.found
               then attr.attrDcl.attrDefDispatcher(dl, attr, @e)
                    --if not found, let the normal dispatcher handle it
               else attributeDef(new(dl), '.', new(attr), '=', @e, ';')
          else errorAttributeDef(merrors, dl, attr, @e);
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
          if null(merrors)
          then if attr.found
               then attr.attrDcl.attrDefDispatcher(dl, attr, @e)
                    --if not found, let the normal dispatcher handle it
               else attributeDef(new(dl), '.', new(attr), '=', @e, ';')
          else errorAttributeDef(merrors, dl, attr, @e);
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
          if attr.found
          then case attr.attrDcl of
               | restrictedSynDcl(_, _, _) -> errorAttributeDef(restrictedErr, dl, attr, @e)
               | restrictedInhDcl(_, _, _) -> errorAttributeDef(restrictedErr, dl, attr, @e)
               | implicitSynDcl(_, _, _) -> errorAttributeDef(implicitErr, dl, attr, @e)
               | implicitInhDcl(_, _, _) -> errorAttributeDef(implicitErr, dl, attr, @e)
               | _ -> attributeDef(new(dl), '.', new(attr), '=', @e, ';')
               end
          --if not found, let the normal dispatcher handle it
          else attributeDef(new(dl), '.', new(attr), '=', @e, ';');
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
abstract production restrictedSynAttributeDef implements AttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
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
    if null(merrors)
    then synthesizedAttributeDef(dl, attr, @e)
    else errorAttributeDef(merrors, dl, attr, @e);
}


abstract production restrictedInhAttributeDef implements AttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
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
    if null(merrors)
    then inheritedAttributeDef(dl, attr, @e)
    else errorAttributeDef(merrors, dl, attr, @e);
}




--productions for error checking on implicit attributes
abstract production implicitSynAttributeDef implements AttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
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
         if null(e.merrors)
         then if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
              then synthesizedAttributeDef(dl, attr, e.monadRewritten)
              else synthesizedAttributeDef(dl, attr, Silver_Expr {
                                                    $Expr {monadReturn()}
                                                        ($Expr {e.monadRewritten})
                                                  })
         else errorAttributeDef(e.merrors, dl, attr, e.monadRewritten);
}


abstract production implicitInhAttributeDef implements AttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
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
         if null(e.merrors)
         then if  fst(monadsMatch(attr.typerep, e.mtyperep, e.mUpSubst))
              then synthesizedAttributeDef(dl, attr, e.monadRewritten)
              else synthesizedAttributeDef(dl, attr, Silver_Expr {
                                                    $Expr {monadReturn()}
                                                        ($Expr {e.monadRewritten})
                                                  })
         else errorAttributeDef(e.merrors, dl, attr, e.monadRewritten);
}

