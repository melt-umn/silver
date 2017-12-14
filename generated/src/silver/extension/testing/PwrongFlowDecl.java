
package silver.extension.testing;

// top::AGDcl ::= 'wrongFlowCode' s::String_t '{' ags::AGDcls '}' 
public final class PwrongFlowDecl extends silver.definition.core.NAGDcl {

	public static final int i__G_4 = 0;
	public static final int i_s = 1;
	public static final int i__G_2 = 2;
	public static final int i_ags = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.extension.testing.TWrongFlowCode_kwd.class,silver.definition.core.TString_t.class,silver.definition.core.TLCurly_t.class,silver.definition.core.NAGDcls.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_testing_wrongFlowDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ags] = new common.Lazy[silver.definition.core.NAGDcls.num_inh_attrs];

	}

	public PwrongFlowDecl(final Object c__G_4, final Object c_s, final Object c__G_2, final Object c_ags, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_s = c_s;
		this.child__G_2 = c__G_2;
		this.child_ags = c_ags;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.extension.testing.TWrongFlowCode_kwd getChild__G_4() {
		return (silver.extension.testing.TWrongFlowCode_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_s;
	public final silver.definition.core.TString_t getChild_s() {
		return (silver.definition.core.TString_t) (child_s = common.Util.demand(child_s));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ags;
	public final silver.definition.core.NAGDcls getChild_ags() {
		return (silver.definition.core.NAGDcls) (child_ags = common.Util.demand(child_ags));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_s: return getChild_s();
			case i__G_2: return getChild__G_2();
			case i_ags: return getChild_ags();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_s: return child_s;
			case i__G_2: return child__G_2;
			case i_ags: return child_ags;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:testing:wrongFlowDecl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "wrongFlowCode" ++ s.lexeme ++ "{" ++ ags.pp ++ "}"
		silver.extension.testing.PwrongFlowDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("wrongFlowCode")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.extension.testing.PwrongFlowDecl.i_s)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("{")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.testing.PwrongFlowDecl.i_ags).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcls)), (common.StringCatter)(new common.StringCatter("}")))))); } };
		// top.errors := if indexOf(substring(1, length(s.lexeme) - 1, s.lexeme), foldMessages(ags.errors)) == -1 then [ err(top.location, "Wrong code did not raise an error containing " ++ s.lexeme ++ ". Bubbling up errors from lines " ++ toString($3.line) ++ " to " ++ toString($5.line)) ] ++ ags.errors else []
		if(silver.extension.testing.PwrongFlowDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.extension.testing.PwrongFlowDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.extension.testing.PwrongFlowDecl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)core.PindexOf.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.extension.testing.PwrongFlowDecl.i_s)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.extension.testing.PwrongFlowDecl.i_s)).lexeme))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.core.PfoldMessages.invoke(context.childDecoratedSynthesizedLazy(silver.extension.testing.PwrongFlowDecl.i_ags, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcls))); } })).equals(Integer.valueOf((int)-1)) ? ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Wrong code did not raise an error containing ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.extension.testing.PwrongFlowDecl.i_s)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(". Bubbling up errors from lines ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((silver.definition.core.TLCurly_t)context.childAsIs(silver.extension.testing.PwrongFlowDecl.i__G_2)).getLine()))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" to ")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((silver.definition.core.TRCurly_t)context.childAsIs(silver.extension.testing.PwrongFlowDecl.i__G_0)).getLine())))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.extension.testing.PwrongFlowDecl.i_ags, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcls))) : ((common.ConsCell)core.Pnil.invoke())); } });
		// ags.env = newScopeEnv(ags.defs, top.env)
		silver.extension.testing.PwrongFlowDecl.childInheritedAttributes[silver.extension.testing.PwrongFlowDecl.i_ags][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childDecoratedSynthesizedLazy(silver.extension.testing.PwrongFlowDecl.i_ags, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcls), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// top.flowDefs = ags.flowDefs
		silver.extension.testing.PwrongFlowDecl.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.testing.PwrongFlowDecl.i_ags).synthesized(silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_AGDcls)); } };

	}

	public static final common.NodeFactory<PwrongFlowDecl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PwrongFlowDecl> {

		@Override
		public PwrongFlowDecl invoke(final Object[] children, final Object[] annotations) {
			return new PwrongFlowDecl(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
