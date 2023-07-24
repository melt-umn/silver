grammar silver:compiler:extension:deriving;

import silver:compiler:extension:patternmatching only Arrow_kwd, Vbar_kwd;

terminal Derive_t 'derive' lexer classes {KEYWORD};

concrete production deriveTCsOnNTListDcl_c
top::AGDcl ::= 'derive' tcs::NameList 'on' nts::NameList ';'
{
  top.unparse = s"derive ${tcs.unparse} on ${nts.unparse};";
  
  forwards to deriveTCsOnNTListDcl(tcs, nts, location=top.location);
}

production deriveTCsOnNTListDcl
top::AGDcl ::= tcs::NameList nts::NameList
{
  top.unparse = s"derive ${tcs.unparse} on ${nts.unparse};";
  
  forwards to
    case nts of
    | nameListOne(n) -> deriveTCsOnOneNTDcl(tcs, n, location=n.location)
    | nameListCons(n, _, rest) ->
      appendAGDcl(
        deriveTCsOnOneNTDcl(tcs, n, location=n.location),
        deriveTCsOnNTListDcl(tcs, rest, location=top.location),
        location=top.location)
    end;
}

production deriveTCsOnOneNTDcl
top::AGDcl ::= tcs::NameList nt::QName
{
  top.unparse = s"derive ${tcs.unparse} on ${nt.unparse};";
  
  forwards to
    case tcs of
    | nameListOne(tc) -> deriveDcl(tc, nt, location=top.location)
    | nameListCons(tc, _, rest) ->
      appendAGDcl(
        deriveDcl(tc, nt, location=top.location),
        deriveTCsOnOneNTDcl(rest, nt, location=top.location),
        location=top.location)
    end;
}

production deriveDcl
top::AGDcl ::= tc::QName nt::QName
{
  top.unparse = s"derive ${tc.unparse} on ${nt.unparse};";
  propagate env;

  local localErrors::[Message] =
    tc.lookupType.errors ++ nt.lookupType.errors ++
    (if tc.lookupType.found && !tc.lookupType.dcl.isClass
     then [err(tc.location, s"${tc.lookupType.fullName} is not a type class")]
     else []) ++
    (if nt.lookupType.found && !(nt.lookupType.dcl.isType && nt.lookupType.typeScheme.isNonterminal)
     then [err(nt.location, s"${nt.lookupType.fullName} is not a nonterminal")]
     else []) ++
    (if nt.lookupType.found && nt.lookupType.dcl.isClosed
     then [err(nt.location, s"Cannot derive instances for ${nt.lookupType.fullName}, since that nonterminal is closed")]
     else []);
  top.errors := if null(localErrors) then forward.errors else localErrors;

  forwards to
    -- TODO: Yuck, can't forward based on env here since we need the defs from the forward.
    case "silver:core:" ++ tc.name of  -- tc.lookupType.fullName
    | "silver:core:Eq" -> deriveEqDcl(nt, location=top.location)
    --| "silver:core:Ord" -> deriveOrdDcl(nt, location=top.location)
    | fn -> errorAGDcl([err(tc.location, s"Cannot derive type class ${fn}")], location=top.location)
    end;
}

