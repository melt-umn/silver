
package silver.definition.regex;

// top::RegexCharSetItem ::= l::RegexChar '-' u::RegexChar 
public final class PregexSetRange extends silver.definition.regex.NRegexCharSetItem {

	public static final int i_l = 0;
	public static final int i__G_1 = 1;
	public static final int i_u = 2;


	public static final Class<?> childTypes[] = {silver.definition.regex.NRegexChar.class,silver.definition.regex.TRange_t.class,silver.definition.regex.NRegexChar.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexSetRange;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexCharSetItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexCharSetItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.regex.NRegexChar.num_inh_attrs];
	childInheritedAttributes[i_u] = new common.Lazy[silver.definition.regex.NRegexChar.num_inh_attrs];

	}

	public PregexSetRange(final Object c_l, final Object c__G_1, final Object c_u) {
		super();
		this.child_l = c_l;
		this.child__G_1 = c__G_1;
		this.child_u = c_u;

	}

	private Object child_l;
	public final silver.definition.regex.NRegexChar getChild_l() {
		return (silver.definition.regex.NRegexChar) (child_l = common.Util.demand(child_l));
	}

	private Object child__G_1;
	public final silver.definition.regex.TRange_t getChild__G_1() {
		return (silver.definition.regex.TRange_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_u;
	public final silver.definition.regex.NRegexChar getChild_u() {
		return (silver.definition.regex.NRegexChar) (child_u = common.Util.demand(child_u));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i__G_1: return getChild__G_1();
			case i_u: return getChild_u();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i__G_1: return child__G_1;
			case i_u: return child_u;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexSetRange erroneously claimed to forward");
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
		return "silver:definition:regex:regexSetRange";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = l.regString ++ "-" ++ u.regString
		silver.definition.regex.PregexSetRange.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexCharSetItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSetRange.i_l).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexChar)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("-")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSetRange.i_u).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexChar)))); } };

	}

	public static final common.NodeFactory<PregexSetRange> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexSetRange> {

		@Override
		public PregexSetRange invoke(final Object[] children, final Object[] annotations) {
			return new PregexSetRange(children[0], children[1], children[2]);
		}
	};

}
