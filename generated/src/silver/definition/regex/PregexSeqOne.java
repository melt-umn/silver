
package silver.definition.regex;

// top::RegexSeq ::= t::RegexRepetition 
public final class PregexSeqOne extends silver.definition.regex.NRegexSeq {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.definition.regex.NRegexRepetition.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexSeqOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexSeq.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexSeq.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.regex.NRegexRepetition.num_inh_attrs];

	}

	public PregexSeqOne(final Object c_t) {
		super();
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.definition.regex.NRegexRepetition getChild_t() {
		return (silver.definition.regex.NRegexRepetition) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexSeqOne erroneously claimed to forward");
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
		return "silver:definition:regex:regexSeqOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = t.regString
		silver.definition.regex.PregexSeqOne.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexSeq] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeqOne.i_t).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexRepetition)); } };

	}

	public static final common.NodeFactory<PregexSeqOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexSeqOne> {

		@Override
		public PregexSeqOne invoke(final Object[] children, final Object[] annotations) {
			return new PregexSeqOne(children[0]);
		}
	};

}
