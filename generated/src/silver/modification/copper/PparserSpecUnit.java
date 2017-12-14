
package silver.modification.copper;

// top::DriverAction ::= spec::ParserSpec cg::EnvTree<Decorated RootSpec> silverGen::String 
public final class PparserSpecUnit extends silver.driver.util.NDriverAction {

	public static final int i_spec = 0;
	public static final int i_cg = 1;
	public static final int i_silverGen = 2;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.NParserSpec.class,Object.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_parserSpecUnit;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_spec] = new common.Lazy[silver.definition.concrete_syntax.NParserSpec.num_inh_attrs];

	}

	public PparserSpecUnit(final Object c_spec, final Object c_cg, final Object c_silverGen) {
		super();
		this.child_spec = c_spec;
		this.child_cg = c_cg;
		this.child_silverGen = c_silverGen;

	}

	private Object child_spec;
	public final silver.definition.concrete_syntax.NParserSpec getChild_spec() {
		return (silver.definition.concrete_syntax.NParserSpec) (child_spec = common.Util.demand(child_spec));
	}

	private Object child_cg;
	public final Object getChild_cg() {
		return (Object) (child_cg = common.Util.demand(child_cg));
	}

	private Object child_silverGen;
	public final common.StringCatter getChild_silverGen() {
		return (common.StringCatter) (child_silverGen = common.Util.demand(child_silverGen));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_spec: return getChild_spec();
			case i_cg: return getChild_cg();
			case i_silverGen: return getChild_silverGen();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_spec: return child_spec;
			case i_cg: return child_cg;
			case i_silverGen: return child_silverGen;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:parserSpecUnit erroneously claimed to forward");
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
		return "silver:modification:copper:parserSpecUnit";
	}

	static void initProductionAttributeDefinitions() {
		// file = silverGen ++ "src/" ++ grammarToPath(spec.sourceGrammar) ++ makeParserName(spec.fullName) ++ ".copper"
		silver.modification.copper.PparserSpecUnit.localAttributes[silver.modification.copper.Init.file__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.copper.PparserSpecUnit.i_silverGen)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("src/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.driver.util.PgrammarToPath.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserSpecUnit.i_spec, silver.definition.concrete_syntax.Init.silver_definition_env_sourceGrammar__ON__silver_definition_concrete_syntax_ParserSpec))), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeParserName.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserSpecUnit.i_spec, silver.definition.concrete_syntax.Init.silver_definition_env_fullName__ON__silver_definition_concrete_syntax_ParserSpec))), (common.StringCatter)(new common.StringCatter(".copper")))))); } };
		// spec.compiledGrammars = cg
		silver.modification.copper.PparserSpecUnit.childInheritedAttributes[silver.modification.copper.PparserSpecUnit.i_spec][silver.definition.concrete_syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_ParserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(silver.modification.copper.PparserSpecUnit.i_cg)); } };
		// newSpec = spec.cstAst.xmlCopper
		silver.modification.copper.PparserSpecUnit.localAttributes[silver.modification.copper.Init.newSpec__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.concrete_syntax.ast.NSyntaxRoot)context.childDecorated(silver.modification.copper.PparserSpecUnit.i_spec).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_cstAst__ON__silver_definition_concrete_syntax_ParserSpec)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } };
		// specCst = spec.cstAst
		silver.modification.copper.PparserSpecUnit.localAttributes[silver.modification.copper.Init.specCst__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxRoot)context.childDecorated(silver.modification.copper.PparserSpecUnit.i_spec).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_cstAst__ON__silver_definition_concrete_syntax_ParserSpec)); } };
		// ex = isFile(file, top.ioIn)
		silver.modification.copper.PparserSpecUnit.localAttributes[silver.modification.copper.Init.ex__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PisFile.invoke(context.localAsIsLazy(silver.modification.copper.Init.file__ON__silver_modification_copper_parserSpecUnit), context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } };
		// oldSpec = readFile(file, ex.io)
		silver.modification.copper.PparserSpecUnit.localAttributes[silver.modification.copper.Init.oldSpec__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PreadFile.invoke(context.localAsIsLazy(silver.modification.copper.Init.file__ON__silver_modification_copper_parserSpecUnit), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.modification.copper.Init.ex__ON__silver_modification_copper_parserSpecUnit).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } };
		// join = if ex.iovalue then oldSpec.io else ex.io
		silver.modification.copper.PparserSpecUnit.localAttributes[silver.modification.copper.Init.join__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.modification.copper.Init.ex__ON__silver_modification_copper_parserSpecUnit).synthesized(core.Init.core_iovalue__ON__core_IOVal)) ? ((Object)context.localDecorated(silver.modification.copper.Init.oldSpec__ON__silver_modification_copper_parserSpecUnit).synthesized(core.Init.core_io__ON__core_IOVal)) : ((Object)context.localDecorated(silver.modification.copper.Init.ex__ON__silver_modification_copper_parserSpecUnit).synthesized(core.Init.core_io__ON__core_IOVal))); } };
		// err = print("CST Errors while Generating Parser " ++ spec.fullName ++ ":\n" ++ implode("\n", specCst.cstErrors) ++ "\n", join)
		silver.modification.copper.PparserSpecUnit.localAttributes[silver.modification.copper.Init.err__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("CST Errors while Generating Parser ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PparserSpecUnit.i_spec).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_fullName__ON__silver_definition_concrete_syntax_ParserSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.specCst__ON__silver_modification_copper_parserSpecUnit).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } })), (common.StringCatter)(new common.StringCatter("\n")))))); } }, context.localAsIsLazy(silver.modification.copper.Init.join__ON__silver_modification_copper_parserSpecUnit))); } };
		// doUTD = print("Parser " ++ spec.fullName ++ " up to date.\n", join)
		silver.modification.copper.PparserSpecUnit.localAttributes[silver.modification.copper.Init.doUTD__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Parser ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PparserSpecUnit.i_spec).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_fullName__ON__silver_definition_concrete_syntax_ParserSpec)), (common.StringCatter)(new common.StringCatter(" up to date.\n")))); } }, context.localAsIsLazy(silver.modification.copper.Init.join__ON__silver_modification_copper_parserSpecUnit))); } };
		// doWR = writeFile(file, newSpec, print("Generating Parser " ++ spec.fullName ++ ".\n", join))
		silver.modification.copper.PparserSpecUnit.localAttributes[silver.modification.copper.Init.doWR__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PwriteFile.invoke(context.localAsIsLazy(silver.modification.copper.Init.file__ON__silver_modification_copper_parserSpecUnit), context.localAsIsLazy(silver.modification.copper.Init.newSpec__ON__silver_modification_copper_parserSpecUnit), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Generating Parser ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PparserSpecUnit.i_spec).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_fullName__ON__silver_definition_concrete_syntax_ParserSpec)), (common.StringCatter)(new common.StringCatter(".\n")))); } }, context.localAsIsLazy(silver.modification.copper.Init.join__ON__silver_modification_copper_parserSpecUnit))); } })); } };
		// top.io = if null(specCst.cstErrors) then if ex.iovalue && oldSpec.iovalue == newSpec then doUTD else doWR else err
		silver.modification.copper.PparserSpecUnit.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.specCst__ON__silver_modification_copper_parserSpecUnit).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } })) ? ((((Boolean)context.localDecorated(silver.modification.copper.Init.ex__ON__silver_modification_copper_parserSpecUnit).synthesized(core.Init.core_iovalue__ON__core_IOVal)) && ((common.StringCatter)context.localDecorated(silver.modification.copper.Init.oldSpec__ON__silver_modification_copper_parserSpecUnit).synthesized(core.Init.core_iovalue__ON__core_IOVal)).equals(((common.StringCatter)context.localAsIs(silver.modification.copper.Init.newSpec__ON__silver_modification_copper_parserSpecUnit)))) ? ((Object)context.localAsIs(silver.modification.copper.Init.doUTD__ON__silver_modification_copper_parserSpecUnit)) : ((Object)context.localAsIs(silver.modification.copper.Init.doWR__ON__silver_modification_copper_parserSpecUnit))) : ((Object)context.localAsIs(silver.modification.copper.Init.err__ON__silver_modification_copper_parserSpecUnit))); } };
		// top.code = if null(specCst.cstErrors) then 0 else 1
		silver.modification.copper.PparserSpecUnit.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.specCst__ON__silver_modification_copper_parserSpecUnit).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } })) ? Integer.valueOf((int)0) : Integer.valueOf((int)1)); } };
		// top.order = 7
		silver.modification.copper.PparserSpecUnit.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)7); } };

	}

	public static final common.NodeFactory<PparserSpecUnit> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PparserSpecUnit> {

		@Override
		public PparserSpecUnit invoke(final Object[] children, final Object[] annotations) {
			return new PparserSpecUnit(children[0], children[1], children[2]);
		}
	};

}
