
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::Name ::= n::String 
public final class Pname extends edu.umn.cs.melt.ableC.abstractsyntax.host.NName {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pname(final Object c_n, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:name erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:name";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:name(, n,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)context.undecorate()).getAnno_core_location()); } })); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:name(, n,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)context.undecorate()).getAnno_core_location()); } })); } };
		// top.name = n
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n)); } };
		// top.pp = text(n,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n))); } };
		// top.valueLocalLookup = lookupValueInLocalScope(n, top.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupValueInLocalScope.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } };
		// top.valueRedeclarationCheck = doValueRedeclarationCheck(_, top,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueRedeclarationCheck__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return edu.umn.cs.melt.ableC.abstractsyntax.host.PdoValueRedeclarationCheck.factory.invokePartial(new int[]{1}, new Object[]{context}); } };
		// top.valueRedeclarationCheckNoCompatible = doValueRedeclarationCheckNoCompatible(top,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueRedeclarationCheckNoCompatible__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.host.PdoValueRedeclarationCheckNoCompatible.invoke(context)); } };
		// top.valueMergeRedeclExtnQualifiers = doValueMergeQualifiers(_, top,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueMergeRedeclExtnQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return edu.umn.cs.melt.ableC.abstractsyntax.host.PdoValueMergeQualifiers.factory.invokePartial(new int[]{1}, new Object[]{context}); } };
		// top.tagLocalLookup = lookupTagInLocalScope(n, top.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupTagInLocalScope.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } };
		// refIdIfOld = case top.tagLocalLookup of refIdTagItem(_, thisRefID)::_ -> just(thisRefID,) | _ -> nothing(,) end
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.refIdIfOld__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6212___fail_6213 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.ConsCell, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6218___sv_tmp_pv_6217 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6219___sv_tmp_pv_6220 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.env.PrefIdTagItem) { final common.Thunk<Object> __SV_LOCAL___pv6223___sv_tmp_pv_6224 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6225___sv_pv_6222_thisRefID = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6226_thisRefID = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv6225___sv_pv_6222_thisRefID.eval())); } };
return ((core.NMaybe)new core.Pjust(__SV_LOCAL_6226_thisRefID)); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_6212___fail_6213.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)(__SV_LOCAL_6218___sv_tmp_pv_6217.eval())).decorate(context, (common.Lazy[])null)); }return ((core.NMaybe)(__SV_LOCAL_6212___fail_6213.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagLocalLookup__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } }).eval()); } };
		// top.tagHasForwardDcl = refIdIfOld.isJust
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagHasForwardDcl__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.refIdIfOld__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name).synthesized(core.Init.core_isJust__ON__core_Maybe)); } };
		// top.tagRefId = fromMaybe(toString(genInt(,)), refIdIfOld,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagRefId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.PfromMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.refIdIfOld__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name)))); } };
		// labdcls = lookupLabel(n, top.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.labdcls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupLabel.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } };
		// top.labelRedeclarationCheck = case labdcls of [] -> [ err(top.location, "INTERNAL compiler error: expected to find label in function scope, was missing.",) ] | [_] -> [] | _::_::_ -> [ err(top.location, "Redeclaration of " ++ n,) ] end
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_labelRedeclarationCheck__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6228___sv_tmp_pv_6229 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6230___sv_tmp_pv_6227 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6231___sv_tmp_pv_6232 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6233___sv_tmp_pv_6234 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Redeclaration of ")), (common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Name.sv', 51, 4, 55, 7, 2444, 2738\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL_6230___sv_tmp_pv_6227.eval()))); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("INTERNAL compiler error: expected to find label in function scope, was missing.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Name.sv', 51, 4, 55, 7, 2444, 2738\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.labdcls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name))); } };
		// values = lookupValue(n, top.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.values__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupValue.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } };
		// tags = lookupTag(n, top.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupTag.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } };
		// labels = lookupLabel(n, top.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupLabel.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name))); } };
		// top.valueLookupCheck = case values of [] -> [ err(top.location, "Undeclared value " ++ n,) ] | _::_ -> [] end
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueLookupCheck__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6246___sv_tmp_pv_6247 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6248___sv_tmp_pv_6249 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)core.Pnil.invoke()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Undeclared value ")), (common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Name.sv', 61, 4, 64, 7, 2937, 3036\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.values__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name))); } };
		// top.labelLookupCheck = case labels of [] -> [ err(top.location, "Undeclared label " ++ n,) ] | _::_ -> [] end
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_labelLookupCheck__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6255___sv_tmp_pv_6256 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6257___sv_tmp_pv_6258 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)core.Pnil.invoke()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Undeclared label ")), (common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Name.sv', 66, 4, 69, 7, 3067, 3166\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name))); } };
		// top.tagLookupCheck = case tags of [] -> [ err(top.location, "Undeclared tag " ++ n,) ] | _::_ -> [] end
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagLookupCheck__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_6264___sv_tmp_pv_6265 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_6266___sv_tmp_pv_6267 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)core.Pnil.invoke()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Undeclared tag ")), (common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.i_n))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Name.sv', 71, 4, 74, 7, 3195, 3290\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.localAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name))); } };
		// value = if null(values,) then errorValueItem(,) else head(values,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.value__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.values__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name))) ? ((edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem)new edu.umn.cs.melt.ableC.abstractsyntax.env.PerrorValueItem()) : ((edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem)core.Phead.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.values__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name)))); } };
		// tag = if null(tags,) then errorTagItem(,) else head(tags,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.tag__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name))) ? ((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)new edu.umn.cs.melt.ableC.abstractsyntax.env.PerrorTagItem()) : ((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)core.Phead.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.tags__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name)))); } };
		// label = if null(labels,) then errorLabelItem(,) else head(labels,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.label__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name))) ? ((edu.umn.cs.melt.ableC.abstractsyntax.env.NLabelItem)new edu.umn.cs.melt.ableC.abstractsyntax.env.PerrorLabelItem()) : ((edu.umn.cs.melt.ableC.abstractsyntax.env.NLabelItem)core.Phead.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.labels__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name)))); } };
		// top.valueItem = value
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueItem__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.value__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name); } };
		// top.tagItem = tag
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_tagItem__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.tag__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name); } };
		// top.labelItem = label
		edu.umn.cs.melt.ableC.abstractsyntax.host.Pname.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_labelItem__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.label__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_name); } };

	}

	public static final common.NodeFactory<Pname> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pname> {

		@Override
		public Pname invoke(final Object[] children, final Object[] annotations) {
			return new Pname(children[0], annotations[0]);
		}
	};

}
