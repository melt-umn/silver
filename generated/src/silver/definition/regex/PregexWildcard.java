
package silver.definition.regex;

// top::RegexItem ::= '.' 
public final class PregexWildcard extends silver.definition.regex.NRegexItem {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.regex.TRegexWildcard_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexWildcard;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PregexWildcard(final Object c__G_0) {
		super();
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.definition.regex.TRegexWildcard_t getChild__G_0() {
		return (silver.definition.regex.TRegexWildcard_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexWildcard erroneously claimed to forward");
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
		return "silver:definition:regex:regexWildcard";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = "."
		silver.definition.regex.PregexWildcard.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter(".")); } };

	}

	public static final common.NodeFactory<PregexWildcard> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexWildcard> {

		@Override
		public PregexWildcard invoke(final Object[] children, final Object[] annotations) {
			return new PregexWildcard(children[0]);
		}
	};

}
