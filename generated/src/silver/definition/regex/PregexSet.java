
package silver.definition.regex;

// top::RegexItem ::= '[' g::RegexCharSet ']' 
public final class PregexSet extends silver.definition.regex.NRegexItem {

	public static final int i__G_2 = 0;
	public static final int i_g = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.regex.TRegexLBrack_t.class,silver.definition.regex.NRegexCharSet.class,silver.definition.regex.TRegexRBrack_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexSet;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_g] = new common.Lazy[silver.definition.regex.NRegexCharSet.num_inh_attrs];

	}

	public PregexSet(final Object c__G_2, final Object c_g, final Object c__G_0) {
		super();
		this.child__G_2 = c__G_2;
		this.child_g = c_g;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.definition.regex.TRegexLBrack_t getChild__G_2() {
		return (silver.definition.regex.TRegexLBrack_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_g;
	public final silver.definition.regex.NRegexCharSet getChild_g() {
		return (silver.definition.regex.NRegexCharSet) (child_g = common.Util.demand(child_g));
	}

	private Object child__G_0;
	public final silver.definition.regex.TRegexRBrack_t getChild__G_0() {
		return (silver.definition.regex.TRegexRBrack_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_g: return getChild_g();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_g: return child_g;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexSet erroneously claimed to forward");
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
		return "silver:definition:regex:regexSet";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = "[" ++ g.regString ++ "]"
		silver.definition.regex.PregexSet.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSet.i_g).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexCharSet)), (common.StringCatter)(new common.StringCatter("]")))); } };

	}

	public static final common.NodeFactory<PregexSet> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexSet> {

		@Override
		public PregexSet invoke(final Object[] children, final Object[] annotations) {
			return new PregexSet(children[0], children[1], children[2]);
		}
	};

}
