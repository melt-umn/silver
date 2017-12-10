
package silver.definition.env.env_parser;

// top::INamedArgTypes ::= ';' n::IName '=' ty::ITypeRep rest::INamedArgTypes 
public final class PaNamedArgTypeCons extends silver.definition.env.env_parser.NINamedArgTypes {

	public static final int i__G_4 = 0;
	public static final int i_n = 1;
	public static final int i__G_2 = 2;
	public static final int i_ty = 3;
	public static final int i_rest = 4;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TSemi.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TEq.class,silver.definition.env.env_parser.NITypeRep.class,silver.definition.env.env_parser.NINamedArgTypes.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aNamedArgTypeCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NINamedArgTypes.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NINamedArgTypes.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_inh_attrs];
	childInheritedAttributes[i_rest] = new common.Lazy[silver.definition.env.env_parser.NINamedArgTypes.num_inh_attrs];

	}

	public PaNamedArgTypeCons(final Object c__G_4, final Object c_n, final Object c__G_2, final Object c_ty, final Object c_rest) {
		super();
		this.child__G_4 = c__G_4;
		this.child_n = c_n;
		this.child__G_2 = c__G_2;
		this.child_ty = c_ty;
		this.child_rest = c_rest;

	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TSemi getChild__G_4() {
		return (silver.definition.env.env_parser.TSemi) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_n;
	public final silver.definition.env.env_parser.NIName getChild_n() {
		return (silver.definition.env.env_parser.NIName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TEq getChild__G_2() {
		return (silver.definition.env.env_parser.TEq) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ty;
	public final silver.definition.env.env_parser.NITypeRep getChild_ty() {
		return (silver.definition.env.env_parser.NITypeRep) (child_ty = common.Util.demand(child_ty));
	}

	private Object child_rest;
	public final silver.definition.env.env_parser.NINamedArgTypes getChild_rest() {
		return (silver.definition.env.env_parser.NINamedArgTypes) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_n: return getChild_n();
			case i__G_2: return getChild__G_2();
			case i_ty: return getChild_ty();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_n: return child_n;
			case i__G_2: return child__G_2;
			case i_ty: return child_ty;
			case i_rest: return child_rest;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aNamedArgTypeCons erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aNamedArgTypeCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.aNamedArgs = (namedArgType(n.aname, ty.typerep) :: rest.aNamedArgs)
		silver.definition.env.env_parser.PaNamedArgTypeCons.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aNamedArgs__ON__silver_definition_env_env_parser_INamedArgTypes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NNamedArgType)new silver.definition.type.PnamedArgType(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedArgTypeCons.i_n, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedArgTypeCons.i_ty, silver.definition.env.env_parser.Init.silver_definition_env_typerep__ON__silver_definition_env_env_parser_ITypeRep))); } }, context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedArgTypeCons.i_rest, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aNamedArgs__ON__silver_definition_env_env_parser_INamedArgTypes))); } };

	}

	public static final common.NodeFactory<PaNamedArgTypeCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaNamedArgTypeCons> {

		@Override
		public PaNamedArgTypeCons invoke(final Object[] children, final Object[] annotations) {
			return new PaNamedArgTypeCons(children[0], children[1], children[2], children[3], children[4]);
		}
	};

}
