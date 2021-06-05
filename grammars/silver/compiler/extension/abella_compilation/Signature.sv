grammar silver:compiler:extension:abella_compilation;


attribute
   usedNames, encodingEnv_up, treeTerm_up<TermList>,
   nodetreeTerm_up<Term>, top_up
occurs on ProductionSignature;

aspect production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  top.usedNames =
      case cl of
      | nilConstraint() -> rhs.usedNames
      | _ -> error("Cannot handle type classes")
      end;
  top.encodingEnv_up = lhs.encodingEnv_up ++ rhs.encodingEnv_up;
  rhs.usedNames_down = lhs.usedNames;
  top.treeTerm_up = rhs.treeTerm_up;
  top.nodetreeTerm_up = lhs.nodetreeTerm_up(rhs.nodetreeTerm_up);
  top.top_up = lhs.top_up;
}


attribute
   encodingEnv_up, usedNames, nodetreeTerm_up<(Term ::= Term)>, top_up
occurs on ProductionLHS;

aspect production productionLHS
top::ProductionLHS ::= id::Name '::' t::TypeExpr
{
  --Missing equation in host
  local newtSig::TypeExpr = t;
  newtSig.onNt = error("Is onNt needed? (productionLHS)");
  newtSig.grammarName = top.grammarName;
  newtSig.env = top.env;
  newtSig.flowEnv = top.flowEnv;
  newtSig.config = top.config;
  --
  local treename::String = capitalize(id.name);
  local treenode::String = treename ++ "$Node";
  top.encodingEnv_up = [(id.name, (treename, treenode))];
  top.usedNames = [treename, treenode];
  top.nodetreeTerm_up =
      \ x::Term ->
        buildApplication(
           nameTerm(nodeTreeConstructorName(newtSig.typerep.abellaType)),
           [nameTerm(treenode), x]);
  top.top_up =
      (treename, treenode, newtSig.typerep.abellaType);
}


attribute
   encodingEnv_up, usedNames, usedNames_down, treeTerm_up<TermList>,
   nodetreeTerm_up<Term>
occurs on ProductionRHS;

attribute
   encodingEnv_up, usedNames, usedNames_down, treeTerm_up<Term>,
   nodetreeTerm_up<Maybe<Term>>
occurs on ProductionRHSElem;

aspect production productionRHSNil
top::ProductionRHS ::=
{
  top.encodingEnv_up = [];
  top.usedNames = top.usedNames_down;
  top.treeTerm_up = nilTermList();
  top.nodetreeTerm_up = nilTerm();
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.encodingEnv_up = h.encodingEnv_up ++ t.encodingEnv_up;
  h.usedNames_down = top.usedNames_down;
  t.usedNames_down = h.usedNames;
  top.usedNames = t.usedNames;
  top.treeTerm_up =
      consTermList(h.treeTerm_up, t.treeTerm_up);
  top.nodetreeTerm_up =
      case h.nodetreeTerm_up of
      | just(x) -> consTerm(x, t.nodetreeTerm_up)
      | nothing() -> t.nodetreeTerm_up
      end;
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  --Missing equation in host
  local newtSig::TypeExpr = t;
  newtSig.onNt = error("Is onNt needed? (productionRHSElem)");
  newtSig.grammarName = top.grammarName;
  newtSig.env = top.env;
  newtSig.flowEnv = top.flowEnv;
  newtSig.config = top.config;
  --
  local treename::String =
        makeUniqueNameFromBase(capitalize(id.name), top.usedNames_down);
  local treenode::String = treename ++ "$Node";
  local childlist::String = treename ++ "$CL";
  top.encodingEnv_up = [(id.name, (treename, treenode))];
  top.usedNames = treename::treenode::childlist::top.usedNames_down;
  top.treeTerm_up = nameTerm(treename);
  top.nodetreeTerm_up =
      if tyIsNonterminal(newtSig.typerep.abellaType)
      then just(buildApplication(
                   nameTerm(nodeTreeConstructorName(newtSig.typerep.abellaType)),
                   [nameTerm(treenode), nameTerm(childlist)]))
      else nothing();
}




attribute
   usedNames, encodingEnv_up, treeTerm_up<TermList>,
   nodetreeTerm_up<Term>, top_up
occurs on AspectProductionSignature;

aspect production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS 
{
  top.usedNames = rhs.usedNames;
  top.encodingEnv_up = lhs.encodingEnv_up ++ rhs.encodingEnv_up;
  rhs.usedNames_down = lhs.usedNames;
  top.treeTerm_up = rhs.treeTerm_up;
  top.nodetreeTerm_up = lhs.nodetreeTerm_up(rhs.nodetreeTerm_up);
  top.top_up = lhs.top_up;
}


attribute
   encodingEnv_up, usedNames, nodetreeTerm_up<(Term ::= Term)>, top_up
occurs on AspectProductionLHS;

aspect production aspectProductionLHSFull
top::AspectProductionLHS ::= id::Name t::Type
{
  local treename::String = capitalize(id.name);
  local treenode::String = treename ++ "$Node";
  top.encodingEnv_up = [(id.name, (treename, treenode))];
  top.usedNames = [treename, treenode];
  top.nodetreeTerm_up =
      \ x::Term ->
        buildApplication(
           nameTerm(nodeTreeConstructorName(t.abellaType)),
           [x]);
  top.top_up = (treename, treenode, t.abellaType);
}


attribute
   encodingEnv_up, usedNames, usedNames_down, treeTerm_up<TermList>,
   nodetreeTerm_up<Term>
occurs on AspectRHS;

attribute
   encodingEnv_up, usedNames, usedNames_down, treeTerm_up<Term>,
   nodetreeTerm_up<Maybe<Term>>
occurs on AspectRHSElem;

aspect production aspectRHSElemNil
top::AspectRHS ::= 
{
  top.encodingEnv_up = [];
  top.usedNames = top.usedNames_down;
  top.treeTerm_up = nilTermList();
  top.nodetreeTerm_up = nilTerm();
}

aspect production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  top.encodingEnv_up = h.encodingEnv_up ++ t.encodingEnv_up;
  h.usedNames_down = top.usedNames_down;
  t.usedNames_down = h.usedNames;
  top.usedNames = t.usedNames;
  top.treeTerm_up =
      consTermList(h.treeTerm_up, t.treeTerm_up);
  top.nodetreeTerm_up =
      case h.nodetreeTerm_up of
      | just(x) -> consTerm(x, t.nodetreeTerm_up)
      | nothing() -> t.nodetreeTerm_up
      end;
}

aspect production aspectRHSElemFull
top::AspectRHSElem ::= id::Name t::Type
{
  local treename::String =
        makeUniqueNameFromBase(capitalize(id.name), top.usedNames_down);
  local treenode::String = treename ++ "$Node";
  local childlist::String = treename ++ "$CL";
  top.encodingEnv_up = [(id.name, (treename, treenode))];
  top.usedNames = treename::treenode::childlist::top.usedNames_down;
  top.treeTerm_up = nameTerm(treename);
  top.nodetreeTerm_up =
      if tyIsNonterminal(t.abellaType)
      then just(buildApplication(
                   nameTerm(nodeTreeConstructorName(t.abellaType)),
                   [nameTerm(treenode), nameTerm(childlist)]))
      else nothing();
}

