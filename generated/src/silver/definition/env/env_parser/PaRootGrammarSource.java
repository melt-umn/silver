
package silver.definition.env.env_parser;

// top::IRootPart ::= 'grammarSource' s::IString 
public final class PaRootGrammarSource extends silver.definition.env.env_parser.NIRootPart {

	public static final int i__G_1 = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TGrammarSourceTerm.class,silver.definition.env.env_parser.NIString.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aRootGrammarSource;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NIRootPart.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NIRootPart.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.env.env_parser.NIString.num_inh_attrs];

	}

	public PaRootGrammarSource(final Object c__G_1, final Object c_s) {
		super();
		this.child__G_1 = c__G_1;
		this.child_s = c_s;

	}

	private Object child__G_1;
	public final silver.definition.env.env_parser.TGrammarSourceTerm getChild__G_1() {
		return (silver.definition.env.env_parser.TGrammarSourceTerm) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_s;
	public final silver.definition.env.env_parser.NIString getChild_s() {
		return (silver.definition.env.env_parser.NIString) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aRootGrammarSource erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aRootGrammarSource";
	}

	static void initProductionAttributeDefinitions() {
		// top.grammarSource = s.str
		silver.definition.env.env_parser.PaRootGrammarSource.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_grammarSource__ON__silver_definition_env_env_parser_IRootPart] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.env.env_parser.PaRootGrammarSource.i_s).synthesized(silver.definition.env.env_parser.Init.silver_definition_env_env_parser_str__ON__silver_definition_env_env_parser_IString)); } };

	}

	public static final common.NodeFactory<PaRootGrammarSource> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaRootGrammarSource> {

		@Override
		public PaRootGrammarSource invoke(final Object[] children, final Object[] annotations) {
			return new PaRootGrammarSource(children[0], children[1]);
		}
	};

}
