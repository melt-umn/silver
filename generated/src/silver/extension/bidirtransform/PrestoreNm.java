
package silver.extension.bidirtransform;

public final class PrestoreNm extends common.FunctionNode {

	public static final int i_rName = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_restoreNm;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PrestoreNm(final Object c_rName) {
		this.child_rName = c_rName;

	}

	private Object child_rName;
	public final common.StringCatter getChild_rName() {
		return (common.StringCatter) (child_rName = common.Util.demand(child_rName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rName: return getChild_rName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rName: return child_rName;

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
		return "silver:extension:bidirtransform:restoreNm";
	}

	public static common.StringCatter invoke(final Object c_rName) {
		try {
		final common.DecoratedNode context = new PrestoreNm(c_rName).decorate();
		//"restored_" ++ rName
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("restored_")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PrestoreNm.i_rName))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:restoreNm", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrestoreNm.invoke(children[0]);
		}
	};
}