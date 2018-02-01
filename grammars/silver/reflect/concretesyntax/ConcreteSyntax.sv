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
terminal True_kwd  'true' dominates {Id_t};
terminal False_kwd 'false' dominates {Id_t};
terminal Int_t     /[\-]?[0-9]+/;
terminal Float_t   /[\-]?[0-9]+[\.][0-9]+/;
terminal String_t  /[\"]([^\r\n\"\\]|[\\][\"]|[\\][\\]|[\\]n|[\\]r|[\\]t)*[\"]/;

ignore terminal WhiteSpace /[\r\n\t\ ]+/;

nonterminal AST_c with ast<AST>;

concrete productions top::AST_c
| prodName::QName_c '(' children::ASTs_c ',' annotations::NamedASTs_c ')'
  { top.ast = nonterminalAST(prodName.ast, foldr(consAST, nilAST(), children.ast), foldr(consNamedAST, nilNamedAST(), annotations.ast)); }
| prodName::QName_c '(' children::ASTs_c ')'
  { top.ast = nonterminalAST(prodName.ast, foldr(consAST, nilAST(), children.ast), nilNamedAST()); }
| prodName::QName_c '(' annotations::NamedASTs_c ')'
  { top.ast = nonterminalAST(prodName.ast, nilAST(), foldr(consNamedAST, nilNamedAST(), annotations.ast)); }
| prodName::QName_c '(' ')'
  { top.ast = nonterminalAST(prodName.ast, nilAST(), nilNamedAST()); }
| '[' vals::ASTs_c ']'
  { top.ast = listAST(foldr(consAST, nilAST(), vals.ast)); }
| s::String_t
{ -- TODO: De-escape string properly
  top.ast = stringAST(substring(1, length(s.lexeme) - 1, s.lexeme)); }
| i::Int_t
  { top.ast = integerAST(toInt(i.lexeme)); }
| f::Float_t
  { top.ast = floatAST(toFloat(f.lexeme)); }
| 'true'
  { top.ast = booleanAST(true); }
| 'false'
  { top.ast = booleanAST(false); }

nonterminal ASTs_c with ast<[AST]>;

concrete productions top::ASTs_c
| t::ASTs_c ',' h::AST_c
  { top.ast = t.ast ++ [h.ast]; }
| h::AST_c
  { top.ast = [h.ast]; }

nonterminal NamedASTs_c with ast<[NamedAST]>;

concrete productions top::NamedASTs_c
| t::NamedASTs_c ',' h::NamedAST_c
  { top.ast = t.ast ++ [h.ast]; }
| h::NamedAST_c
  { top.ast = [h.ast]; }

nonterminal NamedAST_c with ast<NamedAST>;

concrete productions top::NamedAST_c
| n::QName_c '=' v::AST_c
  { top.ast = namedAST(n.ast, v.ast); }

nonterminal QName_c with ast<String>;

concrete productions top::QName_c
| id::Id_t
  { top.ast = id.lexeme; }
| id::Id_t ':' qn::QName_c
  { top.ast = id.lexeme ++ ":" ++ qn.ast; }
