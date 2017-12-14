
package silver.driver;

public final class PrenderMessages extends common.FunctionNode {

	public static final int i_grammarSource = 0;
	public static final int i_msg = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_renderMessages;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_msg] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PrenderMessages(final Object c_grammarSource, final Object c_msg) {
		this.child_grammarSource = c_grammarSource;
		this.child_msg = c_msg;

	}

	private Object child_grammarSource;
	public final common.StringCatter getChild_grammarSource() {
		return (common.StringCatter) (child_grammarSource = common.Util.demand(child_grammarSource));
	}

	private Object child_msg;
	public final core.NPair getChild_msg() {
		return (core.NPair) (child_msg = common.Util.demand(child_msg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_grammarSource: return getChild_grammarSource();
			case i_msg: return getChild_msg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_grammarSource: return child_grammarSource;
			case i_msg: return child_msg;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:driver:renderMessages";
	}

	public static common.StringCatter invoke(final Object c_grammarSource, final Object c_msg) {
		try {
		final common.DecoratedNode context = new PrenderMessages(c_grammarSource, c_msg).decorate();
		//" [" ++ grammarSource ++ msg.fst ++ "]\n" ++ foldMessages(msg.snd)
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter(" [")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.driver.PrenderMessages.i_grammarSource)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.driver.PrenderMessages.i_msg).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("]\n")), (common.StringCatter)((common.StringCatter)silver.definition.core.PfoldMessages.invoke(context.childDecoratedSynthesizedLazy(silver.driver.PrenderMessages.i_msg, core.Init.core_snd__ON__core_Pair))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:renderMessages", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrenderMessages.invoke(children[0], children[1]);
		}
	};
}