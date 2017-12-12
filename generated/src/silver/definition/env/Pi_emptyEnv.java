
package silver.definition.env;

// top::Env ::= 
public final class Pi_emptyEnv extends silver.definition.env.NEnv {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_i_emptyEnv;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NEnv.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NEnv.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pi_emptyEnv() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:i_emptyEnv erroneously claimed to forward");
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
		return "silver:definition:env:i_emptyEnv";
	}

	static void initProductionAttributeDefinitions() {
		// top.typeTree = [ emptyEnvScope() ]
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnvScope.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.valueTree = [ emptyEnvScope() ]
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_valueTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnvScope.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.attrTree = [ emptyEnvScope() ]
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_attrTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnvScope.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.prodOccursTree = emptyEnvScope()
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodOccursTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnvScope.invoke()); } };
		// top.occursTree = emptyEnvScope()
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_occursTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnvScope.invoke()); } };
		// top.prodsForNtTree = [ emptyEnvScope() ]
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodsForNtTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnvScope.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<Pi_emptyEnv> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pi_emptyEnv> {

		@Override
		public Pi_emptyEnv invoke(final Object[] children, final Object[] annotations) {
			return new Pi_emptyEnv();
		}
	};

}
