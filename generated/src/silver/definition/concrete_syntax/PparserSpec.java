
package silver.definition.concrete_syntax;

// top::ParserSpec ::= sl::Location sg::String fn::String snt::String grams::[String] terminalPrefixes::[Pair<String String>] addedDcls::[SyntaxDcl] 
public final class PparserSpec extends silver.definition.concrete_syntax.NParserSpec {

	public static final int i_sl = 0;
	public static final int i_sg = 1;
	public static final int i_fn = 2;
	public static final int i_snt = 3;
	public static final int i_grams = 4;
	public static final int i_terminalPrefixes = 5;
	public static final int i_addedDcls = 6;


	public static final Class<?> childTypes[] = {core.NLocation.class,common.StringCatter.class,common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_parserSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NParserSpec.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NParserSpec.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PparserSpec(final Object c_sl, final Object c_sg, final Object c_fn, final Object c_snt, final Object c_grams, final Object c_terminalPrefixes, final Object c_addedDcls) {
		super();
		this.child_sl = c_sl;
		this.child_sg = c_sg;
		this.child_fn = c_fn;
		this.child_snt = c_snt;
		this.child_grams = c_grams;
		this.child_terminalPrefixes = c_terminalPrefixes;
		this.child_addedDcls = c_addedDcls;

	}

	private Object child_sl;
	public final core.NLocation getChild_sl() {
		return (core.NLocation) (child_sl = common.Util.demand(child_sl));
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

	private Object child_grams;
	public final common.ConsCell getChild_grams() {
		return (common.ConsCell) (child_grams = common.Util.demand(child_grams));
	}

	private Object child_terminalPrefixes;
	public final common.ConsCell getChild_terminalPrefixes() {
		return (common.ConsCell) (child_terminalPrefixes = common.Util.demand(child_terminalPrefixes));
	}

	private Object child_addedDcls;
	public final common.ConsCell getChild_addedDcls() {
		return (common.ConsCell) (child_addedDcls = common.Util.demand(child_addedDcls));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sl: return getChild_sl();
			case i_sg: return getChild_sg();
			case i_fn: return getChild_fn();
			case i_snt: return getChild_snt();
			case i_grams: return getChild_grams();
			case i_terminalPrefixes: return getChild_terminalPrefixes();
			case i_addedDcls: return getChild_addedDcls();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sl: return child_sl;
			case i_sg: return child_sg;
			case i_fn: return child_fn;
			case i_snt: return child_snt;
			case i_grams: return child_grams;
			case i_terminalPrefixes: return child_terminalPrefixes;
			case i_addedDcls: return child_addedDcls;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:parserSpec erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:parserSpec";
	}

	static void initProductionAttributeDefinitions() {
		// top.sourceLocation = sl
		silver.definition.concrete_syntax.PparserSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_sourceLocation__ON__silver_definition_concrete_syntax_ParserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.concrete_syntax.PparserSpec.i_sl).undecorate(); } };
		// top.sourceGrammar = sg
		silver.definition.concrete_syntax.PparserSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_sourceGrammar__ON__silver_definition_concrete_syntax_ParserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.PparserSpec.i_sg)); } };
		// top.fullName = fn
		silver.definition.concrete_syntax.PparserSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_fullName__ON__silver_definition_concrete_syntax_ParserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.PparserSpec.i_fn)); } };
		// top.startNT = snt
		silver.definition.concrete_syntax.PparserSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_startNT__ON__silver_definition_concrete_syntax_ParserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.PparserSpec.i_snt)); } };
		// top.moduleNames = grams
		silver.definition.concrete_syntax.PparserSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_moduleNames__ON__silver_definition_concrete_syntax_ParserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.concrete_syntax.PparserSpec.i_grams)); } };
		// med = moduleExportedDefs(sl, top.compiledGrammars, computeDependencies(grams, top.compiledGrammars), grams, [])
		silver.definition.concrete_syntax.PparserSpec.localAttributes[silver.definition.concrete_syntax.Init.med__ON__silver_definition_concrete_syntax_parserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NModuleExportedDefs)new silver.definition.core.PmoduleExportedDefs(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.PparserSpec.i_sl)), context.contextInheritedLazy(silver.definition.concrete_syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_ParserSpec), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PcomputeDependencies.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_grams), context.contextInheritedLazy(silver.definition.concrete_syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_ParserSpec))); } }, context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_grams), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.cstAst = cstRoot(fn, snt, foldr(consSyntax, nilSyntax(), addedDcls ++ med.syntaxAst), terminalPrefixes)
		silver.definition.concrete_syntax.PparserSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_cstAst__ON__silver_definition_concrete_syntax_ParserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxRoot)new silver.definition.concrete_syntax.ast.PcstRoot(context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_fn), context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_snt), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsSyntax.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)new silver.definition.concrete_syntax.ast.PnilSyntax()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_addedDcls), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.concrete_syntax.Init.med__ON__silver_definition_concrete_syntax_parserSpec).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_ModuleExportedDefs)); } })); } })); } }, context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_terminalPrefixes))); } };
		// decomposedTerminalPrefixes = unzipPairs(terminalPrefixes)
		silver.definition.concrete_syntax.PparserSpec.localAttributes[silver.definition.concrete_syntax.Init.decomposedTerminalPrefixes__ON__silver_definition_concrete_syntax_parserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)core.PunzipPairs.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_terminalPrefixes))); } };
		// top.unparse = "parser(" ++ implode(",", [ sl.unparse, quoteString(sg), quoteString(fn), quoteString(snt), unparseStrings(grams), unparseStrings(decomposedTerminalPrefixes.fst), unparseStrings(decomposedTerminalPrefixes.snd), "[" ++ implode(", ", foldr(consSyntax, nilSyntax(), addedDcls).unparses) ++ "]" ]) ++ ")"
		silver.definition.concrete_syntax.PparserSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ParserSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("parser(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(",")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PparserSpec.i_sl, silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_sg))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_fn))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_snt))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PunparseStrings.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_grams))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PunparseStrings.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.concrete_syntax.Init.decomposedTerminalPrefixes__ON__silver_definition_concrete_syntax_parserSpec).synthesized(core.Init.core_fst__ON__core_Pair)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PunparseStrings.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.concrete_syntax.Init.decomposedTerminalPrefixes__ON__silver_definition_concrete_syntax_parserSpec).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.concrete_syntax.ast.NSyntax)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsSyntax.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)new silver.definition.concrete_syntax.ast.PnilSyntax()); } }, context.childAsIsLazy(silver.definition.concrete_syntax.PparserSpec.i_addedDcls))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_Syntax)); } })), (common.StringCatter)(new common.StringCatter("]")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })); } })); } })), (common.StringCatter)(new common.StringCatter(")")))); } };

	}

	public static final common.NodeFactory<PparserSpec> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PparserSpec> {

		@Override
		public PparserSpec invoke(final Object[] children, final Object[] annotations) {
			return new PparserSpec(children[0], children[1], children[2], children[3], children[4], children[5], children[6]);
		}
	};

}
