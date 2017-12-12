
package silver.definition.concrete_syntax.ast;

// top::Syntax ::= s1::SyntaxDcl s2::Syntax 
public final class PconsSyntax extends silver.definition.concrete_syntax.ast.NSyntax {

	public static final int i_s1 = 0;
	public static final int i_s2 = 1;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.NSyntaxDcl.class,silver.definition.concrete_syntax.ast.NSyntax.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_consSyntax;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntax.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntax.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_s1] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_inh_attrs];
	childInheritedAttributes[i_s2] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntax.num_inh_attrs];

	}

	public PconsSyntax(final Object c_s1, final Object c_s2) {
		super();
		this.child_s1 = c_s1;
		this.child_s2 = c_s2;

	}

	private Object child_s1;
	public final silver.definition.concrete_syntax.ast.NSyntaxDcl getChild_s1() {
		return (silver.definition.concrete_syntax.ast.NSyntaxDcl) (child_s1 = common.Util.demand(child_s1));
	}

	private Object child_s2;
	public final silver.definition.concrete_syntax.ast.NSyntax getChild_s2() {
		return (silver.definition.concrete_syntax.ast.NSyntax) (child_s2 = common.Util.demand(child_s2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s1: return getChild_s1();
			case i_s2: return getChild_s2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s1: return child_s1;
			case i_s2: return child_s2;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:consSyntax erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:consSyntax";
	}

	static void initProductionAttributeDefinitions() {
		// top.cstDcls = s1.cstDcls ++ s2.cstDcls
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// top.cstErrors := s1.cstErrors ++ s2.cstErrors
		if(silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax] == null)
			silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax))); } });
		// top.cstProds = s1.cstProds ++ s2.cstProds
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// top.cstNormalize = s1.cstNormalize ++ s2.cstNormalize
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// top.allIgnoreTerminals = s1.allIgnoreTerminals ++ s2.allIgnoreTerminals
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// top.allMarkingTerminals = s1.allMarkingTerminals ++ s2.allMarkingTerminals
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// top.xmlCopper = s1.xmlCopper ++ s2.xmlCopper
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// top.unparses = s1.unparses ++ s2.unparses
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_Syntax))); } };

	}

	public static final common.NodeFactory<PconsSyntax> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsSyntax> {

		@Override
		public PconsSyntax invoke(final Object[] children, final Object[] annotations) {
			return new PconsSyntax(children[0], children[1]);
		}
	};

}
