
package silver.extension.easyterminal;

public final class PbuildTerminalTree extends common.FunctionNode {

	public static final int i_eis = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_easyterminal_buildTerminalTree;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PbuildTerminalTree(final Object c_eis) {
		this.child_eis = c_eis;

	}

	private Object child_eis;
	public final common.ConsCell getChild_eis() {
		return (common.ConsCell) (child_eis = common.Util.demand(child_eis));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_eis: return getChild_eis();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_eis: return child_eis;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:extension:easyterminal:buildTerminalTree";
	}

	public static Object invoke(final Object c_eis) {
		try {
		final common.DecoratedNode context = new PbuildTerminalTree(c_eis).decorate();
		//directBuildTree(foldr(filterAndConvertTermDcls, [], eis))
		return (Object)(((Object)silver.definition.env.PdirectBuildTree.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfoldr.invoke(silver.extension.easyterminal.PfilterAndConvertTermDcls.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, context.childAsIsLazy(silver.extension.easyterminal.PbuildTerminalTree.i_eis))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:easyterminal:buildTerminalTree", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbuildTerminalTree.invoke(children[0]);
		}
	};
}