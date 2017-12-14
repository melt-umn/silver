
package silver.extension.bidirtransform;

public final class PmkOriginName extends common.FunctionNode {

	public static final int i_typeName = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_mkOriginName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmkOriginName(final Object c_typeName) {
		this.child_typeName = c_typeName;

	}

	private Object child_typeName;
	public final common.StringCatter getChild_typeName() {
		return (common.StringCatter) (child_typeName = common.Util.demand(child_typeName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_typeName: return getChild_typeName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_typeName: return child_typeName;

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
		return "silver:extension:bidirtransform:mkOriginName";
	}

	public static common.StringCatter invoke(final Object c_typeName) {
		try {
		final common.DecoratedNode context = new PmkOriginName(c_typeName).decorate();
		//"origin_" ++ typeName
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("origin_")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PmkOriginName.i_typeName))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:mkOriginName", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmkOriginName.invoke(children[0]);
		}
	};
}