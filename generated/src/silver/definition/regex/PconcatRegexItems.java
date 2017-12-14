
package silver.definition.regex;

public final class PconcatRegexItems extends common.FunctionNode {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_concatRegexItems;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PconcatRegexItems(final Object c_l) {
		this.child_l = c_l;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

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
		return "silver:definition:regex:concatRegexItems";
	}

	public static silver.definition.regex.NRegexSeq invoke(final Object c_l) {
		try {
		final common.DecoratedNode context = new PconcatRegexItems(c_l).decorate();
		//foldl(regexSeqSnoc, regexSeqOne(regexOnce(head(l))), map(regexOnce, tail(l)))
		return (silver.definition.regex.NRegexSeq)(((silver.definition.regex.NRegexSeq)core.Pfoldl.invoke(silver.definition.regex.PregexSeqSnoc.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.regex.NRegexSeq)new silver.definition.regex.PregexSeqOne(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.regex.NRegexRepetition)new silver.definition.regex.PregexOnce(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.regex.NRegexItem)core.Phead.invoke(context.childAsIsLazy(silver.definition.regex.PconcatRegexItems.i_l))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.regex.PregexOnce.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.regex.PconcatRegexItems.i_l))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:regex:concatRegexItems", t);
		}
	}

	public static final common.NodeFactory<silver.definition.regex.NRegexSeq> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.regex.NRegexSeq> {
		@Override
		public silver.definition.regex.NRegexSeq invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PconcatRegexItems.invoke(children[0]);
		}
	};
}