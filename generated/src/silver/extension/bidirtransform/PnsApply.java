
package silver.extension.bidirtransform;

// top::Expr ::= ns::Decorated NamedSignature 
public final class PnsApply extends silver.definition.core.NExpr {

	public static final int i_ns = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_nsApply;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnsApply(final Object c_ns, final Object a_core_location) {
		super(a_core_location);
		this.child_ns = c_ns;

	}

	private Object child_ns;
	public final common.DecoratedNode getChild_ns() {
		return (common.DecoratedNode) (child_ns = common.Util.demand(child_ns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;

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
		return (((Boolean)core.Pnull.invoke(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PnsApply.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))) ? ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PemptyFunc(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PnsApply.i_ns, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PargFunc(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PnsApply.i_ns, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.extension.bidirtransform.PnsElemsToAppExprs(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PnsApply.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:extension:bidirtransform:nsApply";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PnsApply> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnsApply> {

		@Override
		public PnsApply invoke(final Object[] children, final Object[] annotations) {
			return new PnsApply(children[0], annotations[0]);
		}
	};

}
