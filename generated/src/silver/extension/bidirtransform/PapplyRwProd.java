
package silver.extension.bidirtransform;

// top::Expr ::= rwr::Decorated RewriteRule ns::Decorated NamedSignature 
public final class PapplyRwProd extends silver.definition.core.NExpr {

	public static final int i_rwr = 0;
	public static final int i_ns = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_applyRwProd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PapplyRwProd(final Object c_rwr, final Object c_ns, final Object a_core_location) {
		super(a_core_location);
		this.child_rwr = c_rwr;
		this.child_ns = c_ns;

	}

	private Object child_rwr;
	public final common.DecoratedNode getChild_rwr() {
		return (common.DecoratedNode) (child_rwr = common.Util.demand(child_rwr));
	}

	private Object child_ns;
	public final common.DecoratedNode getChild_ns() {
		return (common.DecoratedNode) (child_ns = common.Util.demand(child_ns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rwr: return getChild_rwr();
			case i_ns: return getChild_ns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rwr: return child_rwr;
			case i_ns: return child_ns;

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
		return ((silver.definition.core.NExpr)((common.NodeFactory<silver.definition.core.NExpr>)context.localAsIs(silver.extension.bidirtransform.Init.fwdFunc__ON__silver_extension_bidirtransform_applyRwProd)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PfullFunc(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.extension.bidirtransform.NRewriteProduction)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PapplyRwProd.i_rwr)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inputProduction__ON__silver_extension_bidirtransform_RewriteRule)).decorate(context, (common.Lazy[])null).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_RewriteProduction)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.extension.bidirtransform.PstrAppExprs(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PapplyRwProd.i_ns, silver.definition.env.Init.silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.definition.core.PemptyAnnoAppExprs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }}, null));
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
		return "silver:extension:bidirtransform:applyRwProd";
	}

	static void initProductionAttributeDefinitions() {
		// fwdFunc = if rwr.shouldRestore then rwr.restoreStmt else rwr.outputStmt
		silver.extension.bidirtransform.PapplyRwProd.localAttributes[silver.extension.bidirtransform.Init.fwdFunc__ON__silver_extension_bidirtransform_applyRwProd] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PapplyRwProd.i_rwr)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_shouldRestore__ON__silver_extension_bidirtransform_RewriteRule)) ? ((common.NodeFactory<silver.definition.core.NExpr>)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PapplyRwProd.i_rwr)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_restoreStmt__ON__silver_extension_bidirtransform_RewriteRule)) : ((common.NodeFactory<silver.definition.core.NExpr>)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PapplyRwProd.i_rwr)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_outputStmt__ON__silver_extension_bidirtransform_RewriteRule))); } };

	}

	public static final common.NodeFactory<PapplyRwProd> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PapplyRwProd> {

		@Override
		public PapplyRwProd invoke(final Object[] children, final Object[] annotations) {
			return new PapplyRwProd(children[0], children[1], annotations[0]);
		}
	};

}
