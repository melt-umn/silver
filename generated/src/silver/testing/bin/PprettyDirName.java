
package silver.testing.bin;

public final class PprettyDirName extends common.FunctionNode {

	public static final int i_dn = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_prettyDirName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PprettyDirName(final Object c_dn) {
		this.child_dn = c_dn;

	}

	private Object child_dn;
	public final common.StringCatter getChild_dn() {
		return (common.StringCatter) (child_dn = common.Util.demand(child_dn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dn: return getChild_dn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dn: return child_dn;

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
		return "silver:testing:bin:prettyDirName";
	}

	public static common.StringCatter invoke(final Object c_dn) {
		try {
		final common.DecoratedNode context = new PprettyDirName(c_dn).decorate();
		//if length(dn) < 70 then dn else substring(0, 69, dn,) ++ "\n   " ++ prettyDirName(substring(69, length(dn), dn,),)
		return (common.StringCatter)(((Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PprettyDirName.i_dn))).length()) < Integer.valueOf((int)70)) ? ((common.StringCatter)context.childAsIs(silver.testing.bin.PprettyDirName.i_dn)) : new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), Integer.valueOf((int)69), context.childAsIsLazy(silver.testing.bin.PprettyDirName.i_dn))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n   ")), (common.StringCatter)((common.StringCatter)silver.testing.bin.PprettyDirName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)69), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PprettyDirName.i_dn))).length()); } }, context.childAsIsLazy(silver.testing.bin.PprettyDirName.i_dn))); } }))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:bin:prettyDirName", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprettyDirName.invoke(children[0]);
		}
	};
}