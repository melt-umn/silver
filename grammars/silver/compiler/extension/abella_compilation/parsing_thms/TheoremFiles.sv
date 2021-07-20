grammar silver:compiler:extension:abella_compilation:parsing_thms;

{-
  Parse a theorem file into a grammar name and a list of theorems.

  We probably also want to capture any definitions and splits.
  Definitions can all be entered at the beginning, but splits need to
  take place in the correct location in the order so the theorems they
  are splitting are defined before the split happens.
-}

parser theorem_file_parser::TheoremFile_c
{
  silver:compiler:extension:abella_compilation:parsing_thms;
}


synthesized attribute defs::[DefinitionElement];
synthesized attribute thms::[ParsedElement];

nonterminal TheoremFile_c with defs, thms;

concrete productions top::TheoremFile_c
| 'Grammar' g::Qname_colon_t extra::ProofWater_c thms::ThmFileContents_c
  { top.defs = thms.defs;
    top.thms = thms.thms; }



nonterminal ThmFileContents_c with defs, thms;
nonterminal ThmFileElement_c with defs, thms;

concrete productions top::ThmFileContents_c
| thm::ThmFileElement_c  extra::ProofWater_c
  { top.defs = thm.defs;
    top.thms = thm.thms; }
| thm::ThmFileElement_c  extra::ProofWater_c rest::ThmFileContents_c
  { top.defs = thm.defs ++ rest.defs;
    top.thms = thm.thms ++ rest.thms; }


concrete productions top::ThmFileElement_c
| thm::TheoremProof_c
  { top.defs = [];
    top.thms = thm.thms; }
| def::ThmFileDef_c
  { top.defs = def.defs;
    top.thms = []; }



nonterminal TheoremProof_c with thms;
nonterminal QnameList_c with ast<[String]>;
nonterminal ExtTheoremList_c with ast<[(String, Metaterm, String)]>;
nonterminal IdList_c with ast<[String]>;

concrete productions top::TheoremProof_c
| 'Theorem' name::Id_t ':' stmt::Metaterm_c '.'
  { top.thms = [nonextensibleTheorem(name.lexeme, stmt.ast)]; }
| 'Prove' names::QnameList_c '.'
  { top.thms = [extensibleMutualTheoremGroup([], names.ast)]; }
| 'Extensible_Theorem' newthms::ExtTheoremList_c '.'
  { top.thms = [extensibleMutualTheoremGroup(newthms.ast, [])]; }
| 'Extensible_Theorem' newthms::ExtTheoremList_c 'with' oldthms::QnameList_c '.'
  { top.thms = [extensibleMutualTheoremGroup(newthms.ast, oldthms.ast)]; }
| 'Split' name::Qname_colon_t 'as' names::IdList_c '.'
  { top.thms = [splitElement(name.lexeme, names.ast)]; }


--The metaterms from here will be ignored, so we just need a placeholder
--The tree names ("") will also be ignored
concrete productions top::QnameList_c  
| name::Qname_colon_t
  { top.ast = [name.lexeme]; }
| name::Qname_colon_t ',' rest::QnameList_c
  { top.ast = name.lexeme::rest.ast; }


concrete productions top::ExtTheoremList_c
| name::Id_t ':' stmt::Metaterm_c 'on' tree::Id_t
  { top.ast = [(name.lexeme, stmt.ast, tree.lexeme)]; }
| name::Id_t ':' stmt::Metaterm_c 'on' tree::Id_t ',' rest::ExtTheoremList_c
  { top.ast = (name.lexeme, stmt.ast, tree.lexeme)::rest.ast; }


concrete productions top::IdList_c
| name::Id_t
  { top.ast = [name.lexeme]; }
| name::Id_t ',' rest::IdList_c
  { top.ast = name.lexeme::rest.ast; }



nonterminal ThmFileDef_c with defs;
nonterminal IdTy_c with ast<Pair<String AbellaType>>;
nonterminal IdTys_c with ast<[Pair<String AbellaType>]>;
nonterminal Defs_c with ast<[(Metaterm, Maybe<Metaterm>)]>;
nonterminal Def_c with ast<(Metaterm, Maybe<Metaterm>)>;

concrete productions top::ThmFileDef_c
| 'Define' x::IdTys_c 'by' o::OptSemi_t d::Defs_c '.'
  { top.defs = [defineElement(x.ast, d.ast)]; }


concrete productions top::IdTy_c
| i::Id_t ':' t::Ty_c
  { top.ast = pair(i.lexeme, t.ast); }


concrete productions top::IdTys_c
| i::IdTy_c ',' rest::IdTys_c
  { top.ast = i.ast::rest.ast;}
| i::IdTy_c
  { top.ast = [i.ast]; }


concrete productions top::Defs_c
| d::Def_c ';' rest::Defs_c
  { top.ast = d.ast::rest.ast; }
| d::Def_c
  { top.ast = [d.ast]; }


concrete productions top::Def_c
| m::Metaterm_c
  { top.ast = (m.ast, nothing()); }
| h::Metaterm_c ':=' b::Metaterm_c
  { top.ast = (h.ast, just(b.ast)); }



{-
  The idea here is to capture everything other than the theorems.
  Since we don't care about the proofs, we don't need to capture their
  structure---we just need them to go away.  This lets us do that
  while also not missing any theorems.
-}
nonterminal ProofWater_c;

concrete productions top::ProofWater_c
|
  { }
| p::ProofWater_t rest::ProofWater_c
  { }

