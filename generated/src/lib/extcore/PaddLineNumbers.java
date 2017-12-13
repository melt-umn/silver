
package lib.extcore;

public final class PaddLineNumbers extends common.FunctionNode {

	public static final int i_code = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_extcore_addLineNumbers;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PaddLineNumbers(final Object c_code) {
		this.child_code = c_code;

	}

	private Object child_code;
	public final common.StringCatter getChild_code() {
		return (common.StringCatter) (child_code = common.Util.demand(child_code));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_code: return getChild_code();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_code: return child_code;

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
		return "lib:extcore:addLineNumbers";
	}

	public static common.StringCatter invoke(final Object c_code) {
		try {
		final common.DecoratedNode context = new PaddLineNumbers(c_code).decorate();
		//addLineNums(1, 2, lines)
		return (common.StringCatter)(((common.StringCatter)lib.extcore.PaddLineNums.invoke(Integer.valueOf((int)1), Integer.valueOf((int)2), context.localAsIsLazy(lib.extcore.Init.lines__ON__lib_extcore_addLineNumbers))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:extcore:addLineNumbers", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaddLineNumbers.invoke(children[0]);
		}
	};
}