production deriveEqDcl
top::AGDcl ::= nt::Decorated! QName
{
  undecorates to deriveDcl(qName(top.location, "silver:core:Eq"), nt, location=top.location);
  top.unparse = s"derive silver:core:Eq on ${nt.unparse};";

  local tvs::[TyVar] = map(freshTyVar, nt.lookupType.dcl.kindrep.argKinds);
  local ntty::Type = appTypes(nt.lookupType.typeScheme.monoType, map(skolemType, tvs));
  
  local includedProds::[ValueDclInfo] =
    filter(
      \ d::ValueDclInfo -> !d.hasForward,
      getKnownProds(nt.lookupType.fullName, top.env));
  forwards to Silver_AGDcl {
    instance $ConstraintList{
      foldr(
        consConstraint(_, ',', _, location=top.location),
        nilConstraint(location=top.location),
        map(
          \ tv::TyVar ->
            classConstraint(
              qName(top.location, "silver:core:Eq").qNameType,
              typerepTypeExpr(skolemType(tv), location=top.location),
              location=top.location),
          tvs))} => silver:core:Eq $TypeExpr{typerepTypeExpr(ntty, location=top.location)} {
        eq = \ x::$TypeExpr{typerepTypeExpr(ntty, location=top.location)} y::$TypeExpr{typerepTypeExpr(ntty, location=top.location)} -> $Expr{
          if null(includedProds) then Silver_Expr {true} else
          foldr(
            and(_, '&&', _, location=top.location),
            matchPrimitive(
              Silver_Expr {x},
              Silver_TypeExpr {Boolean},
              foldPrimPatterns(
                map(
                  \ prod::ValueDclInfo ->
                    prodPattern(qName(top.location, prod.fullName), '(',
                    foldr(
                      consVarBinder(_, ',', _, location=top.location),
                      nilVarBinder(location=top.location),
                      map(\ i::Integer ->
                        varVarBinder(name(s"a${toString(i)}", top.location), location=top.location),
                        range(0, length(prod.namedSignature.inputElements)))), ')', '->',
                    matchPrimitive(
                      Silver_Expr {y},
                      Silver_TypeExpr {Boolean},
                      onePattern(
                        prodPattern(qName(top.location, prod.fullName), '(',
                          foldr(
                            consVarBinder(_, ',', _, location=top.location),
                            nilVarBinder(location=top.location),
                            map(\ i::Integer ->
                              varVarBinder(name(s"b${toString(i)}", top.location), location=top.location),
                              range(0, length(prod.namedSignature.inputElements)))), ')', '->',
                          foldr(
                            and(_, '&&', _, location=top.location),
                            Silver_Expr {true},
                            map(
                              \ i::Integer -> Silver_Expr { $name{s"a${toString(i)}"} == $name{s"b${toString(i)}"} },
                              range(0, length(prod.namedSignature.inputElements)))),
                          location=top.location),
                        location=top.location),
                      Silver_Expr {false},
                      location=top.location),
                    location=top.location),
                  includedProds),
                top.location),
              Silver_Expr {error("Unexpected production in derived Eq instance!")},
              location=top.location),
            map(
              \ anno::NamedSignatureElement ->
                Silver_Expr { x.$name{anno.elementName} == y.$name{anno.elementName} },
              annotationsForNonterminal(ntty, top.env)))};
        neq = \ x::$TypeExpr{typerepTypeExpr(ntty, location=top.location)} y::$TypeExpr{typerepTypeExpr(ntty, location=top.location)} -> $Expr{
          if null(includedProds) then Silver_Expr {false} else
          foldr(
            or(_, '||', _, location=top.location),
            matchPrimitive(
              Silver_Expr {x},
              Silver_TypeExpr {Boolean},
              foldPrimPatterns(
                map(
                  \ prod::ValueDclInfo ->
                    prodPattern(qName(top.location, prod.fullName), '(',
                    foldr(
                      consVarBinder(_, ',', _, location=top.location),
                      nilVarBinder(location=top.location),
                      map(\ i::Integer ->
                        varVarBinder(name(s"a${toString(i)}", top.location), location=top.location),
                        range(0, length(prod.namedSignature.inputElements)))), ')', '->',
                    matchPrimitive(
                      Silver_Expr {y},
                      Silver_TypeExpr {Boolean},
                      onePattern(
                        prodPattern(qName(top.location, prod.fullName), '(',
                          foldr(
                            consVarBinder(_, ',', _, location=top.location),
                            nilVarBinder(location=top.location),
                            map(\ i::Integer ->
                              varVarBinder(name(s"b${toString(i)}", top.location), location=top.location),
                              range(0, length(prod.namedSignature.inputElements)))), ')', '->',
                          foldr(
                            or(_, '||', _, location=top.location),
                            Silver_Expr {false},
                            map(
                              \ i::Integer -> Silver_Expr { $name{s"a${toString(i)}"} != $name{s"b${toString(i)}"} },
                              range(0, length(prod.namedSignature.inputElements)))),
                          location=top.location),
                        location=top.location),
                      Silver_Expr {true},
                      location=top.location),
                    location=top.location),
                  includedProds),
                top.location),
              Silver_Expr {error("Unexpected production in derived Eq instance!")},
              location=top.location),
            map(
              \ anno::NamedSignatureElement ->
                Silver_Expr { x.$name{anno.elementName} != y.$name{anno.elementName} },
              annotationsForNonterminal(ntty, top.env)))};
      }
  };
}

function foldPrimPatterns
PrimPatterns ::= ps::[PrimPattern]  loc::Location
{
  return
    case ps of
    | [h] -> onePattern(h, location=loc)
    | h :: t -> consPattern(h, '|', foldPrimPatterns(t, loc), location=loc)
    | [] -> error("empty patterns")
    end;
}
