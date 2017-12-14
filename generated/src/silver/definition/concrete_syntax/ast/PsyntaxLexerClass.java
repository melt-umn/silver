
package silver.definition.concrete_syntax.ast;

// top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers 
public final class PsyntaxLexerClass extends silver.definition.concrete_syntax.ast.NSyntaxDcl {

	public static final int i_n = 0;
	public static final int i_modifiers = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxLexerClass;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_modifiers] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.num_inh_attrs];

	}

	public PsyntaxLexerClass(final Object c_n, final Object c_modifiers) {
		super();
		this.child_n = c_n;
		this.child_modifiers = c_modifiers;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_modifiers;
	public final silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers getChild_modifiers() {
		return (silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers) (child_modifiers = common.Util.demand(child_modifiers));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_modifiers: return getChild_modifiers();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_modifiers: return child_modifiers;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:syntaxLexerClass erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:syntaxLexerClass";
	}

	static void initProductionAttributeDefinitions() {
		// top.sortKey = "AAA" ++ n
		silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_sortKey__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("AAA")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_n))); } };
		// top.cstDcls = [ pair(n, top) ]
		silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_n), context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.cstErrors := modifiers.cstErrors ++ if length(searchEnvTree(n, top.cstEnv)) == 1 then [] else [ "Name conflict with lexer class " ++ n ]
		if(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_modifiers, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_n), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl))); } })).equals(Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Name conflict with lexer class ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_n))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } })); } });
		// top.classDomContribs = modifiers.dominatesXML
		silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_classDomContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers)); } };
		// top.classSubContribs = modifiers.submitsXML
		silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_classSubContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_modifiers).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers)); } };
		// top.cstNormalize = [ top ]
		silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.undecorate(), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.xmlCopper = "  <TerminalClass id=\"" ++ makeCopperName(n) ++ "\" />\n"
		silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("  <TerminalClass id=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_n))), (common.StringCatter)(new common.StringCatter("\" />\n")))); } };
		// top.unparses = [ "lclass('" ++ n ++ "', " ++ unparseNonStrings(modifiers.unparses) ++ ")" ]
		silver.definition.concrete_syntax.ast.PsyntaxLexerClass.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("lclass('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_n)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseNonStrings.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PsyntaxLexerClass.i_modifiers, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers))), (common.StringCatter)(new common.StringCatter(")")))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PsyntaxLexerClass> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsyntaxLexerClass> {

		@Override
		public PsyntaxLexerClass invoke(final Object[] children, final Object[] annotations) {
			return new PsyntaxLexerClass(children[0], children[1]);
		}
	};

}
