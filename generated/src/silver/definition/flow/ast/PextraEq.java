
package silver.definition.flow.ast;

// top::FlowDef ::= prod::String src::FlowVertex deps::[FlowVertex] mayAffectFlowType::Boolean 
public final class PextraEq extends silver.definition.flow.ast.NFlowDef {

	public static final int i_prod = 0;
	public static final int i_src = 1;
	public static final int i_deps = 2;
	public static final int i_mayAffectFlowType = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.flow.ast.NFlowVertex.class,common.DecoratedNode.class,Boolean.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_extraEq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_src] = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_inh_attrs];

	}

	public PextraEq(final Object c_prod, final Object c_src, final Object c_deps, final Object c_mayAffectFlowType) {
		super();
		this.child_prod = c_prod;
		this.child_src = c_src;
		this.child_deps = c_deps;
		this.child_mayAffectFlowType = c_mayAffectFlowType;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_src;
	public final silver.definition.flow.ast.NFlowVertex getChild_src() {
		return (silver.definition.flow.ast.NFlowVertex) (child_src = common.Util.demand(child_src));
	}

	private Object child_deps;
	public final common.ConsCell getChild_deps() {
		return (common.ConsCell) (child_deps = common.Util.demand(child_deps));
	}

	private Object child_mayAffectFlowType;
	public final Boolean getChild_mayAffectFlowType() {
		return (Boolean) (child_mayAffectFlowType = common.Util.demand(child_mayAffectFlowType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_src: return getChild_src();
			case i_deps: return getChild_deps();
			case i_mayAffectFlowType: return getChild_mayAffectFlowType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_src: return child_src;
			case i_deps: return child_deps;
			case i_mayAffectFlowType: return child_mayAffectFlowType;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:extraEq erroneously claimed to forward");
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
		return "silver:definition:flow:ast:extraEq";
	}

	static void initProductionAttributeDefinitions() {
		// top.prodGraphContribs = [ pair(prod, top) ]
		silver.definition.flow.ast.PextraEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.flow.ast.PextraEq.i_prod), context.undecorate())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// edges = map(pair(src, _), deps)
		silver.definition.flow.ast.PextraEq.localAttributes[silver.definition.flow.ast.Init.edges__ON__silver_definition_flow_ast_extraEq] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return core.Ppair.factory.invokePartial(new int[]{0}, new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.flow.ast.PextraEq.i_src))}); } }, context.childAsIsLazy(silver.definition.flow.ast.PextraEq.i_deps))); } };
		// top.flowEdges = if mayAffectFlowType then edges else []
		silver.definition.flow.ast.PextraEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_flowEdges__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childAsIs(silver.definition.flow.ast.PextraEq.i_mayAffectFlowType)) ? ((common.ConsCell)context.localAsIs(silver.definition.flow.ast.Init.edges__ON__silver_definition_flow_ast_extraEq)) : ((common.ConsCell)core.Pnil.invoke())); } };
		// top.suspectFlowEdges = if mayAffectFlowType then [] else edges
		silver.definition.flow.ast.PextraEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_suspectFlowEdges__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childAsIs(silver.definition.flow.ast.PextraEq.i_mayAffectFlowType)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)context.localAsIs(silver.definition.flow.ast.Init.edges__ON__silver_definition_flow_ast_extraEq))); } };
		// top.unparses = [ "extra(" ++ implode(", ", [ quoteString(prod), src.unparse, unparseVertices(deps), if mayAffectFlowType then "t" else "f" ]) ++ ")" ]
		silver.definition.flow.ast.PextraEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("extra(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PextraEq.i_prod))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PextraEq.i_src, silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.flow.ast.PunparseVertices.invoke(context.childAsIsLazy(silver.definition.flow.ast.PextraEq.i_deps))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)context.childAsIs(silver.definition.flow.ast.PextraEq.i_mayAffectFlowType)) ? (new common.StringCatter("t")) : (new common.StringCatter("f"))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })), (common.StringCatter)(new common.StringCatter(")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PextraEq> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PextraEq> {

		@Override
		public PextraEq invoke(final Object[] children, final Object[] annotations) {
			return new PextraEq(children[0], children[1], children[2], children[3]);
		}
	};

}
