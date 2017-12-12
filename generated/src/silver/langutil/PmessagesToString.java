
package silver.langutil;

public final class PmessagesToString extends common.FunctionNode {

	public static final int i_msgs = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_messagesToString;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmessagesToString(final Object c_msgs) {
		this.child_msgs = c_msgs;

	}

	private Object child_msgs;
	public final common.ConsCell getChild_msgs() {
		return (common.ConsCell) (child_msgs = common.Util.demand(child_msgs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_msgs: return getChild_msgs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_msgs: return child_msgs;

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
		return "silver:langutil:messagesToString";
	}

	public static common.StringCatter invoke(final Object c_msgs) {
		try {
		final common.DecoratedNode context = new PmessagesToString(c_msgs).decorate();
		//implode("\n", map((.output), sortBy(messageLte, msgs)))
		return (common.StringCatter)(((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.langutil.Init.silver_langutil_output__ON__silver_langutil_Message, context), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PsortBy.invoke(silver.langutil.PmessageLte.factory, context.childAsIsLazy(silver.langutil.PmessagesToString.i_msgs))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:langutil:messagesToString", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmessagesToString.invoke(children[0]);
		}
	};
}