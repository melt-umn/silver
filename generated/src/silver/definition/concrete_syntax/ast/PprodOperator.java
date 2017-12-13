
package silver.definition.concrete_syntax.ast;

// top::SyntaxProductionModifier ::= term::String 
public final class PprodOperator extends silver.definition.concrete_syntax.ast.NSyntaxProductionModifier {

	public static final int i_term = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_prodOperator;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprodOperator(final Object c_term) {
		super();
		this.child_term = c_term;

	}

	private Object child_term;
	public final common.StringCatter getChild_term() {
		return (common.StringCatter) (child_term = common.Util.demand(child_term));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_term: return getChild_term();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_term: return child_term;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:prodOperator erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:prodOperator";
	}

	static void initProductionAttributeDefinitions() {
		// termRef = searchEnvTree(term, top.cstEnv)
		silver.definition.concrete_syntax.ast.PprodOperator.localAttributes[silver.definition.concrete_syntax.ast.Init.termRef__ON__silver_definition_concrete_syntax_ast_prodOperator] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PprodOperator.i_term), context.contextInheritedLazy(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier))); } };
		// top.cstErrors := if ! null(termRef) then [] else [ "Terminal " ++ term ++ " was referenced but " ++ "this grammar was not included in this parser. (Referenced from operator clause on production " ++ top.productionName ++ ")" ]
		if(silver.definition.concrete_syntax.ast.PprodOperator.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] == null)
			silver.definition.concrete_syntax.ast.PprodOperator.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.PprodOperator.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.termRef__ON__silver_definition_concrete_syntax_ast_prodOperator)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Terminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.concrete_syntax.ast.PprodOperator.i_term)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" was referenced but ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("this grammar was not included in this parser. (Referenced from operator clause on production ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier)), (common.StringCatter)(new common.StringCatter(")"))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.productionOperator = just(xmlCopperRef(head(termRef)))
		silver.definition.concrete_syntax.ast.PprodOperator.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.concrete_syntax.ast.PxmlCopperRef.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.termRef__ON__silver_definition_concrete_syntax_ast_prodOperator))); } })); } })); } };
		// top.unparses = [ "oper(" ++ quoteString(term) ++ ")" ]
		silver.definition.concrete_syntax.ast.PprodOperator.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("oper(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PprodOperator.i_term))), (common.StringCatter)(new common.StringCatter(")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PprodOperator> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprodOperator> {

		@Override
		public PprodOperator invoke(final Object[] children, final Object[] annotations) {
			return new PprodOperator(children[0]);
		}
	};

}
