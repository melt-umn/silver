
package core;

// top::ParseResult<a> ::= t::a terminals::[TerminalDescriptor] 
public final class PparseSucceeded extends core.NParseResult {

	public static final int i_t = 0;
	public static final int i_terminals = 1;


	public static final Class<?> childTypes[] = {Object.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__core_parseSucceeded;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NParseResult.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NParseResult.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PparseSucceeded(final Object c_t, final Object c_terminals) {
		super();
		this.child_t = c_t;
		this.child_terminals = c_terminals;

	}

	private Object child_t;
	public final Object getChild_t() {
		return (Object) (child_t = common.Util.demand(child_t));
	}

	private Object child_terminals;
	public final common.ConsCell getChild_terminals() {
		return (common.ConsCell) (child_terminals = common.Util.demand(child_terminals));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i_terminals: return getChild_terminals();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i_terminals: return child_terminals;

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
		throw new common.exceptions.SilverInternalError("Production core:parseSucceeded erroneously claimed to forward");
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
		return "core:parseSucceeded";
	}

	static void initProductionAttributeDefinitions() {
		// top.parseSuccess = true
		core.PparseSucceeded.synthesizedAttributes[core.Init.core_parseSuccess__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.parseError = error("Demanded parseError, but parsing succeeded!")
		core.PparseSucceeded.synthesizedAttributes[core.Init.core_parseError__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NParseError)core.Perror.invoke((new common.StringCatter("Demanded parseError, but parsing succeeded!")))); } };
		// top.parseErrors = error("Demanded parse errors, but parsing succeeded!")
		core.PparseSucceeded.synthesizedAttributes[core.Init.core_parseErrors__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Demanded parse errors, but parsing succeeded!")))); } };
		// top.parseTree = t
		core.PparseSucceeded.synthesizedAttributes[core.Init.core_parseTree__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.PparseSucceeded.i_t)); } };
		// top.parseTerminals = terminals
		core.PparseSucceeded.synthesizedAttributes[core.Init.core_parseTerminals__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(core.PparseSucceeded.i_terminals)); } };

	}

	public static final common.NodeFactory<PparseSucceeded> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PparseSucceeded> {

		@Override
		public PparseSucceeded invoke(final Object[] children, final Object[] annotations) {
			return new PparseSucceeded(children[0], children[1]);
		}
	};

}
