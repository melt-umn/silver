
package core;

public final class Pcons extends common.FunctionNode {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = { Object.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_cons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pcons(final Object c_h, final Object c_t) {
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final Object getChild_h() {
		return (Object) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final common.ConsCell getChild_t() {
		return (common.ConsCell) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		return "core:cons";
	}

	public static common.ConsCell invoke(final Object c_h, final Object c_t) {
		try {
		return (common.ConsCell)new common.ConsCell(c_h, c_t);

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:cons", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pcons.invoke(children[0], children[1]);
		}
	};
}