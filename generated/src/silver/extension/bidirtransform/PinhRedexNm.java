
package silver.extension.bidirtransform;

public final class PinhRedexNm extends common.FunctionNode {

	public static final int i_tName = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_inhRedexNm;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PinhRedexNm(final Object c_tName) {
		this.child_tName = c_tName;

	}

	private Object child_tName;
	public final common.StringCatter getChild_tName() {
		return (common.StringCatter) (child_tName = common.Util.demand(child_tName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tName: return getChild_tName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tName: return child_tName;

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
		return "silver:extension:bidirtransform:inhRedexNm";
	}

	public static common.StringCatter invoke(final Object c_tName) {
		try {
		final common.DecoratedNode context = new PinhRedexNm(c_tName).decorate();
		//"inhRedex_" ++ tName
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("inhRedex_")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PinhRedexNm.i_tName))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:inhRedexNm", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinhRedexNm.invoke(children[0]);
		}
	};
}