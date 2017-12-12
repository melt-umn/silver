
package core.monad;

// top::IOMonad<String> ::= 
public final class PcwdM extends core.monad.NIOMonad {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__core_monad_cwdM;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.monad.NIOMonad.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.monad.NIOMonad.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PcwdM() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production core:monad:cwdM erroneously claimed to forward");
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
		return "core:monad:cwdM";
	}

	static void initProductionAttributeDefinitions() {
		// res = cwd(top.stateIn)
		core.monad.PcwdM.localAttributes[core.monad.Init.res__ON__core_monad_cwdM] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.Pcwd.invoke(context.contextInheritedLazy(core.monad.Init.core_monad_stateIn__ON__core_monad_IOMonad))); } };
		// top.stateOut = res.io
		core.monad.PcwdM.synthesizedAttributes[core.monad.Init.core_monad_stateOut__ON__core_monad_IOMonad] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localDecorated(core.monad.Init.res__ON__core_monad_cwdM).synthesized(core.Init.core_io__ON__core_IOVal)); } };
		// top.stateVal = res.iovalue
		core.monad.PcwdM.synthesizedAttributes[core.monad.Init.core_monad_stateVal__ON__core_monad_IOMonad] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(core.monad.Init.res__ON__core_monad_cwdM).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } };

	}

	public static final common.NodeFactory<PcwdM> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcwdM> {

		@Override
		public PcwdM invoke(final Object[] children, final Object[] annotations) {
			return new PcwdM();
		}
	};

}
