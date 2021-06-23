grammar silver:compiler:extension:abella_compilation;


--Whether parentheses are possibly needed in printing something
synthesized attribute isAtomic::Boolean;
--Because we print a lot of sequences of conjunctions
synthesized attribute isAnd::Boolean;



--[(production name, production type)]
monoid attribute prods::[(String, AbellaType)] with [], ++;
propagate prods on AGDcl, AGDcls, Grammar, Root, RootSpec;

--[nonterminal name]
monoid attribute nonterminals::[String] with [], ++;
propagate nonterminals on AGDcl, AGDcls, Grammar, Root, RootSpec;

--[attribute name]
monoid attribute attrs::[String] with [], ++;
propagate attrs on AGDcl, AGDcls, Grammar, Root, RootSpec;

--[(attribute name, [(nonterminal name, attribute type)])]
monoid attribute attrOccurrences::[(String, [(String, AbellaType)])]
   with [], ++;
propagate attrOccurrences on AGDcl, AGDcls, Grammar, Root, RootSpec;

--[(local name, [(production name, local type)])]
monoid attribute localAttrs::[(String, [(String, AbellaType)])]
   with [], combineAssociations(_, _);
propagate localAttrs on AGDcl, AGDcls, Grammar, Root, RootSpec,
             ProductionBody, ProductionStmts, ProductionStmt
   excluding aspectDefaultProduction, aspectFunctionDcl,
             functionDclFFI, functionDcl;

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
propagate inheritedAttrs on AGDcl, AGDcls, Grammar, Root, RootSpec;


--[( attribute, top nonterminal type, production,
--   head term (rel tree nodetree), [clause bodies] )]
monoid attribute attrEqInfo::[(String, AbellaType, String,
                               Term, [[Metaterm]])]
   with [], ++;
propagate attrEqInfo on AGDcl, AGDcls, Grammar, Root, RootSpec,
             ProductionBody, ProductionStmts, ProductionStmt
   excluding aspectDefaultProduction, aspectFunctionDcl,
             functionDclFFI, functionDcl, ifElseStmt, blockStmt;


--[(local, production, [clause bodies])]
monoid attribute localAttrEqInfo::[(String, [[Metaterm]])]
   with [], ++;
propagate localAttrEqInfo on ProductionStmt, ProductionStmts,
             ProductionBody;
--
monoid attribute localAttrDefs::[Definition] with [], ++;
propagate localAttrDefs on AGDcl, AGDcls, Grammar, Root
   excluding aspectDefaultProduction, aspectFunctionDcl,
             functionDclFFI, functionDcl;


--[(true if is return statement, encoded expr)]
--answer terms are ignored for anything but return
monoid attribute funRelInfo::[(Boolean, [([Metaterm], Term)])]
   with [], ++;
propagate funRelInfo on ProductionStmt, ProductionStmts, ProductionBody;
--Can't just build definitions because functions could be mutually
--   recursive, and thus all need to be defined mutually
--We could do analysis to define them separately if necessary.
--[(function name, encoded type, defining clauses)]
monoid attribute funRelClauses::[(String, AbellaType, [DefClause])]
   with [], ++;
propagate funRelClauses on Grammar, Root, AGDcls, AGDcl;



{-
  For metaterms, the inner lists are conceptually and-ed together.  We
  don't actually and them together so it is easier to work with later.

  [([encoding metaterms], result)]
-}
synthesized attribute encodedExpr::[([Metaterm], Term)];
--Encoding arguments for application
synthesized attribute encodedArgs::[([Metaterm], [Term])];

--[(Silver name, (tree structure, tree node))]
--If it isn't a tree, we won't ever need the node, but it is easier to
--   include it for all than separate it out
autocopy attribute encodingEnv::[(String, (Term, Term))];
--
synthesized attribute encodingEnv_up::[(String, (Term, Term))];

--(tree, node, tree type, current production name) for the root
autocopy attribute top::(Term, Term, AbellaType, String);
--
synthesized attribute top_up::(Term, Term, AbellaType);

--Root tree of current production
inherited attribute treeTerm::Term;
synthesized attribute treeTerm_up<a>::a;

--Root node tree of current production
inherited attribute nodetreeTerm::Term;
synthesized attribute nodetreeTerm_up<a>::a;

