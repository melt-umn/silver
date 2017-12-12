
package silver.extension.monad;

// top::DoBodyStmt ::= 'if' cond::Expr 'then' th::DoBodyStmt 'else' el::DoBodyStmt 
public final class PcondDoBodyElse extends silver.extension.monad.NDoBodyStmt {

	public static final int i__G_5 = 0;
	public static final int i_cond = 1;
	public static final int i__G_3 = 2;
	public static final int i_th = 3;
	public static final int i__G_1 = 4;
	public static final int i_el = 5;


	public static final Class<?> childTypes[] = {silver.definition.core.TIf_kwd.class,silver.definition.core.NExpr.class,silver.definition.core.TThen_kwd.class,silver.extension.monad.NDoBodyStmt.class,silver.definition.core.TElse_kwd.class,silver.extension.monad.NDoBodyStmt.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_condDoBodyElse;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_cond] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_th] = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_inh_attrs];
	childInheritedAttributes[i_el] = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_inh_attrs];

	}

	public PcondDoBodyElse(final Object c__G_5, final Object c_cond, final Object c__G_3, final Object c_th, final Object c__G_1, final Object c_el, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child_cond = c_cond;
		this.child__G_3 = c__G_3;
		this.child_th = c_th;
		this.child__G_1 = c__G_1;
		this.child_el = c_el;

	}

	private Object child__G_5;
	public final silver.definition.core.TIf_kwd getChild__G_5() {
		return (silver.definition.core.TIf_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_cond;
	public final silver.definition.core.NExpr getChild_cond() {
		return (silver.definition.core.NExpr) (child_cond = common.Util.demand(child_cond));
	}

	private Object child__G_3;
	public final silver.definition.core.TThen_kwd getChild__G_3() {
		return (silver.definition.core.TThen_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_th;
	public final silver.extension.monad.NDoBodyStmt getChild_th() {
		return (silver.extension.monad.NDoBodyStmt) (child_th = common.Util.demand(child_th));
	}

	private Object child__G_1;
	public final silver.definition.core.TElse_kwd getChild__G_1() {
		return (silver.definition.core.TElse_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_el;
	public final silver.extension.monad.NDoBodyStmt getChild_el() {
		return (silver.extension.monad.NDoBodyStmt) (child_el = common.Util.demand(child_el));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i_cond: return getChild_cond();
			case i__G_3: return getChild__G_3();
			case i_th: return getChild_th();
			case i__G_1: return getChild__G_1();
			case i_el: return getChild_el();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i_cond: return child_cond;
			case i__G_3: return child__G_3;
			case i_th: return child_th;
			case i__G_1: return child__G_1;
			case i_el: return child_el;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:monad:condDoBodyElse erroneously claimed to forward");
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
		return "silver:extension:monad:condDoBodyElse";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "if " ++ cond.pp ++ " then " ++ th.pp ++ " else " ++ el.pp
		silver.extension.monad.PcondDoBodyElse.synthesizedAttributes[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("if ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PcondDoBodyElse.i_cond).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" then ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PcondDoBodyElse.i_th).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" else ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PcondDoBodyElse.i_el).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt))))))); } };
		// top.transform = ifThenElse('if', cond, 'then', th.transform, 'else', el.transform,location=top.location)
		silver.extension.monad.PcondDoBodyElse.synthesizedAttributes[silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PifThenElse(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TIf_kwd((new common.StringCatter("if")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.monad.PcondDoBodyElse.i_cond)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TThen_kwd((new common.StringCatter("then")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.childDecoratedSynthesizedLazy(silver.extension.monad.PcondDoBodyElse.i_th, silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TElse_kwd((new common.StringCatter("else")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.childDecoratedSynthesizedLazy(silver.extension.monad.PcondDoBodyElse.i_el, silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NDoBodyStmt)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PcondDoBodyElse> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcondDoBodyElse> {

		@Override
		public PcondDoBodyElse invoke(final Object[] children, final Object[] annotations) {
			return new PcondDoBodyElse(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
