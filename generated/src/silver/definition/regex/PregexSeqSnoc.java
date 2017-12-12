
package silver.definition.regex;

// top::RegexSeq ::= h::RegexSeq t::RegexRepetition 
public final class PregexSeqSnoc extends silver.definition.regex.NRegexSeq {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.regex.NRegexSeq.class,silver.definition.regex.NRegexRepetition.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexSeqSnoc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexSeq.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexSeq.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.regex.NRegexSeq.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.regex.NRegexRepetition.num_inh_attrs];

	}

	public PregexSeqSnoc(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.regex.NRegexSeq getChild_h() {
		return (silver.definition.regex.NRegexSeq) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.regex.NRegexRepetition getChild_t() {
		return (silver.definition.regex.NRegexRepetition) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexSeqSnoc erroneously claimed to forward");
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
		return "silver:definition:regex:regexSeqSnoc";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = h.regString ++ t.regString
		silver.definition.regex.PregexSeqSnoc.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexSeq] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeqSnoc.i_h).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexSeq)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeqSnoc.i_t).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexRepetition))); } };

	}

	public static final common.NodeFactory<PregexSeqSnoc> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexSeqSnoc> {

		@Override
		public PregexSeqSnoc invoke(final Object[] children, final Object[] annotations) {
			return new PregexSeqSnoc(children[0], children[1]);
		}
	};

}
