
package silver.modification.impide.cstast;

// top::SyntaxTerminalModifier ::= fontName::String 
public final class PtermFont extends silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier {

	public static final int i_fontName = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_cstast_termFont;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtermFont(final Object c_fontName) {
		super();
		this.child_fontName = c_fontName;

	}

	private Object child_fontName;
	public final common.StringCatter getChild_fontName() {
		return (common.StringCatter) (child_fontName = common.Util.demand(child_fontName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fontName: return getChild_fontName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fontName: return child_fontName;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:cstast:termFont erroneously claimed to forward");
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
		return "silver:modification:impide:cstast:termFont";
	}

	static void initProductionAttributeDefinitions() {
		// top.cstErrors := []
		if(silver.modification.impide.cstast.PtermFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] == null)
			silver.modification.impide.cstast.PtermFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier);
		((common.CollectionAttribute)silver.modification.impide.cstast.PtermFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.unparses = [ "font('" ++ fontName ++ "')" ]
		silver.modification.impide.cstast.PtermFont.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("font('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.cstast.PtermFont.i_fontName)), (common.StringCatter)(new common.StringCatter("')")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.fontAttr = makeCopperName(fontName)
		silver.modification.impide.cstast.PtermFont.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.concrete_syntax.ast.PmakeCopperName.invoke(context.childAsIsLazy(silver.modification.impide.cstast.PtermFont.i_fontName))); } };

	}

	public static final common.NodeFactory<PtermFont> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtermFont> {

		@Override
		public PtermFont invoke(final Object[] children, final Object[] annotations) {
			return new PtermFont(children[0]);
		}
	};

}
