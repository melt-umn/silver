
package silver.definition.env.env_parser;

// top::ITypeRepsInner ::= t1::ITypeRep ',' t2::ITypeRepsInner 
public final class PaTypeRepsInnerCons extends silver.definition.env.env_parser.NITypeRepsInner {

	public static final int i_t1 = 0;
	public static final int i__G_1 = 1;
	public static final int i_t2 = 2;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.NITypeRep.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NITypeRepsInner.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aTypeRepsInnerCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NITypeRepsInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NITypeRepsInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t1] = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_inh_attrs];
	childInheritedAttributes[i_t2] = new common.Lazy[silver.definition.env.env_parser.NITypeRepsInner.num_inh_attrs];

	}

	public PaTypeRepsInnerCons(final Object c_t1, final Object c__G_1, final Object c_t2) {
		super();
		this.child_t1 = c_t1;
		this.child__G_1 = c__G_1;
		this.child_t2 = c_t2;

	}

	private Object child_t1;
	public final silver.definition.env.env_parser.NITypeRep getChild_t1() {
		return (silver.definition.env.env_parser.NITypeRep) (child_t1 = common.Util.demand(child_t1));
	}

	private Object child__G_1;
	public final silver.definition.env.env_parser.TComma_t getChild__G_1() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t2;
	public final silver.definition.env.env_parser.NITypeRepsInner getChild_t2() {
		return (silver.definition.env.env_parser.NITypeRepsInner) (child_t2 = common.Util.demand(child_t2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t1: return getChild_t1();
			case i__G_1: return getChild__G_1();
			case i_t2: return getChild_t2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t1: return child_t1;
			case i__G_1: return child__G_1;
			case i_t2: return child_t2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aTypeRepsInnerCons erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aTypeRepsInnerCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.typereps = [ t1.typerep ] ++ t2.typereps
		silver.definition.env.env_parser.PaTypeRepsInnerCons.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_typereps__ON__silver_definition_env_env_parser_ITypeRepsInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaTypeRepsInnerCons.i_t1, silver.definition.env.env_parser.Init.silver_definition_env_typerep__ON__silver_definition_env_env_parser_ITypeRep), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaTypeRepsInnerCons.i_t2, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_typereps__ON__silver_definition_env_env_parser_ITypeRepsInner))); } };

	}

	public static final common.NodeFactory<PaTypeRepsInnerCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaTypeRepsInnerCons> {

		@Override
		public PaTypeRepsInnerCons invoke(final Object[] children, final Object[] annotations) {
			return new PaTypeRepsInnerCons(children[0], children[1], children[2]);
		}
	};

}
