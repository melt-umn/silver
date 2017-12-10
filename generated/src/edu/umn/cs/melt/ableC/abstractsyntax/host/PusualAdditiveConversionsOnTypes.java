
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PusualAdditiveConversionsOnTypes extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_usualAdditiveConversionsOnTypes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PusualAdditiveConversionsOnTypes(final Object c_a, final Object c_b) {
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:usualAdditiveConversionsOnTypes";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PusualAdditiveConversionsOnTypes(c_a, c_b).decorate();
		//case a, b of builtinType(_, x), builtinType(_, y) -> case usualArithmeticConversions(x, y,) of nothing() -> errorType(,) | just(z) -> builtinType(nilQualifier(,), z,) end | pointerType(_, _), builtinType(_, _) -> a | builtinType(_, _), pointerType(_, _) -> b | vectorType(b1, s1), vectorType(b2, s2) -> if compatibleTypes(b1, b2, true, false,) && s1 == s2 then a else errorType(,) | _, _ -> errorType(,) end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8351___fail_8352 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PerrorType()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PbuiltinType) { final common.Thunk<Object> __SV_LOCAL___pv8355___sv_tmp_pv_8356 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8357___sv_pv_8358_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PbuiltinType) { final common.Thunk<Object> __SV_LOCAL___pv8361___sv_tmp_pv_8362 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8363___sv_pv_8360_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8364_y = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8363___sv_pv_8360_y.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8365_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8357___sv_pv_8358_x.eval())); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv8367___sv_pv_8366_z = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)scrutinee.childAsIs(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8368_z = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(__SV_LOCAL___pv8367___sv_pv_8366_z.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbuiltinType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier()); } }, __SV_LOCAL_8368_z)); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PerrorType()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'TypeOps.sv', 86, 6, 89, 9, 4324, 4472\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.host.PusualArithmeticConversions.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8365_x), common.Thunk.transformUndecorate(__SV_LOCAL_8364_y))).decorate(context, (common.Lazy[])null)); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType) { final common.Thunk<Object> __SV_LOCAL___pv8369___sv_tmp_pv_8370 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8371___sv_tmp_pv_8372 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualAdditiveConversionsOnTypes.i_b).undecorate(); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_8351___fail_8352.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualAdditiveConversionsOnTypes.i_b)); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType) { final common.Thunk<Object> __SV_LOCAL___pv8385___sv_tmp_pv_8386 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8387___sv_tmp_pv_8388 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PbuiltinType) { final common.Thunk<Object> __SV_LOCAL___pv8389___sv_tmp_pv_8390 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8391___sv_tmp_pv_8392 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualAdditiveConversionsOnTypes.i_a).undecorate(); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_8351___fail_8352.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualAdditiveConversionsOnTypes.i_b)); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType) { final common.Thunk<Object> __SV_LOCAL___pv8373___sv_pv_8374_b1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8375___sv_pv_8376_s1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType) { final common.Thunk<Object> __SV_LOCAL___pv8381___sv_pv_8382_b2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv8383___sv_pv_8384_s2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8380_s2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv8383___sv_pv_8384_s2.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8379_b2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8381___sv_pv_8382_b2.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8378_s1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv8375___sv_pv_8376_s1.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8377_b1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8373___sv_pv_8374_b1.eval())); } };
return ((((Boolean)edu.umn.cs.melt.ableC.abstractsyntax.host.PcompatibleTypes.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8377_b1), common.Thunk.transformUndecorate(__SV_LOCAL_8379_b2), true, false)) && ((Integer)(__SV_LOCAL_8378_s1.eval())).equals(((Integer)(__SV_LOCAL_8380_s2.eval())))) ? context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualAdditiveConversionsOnTypes.i_a).undecorate() : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PerrorType())); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_8351___fail_8352.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualAdditiveConversionsOnTypes.i_b)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_8351___fail_8352.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PusualAdditiveConversionsOnTypes.i_a)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:usualAdditiveConversionsOnTypes", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PusualAdditiveConversionsOnTypes.invoke(children[0], children[1]);
		}
	};
}