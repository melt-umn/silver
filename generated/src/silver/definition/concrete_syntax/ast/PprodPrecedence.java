
package silver.definition.concrete_syntax.ast;

// top::SyntaxProductionModifier ::= lvl::Integer 
public final class PprodPrecedence extends silver.definition.concrete_syntax.ast.NSyntaxProductionModifier {

	public static final int i_lvl = 0;


	public static final Class<?> childTypes[] = {Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_prodPrecedence;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprodPrecedence(final Object c_lvl) {
		super();
		this.child_lvl = c_lvl;

	}

	private Object child_lvl;
	public final Integer getChild_lvl() {
		return (Integer) (child_lvl = common.Util.demand(child_lvl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lvl: return getChild_lvl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lvl: return child_lvl;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:prodPrecedence erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:prodPrecedence";
	}

	static void initProductionAttributeDefinitions() {
		// top.productionPrecedence = just(lvl)
		silver.definition.concrete_syntax.ast.PprodPrecedence.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PprodPrecedence.i_lvl))); } };
		// top.unparses = [ "prec(" ++ toString(lvl) ++ ")" ]
		silver.definition.concrete_syntax.ast.PprodPrecedence.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("prec(")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(silver.definition.concrete_syntax.ast.PprodPrecedence.i_lvl)))), (common.StringCatter)(new common.StringCatter(")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PprodPrecedence> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprodPrecedence> {

		@Override
		public PprodPrecedence invoke(final Object[] children, final Object[] annotations) {
			return new PprodPrecedence(children[0]);
		}
	};

}
