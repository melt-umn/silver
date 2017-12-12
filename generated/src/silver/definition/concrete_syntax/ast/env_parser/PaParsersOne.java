
package silver.definition.concrete_syntax.ast.env_parser;

// top::IParsersInner ::= l::IParser 
public final class PaParsersOne extends silver.definition.concrete_syntax.ast.env_parser.NIParsersInner {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.env_parser.NIParser.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aParsersOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NIParsersInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NIParsersInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NIParser.num_inh_attrs];

	}

	public PaParsersOne(final Object c_l) {
		super();
		this.child_l = c_l;

	}

	private Object child_l;
	public final silver.definition.concrete_syntax.ast.env_parser.NIParser getChild_l() {
		return (silver.definition.concrete_syntax.ast.env_parser.NIParser) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aParsersOne erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aParsersOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.parserSpecs = l.parserSpecs
		silver.definition.concrete_syntax.ast.env_parser.PaParsersOne.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_concrete_syntax_ast_env_parser_IParsersInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.env_parser.PaParsersOne.i_l).synthesized(silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_concrete_syntax_ast_env_parser_IParser)); } };

	}

	public static final common.NodeFactory<PaParsersOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaParsersOne> {

		@Override
		public PaParsersOne invoke(final Object[] children, final Object[] annotations) {
			return new PaParsersOne(children[0]);
		}
	};

}
