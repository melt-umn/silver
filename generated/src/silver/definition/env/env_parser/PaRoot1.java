
package silver.definition.env.env_parser;

// top::IRoot ::= r::IRootPart 
public final class PaRoot1 extends silver.definition.env.env_parser.NIRoot {

	public static final int i_r = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.NIRootPart.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aRoot1;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NIRoot.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NIRoot.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.env.env_parser.NIRootPart.num_inh_attrs];

	}

	public PaRoot1(final Object c_r) {
		super();
		this.child_r = c_r;

	}

	private Object child_r;
	public final silver.definition.env.env_parser.NIRootPart getChild_r() {
		return (silver.definition.env.env_parser.NIRootPart) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aRoot1 erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aRoot1";
	}

	static void initProductionAttributeDefinitions() {
		// top.declaredName = r.declaredName
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_declaredName__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_declaredName__ON__silver_definition_env_env_parser_IRootPart)); } };
		// top.grammarTime = r.grammarTime
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_grammarTime__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarTime__ON__silver_definition_env_env_parser_IRootPart)); } };
		// top.grammarSource = r.grammarSource
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_grammarSource__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_grammarSource__ON__silver_definition_env_env_parser_IRootPart)); } };
		// top.defs = r.defs
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IRootPart)); } };
		// top.moduleNames = r.moduleNames
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_moduleNames__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_moduleNames__ON__silver_definition_env_env_parser_IRootPart)); } };
		// top.allGrammarDependencies = r.allGrammarDependencies
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_allGrammarDependencies__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_allGrammarDependencies__ON__silver_definition_env_env_parser_IRootPart)); } };
		// top.exportedGrammars = r.exportedGrammars
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_exportedGrammars__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_exportedGrammars__ON__silver_definition_env_env_parser_IRootPart)); } };
		// top.optionalGrammars = r.optionalGrammars
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_optionalGrammars__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_optionalGrammars__ON__silver_definition_env_env_parser_IRootPart)); } };
		// top.condBuild = r.condBuild
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_condBuild__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_condBuild__ON__silver_definition_env_env_parser_IRootPart)); } };

	}

	public static final common.NodeFactory<PaRoot1> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaRoot1> {

		@Override
		public PaRoot1 invoke(final Object[] children, final Object[] annotations) {
			return new PaRoot1(children[0]);
		}
	};

}
