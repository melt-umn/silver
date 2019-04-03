grammar silver:reflect:concretesyntax;

imports silver:langutil;
imports silver:reflect;

terminal Comma_t       ',';
terminal Equal_t       '=';
terminal Colon_t       ':';
terminal LParen_t      '(';
terminal RParen_t      ')';
terminal LSqr_t        '[';
terminal RSqr_t        ']';

terminal Id_t /[A-Za-z][A-Za-z0-9\_]*/;
terminal WildCard_t '_';

terminal Terminal_kwd 'terminal' dominates {Id_t};

terminal True_kwd  'true' dominates {Id_t};
terminal False_kwd 'false' dominates {Id_t};
terminal Int_t     /[\-]?[0-9]+/;
terminal Float_t   /[\-]?[0-9]+[\.][0-9]+/;
terminal String_t  /[\"]([^\r\n\"\\]|[\\][\"]|[\\][\\]|[\\]b|[\\]n|[\\]r|[\\]f|[\\]t)*[\"]/;

ignore terminal WhiteSpace /[\r\n\t\ ]+/;

nonterminal AST_c with ast<AST>, errors, location;

concrete productions top::AST_c
| prodName::QName_c '(' children::ASTs_c ',' annotations::NamedASTs_c ')'
  {
    top.ast =
      nonterminalAST(prodName.ast, foldr(consAST, nilAST(), children.ast), foldr(consNamedAST, nilNamedAST(), annotations.ast));
    top.errors := children.errors ++ annotations.errors;
  }
| prodName::QName_c '(' children::ASTs_c ')'
  {
    top.ast = nonterminalAST(prodName.ast, foldr(consAST, nilAST(), children.ast), nilNamedAST());
    top.errors := children.errors;
  }
| prodName::QName_c '(' annotations::NamedASTs_c ')'
  {
    top.ast = nonterminalAST(prodName.ast, nilAST(), foldr(consNamedAST, nilNamedAST(), annotations.ast));
    top.errors := annotations.errors;
  }
| prodName::QName_c '(' ')'
  {
    top.ast = nonterminalAST(prodName.ast, nilAST(), nilNamedAST());
    top.errors := [];
  }
| 'terminal' '(' terminalName::QName_c ',' lexeme::String_t ',' location::AST_c ')'
  {
    local locReifyRes::Either<String Location> = reify(location.ast);
    top.ast =
      terminalAST(
        terminalName.ast,
        unescapeString(substring(1, length(lexeme.lexeme) - 1, lexeme.lexeme)),
        fromRight(locReifyRes, bogusLoc()));
    top.errors :=
      case locReifyRes of
      | left(msg) -> [err(location.location, msg)]
      | right(_) -> []
      end;
  }
| '[' vals::ASTs_c ']'
  {
    top.ast = listAST(foldr(consAST, nilAST(), vals.ast));
    top.errors := vals.errors;
  }
| '[' ']'
  {
    top.ast = listAST(nilAST());
    top.errors := [];
  }
| s::String_t
  {
    top.ast = stringAST(unescapeString(substring(1, length(s.lexeme) - 1, s.lexeme)));
    top.errors := [];
  }
| i::Int_t
  {
    top.ast = integerAST(toInt(i.lexeme));
    top.errors := [];
  }
| f::Float_t
  {
    top.ast = floatAST(toFloat(f.lexeme));
    top.errors := [];
  }
| 'true'
  {
    top.ast = booleanAST(true);
    top.errors := [];
  }
| 'false'
  {
    top.ast = booleanAST(false);
    top.errors := [];
  }
| n::Id_t
  {
    top.ast = varAST(n.lexeme);
    top.errors := [];
  }
| '_'
  {
    top.ast = wildAST();
    top.errors := [];
  }

nonterminal ASTs_c with ast<[AST]>, errors;

concrete productions top::ASTs_c
| t::ASTs_c ',' h::AST_c
  {
    top.ast = t.ast ++ [h.ast];
    top.errors := h.errors ++ t.errors;
  }
| h::AST_c
  {
    top.ast = [h.ast];
    top.errors := [];
  }

nonterminal NamedASTs_c with ast<[NamedAST]>, errors;

concrete productions top::NamedASTs_c
| t::NamedASTs_c ',' h::NamedAST_c
  {
    top.ast = t.ast ++ [h.ast];
    top.errors := h.errors ++ t.errors;
  }
| h::NamedAST_c
  {
    top.ast = [h.ast];
    top.errors := [];
  }

nonterminal NamedAST_c with ast<NamedAST>, errors, location;

concrete productions top::NamedAST_c
| n::QName_c '=' v::AST_c
  {
    top.ast = namedAST(n.ast, v.ast);
    top.errors := v.errors;
  }

nonterminal QName_c with ast<String>, location;

concrete productions top::QName_c
| id::Id_t
  { top.ast = id.lexeme; }
| id::Id_t ':' qn::QName_c
  { top.ast = id.lexeme ++ ":" ++ qn.ast; }
