
package core.monad;

// top::IOMonad<Unit> ::= file::String contents::String 
public final class PwriteFileM extends core.monad.NIOMonad {

	public static final int i_file = 0;
	public static final int i_contents = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__core_monad_writeFileM;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.monad.NIOMonad.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.monad.NIOMonad.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PwriteFileM(final Object c_file, final Object c_contents) {
		super();
		this.child_file = c_file;
		this.child_contents = c_contents;

	}

	private Object child_file;
	public final common.StringCatter getChild_file() {
		return (common.StringCatter) (child_file = common.Util.demand(child_file));
	}

	private Object child_contents;
	public final common.StringCatter getChild_contents() {
		return (common.StringCatter) (child_contents = common.Util.demand(child_contents));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_file: return getChild_file();
			case i_contents: return getChild_contents();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_file: return child_file;
			case i_contents: return child_contents;

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
		throw new common.exceptions.SilverInternalError("Production core:monad:writeFileM erroneously claimed to forward");
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
		return "core:monad:writeFileM";
	}

	static void initProductionAttributeDefinitions() {
		// top.stateOut = writeFile(file, contents, top.stateIn)
		core.monad.PwriteFileM.synthesizedAttributes[core.monad.Init.core_monad_stateOut__ON__core_monad_IOMonad] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PwriteFile.invoke(context.childAsIsLazy(core.monad.PwriteFileM.i_file), context.childAsIsLazy(core.monad.PwriteFileM.i_contents), context.contextInheritedLazy(core.monad.Init.core_monad_stateIn__ON__core_monad_IOMonad))); } };
		// top.stateVal = unit()
		core.monad.PwriteFileM.synthesizedAttributes[core.monad.Init.core_monad_stateVal__ON__core_monad_IOMonad] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NUnit)new core.Punit()); } };

	}

	public static final common.NodeFactory<PwriteFileM> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PwriteFileM> {

		@Override
		public PwriteFileM invoke(final Object[] children, final Object[] annotations) {
			return new PwriteFileM(children[0], children[1]);
		}
	};

}
