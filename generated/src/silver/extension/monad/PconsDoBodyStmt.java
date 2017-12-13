
package silver.extension.monad;

// top::DoBodyStmts ::= h::DoBodyStmt t::DoBodyStmts 
public final class PconsDoBodyStmt extends silver.extension.monad.NDoBodyStmts {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.extension.monad.NDoBodyStmt.class,silver.extension.monad.NDoBodyStmts.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_consDoBodyStmt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.extension.monad.NDoBodyStmt.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.extension.monad.NDoBodyStmts.num_inh_attrs];

	}

	public PconsDoBodyStmt(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.extension.monad.NDoBodyStmt getChild_h() {
		return (silver.extension.monad.NDoBodyStmt) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.extension.monad.NDoBodyStmts getChild_t() {
		return (silver.extension.monad.NDoBodyStmts) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.extension.monad.NDoBodyStmts)new silver.extension.monad.PbindExprDoBodyStmts(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.monad.NMName)silver.extension.monad.PmName.invoke((new common.StringCatter("_")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NDoBodyStmts)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TColonColon_t((new common.StringCatter("::")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.definition.type.syntax.PtyperepTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshType.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NDoBodyStmts)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.monad.TLArrow_t((new common.StringCatter("<-")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.childDecoratedSynthesizedLazy(silver.extension.monad.PconsDoBodyStmt.i_h, silver.extension.monad.Init.silver_extension_monad_transform__ON__silver_extension_monad_DoBodyStmt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TSemi_t((new common.StringCatter(";")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.monad.PconsDoBodyStmt.i_t)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.monad.NDoBodyStmts)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:monad:consDoBodyStmt";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ " " ++ t.pp
		silver.extension.monad.PconsDoBodyStmt.synthesizedAttributes[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PconsDoBodyStmt.i_h).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.monad.PconsDoBodyStmt.i_t).synthesized(silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_DoBodyStmts)))); } };
		// h.isFinalVal = false
		silver.extension.monad.PconsDoBodyStmt.childInheritedAttributes[silver.extension.monad.PconsDoBodyStmt.i_h][silver.extension.monad.Init.silver_extension_monad_isFinalVal__ON__silver_extension_monad_DoBodyStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<PconsDoBodyStmt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsDoBodyStmt> {

		@Override
		public PconsDoBodyStmt invoke(final Object[] children, final Object[] annotations) {
			return new PconsDoBodyStmt(children[0], children[1], annotations[0]);
		}
	};

}
