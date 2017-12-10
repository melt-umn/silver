
package silver.extension.bidirtransform;

// top::Expr ::= name::String arg::AppExpr 
public final class PoneArgFunc extends silver.definition.core.NExpr {

	public static final int i_name = 0;
	public static final int i_arg = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.core.NAppExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_oneArgFunc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_arg] = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	}

	public PoneArgFunc(final Object c_name, final Object c_arg, final Object a_core_location) {
		super(a_core_location);
		this.child_name = c_name;
		this.child_arg = c_arg;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_arg;
	public final silver.definition.core.NAppExpr getChild_arg() {
		return (silver.definition.core.NAppExpr) (child_arg = common.Util.demand(child_arg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_arg: return getChild_arg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_arg: return child_arg;

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
		return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PargFunc(context.childAsIsLazy(silver.extension.bidirtransform.PoneArgFunc.i_name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.extension.bidirtransform.PappExprList(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PoneArgFunc.i_arg)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:oneArgFunc";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PoneArgFunc> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneArgFunc> {

		@Override
		public PoneArgFunc invoke(final Object[] children, final Object[] annotations) {
			return new PoneArgFunc(children[0], children[1], annotations[0]);
		}
	};

}
