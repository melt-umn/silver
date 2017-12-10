
package core;

public final class PtouchFiles extends common.FunctionNode {

	public static final int i_files = 0;
	public static final int i_i = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__core_touchFiles;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtouchFiles(final Object c_files, final Object c_i) {
		this.child_files = c_files;
		this.child_i = c_i;

	}

	private Object child_files;
	public final common.ConsCell getChild_files() {
		return (common.ConsCell) (child_files = common.Util.demand(child_files));
	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_files: return getChild_files();
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_files: return child_files;
			case i_i: return child_i;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "core:touchFiles";
	}

	public static Object invoke(final Object c_files, final Object c_i) {
		try {
		return (Object)common.Util.io(((Object)common.Util.demand(c_i)), common.Util.touchFiles(((common.ConsCell)common.Util.demand(c_files))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:touchFiles", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtouchFiles.invoke(children[0], children[1]);
		}
	};
}