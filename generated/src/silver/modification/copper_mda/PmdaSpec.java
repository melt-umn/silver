
package silver.modification.copper_mda;

// top::MdaSpec ::= sg::String fn::String snt::String hostgrams::[String] extgrams::[String] terminalPrefixes::[Pair<String String>] 
public final class PmdaSpec extends silver.modification.copper_mda.NMdaSpec {

	public static final int i_sg = 0;
	public static final int i_fn = 1;
	public static final int i_snt = 2;
	public static final int i_hostgrams = 3;
	public static final int i_extgrams = 4;
	public static final int i_terminalPrefixes = 5;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_mda_mdaSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper_mda.NMdaSpec.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper_mda.NMdaSpec.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PmdaSpec(final Object c_sg, final Object c_fn, final Object c_snt, final Object c_hostgrams, final Object c_extgrams, final Object c_terminalPrefixes) {
		super();
		this.child_sg = c_sg;
		this.child_fn = c_fn;
		this.child_snt = c_snt;
		this.child_hostgrams = c_hostgrams;
		this.child_extgrams = c_extgrams;
		this.child_terminalPrefixes = c_terminalPrefixes;

	}

	private Object child_sg;
	public final common.StringCatter getChild_sg() {
		return (common.StringCatter) (child_sg = common.Util.demand(child_sg));
	}

	private Object child_fn;
	public final common.StringCatter getChild_fn() {
		return (common.StringCatter) (child_fn = common.Util.demand(child_fn));
	}

	private Object child_snt;
	public final common.StringCatter getChild_snt() {
		return (common.StringCatter) (child_snt = common.Util.demand(child_snt));
	}

	private Object child_hostgrams;
	public final common.ConsCell getChild_hostgrams() {
		return (common.ConsCell) (child_hostgrams = common.Util.demand(child_hostgrams));
	}

	private Object child_extgrams;
	public final common.ConsCell getChild_extgrams() {
		return (common.ConsCell) (child_extgrams = common.Util.demand(child_extgrams));
	}

	private Object child_terminalPrefixes;
	public final common.ConsCell getChild_terminalPrefixes() {
		return (common.ConsCell) (child_terminalPrefixes = common.Util.demand(child_terminalPrefixes));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sg: return getChild_sg();
			case i_fn: return getChild_fn();
			case i_snt: return getChild_snt();
			case i_hostgrams: return getChild_hostgrams();
			case i_extgrams: return getChild_extgrams();
			case i_terminalPrefixes: return getChild_terminalPrefixes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sg: return child_sg;
			case i_fn: return child_fn;
			case i_snt: return child_snt;
			case i_hostgrams: return child_hostgrams;
			case i_extgrams: return child_extgrams;
			case i_terminalPrefixes: return child_terminalPrefixes;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper_mda:mdaSpec erroneously claimed to forward");
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
		return "silver:modification:copper_mda:mdaSpec";
	}

	static void initProductionAttributeDefinitions() {
		// top.sourceGrammar = sg
		silver.modification.copper_mda.PmdaSpec.synthesizedAttributes[silver.modification.copper_mda.Init.silver_definition_env_sourceGrammar__ON__silver_modification_copper_mda_MdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.modification.copper_mda.PmdaSpec.i_sg)); } };
		// top.fullName = fn
		silver.modification.copper_mda.PmdaSpec.synthesizedAttributes[silver.modification.copper_mda.Init.silver_definition_env_fullName__ON__silver_modification_copper_mda_MdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.modification.copper_mda.PmdaSpec.i_fn)); } };
		// hostmed = moduleExportedDefs(error("no sl"), top.compiledGrammars, computeDependencies(hostgrams ++ extgrams, top.compiledGrammars), hostgrams, [])
		silver.modification.copper_mda.PmdaSpec.localAttributes[silver.modification.copper_mda.Init.hostmed__ON__silver_modification_copper_mda_mdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModuleExportedDefs)new silver.definition.core.PmoduleExportedDefs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)core.Perror.invoke((new common.StringCatter("no sl")))); } }, context.contextInheritedLazy(silver.modification.copper_mda.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_mda_MdaSpec), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PcomputeDependencies.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.modification.copper_mda.PmdaSpec.i_hostgrams), context.childAsIsLazy(silver.modification.copper_mda.PmdaSpec.i_extgrams))); } }, context.contextInheritedLazy(silver.modification.copper_mda.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_mda_MdaSpec))); } }, context.childAsIsLazy(silver.modification.copper_mda.PmdaSpec.i_hostgrams), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// extmed = moduleExportedDefs(error("no sl"), top.compiledGrammars, computeDependencies(hostgrams ++ extgrams, top.compiledGrammars), extgrams, [])
		silver.modification.copper_mda.PmdaSpec.localAttributes[silver.modification.copper_mda.Init.extmed__ON__silver_modification_copper_mda_mdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModuleExportedDefs)new silver.definition.core.PmoduleExportedDefs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)core.Perror.invoke((new common.StringCatter("no sl")))); } }, context.contextInheritedLazy(silver.modification.copper_mda.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_mda_MdaSpec), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PcomputeDependencies.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.modification.copper_mda.PmdaSpec.i_hostgrams), context.childAsIsLazy(silver.modification.copper_mda.PmdaSpec.i_extgrams))); } }, context.contextInheritedLazy(silver.modification.copper_mda.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_mda_MdaSpec))); } }, context.childAsIsLazy(silver.modification.copper_mda.PmdaSpec.i_extgrams), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.cstAst = cstCopperMdaRoot(fn, snt, foldr(consSyntax, nilSyntax(), hostmed.syntaxAst), foldr(consSyntax, nilSyntax(), extmed.syntaxAst), terminalPrefixes)
		silver.modification.copper_mda.PmdaSpec.synthesizedAttributes[silver.modification.copper_mda.Init.silver_definition_concrete_syntax_cstAst__ON__silver_modification_copper_mda_MdaSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxRoot)new silver.modification.copper_mda.PcstCopperMdaRoot(context.childAsIsLazy(silver.modification.copper_mda.PmdaSpec.i_fn), context.childAsIsLazy(silver.modification.copper_mda.PmdaSpec.i_snt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsSyntax.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)new silver.definition.concrete_syntax.ast.PnilSyntax()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper_mda.Init.hostmed__ON__silver_modification_copper_mda_mdaSpec).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_ModuleExportedDefs)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsSyntax.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)new silver.definition.concrete_syntax.ast.PnilSyntax()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper_mda.Init.extmed__ON__silver_modification_copper_mda_mdaSpec).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_ModuleExportedDefs)); } })); } }, context.childAsIsLazy(silver.modification.copper_mda.PmdaSpec.i_terminalPrefixes))); } };

	}

	public static final common.NodeFactory<PmdaSpec> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmdaSpec> {

		@Override
		public PmdaSpec invoke(final Object[] children, final Object[] annotations) {
			return new PmdaSpec(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
