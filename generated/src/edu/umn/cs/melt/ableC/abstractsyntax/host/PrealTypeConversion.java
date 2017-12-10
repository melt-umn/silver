
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PrealTypeConversion extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_realTypeConversion;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType.num_inh_attrs];

	}

	public PrealTypeConversion(final Object c_a, final Object c_b) {
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType getChild_a() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType getChild_b() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:realTypeConversion";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PrealTypeConversion(c_a, c_b).decorate();
		//case a, b of longdoubleType(), _ -> longdoubleType(,) | _, longdoubleType() -> longdoubleType(,) | floatType(), floatType() -> floatType(,) | _, _ -> doubleType(,) end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8697___fail_8696 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8692___fail_8693 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdoubleType()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PlongdoubleType) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PlongdoubleType()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)(__SV_LOCAL_8692___fail_8693.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.i_b)); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatType) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatType) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatType()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)(__SV_LOCAL_8697___fail_8696.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.i_b)); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PlongdoubleType) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PlongdoubleType()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)(__SV_LOCAL_8697___fail_8696.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.i_a)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:realTypeConversion", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrealTypeConversion.invoke(children[0], children[1]);
		}
	};
}