grammar silver:compiler:extension:abella_compilation:encoding;


attribute
   encodingEnv_up,
   treeTerm_up<silver:compiler:extension:abella_compilation:abella:TermList>,
   nodetreeTerm_up<Term>, top_up
occurs on ProductionSignature;

synthesized attribute argLength::Integer occurs on
   ProductionSignature, ProductionRHS,
   AspectProductionSignature, AspectRHS, FunctionSignature;

aspect production productionSignature
top::ProductionSignature ::= cl::ConstraintList '=>' lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  top.encodingEnv_up =
      case cl of
      | nilConstraint() ->
        lhs.encodingEnv_up ++ rhs.encodingEnv_up
      | _ -> error("Cannot handle type classes")
      end;
  top.treeTerm_up = rhs.treeTerm_up;
  top.nodetreeTerm_up = lhs.nodetreeTerm_up(rhs.nodetreeTerm_up);
  top.top_up = lhs.top_up;

  top.argLength = rhs.argLength;
}


attribute
   encodingEnv_up, nodetreeTerm_up<(Term ::= Term)>, top_up
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
  local treeterm::Term = varTerm(treename, genInt());
  local treenode::String = treename ++ "$Node";
  local treenodeterm::Term = varTerm(treenode, genInt());
  top.encodingEnv_up = [(id.name, (treeterm, new(treenodeterm)))];
  top.nodetreeTerm_up =
      \ x::Term ->
        buildApplication(
           nameTerm(nodeTreeConstructorName(newtSig.typerep.abellaType)),
           [treenodeterm, x]);
  top.top_up =
      (treeterm, treenodeterm, newtSig.typerep.abellaType);
}


attribute
   encodingEnv_up,
   treeTerm_up<silver:compiler:extension:abella_compilation:abella:TermList>,
   nodetreeTerm_up<Term>
occurs on ProductionRHS;

attribute
   encodingEnv_up, treeTerm_up<Term>,
   nodetreeTerm_up<Maybe<Term>>
occurs on ProductionRHSElem;

aspect production productionRHSNil
top::ProductionRHS ::=
{
  top.encodingEnv_up = [];
  top.treeTerm_up = nilTermList();
  top.nodetreeTerm_up = nilTerm();

  top.argLength = 0;

  top.functionType =
      arrowAbellaType(top.functionResultType, nameAbellaType("prop"));

  top.args = [];
}

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.encodingEnv_up = h.encodingEnv_up ++ t.encodingEnv_up;
  top.treeTerm_up =
      consTermList(h.treeTerm_up, t.treeTerm_up);
  top.nodetreeTerm_up =
      case h.nodetreeTerm_up of
      | just(x) -> consTerm(x, t.nodetreeTerm_up)
      | nothing() -> t.nodetreeTerm_up
      end;

  top.argLength = 1 + t.argLength;

  t.functionResultType = top.functionResultType;
  top.functionType = arrowAbellaType(h.abellaType, t.functionType);

  top.args = h.args ++ t.args;
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
  local treename::String = capitalize(id.name);
  local treeterm::Term = varTerm(treename, genInt());
  local treenode::String = treename ++ "$Node";
  local treenodeterm::Term = varTerm(treenode, genInt());
  local childlist::String = treename ++ "$CL";
  local childlistterm::Term = varTerm(childlist, genInt());
  top.encodingEnv_up = [(id.name, (treeterm, new(treenodeterm)))];
  top.treeTerm_up = treeterm;
  top.nodetreeTerm_up =
      if tyIsNonterminal(newtSig.typerep.abellaType)
      then just(buildApplication(
                   nameTerm(nodeTreeConstructorName(newtSig.typerep.abellaType)),
                   [treenodeterm, childlistterm]))
      else nothing();

  top.args = [treeterm];
}




attribute
   encodingEnv_up,
   treeTerm_up<silver:compiler:extension:abella_compilation:abella:TermList>,
   nodetreeTerm_up<Term>, top_up
occurs on AspectProductionSignature;

aspect production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS 
{
  top.encodingEnv_up = lhs.encodingEnv_up ++ rhs.encodingEnv_up;
  top.treeTerm_up = rhs.treeTerm_up;
  top.nodetreeTerm_up = lhs.nodetreeTerm_up(rhs.nodetreeTerm_up);
  top.top_up = lhs.top_up;

  top.argLength = rhs.argLength;
}


