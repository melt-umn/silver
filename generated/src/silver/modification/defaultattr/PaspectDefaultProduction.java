
package silver.modification.defaultattr;

// top::AGDcl ::= 'aspect' 'default' 'production' lhs::Name '::' te::TypeExpr '::=' body::ProductionBody 
public final class PaspectDefaultProduction extends silver.definition.core.NAGDcl {

	public static final int i__G_7 = 0;
	public static final int i__G_6 = 1;
	public static final int i__G_5 = 2;
	public static final int i_lhs = 3;
	public static final int i__G_3 = 4;
	public static final int i_te = 5;
	public static final int i__G_1 = 6;
	public static final int i_body = 7;


	public static final Class<?> childTypes[] = {silver.definition.core.TAspect_kwd.class,silver.definition.core.TDefault_kwd.class,silver.definition.core.TProduction_kwd.class,silver.definition.core.NName.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TCCEQ_t.class,silver.definition.core.NProductionBody.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_defaultattr_aspectDefaultProduction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lhs] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];

	}

	public PaspectDefaultProduction(final Object c__G_7, final Object c__G_6, final Object c__G_5, final Object c_lhs, final Object c__G_3, final Object c_te, final Object c__G_1, final Object c_body, final Object a_core_location) {
		super(a_core_location);
		this.child__G_7 = c__G_7;
		this.child__G_6 = c__G_6;
		this.child__G_5 = c__G_5;
		this.child_lhs = c_lhs;
		this.child__G_3 = c__G_3;
		this.child_te = c_te;
		this.child__G_1 = c__G_1;
		this.child_body = c_body;

	}

	private Object child__G_7;
	public final silver.definition.core.TAspect_kwd getChild__G_7() {
		return (silver.definition.core.TAspect_kwd) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child__G_6;
	public final silver.definition.core.TDefault_kwd getChild__G_6() {
		return (silver.definition.core.TDefault_kwd) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child__G_5;
	public final silver.definition.core.TProduction_kwd getChild__G_5() {
		return (silver.definition.core.TProduction_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_lhs;
	public final silver.definition.core.NName getChild_lhs() {
		return (silver.definition.core.NName) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child__G_3;
	public final silver.definition.core.TColonColon_t getChild__G_3() {
		return (silver.definition.core.TColonColon_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_te;
	public final silver.definition.type.syntax.NTypeExpr getChild_te() {
		return (silver.definition.type.syntax.NTypeExpr) (child_te = common.Util.demand(child_te));
	}

	private Object child__G_1;
	public final silver.definition.core.TCCEQ_t getChild__G_1() {
		return (silver.definition.core.TCCEQ_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_7: return getChild__G_7();
			case i__G_6: return getChild__G_6();
			case i__G_5: return getChild__G_5();
			case i_lhs: return getChild_lhs();
			case i__G_3: return getChild__G_3();
			case i_te: return getChild_te();
			case i__G_1: return getChild__G_1();
			case i_body: return getChild_body();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i__G_6: return child__G_6;
			case i__G_5: return child__G_5;
			case i_lhs: return child_lhs;
			case i__G_3: return child__G_3;
			case i_te: return child_te;
			case i__G_1: return child__G_1;
			case i_body: return child_body;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 8;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:defaultattr:aspectDefaultProduction erroneously claimed to forward");
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
		return "silver:modification:defaultattr:aspectDefaultProduction";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "aspect default production\n" ++ lhs.pp ++ "::" ++ te.pp ++ " ::=\n" ++ body.pp
		silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("aspect default production\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.defaultattr.PaspectDefaultProduction.i_lhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("::")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.defaultattr.PaspectDefaultProduction.i_te).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ::=\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.defaultattr.PaspectDefaultProduction.i_body).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionBody))))))); } };
		// top.defs = []
		silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// namedSig = namedSignature(top.grammarName ++ ":default" ++ te.typerep.typeName, [], namedSignatureElement(lhs.name, te.typerep), annotationsForNonterminal(te.typerep, top.env))
		silver.modification.defaultattr.PaspectDefaultProduction.localAttributes[silver.modification.defaultattr.Init.namedSig__ON__silver_modification_defaultattr_aspectDefaultProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)new silver.definition.env.PnamedSignature(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":default")), (common.StringCatter)((common.StringCatter)((silver.definition.type.NType)context.childDecorated(silver.modification.defaultattr.PaspectDefaultProduction.i_te).synthesized(silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)new silver.definition.env.PnamedSignatureElement(context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_lhs, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_te, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PannotationsForNonterminal.invoke(context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_te, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } })); } };
		// top.errors := te.errors ++ body.errors
		if(silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_te, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_body, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionBody))); } });
		// fakedDefs = [ defaultLhsDef(top.grammarName, lhs.location, lhs.name, te.typerep) ]
		silver.modification.defaultattr.PaspectDefaultProduction.localAttributes[silver.modification.defaultattr.Init.fakedDefs__ON__silver_modification_defaultattr_aspectDefaultProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.defaultattr.PdefaultLhsDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.defaultattr.PaspectDefaultProduction.i_lhs).undecorate()).getAnno_core_location()); } }, context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_lhs, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_te, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// sigDefs = addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, te.lexicalTypeVariables)
		silver.modification.defaultattr.PaspectDefaultProduction.localAttributes[silver.modification.defaultattr.Init.sigDefs__ON__silver_modification_defaultattr_aspectDefaultProduction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.syntax.PaddNewLexicalTyVars_ActuallyVariables.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_te, silver.definition.type.syntax.Init.silver_definition_type_syntax_lexicalTypeVariables__ON__silver_definition_type_syntax_TypeExpr))); } };
		// body.env = newScopeEnv(fakedDefs ++ sigDefs, top.env)
		silver.modification.defaultattr.PaspectDefaultProduction.childInheritedAttributes[silver.modification.defaultattr.PaspectDefaultProduction.i_body][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.localAsIsLazy(silver.modification.defaultattr.Init.fakedDefs__ON__silver_modification_defaultattr_aspectDefaultProduction), context.localAsIsLazy(silver.modification.defaultattr.Init.sigDefs__ON__silver_modification_defaultattr_aspectDefaultProduction))); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// body.frame = defaultAspectContext(namedSig)
		silver.modification.defaultattr.PaspectDefaultProduction.childInheritedAttributes[silver.modification.defaultattr.PaspectDefaultProduction.i_body][silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NBlockContext)new silver.modification.defaultattr.PdefaultAspectContext(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.defaultattr.Init.namedSig__ON__silver_modification_defaultattr_aspectDefaultProduction)))); } };
		// body.downSubst = emptySubst()
		silver.modification.defaultattr.PaspectDefaultProduction.childInheritedAttributes[silver.modification.defaultattr.PaspectDefaultProduction.i_body][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()); } };
		// top.setupInh := body.setupInh
		if(silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl] == null)
			silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAsetupInh(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.defaultattr.PaspectDefaultProduction.i_body).synthesized(silver.translation.java.core.Init.silver_translation_java_core_setupInh__ON__silver_definition_core_ProductionBody)); } });
		// top.initProd := "\t\t//ASPECT DEFAULT PRODUCTION for " ++ te.pp ++ "\n" ++ body.translation
		if(silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl] == null)
			silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAinitProd(silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initProd__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\t\t//ASPECT DEFAULT PRODUCTION for ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.defaultattr.PaspectDefaultProduction.i_te).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.defaultattr.PaspectDefaultProduction.i_body).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_ProductionBody))))); } });
		// top.valueWeaving := body.valueWeaving
		if(silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl] == null)
			silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAvalueWeaving(silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.defaultattr.PaspectDefaultProduction.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.defaultattr.PaspectDefaultProduction.i_body).synthesized(silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_ProductionBody)); } });

	}

	public static final common.NodeFactory<PaspectDefaultProduction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectDefaultProduction> {

		@Override
		public PaspectDefaultProduction invoke(final Object[] children, final Object[] annotations) {
			return new PaspectDefaultProduction(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], annotations[0]);
		}
	};

}
