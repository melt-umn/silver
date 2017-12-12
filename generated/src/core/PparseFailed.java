
package core;

// top::ParseResult<a> ::= e::ParseError terminals::[TerminalDescriptor] 
public final class PparseFailed extends core.NParseResult {

	public static final int i_e = 0;
	public static final int i_terminals = 1;


	public static final Class<?> childTypes[] = {core.NParseError.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__core_parseFailed;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NParseResult.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NParseResult.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[core.NParseError.num_inh_attrs];

	}

	public PparseFailed(final Object c_e, final Object c_terminals) {
		super();
		this.child_e = c_e;
		this.child_terminals = c_terminals;

	}

	private Object child_e;
	public final core.NParseError getChild_e() {
		return (core.NParseError) (child_e = common.Util.demand(child_e));
	}

	private Object child_terminals;
	public final common.ConsCell getChild_terminals() {
		return (common.ConsCell) (child_terminals = common.Util.demand(child_terminals));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i_terminals: return getChild_terminals();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
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
		throw new common.exceptions.SilverInternalError("Production core:parseFailed erroneously claimed to forward");
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
		return "core:parseFailed";
	}

	static void initProductionAttributeDefinitions() {
		// top.parseSuccess = false
		core.PparseFailed.synthesizedAttributes[core.Init.core_parseSuccess__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.parseError = e
		core.PparseFailed.synthesizedAttributes[core.Init.core_parseError__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(core.PparseFailed.i_e).undecorate(); } };
		// top.parseErrors = e.parseErrors
		core.PparseFailed.synthesizedAttributes[core.Init.core_parseErrors__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(core.PparseFailed.i_e).synthesized(core.Init.core_parseErrors__ON__core_ParseError)); } };
		// top.parseTree = error("Demanded parse tree when parsing failed! With errors: " ++ e.parseErrors)
		core.PparseFailed.synthesizedAttributes[core.Init.core_parseTree__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Perror.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Demanded parse tree when parsing failed! With errors: ")), (common.StringCatter)((common.StringCatter)context.childDecorated(core.PparseFailed.i_e).synthesized(core.Init.core_parseErrors__ON__core_ParseError))); } })); } };
		// top.parseTerminals = terminals
		core.PparseFailed.synthesizedAttributes[core.Init.core_parseTerminals__ON__core_ParseResult] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(core.PparseFailed.i_terminals)); } };

	}

	public static final common.NodeFactory<PparseFailed> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PparseFailed> {

		@Override
		public PparseFailed invoke(final Object[] children, final Object[] annotations) {
			return new PparseFailed(children[0], children[1]);
		}
	};

}
