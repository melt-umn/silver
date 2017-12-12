
package silver.definition.regex;

// top::RegexChar ::= char::RegexChar_t 
public final class PregexChar extends silver.definition.regex.NRegexChar {

	public static final int i_char = 0;


	public static final Class<?> childTypes[] = {silver.definition.regex.TRegexChar_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexChar;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexChar.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexChar.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PregexChar(final Object c_char) {
		super();
		this.child_char = c_char;

	}

	private Object child_char;
	public final silver.definition.regex.TRegexChar_t getChild_char() {
		return (silver.definition.regex.TRegexChar_t) (child_char = common.Util.demand(child_char));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_char: return getChild_char();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_char: return child_char;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexChar erroneously claimed to forward");
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
		return "silver:definition:regex:regexChar";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = char.lexeme
		silver.definition.regex.PregexChar.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexChar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.regex.TRegexChar_t)context.childAsIs(silver.definition.regex.PregexChar.i_char)).lexeme); } };

	}

	public static final common.NodeFactory<PregexChar> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexChar> {

		@Override
		public PregexChar invoke(final Object[] children, final Object[] annotations) {
			return new PregexChar(children[0]);
		}
	};

}
