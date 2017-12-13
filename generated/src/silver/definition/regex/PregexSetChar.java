
package silver.definition.regex;

// top::RegexCharSetItem ::= char::RegexChar 
public final class PregexSetChar extends silver.definition.regex.NRegexCharSetItem {

	public static final int i_char = 0;


	public static final Class<?> childTypes[] = {silver.definition.regex.NRegexChar.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexSetChar;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexCharSetItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexCharSetItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_char] = new common.Lazy[silver.definition.regex.NRegexChar.num_inh_attrs];

	}

	public PregexSetChar(final Object c_char) {
		super();
		this.child_char = c_char;

	}

	private Object child_char;
	public final silver.definition.regex.NRegexChar getChild_char() {
		return (silver.definition.regex.NRegexChar) (child_char = common.Util.demand(child_char));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexSetChar erroneously claimed to forward");
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
		return "silver:definition:regex:regexSetChar";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = char.regString
		silver.definition.regex.PregexSetChar.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexCharSetItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSetChar.i_char).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexChar)); } };

	}

	public static final common.NodeFactory<PregexSetChar> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexSetChar> {

		@Override
		public PregexSetChar invoke(final Object[] children, final Object[] annotations) {
			return new PregexSetChar(children[0]);
		}
	};

}
