
package core;

public final class PconsMaybe extends common.FunctionNode {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = { core.NMaybe.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_consMaybe;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_h] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PconsMaybe(final Object c_h, final Object c_t) {
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final core.NMaybe getChild_h() {
		return (core.NMaybe) (child_h = common.Util.demand(child_h));
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
		return "core:consMaybe";
	}

	public static common.ConsCell invoke(final Object c_h, final Object c_t) {
		try {
		final common.DecoratedNode context = new PconsMaybe(c_h, c_t).decorate();
		//if h.isJust then (h.fromJust :: t) else t
		return (common.ConsCell)((((Boolean)context.childDecorated(core.PconsMaybe.i_h).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(core.PconsMaybe.i_h, core.Init.core_fromJust__ON__core_Maybe), context.childAsIsLazy(core.PconsMaybe.i_t))) : ((common.ConsCell)context.childAsIs(core.PconsMaybe.i_t))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:consMaybe", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PconsMaybe.invoke(children[0], children[1]);
		}
	};
}