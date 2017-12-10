
package silver.definition.regex;

// top::RegexRepetition ::= i::RegexItem 
public final class PregexOnce extends silver.definition.regex.NRegexRepetition {

	public static final int i_i = 0;


	public static final Class<?> childTypes[] = {silver.definition.regex.NRegexItem.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexOnce;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.regex.NRegexRepetition.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.regex.NRegexRepetition.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_i] = new common.Lazy[silver.definition.regex.NRegexItem.num_inh_attrs];

	}

	public PregexOnce(final Object c_i) {
		super();
		this.child_i = c_i;

	}

	private Object child_i;
	public final silver.definition.regex.NRegexItem getChild_i() {
		return (silver.definition.regex.NRegexItem) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:regex:regexOnce erroneously claimed to forward");
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
		return "silver:definition:regex:regexOnce";
	}

	static void initProductionAttributeDefinitions() {
		// top.regString = i.regString
		silver.definition.regex.PregexOnce.synthesizedAttributes[silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexRepetition] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexOnce.i_i).synthesized(silver.definition.regex.Init.silver_definition_regex_regString__ON__silver_definition_regex_RegexItem)); } };

	}

	public static final common.NodeFactory<PregexOnce> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PregexOnce> {

		@Override
		public PregexOnce invoke(final Object[] children, final Object[] annotations) {
			return new PregexOnce(children[0]);
		}
	};

}
