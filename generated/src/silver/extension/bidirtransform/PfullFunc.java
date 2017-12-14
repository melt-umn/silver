
package silver.extension.bidirtransform;

// top::Expr ::= name::String args::AppExprs annos::AnnoAppExprs 
public final class PfullFunc extends silver.definition.core.NExpr {

	public static final int i_name = 0;
	public static final int i_args = 1;
	public static final int i_annos = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.core.NAppExprs.class,silver.definition.core.NAnnoAppExprs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_fullFunc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_args] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];
	childInheritedAttributes[i_annos] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PfullFunc(final Object c_name, final Object c_args, final Object c_annos, final Object a_core_location) {
		super(a_core_location);
		this.child_name = c_name;
		this.child_args = c_args;
		this.child_annos = c_annos;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_args;
	public final silver.definition.core.NAppExprs getChild_args() {
		return (silver.definition.core.NAppExprs) (child_args = common.Util.demand(child_args));
	}

	private Object child_annos;
	public final silver.definition.core.NAnnoAppExprs getChild_annos() {
		return (silver.definition.core.NAnnoAppExprs) (child_annos = common.Util.demand(child_annos));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_args: return getChild_args();
			case i_annos: return getChild_annos();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_args: return child_args;
			case i_annos: return child_annos;

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
		return ((silver.definition.core.NExpr)new silver.definition.core.Papplication(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PbaseName(context.childAsIsLazy(silver.extension.bidirtransform.PfullFunc.i_name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PfullFunc.i_args)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PfullFunc.i_annos)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:fullFunc";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PfullFunc> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfullFunc> {

		@Override
		public PfullFunc invoke(final Object[] children, final Object[] annotations) {
			return new PfullFunc(children[0], children[1], children[2], annotations[0]);
		}
	};

}
