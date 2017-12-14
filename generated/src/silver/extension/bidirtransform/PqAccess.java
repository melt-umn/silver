
package silver.extension.bidirtransform;

// top::Expr ::= q::String accessOn::Expr 
public final class PqAccess extends silver.definition.core.NExpr {

	public static final int i_q = 0;
	public static final int i_accessOn = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_qAccess;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_accessOn] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PqAccess(final Object c_q, final Object c_accessOn, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;
		this.child_accessOn = c_accessOn;

	}

	private Object child_q;
	public final common.StringCatter getChild_q() {
		return (common.StringCatter) (child_q = common.Util.demand(child_q));
	}

	private Object child_accessOn;
	public final silver.definition.core.NExpr getChild_accessOn() {
		return (silver.definition.core.NExpr) (child_accessOn = common.Util.demand(child_accessOn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();
			case i_accessOn: return getChild_accessOn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;
			case i_accessOn: return child_accessOn;

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
		return ((silver.definition.core.NExpr)new silver.definition.core.Paccess(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PqAccess.i_accessOn)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TDot_t((new common.StringCatter(".")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameAttrOccur)new silver.definition.core.PqNameAttrOccur(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, context.childAsIsLazy(silver.extension.bidirtransform.PqAccess.i_q))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:qAccess";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PqAccess> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqAccess> {

		@Override
		public PqAccess invoke(final Object[] children, final Object[] annotations) {
			return new PqAccess(children[0], children[1], annotations[0]);
		}
	};

}
