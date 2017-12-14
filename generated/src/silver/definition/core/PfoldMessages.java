
package silver.definition.core;

public final class PfoldMessages extends common.FunctionNode {

	public static final int i_es = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_foldMessages;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfoldMessages(final Object c_es) {
		this.child_es = c_es;

	}

	private Object child_es;
	public final common.ConsCell getChild_es() {
		return (common.ConsCell) (child_es = common.Util.demand(child_es));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_es: return getChild_es();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_es: return child_es;

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
		return "silver:definition:core:foldMessages";
	}

	public static common.StringCatter invoke(final Object c_es) {
		try {
		final common.DecoratedNode context = new PfoldMessages(c_es).decorate();
		//implode("\n", map((.pp), es)) ++ "\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Message, context), context.childAsIsLazy(silver.definition.core.PfoldMessages.i_es))); } })), (common.StringCatter)(new common.StringCatter("\n"))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:foldMessages", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfoldMessages.invoke(children[0]);
		}
	};
}