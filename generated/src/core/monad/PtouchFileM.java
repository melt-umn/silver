
package core.monad;

// top::IOMonad<Unit> ::= file::String 
public final class PtouchFileM extends core.monad.NIOMonad {

	public static final int i_file = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__core_monad_touchFileM;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.monad.NIOMonad.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.monad.NIOMonad.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtouchFileM(final Object c_file) {
		super();
		this.child_file = c_file;

	}

	private Object child_file;
	public final common.StringCatter getChild_file() {
		return (common.StringCatter) (child_file = common.Util.demand(child_file));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_file: return getChild_file();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_file: return child_file;

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
		throw new common.exceptions.SilverInternalError("Production core:monad:touchFileM erroneously claimed to forward");
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
		return "core:monad:touchFileM";
	}

	static void initProductionAttributeDefinitions() {
		// top.stateOut = touchFile(file, top.stateIn)
		core.monad.PtouchFileM.synthesizedAttributes[core.monad.Init.core_monad_stateOut__ON__core_monad_IOMonad] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PtouchFile.invoke(context.childAsIsLazy(core.monad.PtouchFileM.i_file), context.contextInheritedLazy(core.monad.Init.core_monad_stateIn__ON__core_monad_IOMonad))); } };
		// top.stateVal = unit()
		core.monad.PtouchFileM.synthesizedAttributes[core.monad.Init.core_monad_stateVal__ON__core_monad_IOMonad] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NUnit)new core.Punit()); } };

	}

	public static final common.NodeFactory<PtouchFileM> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtouchFileM> {

		@Override
		public PtouchFileM invoke(final Object[] children, final Object[] annotations) {
			return new PtouchFileM(children[0]);
		}
	};

}
