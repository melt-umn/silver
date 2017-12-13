
package silver.modification.defaultattr;

// top::BlockContext ::= sig::NamedSignature 
public final class PdefaultAspectContext extends silver.definition.core.NBlockContext {

	public static final int i_sig = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.NNamedSignature.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_defaultattr_defaultAspectContext;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NBlockContext.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NBlockContext.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sig] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];

	}

	public PdefaultAspectContext(final Object c_sig) {
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:modification:defaultattr:defaultAspectContext erroneously claimed to forward");
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
		return "silver:modification:defaultattr:defaultAspectContext";
	}

	static void initProductionAttributeDefinitions() {
		// top.fullName = sig.fullName
		silver.modification.defaultattr.PdefaultAspectContext.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.defaultattr.PdefaultAspectContext.i_sig).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)); } };
		// top.lhsNtName = sig.outputElement.typerep.typeName
		silver.modification.defaultattr.PdefaultAspectContext.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_lhsNtName__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.childDecorated(silver.modification.defaultattr.PdefaultAspectContext.i_sig).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } };
		// top.signature = sig
		silver.modification.defaultattr.PdefaultAspectContext.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_signature__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.modification.defaultattr.PdefaultAspectContext.i_sig).undecorate(); } };

	}

	public static final common.NodeFactory<PdefaultAspectContext> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdefaultAspectContext> {

		@Override
		public PdefaultAspectContext invoke(final Object[] children, final Object[] annotations) {
			return new PdefaultAspectContext(children[0]);
		}
	};

}
