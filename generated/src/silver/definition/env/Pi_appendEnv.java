
package silver.definition.env;

// top::Env ::= e1::Decorated Env e2::Decorated Env 
public final class Pi_appendEnv extends silver.definition.env.NEnv {

	public static final int i_e1 = 0;
	public static final int i_e2 = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_i_appendEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NEnv.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NEnv.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pi_appendEnv(final Object c_e1, final Object c_e2) {
		super();
		this.child_e1 = c_e1;
		this.child_e2 = c_e2;

	}

	private Object child_e1;
	public final common.DecoratedNode getChild_e1() {
		return (common.DecoratedNode) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child_e2;
	public final common.DecoratedNode getChild_e2() {
		return (common.DecoratedNode) (child_e2 = common.Util.demand(child_e2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e1: return getChild_e1();
			case i_e2: return getChild_e2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e1: return child_e1;
			case i_e2: return child_e2;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:i_appendEnv erroneously claimed to forward");
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
		return "silver:definition:env:i_appendEnv";
	}

	static void initProductionAttributeDefinitions() {
		// top.typeTree = e1.typeTree ++ e2.typeTree
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.definition.env.Init.silver_definition_env_typeTree__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.definition.env.Init.silver_definition_env_typeTree__ON__silver_definition_env_Env))); } };
		// top.valueTree = e1.valueTree ++ e2.valueTree
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_valueTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.definition.env.Init.silver_definition_env_valueTree__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.definition.env.Init.silver_definition_env_valueTree__ON__silver_definition_env_Env))); } };
		// top.attrTree = e1.attrTree ++ e2.attrTree
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_attrTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.definition.env.Init.silver_definition_env_attrTree__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.definition.env.Init.silver_definition_env_attrTree__ON__silver_definition_env_Env))); } };
		// top.prodOccursTree = appendEnvScope(e1.prodOccursTree, e2.prodOccursTree)
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodOccursTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PappendEnvScope.invoke(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.definition.env.Init.silver_definition_env_prodOccursTree__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.definition.env.Init.silver_definition_env_prodOccursTree__ON__silver_definition_env_Env))); } };
		// top.occursTree = appendEnvScope(e1.occursTree, e2.occursTree)
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_occursTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PappendEnvScope.invoke(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.definition.env.Init.silver_definition_env_occursTree__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.definition.env.Init.silver_definition_env_occursTree__ON__silver_definition_env_Env))); } };
		// top.prodsForNtTree = e1.prodsForNtTree ++ e2.prodsForNtTree
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodsForNtTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.definition.env.Init.silver_definition_env_prodsForNtTree__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.definition.env.Init.silver_definition_env_prodsForNtTree__ON__silver_definition_env_Env))); } };

	}

	public static final common.NodeFactory<Pi_appendEnv> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pi_appendEnv> {

		@Override
		public Pi_appendEnv invoke(final Object[] children, final Object[] annotations) {
			return new Pi_appendEnv(children[0], children[1]);
		}
	};

}
