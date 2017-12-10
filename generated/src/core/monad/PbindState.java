
package core.monad;

// top::State<s b> ::= st::State<s a> fn::(State<s b> ::= a) 
public final class PbindState extends core.monad.NState {

	public static final int i_st = 0;
	public static final int i_fn = 1;


	public static final Class<?> childTypes[] = {core.monad.NState.class,common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__core_monad_bindState;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.monad.NState.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.monad.NState.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_st] = new common.Lazy[core.monad.NState.num_inh_attrs];

	}

	public PbindState(final Object c_st, final Object c_fn) {
		super();
		this.child_st = c_st;
		this.child_fn = c_fn;

	}

	private Object child_st;
	public final core.monad.NState getChild_st() {
		return (core.monad.NState) (child_st = common.Util.demand(child_st));
	}

	private Object child_fn;
	public final common.NodeFactory<core.monad.NState> getChild_fn() {
		return (common.NodeFactory<core.monad.NState>) (child_fn = common.Util.demand(child_fn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_st: return getChild_st();
			case i_fn: return getChild_fn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_st: return child_st;
			case i_fn: return child_fn;

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
		throw new common.exceptions.SilverInternalError("Production core:monad:bindState erroneously claimed to forward");
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
		return "core:monad:bindState";
	}

	static void initProductionAttributeDefinitions() {
		// st.stateIn = top.stateIn
		core.monad.PbindState.childInheritedAttributes[core.monad.PbindState.i_st][core.monad.Init.core_monad_stateIn__ON__core_monad_State] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(core.monad.Init.core_monad_stateIn__ON__core_monad_State)); } };
		// newState = fn(st.stateVal)
		core.monad.PbindState.localAttributes[core.monad.Init.newState__ON__core_monad_bindState] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.monad.NState)((common.NodeFactory<core.monad.NState>)context.childAsIs(core.monad.PbindState.i_fn)).invoke(new Object[]{context.childDecoratedSynthesizedLazy(core.monad.PbindState.i_st, core.monad.Init.core_monad_stateVal__ON__core_monad_State)}, null)); } };
		// newState.stateIn = st.stateOut
		core.monad.PbindState.localInheritedAttributes[core.monad.Init.newState__ON__core_monad_bindState][core.monad.Init.core_monad_stateIn__ON__core_monad_State] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childDecorated(core.monad.PbindState.i_st).synthesized(core.monad.Init.core_monad_stateOut__ON__core_monad_State)); } };
		// top.stateOut = newState.stateOut
		core.monad.PbindState.synthesizedAttributes[core.monad.Init.core_monad_stateOut__ON__core_monad_State] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localDecorated(core.monad.Init.newState__ON__core_monad_bindState).synthesized(core.monad.Init.core_monad_stateOut__ON__core_monad_State)); } };
		// top.stateVal = newState.stateVal
		core.monad.PbindState.synthesizedAttributes[core.monad.Init.core_monad_stateVal__ON__core_monad_State] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localDecorated(core.monad.Init.newState__ON__core_monad_bindState).synthesized(core.monad.Init.core_monad_stateVal__ON__core_monad_State)); } };

	}

	public static final common.NodeFactory<PbindState> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbindState> {

		@Override
		public PbindState invoke(final Object[] children, final Object[] annotations) {
			return new PbindState(children[0], children[1]);
		}
	};

}
