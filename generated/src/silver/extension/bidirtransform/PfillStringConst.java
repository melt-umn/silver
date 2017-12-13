
package silver.extension.bidirtransform;

// top::Expr ::= toFill::Expr exps::[Expr] names::[String] s::String 
public final class PfillStringConst extends silver.definition.core.NExpr {

	public static final int i_toFill = 0;
	public static final int i_exps = 1;
	public static final int i_names = 2;
	public static final int i_s = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,common.DecoratedNode.class,common.DecoratedNode.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_fillStringConst;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PfillStringConst(final Object c_toFill, final Object c_exps, final Object c_names, final Object c_s, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_exps = c_exps;
		this.child_names = c_names;
		this.child_s = c_s;

	}

	private Object child_toFill;
	public final silver.definition.core.NExpr getChild_toFill() {
		return (silver.definition.core.NExpr) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_exps;
	public final common.ConsCell getChild_exps() {
		return (common.ConsCell) (child_exps = common.Util.demand(child_exps));
	}

	private Object child_names;
	public final common.ConsCell getChild_names() {
		return (common.ConsCell) (child_names = common.Util.demand(child_names));
	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_exps: return getChild_exps();
			case i_names: return getChild_names();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_exps: return child_exps;
			case i_names: return child_names;
			case i_s: return child_s;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return ((!((Boolean)silver.util.Pcontains.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfillStringConst.i_s), context.childAsIsLazy(silver.extension.bidirtransform.PfillStringConst.i_names)))) ? context.childDecorated(silver.extension.bidirtransform.PfillStringConst.i_toFill).undecorate() : ((((Integer)context.localAsIs(silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_fillStringConst)).equals(Integer.valueOf((int)-1)) || (((Integer)context.localAsIs(silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_fillStringConst)) >= ((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfillStringConst.i_exps))))) ? context.childDecorated(silver.extension.bidirtransform.PfillStringConst.i_toFill).undecorate() : ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PidxOfExprs(context.childAsIsLazy(silver.extension.bidirtransform.PfillStringConst.i_exps), context.localAsIsLazy(silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_fillStringConst), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PfillStringConst.i_toFill).undecorate()).getAnno_core_location()); } }))));
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
		return "silver:extension:bidirtransform:fillStringConst";
	}

	static void initProductionAttributeDefinitions() {
		// idx = findIdx(names, s)
		silver.extension.bidirtransform.PfillStringConst.localAttributes[silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_fillStringConst] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)silver.extension.bidirtransform.PfindIdx.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfillStringConst.i_names), context.childAsIsLazy(silver.extension.bidirtransform.PfillStringConst.i_s))); } };

	}

	public static final common.NodeFactory<PfillStringConst> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfillStringConst> {

		@Override
		public PfillStringConst invoke(final Object[] children, final Object[] annotations) {
			return new PfillStringConst(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
