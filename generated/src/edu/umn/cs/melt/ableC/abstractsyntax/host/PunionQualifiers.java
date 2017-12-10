
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PunionQualifiers extends common.FunctionNode {

	public static final int i_q1 = 0;
	public static final int i_q2 = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_unionQualifiers;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunionQualifiers(final Object c_q1, final Object c_q2) {
		this.child_q1 = c_q1;
		this.child_q2 = c_q2;

	}

	private Object child_q1;
	public final common.ConsCell getChild_q1() {
		return (common.ConsCell) (child_q1 = common.Util.demand(child_q1));
	}

	private Object child_q2;
	public final common.ConsCell getChild_q2() {
		return (common.ConsCell) (child_q2 = common.Util.demand(child_q2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q1: return getChild_q1();
			case i_q2: return getChild_q2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q1: return child_q1;
			case i_q2: return child_q2;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:unionQualifiers";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers invoke(final Object c_q1, final Object c_q2) {
		try {
		final common.DecoratedNode context = new PunionQualifiers(c_q1, c_q2).decorate();
		//foldQualifier(filter(\ q::Qualifier  -> ! containsBy(qualifierCompat, q, q2,), nubBy(qualifierCompat, q1,),) ++ q2,)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)edu.umn.cs.melt.ableC.abstractsyntax.construction.PfoldQualifier.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfilter.invoke((new common.NodeFactory<Boolean>() {
  public final Boolean invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_7858_q = args[0];

    return (!((Boolean)core.PcontainsBy.invoke(edu.umn.cs.melt.ableC.abstractsyntax.host.PqualifierCompat.factory, ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)common.Util.demand(__SV_LAMBDA_PARAM_7858_q)), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PunionQualifiers.i_q2))));
  }
}), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PnubBy.invoke(edu.umn.cs.melt.ableC.abstractsyntax.host.PqualifierCompat.factory, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PunionQualifiers.i_q1))); } })); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PunionQualifiers.i_q2))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:unionQualifiers", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunionQualifiers.invoke(children[0], children[1]);
		}
	};
}