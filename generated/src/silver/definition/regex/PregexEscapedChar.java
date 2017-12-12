
package silver.definition.regex;

// top::RegexChar ::= esc::EscapedChar_t 
public final class PregexEscapedChar extends silver.definition.regex.NRegexChar {

	public static final int i_esc = 0;


	public static final Class<?> childTypes[] = {silver.definition.regex.TEscapedChar_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexEscapedChar;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexChar.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexChar.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PregexEscapedChar(final Object c_esc) {
		super();
		this.child_esc = c_esc;

	}

	private Object child_esc;
	public final silver.definition.regex.TEscapedChar_t getChild_esc() {
		return (silver.definition.regex.TEscapedChar_t) (child_esc = common.Util.demand(child_esc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_esc: return getChild_esc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_esc: return child_esc;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexEscapedChar erroneously claimed to forward");
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
		return "silver:definition:regex:regexEscapedChar";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = esc.lexeme
		silver.definition.regex.PregexEscapedChar.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexChar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.regex.TEscapedChar_t)context.childAsIs(silver.definition.regex.PregexEscapedChar.i_esc)).lexeme); } };

	}

	public static final common.NodeFactory<PregexEscapedChar> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexEscapedChar> {

		@Override
		public PregexEscapedChar invoke(final Object[] children, final Object[] annotations) {
			return new PregexEscapedChar(children[0]);
		}
	};

}
