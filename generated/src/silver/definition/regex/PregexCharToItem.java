
package silver.definition.regex;

public final class PregexCharToItem extends common.FunctionNode {

	public static final int i_ch = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_regex_regexCharToItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PregexCharToItem(final Object c_ch) {
		this.child_ch = c_ch;

	}

	private Object child_ch;
	public final common.StringCatter getChild_ch() {
		return (common.StringCatter) (child_ch = common.Util.demand(child_ch));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ch: return getChild_ch();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ch: return child_ch;

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
		return "silver:definition:regex:regexCharToItem";
	}

	public static silver.definition.regex.NRegexItem invoke(final Object c_ch) {
		try {
		final common.DecoratedNode context = new PregexCharToItem(c_ch).decorate();
		//regexCharItem(if isAlpha(ch) || isDigit(ch) then regexChar(terminal(RegexChar_t, ch, core:loc("??", -1, -1, -1, -1, -1, -1))) else regexEscapedChar(terminal(EscapedChar_t, "\\" ++ ch, core:loc("??", -1, -1, -1, -1, -1, -1))))
		return (silver.definition.regex.NRegexItem)(((silver.definition.regex.NRegexItem)new silver.definition.regex.PregexCharItem(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((((Boolean)core.PisAlpha.invoke(context.childAsIsLazy(silver.definition.regex.PregexCharToItem.i_ch))) || ((Boolean)core.PisDigit.invoke(context.childAsIsLazy(silver.definition.regex.PregexCharToItem.i_ch)))) ? ((silver.definition.regex.NRegexChar)new silver.definition.regex.PregexChar(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.regex.TRegexChar_t(((common.StringCatter)context.childAsIs(silver.definition.regex.PregexCharToItem.i_ch)), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } })) : ((silver.definition.regex.NRegexChar)new silver.definition.regex.PregexEscapedChar(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.regex.TEscapedChar_t(new common.StringCatter((common.StringCatter)(new common.StringCatter("\\")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.regex.PregexCharToItem.i_ch))), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:regex:regexCharToItem", t);
		}
	}

	public static final common.NodeFactory<silver.definition.regex.NRegexItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.regex.NRegexItem> {
		@Override
		public silver.definition.regex.NRegexItem invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PregexCharToItem.invoke(children[0]);
		}
	};
}