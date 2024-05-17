grammar silver:compiler:metatranslation;

imports silver:reflect;
imports silver:langutil:pp;
imports silver:core;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:modification:list;
imports silver:compiler:extension:patternmatching;

function translate
Expr ::= ast::AST
{
  ast.givenLocation = getParsedOriginLocationOrFallback(ambientOrigin());
  return ast.translation;
}

function translatePattern
Pattern ::= ast::AST
{
  ast.givenLocation = getParsedOriginLocationOrFallback(ambientOrigin());
  return ast.patternTranslation;
}

synthesized attribute translation<a>::a;
synthesized attribute patternTranslation<a>::a;
synthesized attribute foundLocation::Maybe<Location>;
inherited attribute givenLocation::Location;

flowtype translation {givenLocation} on AST, ASTs, NamedASTs, NamedAST;
flowtype patternTranslation {givenLocation} on AST, ASTs;
flowtype foundLocation {} on ASTs, NamedASTs, NamedAST;

propagate givenLocation on AST, ASTs, NamedASTs, NamedAST excluding nonterminalAST;

attribute givenLocation, translation<Expr>, patternTranslation<Pattern> occurs on AST;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  production givenLocation::Location =
    fromMaybe(top.givenLocation, alt(getParsedOriginLocation(top), alt(children.foundLocation, annotations.foundLocation)));
  attachNote logicalLocationNote(givenLocation);  -- In case the quoted language doesn't use origins
  
  production attribute antiquoteTranslation::Maybe<Expr> with orElse;
  antiquoteTranslation := nothing();
  
  -- "Direct" antiquote productions
  production attribute directAntiquoteProductions::[String] with ++;
  directAntiquoteProductions := [];
  antiquoteTranslation <-
    if contains(prodName, directAntiquoteProductions)
    then
      let wrapped::AST = 
        case children of
        | consAST(a, nilAST()) -> new(a)
        | consAST(
            terminalAST(_, _, _),
            consAST(
              terminalAST(_, _, _),
              consAST(
                a,
                consAST(
                  terminalAST(_, _, _),
                  nilAST())))) -> a
        | _ -> error(s"Unexpected antiquote production arguments: ${show(80, top.pp)}")
        end
      in
        case reify(wrapped) of
        | right(e) -> just(e)
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
      end
    else nothing();
  
  -- "Collection" antiquote productions
  -- Key: antiquote production name
  -- Value: (nonterminal short name, cons production name, nil production name, append production name)
  production attribute collectionAntiquoteProductions::[(String, String, String, String, String)] with ++;
  collectionAntiquoteProductions := [];
  antiquoteTranslation <-
    do {
      -- (antiquote production name, antiquote expr AST, rest AST)
      antiquote::(String, AST, Decorated AST with {givenLocation}) <-
        case children of
        | consAST(
            nonterminalAST(p, consAST(a, _), _),
            consAST(rest, nilAST())) ->
          just((p, a, rest))
        | _ -> nothing()
        end;
      -- (nonterminal short name, cons production name, nil production name, append production name)
      trans::(String, String, String, String) <-
        lookup(antiquote.1, collectionAntiquoteProductions);
      guard(prodName == trans.2);
      let antiquoteExpr::Expr =
        case reify(antiquote.2) of
        | right(e) -> e
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end;
      return
        case antiquote.3 of
        -- The next item in the list is the nil production, no need to insert an append.
        | nonterminalAST(p, _, _) when p == trans.3 -> antiquoteExpr
        -- There are more items that need to be appended to the antiquoted expression.
        | _ -> mkStrFunctionInvocation(trans.4, [antiquoteExpr, antiquote.3.translation])
        end;
    };
  antiquoteTranslation <-
    do {
      -- (nonterminal short name, cons production name, append production name)
      trans::(String, String, String, String) <-
        lookup(prodName, collectionAntiquoteProductions);
      return
        errorExpr([err(givenLocation, s"$$${trans.1} may only occur as a member of ${trans.1}")]);
    };
  
  antiquoteTranslation <-
    if contains(prodName, patternAntiquoteProductions)
    then just(errorExpr([err(givenLocation, "Pattern antiquote is invalid in expression context")]))
    else nothing();
  
  top.translation =
    fromMaybe(
      mkFullFunctionInvocation(
        baseExpr(qName(prodName)),
        children.translation,
        annotations.translation),
      antiquoteTranslation);
  
  production attribute patternAntiquoteTranslation::Maybe<Pattern> with orElse;
  patternAntiquoteTranslation := nothing();
  
  production attribute patternAntiquoteProductions::[String] with ++;
  patternAntiquoteProductions := [];
  patternAntiquoteTranslation <-
    if contains(prodName, patternAntiquoteProductions)
    then
      let wrapped::AST = 
        case children of
        | consAST(a, nilAST()) -> new(a)
        | consAST(terminalAST(_, _, _), consAST(a, nilAST())) -> a
        | consAST(
            terminalAST(_, _, _),
            consAST(
              terminalAST(_, _, _),
              consAST(
                a,
                consAST(
                  terminalAST(_, _, _),
                  nilAST())))) -> a
        | _ -> error(s"Unexpected antiquote production arguments: ${show(80, top.pp)}")
        end
      in
        case reify(wrapped) of
        | right(p) -> just(p)
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
      end
    else nothing();
  
  patternAntiquoteTranslation <-
    if contains(prodName, directAntiquoteProductions ++ map(fst, collectionAntiquoteProductions))
    then just(errorPattern([err(givenLocation, "Expression antiquote is invalid in pattern context")]))
    else nothing();
  
  -- Note that we intentionally ignore annotations here
  top.patternTranslation =
    fromMaybe(
      prodAppPattern(qName(prodName), '(', children.patternTranslation, ')'),
      patternAntiquoteTranslation);
  
  children.givenLocation = givenLocation;
  annotations.givenLocation = givenLocation;
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  local locationAST::AST = reflect(location);
  locationAST.givenLocation = top.givenLocation;

  top.translation = Silver_Expr {
    terminal(
      -- The type expression from the name of the terminal
      $TypeExpr{nominalTypeExpr(makeQNameType(terminalName, top.givenLocation))},
      -- The quoted lexeme
      $Expr{stringConst(terminal(String_t, s"\"${escapeString(lexeme)}\"", top.givenLocation))},
      -- Try to get the location dynamically from the parsed origin location if tracking is available,
      -- or else fall back to the compile-time AST location.
      silver:core:fromMaybe($Expr{locationAST.translation}, silver:core:getParsedOriginLocation(silver:core:ambientOrigin())))
  };
  
  -- TODO: What to do here- warn about this maybe?
  -- Shouldn't really be an issue unless matching against concrete syntax containing non-fixed terminals
  top.patternTranslation = wildcPattern('_');
}

