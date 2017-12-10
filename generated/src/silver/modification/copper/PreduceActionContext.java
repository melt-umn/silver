
package silver.modification.copper;

// top::BlockContext ::= sig::NamedSignature 
public final class PreduceActionContext extends silver.definition.core.NBlockContext {

	public static final int i_sig = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.NNamedSignature.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_reduceActionContext;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NBlockContext.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NBlockContext.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sig] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];

	}

	public PreduceActionContext(final Object c_sig) {
		super();
		this.child_sig = c_sig;

	}

	private Object child_sig;
	public final silver.definition.env.NNamedSignature getChild_sig() {
		return (silver.definition.env.NNamedSignature) (child_sig = common.Util.demand(child_sig));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sig: return getChild_sig();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sig: return child_sig;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return ((silver.definition.core.NBlockContext)new silver.modification.copper.PactionContext());
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
		return "silver:modification:copper:reduceActionContext";
	}

	static void initProductionAttributeDefinitions() {
		// top.fullName = sig.fullName
		silver.modification.copper.PreduceActionContext.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.copper.PreduceActionContext.i_sig).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)); } };
		// top.signature = sig
		silver.modification.copper.PreduceActionContext.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_signature__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.modification.copper.PreduceActionContext.i_sig).undecorate(); } };
		// top.className = makeClassName(top.fullName)
		silver.modification.copper.PreduceActionContext.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_className__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeClassName.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_BlockContext))); } };

	}

	public static final common.NodeFactory<PreduceActionContext> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PreduceActionContext> {

		@Override
		public PreduceActionContext invoke(final Object[] children, final Object[] annotations) {
			return new PreduceActionContext(children[0]);
		}
	};

}
