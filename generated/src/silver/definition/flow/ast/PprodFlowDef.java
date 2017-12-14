
package silver.definition.flow.ast;

// top::FlowDef ::= nt::String prod::String 
public final class PprodFlowDef extends silver.definition.flow.ast.NFlowDef {

	public static final int i_nt = 0;
	public static final int i_prod = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_prodFlowDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprodFlowDef(final Object c_nt, final Object c_prod) {
		super();
		this.child_nt = c_nt;
		this.child_prod = c_prod;

	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_prod: return getChild_prod();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_prod: return child_prod;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:prodFlowDef erroneously claimed to forward");
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
		return "silver:definition:flow:ast:prodFlowDef";
	}

	static void initProductionAttributeDefinitions() {
		// top.prodTreeContribs = [ pair(nt, top) ]
		silver.definition.flow.ast.PprodFlowDef.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.flow.ast.PprodFlowDef.i_nt), context.undecorate())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.prodGraphContribs = []
		silver.definition.flow.ast.PprodFlowDef.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.flowEdges = error("Internal compiler error: this sort of def should not be in a context where edges are requested.")
		silver.definition.flow.ast.PprodFlowDef.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_flowEdges__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Internal compiler error: this sort of def should not be in a context where edges are requested.")))); } };
		// top.unparses = [ "prod(" ++ quoteString(nt) ++ ", " ++ quoteString(prod) ++ ")" ]
		silver.definition.flow.ast.PprodFlowDef.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("prod(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PprodFlowDef.i_nt))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PprodFlowDef.i_prod))), (common.StringCatter)(new common.StringCatter(")")))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PprodFlowDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprodFlowDef> {

		@Override
		public PprodFlowDef invoke(final Object[] children, final Object[] annotations) {
			return new PprodFlowDef(children[0], children[1]);
		}
	};

}
