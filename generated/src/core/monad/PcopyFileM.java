
package core.monad;

// top::IOMonad<Unit> ::= src::String dst::String 
public final class PcopyFileM extends core.monad.NIOMonad {

	public static final int i_src = 0;
	public static final int i_dst = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__core_monad_copyFileM;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.monad.NIOMonad.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.monad.NIOMonad.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PcopyFileM(final Object c_src, final Object c_dst) {
		super();
		this.child_src = c_src;
		this.child_dst = c_dst;

	}

	private Object child_src;
	public final common.StringCatter getChild_src() {
		return (common.StringCatter) (child_src = common.Util.demand(child_src));
	}

	private Object child_dst;
	public final common.StringCatter getChild_dst() {
		return (common.StringCatter) (child_dst = common.Util.demand(child_dst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_src: return getChild_src();
			case i_dst: return getChild_dst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_src: return child_src;
			case i_dst: return child_dst;

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
		throw new common.exceptions.SilverInternalError("Production core:monad:copyFileM erroneously claimed to forward");
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
		return "core:monad:copyFileM";
	}

	static void initProductionAttributeDefinitions() {
		// top.stateOut = copyFile(src, dst, top.stateIn)
		core.monad.PcopyFileM.synthesizedAttributes[core.monad.Init.core_monad_stateOut__ON__core_monad_IOMonad] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PcopyFile.invoke(context.childAsIsLazy(core.monad.PcopyFileM.i_src), context.childAsIsLazy(core.monad.PcopyFileM.i_dst), context.contextInheritedLazy(core.monad.Init.core_monad_stateIn__ON__core_monad_IOMonad))); } };
		// top.stateVal = unit()
		core.monad.PcopyFileM.synthesizedAttributes[core.monad.Init.core_monad_stateVal__ON__core_monad_IOMonad] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NUnit)new core.Punit()); } };

	}

	public static final common.NodeFactory<PcopyFileM> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcopyFileM> {

		@Override
		public PcopyFileM invoke(final Object[] children, final Object[] annotations) {
			return new PcopyFileM(children[0], children[1]);
		}
	};

}
