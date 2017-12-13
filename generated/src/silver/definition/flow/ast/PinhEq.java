
package silver.definition.flow.ast;

// top::FlowDef ::= prod::String sigName::String attr::String deps::[FlowVertex] 
public final class PinhEq extends silver.definition.flow.ast.NFlowDef {

	public static final int i_prod = 0;
	public static final int i_sigName = 1;
	public static final int i_attr = 2;
	public static final int i_deps = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_inhEq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PinhEq(final Object c_prod, final Object c_sigName, final Object c_attr, final Object c_deps) {
		super();
		this.child_prod = c_prod;
		this.child_sigName = c_sigName;
		this.child_attr = c_attr;
		this.child_deps = c_deps;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_sigName;
	public final common.StringCatter getChild_sigName() {
		return (common.StringCatter) (child_sigName = common.Util.demand(child_sigName));
	}

	private Object child_attr;
	public final common.StringCatter getChild_attr() {
		return (common.StringCatter) (child_attr = common.Util.demand(child_attr));
	}

	private Object child_deps;
	public final common.ConsCell getChild_deps() {
		return (common.ConsCell) (child_deps = common.Util.demand(child_deps));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_sigName: return getChild_sigName();
			case i_attr: return getChild_attr();
			case i_deps: return getChild_deps();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_sigName: return child_sigName;
			case i_attr: return child_attr;
			case i_deps: return child_deps;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:inhEq erroneously claimed to forward");
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
		return "silver:definition:flow:ast:inhEq";
	}

	static void initProductionAttributeDefinitions() {
		// top.inhTreeContribs = [ pair(crossnames(prod, crossnames(sigName, attr)), top) ]
		silver.definition.flow.ast.PinhEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.flow.ast.Pcrossnames.invoke(context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_prod), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.flow.ast.Pcrossnames.invoke(context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_sigName), context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_attr))); } })); } }, context.undecorate())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.prodGraphContribs = [ pair(prod, top) ]
		silver.definition.flow.ast.PinhEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_prod), context.undecorate())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.flowEdges = map(pair(rhsVertex(sigName, attr), _), deps)
		silver.definition.flow.ast.PinhEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_flowEdges__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return core.Ppair.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PrhsVertex(context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_sigName), context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_attr))); } }}); } }, context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_deps))); } };
		// top.unparses = [ "inh(" ++ implode(", ", [ quoteString(prod), quoteString(sigName), quoteString(attr), unparseVertices(deps) ]) ++ ")" ]
		silver.definition.flow.ast.PinhEq.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("inh(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_prod))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_sigName))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_attr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.flow.ast.PunparseVertices.invoke(context.childAsIsLazy(silver.definition.flow.ast.PinhEq.i_deps))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })), (common.StringCatter)(new common.StringCatter(")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PinhEq> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PinhEq> {

		@Override
		public PinhEq invoke(final Object[] children, final Object[] annotations) {
			return new PinhEq(children[0], children[1], children[2], children[3]);
		}
	};

}
