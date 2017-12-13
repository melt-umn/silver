
package silver.modification.impide;

// top::AGDcl ::= 'temp_imp_ide_dcl' parsername::QName fileextension::String_t stmts::IdeStmts 
public final class PideDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_3 = 0;
	public static final int i_parsername = 1;
	public static final int i_fileextension = 2;
	public static final int i_stmts = 3;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpIde_t.class,silver.definition.core.NQName.class,silver.definition.core.TString_t.class,silver.modification.impide.NIdeStmts.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_ideDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_parsername] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_stmts] = new common.Lazy[silver.modification.impide.NIdeStmts.num_inh_attrs];

	}

	public PideDcl(final Object c__G_3, final Object c_parsername, final Object c_fileextension, final Object c_stmts, final Object a_core_location) {
		super(a_core_location);
		this.child__G_3 = c__G_3;
		this.child_parsername = c_parsername;
		this.child_fileextension = c_fileextension;
		this.child_stmts = c_stmts;

	}

	private Object child__G_3;
	public final silver.modification.impide.TImpIde_t getChild__G_3() {
		return (silver.modification.impide.TImpIde_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_parsername;
	public final silver.definition.core.NQName getChild_parsername() {
		return (silver.definition.core.NQName) (child_parsername = common.Util.demand(child_parsername));
	}

	private Object child_fileextension;
	public final silver.definition.core.TString_t getChild_fileextension() {
		return (silver.definition.core.TString_t) (child_fileextension = common.Util.demand(child_fileextension));
	}

	private Object child_stmts;
	public final silver.modification.impide.NIdeStmts getChild_stmts() {
		return (silver.modification.impide.NIdeStmts) (child_stmts = common.Util.demand(child_stmts));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i_parsername: return getChild_parsername();
			case i_fileextension: return getChild_fileextension();
			case i_stmts: return getChild_stmts();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i_parsername: return child_parsername;
			case i_fileextension: return child_fileextension;
			case i_stmts: return child_stmts;

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
		return "silver:modification:impide:ideDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "temp_imp_ide_dcl " ++ parsername.pp ++ " " ++ fileextension.lexeme ++ "\n"
		silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("temp_imp_ide_dcl ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PideDcl.i_parsername).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PideDcl.i_fileextension)).lexeme), (common.StringCatter)(new common.StringCatter("\n")))))); } };
		// top.defs = []
		silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.errors := parsername.lookupValue.errors
		if(silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PideDcl.i_parsername).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } });
		// top.errors <- if startsWith("\".", fileextension.lexeme) then [] else [ err(fileextension.location, "File extension should begin with dot (like \".sv\")") ]
		if(silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.PstartsWith.invoke((new common.StringCatter("\".")), ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PideDcl.i_fileextension)).lexeme))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(((core.NLocation)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PideDcl.i_fileextension)).location), (new common.StringCatter("File extension should begin with dot (like \".sv\")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// parsergrammar = head(searchEnvTree(parsername.lookupValue.dcl.sourceGrammar, top.compiledGrammars))
		silver.modification.impide.PideDcl.localAttributes[silver.modification.impide.Init.parsergrammar__ON__silver_modification_impide_ideDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PideDcl.i_parsername).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl))); } })); } };
		// spec = findSpec(parsername.lookupValue.fullName, parsergrammar.parserSpecs)
		silver.modification.impide.PideDcl.localAttributes[silver.modification.impide.Init.spec__ON__silver_modification_impide_ideDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.modification.copper_mda.PfindSpec.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PideDcl.i_parsername).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.localAsIs(silver.modification.impide.Init.parsergrammar__ON__silver_modification_impide_ideDcl)).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec)); } })); } };
		// stmts.startNTName = head(spec).startNT
		silver.modification.impide.PideDcl.childInheritedAttributes[silver.modification.impide.PideDcl.i_stmts][silver.modification.impide.Init.silver_modification_impide_startNTName__ON__silver_modification_impide_IdeStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.concrete_syntax.NParserSpec)core.Phead.invoke(context.localAsIsLazy(silver.modification.impide.Init.spec__ON__silver_modification_impide_ideDcl))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_startNT__ON__silver_definition_concrete_syntax_ParserSpec)); } };
		// top.errors <- if ! null(parsername.lookupValue.errors) || ! null(spec) then [] else [ err(parsername.location, parsername.name ++ " is not a parser.") ]
		if(silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.impide.PideDcl.i_parsername).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } }))) || (!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.modification.impide.Init.spec__ON__silver_modification_impide_ideDcl))))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.modification.impide.PideDcl.i_parsername).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.PideDcl.i_parsername).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(" is not a parser."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// fext = substring(2, length(fileextension.lexeme) - 1, fileextension.lexeme)
		silver.modification.impide.PideDcl.localAttributes[silver.modification.impide.Init.fext__ON__silver_modification_impide_ideDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PideDcl.i_fileextension)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.impide.PideDcl.i_fileextension)).lexeme))); } };
		// ideName = if null(stmts.ideNames) then deriveLangNameFromGrammar(top.grammarName) else head(stmts.ideNames)
		silver.modification.impide.PideDcl.localAttributes[silver.modification.impide.Init.ideName__ON__silver_modification_impide_ideDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmts))) ? ((common.StringCatter)silver.modification.impide.PderiveLangNameFromGrammar.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl))) : ((common.StringCatter)core.Phead.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmts)))); } };
		// top.errors <- if length(stmts.ideNames) > 1 then [ err(top.location, "Multiple name declarations") ] else []
		if(silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_ideNames__ON__silver_modification_impide_IdeStmts))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Multiple name declarations")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// ideVersion = if null(stmts.ideVersions) then "1.0.0" else head(stmts.ideVersions)
		silver.modification.impide.PideDcl.localAttributes[silver.modification.impide.Init.ideVersion__ON__silver_modification_impide_ideDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmts))) ? (new common.StringCatter("1.0.0")) : ((common.StringCatter)core.Phead.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmts)))); } };
		// top.errors <- if length(stmts.ideVersions) > 1 then [ err(top.location, "Multiple version declarations") ] else []
		if(silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_ideVersions__ON__silver_modification_impide_IdeStmts))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Multiple version declarations")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.ideSpecs = [ ideSpec(top.grammarName, ideName, ideVersion, fext, stmts.ideFunctions, stmts.propDcls, stmts.wizards, head(spec), stmts.ideResources) ]
		silver.modification.impide.PideDcl.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_ideSpecs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.impide.spec.NIdeSpec)new silver.modification.impide.spec.PideSpec(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), context.localAsIsLazy(silver.modification.impide.Init.ideName__ON__silver_modification_impide_ideDcl), context.localAsIsLazy(silver.modification.impide.Init.ideVersion__ON__silver_modification_impide_ideDcl), context.localAsIsLazy(silver.modification.impide.Init.fext__ON__silver_modification_impide_ideDcl), context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_ideFunctions__ON__silver_modification_impide_IdeStmts), context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmts), context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_wizards__ON__silver_modification_impide_IdeStmts), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.NParserSpec)core.Phead.invoke(context.localAsIsLazy(silver.modification.impide.Init.spec__ON__silver_modification_impide_ideDcl))); } }, context.childDecoratedSynthesizedLazy(silver.modification.impide.PideDcl.i_stmts, silver.modification.impide.Init.silver_modification_impide_spec_ideResources__ON__silver_modification_impide_IdeStmts))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors <- stmts.errors
		if(silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.impide.PideDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.impide.PideDcl.i_stmts).synthesized(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmts)); } });

	}

	public static final common.NodeFactory<PideDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PideDcl> {

		@Override
		public PideDcl invoke(final Object[] children, final Object[] annotations) {
			return new PideDcl(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
