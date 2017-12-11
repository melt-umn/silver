
package silver.definition.concrete_syntax.ast;

// top::SyntaxLexerClassModifier ::= sub::[String] 
public final class PlexerClassSubmits extends silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier {

	public static final int i_sub = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_lexerClassSubmits;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PlexerClassSubmits(final Object c_sub) {
		super();
		this.child_sub = c_sub;

	}

	private Object child_sub;
	public final common.ConsCell getChild_sub() {
		return (common.ConsCell) (child_sub = common.Util.demand(child_sub));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sub: return getChild_sub();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sub: return child_sub;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
	}

	@Override
	public common.Lazy[] getLocalInheritedAttributes(final int key) {
		return localInheritedAttributes[key];
	}

	@Override
	public common.Lazy[] getChildInheritedAttributes(final int key) {
		return childInheritedAttributes[key];
	}

	@Override
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:lexerClassSubmits erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
	}

	@Override
	public common.Lazy getLocal(final int key) {
		return localAttributes[key];
	}

	@Override
	public final int getNumberOfLocalAttrs() {
		return num_local_attrs;
	}

	@Override
	public final String getNameOfLocalAttr(final int index) {
		return occurs_local[index];
	}

	@Override
	public String getName() {
		return "silver:definition:concrete_syntax:ast:lexerClassSubmits";
	}

	static void initProductionAttributeDefinitions() {
		// subRefs = lookupStrings(sub, top.cstEnv)
		silver.definition.concrete_syntax.ast.PlexerClassSubmits.localAttributes[silver.definition.concrete_syntax.ast.Init.subRefs__ON__silver_definition_concrete_syntax_ast_lexerClassSubmits] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.concrete_syntax.ast.PlookupStrings.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PlexerClassSubmits.i_sub), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier))); } };
		// top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]>  -> if ! null(a.snd) then [] else [ "Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++ "this grammar was not included in this parser. (Referenced from submit clause for lexer class)" ], zipWith(pair, sub, subRefs))
		if(silver.definition.concrete_syntax.ast.PlexerClassSubmits.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] == null)
			silver.definition.concrete_syntax.ast.PlexerClassSubmits.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PlexerClassSubmits.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke((new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1828_a = args[0];

    return ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_1828_a)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Terminal / Lexer Class ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_1828_a)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" was referenced but ")), (common.StringCatter)(new common.StringCatter("this grammar was not included in this parser. (Referenced from submit clause for lexer class)"))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke(core.Ppair.factory, context.childAsIsLazy(silver.definition.concrete_syntax.ast.PlexerClassSubmits.i_sub), context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.subRefs__ON__silver_definition_concrete_syntax_ast_lexerClassSubmits))); } })); } });
		// top.submitsXML = implode("", map(xmlCopperRef, map(head, subRefs)))
		silver.definition.concrete_syntax.ast.PlexerClassSubmits.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.concrete_syntax.ast.PxmlCopperRef.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(core.Phead.factory, context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.subRefs__ON__silver_definition_concrete_syntax_ast_lexerClassSubmits))); } })); } })); } };
		// top.unparses = [ "sub(" ++ unparseStrings(sub) ++ ")" ]
		silver.definition.concrete_syntax.ast.PlexerClassSubmits.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("sub(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PlexerClassSubmits.i_sub))), (common.StringCatter)(new common.StringCatter(")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PlexerClassSubmits> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexerClassSubmits> {

		@Override
		public PlexerClassSubmits invoke(final Object[] children, final Object[] annotations) {
			return new PlexerClassSubmits(children[0]);
		}
	};

}
