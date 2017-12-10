
package silver.modification.copper;

// top::AGDcl ::= 'parser' n::Name '::' t::TypeExpr '{' m::ParserComponents '}' 
public final class PparserDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_6 = 0;
	public static final int i_n = 1;
	public static final int i__G_4 = 2;
	public static final int i_t = 3;
	public static final int i__G_2 = 4;
	public static final int i_m = 5;
	public static final int i__G_0 = 6;


	public static final Class<?> childTypes[] = {silver.modification.copper.TParser_kwd.class,silver.definition.core.NName.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TLCurly_t.class,silver.modification.copper.NParserComponents.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_parserDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_m] = new common.Lazy[silver.modification.copper.NParserComponents.num_inh_attrs];

	}

	public PparserDcl(final Object c__G_6, final Object c_n, final Object c__G_4, final Object c_t, final Object c__G_2, final Object c_m, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_6 = c__G_6;
		this.child_n = c_n;
		this.child__G_4 = c__G_4;
		this.child_t = c_t;
		this.child__G_2 = c__G_2;
		this.child_m = c_m;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_6;
	public final silver.modification.copper.TParser_kwd getChild__G_6() {
		return (silver.modification.copper.TParser_kwd) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_n;
	public final silver.definition.core.NName getChild_n() {
		return (silver.definition.core.NName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_4;
	public final silver.definition.core.TColonColon_t getChild__G_4() {
		return (silver.definition.core.TColonColon_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_m;
	public final silver.modification.copper.NParserComponents getChild_m() {
		return (silver.modification.copper.NParserComponents) (child_m = common.Util.demand(child_m));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_6: return getChild__G_6();
			case i_n: return getChild_n();
			case i__G_4: return getChild__G_4();
			case i_t: return getChild_t();
			case i__G_2: return getChild__G_2();
			case i_m: return getChild_m();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_6: return child__G_6;
			case i_n: return child_n;
			case i__G_4: return child__G_4;
			case i_t: return child_t;
			case i__G_2: return child__G_2;
			case i_m: return child_m;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:parserDcl erroneously claimed to forward");
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
		return "silver:modification:copper:parserDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "parser " ++ m.pp ++ ";"
		silver.modification.copper.PparserDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("parser ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PparserDcl.i_m).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponents)), (common.StringCatter)(new common.StringCatter(";")))); } };
		// top.moduleNames = m.moduleNames
		silver.modification.copper.PparserDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_moduleNames__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PparserDcl.i_m).synthesized(silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents)); } };
		// top.errors := t.errors ++ m.errors ++ liftedAGDcls.errors
		if(silver.modification.copper.PparserDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper.PparserDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper.PparserDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserDcl.i_t, silver.definition.type.syntax.Init.silver_definition_core_errors__ON__silver_definition_type_syntax_TypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserDcl.i_m, silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponents), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl)); } })); } })); } });
		// top.defs = [ funDef(top.grammarName, n.location, namedSig) ]
		silver.modification.copper.PparserDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PfunDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.modification.copper.PparserDcl.i_n).undecorate()).getAnno_core_location()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.copper.Init.namedSig__ON__silver_modification_copper_parserDcl)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// liftedAGDcls = m.liftedAGDcls
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)context.childDecorated(silver.modification.copper.PparserDcl.i_m).synthesized(silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponents)); } };
		// liftedAGDcls.config = top.config
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl][silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.core.Init.silver_definition_env_config__ON__silver_definition_core_AGDcl)); } };
		// liftedAGDcls.grammarName = top.grammarName
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl][silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)); } };
		// liftedAGDcls.env = m.env
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl][silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.modification.copper.PparserDcl.i_m).inherited(silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ParserComponents)); } };
		// liftedAGDcls.compiledGrammars = top.compiledGrammars
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl][silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl)); } };
		// liftedAGDcls.grammarDependencies = top.grammarDependencies
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl][silver.definition.core.Init.silver_definition_core_grammarDependencies__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.core.Init.silver_definition_core_grammarDependencies__ON__silver_definition_core_AGDcl)); } };
		// liftedAGDcls.flowEnv = top.flowEnv
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl][silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl)); } };
		// m.grammarDependencies = computeDependencies(m.moduleNames, top.compiledGrammars)
		silver.modification.copper.PparserDcl.childInheritedAttributes[silver.modification.copper.PparserDcl.i_m][silver.modification.copper.Init.silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponents] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PcomputeDependencies.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserDcl.i_m, silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl))); } };
		// med = moduleExportedDefs(top.location, top.compiledGrammars, m.grammarDependencies, m.moduleNames, [])
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.med__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModuleExportedDefs)new silver.definition.core.PmoduleExportedDefs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PparserDcl.i_m).inherited(silver.modification.copper.Init.silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponents)); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserDcl.i_m, silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// m.env = appendEnv(toEnv(liftedAGDcls.defs ++ med.defs), top.env)
		silver.modification.copper.PparserDcl.childInheritedAttributes[silver.modification.copper.PparserDcl.i_m][silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ParserComponents] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PappendEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PtoEnv.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.med__ON__silver_modification_copper_parserDcl).synthesized(silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ModuleExportedDefs)); } })); } })); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AGDcl))); } };
		// fName = top.grammarName ++ ":" ++ n.name
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.fName__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PparserDcl.i_n).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// namedSig = namedSignature(fName, [ namedSignatureElement("stringToParse", stringType()), namedSignatureElement("filenameToReport", stringType()) ], namedSignatureElement("__func__lhs", nonterminalType("core:ParseResult", [ t.typerep ])), [])
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.namedSig__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)new silver.definition.env.PnamedSignature(context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_parserDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)new silver.definition.env.PnamedSignatureElement((new common.StringCatter("stringToParse")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PstringType()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)new silver.definition.env.PnamedSignatureElement((new common.StringCatter("filenameToReport")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PstringType()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)new silver.definition.env.PnamedSignatureElement((new common.StringCatter("__func__lhs")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PnonterminalType((new common.StringCatter("core:ParseResult")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserDcl.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// spec = parserSpec(top.location, top.grammarName, fName, t.typerep.typeName, m.moduleNames, m.terminalPrefixes, liftedAGDcls.syntaxAst)
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.spec__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.NParserSpec)new silver.definition.concrete_syntax.PparserSpec(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_parserDcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.childDecorated(silver.modification.copper.PparserDcl.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserDcl.i_m, silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents), context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserDcl.i_m, silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponents), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl)); } })); } };
		// spec.compiledGrammars = top.compiledGrammars
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.spec__ON__silver_modification_copper_parserDcl][silver.definition.concrete_syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_ParserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl)); } };
		// top.parserSpecs = [ spec ]
		silver.modification.copper.PparserDcl.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.copper.Init.spec__ON__silver_modification_copper_parserDcl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PparserDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PparserDcl> {

		@Override
		public PparserDcl invoke(final Object[] children, final Object[] annotations) {
			return new PparserDcl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
