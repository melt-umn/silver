
package silver.extension.bidirtransform;

// top::Expr ::= ls::[Expr] idx::Integer 
public final class PidxOfExprs extends silver.definition.core.NExpr {

	public static final int i_ls = 0;
	public static final int i_idx = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_idxOfExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PidxOfExprs(final Object c_ls, final Object c_idx, final Object a_core_location) {
		super(a_core_location);
		this.child_ls = c_ls;
		this.child_idx = c_idx;

	}

	private Object child_ls;
	public final common.ConsCell getChild_ls() {
		return (common.ConsCell) (child_ls = common.Util.demand(child_ls));
	}

	private Object child_idx;
	public final Integer getChild_idx() {
		return (Integer) (child_idx = common.Util.demand(child_idx));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ls: return getChild_ls();
			case i_idx: return getChild_idx();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ls: return child_ls;
			case i_idx: return child_idx;

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
		return (((Integer)context.childAsIs(silver.extension.bidirtransform.PidxOfExprs.i_idx)).equals(Integer.valueOf((int)0)) ? ((silver.definition.core.NExpr)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PidxOfExprs.i_ls))) : ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PidxOfExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PidxOfExprs.i_ls))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.extension.bidirtransform.PidxOfExprs.i_idx)) - Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:extension:bidirtransform:idxOfExprs";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PidxOfExprs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PidxOfExprs> {

		@Override
		public PidxOfExprs invoke(final Object[] children, final Object[] annotations) {
			return new PidxOfExprs(children[0], children[1], annotations[0]);
		}
	};

}
