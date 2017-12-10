
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PintegerConversion extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_integerConversion;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.num_inh_attrs];

	}

	public PintegerConversion(final Object c_a, final Object c_b) {
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:integerConversion";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PintegerConversion(c_a, c_b).decorate();
		//case a.integerPromotionsBuiltin, b.integerPromotionsBuiltin of signedType(at), signedType(bt) -> signedType(maximumConversionRank(at, bt,),) | signedType(at), unsignedType(bt) -> if at.integerConversionRank > bt.integerConversionRank then signedType(at,) else unsignedType(bt,) | unsignedType(at), signedType(bt) -> if at.integerConversionRank > bt.integerConversionRank then signedType(at,) else unsignedType(bt,) | unsignedType(at), unsignedType(bt) -> unsignedType(maximumConversionRank(at, bt,),) | unsignedType(at), complexIntegerType(bt) -> complexIntegerType(maximumConversionRank(at, bt,),) | signedType(at), complexIntegerType(bt) -> complexIntegerType(maximumConversionRank(at, bt,),) | complexIntegerType(at), signedType(bt) -> complexIntegerType(maximumConversionRank(at, bt,),) | complexIntegerType(at), unsignedType(bt) -> complexIntegerType(maximumConversionRank(at, bt,),) | complexIntegerType(at), complexIntegerType(bt) -> complexIntegerType(maximumConversionRank(at, bt,),) end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexIntegerType) { final common.Thunk<Object> __SV_LOCAL___pv8700___sv_pv_8701_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexIntegerType) { final common.Thunk<Object> __SV_LOCAL___pv8705___sv_pv_8704_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8706_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8705___sv_pv_8704_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8707_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8700___sv_pv_8701_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexIntegerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NIntegerType)edu.umn.cs.melt.ableC.abstractsyntax.host.PmaximumConversionRank.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8707_at), common.Thunk.transformUndecorate(__SV_LOCAL_8706_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PsignedType) { final common.Thunk<Object> __SV_LOCAL___pv8709___sv_pv_8708_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8710_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8709___sv_pv_8708_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8711_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8700___sv_pv_8701_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexIntegerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NIntegerType)edu.umn.cs.melt.ableC.abstractsyntax.host.PmaximumConversionRank.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8711_at), common.Thunk.transformUndecorate(__SV_LOCAL_8710_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PunsignedType) { final common.Thunk<Object> __SV_LOCAL___pv8713___sv_pv_8712_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8714_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8713___sv_pv_8712_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8715_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8700___sv_pv_8701_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexIntegerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NIntegerType)edu.umn.cs.melt.ableC.abstractsyntax.host.PmaximumConversionRank.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8715_at), common.Thunk.transformUndecorate(__SV_LOCAL_8714_bt))); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'TypeOps.sv', 186, 9, 209, 5, 8195, 9644\n"))));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PintegerConversion.i_b).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotionsBuiltin__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType)).decorate(context, (common.Lazy[])null)); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PsignedType) { final common.Thunk<Object> __SV_LOCAL___pv8716___sv_pv_8717_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexIntegerType) { final common.Thunk<Object> __SV_LOCAL___pv8738___sv_pv_8737_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8739_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8738___sv_pv_8737_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8740_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8716___sv_pv_8717_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexIntegerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NIntegerType)edu.umn.cs.melt.ableC.abstractsyntax.host.PmaximumConversionRank.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8740_at), common.Thunk.transformUndecorate(__SV_LOCAL_8739_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PsignedType) { final common.Thunk<Object> __SV_LOCAL___pv8742___sv_pv_8741_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8743_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8742___sv_pv_8741_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8744_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8716___sv_pv_8717_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PsignedType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NIntegerType)edu.umn.cs.melt.ableC.abstractsyntax.host.PmaximumConversionRank.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8744_at), common.Thunk.transformUndecorate(__SV_LOCAL_8743_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PunsignedType) { final common.Thunk<Object> __SV_LOCAL___pv8746___sv_pv_8745_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8747_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8746___sv_pv_8745_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8748_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8716___sv_pv_8717_at.eval())); } };
return ((((Integer)((common.DecoratedNode)(__SV_LOCAL_8748_at.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerConversionRank__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_IntegerType)) > ((Integer)((common.DecoratedNode)(__SV_LOCAL_8747_bt.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerConversionRank__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_IntegerType))) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PsignedType(common.Thunk.transformUndecorate(__SV_LOCAL_8748_at))) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PunsignedType(common.Thunk.transformUndecorate(__SV_LOCAL_8747_bt)))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'TypeOps.sv', 186, 9, 209, 5, 8195, 9644\n"))));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PintegerConversion.i_b).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotionsBuiltin__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType)).decorate(context, (common.Lazy[])null)); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PunsignedType) { final common.Thunk<Object> __SV_LOCAL___pv8765___sv_pv_8766_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexIntegerType) { final common.Thunk<Object> __SV_LOCAL___pv8772___sv_pv_8771_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8773_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8772___sv_pv_8771_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8774_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8765___sv_pv_8766_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcomplexIntegerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NIntegerType)edu.umn.cs.melt.ableC.abstractsyntax.host.PmaximumConversionRank.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8774_at), common.Thunk.transformUndecorate(__SV_LOCAL_8773_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PsignedType) { final common.Thunk<Object> __SV_LOCAL___pv8776___sv_pv_8775_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8777_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8776___sv_pv_8775_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8778_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8765___sv_pv_8766_at.eval())); } };
return ((((Integer)((common.DecoratedNode)(__SV_LOCAL_8778_at.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerConversionRank__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_IntegerType)) > ((Integer)((common.DecoratedNode)(__SV_LOCAL_8777_bt.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerConversionRank__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_IntegerType))) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PsignedType(common.Thunk.transformUndecorate(__SV_LOCAL_8778_at))) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PunsignedType(common.Thunk.transformUndecorate(__SV_LOCAL_8777_bt)))); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PunsignedType) { final common.Thunk<Object> __SV_LOCAL___pv8791___sv_pv_8790_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8792_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8791___sv_pv_8790_bt.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_8793_at = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv8765___sv_pv_8766_at.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PunsignedType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NIntegerType)edu.umn.cs.melt.ableC.abstractsyntax.host.PmaximumConversionRank.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_8793_at), common.Thunk.transformUndecorate(__SV_LOCAL_8792_bt))); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'TypeOps.sv', 186, 9, 209, 5, 8195, 9644\n"))));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PintegerConversion.i_b).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotionsBuiltin__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType)).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'TypeOps.sv', 186, 9, 209, 5, 8195, 9644\n"))));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PintegerConversion.i_a).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotionsBuiltin__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType)).decorate(context, (common.Lazy[])null)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:integerConversion", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PintegerConversion.invoke(children[0], children[1]);
		}
	};
}