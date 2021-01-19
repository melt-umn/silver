grammar silver:compiler:translation:java:core;

aspect production errorLength
top::Expr ::= e::Decorated Expr
{
  top.translation = error("Internal compiler error: translation not defined in the presence of errors");
  top.lazyTranslation = top.translation;
}

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.translation = s"Integer.valueOf(((common.StringCatter)${e.translation}).length())";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production toBooleanFunction
top::Expr ::= 'toBoolean' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                    | boolType() -> e.translation
                    | intType() -> s"Boolean.valueOf(${e.translation} != 0)"
                    | floatType() -> s"Boolean.valueOf(${e.translation} != 0.0)"
                    | stringType() -> s"Boolean.valueOf(${e.translation}.toString())"
                    | t -> error("INTERNAL ERROR: no toBoolean translation for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production toIntegerFunction
top::Expr ::= 'toInteger' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                    | boolType() -> s"Integer.valueOf(${e.translation}? 1 : 0)"
                    | intType() -> e.translation
                    | floatType() -> s"Integer.valueOf(((Float)${e.translation}).intValue())"
                    | stringType() -> s"Integer.valueOf(${e.translation}.toString())"
                    | t -> error("INTERNAL ERROR: no toInteger translation for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.translation = case finalType(e) of
                    | boolType() -> s"Float.valueOf(${e.translation}? 1.0f : 0.0f)"
                    | intType() -> s"Float.valueOf(((Integer)${e.translation}).floatValue())"
                    | floatType() -> e.translation
                    | stringType() -> s"Float.valueOf(${e.translation}.toString())"
                    | t -> error("INTERNAL ERROR: no toFloat translation for type " ++ prettyType(t))
                    end;

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.translation = s"new common.StringCatter(String.valueOf(${e.translation}))";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production reifyFunctionLiteral
top::Expr ::= 'reify'
{
  local resultType::Type =
    case finalType(top).outputType of
    | appType(appType(nonterminalType("silver:core:Either", 2, _), stringType()), a) -> a
    | _ -> error("Unexpected final type for reify!")
    end;
  
  -- In the unusual case that we have skolems in the result type, we can't generalize them, but
  -- we also can't do any better, so leave the runtime result TypeRep unfreshened.
  -- There is a similar problem with lambdas.
  top.translation =
s"""(new common.NodeFactory<silver.core.NEither>() {
				@Override
				public final silver.core.NEither invoke(final common.OriginContext originCtx, final Object[] args, final Object[] namedArgs) {
					assert args != null && args.length == 1;
					assert namedArgs == null || namedArgs.length == 0;
					
${makeTyVarDecls(5, resultType.freeVariables)}
					common.TypeRep resultType = ${resultType.transTypeRep};
					
					return common.Reflection.reifyChecked((originCtx!=null)?originCtx.rulesAsSilverList():null, resultType, (silver.core.NAST)common.Util.demand(args[0]));
				}
				
				@Override
				public final common.TypeRep getType() {
${makeTyVarDecls(5, finalType(top).freeVariables)}
					return ${finalType(top).transTypeRep};
				}
	
				@Override
				public final String toString() {
					return "reify";
				}
			})""";
  
  top.lazyTranslation = top.translation;
}

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.translation = s"((${finalType(top).transType})" ++ wrapNewWithOT(top, s"${e.translation}.undecorate()") ++ ")";
  
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.translation = s"new ${makeTerminalName(t.typerep.typeName)}(${es.translation}, (silver.core.NLocation)${el.translation})";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}
