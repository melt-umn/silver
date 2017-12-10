
package silver.util.fixedmap;

public final class PfstStringLte extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = { core.NPair.class,core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_fixedmap_fstStringLte;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NPair.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PfstStringLte(final Object c_l, final Object c_r) {
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final core.NPair getChild_l() {
		return (core.NPair) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final core.NPair getChild_r() {
		return (core.NPair) (child_r = common.Util.demand(child_r));
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
		return "silver:util:fixedmap:fstStringLte";
	}

	public static Boolean invoke(final Object c_l, final Object c_r) {
		try {
		final common.DecoratedNode context = new PfstStringLte(c_l, c_r).decorate();
		//l.fst <= r.fst
		return (Boolean)((((common.StringCatter)context.childDecorated(silver.util.fixedmap.PfstStringLte.i_l).synthesized(core.Init.core_fst__ON__core_Pair)).toString().compareTo(((common.StringCatter)context.childDecorated(silver.util.fixedmap.PfstStringLte.i_r).synthesized(core.Init.core_fst__ON__core_Pair)).toString()) <= 0));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:fixedmap:fstStringLte", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfstStringLte.invoke(children[0], children[1]);
		}
	};
}