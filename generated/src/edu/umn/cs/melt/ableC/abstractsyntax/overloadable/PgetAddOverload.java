
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

public final class PgetAddOverload extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;
	public static final int i_env = 2;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PgetAddOverload(final Object c_l, final Object c_r, final Object c_env) {
		this.child_l = c_l;
		this.child_r = c_r;
		this.child_env = c_env;

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



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;
			case i_env: return child_env;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAddOverload";
	}

	public static core.NMaybe invoke(final Object c_l, final Object c_r, final Object c_env) {
		try {
		final common.DecoratedNode context = new PgetAddOverload(c_l, c_r, c_env).decorate();
		//getBinaryOverload(l, r, env, overloads, lOverloads, rOverloads,)
		return (core.NMaybe)(((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetBinaryOverload.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.i_l)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.i_r)), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAddOverload.i_env), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.overloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.rOverloads__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getAddOverload))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getAddOverload", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetAddOverload.invoke(children[0], children[1], children[2]);
		}
	};
}