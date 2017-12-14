
package silver.definition.flow.ast;

// top::FlowDef ::= prod::String matchProd::String scrutinee::VertexType vars::[PatternVarProjection] 
public final class PpatternRuleEq extends silver.definition.flow.ast.NFlowDef {

	public static final int i_prod = 0;
	public static final int i_matchProd = 1;
	public static final int i_scrutinee = 2;
	public static final int i_vars = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,silver.definition.flow.ast.NVertexType.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_patternRuleEq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_scrutinee] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];

	}

	public PpatternRuleEq(final Object c_prod, final Object c_matchProd, final Object c_scrutinee, final Object c_vars) {
		super();
		this.child_prod = c_prod;
		this.child_matchProd = c_matchProd;
		this.child_scrutinee = c_scrutinee;
		this.child_vars = c_vars;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_matchProd;
	public final common.StringCatter getChild_matchProd() {
		return (common.StringCatter) (child_matchProd = common.Util.demand(child_matchProd));
	}

	private Object child_scrutinee;
	public final silver.definition.flow.ast.NVertexType getChild_scrutinee() {
		return (silver.definition.flow.ast.NVertexType) (child_scrutinee = common.Util.demand(child_scrutinee));
	}

	private Object child_vars;
	public final common.ConsCell getChild_vars() {
		return (common.ConsCell) (child_vars = common.Util.demand(child_vars));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_matchProd: return getChild_matchProd();
			case i_scrutinee: return getChild_scrutinee();
			case i_vars: return getChild_vars();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_matchProd: return child_matchProd;
			case i_scrutinee: return child_scrutinee;
			case i_vars: return child_vars;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:patternRuleEq erroneously claimed to forward");
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
		return "silver:definition:flow:ast:patternRuleEq";
	}

	static void initProductionAttributeDefinitions() {
		// top.prodGraphContribs = [ pair(prod, top) ]
		silver.definition.flow.ast.PpatternRuleEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.flow.ast.PpatternRuleEq.i_prod), context.undecorate())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.flowEdges = []
		silver.definition.flow.ast.PpatternRuleEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_flowEdges__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.unparses = [ "patternRuleEq(" ++ implode(", ", [ quoteString(prod), quoteString(matchProd), scrutinee.unparse, "[" ++ implode(", ", map((.unparse), vars)) ++ "]" ]) ++ ")" ]
		silver.definition.flow.ast.PpatternRuleEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("patternRuleEq(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PpatternRuleEq.i_prod))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PpatternRuleEq.i_matchProd))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PpatternRuleEq.i_scrutinee, silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_VertexType), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_PatternVarProjection, context), context.childAsIsLazy(silver.definition.flow.ast.PpatternRuleEq.i_vars))); } })), (common.StringCatter)(new common.StringCatter("]")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })), (common.StringCatter)(new common.StringCatter(")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PpatternRuleEq> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpatternRuleEq> {

		@Override
		public PpatternRuleEq invoke(final Object[] children, final Object[] annotations) {
			return new PpatternRuleEq(children[0], children[1], children[2], children[3]);
		}
	};

}
