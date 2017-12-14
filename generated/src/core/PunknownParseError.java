
package core;

// top::ParseError ::= diagnosticString::String file::String 
public final class PunknownParseError extends core.NParseError {

	public static final int i_diagnosticString = 0;
	public static final int i_file = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__core_unknownParseError;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NParseError.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NParseError.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PunknownParseError(final Object c_diagnosticString, final Object c_file) {
		super();
		this.child_diagnosticString = c_diagnosticString;
		this.child_file = c_file;

	}

	private Object child_diagnosticString;
	public final common.StringCatter getChild_diagnosticString() {
		return (common.StringCatter) (child_diagnosticString = common.Util.demand(child_diagnosticString));
	}

	private Object child_file;
	public final common.StringCatter getChild_file() {
		return (common.StringCatter) (child_file = common.Util.demand(child_file));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_diagnosticString: return getChild_diagnosticString();
			case i_file: return getChild_file();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_diagnosticString: return child_diagnosticString;
			case i_file: return child_file;

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
		throw new common.exceptions.SilverInternalError("Production core:unknownParseError erroneously claimed to forward");
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
		return "core:unknownParseError";
	}

	static void initProductionAttributeDefinitions() {
		// top.parseErrors = diagnosticString
		core.PunknownParseError.synthesizedAttributes[core.Init.core_parseErrors__ON__core_ParseError] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(core.PunknownParseError.i_diagnosticString)); } };

	}

	public static final common.NodeFactory<PunknownParseError> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PunknownParseError> {

		@Override
		public PunknownParseError invoke(final Object[] children, final Object[] annotations) {
			return new PunknownParseError(children[0], children[1]);
		}
	};

}
