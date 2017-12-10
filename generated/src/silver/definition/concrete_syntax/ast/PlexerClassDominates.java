
package silver.definition.concrete_syntax.ast;

// top::SyntaxLexerClassModifier ::= dom::[String] 
public final class PlexerClassDominates extends silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier {

	public static final int i_dom = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_lexerClassDominates;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PlexerClassDominates(final Object c_dom) {
		super();
		this.child_dom = c_dom;

	}

	private Object child_dom;
	public final common.ConsCell getChild_dom() {
		return (common.ConsCell) (child_dom = common.Util.demand(child_dom));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dom: return getChild_dom();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dom: return child_dom;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:lexerClassDominates erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:lexerClassDominates";
	}

	static void initProductionAttributeDefinitions() {
		// domRefs = lookupStrings(dom, top.cstEnv)
		silver.definition.concrete_syntax.ast.PlexerClassDominates.localAttributes[silver.definition.concrete_syntax.ast.Init.domRefs__ON__silver_definition_concrete_syntax_ast_lexerClassDominates] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.concrete_syntax.ast.PlookupStrings.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PlexerClassDominates.i_dom), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier))); } };
		// top.cstErrors := flatMap(\ a::Pair<String [Decorated SyntaxDcl]>  -> if ! null(a.snd) then [] else [ "Terminal / Lexer Class " ++ a.fst ++ " was referenced but " ++ "this grammar was not included in this parser. (Referenced from dominates clause for lexer class)" ], zipWith(pair, dom, domRefs))
		if(silver.definition.concrete_syntax.ast.PlexerClassDominates.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] == null)
			silver.definition.concrete_syntax.ast.PlexerClassDominates.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PlexerClassDominates.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke((new common.NodeFactory<common.ConsCell>() {
  public final common.ConsCell invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_1811_a = args[0];

    return ((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_1811_a)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Terminal / Lexer Class ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NPair)common.Util.demand(__SV_LAMBDA_PARAM_1811_a)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" was referenced but ")), (common.StringCatter)(new common.StringCatter("this grammar was not included in this parser. (Referenced from dominates clause for lexer class)"))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke(core.Ppair.factory, context.childAsIsLazy(silver.definition.concrete_syntax.ast.PlexerClassDominates.i_dom), context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.domRefs__ON__silver_definition_concrete_syntax_ast_lexerClassDominates))); } })); } });
		// top.dominatesXML = implode("", map(xmlCopperRef, map(head, domRefs)))
		silver.definition.concrete_syntax.ast.PlexerClassDominates.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.concrete_syntax.ast.PxmlCopperRef.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(core.Phead.factory, context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.domRefs__ON__silver_definition_concrete_syntax_ast_lexerClassDominates))); } })); } })); } };
		// top.unparses = [ "dom(" ++ unparseStrings(dom) ++ ")" ]
		silver.definition.concrete_syntax.ast.PlexerClassDominates.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("dom(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PlexerClassDominates.i_dom))), (common.StringCatter)(new common.StringCatter(")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PlexerClassDominates> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexerClassDominates> {

		@Override
		public PlexerClassDominates invoke(final Object[] children, final Object[] annotations) {
			return new PlexerClassDominates(children[0]);
		}
	};

}
