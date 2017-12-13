
package silver.extension.bidirtransform;

// top::TypeExpr ::= inner::String 
public final class PmkMaybeTypeExpr extends silver.definition.type.syntax.NTypeExpr {

	public static final int i_inner = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_mkMaybeTypeExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PmkMaybeTypeExpr(final Object c_inner, final Object a_core_location) {
		super(a_core_location);
		this.child_inner = c_inner;

	}

	private Object child_inner;
	public final common.StringCatter getChild_inner() {
		return (common.StringCatter) (child_inner = common.Util.demand(child_inner));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_inner: return getChild_inner();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_inner: return child_inner;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return ((silver.definition.type.syntax.NTypeExpr)new silver.definition.type.syntax.PnominalTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameType)new silver.extension.bidirtransform.PqnTyId((new common.StringCatter("Maybe")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NBracketedOptTypeExprs)new silver.extension.bidirtransform.PbotlOneString(context.childAsIsLazy(silver.extension.bidirtransform.PmkMaybeTypeExpr.i_inner), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:mkMaybeTypeExpr";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PmkMaybeTypeExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmkMaybeTypeExpr> {

		@Override
		public PmkMaybeTypeExpr invoke(final Object[] children, final Object[] annotations) {
			return new PmkMaybeTypeExpr(children[0], annotations[0]);
		}
	};

}
