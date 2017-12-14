
package silver.modification.impide.cstast;

// top::SyntaxDcl ::= fontName::String fnt::Font 
public final class PsyntaxFont extends silver.definition.concrete_syntax.ast.NSyntaxDcl {

	public static final int i_fontName = 0;
	public static final int i_fnt = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.modification.impide.spec.NFont.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_cstast_syntaxFont;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_fnt] = new common.Lazy[silver.modification.impide.spec.NFont.num_inh_attrs];

	}

	public PsyntaxFont(final Object c_fontName, final Object c_fnt) {
		super();
		this.child_fontName = c_fontName;
		this.child_fnt = c_fnt;

	}

	private Object child_fontName;
	public final common.StringCatter getChild_fontName() {
		return (common.StringCatter) (child_fontName = common.Util.demand(child_fontName));
	}

	private Object child_fnt;
	public final silver.modification.impide.spec.NFont getChild_fnt() {
		return (silver.modification.impide.spec.NFont) (child_fnt = common.Util.demand(child_fnt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fontName: return getChild_fontName();
			case i_fnt: return getChild_fnt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fontName: return child_fontName;
			case i_fnt: return child_fnt;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:cstast:syntaxFont erroneously claimed to forward");
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
		return "silver:modification:impide:cstast:syntaxFont";
	}

	static void initProductionAttributeDefinitions() {
		// top.fontList = [ pair(makeCopperName(fontName), fnt) ]
		silver.modification.impide.cstast.PsyntaxFont.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childAsIsLazy(silver.modification.impide.cstast.PsyntaxFont.i_fontName))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.impide.cstast.PsyntaxFont.i_fnt)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.sortKey = "111"
		silver.modification.impide.cstast.PsyntaxFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_sortKey__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("111")); } };
		// top.cstDcls = [ pair(fontName, top) ]
		silver.modification.impide.cstast.PsyntaxFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.modification.impide.cstast.PsyntaxFont.i_fontName), context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.cstErrors := []
		if(silver.modification.impide.cstast.PsyntaxFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] == null)
			silver.modification.impide.cstast.PsyntaxFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl);
		((common.CollectionAttribute)silver.modification.impide.cstast.PsyntaxFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.cstNormalize = [ top ]
		silver.modification.impide.cstast.PsyntaxFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.undecorate(), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.xmlCopper = ""
		silver.modification.impide.cstast.PsyntaxFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.unparses = []
		silver.modification.impide.cstast.PsyntaxFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PsyntaxFont> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsyntaxFont> {

		@Override
		public PsyntaxFont invoke(final Object[] children, final Object[] annotations) {
			return new PsyntaxFont(children[0], children[1]);
		}
	};

}
