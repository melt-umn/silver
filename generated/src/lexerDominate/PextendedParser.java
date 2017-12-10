
package lexerDominate;

public final class PextendedParser extends common.FunctionNode {

	public static final int i_stringToParse = 0;
	public static final int i_filenameToReport = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__lexerDominate_extendedParser;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PextendedParser(final Object c_stringToParse, final Object c_filenameToReport) {
		this.child_stringToParse = c_stringToParse;
		this.child_filenameToReport = c_filenameToReport;

	}

	private Object child_stringToParse;
	public final common.StringCatter getChild_stringToParse() {
		return (common.StringCatter) (child_stringToParse = common.Util.demand(child_stringToParse));
	}

	private Object child_filenameToReport;
	public final common.StringCatter getChild_filenameToReport() {
		return (common.StringCatter) (child_filenameToReport = common.Util.demand(child_filenameToReport));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_stringToParse: return getChild_stringToParse();
			case i_filenameToReport: return getChild_filenameToReport();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_stringToParse: return child_stringToParse;
			case i_filenameToReport: return child_filenameToReport;

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
		return "lexerDominate:extendedParser";
	}

	public static core.NParseResult invoke(final Object c_stringToParse, final Object c_filenameToReport) {
		try {
		return common.Util.callCopperParser(new lexerDominate.Parser_lexerDominate_extendedParser(), c_stringToParse, c_filenameToReport);
		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lexerDominate:extendedParser", t);
		}
	}

	public static final common.NodeFactory<core.NParseResult> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NParseResult> {
		@Override
		public core.NParseResult invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PextendedParser.invoke(children[0], children[1]);
		}
	};
}