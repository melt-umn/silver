
package silver.testing.bin;

public final class PcleanDirName extends common.FunctionNode {

	public static final int i_dirName = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_cleanDirName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcleanDirName(final Object c_dirName) {
		this.child_dirName = c_dirName;

	}

	private Object child_dirName;
	public final common.StringCatter getChild_dirName() {
		return (common.StringCatter) (child_dirName = common.Util.demand(child_dirName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dirName: return getChild_dirName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dirName: return child_dirName;

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
		return "silver:testing:bin:cleanDirName";
	}

	public static common.StringCatter invoke(final Object c_dirName) {
		try {
		final common.DecoratedNode context = new PcleanDirName(c_dirName).decorate();
		//if endsWith("/", dirName,) then substring(0, length(dirName) - 1, dirName,) else if endsWith(" ", dirName,) then error("The current implementation of the test harness \n" ++ "that the arguments (\"args\") are separated by a \n" ++ "single space and thus no leading or trailing \n" ++ "spaces exist in the exploded list of arguments that \n" ++ "represent directory names.  But something when wrong \n" ++ "somewhere as a directory name now ends with a sapce. \n",) else dirName
		return (common.StringCatter)((((Boolean)core.PendsWith.invoke((new common.StringCatter("/")), context.childAsIsLazy(silver.testing.bin.PcleanDirName.i_dirName))) ? ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PcleanDirName.i_dirName))).length()) - Integer.valueOf((int)1)); } }, context.childAsIsLazy(silver.testing.bin.PcleanDirName.i_dirName))) : (((Boolean)core.PendsWith.invoke((new common.StringCatter(" ")), context.childAsIsLazy(silver.testing.bin.PcleanDirName.i_dirName))) ? ((common.StringCatter)core.Perror.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("The current implementation of the test harness \n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("that the arguments (\"args\") are separated by a \n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("single space and thus no leading or trailing \n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("spaces exist in the exploded list of arguments that \n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("represent directory names.  But something when wrong \n")), (common.StringCatter)(new common.StringCatter("somewhere as a directory name now ends with a sapce. \n"))))))); } })) : ((common.StringCatter)context.childAsIs(silver.testing.bin.PcleanDirName.i_dirName)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:bin:cleanDirName", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcleanDirName.invoke(children[0]);
		}
	};
}