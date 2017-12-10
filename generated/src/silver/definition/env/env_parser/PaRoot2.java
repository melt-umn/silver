
package silver.definition.env.env_parser;

// top::IRoot ::= r1::IRootPart r2::IRoot 
public final class PaRoot2 extends silver.definition.env.env_parser.NIRoot {

	public static final int i_r1 = 0;
	public static final int i_r2 = 1;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.NIRootPart.class,silver.definition.env.env_parser.NIRoot.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aRoot2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NIRoot.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NIRoot.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_r1] = new common.Lazy[silver.definition.env.env_parser.NIRootPart.num_inh_attrs];
	childInheritedAttributes[i_r2] = new common.Lazy[silver.definition.env.env_parser.NIRoot.num_inh_attrs];

	}

	public PaRoot2(final Object c_r1, final Object c_r2) {
		super();
		this.child_r1 = c_r1;
		this.child_r2 = c_r2;

	}

	private Object child_r1;
	public final silver.definition.env.env_parser.NIRootPart getChild_r1() {
		return (silver.definition.env.env_parser.NIRootPart) (child_r1 = common.Util.demand(child_r1));
	}

	private Object child_r2;
	public final silver.definition.env.env_parser.NIRoot getChild_r2() {
		return (silver.definition.env.env_parser.NIRoot) (child_r2 = common.Util.demand(child_r2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r1: return getChild_r1();
			case i_r2: return getChild_r2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r1: return child_r1;
			case i_r2: return child_r2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aRoot2 erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aRoot2";
	}

	static void initProductionAttributeDefinitions() {
		// top.declaredName = if r1.declaredName == "" then r2.declaredName else r1.declaredName
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_declaredName__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)context.childDecorated(silver.definition.env.env_parser.PaRoot2.i_r1).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_declaredName__ON__silver_definition_env_env_parser_IRootPart)).equals((new common.StringCatter(""))) ? ((common.StringCatter)context.childDecorated(silver.definition.env.env_parser.PaRoot2.i_r2).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_declaredName__ON__silver_definition_env_env_parser_IRoot)) : ((common.StringCatter)context.childDecorated(silver.definition.env.env_parser.PaRoot2.i_r1).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_declaredName__ON__silver_definition_env_env_parser_IRootPart))); } };
		// top.grammarTime = if r1.grammarTime == -1 then r2.grammarTime else r1.grammarTime
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_grammarTime__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)context.childDecorated(silver.definition.env.env_parser.PaRoot2.i_r1).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarTime__ON__silver_definition_env_env_parser_IRootPart)).equals(Integer.valueOf((int)-1)) ? ((Integer)context.childDecorated(silver.definition.env.env_parser.PaRoot2.i_r2).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarTime__ON__silver_definition_env_env_parser_IRoot)) : ((Integer)context.childDecorated(silver.definition.env.env_parser.PaRoot2.i_r1).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarTime__ON__silver_definition_env_env_parser_IRootPart))); } };
		// top.grammarSource = if r1.grammarSource == "" then r2.grammarSource else r1.grammarSource
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_grammarSource__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)context.childDecorated(silver.definition.env.env_parser.PaRoot2.i_r1).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarSource__ON__silver_definition_env_env_parser_IRootPart)).equals((new common.StringCatter(""))) ? ((common.StringCatter)context.childDecorated(silver.definition.env.env_parser.PaRoot2.i_r2).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarSource__ON__silver_definition_env_env_parser_IRoot)) : ((common.StringCatter)context.childDecorated(silver.definition.env.env_parser.PaRoot2.i_r1).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarSource__ON__silver_definition_env_env_parser_IRootPart))); } };
		// top.defs = r1.defs ++ r2.defs
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r1, silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IRootPart), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r2, silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IRoot))); } };
		// top.moduleNames = r1.moduleNames ++ r2.moduleNames
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_moduleNames__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r1, silver.definition.env.env_parser.Init.silver_definition_env_moduleNames__ON__silver_definition_env_env_parser_IRootPart), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r2, silver.definition.env.env_parser.Init.silver_definition_env_moduleNames__ON__silver_definition_env_env_parser_IRoot))); } };
		// top.allGrammarDependencies = r1.allGrammarDependencies ++ r2.allGrammarDependencies
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_allGrammarDependencies__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r1, silver.definition.env.env_parser.Init.silver_definition_env_allGrammarDependencies__ON__silver_definition_env_env_parser_IRootPart), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r2, silver.definition.env.env_parser.Init.silver_definition_env_allGrammarDependencies__ON__silver_definition_env_env_parser_IRoot))); } };
		// top.exportedGrammars = r1.exportedGrammars ++ r2.exportedGrammars
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_exportedGrammars__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r1, silver.definition.env.env_parser.Init.silver_definition_env_exportedGrammars__ON__silver_definition_env_env_parser_IRootPart), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r2, silver.definition.env.env_parser.Init.silver_definition_env_exportedGrammars__ON__silver_definition_env_env_parser_IRoot))); } };
		// top.optionalGrammars = r1.optionalGrammars ++ r2.optionalGrammars
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_optionalGrammars__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r1, silver.definition.env.env_parser.Init.silver_definition_env_optionalGrammars__ON__silver_definition_env_env_parser_IRootPart), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r2, silver.definition.env.env_parser.Init.silver_definition_env_optionalGrammars__ON__silver_definition_env_env_parser_IRoot))); } };
		// top.condBuild = r1.condBuild ++ r2.condBuild
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_condBuild__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r1, silver.definition.env.env_parser.Init.silver_definition_env_condBuild__ON__silver_definition_env_env_parser_IRootPart), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r2, silver.definition.env.env_parser.Init.silver_definition_env_condBuild__ON__silver_definition_env_env_parser_IRoot))); } };

	}

	public static final common.NodeFactory<PaRoot2> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaRoot2> {

		@Override
		public PaRoot2 invoke(final Object[] children, final Object[] annotations) {
			return new PaRoot2(children[0], children[1]);
		}
	};

}
