
package silver.driver.util;

public final class PgetProdNt extends common.FunctionNode {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_getProdNt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PgetProdNt(final Object c_d) {
		this.child_d = c_d;

	}

	private Object child_d;
	public final silver.definition.env.NDclInfo getChild_d() {
		return (silver.definition.env.NDclInfo) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;

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
		return "silver:driver:util:getProdNt";
	}

	public static common.StringCatter invoke(final Object c_d) {
		try {
		final common.DecoratedNode context = new PgetProdNt(c_d).decorate();
		//d.namedSignature.outputElement.typerep.typeName
		return (common.StringCatter)(((common.StringCatter)((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)context.childDecorated(silver.driver.util.PgetProdNt.i_d).synthesized(silver.definition.env.Init.silver_definition_env_namedSignature__ON__silver_definition_env_DclInfo)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:getProdNt", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetProdNt.invoke(children[0]);
		}
	};
}