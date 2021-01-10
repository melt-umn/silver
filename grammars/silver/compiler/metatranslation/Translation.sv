grammar silver:compiler:metatranslation;

imports silver:reflect;
imports silver:langutil:pp;
imports silver:core;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:extension:list;
imports silver:compiler:extension:patternmatching;

function translate
Expr ::= loc::Location ast::AST
{
  ast.givenLocation = loc;
  return ast.translation;
}

function translatePattern
Pattern ::= loc::Location ast::AST
{
  ast.givenLocation = loc;
  return ast.patternTranslation;
}

synthesized attribute translation<a>::a;
synthesized attribute patternTranslation<a>::a;
synthesized attribute foundLocation::Maybe<Location>;
autocopy attribute givenLocation::Location;

flowtype translation {givenLocation} on AST, ASTs, NamedASTs, NamedAST;
flowtype patternTranslation {givenLocation} on AST, ASTs;
flowtype foundLocation {} on ASTs, NamedASTs, NamedAST;

attribute givenLocation, translation<Expr>, patternTranslation<Pattern> occurs on AST;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  production givenLocation::Location =
    fromMaybe(top.givenLocation, orElse(children.foundLocation, annotations.foundLocation));
  
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
        | consAST(a, nilAST()) -> a
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
  -- Value: pair(nonterminal short name, pair(cons production name, append production name))
  production attribute collectionAntiquoteProductions::[Pair<String Pair<String Pair<String String>>>] with ++;
  collectionAntiquoteProductions := [];
  antiquoteTranslation <-
    do (bindMaybe, returnMaybe) {
      -- pair(antiquote production name, antiquote expr AST, rest AST)
      antiquote::Pair<String Pair<AST Decorated AST>> <-
        case children of
        | consAST(
            nonterminalAST(n, consAST(a, _), _),
            consAST(rest, nilAST())) -> just(pair(n, pair(a, rest)))
        | _ -> nothing()
        end;
      -- pair(nonterminal short name, pair(cons production name, append production name))
      trans::Pair<String Pair<String String>> <-
        lookup(antiquote.fst, collectionAntiquoteProductions);
      if prodName == trans.snd.fst then just(unit()) else nothing(); -- require prodName == trans.snd.fst
      return
        case reify(antiquote.snd.fst) of
        | right(e) ->
          mkStrFunctionInvocation(
            givenLocation, trans.snd.snd, [e, antiquote.snd.snd.translation])
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end;
    };
  antiquoteTranslation <-
    do (bindMaybe, returnMaybe) {
      -- pair(nonterminal short name, pair(cons production name, append production name))
      trans::Pair<String Pair<String String>> <-
        lookup(prodName, collectionAntiquoteProductions);
      return
        errorExpr([err(givenLocation, s"$$${trans.fst} may only occur as a member of ${trans.fst}")], location=givenLocation);
    };
  
  antiquoteTranslation <-
    if contains(prodName, patternAntiquoteProductions)
    then just(errorExpr([err(givenLocation, "Pattern antiquote is invalid in expression context")], location=givenLocation))
    else nothing();
  
  top.translation =
    fromMaybe(
      mkFullFunctionInvocation(
        givenLocation,
        baseExpr(qName(givenLocation, prodName), location=givenLocation),
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
        | consAST(a, nilAST()) -> a
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
    then just(errorPattern([err(givenLocation, "Expression antiquote is invalid in pattern context")], location=givenLocation))
    else nothing();
  
  -- Note that we intentionally ignore annotations here
  top.patternTranslation =
    fromMaybe(
      prodAppPattern(
        qName(givenLocation, prodName),
        '(',
        children.patternTranslation,
        ')',
        location=givenLocation),
      patternAntiquoteTranslation);
  
  children.givenLocation = givenLocation;
  annotations.givenLocation = givenLocation;
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  local locationAST::AST = reflect(new(location));
  locationAST.givenLocation = top.givenLocation;

  top.translation =
    terminalConstructor(
      'terminal', '(',
      nominalTypeExpr(
        makeQNameType(terminalName, top.givenLocation),
        location=top.givenLocation),
      ',',
      stringConst(
        terminal(String_t, s"\"${escapeString(lexeme)}\"", top.givenLocation),
        location=top.givenLocation),
      ',',
      locationAST.translation,
      ')', location=top.givenLocation);
  
  -- TODO: What to do here- warn about this maybe?
  -- Shouldn't really be an issue unless matching against concrete syntax containing non-fixed terminals
  top.patternTranslation = wildcPattern('_', location=top.givenLocation);
}

aspect production listAST
top::AST ::= vals::ASTs
{
  top.translation =
    fullList(
      '[',
      foldr(
        exprsCons(_, ',', _, location=top.givenLocation),
        exprsEmpty(location=top.givenLocation),
        vals.translation),
      ']',
      location=top.givenLocation);
  top.patternTranslation =
    listPattern('[', vals.patternTranslation, ']', location=top.givenLocation);
}

aspect production stringAST
top::AST ::= s::String
{
  top.translation =
    stringConst(
      terminal(String_t, s"\"${escapeString(s)}\"", top.givenLocation),
      location=top.givenLocation);
  top.patternTranslation =
    strPattern(
      terminal(String_t, s"\"${escapeString(s)}\"", top.givenLocation),
      location=top.givenLocation);
}

aspect production integerAST
top::AST ::= i::Integer
{
  top.translation =
    intConst(terminal(Int_t, toString(i), top.givenLocation), location=top.givenLocation);
  top.patternTranslation =
    intPattern(terminal(Int_t, toString(i), top.givenLocation), location=top.givenLocation);
}

aspect production floatAST
top::AST ::= f::Float
{
  top.translation =
    floatConst(terminal(Float_t, toString(f), top.givenLocation), location=top.givenLocation);
  top.patternTranslation =
    fltPattern(terminal(Float_t, toString(f), top.givenLocation), location=top.givenLocation);
}

aspect production booleanAST
top::AST ::= b::Boolean
{
  top.translation =
    if b
    then trueConst('true', location=top.givenLocation)
    else falseConst('false', location=top.givenLocation);
  top.patternTranslation =
    if b
    then truePattern('true', location=top.givenLocation)
    else falsePattern('false', location=top.givenLocation);
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
    patternList_more(h.patternTranslation, ',', t.patternTranslation, location=top.givenLocation);
  top.foundLocation =
    -- Try to reify the last child as a location
    case t of
    | nilAST() ->
        case reify(h) of
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
  top.patternTranslation = patternList_nil(location=top.givenLocation);
  top.foundLocation = nothing();
}

attribute givenLocation, translation<[Pair<String Expr>]>, foundLocation occurs on NamedASTs;

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

attribute givenLocation, translation<Pair<String Expr>>, foundLocation occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  top.translation =
    -- hack to get annotation shortname
    pair(last(explode(":", n)), v.translation);
  top.foundLocation =
    if n == "silver:core:location"
    then
      case reify(v) of
      | right(l) -> just(l)
      | left(msg) -> error(s"Error in reifying location:\n${msg}")
      end
    else nothing();
}

-- the functions below are directly referenced in reflection code in silver:compiler:extensions:silverconstruction
-- so make sure you grep for that if you change/move them.

function makeName
Name ::= n::String loc::Location
{
  return
    if isUpper(head(explode("", n)))
    then nameIdUpper(terminal(IdUpper_t, n, loc), location=loc)
    else nameIdLower(terminal(IdLower_t, n, loc), location=loc);
}

function makeQName
QName ::= n::String loc::Location
{
  local ns::[Name] = map(makeName(_, loc), explode(":", n));
  return
    foldr(
      qNameCons(_, ':', _, location=loc),
      qNameId(last(ns), location=loc),
      init(ns));
}

function makeQNameType
QNameType ::= n::String loc::Location
{
  local ns::[String] = explode(":", n);
  return
    foldr(
      qNameTypeCons(_, ':', _, location=loc),
      qNameTypeId(terminal(IdUpper_t, last(ns), loc), location=loc),
      map(makeName(_, loc), init(ns)));
}
