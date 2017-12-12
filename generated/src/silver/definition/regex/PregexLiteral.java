
package silver.definition.regex;

public final class PregexLiteral extends common.FunctionNode {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexLiteral;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PregexLiteral(final Object c_s) {
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		return "silver:definition:regex:regexLiteral";
	}

	public static silver.definition.regex.NRegex invoke(final Object c_s) {
		try {
		final common.DecoratedNode context = new PregexLiteral(c_s).decorate();
		//if length(s) == 0 then regexEpsilon() else regexSeq(concatRegexItems(map(regexCharToItem, explode("", s))))
		return (silver.definition.regex.NRegex)((Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.regex.PregexLiteral.i_s))).length()).equals(Integer.valueOf((int)0)) ? ((silver.definition.regex.NRegex)new silver.definition.regex.PregexEpsilon()) : ((silver.definition.regex.NRegex)new silver.definition.regex.PregexSeq(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.regex.NRegexSeq)silver.definition.regex.PconcatRegexItems.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.regex.PregexCharToItem.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pexplode.invoke((new common.StringCatter("")), context.childAsIsLazy(silver.definition.regex.PregexLiteral.i_s))); } })); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:regex:regexLiteral", t);
		}
	}

	public static final common.NodeFactory<silver.definition.regex.NRegex> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.regex.NRegex> {
		@Override
		public silver.definition.regex.NRegex invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PregexLiteral.invoke(children[0]);
		}
	};
}