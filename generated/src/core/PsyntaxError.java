
package core;

// top::ParseError ::= diagnosticString::String location::Location expectedNames::[String] matchedNames::[String] 
public final class PsyntaxError extends core.NParseError {

	public static final int i_diagnosticString = 0;
	public static final int i_location = 1;
	public static final int i_expectedNames = 2;
	public static final int i_matchedNames = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,core.NLocation.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__core_syntaxError;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NParseError.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NParseError.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_location] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PsyntaxError(final Object c_diagnosticString, final Object c_location, final Object c_expectedNames, final Object c_matchedNames) {
		super();
		this.child_diagnosticString = c_diagnosticString;
		this.child_location = c_location;
		this.child_expectedNames = c_expectedNames;
		this.child_matchedNames = c_matchedNames;

	}

	private Object child_diagnosticString;
	public final common.StringCatter getChild_diagnosticString() {
		return (common.StringCatter) (child_diagnosticString = common.Util.demand(child_diagnosticString));
	}

	private Object child_location;
	public final core.NLocation getChild_location() {
		return (core.NLocation) (child_location = common.Util.demand(child_location));
	}

	private Object child_expectedNames;
	public final common.ConsCell getChild_expectedNames() {
		return (common.ConsCell) (child_expectedNames = common.Util.demand(child_expectedNames));
	}

	private Object child_matchedNames;
	public final common.ConsCell getChild_matchedNames() {
		return (common.ConsCell) (child_matchedNames = common.Util.demand(child_matchedNames));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_diagnosticString: return getChild_diagnosticString();
			case i_location: return getChild_location();
			case i_expectedNames: return getChild_expectedNames();
			case i_matchedNames: return getChild_matchedNames();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_diagnosticString: return child_diagnosticString;
			case i_location: return child_location;
			case i_expectedNames: return child_expectedNames;
			case i_matchedNames: return child_matchedNames;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production core:syntaxError erroneously claimed to forward");
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
		return "core:syntaxError";
	}

	static void initProductionAttributeDefinitions() {
		// top.parseErrors = diagnosticString
		core.PsyntaxError.synthesizedAttributes[core.Init.core_parseErrors__ON__core_ParseError] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(core.PsyntaxError.i_diagnosticString)); } };

	}

	public static final common.NodeFactory<PsyntaxError> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsyntaxError> {

		@Override
		public PsyntaxError invoke(final Object[] children, final Object[] annotations) {
			return new PsyntaxError(children[0], children[1], children[2], children[3]);
		}
	};

}
