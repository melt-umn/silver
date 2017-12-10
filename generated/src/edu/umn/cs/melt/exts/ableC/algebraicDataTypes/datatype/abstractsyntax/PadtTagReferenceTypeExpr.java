
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

// b::BaseTypeExpr ::= q::Qualifiers n::Name 
public final class PadtTagReferenceTypeExpr extends edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr {

	public static final int i_q = 0;
	public static final int i_n = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NName.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_q] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.num_inh_attrs];
	childInheritedAttributes[i_n] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_inh_attrs];

	}

	public PadtTagReferenceTypeExpr(final Object c_q, final Object c_n) {
		super();
		this.child_q = c_q;
		this.child_n = c_n;

	}

	private Object child_q;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers getChild_q() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers) (child_q = common.Util.demand(child_q));
	}

	private Object child_n;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NName getChild_n() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;
			case i_n: return child_n;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9992___fail_9993 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PerrorTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)), (common.StringCatter)(new common.StringCatter(" is not a declared datatype"))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
return new common.PatternLazy<common.ConsCell, edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_9995___sv_tmp_pv_9994 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_9996___sv_tmp_pv_9997 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtRefIdTagItem) { final common.Thunk<Object> __SV_LOCAL___pv10003___sv_pv_10004_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10005___sv_pv_10002_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10006_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv10005___sv_pv_10002_s.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10007_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv10003___sv_pv_10004_r.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PtagReferenceTypeExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_q)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstructSEU()); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n)))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)(__SV_LOCAL_9992___fail_9993.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)(__SV_LOCAL_9995___sv_tmp_pv_9994.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee.nil()) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PtagReferenceTypeExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_q)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructOrEnumOrUnion)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstructSEU()); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n)))); }return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)(__SV_LOCAL_9992___fail_9993.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.tags__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr))); } }).eval());
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:adtTagReferenceTypeExpr";
	}

	static void initProductionAttributeDefinitions() {
		// b.pp = ppConcat([ terminate(space(,), q.pps,), text("datatype",), space(,), n.pp ],)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pterminate.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_q, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_pps__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("datatype")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } };
		// tags = lookupTag(n.name, b.env,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.localAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.tags__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupTag.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr))); } };
		// name_refIdIfOld_workaround = case n.tagLocalLookup of adtRefIdTagItem(thisRefId, _)::_ -> just(thisRefId,) | _ -> nothing(,) end
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.localAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.name_refIdIfOld_workaround__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9940___fail_9941 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.ConsCell, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_9946___sv_tmp_pv_9945 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_9947___sv_tmp_pv_9948 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtRefIdTagItem) { final common.Thunk<Object> __SV_LOCAL___pv9951___sv_pv_9950_thisRefId = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9952___sv_tmp_pv_9953 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9954_thisRefId = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv9951___sv_pv_9950_thisRefId.eval())); } };
return ((core.NMaybe)new core.Pjust(__SV_LOCAL_9954_thisRefId)); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_9940___fail_9941.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)(__SV_LOCAL_9946___sv_tmp_pv_9945.eval())).decorate(context, (common.Lazy[])null)); }return ((core.NMaybe)(__SV_LOCAL_9940___fail_9941.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } }).eval()); } };
		// name_tagRefId_workaround = fromMaybe(toString(genInt(,)), name_refIdIfOld_workaround,)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.localAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.name_tagRefId_workaround__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.PfromMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.name_refIdIfOld_workaround__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr)))); } };
		// name_tagHasForwardDcl_workaround = name_refIdIfOld_workaround.isJust
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.localAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.name_tagHasForwardDcl_workaround__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.localDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.name_refIdIfOld_workaround__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr).synthesized(core.Init.core_isJust__ON__core_Maybe)); } };
		// refId = case tags of [] -> name_tagRefId_workaround | adtRefIdTagItem(r, _)::_ -> r end
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.localAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.refId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_9956___sv_tmp_pv_9955 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_9957___sv_tmp_pv_9958 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtRefIdTagItem) { final common.Thunk<Object> __SV_LOCAL___pv9961___sv_pv_9960_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9962___sv_tmp_pv_9963 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9964_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv9961___sv_pv_9960_r.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_9964_r.eval())); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax 'Type.sv', 98, 4, 103, 7, 3011, 3223\n"))));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)(__SV_LOCAL_9956___sv_tmp_pv_9955.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee.nil()) { return (common.StringCatter)((common.StringCatter)context.localAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.name_tagRefId_workaround__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr)); }return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax 'Type.sv', 98, 4, 103, 7, 3011, 3223\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.tags__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr))); } };
		// structRefId = toString(genInt(,))
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.localAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.structRefId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))); } };
		// b.defs := case tags of [] -> [ adtTagDef(n.name, adtRefIdTagItem(refId, structRefId,),) ] | _ -> [] end
		if(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr] == null)
			edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr] = new edu.umn.cs.melt.ableC.abstractsyntax.env.CAdefs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr);
		((common.CollectionAttribute)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9965___fail_9966 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDef)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagDef(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtRefIdTagItem(context.localAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.refId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr), context.localAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.structRefId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }return ((common.ConsCell)(__SV_LOCAL_9965___fail_9966.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.tags__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr))); } }).eval()); } });
		// b.typerep = case tags of [] -> adtTagType(n.name, refId, structRefId,) | adtRefIdTagItem(r, s)::_ -> adtTagType(n.name, refId, structRefId,) | _ -> errorType(,) end
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9971___fail_9972 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PerrorType()); } };
return new common.PatternLazy<common.ConsCell, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_9974___sv_tmp_pv_9973 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_9975___sv_tmp_pv_9976 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtRefIdTagItem) { final common.Thunk<Object> __SV_LOCAL___pv9980___sv_pv_9981_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9982___sv_pv_9979_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9983_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv9982___sv_pv_9979_s.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9984_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv9980___sv_pv_9981_r.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name), context.localAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.refId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr), context.localAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.structRefId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_9971___fail_9972.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)(__SV_LOCAL_9974___sv_tmp_pv_9973.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee.nil()) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagType(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PadtTagReferenceTypeExpr.i_n, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name), context.localAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.refId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr), context.localAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.structRefId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr))); }return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_9971___fail_9972.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.tags__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_adtTagReferenceTypeExpr))); } }).eval()); } };

	}

	public static final common.NodeFactory<PadtTagReferenceTypeExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PadtTagReferenceTypeExpr> {

		@Override
		public PadtTagReferenceTypeExpr invoke(final Object[] children, final Object[] annotations) {
			return new PadtTagReferenceTypeExpr(children[0], children[1]);
		}
	};

}
