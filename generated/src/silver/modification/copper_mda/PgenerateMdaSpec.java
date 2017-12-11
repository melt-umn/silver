
package silver.modification.copper_mda;

// top::DriverAction ::= grams::EnvTree<Decorated RootSpec> spec::MdaSpec silvergen::String 
public final class PgenerateMdaSpec extends silver.driver.util.NDriverAction {

	public static final int i_grams = 0;
	public static final int i_spec = 1;
	public static final int i_silvergen = 2;


	public static final Class<?> childTypes[] = {Object.class,silver.modification.copper_mda.NMdaSpec.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_mda_generateMdaSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_spec] = new common.Lazy[silver.modification.copper_mda.NMdaSpec.num_inh_attrs];

	}

	public PgenerateMdaSpec(final Object c_grams, final Object c_spec, final Object c_silvergen) {
		super();
		this.child_grams = c_grams;
		this.child_spec = c_spec;
		this.child_silvergen = c_silvergen;

	}

	private Object child_grams;
	public final Object getChild_grams() {
		return (Object) (child_grams = common.Util.demand(child_grams));
	}

	private Object child_spec;
	public final silver.modification.copper_mda.NMdaSpec getChild_spec() {
		return (silver.modification.copper_mda.NMdaSpec) (child_spec = common.Util.demand(child_spec));
	}

	private Object child_silvergen;
	public final common.StringCatter getChild_silvergen() {
		return (common.StringCatter) (child_silvergen = common.Util.demand(child_silvergen));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_grams: return getChild_grams();
			case i_spec: return getChild_spec();
			case i_silvergen: return getChild_silvergen();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_grams: return child_grams;
			case i_spec: return child_spec;
			case i_silvergen: return child_silvergen;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper_mda:generateMdaSpec erroneously claimed to forward");
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
		return "silver:modification:copper_mda:generateMdaSpec";
	}

	static void initProductionAttributeDefinitions() {
		// spec.compiledGrammars = grams
		silver.modification.copper_mda.PgenerateMdaSpec.childInheritedAttributes[silver.modification.copper_mda.PgenerateMdaSpec.i_spec][silver.modification.copper_mda.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_mda_MdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(silver.modification.copper_mda.PgenerateMdaSpec.i_grams)); } };
		// ast = spec.cstAst
		silver.modification.copper_mda.PgenerateMdaSpec.localAttributes[silver.modification.copper_mda.Init.ast__ON__silver_modification_copper_mda_generateMdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxRoot)context.childDecorated(silver.modification.copper_mda.PgenerateMdaSpec.i_spec).synthesized(silver.modification.copper_mda.Init.silver_definition_concrete_syntax_cstAst__ON__silver_modification_copper_mda_MdaSpec)); } };
		// parserName = makeParserName(spec.fullName)
		silver.modification.copper_mda.PgenerateMdaSpec.localAttributes[silver.modification.copper_mda.Init.parserName__ON__silver_modification_copper_mda_generateMdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeParserName.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PgenerateMdaSpec.i_spec, silver.modification.copper_mda.Init.silver_definition_env_fullName__ON__silver_modification_copper_mda_MdaSpec))); } };
		// copperFile = silvergen ++ grammarToPath(spec.sourceGrammar) ++ parserName ++ ".copper"
		silver.modification.copper_mda.PgenerateMdaSpec.localAttributes[silver.modification.copper_mda.Init.copperFile__ON__silver_modification_copper_mda_generateMdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.copper_mda.PgenerateMdaSpec.i_silvergen)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.driver.util.PgrammarToPath.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper_mda.PgenerateMdaSpec.i_spec, silver.modification.copper_mda.Init.silver_definition_env_sourceGrammar__ON__silver_modification_copper_mda_MdaSpec))), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper_mda.Init.parserName__ON__silver_modification_copper_mda_generateMdaSpec)), (common.StringCatter)(new common.StringCatter(".copper"))))); } };
		// err = print("CST Errors while Testing MDA " ++ spec.fullName ++ ":\n" ++ foldr(\ a::String b::String  -> a ++ "\n" ++ b, "", ast.cstErrors) ++ "\n", top.ioIn)
		silver.modification.copper_mda.PgenerateMdaSpec.localAttributes[silver.modification.copper_mda.Init.err__ON__silver_modification_copper_mda_generateMdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("CST Errors while Testing MDA ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PgenerateMdaSpec.i_spec).synthesized(silver.modification.copper_mda.Init.silver_definition_env_fullName__ON__silver_modification_copper_mda_MdaSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pfoldr.invoke((new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_8269_a = args[0];
final Object __SV_LAMBDA_PARAM_8270_b = args[1];

    return new common.StringCatter((common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_8269_a)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_8270_b))));
  }
}), (new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper_mda.Init.ast__ON__silver_modification_copper_mda_generateMdaSpec).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } })), (common.StringCatter)(new common.StringCatter("\n")))))); } }, context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } };
		// printio = print("MDA test file: " ++ spec.fullName ++ "\n", top.ioIn)
		silver.modification.copper_mda.PgenerateMdaSpec.localAttributes[silver.modification.copper_mda.Init.printio__ON__silver_modification_copper_mda_generateMdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("MDA test file: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper_mda.PgenerateMdaSpec.i_spec).synthesized(silver.modification.copper_mda.Init.silver_definition_env_fullName__ON__silver_modification_copper_mda_MdaSpec)), (common.StringCatter)(new common.StringCatter("\n")))); } }, context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } };
		// writeio = writeFile(copperFile, ast.xmlCopper, printio)
		silver.modification.copper_mda.PgenerateMdaSpec.localAttributes[silver.modification.copper_mda.Init.writeio__ON__silver_modification_copper_mda_generateMdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PwriteFile.invoke(context.localAsIsLazy(silver.modification.copper_mda.Init.copperFile__ON__silver_modification_copper_mda_generateMdaSpec), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.modification.copper_mda.Init.ast__ON__silver_modification_copper_mda_generateMdaSpec).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } }, context.localAsIsLazy(silver.modification.copper_mda.Init.printio__ON__silver_modification_copper_mda_generateMdaSpec))); } };
		// top.io = if null(ast.cstErrors) then writeio else err
		silver.modification.copper_mda.PgenerateMdaSpec.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper_mda.Init.ast__ON__silver_modification_copper_mda_generateMdaSpec).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } })) ? ((Object)context.localAsIs(silver.modification.copper_mda.Init.writeio__ON__silver_modification_copper_mda_generateMdaSpec)) : ((Object)context.localAsIs(silver.modification.copper_mda.Init.err__ON__silver_modification_copper_mda_generateMdaSpec))); } };
		// top.code = if null(ast.cstErrors) then 0 else 1
		silver.modification.copper_mda.PgenerateMdaSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper_mda.Init.ast__ON__silver_modification_copper_mda_generateMdaSpec).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } })) ? Integer.valueOf((int)0) : Integer.valueOf((int)1)); } };
		// top.order = 5
		silver.modification.copper_mda.PgenerateMdaSpec.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)5); } };

	}

	public static final common.NodeFactory<PgenerateMdaSpec> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgenerateMdaSpec> {

		@Override
		public PgenerateMdaSpec invoke(final Object[] children, final Object[] annotations) {
			return new PgenerateMdaSpec(children[0], children[1], children[2]);
		}
	};

}
