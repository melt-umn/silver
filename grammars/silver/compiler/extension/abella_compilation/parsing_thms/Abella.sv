grammar silver:compiler:extension:abella_compilation:parsing_thms;


nonterminal Metaterm_c with ast<Metaterm>;
nonterminal SubMetaterm_c with ast<Metaterm>;


concrete productions top::Metaterm_c
| t::Term_c
  {-This lets us overload `true` and `false` for use as metaterms and
    Silver Booleans.  If it is simply "true" or "false" where a
    metaterm is needed, that is the Abella metaterm; otherwise, it
    must be the Silver Boolean.-}
  { top.ast = case t.ast of
              | trueTerm() -> trueMetaterm()
              | falseTerm() -> falseMetaterm()
              | _ -> termMetaterm(t.ast)
              end; }
| s::SubMetaterm_c
  { top.ast = s.ast; }


concrete productions top::SubMetaterm_c
| t1::Term_c '=' t2::Term_c
  { top.ast = eqMetaterm(t1.ast, t2.ast); }
| b::Binder_c bl::BindingList_c ',' m::Metaterm_c
  { top.ast = bindingMetaterm(b.ast, bl.ast, m.ast); }
| m1::Metaterm_c '->' m2::Metaterm_c
  { top.ast = impliesMetaterm(m1.ast, m2.ast); }
| m1::Metaterm_c '\/' m2::Metaterm_c
  { top.ast = orMetaterm(m1.ast, m2.ast); }
| m1::Metaterm_c '/\' m2::Metaterm_c
  { top.ast = andMetaterm(m1.ast, m2.ast); }
| '(' m::SubMetaterm_c ')'
  { top.ast = m.ast; }
--Things for Silver (for special relations)
| a::AttrAccess_t '=' val::Term_c
  { local pieces::(String, String) = split_AttrAccess_t(a);
    top.ast = attrAccessMetaterm(pieces.1, pieces.2, val.ast); }
| a::AttrAccess_t '=' '<' 'no' 'value' '>'
  { local pieces::(String, String) = split_AttrAccess_t(a);
    top.ast = attrAccessEmptyMetaterm(pieces.1, pieces.2); }
