grammar silver:compiler:extension:abella_compilation:encoding;


attribute
   encodingEnv_up,
   treeTerm_up<silver:compiler:extension:abella_compilation:abella:TermList>,
   nodetreeTerm_up<Term>, top_up, childNames
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

  top.childNames = rhs.childNames;
}


attribute
   encodingEnv_up, nodetreeTerm_up<(Term ::= Term)>, top_up
occurs on ProductionLHS;

aspect production productionLHS
top::ProductionLHS ::= id::Name '::' t::TypeExpr
{
  local treename::String = capitalize(id.name);
  local treeterm::Term = varTerm(treename, genInt());
  local treenode::String = treename ++ "$Node";
  local treenodeterm::Term = varTerm(treenode, genInt());
  top.encodingEnv_up = [(id.name, (treeterm, new(treenodeterm)))];
  top.nodetreeTerm_up =
      \ x::Term ->
        buildApplication(
           nameTerm(nodeTreeConstructorName(t.typerep.abellaType)),
           [treenodeterm, x]);
  top.top_up =
      (treeterm, treenodeterm, t.typerep.abellaType);
}


attribute
   encodingEnv_up,
   treeTerm_up<silver:compiler:extension:abella_compilation:abella:TermList>,
   nodetreeTerm_up<Term>, childNames
occurs on ProductionRHS;

attribute
   encodingEnv_up, treeTerm_up<Term>,
   nodetreeTerm_up<Maybe<Term>>, childNames
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

  top.childNames = [];
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

  top.childNames = h.childNames ++ t.childNames;
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
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
      if tyIsNonterminal(t.typerep.abellaType)
      then just(buildApplication(
                   nameTerm(nodeTreeConstructorName(t.typerep.abellaType)),
                   [treenodeterm, childlistterm]))
      else nothing();

  top.args = [treeterm];

  top.childNames = [id.name];
}




attribute
   encodingEnv_up,
   treeTerm_up<silver:compiler:extension:abella_compilation:abella:TermList>,
   nodetreeTerm_up<Term>, top_up, childNames
occurs on AspectProductionSignature;

aspect production aspectProductionSignature
top::AspectProductionSignature ::= lhs::AspectProductionLHS '::=' rhs::AspectRHS 
{
  top.encodingEnv_up = lhs.encodingEnv_up ++ rhs.encodingEnv_up;
  top.treeTerm_up = rhs.treeTerm_up;
  top.nodetreeTerm_up = lhs.nodetreeTerm_up(rhs.nodetreeTerm_up);
  top.top_up = lhs.top_up;

  top.argLength = rhs.argLength;

  top.childNames = rhs.childNames;
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
           [treenodeterm, x]);
  top.top_up = (treeterm, treenodeterm, t.abellaType);
}


attribute
   encodingEnv_up,
   treeTerm_up<silver:compiler:extension:abella_compilation:abella:TermList>,
   nodetreeTerm_up<Term>, childNames
occurs on AspectRHS;

attribute
   encodingEnv_up, treeTerm_up<Term>,
   nodetreeTerm_up<Maybe<Term>>, childNames
occurs on AspectRHSElem;

aspect production aspectRHSElemNil
top::AspectRHS ::= 
{
  top.encodingEnv_up = [];
  top.treeTerm_up = nilTermList();
  top.nodetreeTerm_up = nilTerm();

  top.argLength = 0;

  top.childNames = [];
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

  top.childNames = h.childNames ++ t.childNames;
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

  top.childNames = [id.name];
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
  top.abellaType = t.typerep.abellaType;
}

