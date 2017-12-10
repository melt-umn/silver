
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax;

// top::Decl ::= adt::ADTDecl 
public final class PdatatypeDecl extends edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl {

	public static final int i_adt = 0;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NADTDecl.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_datatypeDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_adt] = new common.Lazy[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NADTDecl.num_inh_attrs];

	}

	public PdatatypeDecl(final Object c_adt) {
		super();
		this.child_adt = c_adt;

	}

	private Object child_adt;
	public final edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NADTDecl getChild_adt() {
		return (edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.NADTDecl) (child_adt = common.Util.demand(child_adt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_adt: return getChild_adt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_adt: return child_adt;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt).synthesized(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_transform__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl));
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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:datatype:abstractsyntax:datatypeDecl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ppConcat([ text("datatype",), space(,), adt.pp ],)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("datatype")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_pp__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
		// top.errors <- adt.errors
		if(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl] == null)
			edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl);
		((common.CollectionAttribute)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt).synthesized(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.silver_langutil_errors__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl)); } });
		// adt.env = top.env
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.childInheritedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt][edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl)); } };
		// lookupEnv = addEnv(forward.defs, openScope(top.env,),)
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.localAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.lookupEnv__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_datatypeDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.forward().synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PopenScope.invoke(context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl))); } })); } };
		// adt.structRefId = case lookupTag(adt.name, lookupEnv,) of refIdTagItem(_, r)::_ -> r | _ -> error("struct ref id not found.",) end
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.childInheritedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt][edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_structRefId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10013___fail_10014 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("struct ref id not found.")))); } };
return new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_10019___sv_tmp_pv_10018 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_10020___sv_tmp_pv_10021 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.env.PrefIdTagItem) { final common.Thunk<Object> __SV_LOCAL___pv10024___sv_tmp_pv_10025 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10026___sv_pv_10023_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10027_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv10026___sv_pv_10023_r.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_10027_r.eval())); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)(__SV_LOCAL_10013___fail_10014.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.env.NTagItem)(__SV_LOCAL_10019___sv_tmp_pv_10018.eval())).decorate(context, (common.Lazy[])null)); }return ((common.StringCatter)(__SV_LOCAL_10013___fail_10014.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupTag.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl), context.localAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.lookupEnv__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_datatypeDecl)))); } }).eval()); } };
		// adt.structDcl = case lookupRefId(adt.structRefId, lookupEnv,) of structRefIdItem(s)::_ -> s | _ -> error("struct decl not found",) end
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.childInheritedAttributes[edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt][edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_structDcl__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10028___fail_10029 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Perror.invoke((new common.StringCatter("struct decl not found")))); } };
return new common.PatternLazy<common.ConsCell, common.DecoratedNode>() { public final common.DecoratedNode eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_10034___sv_tmp_pv_10033 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_10035___sv_tmp_pv_10036 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.DecoratedNode>() { public final common.DecoratedNode eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.env.PstructRefIdItem) { final common.Thunk<Object> __SV_LOCAL___pv10039___sv_pv_10038_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childAsIs(0); } };
 return (common.DecoratedNode)((common.DecoratedNode)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10040_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10039___sv_pv_10038_s.eval())); } };
return ((common.DecoratedNode)(__SV_LOCAL_10040_s.eval())); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.DecoratedNode)(__SV_LOCAL_10028___fail_10029.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem)(__SV_LOCAL_10034___sv_tmp_pv_10033.eval())).decorate(context, (common.Lazy[])null)); }return ((common.DecoratedNode)(__SV_LOCAL_10028___fail_10029.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupRefId.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt).inherited(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_structRefId__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl)); } }, context.localAsIsLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.lookupEnv__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_datatypeDecl)))); } }).eval()); } };
		// top.defs := adt.defs ++ forward.defs
		if(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl] == null)
			edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl] = new edu.umn.cs.melt.ableC.abstractsyntax.env.CAdefs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl);
		((common.CollectionAttribute)edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.forward().synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl)); } })); } });
		//env = addEnv(adt.defs, top.env,);
		edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.forwardInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.PdatatypeDecl.i_adt, edu.umn.cs.melt.exts.ableC.algebraicDataTypes.datatype.abstractsyntax.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_datatype_abstractsyntax_ADTDecl), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Decl))); } };

	}

	public static final common.NodeFactory<PdatatypeDecl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdatatypeDecl> {

		@Override
		public PdatatypeDecl invoke(final Object[] children, final Object[] annotations) {
			return new PdatatypeDecl(children[0]);
		}
	};

}
