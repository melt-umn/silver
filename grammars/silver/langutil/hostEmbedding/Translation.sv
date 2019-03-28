grammar silver:langutil:hostEmbedding;

imports silver:reflect;
imports silver:langutil:pp;
imports core:monad;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type:syntax;
imports silver:extension:list;

function translate
Expr ::= loc::Location ast::AST
{
  ast.givenLocation = loc;
  return ast.translation;
}

synthesized attribute translation<a>::a;
synthesized attribute foundLocation::Maybe<Location>;
autocopy attribute givenLocation::Location;

attribute givenLocation, translation<Expr> occurs on AST;

aspect production nonterminalAST
top::AST ::= prodName::String children::ASTs annotations::NamedASTs
{
  production givenLocation::Location =
    fromMaybe(top.givenLocation, orElse(children.foundLocation, annotations.foundLocation));
  
  production attribute escapeTranslation::Maybe<Expr> with orElse;
  escapeTranslation := nothing();
  
  -- "Direct" escape productions
  production attribute directEscapeProductions::[String] with ++;
  directEscapeProductions := [];
  escapeTranslation <-
    if containsBy(stringEq, prodName, directEscapeProductions)
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
        | _ -> error(s"Unexpected escape production arguments: ${show(80, top.pp)}")
        end
      in
        case reify(wrapped) of
        | right(e) -> just(e)
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
      end
    else nothing();
  
  -- "Collection" escape productions
  -- Key: escape production name
  -- Value: pair(nonterminal short name, pair(cons production name, append production name))
  production attribute collectionEscapeProductions::[Pair<String Pair<String Pair<String String>>>] with ++;
  collectionEscapeProductions := [];
  escapeTranslation <-
    do (bindMaybe, returnMaybe) {
      -- pair(escape production name, escape expr AST, rest AST)
      escape::Pair<String Pair<AST Decorated AST>> <-
        case children of
        | consAST(
            nonterminalAST(n, consAST(a, _), _),
            consAST(rest, nilAST())) -> just(pair(n, pair(a, rest)))
        | _ -> nothing()
        end;
      -- pair(nonterminal short name, pair(cons production name, append production name))
      trans::Pair<String Pair<String String>> <-
        lookupBy(stringEq, escape.fst, collectionEscapeProductions);
      if prodName == trans.snd.fst then just(unit()) else nothing(); -- require prodName == trans.snd.fst
      return
        case reify(escape.snd.fst) of
        | right(e) ->
          mkStrFunctionInvocation(
            givenLocation, trans.snd.snd, [e, escape.snd.snd.translation])
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end;
    };
  escapeTranslation <-
    do (bindMaybe, returnMaybe) {
      -- pair(nonterminal short name, pair(cons production name, append production name))
      trans::Pair<String Pair<String String>> <-
        lookupBy(stringEq, prodName, collectionEscapeProductions);
      return
        errorExpr([err(givenLocation, s"$$${trans.fst} may only occur as a member of ${trans.fst}")], location=givenLocation);
    };
    
  top.translation =
    fromMaybe(
      application(
        baseExpr(makeQName(prodName, givenLocation), location=givenLocation),
        '(',
        foldAppExprs(givenLocation, reverse(children.translation)),
        ',',
        foldl(
          snocAnnoAppExprs(_, ',', _, location=givenLocation),
          emptyAnnoAppExprs(location=givenLocation),
          reverse(annotations.translation)),
        ')', location=givenLocation),
      escapeTranslation);
  
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
        makeQNameType(terminalName, top.givenLocation), botlNone(location=top.givenLocation),
        location=top.givenLocation),
      ',',
      stringConst(
        terminal(String_t, s"\"${escapeString(lexeme)}\"", top.givenLocation),
        location=top.givenLocation),
      ',',
      locationAST.translation,
      ')', location=top.givenLocation);
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
}

aspect production stringAST
top::AST ::= s::String
{
  top.translation =
    stringConst(
      terminal(String_t, s"\"${escapeString(s)}\"", top.givenLocation),
      location=top.givenLocation);
}

aspect production integerAST
top::AST ::= i::Integer
{
  top.translation =
    intConst(terminal(Int_t, toString(i), top.givenLocation), location=top.givenLocation);
}

aspect production floatAST
top::AST ::= f::Float
{
  top.translation =
    floatConst(terminal(Float_t, toString(f), top.givenLocation), location=top.givenLocation);
}

aspect production booleanAST
top::AST ::= b::Boolean
{
  top.translation =
    if b
    then trueConst('true', location=top.givenLocation)
    else falseConst('false', location=top.givenLocation);
}

aspect production anyAST
top::AST ::= x::a
{
  top.translation =
    case reflectTypeName(x) of
      just(n) -> error(s"Can't translate anyAST (type ${n})")
    | nothing() -> error("Can't translate anyAST")
    end;
}

attribute givenLocation, translation<[Expr]>, foundLocation occurs on ASTs;

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.translation = h.translation :: t.translation;
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
  top.foundLocation = nothing();
}

attribute givenLocation, translation<[AnnoExpr]>, foundLocation occurs on NamedASTs;

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

attribute givenLocation, translation<AnnoExpr>, foundLocation occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  top.translation =
    annoExpr(
      qNameId(makeName(last(explode(":", n)), top.givenLocation), location=top.givenLocation),
      '=',
      presentAppExpr(v.translation, location=top.givenLocation),
      location=top.givenLocation);
  top.foundLocation =
    if n == "core:location"
    then
      case reify(v) of
      | right(l) -> just(l)
      | left(msg) -> error(s"Error in reifying location:\n${msg}")
      end
    else nothing();
}

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
