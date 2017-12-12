
package silver.extension.doc.core;

// top::DocConfigs ::= c::DocConfig rest::DocConfigs 
public final class PconsConfigs extends silver.extension.doc.core.NDocConfigs {

	public static final int i_c = 0;
	public static final int i_rest = 1;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.NDocConfig.class,silver.extension.doc.core.NDocConfigs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_consConfigs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NDocConfigs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NDocConfigs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_c] = new common.Lazy[silver.extension.doc.core.NDocConfig.num_inh_attrs];
	childInheritedAttributes[i_rest] = new common.Lazy[silver.extension.doc.core.NDocConfigs.num_inh_attrs];

	}

	public PconsConfigs(final Object c_c, final Object c_rest) {
		super();
		this.child_c = c_c;
		this.child_rest = c_rest;

	}

	private Object child_c;
	public final silver.extension.doc.core.NDocConfig getChild_c() {
		return (silver.extension.doc.core.NDocConfig) (child_c = common.Util.demand(child_c));
	}

	private Object child_rest;
	public final silver.extension.doc.core.NDocConfigs getChild_rest() {
		return (silver.extension.doc.core.NDocConfigs) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_c: return getChild_c();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_c: return child_c;
			case i_rest: return child_rest;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:consConfigs erroneously claimed to forward");
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
		return "silver:extension:doc:core:consConfigs";
	}

	static void initProductionAttributeDefinitions() {
		// headerWarnings = if c.header != "" && rest.header != "" then "Multiple header definitions in documentation configuration." else ""
		silver.extension.doc.core.PconsConfigs.localAttributes[silver.extension.doc.core.Init.headerWarnings__ON__silver_extension_doc_core_consConfigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((common.StringCatter)context.childDecorated(silver.extension.doc.core.PconsConfigs.i_c).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfig)).equals((new common.StringCatter(""))) && !((common.StringCatter)context.childDecorated(silver.extension.doc.core.PconsConfigs.i_rest).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfigs)).equals((new common.StringCatter("")))) ? (new common.StringCatter("Multiple header definitions in documentation configuration.")) : (new common.StringCatter(""))); } };
		// splitFilesWarnings = if c.header != "" && rest.header != "" then "Multpile split-files definitions in documentation configuration." else ""
		silver.extension.doc.core.PconsConfigs.localAttributes[silver.extension.doc.core.Init.splitFilesWarnings__ON__silver_extension_doc_core_consConfigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((common.StringCatter)context.childDecorated(silver.extension.doc.core.PconsConfigs.i_c).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfig)).equals((new common.StringCatter(""))) && !((common.StringCatter)context.childDecorated(silver.extension.doc.core.PconsConfigs.i_rest).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfigs)).equals((new common.StringCatter("")))) ? (new common.StringCatter("Multpile split-files definitions in documentation configuration.")) : (new common.StringCatter(""))); } };
		// top.header = case pair(c.header, rest.header) of pair("", h) -> h | pair(h, _) -> h end
		silver.extension.doc.core.PconsConfigs.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Ppair) { final common.Thunk<Object> __SV_LOCAL___pv11423___sv_tmp_pv_11422 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv11424___sv_pv_11425_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11427___fail_11428 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11426_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11423___sv_tmp_pv_11422.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_11426_h.eval())); } }).eval()); } };
return new common.PatternLazy<common.StringCatter, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.StringCatter scrutineeIter) {final common.StringCatter scrutinee = scrutineeIter; if(scrutinee.equals("")) { return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11434_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11424___sv_pv_11425_h.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_11434_h.eval())); } }).eval()); }return ((common.StringCatter)(__SV_LOCAL_11427___fail_11428.eval()));}}.eval(context, (common.StringCatter)((common.StringCatter)(__SV_LOCAL___pv11423___sv_tmp_pv_11422.eval()))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'DocConfig.sv', 31, 15, 34, 30, 1024, 1114\n"))));}}.eval(context, (common.DecoratedNode)((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.extension.doc.core.PconsConfigs.i_c, silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfig), context.childDecoratedSynthesizedLazy(silver.extension.doc.core.PconsConfigs.i_rest, silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfigs))).decorate(context, (common.Lazy[])null)); } };
		// top.splitFiles = case pair(c.splitFiles, rest.splitFiles) of pair("", s) -> s | pair(s, _) -> s end
		silver.extension.doc.core.PconsConfigs.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_splitFiles__ON__silver_extension_doc_core_DocConfigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Ppair) { final common.Thunk<Object> __SV_LOCAL___pv11439___sv_tmp_pv_11438 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv11440___sv_pv_11441_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11443___fail_11444 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11442_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11439___sv_tmp_pv_11438.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_11442_s.eval())); } }).eval()); } };
return new common.PatternLazy<common.StringCatter, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.StringCatter scrutineeIter) {final common.StringCatter scrutinee = scrutineeIter; if(scrutinee.equals("")) { return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11450_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11440___sv_pv_11441_s.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_11450_s.eval())); } }).eval()); }return ((common.StringCatter)(__SV_LOCAL_11443___fail_11444.eval()));}}.eval(context, (common.StringCatter)((common.StringCatter)(__SV_LOCAL___pv11439___sv_tmp_pv_11438.eval()))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'DocConfig.sv', 36, 19, 39, 38, 1136, 1237\n"))));}}.eval(context, (common.DecoratedNode)((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.extension.doc.core.PconsConfigs.i_c, silver.extension.doc.core.Init.silver_extension_doc_core_splitFiles__ON__silver_extension_doc_core_DocConfig), context.childDecoratedSynthesizedLazy(silver.extension.doc.core.PconsConfigs.i_rest, silver.extension.doc.core.Init.silver_extension_doc_core_splitFiles__ON__silver_extension_doc_core_DocConfigs))).decorate(context, (common.Lazy[])null)); } };
		// top.noDoc = c.noDoc || rest.noDoc
		silver.extension.doc.core.PconsConfigs.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_noDoc__ON__silver_extension_doc_core_DocConfigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.extension.doc.core.PconsConfigs.i_c).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_noDoc__ON__silver_extension_doc_core_DocConfig)) || ((Boolean)context.childDecorated(silver.extension.doc.core.PconsConfigs.i_rest).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_noDoc__ON__silver_extension_doc_core_DocConfigs))); } };
		// top.warnings = headerWarnings ++ splitFilesWarnings ++ rest.warnings
		silver.extension.doc.core.PconsConfigs.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_warnings__ON__silver_extension_doc_core_DocConfigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.extension.doc.core.Init.headerWarnings__ON__silver_extension_doc_core_consConfigs)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.extension.doc.core.Init.splitFilesWarnings__ON__silver_extension_doc_core_consConfigs)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.doc.core.PconsConfigs.i_rest).synthesized(silver.extension.doc.core.Init.silver_extension_doc_core_warnings__ON__silver_extension_doc_core_DocConfigs)))); } };

	}

	public static final common.NodeFactory<PconsConfigs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsConfigs> {

		@Override
		public PconsConfigs invoke(final Object[] children, final Object[] annotations) {
			return new PconsConfigs(children[0], children[1]);
		}
	};

}
