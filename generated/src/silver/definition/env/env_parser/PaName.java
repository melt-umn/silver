
package silver.definition.env.env_parser;

// top::IName ::= i::Id_t 
public final class PaName extends silver.definition.env.env_parser.NIName {

	public static final int i_i = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TId_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NIName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PaName(final Object c_i) {
		super();
		this.child_i = c_i;

	}

	private Object child_i;
	public final silver.definition.env.env_parser.TId_t getChild_i() {
		return (silver.definition.env.env_parser.TId_t) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aName erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aName";
	}

	static void initProductionAttributeDefinitions() {
		// top.aname = substring(1, length(i.lexeme) - 1, i.lexeme)
		silver.definition.env.env_parser.PaName.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.definition.env.env_parser.TId_t)context.childAsIs(silver.definition.env.env_parser.PaName.i_i)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.definition.env.env_parser.TId_t)context.childAsIs(silver.definition.env.env_parser.PaName.i_i)).lexeme))); } };

	}

	public static final common.NodeFactory<PaName> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaName> {

		@Override
		public PaName invoke(final Object[] children, final Object[] annotations) {
			return new PaName(children[0]);
		}
	};

}
