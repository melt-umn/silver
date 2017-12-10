
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

public final class PgetUnaryOverload extends common.FunctionNode {

	public static final int i_t = 0;
	public static final int i_env = 1;
	public static final int i_overloads = 2;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_getUnaryOverload;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_t] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PgetUnaryOverload(final Object c_t, final Object c_env, final Object c_overloads) {
		this.child_t = c_t;
		this.child_env = c_env;
		this.child_overloads = c_overloads;

	}

	private Object child_t;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_t() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_t = common.Util.demand(child_t));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}

	private Object child_overloads;
	public final common.ConsCell getChild_overloads() {
		return (common.ConsCell) (child_overloads = common.Util.demand(child_overloads));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i_env: return getChild_env();
			case i_overloads: return getChild_overloads();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i_env: return child_env;
			case i_overloads: return child_overloads;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getUnaryOverload";
	}

	public static core.NMaybe invoke(final Object c_t, final Object c_env, final Object c_overloads) {
		try {
		final common.DecoratedNode context = new PgetUnaryOverload(c_t, c_env, c_overloads).decorate();
		//do (bindMaybe, returnMaybe) {n::String <- moduleName(env, t,); prod::(Expr ::= Expr Location) <- lookupBy(stringEq, n, overloads,); return prod;}
		return (core.NMaybe)(((core.NMaybe)core.monad.PbindMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.host.PmoduleName.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetUnaryOverload.i_env), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetUnaryOverload.i_t)))); } }, (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_890_n = args[0];

    return ((core.NMaybe)core.monad.PbindMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)core.PlookupBy.invoke(core.PstringEq.factory, ((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_890_n)), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetUnaryOverload.i_overloads))); } }, (new common.NodeFactory<core.NMaybe>() {
  public final core.NMaybe invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_891_prod = args[0];

    return ((core.NMaybe)core.monad.PreturnMaybe.invoke(((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)common.Util.demand(__SV_LAMBDA_PARAM_891_prod))));
  }
})));
  }
}))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:overloadable:getUnaryOverload", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetUnaryOverload.invoke(children[0], children[1], children[2]);
		}
	};
}