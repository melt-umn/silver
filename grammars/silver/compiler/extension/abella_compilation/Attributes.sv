grammar silver:compiler:extension:abella_compilation;


--Whether parentheses are needed in printing something
synthesized attribute isAtomic::Boolean;



--[(production name, production type)]
monoid attribute prods::[(String, AbellaType)] with [], ++;
propagate prods on AGDcl, AGDcls, Grammar, Root;

--[nonterminal name]
monoid attribute nonterminals::[String] with [], ++;
propagate nonterminals on AGDcl, AGDcls, Grammar, Root;

--[attribute name]
monoid attribute attrs::[String] with [], ++;
propagate attrs on AGDcl, AGDcls, Grammar, Root;

--[(attribute name, [(nonterminal name, attribute type)])]
monoid attribute attrOccurrences::[(String, [(String, AbellaType)])]
   with [], ++;
propagate attrOccurrences on AGDcl, AGDcls, Grammar, Root;

--[(local name, [(production name, local type)])]
monoid attribute localAttrs::[(String, [(String, AbellaType)])]
   with [], combineAssociations(_, _);
propagate localAttrs on AGDcl, AGDcls, Grammar, Root;

function combineAssociations
[(String, [a])] ::= l1::[(String, [a])] l2::[(String, [a])]
{
  return
     case l1 of
     | [] -> l2
     | (s, lst)::t ->
       case findAssociated(s, l2) of
       | nothing() -> combineAssociations(t, (s, lst)::l2)
       | just(lst2) ->
         combineAssociations(t,
            replaceAssociated(s, lst ++ lst2, l2).fromJust)
       end
     end;
}

--[attribute name]
monoid attribute inheritedAttrs::[String] with [], ++;
propagate inheritedAttrs on AGDcl, AGDcls, Grammar, Root;

--[(attribute name, [nonterminal name])]
--Attributes which need to be defined by the nonterminal but
--   which don't occur on the nonterminal
monoid attribute associatedAttrs::[(String, [String])]
   with [], ++;
propagate associatedAttrs on AGDcl, AGDcls, Grammar, Root;

