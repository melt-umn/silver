
package silver.extension.bidirtransform;

// top::ProductionStmt ::= lhs::String att::String eqs::Expr 
public final class PattribDef extends silver.definition.core.NProductionStmt {

	public static final int i_lhs = 0;
	public static final int i_att = 1;
	public static final int i_eqs = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_attribDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_eqs] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PattribDef(final Object c_lhs, final Object c_att, final Object c_eqs, final Object a_core_location) {
		super(a_core_location);
		this.child_lhs = c_lhs;
		this.child_att = c_att;
		this.child_eqs = c_eqs;

	}

	private Object child_lhs;
	public final common.StringCatter getChild_lhs() {
		return (common.StringCatter) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child_att;
	public final common.StringCatter getChild_att() {
		return (common.StringCatter) (child_att = common.Util.demand(child_att));
	}

	private Object child_eqs;
	public final silver.definition.core.NExpr getChild_eqs() {
		return (silver.definition.core.NExpr) (child_eqs = common.Util.demand(child_eqs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lhs: return getChild_lhs();
			case i_att: return getChild_att();
			case i_eqs: return getChild_eqs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhs: return child_lhs;
			case i_att: return child_att;
			case i_eqs: return child_eqs;

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
		return ((silver.definition.core.NProductionStmt)new silver.definition.core.PattributeDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NDefLHS)new silver.extension.bidirtransform.PmkLhsDef(context.childAsIsLazy(silver.extension.bidirtransform.PattribDef.i_lhs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDot_t((new common.StringCatter(".")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameAttrOccur)new silver.extension.bidirtransform.PqAttr(context.childAsIsLazy(silver.extension.bidirtransform.PattribDef.i_att), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TEqual_t((new common.StringCatter("=")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PattribDef.i_eqs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TSemi_t((new common.StringCatter(";")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:attribDef";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PattribDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PattribDef> {

		@Override
		public PattribDef invoke(final Object[] children, final Object[] annotations) {
			return new PattribDef(children[0], children[1], children[2], annotations[0]);
		}
	};

}
