
package silver.definition.env;

public final class PmapGetDcls extends common.FunctionNode {

	public static final int i_i = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_mapGetDcls;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmapGetDcls(final Object c_i) {
		this.child_i = c_i;

	}

	private Object child_i;
	public final common.ConsCell getChild_i() {
		return (common.ConsCell) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;

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
		return "silver:definition:env:mapGetDcls";
	}

	public static common.ConsCell invoke(final Object c_i) {
		try {
		final common.DecoratedNode context = new PmapGetDcls(c_i).decorate();
		//map((.dcl), i)
		return (common.ConsCell)(((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_EnvItem, context), context.childAsIsLazy(silver.definition.env.PmapGetDcls.i_i))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:mapGetDcls", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmapGetDcls.invoke(children[0]);
		}
	};
}