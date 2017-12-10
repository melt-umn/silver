
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PdoValueMergeQualifiers extends common.FunctionNode {

	public static final int i_t = 0;
	public static final int i_n = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_doValueMergeQualifiers;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_t] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PdoValueMergeQualifiers(final Object c_t, final Object c_n) {
		this.child_t = c_t;
		this.child_n = c_n;

	}

	private Object child_t;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_t() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_t = common.Util.demand(child_t));
	}

	private Object child_n;
	public final common.DecoratedNode getChild_n() {
		return (common.DecoratedNode) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i_n: return child_n;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:doValueMergeQualifiers";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object c_t, final Object c_n) {
		try {
		final common.DecoratedNode context = new PdoValueMergeQualifiers(c_t, c_n).decorate();
		//foldr(\ t1::Type t2::Type  -> t2.mergeQualifiers(t1,), t, map((.typerep), n.valueLocalLookup,),)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)core.Pfoldr.invoke((new common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() {
  public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_6293_t1 = args[0];
final Object __SV_LAMBDA_PARAM_6294_t2 = args[1];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)common.Util.demand(__SV_LAMBDA_PARAM_6294_t2)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)).invoke(new Object[]{((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)common.Util.demand(__SV_LAMBDA_PARAM_6293_t1))}, null));
  }
}), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PdoValueMergeQualifiers.i_t)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_ValueItem, context), context.childAsIsSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PdoValueMergeQualifiers.i_n, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:doValueMergeQualifiers", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdoValueMergeQualifiers.invoke(children[0], children[1]);
		}
	};
}