
package silver.extension.bidirtransform;

public final class PinhRedexNameSig extends common.FunctionNode {

	public static final int i_ns = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_inhRedexNameSig;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PinhRedexNameSig(final Object c_ns) {
		this.child_ns = c_ns;

	}

	private Object child_ns;
	public final common.DecoratedNode getChild_ns() {
		return (common.DecoratedNode) (child_ns = common.Util.demand(child_ns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ns: return getChild_ns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ns: return child_ns;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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

	public static common.StringCatter invoke(final Object c_ns) {
		try {
		final common.DecoratedNode context = new PinhRedexNameSig(c_ns).decorate();
		//if ! null(ns.inputElements) then validInhRedex(head(ns.inputNames), ns.outputElement.elementName) else ns.outputElement.elementName
		return (common.StringCatter)(((!((Boolean)core.Pnull.invoke(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PinhRedexNameSig.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)))) ? ((common.StringCatter)silver.extension.bidirtransform.PvalidInhRedex.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PinhRedexNameSig.i_ns, silver.definition.env.Init.silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PinhRedexNameSig.i_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } })) : ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PinhRedexNameSig.i_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:inhRedexNameSig", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinhRedexNameSig.invoke(children[0]);
		}
	};
}