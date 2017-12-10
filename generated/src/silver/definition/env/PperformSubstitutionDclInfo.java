
package silver.definition.env;

public final class PperformSubstitutionDclInfo extends common.FunctionNode {

	public static final int i_valueDclInfo = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,silver.definition.type.NSubstitution.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_performSubstitutionDclInfo;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_valueDclInfo] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];

	}

	public PperformSubstitutionDclInfo(final Object c_valueDclInfo, final Object c_s) {
		this.child_valueDclInfo = c_valueDclInfo;
		this.child_s = c_s;

	}

	private Object child_valueDclInfo;
	public final silver.definition.env.NDclInfo getChild_valueDclInfo() {
		return (silver.definition.env.NDclInfo) (child_valueDclInfo = common.Util.demand(child_valueDclInfo));
	}

	private Object child_s;
	public final silver.definition.type.NSubstitution getChild_s() {
		return (silver.definition.type.NSubstitution) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_valueDclInfo: return getChild_valueDclInfo();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_valueDclInfo: return child_valueDclInfo;
			case i_s: return child_s;

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
		return "silver:definition:env:performSubstitutionDclInfo";
	}

	public static silver.definition.env.NDclInfo invoke(final Object c_valueDclInfo, final Object c_s) {
		try {
		final common.DecoratedNode context = new PperformSubstitutionDclInfo(c_valueDclInfo, c_s).decorate();
		//valueDclInfo.substitutedDclInfo
		return (silver.definition.env.NDclInfo)(((silver.definition.env.NDclInfo)context.childDecorated(silver.definition.env.PperformSubstitutionDclInfo.i_valueDclInfo).synthesized(silver.definition.env.Init.silver_definition_env_substitutedDclInfo__ON__silver_definition_env_DclInfo)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:performSubstitutionDclInfo", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NDclInfo> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NDclInfo> {
		@Override
		public silver.definition.env.NDclInfo invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PperformSubstitutionDclInfo.invoke(children[0], children[1]);
		}
	};
}