attribute
   encodingEnv_up, nodetreeTerm_up<(Term ::= Term)>, top_up
occurs on AspectProductionLHS;

aspect production aspectProductionLHSFull
top::AspectProductionLHS ::= id::Name t::Type
{
  local treename::String = capitalize(id.name);
  local treeterm::Term = varTerm(treename, genInt());
  local treenode::String = treename ++ "$Node";
  local treenodeterm::Term = varTerm(treenode, genInt());
  top.encodingEnv_up = [(id.name, (treeterm, new(treenodeterm)))];
  top.nodetreeTerm_up =
      \ x::Term ->
        buildApplication(
           nameTerm(nodeTreeConstructorName(t.abellaType)),
           [x]);
  top.top_up = (treeterm, treenodeterm, t.abellaType);
}


attribute
   encodingEnv_up,
   treeTerm_up<silver:compiler:extension:abella_compilation:abella:TermList>,
   nodetreeTerm_up<Term>
occurs on AspectRHS;

attribute
   encodingEnv_up, treeTerm_up<Term>,
   nodetreeTerm_up<Maybe<Term>>
occurs on AspectRHSElem;

aspect production aspectRHSElemNil
top::AspectRHS ::= 
{
  top.encodingEnv_up = [];
  top.treeTerm_up = nilTermList();
  top.nodetreeTerm_up = nilTerm();

  top.argLength = 0;
}

aspect production aspectRHSElemCons
top::AspectRHS ::= h::AspectRHSElem t::AspectRHS
{
  top.encodingEnv_up = h.encodingEnv_up ++ t.encodingEnv_up;
  top.treeTerm_up =
      consTermList(h.treeTerm_up, t.treeTerm_up);
  top.nodetreeTerm_up =
      case h.nodetreeTerm_up of
      | just(x) -> consTerm(x, t.nodetreeTerm_up)
      | nothing() -> t.nodetreeTerm_up
      end;

  top.argLength = 1 + t.argLength;
}

aspect production aspectRHSElemFull
top::AspectRHSElem ::= id::Name t::Type
{
  local treename::String = capitalize(id.name);
  local treeterm::Term = varTerm(treename, genInt());
  local treenode::String = treename ++ "$Node";
  local treenodeterm::Term = varTerm(treenode, genInt());
  local childlist::String = treename ++ "$CL";
  local childlistterm::Term = varTerm(childlist, genInt());
  top.encodingEnv_up = [(id.name, (treeterm, new(treenodeterm)))];
  top.treeTerm_up = treeterm;
  top.nodetreeTerm_up =
      if tyIsNonterminal(t.abellaType)
      then just(buildApplication(
                   nameTerm(nodeTreeConstructorName(t.abellaType)),
                   [treenodeterm, childlistterm]))
      else nothing();
}




inherited attribute functionResultType::AbellaType occurs on
   ProductionRHS;
synthesized attribute functionType::AbellaType occurs on
   FunctionSignature, ProductionRHS;
synthesized attribute args::[Term] occurs on
   FunctionSignature, ProductionRHS, ProductionRHSElem;

attribute
   encodingEnv_up
occurs on FunctionSignature;

aspect production functionSignature
top::FunctionSignature ::= cl::ConstraintList '=>' lhs::FunctionLHS '::=' rhs::ProductionRHS 
{
  top.encodingEnv_up =
      case cl of
      | nilConstraint() -> rhs.encodingEnv_up
      | _ -> error("Cannot handle type classes")
      end;

  top.argLength = rhs.argLength;

  rhs.functionResultType = lhs.abellaType;
  top.functionType = rhs.functionType;

  top.args = rhs.args;
}


attribute
   abellaType
occurs on FunctionLHS;

aspect production functionLHS
top::FunctionLHS ::= t::TypeExpr
{
  --Missing equation in host
  local newt::TypeExpr = t;
  newt.onNt = error("Is onNt needed? (productionRHSElem)");
  newt.grammarName = top.grammarName;
  newt.env = top.env;
  newt.flowEnv = top.flowEnv;
  newt.config = top.config;
  --
  top.abellaType = newt.typerep.abellaType;
}

