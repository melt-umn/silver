
package silver.definition.core;

// top::Expr ::= e::Expr '.' 'forward' 
public final class PforwardAccess extends silver.definition.core.NExpr {

	public static final int i_e = 0;
	public static final int i__G_1 = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.core.TDot_t.class,silver.definition.core.TForward_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_forwardAccess;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PforwardAccess(final Object c_e, final Object c__G_1, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_e = c_e;
		this.child__G_1 = c__G_1;
		this.child__G_0 = c__G_0;

	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_1;
	public final silver.definition.core.TDot_t getChild__G_1() {
		return (silver.definition.core.TDot_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child__G_0;
	public final silver.definition.core.TForward_kwd getChild__G_0() {
		return (silver.definition.core.TForward_kwd) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i__G_1: return getChild__G_1();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i__G_1: return child__G_1;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:forwardAccess erroneously claimed to forward");
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
		return "silver:definition:core:forwardAccess";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp ++ ".forward"
		silver.definition.core.PforwardAccess.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PforwardAccess.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(".forward"))); } };
		// top.errors := e.errors
		if(silver.definition.core.PforwardAccess.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.definition.core.PforwardAccess.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.definition.core.PforwardAccess.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PforwardAccess.i_e).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr)); } });
		// top.typerep = e.typerep
		silver.definition.core.PforwardAccess.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.childDecorated(silver.definition.core.PforwardAccess.i_e).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr)); } };

	}

	public static final common.NodeFactory<PforwardAccess> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PforwardAccess> {

		@Override
		public PforwardAccess invoke(final Object[] children, final Object[] annotations) {
			return new PforwardAccess(children[0], children[1], children[2], annotations[0]);
		}
	};

}
