grammar silver:reflect:concretesyntax;

imports silver:langutil;
imports silver:reflect;

-- Re-use Silver's concrete syntax where useful
imports silver:definition:core;
imports silver:extension:list;

nonterminal AST_c with ast<AST>;

concrete productions top::AST_c
| prodName::QName '(' children::ASTs_c ',' annotations::NamedASTs_c ')'
  { top.ast = nonterminalAST(prodName.name, foldr(consAST, nilAST(), children.ast), foldr(consNamedAST, nilNamedAST(), annotations.ast)); }
| prodName::QName '(' children::ASTs_c ')'
  { top.ast = nonterminalAST(prodName.name, foldr(consAST, nilAST(), children.ast), nilNamedAST()); }
| prodName::QName '(' annotations::NamedASTs_c ')'
  { top.ast = nonterminalAST(prodName.name, nilAST(), foldr(consNamedAST, nilNamedAST(), annotations.ast)); }
| prodName::QName '(' ')'
  { top.ast = nonterminalAST(prodName.name, nilAST(), nilNamedAST()); }
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
| n::QName '=' v::AST_c
  { top.ast = namedAST(n.name, v.ast); }
