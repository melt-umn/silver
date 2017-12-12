
package silver.definition.concrete_syntax.ast;

// top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier t::SyntaxTerminalModifiers 
public final class PconsTerminalMod extends silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.class,silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_consTerminalMod;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.num_inh_attrs];

	}

	public PconsTerminalMod(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier getChild_h() {
		return (silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers getChild_t() {
		return (silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:consTerminalMod erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:consTerminalMod";
	}

	static void initProductionAttributeDefinitions() {
		// top.cstErrors := h.cstErrors ++ t.cstErrors
		if(silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] == null)
			silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } });
		// top.dominatesXML = h.dominatesXML ++ t.dominatesXML
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		// top.submitsXML = h.submitsXML ++ t.submitsXML
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		// top.lexerclassesXML = h.lexerclassesXML ++ t.lexerclassesXML
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		// top.ignored = h.ignored || t.ignored
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_ignored__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_ignored__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)) || ((Boolean)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_ignored__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		// top.marking = h.marking || t.marking
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)) || ((Boolean)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		// top.acode = h.acode ++ t.acode
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		// top.opPrecedence = orElse(h.opPrecedence, t.opPrecedence)
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.PorElse.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		// top.opAssociation = orElse(h.opAssociation, t.opAssociation)
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.PorElse.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		// top.unparses = h.unparses ++ t.unparses
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };

	}

	public static final common.NodeFactory<PconsTerminalMod> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsTerminalMod> {

		@Override
		public PconsTerminalMod invoke(final Object[] children, final Object[] annotations) {
			return new PconsTerminalMod(children[0], children[1]);
		}
	};

}
