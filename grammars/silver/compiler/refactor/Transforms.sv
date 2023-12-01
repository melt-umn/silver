grammar silver:compiler:refactor;

functor attribute transformed occurs on Root, AGDcls;
propagate transformed on Root;

aspect transformed on AGDcls of
| consAGDcls(h, t) -> consAGDcls(
    case h.transforms of
    | fail() -> new(h)
    | ts -> rewriteWith(allTopDown(ts), new(h)).fromJust
    end,
    t.transformed)
| nilAGDcls() -> nilAGDcls()
end;

monoid attribute transforms::Strategy with fail(), choice_;

attribute transforms occurs on
  AGDcl,
  ProductionModifiers, ProductionModifierList, TerminalModifiers,
  AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS, AspectRHSElem,
  ClassBody, ClassBodyItem,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr,
  FunctionSignature, FunctionLHS,
  InstanceBody, InstanceBodyItem,
  NTDeclQualifiers, NonterminalModifiers, NonterminalModifierList,
  ProductionBody, ProductionStmts, ProductionStmt, DefLHS, ForwardInhs, ForwardInh, ForwardLHSExpr,
  ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
  FlowSpecs, FlowSpec, FlowSpecId, FlowSpecInhs, FlowSpecInh, NtList, NtName,
  ConstraintList, Constraint, KindExpr, TypeExpr,
  Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs, NamedTypeExprs,
  NameOrBOperator,
  LexerClassModifiers, ParserComponents, ParserComponentModifiers, ParserComponentModifier,
  TerminalPrefix, TerminalPrefixItems, TerminalPrefixItem,
  TermList, TermPrecs, TermPrecList, LexerClasses, LexerClassList,
  AspectDefaultProductionSignature, FFIDefs, FFIDef,
  AssignExpr, PrimPatterns, PrimPattern, VarBinders, VarBinder;
propagate transforms on
  AGDcl, 
  ProductionModifiers, ProductionModifierList, TerminalModifiers,
  AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS, AspectRHSElem,
  ClassBody, ClassBodyItem,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr,
  FunctionSignature, FunctionLHS,
  InstanceBody, InstanceBodyItem,
  NTDeclQualifiers, NonterminalModifiers, NonterminalModifierList,
  ProductionBody, ProductionStmts, ProductionStmt, DefLHS, ForwardInhs, ForwardInh, ForwardLHSExpr,
  ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
  FlowSpecs, FlowSpec, FlowSpecId, FlowSpecInhs, FlowSpecInh, NtList, NtName,
  ConstraintList, Constraint, KindExpr, TypeExpr,
  Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs, NamedTypeExprs,
  NameOrBOperator,
  LexerClassModifiers, ParserComponents, ParserComponentModifiers, ParserComponentModifier,
  TerminalPrefix, TerminalPrefixItems, TerminalPrefixItem,
  TermList, TermPrecs, TermPrecList, LexerClasses, LexerClassList,
  AspectDefaultProductionSignature, FFIDefs, FFIDef,
  AssignExpr, PrimPatterns, PrimPattern, VarBinders, VarBinder;
