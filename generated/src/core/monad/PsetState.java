
package core.monad;

// top::State<s Unit> ::= newState::s 
public final class PsetState extends core.monad.NState {

	public static final int i_newState = 0;


	public static final Class<?> childTypes[] = {Object.class};

	public static final int num_local_attrs = Init.count_local__ON__core_monad_setState;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.monad.NState.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.monad.NState.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PsetState(final Object c_newState) {
		super();
		this.child_newState = c_newState;

	}

	private Object child_newState;
	public final Object getChild_newState() {
		return (Object) (child_newState = common.Util.demand(child_newState));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_newState: return getChild_newState();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_newState: return child_newState;

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
		throw new common.exceptions.SilverInternalError("Production core:monad:setState erroneously claimed to forward");
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
		return "core:monad:setState";
	}

	static void initProductionAttributeDefinitions() {
		// top.stateOut = newState
		core.monad.PsetState.synthesizedAttributes[core.monad.Init.core_monad_stateOut__ON__core_monad_State] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.monad.PsetState.i_newState)); } };
		// top.stateVal = unit()
		core.monad.PsetState.synthesizedAttributes[core.monad.Init.core_monad_stateVal__ON__core_monad_State] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NUnit)new core.Punit()); } };

	}

	public static final common.NodeFactory<PsetState> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsetState> {

		@Override
		public PsetState invoke(final Object[] children, final Object[] annotations) {
			return new PsetState(children[0]);
		}
	};

}
