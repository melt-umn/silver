
package silver.modification.copper;

// top::AGDcl ::= 'disambiguate' terms::TermList acode::ActionCode_c 
public final class PdisambiguationGroupDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_2 = 0;
	public static final int i_terms = 1;
	public static final int i_acode = 2;


	public static final Class<?> childTypes[] = {silver.modification.copper.TDisambiguation_kwd.class,silver.modification.copper.NTermList.class,silver.modification.copper.NActionCode_c.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_disambiguationGroupDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_terms] = new common.Lazy[silver.modification.copper.NTermList.num_inh_attrs];
	childInheritedAttributes[i_acode] = new common.Lazy[silver.modification.copper.NActionCode_c.num_inh_attrs];

	}

	public PdisambiguationGroupDcl(final Object c__G_2, final Object c_terms, final Object c_acode, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_terms = c_terms;
		this.child_acode = c_acode;

	}

	private Object child__G_2;
	public final silver.modification.copper.TDisambiguation_kwd getChild__G_2() {
		return (silver.modification.copper.TDisambiguation_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_terms;
	public final silver.modification.copper.NTermList getChild_terms() {
		return (silver.modification.copper.NTermList) (child_terms = common.Util.demand(child_terms));
	}

	private Object child_acode;
	public final silver.modification.copper.NActionCode_c getChild_acode() {
		return (silver.modification.copper.NActionCode_c) (child_acode = common.Util.demand(child_acode));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_terms: return getChild_terms();
			case i_acode: return getChild_acode();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_terms: return child_terms;
			case i_acode: return child_acode;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:disambiguationGroupDcl erroneously claimed to forward");
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
		return "silver:modification:copper:disambiguationGroupDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "disambiguate " ++ terms.pp ++ " " ++ acode.pp
		silver.modification.copper.PdisambiguationGroupDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("disambiguate ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PdisambiguationGroupDcl.i_terms).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermList)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PdisambiguationGroupDcl.i_acode).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ActionCode_c))))); } };
		// top.errors := terms.errors ++ acode.errors
		if(silver.modification.copper.PdisambiguationGroupDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper.PdisambiguationGroupDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper.PdisambiguationGroupDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PdisambiguationGroupDcl.i_terms, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList), context.childDecoratedSynthesizedLazy(silver.modification.copper.PdisambiguationGroupDcl.i_acode, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ActionCode_c))); } });
		// acode.env = newScopeEnv((disambigLexemeDef(top.grammarName, top.location) :: acode.defs) ++ terms.defs, top.env)
		silver.modification.copper.PdisambiguationGroupDcl.childInheritedAttributes[silver.modification.copper.PdisambiguationGroupDcl.i_acode][silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ActionCode_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.copper.PdisambigLexemeDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PdisambiguationGroupDcl.i_acode, silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_ActionCode_c))); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PdisambiguationGroupDcl.i_terms, silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_TermList))); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// fName = top.grammarName ++ ":__disam" ++ toString(top.location.line)
		silver.modification.copper.PdisambiguationGroupDcl.localAttributes[silver.modification.copper.Init.fName__ON__silver_modification_copper_disambiguationGroupDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":__disam")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_line__ON__core_Location)))))); } };
		// acode.frame = disambiguationContext()
		silver.modification.copper.PdisambiguationGroupDcl.childInheritedAttributes[silver.modification.copper.PdisambiguationGroupDcl.i_acode][silver.modification.copper.Init.silver_definition_core_frame__ON__silver_modification_copper_ActionCode_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.modification.copper.PdisambiguationContext()); } };
		// top.syntaxAst = [ syntaxDisambiguationGroup(fName, terms.termList, acode.actionCode) ]
		silver.modification.copper.PdisambiguationGroupDcl.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup(context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_disambiguationGroupDcl), context.childDecoratedSynthesizedLazy(silver.modification.copper.PdisambiguationGroupDcl.i_terms, silver.modification.copper.Init.silver_modification_copper_termList__ON__silver_modification_copper_TermList), context.childDecoratedSynthesizedLazy(silver.modification.copper.PdisambiguationGroupDcl.i_acode, silver.modification.copper.Init.silver_modification_copper_actionCode__ON__silver_modification_copper_ActionCode_c))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PdisambiguationGroupDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdisambiguationGroupDcl> {

		@Override
		public PdisambiguationGroupDcl invoke(final Object[] children, final Object[] annotations) {
			return new PdisambiguationGroupDcl(children[0], children[1], children[2], annotations[0]);
		}
	};

}
