
package core.monad;

// top::State<s a> ::= x::a 
public final class PreturnState extends core.monad.NState {

	public static final int i_x = 0;


	public static final Class<?> childTypes[] = {Object.class};

	public static final int num_local_attrs = Init.count_local__ON__core_monad_returnState;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.monad.NState.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.monad.NState.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PreturnState(final Object c_x) {
		super();
		this.child_x = c_x;

	}

	private Object child_x;
	public final Object getChild_x() {
		return (Object) (child_x = common.Util.demand(child_x));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_x: return getChild_x();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_x: return child_x;

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
		throw new common.exceptions.SilverInternalError("Production core:monad:returnState erroneously claimed to forward");
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
		return "core:monad:returnState";
	}

	static void initProductionAttributeDefinitions() {
		// top.stateOut = top.stateIn
		core.monad.PreturnState.synthesizedAttributes[core.monad.Init.core_monad_stateOut__ON__core_monad_State] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(core.monad.Init.core_monad_stateIn__ON__core_monad_State)); } };
		// top.stateVal = x
		core.monad.PreturnState.synthesizedAttributes[core.monad.Init.core_monad_stateVal__ON__core_monad_State] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childAsIs(core.monad.PreturnState.i_x)); } };

	}

	public static final common.NodeFactory<PreturnState> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PreturnState> {

		@Override
		public PreturnState invoke(final Object[] children, final Object[] annotations) {
			return new PreturnState(children[0]);
		}
	};

}
