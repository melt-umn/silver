
package silver.definition.flow.syntax;

// top::FlowSpec ::= attr::FlowSpecId '{' inhs::FlowSpecInhs '}' 
public final class PflowSpecDcl extends silver.definition.flow.syntax.NFlowSpec {

	public static final int i_attr = 0;
	public static final int i__G_2 = 1;
	public static final int i_inhs = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.definition.flow.syntax.NFlowSpecId.class,silver.definition.core.TLCurly_t.class,silver.definition.flow.syntax.NFlowSpecInhs.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_flowSpecDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpec.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpec.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_attr] = new common.Lazy[silver.definition.flow.syntax.NFlowSpecId.num_inh_attrs];
	childInheritedAttributes[i_inhs] = new common.Lazy[silver.definition.flow.syntax.NFlowSpecInhs.num_inh_attrs];

	}

	public PflowSpecDcl(final Object c_attr, final Object c__G_2, final Object c_inhs, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_attr = c_attr;
		this.child__G_2 = c__G_2;
		this.child_inhs = c_inhs;
		this.child__G_0 = c__G_0;

	}

	private Object child_attr;
	public final silver.definition.flow.syntax.NFlowSpecId getChild_attr() {
		return (silver.definition.flow.syntax.NFlowSpecId) (child_attr = common.Util.demand(child_attr));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_inhs;
	public final silver.definition.flow.syntax.NFlowSpecInhs getChild_inhs() {
		return (silver.definition.flow.syntax.NFlowSpecInhs) (child_inhs = common.Util.demand(child_inhs));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_attr: return getChild_attr();
			case i__G_2: return getChild__G_2();
			case i_inhs: return getChild_inhs();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_attr: return child_attr;
			case i__G_2: return child__G_2;
			case i_inhs: return child_inhs;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:flowSpecDcl erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:flowSpecDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = attr.pp ++ " {" ++ inhs.pp ++ "}"
		silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowSpecDcl.i_attr).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecId)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" {")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowSpecDcl.i_inhs).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecInhs)), (common.StringCatter)(new common.StringCatter("}"))))); } };
		// top.errors := attr.errors ++ inhs.errors
		if(silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec] == null)
			silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec);
		((common.CollectionAttribute)silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_attr, silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId), context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_inhs, silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInhs))); } });
		// top.errors <- if ! null(attr.errors) || isExportedBy(top.grammarName, [ attr.authorityGrammar ], top.compiledGrammars) then [] else [ err(attr.location, "flow type for " ++ attr.pp ++ " must be exported by " ++ attr.authorityGrammar) ]
		if(silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec] == null)
			silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec);
		((common.CollectionAttribute)silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((!((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_attr, silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId)))) || ((Boolean)silver.driver.util.PisExportedBy.invoke(context.contextInheritedLazy(silver.definition.flow.syntax.Init.silver_definition_core_grammarName__ON__silver_definition_flow_syntax_FlowSpec), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_attr, silver.definition.flow.syntax.Init.silver_definition_flow_syntax_authorityGrammar__ON__silver_definition_flow_syntax_FlowSpecId), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.contextInheritedLazy(silver.definition.flow.syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_flow_syntax_FlowSpec)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.flow.syntax.NFlowSpecId)context.childDecorated(silver.definition.flow.syntax.PflowSpecDcl.i_attr).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("flow type for ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowSpecDcl.i_attr).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecId)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" must be exported by ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowSpecDcl.i_attr).synthesized(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_authorityGrammar__ON__silver_definition_flow_syntax_FlowSpecId))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.errors <- if null(attr.errors) && length(filter(stringEq(attr.synName, _), getSpecifiedSynsForNt(top.onNt.typeName, top.flowEnv))) > 1 then [ err(attr.location, "duplicate specification of flow type for " ++ attr.pp ++ " on " ++ top.onNt.typeName) ] else []
		if(silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec] == null)
			silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec);
		((common.CollectionAttribute)silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_attr, silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId))) && (((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfilter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return core.PstringEq.factory.invokePartial(new int[]{0}, new Object[]{context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_attr, silver.definition.flow.syntax.Init.silver_definition_flow_syntax_synName__ON__silver_definition_flow_syntax_FlowSpecId)}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PgetSpecifiedSynsForNt.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpec)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.contextInheritedLazy(silver.definition.flow.syntax.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_flow_syntax_FlowSpec))); } })); } })) > Integer.valueOf((int)1))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.flow.syntax.NFlowSpecId)context.childDecorated(silver.definition.flow.syntax.PflowSpecDcl.i_attr).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("duplicate specification of flow type for ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowSpecDcl.i_attr).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecId)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" on ")), (common.StringCatter)((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpec)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// myFlow = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes
		silver.definition.flow.syntax.PflowSpecDcl.localAttributes[silver.definition.flow.syntax.Init.myFlow__ON__silver_definition_flow_syntax_flowSpecDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)((common.DecoratedNode)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.contextInheritedLazy(silver.definition.flow.syntax.Init.silver_definition_core_grammarName__ON__silver_definition_flow_syntax_FlowSpec), context.contextInheritedLazy(silver.definition.flow.syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_flow_syntax_FlowSpec))); } })).inherited(silver.driver.util.Init.silver_definition_env_grammarFlowTypes__ON__silver_driver_util_RootSpec)); } };
		// missingFt = set:toList(set:removeAll(inhs.inhList, inhDepsForSyn("forward", top.onNt.typeName, myFlow)))
		silver.definition.flow.syntax.PflowSpecDcl.localAttributes[silver.definition.flow.syntax.Init.missingFt__ON__silver_definition_flow_syntax_flowSpecDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.raw.treeset.PtoList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treeset.PremoveAll.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_inhs, silver.definition.flow.syntax.Init.silver_definition_flow_syntax_inhList__ON__silver_definition_flow_syntax_FlowSpecInhs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.flow.driver.PinhDepsForSyn.invoke((new common.StringCatter("forward")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpec)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.localAsIsLazy(silver.definition.flow.syntax.Init.myFlow__ON__silver_definition_flow_syntax_flowSpecDcl))); } })); } })); } };
		// top.errors <- if ! null(attr.errors) || ! (top.config.warnAll || top.config.warnMissingInh) || isExportedBy(attr.authorityGrammar, [ hackGramFromFName(top.onNt.typeName) ], top.compiledGrammars) || null(missingFt) then [] else [ err(attr.location, attr.pp ++ " is an extension synthesized attribute, and must contain at least the forward flow type. It is missing " ++ implode(", ", missingFt)) ]
		if(silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec] == null)
			silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec);
		((common.CollectionAttribute)silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((((!((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_attr, silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId)))) || (!(((Boolean)((common.DecoratedNode)context.inherited(silver.definition.flow.syntax.Init.silver_definition_env_config__ON__silver_definition_flow_syntax_FlowSpec)).synthesized(silver.analysis.warnings.Init.silver_analysis_warnings_warnAll__ON__silver_util_cmdargs_CmdArgs)) || ((Boolean)((common.DecoratedNode)context.inherited(silver.definition.flow.syntax.Init.silver_definition_env_config__ON__silver_definition_flow_syntax_FlowSpec)).synthesized(silver.analysis.warnings.defs.Init.silver_analysis_warnings_defs_warnMissingInh__ON__silver_util_cmdargs_CmdArgs))))) || ((Boolean)silver.driver.util.PisExportedBy.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_attr, silver.definition.flow.syntax.Init.silver_definition_flow_syntax_authorityGrammar__ON__silver_definition_flow_syntax_FlowSpecId), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.flow.env.PhackGramFromFName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpec)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.contextInheritedLazy(silver.definition.flow.syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_flow_syntax_FlowSpec)))) || ((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.flow.syntax.Init.missingFt__ON__silver_definition_flow_syntax_flowSpecDcl)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.flow.syntax.NFlowSpecId)context.childDecorated(silver.definition.flow.syntax.PflowSpecDcl.i_attr).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowSpecDcl.i_attr).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecId)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" is an extension synthesized attribute, and must contain at least the forward flow type. It is missing ")), (common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), context.localAsIsLazy(silver.definition.flow.syntax.Init.missingFt__ON__silver_definition_flow_syntax_flowSpecDcl))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.flowDefs = if ! null(attr.errors) then [] else [ specificationFlowDef(top.onNt.typeName, attr.synName, inhs.inhList) ]
		silver.definition.flow.syntax.PflowSpecDcl.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_FlowSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_attr, silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowDef)new silver.definition.flow.ast.PspecificationFlowDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpec)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_attr, silver.definition.flow.syntax.Init.silver_definition_flow_syntax_synName__ON__silver_definition_flow_syntax_FlowSpecId), context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecDcl.i_inhs, silver.definition.flow.syntax.Init.silver_definition_flow_syntax_inhList__ON__silver_definition_flow_syntax_FlowSpecInhs))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } };

	}

	public static final common.NodeFactory<PflowSpecDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PflowSpecDcl> {

		@Override
		public PflowSpecDcl invoke(final Object[] children, final Object[] annotations) {
			return new PflowSpecDcl(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
