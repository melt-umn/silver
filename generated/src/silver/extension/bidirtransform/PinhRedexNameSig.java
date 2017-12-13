
package silver.extension.bidirtransform;

public final class PinhRedexNameSig extends common.FunctionNode {

	public static final int i_ns = 0;
	public static final int i_allowedTypes = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_inhRedexNameSig;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PinhRedexNameSig(final Object c_ns, final Object c_allowedTypes) {
		this.child_ns = c_ns;
		this.child_allowedTypes = c_allowedTypes;

	}

	private Object child_ns;
	public final common.DecoratedNode getChild_ns() {
		return (common.DecoratedNode) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_allowedTypes;
	public final common.ConsCell getChild_allowedTypes() {
		return (common.ConsCell) (child_allowedTypes = common.Util.demand(child_allowedTypes));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();
			case i_allowedTypes: return getChild_allowedTypes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;
			case i_allowedTypes: return child_allowedTypes;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:extension:bidirtransform:inhRedexNameSig";
	}

	public static common.StringCatter invoke(final Object c_ns, final Object c_allowedTypes) {
		try {
		final common.DecoratedNode context = new PinhRedexNameSig(c_ns, c_allowedTypes).decorate();
		//if null(ns.inputElements) then def else if contains(unFull(hd.typerep.typeName), allowedTypes) then hd.elementName else def
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PinhRedexNameSig.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))) ? ((common.StringCatter)context.localAsIs(silver.extension.bidirtransform.Init.def__ON__silver_extension_bidirtransform_inhRedexNameSig)) : (((Boolean)silver.util.Pcontains.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.localDecorated(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_inhRedexNameSig).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })); } }, context.childAsIsLazy(silver.extension.bidirtransform.PinhRedexNameSig.i_allowedTypes))) ? ((common.StringCatter)context.localDecorated(silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_inhRedexNameSig).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)) : ((common.StringCatter)context.localAsIs(silver.extension.bidirtransform.Init.def__ON__silver_extension_bidirtransform_inhRedexNameSig)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:inhRedexNameSig", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinhRedexNameSig.invoke(children[0], children[1]);
		}
	};
}