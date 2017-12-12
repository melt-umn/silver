
package silver.extension.list;

// top::Expr ::= '[' es::Exprs ']' 
public final class PfullList extends silver.definition.core.NExpr {

	public static final int i__G_2 = 0;
	public static final int i_es = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.extension.list.TLSqr_t.class,silver.definition.core.NExprs.class,silver.extension.list.TRSqr_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_list_fullList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_es] = new common.Lazy[silver.definition.core.NExprs.num_inh_attrs];

	}

	public PfullList(final Object c__G_2, final Object c_es, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_es = c_es;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.extension.list.TLSqr_t getChild__G_2() {
		return (silver.extension.list.TLSqr_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_es;
	public final silver.definition.core.NExprs getChild_es() {
		return (silver.definition.core.NExprs) (child_es = common.Util.demand(child_es));
	}

	private Object child__G_0;
	public final silver.extension.list.TRSqr_t getChild__G_0() {
		return (silver.extension.list.TRSqr_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_es: return getChild_es();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_es: return child_es;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)context.childDecorated(silver.extension.list.PfullList.i_es).synthesized(silver.extension.list.Init.silver_extension_list_listtrans__ON__silver_definition_core_Exprs));
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
		return "silver:extension:list:fullList";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "[ " ++ es.pp ++ " ]"
		silver.extension.list.PfullList.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("[ ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.list.PfullList.i_es).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Exprs)), (common.StringCatter)(new common.StringCatter(" ]")))); } };
		// es.downSubst = top.downSubst
		silver.extension.list.PfullList.childInheritedAttributes[silver.extension.list.PfullList.i_es][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Exprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr)); } };

	}

	public static final common.NodeFactory<PfullList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfullList> {

		@Override
		public PfullList invoke(final Object[] children, final Object[] annotations) {
			return new PfullList(children[0], children[1], children[2], annotations[0]);
		}
	};

}
