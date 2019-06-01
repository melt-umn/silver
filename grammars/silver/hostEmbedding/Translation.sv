grammar silver:hostEmbedding;

imports silver:reflect;
imports silver:langutil:pp;
imports core:monad;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type:syntax;
imports silver:extension:list;
imports silver:extension:patternmatching;

function srcPP
String ::= loc::Location ast::AST
{
  ast.givenLocation = loc;
  return show(80, ast.srcPP);
}

function baseName
String ::= n::String
{
  return
    if n == ""
    then n
    else last(explode(":", n));
}

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

synthesized attribute srcPP::Document; -- pp for the equivalent plain-Silver source code
synthesized attribute translation<a>::a;
synthesized attribute patternTranslation<a>::a;
synthesized attribute foundLocation::Maybe<Location>;
autocopy attribute givenLocation::Location;

attribute givenLocation, srcPP, translation<Expr>, patternTranslation<Pattern> occurs on AST;

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
  
  top.srcPP =
    case escapeTranslation of
    | just(e) ->
      text(substitute("( ", "(", substitute(",,", ",", substitute("(,", "(",
        case indexOf("(", e.unparse), lastIndexOf("core:loc", e.unparse) of
        | -1, _ -> e.unparse
        | i, -1 ->
          baseName(substring(0, i, e.unparse)) ++
          substring(i, length(e.unparse), e.unparse)
        | i, j ->
          baseName(substring(0, i, e.unparse)) ++
          substring(i, j, e.unparse) ++
          "builtin)"
        end))))
    | nothing() ->
      cat(
        text(baseName(prodName)),
        parens(
          groupnest(
            2,
            ppImplode(
              cat(comma(), line()),
              children.srcPPs ++ annotations.srcPPs))))
    end;
  
  top.translation =
    fromMaybe(
      mkFullFunctionInvocation(
        givenLocation,
        baseExpr(qName(givenLocation, prodName), location=givenLocation),
        children.translation,
        annotations.translation),
      escapeTranslation);
  
  production attribute patternEscapeTranslation::Maybe<Pattern> with orElse;
  patternEscapeTranslation := nothing();
  
  production attribute varPatternProductions::[String] with ++;
  varPatternProductions := [];
  patternEscapeTranslation <-
    if containsBy(stringEq, prodName, varPatternProductions)
    then
      let wrapped::AST = 
        case children of
        | consAST(a, nilAST()) -> a
        | consAST(terminalAST(_, _, _), consAST(a, nilAST())) -> a
        | _ -> error(s"Unexpected escape production arguments: ${show(80, top.pp)}")
        end
      in
        case reify(wrapped) of
        | right(n) -> just(varPattern(n, location=givenLocation))
        | left(msg) -> error(s"Error in reifying child of production ${prodName}:\n${msg}")
        end
      end
    else nothing();
  
  production attribute wildPatternProductions::[String] with ++;
  wildPatternProductions := [];
  patternEscapeTranslation <-
    if containsBy(stringEq, prodName, wildPatternProductions)
    then
      case children of
      | nilAST() -> just(wildcPattern('_', location=givenLocation))
      | consAST(terminalAST(_, _, _), nilAST()) -> just(wildcPattern('_', location=givenLocation))
      | _ -> error(s"Unexpected escape production arguments: ${show(80, top.pp)}")
      end
    else nothing();
  
  top.patternTranslation =
    fromMaybe(
      prodAppPattern(
        qName(givenLocation, prodName),
        '(',
        children.patternTranslation,
        ')',
        location=givenLocation),
      patternEscapeTranslation);
  
  children.givenLocation = givenLocation;
  annotations.givenLocation = givenLocation;
}

aspect production terminalAST
top::AST ::= terminalName::String lexeme::String location::Location
{
  local locationAST::AST = reflect(new(location));
  locationAST.givenLocation = top.givenLocation;
  
  top.srcPP = pp"terminal(${text(terminalName)}, \"${text(escapeString(lexeme))}\", ${text(location.unparse)}";

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
  
  -- TODO: What to do here- warn about this maybe?
  -- Shouldn't really be an issue unless matching against concrete syntax containing non-fixed terminals
  top.patternTranslation = wildcPattern('_', location=top.givenLocation);
}

aspect production listAST
top::AST ::= vals::ASTs
{
  top.srcPP = brackets(groupnest(2, cat(line(), ppImplode(cat(comma(), line()), vals.srcPPs))));
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
  top.srcPP = text(s"\"${escapeString(s)}\"");
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
  top.srcPP = text(toString(i));
  top.translation =
    intConst(terminal(Int_t, toString(i), top.givenLocation), location=top.givenLocation);
  top.patternTranslation =
    intPattern(terminal(Int_t, toString(i), top.givenLocation), location=top.givenLocation);
}

aspect production floatAST
top::AST ::= f::Float
{
  top.srcPP = text(toString(f));
  top.translation =
    floatConst(terminal(Float_t, toString(f), top.givenLocation), location=top.givenLocation);
  top.patternTranslation =
    fltPattern(terminal(Float_t, toString(f), top.givenLocation), location=top.givenLocation);
}

aspect production booleanAST
top::AST ::= b::Boolean
{
  top.srcPP = text(toString(b));
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
  top.srcPP = error("Can't srcPP anyAST");
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

synthesized attribute srcPPs::[Document];

attribute givenLocation, srcPPs, translation<[Expr]>, patternTranslation<PatternList>, foundLocation occurs on ASTs;

aspect production consAST
top::ASTs ::= h::AST t::ASTs
{
  top.srcPPs = h.srcPP :: t.srcPPs;
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
  top.srcPPs = [];
  top.translation = [];
  top.patternTranslation = patternList_nil(location=top.givenLocation);
  top.foundLocation = nothing();
}

attribute givenLocation, srcPPs, translation<[Pair<String Expr>]>, foundLocation occurs on NamedASTs;

aspect production consNamedAST
top::NamedASTs ::= h::NamedAST t::NamedASTs
{
  top.srcPPs = h.srcPP :: t.srcPPs;
  top.translation = h.translation :: t.translation;
  top.foundLocation = orElse(h.foundLocation, t.foundLocation);
}

aspect production nilNamedAST
top::NamedASTs ::=
{
  top.srcPPs = [];
  top.translation = [];
  top.foundLocation = nothing();
}

attribute givenLocation, srcPP, translation<Pair<String Expr>>, foundLocation occurs on NamedAST;

aspect production namedAST
top::NamedAST ::= n::String v::AST
{
  top.srcPP = 
    if n == "core:location"
    then pp"location=builtin" -- For computing the actual source character count this is more accurate
    else pp"${text(baseName(n))}=${v.srcPP}";
  top.translation =
    -- hack to get annotation shortname
    pair(last(explode(":", n)), v.translation);
  top.foundLocation =
    if n == "core:location"
    then
      case reify(v) of
      | right(l) -> just(l)
      | left(msg) -> error(s"Error in reifying location:\n${msg}")
      end
    else nothing();
}

-- the functions below are directly referenced in reflection code in silver:extensions:silverconstruction
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
