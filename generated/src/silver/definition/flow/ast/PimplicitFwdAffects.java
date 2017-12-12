
package silver.definition.flow.ast;

// top::FlowDef ::= prod::String attrs::[String] 
public final class PimplicitFwdAffects extends silver.definition.flow.ast.NFlowDef {

	public static final int i_prod = 0;
	public static final int i_attrs = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_implicitFwdAffects;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PimplicitFwdAffects(final Object c_prod, final Object c_attrs) {
		super();
		this.child_prod = c_prod;
		this.child_attrs = c_attrs;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_attrs;
	public final common.ConsCell getChild_attrs() {
		return (common.ConsCell) (child_attrs = common.Util.demand(child_attrs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_attrs: return getChild_attrs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_attrs: return child_attrs;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:implicitFwdAffects erroneously claimed to forward");
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
		return "silver:definition:flow:ast:implicitFwdAffects";
	}

	static void initProductionAttributeDefinitions() {
		// top.nonSuspectContribs = [ pair(prod, attrs) ]
		silver.definition.flow.ast.PimplicitFwdAffects.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.flow.ast.PimplicitFwdAffects.i_prod), context.childAsIsLazy(silver.definition.flow.ast.PimplicitFwdAffects.i_attrs))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.prodGraphContribs = []
		silver.definition.flow.ast.PimplicitFwdAffects.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.flowEdges = error("Internal compiler error: this sort of def should not be in a context where edges are requested.")
		silver.definition.flow.ast.PimplicitFwdAffects.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_flowEdges__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Internal compiler error: this sort of def should not be in a context where edges are requested.")))); } };
		// top.unparses = [ "implicitFwdAffects(" ++ quoteString(prod) ++ ", " ++ unparseStrings(attrs) ++ ")" ]
		silver.definition.flow.ast.PimplicitFwdAffects.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("implicitFwdAffects(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PimplicitFwdAffects.i_prod))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsLazy(silver.definition.flow.ast.PimplicitFwdAffects.i_attrs))), (common.StringCatter)(new common.StringCatter(")")))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PimplicitFwdAffects> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PimplicitFwdAffects> {

		@Override
		public PimplicitFwdAffects invoke(final Object[] children, final Object[] annotations) {
			return new PimplicitFwdAffects(children[0], children[1]);
		}
	};

}
