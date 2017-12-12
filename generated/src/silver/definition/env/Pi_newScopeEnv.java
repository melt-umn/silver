
package silver.definition.env;

// top::Env ::= d::Defs e::Decorated Env 
public final class Pi_newScopeEnv extends silver.definition.env.NEnv {

	public static final int i_d = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = {silver.definition.env.NDefs.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_i_newScopeEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NEnv.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NEnv.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDefs.num_inh_attrs];

	}

	public Pi_newScopeEnv(final Object c_d, final Object c_e) {
		super();
		this.child_d = c_d;
		this.child_e = c_e;

	}

	private Object child_d;
	public final silver.definition.env.NDefs getChild_d() {
		return (silver.definition.env.NDefs) (child_d = common.Util.demand(child_d));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;
			case i_e: return child_e;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:i_newScopeEnv erroneously claimed to forward");
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
		return "silver:definition:env:i_newScopeEnv";
	}

	static void initProductionAttributeDefinitions() {
		// top.typeTree = (oneEnvScope(buildTree(d.typeList)) :: e.typeTree)
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PoneEnvScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PbuildTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.definition.env.Init.silver_definition_env_typeList__ON__silver_definition_env_Defs))); } })); } }, context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.definition.env.Init.silver_definition_env_typeTree__ON__silver_definition_env_Env))); } };
		// top.valueTree = (oneEnvScope(buildTree(d.valueList)) :: e.valueTree)
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_valueTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PoneEnvScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PbuildTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.definition.env.Init.silver_definition_env_valueList__ON__silver_definition_env_Defs))); } })); } }, context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.definition.env.Init.silver_definition_env_valueTree__ON__silver_definition_env_Env))); } };
		// top.attrTree = (oneEnvScope(buildTree(d.attrList)) :: e.attrTree)
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_attrTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PoneEnvScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PbuildTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.definition.env.Init.silver_definition_env_attrList__ON__silver_definition_env_Defs))); } })); } }, context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.definition.env.Init.silver_definition_env_attrTree__ON__silver_definition_env_Env))); } };
		// top.prodOccursTree = consEnvScope(buildTree(mapFullnameDcls(d.prodOccursList)), e.prodOccursTree)
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodOccursTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PconsEnvScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PbuildTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PmapFullnameDcls.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.definition.env.Init.silver_definition_env_prodOccursList__ON__silver_definition_env_Defs))); } })); } }, context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.definition.env.Init.silver_definition_env_prodOccursTree__ON__silver_definition_env_Env))); } };
		// top.occursTree = consEnvScope(buildTree(mapFullnameDcls(d.occursList)), e.occursTree)
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_occursTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PconsEnvScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PbuildTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PmapFullnameDcls.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.definition.env.Init.silver_definition_env_occursList__ON__silver_definition_env_Defs))); } })); } }, context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.definition.env.Init.silver_definition_env_occursTree__ON__silver_definition_env_Env))); } };
		// top.prodsForNtTree = (oneEnvScope(buildTree(map(envItemNTFromProdDcl, d.prodDclList))) :: e.prodsForNtTree)
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodsForNtTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PoneEnvScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PbuildTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.env.PenvItemNTFromProdDcl.factory, context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.definition.env.Init.silver_definition_env_prodDclList__ON__silver_definition_env_Defs))); } })); } })); } }, context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.definition.env.Init.silver_definition_env_prodsForNtTree__ON__silver_definition_env_Env))); } };

	}

	public static final common.NodeFactory<Pi_newScopeEnv> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pi_newScopeEnv> {

		@Override
		public Pi_newScopeEnv invoke(final Object[] children, final Object[] annotations) {
			return new Pi_newScopeEnv(children[0], children[1]);
		}
	};

}
