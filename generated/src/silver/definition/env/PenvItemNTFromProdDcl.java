
package silver.definition.env;

public final class PenvItemNTFromProdDcl extends common.FunctionNode {

	public static final int i_di = 0;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_envItemNTFromProdDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_di] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PenvItemNTFromProdDcl(final Object c_di) {
		this.child_di = c_di;

	}

	private Object child_di;
	public final silver.definition.env.NDclInfo getChild_di() {
		return (silver.definition.env.NDclInfo) (child_di = common.Util.demand(child_di));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_di: return getChild_di();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_di: return child_di;

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
		return "silver:definition:env:envItemNTFromProdDcl";
	}

	public static silver.definition.env.NEnvItem invoke(final Object c_di) {
		try {
		final common.DecoratedNode context = new PenvItemNTFromProdDcl(c_di).decorate();
		//onlyRenamedEnvItem(di.namedSignature.outputElement.typerep.typeName, di)
		return (silver.definition.env.NEnvItem)(((silver.definition.env.NEnvItem)new silver.definition.env.PonlyRenamedEnvItem(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)context.childDecorated(silver.definition.env.PenvItemNTFromProdDcl.i_di).synthesized(silver.definition.env.Init.silver_definition_env_namedSignature__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PenvItemNTFromProdDcl.i_di)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:envItemNTFromProdDcl", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NEnvItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NEnvItem> {
		@Override
		public silver.definition.env.NEnvItem invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PenvItemNTFromProdDcl.invoke(children[0]);
		}
	};
}