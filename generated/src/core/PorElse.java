
package core;

public final class PorElse extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = { core.NMaybe.class,core.NMaybe.class };

	public static final int num_local_attrs = Init.count_local__ON__core_orElse;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NMaybe.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PorElse(final Object c_l, final Object c_r) {
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final core.NMaybe getChild_l() {
		return (core.NMaybe) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final core.NMaybe getChild_r() {
		return (core.NMaybe) (child_r = common.Util.demand(child_r));
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
		return "core:orElse";
	}

	public static core.NMaybe invoke(final Object c_l, final Object c_r) {
		try {
		final common.DecoratedNode context = new PorElse(c_l, c_r).decorate();
		//if l.isJust then l else r
		return (core.NMaybe)((((Boolean)context.childDecorated(core.PorElse.i_l).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? context.childDecorated(core.PorElse.i_l).undecorate() : context.childDecorated(core.PorElse.i_r).undecorate()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:orElse", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PorElse.invoke(children[0], children[1]);
		}
	};
}