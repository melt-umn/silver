
package silver.definition.core;

// top::Expr ::= target::(Expr ::= Decorated Expr Decorated QNameAttrOccur Location) e::Expr q::Decorated QNameAttrOccur 
public final class PaccessBouncer extends silver.definition.core.NExpr {

	public static final int i_target = 0;
	public static final int i_e = 1;
	public static final int i_q = 2;


	public static final Class<?> childTypes[] = {common.NodeFactory.class,silver.definition.core.NExpr.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_accessBouncer;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PaccessBouncer(final Object c_target, final Object c_e, final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_target = c_target;
		this.child_e = c_e;
		this.child_q = c_q;

	}

	private Object child_target;
	public final common.NodeFactory<silver.definition.core.NExpr> getChild_target() {
		return (common.NodeFactory<silver.definition.core.NExpr>) (child_target = common.Util.demand(child_target));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}

	private Object child_q;
	public final common.DecoratedNode getChild_q() {
		return (common.DecoratedNode) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_target: return getChild_target();
			case i_e: return getChild_e();
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_target: return child_target;
			case i_e: return child_e;
			case i_q: return child_q;

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
		return ((silver.definition.core.NExpr)((common.NodeFactory<silver.definition.core.NExpr>)context.childAsIs(silver.definition.core.PaccessBouncer.i_target)).invoke(new Object[]{context.childDecoratedLazy(silver.definition.core.PaccessBouncer.i_e), context.childAsIsLazy(silver.definition.core.PaccessBouncer.i_q), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }}, null));
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
		return "silver:definition:core:accessBouncer";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = e.pp ++ "." ++ q.pp
		silver.definition.core.PaccessBouncer.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PaccessBouncer.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PaccessBouncer.i_q)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)))); } };

	}

	public static final common.NodeFactory<PaccessBouncer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaccessBouncer> {

		@Override
		public PaccessBouncer invoke(final Object[] children, final Object[] annotations) {
			return new PaccessBouncer(children[0], children[1], children[2], annotations[0]);
		}
	};

}
