
package silver.definition.regex;

// top::RegexItem ::= '(' r::Regex ')' 
public final class PregexGroup extends silver.definition.regex.NRegexItem {

	public static final int i__G_2 = 0;
	public static final int i_r = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.regex.TRegexLParen_t.class,silver.definition.regex.NRegex.class,silver.definition.regex.TRegexRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexGroup;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.regex.NRegex.num_inh_attrs];

	}

	public PregexGroup(final Object c__G_2, final Object c_r, final Object c__G_0) {
		super();
		this.child__G_2 = c__G_2;
		this.child_r = c_r;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.definition.regex.TRegexLParen_t getChild__G_2() {
		return (silver.definition.regex.TRegexLParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_r;
	public final silver.definition.regex.NRegex getChild_r() {
		return (silver.definition.regex.NRegex) (child_r = common.Util.demand(child_r));
	}

	private Object child__G_0;
	public final silver.definition.regex.TRegexRParen_t getChild__G_0() {
		return (silver.definition.regex.TRegexRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_r: return getChild_r();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_r: return child_r;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexGroup erroneously claimed to forward");
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
		return "silver:definition:regex:regexGroup";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = "(" ++ r.regString ++ ")"
		silver.definition.regex.PregexGroup.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexGroup.i_r).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_Regex)), (common.StringCatter)(new common.StringCatter(")")))); } };

	}

	public static final common.NodeFactory<PregexGroup> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexGroup> {

		@Override
		public PregexGroup invoke(final Object[] children, final Object[] annotations) {
			return new PregexGroup(children[0], children[1], children[2]);
		}
	};

}
