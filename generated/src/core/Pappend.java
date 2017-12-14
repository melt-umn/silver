
package core;

public final class Pappend extends common.FunctionNode {

	public static final int i_l1 = 0;
	public static final int i_l2 = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_append;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pappend(final Object c_l1, final Object c_l2) {
		this.child_l1 = c_l1;
		this.child_l2 = c_l2;

	}

	private Object child_l1;
	public final common.ConsCell getChild_l1() {
		return (common.ConsCell) (child_l1 = common.Util.demand(child_l1));
	}

	private Object child_l2;
	public final common.ConsCell getChild_l2() {
		return (common.ConsCell) (child_l2 = common.Util.demand(child_l2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l1: return getChild_l1();
			case i_l2: return getChild_l2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l1: return child_l1;
			case i_l2: return child_l2;

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
		return "core:append";
	}

	public static common.ConsCell invoke(final Object c_l1, final Object c_l2) {
		try {
		return (common.ConsCell)common.AppendCell.append(((common.ConsCell)common.Util.demand(c_l1)), c_l2);

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:append", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pappend.invoke(children[0], children[1]);
		}
	};
}