aspect production listAST
top::AST ::= vals::ASTs
{
  top.translation =
    fullList(
      '[',
      foldr(
        exprsCons(_, ',', _),
        exprsEmpty(),
        vals.translation),
      ']');
  top.patternTranslation =
    listPattern('[', vals.patternTranslation, ']');
}

aspect production stringAST
top::AST ::= s::String
{
  top.translation =
    stringConst(terminal(String_t, s"\"${escapeString(s)}\"", top.givenLocation));
  top.patternTranslation =
    strPattern(terminal(String_t, s"\"${escapeString(s)}\"", top.givenLocation));
}

aspect production integerAST
top::AST ::= i::Integer
{
  top.translation =
    intConst(terminal(Int_t, toString(i), top.givenLocation));
  top.patternTranslation =
    intPattern(terminal(Int_t, toString(i), top.givenLocation));
}

aspect production floatAST
top::AST ::= f::Float
{
  top.translation =
    floatConst(terminal(Float_t, toString(f), top.givenLocation));
  top.patternTranslation =
    fltPattern(terminal(Float_t, toString(f), top.givenLocation));
}

aspect production booleanAST
top::AST ::= b::Boolean
{
  top.translation =
    if b
    then trueConst('true')
    else falseConst('false');
  top.patternTranslation =
    if b
    then truePattern('true')
    else falsePattern('false');
}

aspect production anyAST
top::AST ::= x::a
{
  top.translation =
    case reflectTypeName(x) of
      just(n) -> error(s"Can't translate anyAST (type ${n})")
    | nothing() -> error("Can't translate anyAST")
    end;
  top.patternTranslation =
    case reflectTypeName(x) of
      just(n) -> error(s"Can't translate anyAST (type ${n})")
    | nothing() -> error("Can't translate anyAST")
    end;
}

attribute givenLocation, translation<[Expr]>, patternTranslation<PatternList>, foundLocation occurs on ASTs;

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.translation = h.translation :: t.translation;
  top.patternTranslation =
    patternList_more(h.patternTranslation, ',', t.patternTranslation);
  top.foundLocation =
    -- Try to reify the last child as a location
    case t of
    | nilAST() ->
        case reify(new(h)) of
        | right(l) -> just(l)
        | left(_) -> nothing()
        end
    | _ -> t.foundLocation
    end;
}

aspect production nilAST
top::ASTs ::=
{
  top.translation = [];
  top.patternTranslation = patternList_nil();
  top.foundLocation = nothing();
}

attribute givenLocation, translation<[(String, Expr)]>, foundLocation occurs on NamedASTs;

aspect production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  top.translation = h.translation :: t.translation;
  top.foundLocation = orElse(h.foundLocation, t.foundLocation);
}

aspect production nilNamedAST
top::NamedASTs ::=
{
  top.translation = [];
  top.foundLocation = nothing();
}

attribute givenLocation, translation<(String, Expr)>, foundLocation occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  top.translation =
    -- hack to get annotation shortname
    (last(explode(":", n)), v.translation);
  top.foundLocation =
    if n == "silver:core:location"
    then
      case reify(new(v)) of
      | right(l) -> just(l)
      | left(msg) -> error(s"Error in reifying location:\n${msg}")
      end
    else nothing();
}

-- the functions below are directly referenced in reflection code in silver:compiler:extensions:silverconstruction
-- so make sure you grep for that if you change/move them.

fun makeName Name ::= n::String loc::Location =
  if isUpper(head(explode("", n)))
  then nameIdUpper(terminal(IdUpper_t, n, loc))
  else nameIdLower(terminal(IdLower_t, n, loc));

function makeQName
QName ::= n::String loc::Location
{
  local ns::[Name] = map(makeName(_, loc), explode(":", n));
  return
    foldr(
      qNameCons(_, ':', _),
      qNameId(last(ns)),
      init(ns));
}

function makeQNameType
QNameType ::= n::String loc::Location
{
  local ns::[String] = explode(":", n);
  return
    foldr(
      qNameTypeCons(_, ':', _),
      qNameTypeId(terminal(IdUpper_t, last(ns), loc)),
      map(makeName(_, loc), init(ns)));
}
