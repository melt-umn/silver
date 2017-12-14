
package silver.extension.monad;

// top::DoBodyStmt ::= 'if' cond::Expr 'then' th::DoBodyStmt NoElse_t 
public final class PcondDoBody extends silver.extension.monad.NDoBodyStmt {

	public static final int i__G_4 = 0;
	public static final int i_cond = 1;
	public static final int i__G_2 = 2;
	public static final int i_th = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.definition.core.TIf_kwd.class,silver.definition.core.NExpr.class,silver.definition.core.TThen_kwd.class,silver.extension.monad.NDoBodyStmt.class,silver.extension.monad.TNoElse_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_condDoBody;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_cond] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_th] = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_inh_attrs];

	}

	public PcondDoBody(final Object c__G_4, final Object c_cond, final Object c__G_2, final Object c_th, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_cond = c_cond;
		this.child__G_2 = c__G_2;
		this.child_th = c_th;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.definition.core.TIf_kwd getChild__G_4() {
		return (silver.definition.core.TIf_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_cond;
	public final silver.definition.core.NExpr getChild_cond() {
		return (silver.definition.core.NExpr) (child_cond = common.Util.demand(child_cond));
	}

	private Object child__G_2;
	public final silver.definition.core.TThen_kwd getChild__G_2() {
		return (silver.definition.core.TThen_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_th;
	public final silver.extension.monad.NDoBodyStmt getChild_th() {
		return (silver.extension.monad.NDoBodyStmt) (child_th = common.Util.demand(child_th));
	}

	private Object child__G_0;
	public final silver.extension.monad.TNoElse_t getChild__G_0() {
		return (silver.extension.monad.TNoElse_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_cond: return getChild_cond();
			case i__G_2: return getChild__G_2();
			case i_th: return getChild_th();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_cond: return child_cond;
			case i__G_2: return child__G_2;
			case i_th: return child_th;
			case i__G_0: return child__G_0;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.extension.monad.NDoBodyStmt)new silver.extension.monad.PcondDoBodyElse(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TIf_kwd((new common.StringCatter("if")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.monad.PcondDoBody.i_cond)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TThen_kwd((new common.StringCatter("then")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.monad.PcondDoBody.i_th)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TElse_kwd((new common.StringCatter("else")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.monad.NDoBodyStmt)new silver.extension.monad.PreturnUnitDoBody(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NDoBodyStmt)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NDoBodyStmt)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:monad:condDoBody";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "if " ++ cond.pp ++ " then " ++ th.pp
		silver.extension.monad.PcondDoBody.synthesizedAttributes[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("if ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PcondDoBody.i_cond).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" then ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PcondDoBody.i_th).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt))))); } };

	}

	public static final common.NodeFactory<PcondDoBody> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcondDoBody> {

		@Override
		public PcondDoBody invoke(final Object[] children, final Object[] annotations) {
			return new PcondDoBody(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
