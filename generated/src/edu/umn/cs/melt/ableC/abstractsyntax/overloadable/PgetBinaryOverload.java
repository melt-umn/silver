
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

public final class PgetBinaryOverload extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;
	public static final int i_env = 2;
	public static final int i_overloads = 3;
	public static final int i_lOverloads = 4;
	public static final int i_rOverloads = 5;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PgetBinaryOverload(final Object c_l, final Object c_r, final Object c_env, final Object c_overloads, final Object c_lOverloads, final Object c_rOverloads) {
		this.child_l = c_l;
		this.child_r = c_r;
		this.child_env = c_env;
		this.child_overloads = c_overloads;
		this.child_lOverloads = c_lOverloads;
		this.child_rOverloads = c_rOverloads;

	}

	private Object child_l;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_l() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_r() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_r = common.Util.demand(child_r));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}

	private Object child_overloads;
	public final common.ConsCell getChild_overloads() {
		return (common.ConsCell) (child_overloads = common.Util.demand(child_overloads));
	}

	private Object child_lOverloads;
	public final common.ConsCell getChild_lOverloads() {
		return (common.ConsCell) (child_lOverloads = common.Util.demand(child_lOverloads));
	}

	private Object child_rOverloads;
	public final common.ConsCell getChild_rOverloads() {
		return (common.ConsCell) (child_rOverloads = common.Util.demand(child_rOverloads));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();
			case i_env: return getChild_env();
			case i_overloads: return getChild_overloads();
			case i_lOverloads: return getChild_lOverloads();
			case i_rOverloads: return getChild_rOverloads();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;
			case i_env: return child_env;
			case i_overloads: return child_overloads;
			case i_lOverloads: return child_lOverloads;
			case i_rOverloads: return child_rOverloads;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getBinaryOverload";
	}

	public static core.NMaybe invoke(final Object c_l, final Object c_r, final Object c_env, final Object c_overloads, final Object c_lOverloads, final Object c_rOverloads) {
		try {
		final common.DecoratedNode context = new PgetBinaryOverload(c_l, c_r, c_env, c_overloads, c_lOverloads, c_rOverloads).decorate();
		//orElse(option1, orElse(option2, option3,),)
		return (core.NMaybe)(((core.NMaybe)core.PorElse.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)core.PorElse.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload)), common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option3__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getBinaryOverload)))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getBinaryOverload", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetBinaryOverload.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}