grammar silver:compiler:refactor;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:flow:syntax;

imports silver:compiler:modification:let_fix;
imports silver:compiler:modification:lambda_fn;
imports silver:compiler:modification:collection;
imports silver:compiler:modification:primitivepattern;
imports silver:compiler:modification:ffi;
imports silver:compiler:modification:copper;
imports silver:compiler:modification:defaultattr;
imports silver:compiler:modification:list;
imports silver:compiler:modification:copper_mda;

imports silver:rewrite;

synthesized attribute transformed::Root occurs on Root;

aspect production root
top::Root ::= _ _ _ _
{
  top.transformed = rewriteWith(topDown(try(top.transforms)), new(top)).fromJust;
}

monoid attribute transforms::Strategy with fail(), choice;

attribute transforms occurs on
  Root, AGDcls, AGDcl, 
  ProductionModifiers, ProductionModifierList, TerminalModifiers,
  AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS, AspectRHSElem,
  ClassBody, ClassBodyItem,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr,
  FunctionSignature, FunctionLHS,
  InstanceBody, InstanceBodyItem,
  ModuleStmts, ModuleStmt, ImportStmt, ImportStmts, ModuleExpr, ModuleName, NameList, WithElems, WithElem, Module, ModuleExportedDefs,
  Name,
  NTDeclQualifiers, NonterminalModifiers, NonterminalModifierList,
  ProductionBody, ProductionStmts, ProductionStmt, DefLHS, ForwardInhs, ForwardInh, ForwardLHSExpr,
  ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
  QName, QNameType, QNameAttrOccur, GrammarDcl,
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
  Root, AGDcls, AGDcl, 
  ProductionModifiers, ProductionModifierList, TerminalModifiers,
  AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS, AspectRHS, AspectRHSElem,
  ClassBody, ClassBodyItem,
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr,
  FunctionSignature, FunctionLHS,
  InstanceBody, InstanceBodyItem,
  ModuleStmts, ModuleStmt, ImportStmt, ImportStmts, ModuleExpr, ModuleName, NameList, WithElems, WithElem, Module, ModuleExportedDefs,
  Name,
  NTDeclQualifiers, NonterminalModifiers, NonterminalModifierList,
  ProductionBody, ProductionStmts, ProductionStmt, DefLHS, ForwardInhs, ForwardInh, ForwardLHSExpr,
  ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
  QName, QNameType, QNameAttrOccur, GrammarDcl,
  FlowSpecs, FlowSpec, FlowSpecId, FlowSpecInhs, FlowSpecInh, NtList, NtName,
  ConstraintList, Constraint, KindExpr, TypeExpr,
  Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs, NamedTypeExprs,
  NameOrBOperator,
  LexerClassModifiers, ParserComponents, ParserComponentModifiers, ParserComponentModifier,
  TerminalPrefix, TerminalPrefixItems, TerminalPrefixItem,
  TermList, TermPrecs, TermPrecList, LexerClasses, LexerClassList,
  AspectDefaultProductionSignature, FFIDefs, FFIDef,
  AssignExpr, PrimPatterns, PrimPattern, VarBinders, VarBinder;
