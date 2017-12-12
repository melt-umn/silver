
package core;

public final class PlocationLte extends common.FunctionNode {

	public static final int i_l1 = 0;
	public static final int i_l2 = 1;


	public static final Class<?> childTypes[] = { core.NLocation.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__core_locationLte;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l1] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_l2] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PlocationLte(final Object c_l1, final Object c_l2) {
		this.child_l1 = c_l1;
		this.child_l2 = c_l2;

	}

	private Object child_l1;
	public final core.NLocation getChild_l1() {
		return (core.NLocation) (child_l1 = common.Util.demand(child_l1));
	}

	private Object child_l2;
	public final core.NLocation getChild_l2() {
		return (core.NLocation) (child_l2 = common.Util.demand(child_l2));
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
		return "core:locationLte";
	}

	public static Boolean invoke(final Object c_l1, final Object c_l2) {
		try {
		final common.DecoratedNode context = new PlocationLte(c_l1, c_l2).decorate();
		//l1.filename < l2.filename || (l1.filename == l2.filename && (l1.line < l2.line || (l1.line == l2.line && (l1.column < l2.column))))
		return (Boolean)(((((common.StringCatter)context.childDecorated(core.PlocationLte.i_l1).synthesized(core.Init.core_filename__ON__core_Location)).toString().compareTo(((common.StringCatter)context.childDecorated(core.PlocationLte.i_l2).synthesized(core.Init.core_filename__ON__core_Location)).toString()) < 0) || (((common.StringCatter)context.childDecorated(core.PlocationLte.i_l1).synthesized(core.Init.core_filename__ON__core_Location)).equals(((common.StringCatter)context.childDecorated(core.PlocationLte.i_l2).synthesized(core.Init.core_filename__ON__core_Location))) && ((((Integer)context.childDecorated(core.PlocationLte.i_l1).synthesized(core.Init.core_line__ON__core_Location)) < ((Integer)context.childDecorated(core.PlocationLte.i_l2).synthesized(core.Init.core_line__ON__core_Location))) || (((Integer)context.childDecorated(core.PlocationLte.i_l1).synthesized(core.Init.core_line__ON__core_Location)).equals(((Integer)context.childDecorated(core.PlocationLte.i_l2).synthesized(core.Init.core_line__ON__core_Location))) && (((Integer)context.childDecorated(core.PlocationLte.i_l1).synthesized(core.Init.core_column__ON__core_Location)) < ((Integer)context.childDecorated(core.PlocationLte.i_l2).synthesized(core.Init.core_column__ON__core_Location))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:locationLte", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlocationLte.invoke(children[0], children[1]);
		}
	};
}