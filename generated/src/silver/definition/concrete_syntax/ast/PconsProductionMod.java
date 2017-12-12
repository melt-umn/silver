
package silver.definition.concrete_syntax.ast;

// top::SyntaxProductionModifiers ::= h::SyntaxProductionModifier t::SyntaxProductionModifiers 
public final class PconsProductionMod extends silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.class,silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_consProductionMod;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.num_inh_attrs];

	}

	public PconsProductionMod(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.concrete_syntax.ast.NSyntaxProductionModifier getChild_h() {
		return (silver.definition.concrete_syntax.ast.NSyntaxProductionModifier) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers getChild_t() {
		return (silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:consProductionMod erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:consProductionMod";
	}

	static void initProductionAttributeDefinitions() {
		// top.cstErrors := h.cstErrors ++ t.cstErrors
		if(silver.definition.concrete_syntax.ast.PconsProductionMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] == null)
			silver.definition.concrete_syntax.ast.PconsProductionMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PconsProductionMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers))); } });
		// top.acode = h.acode ++ t.acode
		silver.definition.concrete_syntax.ast.PconsProductionMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsProductionMod.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsProductionMod.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers))); } };
		// top.customLayout = orElse(h.customLayout, t.customLayout)
		silver.definition.concrete_syntax.ast.PconsProductionMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.PorElse.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers))); } };
		// top.productionOperator = orElse(h.productionOperator, t.productionOperator)
		silver.definition.concrete_syntax.ast.PconsProductionMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.PorElse.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers))); } };
		// top.productionPrecedence = orElse(h.productionPrecedence, t.productionPrecedence)
		silver.definition.concrete_syntax.ast.PconsProductionMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.PorElse.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers))); } };
		// top.unparses = h.unparses ++ t.unparses
		silver.definition.concrete_syntax.ast.PconsProductionMod.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_h, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsProductionMod.i_t, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers))); } };

	}

	public static final common.NodeFactory<PconsProductionMod> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsProductionMod> {

		@Override
		public PconsProductionMod invoke(final Object[] children, final Object[] annotations) {
			return new PconsProductionMod(children[0], children[1]);
		}
	};

}
