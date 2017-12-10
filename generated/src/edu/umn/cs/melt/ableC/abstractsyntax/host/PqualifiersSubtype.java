
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PqualifiersSubtype extends common.FunctionNode {

	public static final int i_q1 = 0;
	public static final int i_q2 = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiersSubtype;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PqualifiersSubtype(final Object c_q1, final Object c_q2) {
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:qualifiersSubtype";
	}

	public static Boolean invoke(final Object c_q1, final Object c_q2) {
		try {
		final common.DecoratedNode context = new PqualifiersSubtype(c_q1, c_q2).decorate();
		//foldr(\ a::Boolean b::Boolean  -> a && b, true, map((.qualIsNegative), inQ1notQ2,),) && foldr(\ a::Boolean b::Boolean  -> a && b, true, map((.qualIsPositive), inQ2notQ1,),)
		return (Boolean)((((Boolean)core.Pfoldr.invoke((new common.NodeFactory<Boolean>() {
  public final Boolean invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_8911_a = args[0];
final Object __SV_LAMBDA_PARAM_8912_b = args[1];

    return (((Boolean)common.Util.demand(__SV_LAMBDA_PARAM_8911_a)) && ((Boolean)common.Util.demand(__SV_LAMBDA_PARAM_8912_b)));
  }
}), true, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualIsNegative__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier, context), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.inQ1notQ2__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiersSubtype))); } })) && ((Boolean)core.Pfoldr.invoke((new common.NodeFactory<Boolean>() {
  public final Boolean invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_8913_a = args[0];
final Object __SV_LAMBDA_PARAM_8914_b = args[1];

    return (((Boolean)common.Util.demand(__SV_LAMBDA_PARAM_8913_a)) && ((Boolean)common.Util.demand(__SV_LAMBDA_PARAM_8914_b)));
  }
}), true, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualIsPositive__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier, context), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.inQ2notQ1__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiersSubtype))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:qualifiersSubtype", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PqualifiersSubtype.invoke(children[0], children[1]);
		}
	};
}