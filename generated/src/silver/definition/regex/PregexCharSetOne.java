
package silver.definition.regex;

// top::RegexCharSet ::= t::RegexCharSetItem 
public final class PregexCharSetOne extends silver.definition.regex.NRegexCharSet {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.definition.regex.NRegexCharSetItem.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexCharSetOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexCharSet.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexCharSet.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.regex.NRegexCharSetItem.num_inh_attrs];

	}

	public PregexCharSetOne(final Object c_t) {
		super();
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.definition.regex.NRegexCharSetItem getChild_t() {
		return (silver.definition.regex.NRegexCharSetItem) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexCharSetOne erroneously claimed to forward");
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
		return "silver:definition:regex:regexCharSetOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = t.regString
		silver.definition.regex.PregexCharSetOne.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexCharSet] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexCharSetOne.i_t).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexCharSetItem)); } };

	}

	public static final common.NodeFactory<PregexCharSetOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexCharSetOne> {

		@Override
		public PregexCharSetOne invoke(final Object[] children, final Object[] annotations) {
			return new PregexCharSetOne(children[0]);
		}
	};

}
