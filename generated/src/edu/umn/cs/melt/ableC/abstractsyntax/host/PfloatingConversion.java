
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PfloatingConversion extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_floatingConversion;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.num_inh_attrs];

	}

	public PfloatingConversion(final Object c_a, final Object c_b) {
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType getChild_a() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType getChild_b() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType) (child_b = common.Util.demand(child_b));
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:floatingConversion";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PfloatingConversion(c_a, c_b).decorate();
		//case a, b of realType(at), realType(bt) -> realType(realTypeConversion(at, bt,),) | realType(at), complexType(bt) -> complexType(realTypeConversion(at, bt,),) | realType(at), imaginaryType(bt) -> complexType(realTypeConversion(at, bt,),) | complexType(at), realType(bt) -> complexType(realTypeConversion(at, bt,),) | complexType(at), complexType(bt) -> complexType(realTypeConversion(at, bt,),) | complexType(at), imaginaryType(bt) -> complexType(realTypeConversion(at, bt,),) | imaginaryType(at), realType(bt) -> complexType(realTypeConversion(at, bt,),) | imaginaryType(at), complexType(bt) -> complexType(realTypeConversion(at, bt,),) | imaginaryType(at), imaginaryType(bt) -> imaginaryType(realTypeConversion(at, bt,),) | realType(rt), _ -> a | complexType(rt), _ -> a | imaginaryType(rt), _ -> a end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType) { final common.Thunk<Object> __SV_LOCAL___pv8581___sv_pv_8580_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8583___fail_8584 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8582_rt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8581___sv_pv_8580_at.eval())); } };
return context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatingConversion.i_a).undecorate(); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType) { final common.Thunk<Object> __SV_LOCAL___pv8592___sv_pv_8591_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8593_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8592___sv_pv_8591_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8594_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8581___sv_pv_8580_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8594_at), common.Thunk.transformUndecorate(__SV_LOCAL_8593_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType) { final common.Thunk<Object> __SV_LOCAL___pv8596___sv_pv_8595_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8597_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8596___sv_pv_8595_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8598_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8581___sv_pv_8580_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8598_at), common.Thunk.transformUndecorate(__SV_LOCAL_8597_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PrealType) { final common.Thunk<Object> __SV_LOCAL___pv8600___sv_pv_8599_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8601_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8600___sv_pv_8599_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8602_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8581___sv_pv_8580_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8602_at), common.Thunk.transformUndecorate(__SV_LOCAL_8601_bt))); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(__SV_LOCAL_8583___fail_8584.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatingConversion.i_b)); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType) { final common.Thunk<Object> __SV_LOCAL___pv8622___sv_pv_8623_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8621___fail_8620 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8636_rt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8622___sv_pv_8623_at.eval())); } };
return context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatingConversion.i_a).undecorate(); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType) { final common.Thunk<Object> __SV_LOCAL___pv8634___sv_pv_8635_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8633_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8634___sv_pv_8635_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8632_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8622___sv_pv_8623_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8632_at), common.Thunk.transformUndecorate(__SV_LOCAL_8633_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType) { final common.Thunk<Object> __SV_LOCAL___pv8630___sv_pv_8631_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8629_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8630___sv_pv_8631_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8628_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8622___sv_pv_8623_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8628_at), common.Thunk.transformUndecorate(__SV_LOCAL_8629_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PrealType) { final common.Thunk<Object> __SV_LOCAL___pv8626___sv_pv_8627_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8625_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8626___sv_pv_8627_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8624_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8622___sv_pv_8623_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8624_at), common.Thunk.transformUndecorate(__SV_LOCAL_8625_bt))); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(__SV_LOCAL_8621___fail_8620.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatingConversion.i_b)); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PrealType) { final common.Thunk<Object> __SV_LOCAL___pv8605___sv_pv_8606_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8604___fail_8603 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8619_rt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8605___sv_pv_8606_at.eval())); } };
return context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatingConversion.i_a).undecorate(); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType) { final common.Thunk<Object> __SV_LOCAL___pv8617___sv_pv_8618_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8616_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8617___sv_pv_8618_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8615_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8605___sv_pv_8606_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8615_at), common.Thunk.transformUndecorate(__SV_LOCAL_8616_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType) { final common.Thunk<Object> __SV_LOCAL___pv8613___sv_pv_8614_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8612_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8613___sv_pv_8614_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8611_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8605___sv_pv_8606_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8611_at), common.Thunk.transformUndecorate(__SV_LOCAL_8612_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PrealType) { final common.Thunk<Object> __SV_LOCAL___pv8609___sv_pv_8610_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8608_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8609___sv_pv_8610_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8607_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8605___sv_pv_8606_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PrealType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)edu.umn.cs.melt.ableC.abstractsyntax.host.PrealTypeConversion.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8607_at), common.Thunk.transformUndecorate(__SV_LOCAL_8608_bt))); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(__SV_LOCAL_8604___fail_8603.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatingConversion.i_b)); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'TypeOps.sv', 153, 9, 169, 5, 6736, 7848\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatingConversion.i_a)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:floatingConversion", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfloatingConversion.invoke(children[0], children[1]);
		}
	};
}