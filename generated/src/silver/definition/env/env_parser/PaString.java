
package silver.definition.env.env_parser;

// top::IString ::= s::EscapedStringTerm 
public final class PaString extends silver.definition.env.env_parser.NIString {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TEscapedStringTerm.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aString;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NIString.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NIString.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PaString(final Object c_s) {
		super();
		this.child_s = c_s;

	}

	private Object child_s;
	public final silver.definition.env.env_parser.TEscapedStringTerm getChild_s() {
		return (silver.definition.env.env_parser.TEscapedStringTerm) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aString erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aString";
	}

	static void initProductionAttributeDefinitions() {
		// top.str = unescapeString(substring(1, length(s.lexeme) - 1, s.lexeme))
		silver.definition.env.env_parser.PaString.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_str__ON__silver_definition_env_env_parser_IString] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.env.PunescapeString.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.definition.env.env_parser.TEscapedStringTerm)context.childAsIs(silver.definition.env.env_parser.PaString.i_s)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.definition.env.env_parser.TEscapedStringTerm)context.childAsIs(silver.definition.env.env_parser.PaString.i_s)).lexeme))); } })); } };

	}

	public static final common.NodeFactory<PaString> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaString> {

		@Override
		public PaString invoke(final Object[] children, final Object[] annotations) {
			return new PaString(children[0]);
		}
	};

}
