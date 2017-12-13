
package silver.definition.env.env_parser;

// top::ITypeRep ::= 'fun' '(' it::ITypeReps ',' ot::ITypeRep na::INamedArgTypes ')' 
public final class PaTypeRepFunction extends silver.definition.env.env_parser.NITypeRep {

	public static final int i__G_6 = 0;
	public static final int i__G_5 = 1;
	public static final int i_it = 2;
	public static final int i__G_3 = 3;
	public static final int i_ot = 4;
	public static final int i_na = 5;
	public static final int i__G_0 = 6;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TFunctionTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NITypeReps.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NITypeRep.class,silver.definition.env.env_parser.NINamedArgTypes.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aTypeRepFunction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_it] = new common.Lazy[silver.definition.env.env_parser.NITypeReps.num_inh_attrs];
	childInheritedAttributes[i_ot] = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_inh_attrs];
	childInheritedAttributes[i_na] = new common.Lazy[silver.definition.env.env_parser.NINamedArgTypes.num_inh_attrs];

	}

	public PaTypeRepFunction(final Object c__G_6, final Object c__G_5, final Object c_it, final Object c__G_3, final Object c_ot, final Object c_na, final Object c__G_0) {
		super();
		this.child__G_6 = c__G_6;
		this.child__G_5 = c__G_5;
		this.child_it = c_it;
		this.child__G_3 = c__G_3;
		this.child_ot = c_ot;
		this.child_na = c_na;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_6;
	public final silver.definition.env.env_parser.TFunctionTerm getChild__G_6() {
		return (silver.definition.env.env_parser.TFunctionTerm) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child__G_5;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_5() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_it;
	public final silver.definition.env.env_parser.NITypeReps getChild_it() {
		return (silver.definition.env.env_parser.NITypeReps) (child_it = common.Util.demand(child_it));
	}

	private Object child__G_3;
	public final silver.definition.env.env_parser.TComma_t getChild__G_3() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_ot;
	public final silver.definition.env.env_parser.NITypeRep getChild_ot() {
		return (silver.definition.env.env_parser.NITypeRep) (child_ot = common.Util.demand(child_ot));
	}

	private Object child_na;
	public final silver.definition.env.env_parser.NINamedArgTypes getChild_na() {
		return (silver.definition.env.env_parser.NINamedArgTypes) (child_na = common.Util.demand(child_na));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_6: return getChild__G_6();
			case i__G_5: return getChild__G_5();
			case i_it: return getChild_it();
			case i__G_3: return getChild__G_3();
			case i_ot: return getChild_ot();
			case i_na: return getChild_na();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_6: return child__G_6;
			case i__G_5: return child__G_5;
			case i_it: return child_it;
			case i__G_3: return child__G_3;
			case i_ot: return child_ot;
			case i_na: return child_na;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aTypeRepFunction erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aTypeRepFunction";
	}

	static void initProductionAttributeDefinitions() {
		// top.typerep = functionType(ot.typerep, it.typereps, na.aNamedArgs)
		silver.definition.env.env_parser.PaTypeRepFunction.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_typerep__ON__silver_definition_env_env_parser_ITypeRep] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PfunctionType(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaTypeRepFunction.i_ot, silver.definition.env.env_parser.Init.silver_definition_env_typerep__ON__silver_definition_env_env_parser_ITypeRep), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaTypeRepFunction.i_it, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_typereps__ON__silver_definition_env_env_parser_ITypeReps), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaTypeRepFunction.i_na, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aNamedArgs__ON__silver_definition_env_env_parser_INamedArgTypes))); } };

	}

	public static final common.NodeFactory<PaTypeRepFunction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaTypeRepFunction> {

		@Override
		public PaTypeRepFunction invoke(final Object[] children, final Object[] annotations) {
			return new PaTypeRepFunction(children[0], children[1], children[2], children[3], children[4], children[5], children[6]);
		}
	};

}
