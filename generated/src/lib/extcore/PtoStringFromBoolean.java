
package lib.extcore;

public final class PtoStringFromBoolean extends common.FunctionNode {

	public static final int i_v = 0;


	public static final Class<?> childTypes[] = { Boolean.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_extcore_toStringFromBoolean;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoStringFromBoolean(final Object c_v) {
		this.child_v = c_v;

	}

	private Object child_v;
	public final Boolean getChild_v() {
		return (Boolean) (child_v = common.Util.demand(child_v));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;

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
		return "lib:extcore:toStringFromBoolean";
	}

	public static common.StringCatter invoke(final Object c_v) {
		try {
		final common.DecoratedNode context = new PtoStringFromBoolean(c_v).decorate();
		//if v then "true" else "false"
		return (common.StringCatter)((((Boolean)context.childAsIs(lib.extcore.PtoStringFromBoolean.i_v)) ? (new common.StringCatter("true")) : (new common.StringCatter("false"))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:extcore:toStringFromBoolean", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoStringFromBoolean.invoke(children[0]);
		}
	};
}