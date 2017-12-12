
package silver.definition.env;

public final class PdetermineAttributeType extends common.FunctionNode {

	public static final int i_occursDclInfo = 0;
	public static final int i_ntty = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,silver.definition.type.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_determineAttributeType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_occursDclInfo] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];
	childInheritedAttributes[i_ntty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PdetermineAttributeType(final Object c_occursDclInfo, final Object c_ntty) {
		this.child_occursDclInfo = c_occursDclInfo;
		this.child_ntty = c_ntty;

	}

	private Object child_occursDclInfo;
	public final silver.definition.env.NDclInfo getChild_occursDclInfo() {
		return (silver.definition.env.NDclInfo) (child_occursDclInfo = common.Util.demand(child_occursDclInfo));
	}

	private Object child_ntty;
	public final silver.definition.type.NType getChild_ntty() {
		return (silver.definition.type.NType) (child_ntty = common.Util.demand(child_ntty));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_occursDclInfo: return getChild_occursDclInfo();
			case i_ntty: return getChild_ntty();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_occursDclInfo: return child_occursDclInfo;
			case i_ntty: return child_ntty;

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
		return "silver:definition:env:determineAttributeType";
	}

	public static silver.definition.type.NType invoke(final Object c_occursDclInfo, final Object c_ntty) {
		try {
		final common.DecoratedNode context = new PdetermineAttributeType(c_occursDclInfo, c_ntty).decorate();
		//occursDclInfo.typerep
		return (silver.definition.type.NType)(((silver.definition.type.NType)context.childDecorated(silver.definition.env.PdetermineAttributeType.i_occursDclInfo).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:determineAttributeType", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NType> {
		@Override
		public silver.definition.type.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdetermineAttributeType.invoke(children[0], children[1]);
		}
	};
}