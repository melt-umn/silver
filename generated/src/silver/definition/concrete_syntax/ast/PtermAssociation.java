
package silver.definition.concrete_syntax.ast;

// top::SyntaxTerminalModifier ::= direction::String 
public final class PtermAssociation extends silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier {

	public static final int i_direction = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_termAssociation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtermAssociation(final Object c_direction) {
		super();
		this.child_direction = c_direction;

	}

	private Object child_direction;
	public final common.StringCatter getChild_direction() {
		return (common.StringCatter) (child_direction = common.Util.demand(child_direction));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_direction: return getChild_direction();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_direction: return child_direction;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:termAssociation erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:termAssociation";
	}

	static void initProductionAttributeDefinitions() {
		// top.opAssociation = just(direction)
		silver.definition.concrete_syntax.ast.PtermAssociation.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PtermAssociation.i_direction))); } };
		// top.unparses = [ "assoc(" ++ quoteString(direction) ++ ")" ]
		silver.definition.concrete_syntax.ast.PtermAssociation.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("assoc(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PtermAssociation.i_direction))), (common.StringCatter)(new common.StringCatter(")")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PtermAssociation> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtermAssociation> {

		@Override
		public PtermAssociation invoke(final Object[] children, final Object[] annotations) {
			return new PtermAssociation(children[0]);
		}
	};

}
