
package silver.definition.concrete_syntax.ast;

// top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier t::SyntaxLexerClassModifiers 
public final class PconsLexerClassMod extends silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.class,silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_consLexerClassMod;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.num_inh_attrs];

	}

	public PconsLexerClassMod(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier getChild_h() {
		return (silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers getChild_t() {
		return (silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:consLexerClassMod erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:consLexerClassMod";
	}

	static void initProductionAttributeDefinitions() {
		// top.cstErrors := h.cstErrors ++ t.cstErrors
		if(silver.definition.concrete_syntax.ast.PconsLexerClassMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] == null)
			silver.definition.concrete_syntax.ast.PconsLexerClassMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PconsLexerClassMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers))); } });
		// top.dominatesXML = h.dominatesXML ++ t.dominatesXML
		silver.definition.concrete_syntax.ast.PconsLexerClassMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers))); } };
		// top.submitsXML = h.submitsXML ++ t.submitsXML
		silver.definition.concrete_syntax.ast.PconsLexerClassMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers))); } };
		// top.unparses = h.unparses ++ t.unparses
		silver.definition.concrete_syntax.ast.PconsLexerClassMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers))); } };

	}

	public static final common.NodeFactory<PconsLexerClassMod> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsLexerClassMod> {

		@Override
		public PconsLexerClassMod invoke(final Object[] children, final Object[] annotations) {
			return new PconsLexerClassMod(children[0], children[1]);
		}
	};

}
