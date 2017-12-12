
package core;

public final class Pmax extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = { Integer.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__core_max;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pmax(final Object c_l, final Object c_r) {
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final Integer getChild_l() {
		return (Integer) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final Integer getChild_r() {
		return (Integer) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;

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
		return "core:max";
	}

	public static Integer invoke(final Object c_l, final Object c_r) {
		try {
		final common.DecoratedNode context = new Pmax(c_l, c_r).decorate();
		//if l < r then r else l
		return (Integer)(((((Integer)context.childAsIs(core.Pmax.i_l)) < ((Integer)context.childAsIs(core.Pmax.i_r))) ? ((Integer)context.childAsIs(core.Pmax.i_r)) : ((Integer)context.childAsIs(core.Pmax.i_l))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:max", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pmax.invoke(children[0], children[1]);
		}
	};
}