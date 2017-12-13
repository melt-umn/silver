
package silver.definition.core;

// top::Root ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls 
public final class Proot extends silver.definition.core.NRoot {

	public static final int i_gdcl = 0;
	public static final int i_ms = 1;
	public static final int i_ims = 2;
	public static final int i_ags = 3;


	public static final Class<?> childTypes[] = {silver.definition.core.NGrammarDcl.class,silver.definition.core.NModuleStmts.class,silver.definition.core.NImportStmts.class,silver.definition.core.NAGDcls.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_root;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NRoot.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NRoot.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_gdcl] = new common.Lazy[silver.definition.core.NGrammarDcl.num_inh_attrs];
	childInheritedAttributes[i_ms] = new common.Lazy[silver.definition.core.NModuleStmts.num_inh_attrs];
	childInheritedAttributes[i_ims] = new common.Lazy[silver.definition.core.NImportStmts.num_inh_attrs];
	childInheritedAttributes[i_ags] = new common.Lazy[silver.definition.core.NAGDcls.num_inh_attrs];

	}

	public Proot(final Object c_gdcl, final Object c_ms, final Object c_ims, final Object c_ags, final Object a_core_location) {
		super(a_core_location);
		this.child_gdcl = c_gdcl;
		this.child_ms = c_ms;
		this.child_ims = c_ims;
		this.child_ags = c_ags;

	}

	private Object child_gdcl;
	public final silver.definition.core.NGrammarDcl getChild_gdcl() {
		return (silver.definition.core.NGrammarDcl) (child_gdcl = common.Util.demand(child_gdcl));
	}

	private Object child_ms;
	public final silver.definition.core.NModuleStmts getChild_ms() {
		return (silver.definition.core.NModuleStmts) (child_ms = common.Util.demand(child_ms));
	}

	private Object child_ims;
	public final silver.definition.core.NImportStmts getChild_ims() {
		return (silver.definition.core.NImportStmts) (child_ims = common.Util.demand(child_ims));
	}

	private Object child_ags;
	public final silver.definition.core.NAGDcls getChild_ags() {
		return (silver.definition.core.NAGDcls) (child_ags = common.Util.demand(child_ags));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_gdcl: return getChild_gdcl();
			case i_ms: return getChild_ms();
			case i_ims: return getChild_ims();
			case i_ags: return getChild_ags();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_gdcl: return child_gdcl;
			case i_ms: return child_ms;
			case i_ims: return child_ims;
			case i_ags: return child_ags;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:root erroneously claimed to forward");
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
		return "silver:definition:core:root";
	}

	static void initProductionAttributeDefinitions() {
		// ims.compiledGrammars = top.compiledGrammars
		silver.definition.core.Proot.childInheritedAttributes[silver.definition.core.Proot.i_ims][silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_ImportStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_Root)); } };
		// ims.grammarDependencies = top.grammarDependencies
		silver.definition.core.Proot.childInheritedAttributes[silver.definition.core.Proot.i_ims][silver.definition.core.Init.silver_definition_core_grammarDependencies__ON__silver_definition_core_ImportStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.core.Init.silver_definition_core_grammarDependencies__ON__silver_definition_core_Root)); } };
		// ims.grammarName = top.grammarName
		silver.definition.core.Proot.childInheritedAttributes[silver.definition.core.Proot.i_ims][silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_ImportStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_Root)); } };
		// ims.config = top.config
		silver.definition.core.Proot.childInheritedAttributes[silver.definition.core.Proot.i_ims][silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_ImportStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_Root)); } };
		// top.pp = gdcl.pp ++ "\n\n" ++ ms.pp ++ "\n\n" ++ ims.pp ++ "\n\n" ++ ags.pp
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.Proot.i_gdcl).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_GrammarDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.Proot.i_ms).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ModuleStmts)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.Proot.i_ims).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ImportStmts)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcls)))))))); } };
		// top.declaredName = gdcl.declaredName
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_declaredName__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.Proot.i_gdcl).synthesized(silver.definition.core.Init.silver_definition_env_declaredName__ON__silver_definition_core_GrammarDcl)); } };
		// top.moduleNames = ims.moduleNames ++ ms.moduleNames ++ ags.moduleNames
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.Proot.i_ims, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ImportStmts), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.Proot.i_ms, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_ModuleStmts), context.childDecoratedSynthesizedLazy(silver.definition.core.Proot.i_ags, silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_AGDcls))); } })); } };
		// top.defs = ags.defs
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcls)); } };
		// top.importedDefs = ms.defs
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_importedDefs__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ms).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleStmts)); } };
		// top.exportedGrammars = ms.exportedGrammars
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ms).synthesized(silver.definition.core.Init.silver_definition_env_exportedGrammars__ON__silver_definition_core_ModuleStmts)); } };
		// top.optionalGrammars = ms.optionalGrammars
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ms).synthesized(silver.definition.core.Init.silver_definition_env_optionalGrammars__ON__silver_definition_core_ModuleStmts)); } };
		// top.condBuild = ms.condBuild
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ms).synthesized(silver.definition.core.Init.silver_definition_env_condBuild__ON__silver_definition_core_ModuleStmts)); } };
		// top.errors := gdcl.errors ++ ms.errors ++ ims.errors ++ ags.errors
		if(silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Root] == null)
			silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Root] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Root);
		((common.CollectionAttribute)silver.definition.core.Proot.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Root]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.Proot.i_gdcl, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_GrammarDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.Proot.i_ms, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ModuleStmts), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.Proot.i_ims, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ImportStmts), context.childDecoratedSynthesizedLazy(silver.definition.core.Proot.i_ags, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcls))); } })); } })); } });
		// ags.env = appendEnv(top.env, newScopeEnv(ims.defs, top.globalImports))
		silver.definition.core.Proot.childInheritedAttributes[silver.definition.core.Proot.i_ags][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PappendEnv.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Root), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.Proot.i_ims, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ImportStmts), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_globalImports__ON__silver_definition_core_Root))); } })); } };

	}

	public static final common.NodeFactory<Proot> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Proot> {

		@Override
		public Proot invoke(final Object[] children, final Object[] annotations) {
			return new Proot(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
