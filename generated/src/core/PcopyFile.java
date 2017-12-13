
package core;

public final class PcopyFile extends common.FunctionNode {

	public static final int i_src = 0;
	public static final int i_dst = 1;
	public static final int i_i = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__core_copyFile;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcopyFile(final Object c_src, final Object c_dst, final Object c_i) {
		this.child_src = c_src;
		this.child_dst = c_dst;
		this.child_i = c_i;

	}

	private Object child_src;
	public final common.StringCatter getChild_src() {
		return (common.StringCatter) (child_src = common.Util.demand(child_src));
	}

	private Object child_dst;
	public final common.StringCatter getChild_dst() {
		return (common.StringCatter) (child_dst = common.Util.demand(child_dst));
	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_src: return getChild_src();
			case i_dst: return getChild_dst();
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_src: return child_src;
			case i_dst: return child_dst;
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
		return "core:copyFile";
	}

	public static Object invoke(final Object c_src, final Object c_dst, final Object c_i) {
		try {
		return (Object)common.Util.io(((Object)common.Util.demand(c_i)), common.Util.copyFile(((common.StringCatter)common.Util.demand(c_src)).toString(), ((common.StringCatter)common.Util.demand(c_dst)).toString()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:copyFile", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcopyFile.invoke(children[0], children[1], children[2]);
		}
	};
}