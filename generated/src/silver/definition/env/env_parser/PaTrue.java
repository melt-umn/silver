
package silver.definition.env.env_parser;

// top::IBool ::= 't' 
public final class PaTrue extends silver.definition.env.env_parser.NIBool {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TT_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aTrue;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NIBool.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NIBool.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PaTrue(final Object c__G_0) {
		super();
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TT_t getChild__G_0() {
		return (silver.definition.env.env_parser.TT_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aTrue erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aTrue";
	}

	static void initProductionAttributeDefinitions() {
		// top.bval = true
		silver.definition.env.env_parser.PaTrue.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_bval__ON__silver_definition_env_env_parser_IBool] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };

	}

	public static final common.NodeFactory<PaTrue> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaTrue> {

		@Override
		public PaTrue invoke(final Object[] children, final Object[] annotations) {
			return new PaTrue(children[0]);
		}
	};

}
