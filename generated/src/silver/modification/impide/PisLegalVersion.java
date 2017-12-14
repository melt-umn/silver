
package silver.modification.impide;

public final class PisLegalVersion extends common.FunctionNode {

	public static final int i_ver = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_isLegalVersion;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisLegalVersion(final Object c_ver) {
		this.child_ver = c_ver;

	}

	private Object child_ver;
	public final common.StringCatter getChild_ver() {
		return (common.StringCatter) (child_ver = common.Util.demand(child_ver));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ver: return getChild_ver();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ver: return child_ver;

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
		return "silver:modification:impide:isLegalVersion";
	}

	public static Boolean invoke(final Object c_ver) {
		try {
		final common.DecoratedNode context = new PisLegalVersion(c_ver).decorate();
		//(length(parts) == 2 || length(parts) == 3) && isAllDigital(parts)
		return (Boolean)(((((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.modification.impide.Init.parts__ON__silver_modification_impide_isLegalVersion))).equals(Integer.valueOf((int)2)) || ((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.modification.impide.Init.parts__ON__silver_modification_impide_isLegalVersion))).equals(Integer.valueOf((int)3))) && ((Boolean)silver.modification.impide.PisAllDigital.invoke(context.localAsIsLazy(silver.modification.impide.Init.parts__ON__silver_modification_impide_isLegalVersion)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:isLegalVersion", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisLegalVersion.invoke(children[0]);
		}
	};
}