
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PusualArithmeticConversionsOnTypes extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_usualArithmeticConversionsOnTypes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PusualArithmeticConversionsOnTypes(final Object c_a, final Object c_b) {
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_a() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_b() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_b = common.Util.demand(child_b));
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:usualArithmeticConversionsOnTypes";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PusualArithmeticConversionsOnTypes(c_a, c_b).decorate();
		//case a, b of builtinType(_, x), builtinType(_, y) -> case usualArithmeticConversions(x, y,) of nothing() -> errorType(,) | just(z) -> builtinType(nilQualifier(,), z,) end | vectorType(b1, s1), vectorType(b2, s2) -> if compatibleTypes(b1, b2, true, false,) && s1 == s2 then a else errorType(,) | _, _ -> errorType(,) end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8487___fail_8488 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PerrorType()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PbuiltinType) { final common.Thunk<Object> __SV_LOCAL___pv8491___sv_tmp_pv_8492 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8493___sv_pv_8494_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PbuiltinType) { final common.Thunk<Object> __SV_LOCAL___pv8497___sv_tmp_pv_8498 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8499___sv_pv_8496_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8500_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8499___sv_pv_8496_y.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8501_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8493___sv_pv_8494_x.eval())); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv8503___sv_pv_8502_z = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)scrutinee.childAsIs(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8504_z = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(__SV_LOCAL___pv8503___sv_pv_8502_z.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbuiltinType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier()); } }, __SV_LOCAL_8504_z)); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PerrorType()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'TypeOps.sv', 124, 6, 127, 9, 5745, 5893\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.host.PusualArithmeticConversions.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8501_x), common.Thunk.transformUndecorate(__SV_LOCAL_8500_y))).decorate(context, (common.Lazy[])null)); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_8487___fail_8488.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualArithmeticConversionsOnTypes.i_b)); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType) { final common.Thunk<Object> __SV_LOCAL___pv8505___sv_pv_8506_b1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8507___sv_pv_8508_s1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType) { final common.Thunk<Object> __SV_LOCAL___pv8513___sv_pv_8514_b2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8515___sv_pv_8516_s2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8512_s2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv8515___sv_pv_8516_s2.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8511_b2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8513___sv_pv_8514_b2.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8510_s1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv8507___sv_pv_8508_s1.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8509_b1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8505___sv_pv_8506_b1.eval())); } };
return ((((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypes.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8509_b1), common.Thunk.transformUndecorate(__SV_LOCAL_8511_b2), true, false)) && ((Integer)(__SV_LOCAL_8510_s1.eval())).equals(((Integer)(__SV_LOCAL_8512_s2.eval())))) ? context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualArithmeticConversionsOnTypes.i_a).undecorate() : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PerrorType())); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_8487___fail_8488.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualArithmeticConversionsOnTypes.i_b)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_8487___fail_8488.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualArithmeticConversionsOnTypes.i_a)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:usualArithmeticConversionsOnTypes", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PusualArithmeticConversionsOnTypes.invoke(children[0], children[1]);
		}
	};
}