| t1::Term_c '+' t2::Term_c '=' t3::Term_c
  { top.ast = plusMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '-' t2::Term_c '=' t3::Term_c
  { top.ast = minusMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '*' t2::Term_c '=' t3::Term_c
  { top.ast = multiplyMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '/' t2::Term_c '=' t3::Term_c
  { top.ast = divideMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c 'mod' t2::Term_c '=' t3::Term_c
  { top.ast = modulusMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '<' t2::Term_c '=' t3::Term_c
  { top.ast = lessMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '<=' t2::Term_c '=' t3::Term_c
  { top.ast = lessEqMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '>' t2::Term_c '=' t3::Term_c
  { top.ast = greaterMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '>=' t2::Term_c '=' t3::Term_c
  { top.ast = greaterEqMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '++' t2::Term_c '=' t3::Term_c
  { top.ast = appendMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '||' t2::Term_c '=' t3::Term_c
  { top.ast = orBoolMetaterm(t1.ast, t2.ast, t3.ast); }
| t1::Term_c '&&' t2::Term_c '=' t3::Term_c
  { top.ast = andBoolMetaterm(t1.ast, t2.ast, t3.ast); }
| '!' t1::Term_c '=' t2::Term_c
  { top.ast = notBoolMetaterm(t1.ast, t2.ast); }
--Symmetry for the same
| val::Term_c '=' a::AttrAccess_t
  { local pieces::(String, String) = split_AttrAccess_t(a);
    top.ast = attrAccessMetaterm(pieces.1, pieces.2, val.ast); }
| t3::Term_c '=' t1::Term_c '+' t2::Term_c
  { top.ast = plusMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '-' t2::Term_c
  { top.ast = minusMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '*' t2::Term_c
  { top.ast = multiplyMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '/' t2::Term_c
  { top.ast = divideMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c 'mod' t2::Term_c
  { top.ast = modulusMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '<' t2::Term_c
  { top.ast = lessMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '<=' t2::Term_c
  { top.ast = lessEqMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '>' t2::Term_c
  { top.ast = greaterMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '>=' t2::Term_c
  { top.ast = greaterEqMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '++' t2::Term_c
  { top.ast = appendMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '||' t2::Term_c
  { top.ast = orBoolMetaterm(t1.ast, t2.ast, t3.ast); }
| t3::Term_c '=' t1::Term_c '&&' t2::Term_c
  { top.ast = andBoolMetaterm(t1.ast, t2.ast, t3.ast); }
| t2::Term_c '=' '!' t1::Term_c
  { top.ast = notBoolMetaterm(t1.ast, t2.ast); }





nonterminal Term_c with ast<Term>;
nonterminal MidTerm_c with ast<Term>;
nonterminal Exp_c with ast<Term>;
nonterminal ExpList_c with ast<TermList>;
nonterminal PairBody_c with ast<PairContents>;
nonterminal ListBody_c with ast<ListContents>;
nonterminal PAId_c with ast<Term>;


concrete productions top::Term_c
| t1::MidTerm_c '::' t2::Term_c
  { top.ast = consTerm(t1.ast, t2.ast); }
| e::MidTerm_c
  { top.ast = e.ast; }


concrete productions top::MidTerm_c
| e::Exp_c el::ExpList_c
  { top.ast = disambiguateApplicationTerm(e.ast, el.ast); }
| e::Exp_c '(' ')'
  { top.ast = emptySilverApplicationTerm(e.ast); }
| e::Exp_c
  { top.ast = e.ast; }


concrete productions top::Exp_c
| '(' t::Term_c ')'
  { top.ast = t.ast; }
| p::PAId_c
  { top.ast = p.ast; }
| 'nil'
  { top.ast = nilTerm(); }
--New for Silver:
| i::Number_t
  { top.ast = intTerm(toInteger(i.lexeme)); }
| i::SilverNegativeInteger_t
  { top.ast = intTerm(toInteger(i.lexeme)); }
| s::SilverString_t
  { top.ast = stringTerm(unescapeString(substring(1, length(s.lexeme)-1, s.lexeme))); }
| 'true'
  { top.ast = trueTerm(); }
| 'false'
  { top.ast = falseTerm(); }
| '(' pairBody::PairBody_c ')'
  { top.ast = pairTerm(pairBody.ast); }
| '[' listBody::ListBody_c ']'
  { top.ast = listTerm(listBody.ast); }
| '[' ']'
  { top.ast = listTerm(emptyListContents()); }


concrete productions top::ExpList_c
| e::Exp_c el::ExpList_c
  { top.ast = consTermList(e.ast, el.ast); }
| e::Exp_c
  { top.ast = singleTermList(e.ast); }


concrete productions top::PairBody_c
| t1::Term_c ',' t2::Term_c
  { top.ast = addPairContents(t1.ast, singlePairContents(t2.ast)); }
| t::Term_c ',' rest::PairBody_c
  { top.ast = addPairContents(t.ast, rest.ast); }


concrete productions top::ListBody_c
| t::Exp_c
  { top.ast = addListContents(t.ast, emptyListContents()); }
| t::Exp_c ',' rest::ListBody_c
  { top.ast = addListContents(t.ast, rest.ast); }


concrete productions top::PAId_c
| l::Id_t
  { top.ast = nameTerm(l.lexeme); }
| '(' l::Id_t ':' t::Ty_c ')'
  { top.ast = nameTerm(l.lexeme); }





nonterminal PTy_c with ast<AbellaType>;
nonterminal ATy_c with ast<AbellaType>;
nonterminal Ty_c with ast<AbellaType>;


concrete productions top::PTy_c
| i::Id_t
  { top.ast = nameAbellaType(i.lexeme); }
| '(' t::Ty_c ')'
  { top.ast = t.ast; }


concrete productions top::ATy_c
| i::Id_t
  { top.ast = nameAbellaType(i.lexeme); }
| a::ATy_c p::PTy_c
  { top.ast = functorAbellaType(a.ast, p.ast); }


concrete productions top::Ty_c
| a::ATy_c
  { top.ast = a.ast; }
| t1::Ty_c '->' t2::Ty_c
  { top.ast = arrowAbellaType(t1.ast, t2.ast); }
| '(' t::Ty_c ')'
  { top.ast = t.ast; }



nonterminal Binder_c with ast<Binder>;
nonterminal BindingList_c with ast<[Pair<String Maybe<AbellaType>>]>;
nonterminal BindingOne_c with ast<[Pair<String Maybe<AbellaType>>]>;
nonterminal BindingVars_c with ast<[String]>;


concrete productions top::Binder_c
| 'forall'
  { top.ast = forallBinder(); }
| 'exists'
  { top.ast = existsBinder(); }
| 'nabla'
  { top.ast = nablaBinder(); }


concrete productions top::BindingList_c
| b::BindingOne_c
  { top.ast = b.ast; }
| b::BindingOne_c rest::BindingList_c
  { top.ast = b.ast ++ rest.ast; }


concrete productions top::BindingOne_c
| i::Id_t
  { top.ast = [pair(i.lexeme, nothing())]; }
| '(' bv::BindingVars_c ':' t::Ty_c ')'
  { top.ast = map(\x::String -> pair(x, just(t.ast)), bv.ast); }


concrete productions top::BindingVars_c
| i::Id_t
  { top.ast = [i.lexeme]; }
| i::Id_t rest::BindingVars_c
  { top.ast = i.lexeme::rest.ast; }


--split an AttrAccess_t into the tree name and attr name
function split_AttrAccess_t
(String, String) ::= a::AttrAccess_t
{
  local lexeme::String = a.lexeme;
  local dotLoc::Integer = indexOf(".", a.lexeme);
  return (substring(0, dotLoc, lexeme),
          substring(dotLoc + 1, length(lexeme), lexeme));
}

