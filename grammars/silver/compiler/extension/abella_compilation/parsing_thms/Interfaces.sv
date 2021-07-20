grammar silver:compiler:extension:abella_compilation:parsing_thms;

{-
  Parse an interface theorem file into a grammar name, a list of
  imports, and a list of theorems.
-}

parser interface_parser::Interface_c
{
  silver:compiler:extension:abella_compilation:parsing_thms;
}


synthesized attribute ast<a>::a;


--(current grammar, imports, defined relations, theorems)
nonterminal Interface_c with ast<(String, [String], [DefinitionElement], [ParsedElement])>;


concrete productions top::Interface_c
| g::Qname_colon_t '.' imps::Imports_c '.' defs::Definitions_c
     info::InterfaceContents_c
  { top.ast = (toString(g.lexeme), imps.ast, defs.ast, info.ast); }



nonterminal Imports_c with ast<[String]>;
nonterminal Imports_List_c with ast<[String]>;

concrete productions top::Imports_c
|
  { top.ast = []; }
| lst::Imports_List_c
  { top.ast = lst.ast; }


concrete productions top::Imports_List_c
| g::Qname_colon_t
  { top.ast = [toString(g.lexeme)]; }
| g::Qname_colon_t ',' rest::Imports_List_c
  { top.ast = toString(g.lexeme)::rest.ast; }



nonterminal Definitions_c with ast<[DefinitionElement]>;
nonterminal Definitions_List_c with ast<[DefinitionElement]>;
nonterminal RelNames_c with ast<[(String, AbellaType)]>;
nonterminal Clauses_c with ast<[(Metaterm, Maybe<Metaterm>)]>;

concrete productions top::Definitions_c
| 
  { top.ast = []; }
| '$Def' lst::Definitions_List_c
  { top.ast = lst.ast; }


concrete productions top::Definitions_List_c
| rels::RelNames_c '&' clauses::Clauses_c '.'
  { top.ast = [defineElement(rels.ast, clauses.ast)]; }
| rels::RelNames_c '&' clauses::Clauses_c '.' '$Def' rest::Definitions_List_c
  { top.ast = defineElement(rels.ast, clauses.ast)::rest.ast; }


concrete productions top::RelNames_c
| name::Qname_colon_t ':' ty::Ty_c
  { top.ast = [(name.lexeme, ty.ast)]; }
| name::Qname_colon_t ':' ty::Ty_c ',' rest::RelNames_c
  { top.ast = (name.lexeme, ty.ast)::rest.ast; }


concrete productions top::Clauses_c
| hd::Metaterm_c ',' body::Metaterm_c
  { top.ast = [(hd.ast, just(body.ast))]; }
| hd::Metaterm_c
  { top.ast = [(hd.ast, nothing())]; }
| hd::Metaterm_c ',' body::Metaterm_c ';' rest::Clauses_c
  { top.ast = (hd.ast, just(body.ast))::rest.ast; }
| hd::Metaterm_c ';' rest::Clauses_c
  { top.ast = (hd.ast, nothing())::rest.ast; }



nonterminal InterfaceContents_c with ast<[ParsedElement]>;
nonterminal InterfaceContents_List_c with ast<[ParsedElement]>;
nonterminal InterfaceElement_c with ast<ParsedElement>;

concrete productions top::InterfaceContents_c
|
  { top.ast = []; }
| lst::InterfaceContents_List_c
  { top.ast = lst.ast; }


concrete productions top::InterfaceContents_List_c
| elem::InterfaceElement_c '.'
  { top.ast = [elem.ast]; }
| elem::InterfaceElement_c '.' rest::InterfaceContents_List_c
  { top.ast = elem.ast::rest.ast; }


concrete productions top::InterfaceElement_c
--Extensible Theorems
| thms::ExtensibleTheorems_c
  { top.ast = extensibleMutualTheoremGroup(thms.ast, []); }
--Non-Extensible Theorem
| name::Qname_colon_t '&' stmt::Metaterm_c
  { top.ast = nonextensibleTheorem(name.lexeme, stmt.ast); }
--Split
| '$Spl' toSplit::Qname_colon_t names::NameList_c
  { top.ast = splitElement(toSplit.lexeme, names.ast); }



nonterminal ExtensibleTheorems_c with ast<[(String, Metaterm, String)]>;

concrete productions top::ExtensibleTheorems_c
| name::Qname_colon_t '&' stmt::Metaterm_c '&' tree::Id_t
  { top.ast = [(name.lexeme, stmt.ast, tree.lexeme)]; }
| name::Qname_colon_t '&' stmt::Metaterm_c '&' tree::Id_t ','
     rest::ExtensibleTheorems_c
  { top.ast = (name.lexeme, stmt.ast, tree.lexeme)::rest.ast; }



nonterminal NameList_c with ast<[String]>;

concrete productions top::NameList_c
| name::Id_t
  { top.ast = [name.lexeme]; }
| name::Id_t ',' rest::NameList_c
  { top.ast = name.lexeme::rest.ast; }

