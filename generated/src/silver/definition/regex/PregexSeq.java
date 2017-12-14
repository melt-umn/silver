
package silver.definition.regex;

// top::Regex ::= h::RegexSeq 
public final class PregexSeq extends silver.definition.regex.NRegex {

	public static final int i_h = 0;


	public static final Class<?> childTypes[] = {silver.definition.regex.NRegexSeq.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexSeq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegex.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegex.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.regex.NRegexSeq.num_inh_attrs];

	}

	public PregexSeq(final Object c_h) {
		super();
		this.child_h = c_h;

	}

	private Object child_h;
	public final silver.definition.regex.NRegexSeq getChild_h() {
		return (silver.definition.regex.NRegexSeq) (child_h = common.Util.demand(child_h));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexSeq erroneously claimed to forward");
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
		return "silver:definition:regex:regexSeq";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = h.regString
		silver.definition.regex.PregexSeq.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_Regex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeq.i_h).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexSeq)); } };

	}

	public static final common.NodeFactory<PregexSeq> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexSeq> {

		@Override
		public PregexSeq invoke(final Object[] children, final Object[] annotations) {
			return new PregexSeq(children[0]);
		}
	};

}
