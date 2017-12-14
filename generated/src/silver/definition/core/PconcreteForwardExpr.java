
package silver.definition.core;

// top::Expr ::= q::'forward' 
public final class PconcreteForwardExpr extends silver.definition.core.NExpr {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TForward_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_concreteForwardExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PconcreteForwardExpr(final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;

	}

	private Object child_q;
	public final silver.definition.core.TForward_kwd getChild_q() {
		return (silver.definition.core.TForward_kwd) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;

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
		return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(((core.NLocation)((silver.definition.core.TForward_kwd)context.childAsIs(silver.definition.core.PconcreteForwardExpr.i_q)).location), (new common.StringCatter("forward")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:concreteForwardExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "forward"
		silver.definition.core.PconcreteForwardExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("forward")); } };

	}

	public static final common.NodeFactory<PconcreteForwardExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconcreteForwardExpr> {

		@Override
		public PconcreteForwardExpr invoke(final Object[] children, final Object[] annotations) {
			return new PconcreteForwardExpr(children[0], annotations[0]);
		}
	};

}
