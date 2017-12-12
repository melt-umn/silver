
package silver.definition.core;

// top::BlockContext ::= sig::NamedSignature g::ProductionGraph 
public final class PaspectFunctionContext extends silver.definition.core.NBlockContext {

	public static final int i_sig = 0;
	public static final int i_g = 1;


	public static final Class<?> childTypes[] = {silver.definition.env.NNamedSignature.class,silver.definition.flow.driver.NProductionGraph.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_aspectFunctionContext;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NBlockContext.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NBlockContext.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sig] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];
	childInheritedAttributes[i_g] = new common.Lazy[silver.definition.flow.driver.NProductionGraph.num_inh_attrs];

	}

	public PaspectFunctionContext(final Object c_sig, final Object c_g) {
		super();
		this.child_sig = c_sig;
		this.child_g = c_g;

	}

	private Object child_sig;
	public final silver.definition.env.NNamedSignature getChild_sig() {
		return (silver.definition.env.NNamedSignature) (child_sig = common.Util.demand(child_sig));
	}

	private Object child_g;
	public final silver.definition.flow.driver.NProductionGraph getChild_g() {
		return (silver.definition.flow.driver.NProductionGraph) (child_g = common.Util.demand(child_g));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sig: return getChild_sig();
			case i_g: return getChild_g();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sig: return child_sig;
			case i_g: return child_g;

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
		return ((silver.definition.core.NBlockContext)new silver.definition.core.PfunctionContext(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PaspectFunctionContext.i_sig)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PaspectFunctionContext.i_g))));
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
		return "silver:definition:core:aspectFunctionContext";
	}

	static void initProductionAttributeDefinitions() {
		// top.permitReturn = false
		silver.definition.core.PaspectFunctionContext.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_permitReturn__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<PaspectFunctionContext> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectFunctionContext> {

		@Override
		public PaspectFunctionContext invoke(final Object[] children, final Object[] annotations) {
			return new PaspectFunctionContext(children[0], children[1]);
		}
	};

}
