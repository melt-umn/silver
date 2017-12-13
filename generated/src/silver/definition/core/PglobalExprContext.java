
package silver.definition.core;

// top::BlockContext ::= 
public final class PglobalExprContext extends silver.definition.core.NBlockContext {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_globalExprContext;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NBlockContext.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NBlockContext.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PglobalExprContext() {
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:globalExprContext erroneously claimed to forward");
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
		return "silver:definition:core:globalExprContext";
	}

	static void initProductionAttributeDefinitions() {
		// top.fullName = "_NULL_"
		silver.definition.core.PglobalExprContext.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("_NULL_")); } };
		// top.signature = bogusNamedSignature()
		silver.definition.core.PglobalExprContext.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_signature__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)new silver.definition.env.PbogusNamedSignature()); } };

	}

	public static final common.NodeFactory<PglobalExprContext> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PglobalExprContext> {

		@Override
		public PglobalExprContext invoke(final Object[] children, final Object[] annotations) {
			return new PglobalExprContext();
		}
	};

}
