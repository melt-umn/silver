
package core;

public final class PappendFile extends common.FunctionNode {

	public static final int i_file = 0;
	public static final int i_contents = 1;
	public static final int i_i = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__core_appendFile;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PappendFile(final Object c_file, final Object c_contents, final Object c_i) {
		this.child_file = c_file;
		this.child_contents = c_contents;
		this.child_i = c_i;

	}

	private Object child_file;
	public final common.StringCatter getChild_file() {
		return (common.StringCatter) (child_file = common.Util.demand(child_file));
	}

	private Object child_contents;
	public final common.StringCatter getChild_contents() {
		return (common.StringCatter) (child_contents = common.Util.demand(child_contents));
	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_file: return getChild_file();
			case i_contents: return getChild_contents();
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_file: return child_file;
			case i_contents: return child_contents;
			case i_i: return child_i;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "core:appendFile";
	}

	public static Object invoke(final Object c_file, final Object c_contents, final Object c_i) {
		try {
		return (Object)common.Util.io(((Object)common.Util.demand(c_i)), common.Util.appendFile(((common.StringCatter)common.Util.demand(c_file)).toString(), ((common.StringCatter)common.Util.demand(c_contents))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:appendFile", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PappendFile.invoke(children[0], children[1], children[2]);
		}
	};
}