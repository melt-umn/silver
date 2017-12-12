
package silver.extension.doc.core;

public final class PtoSingleFile extends common.FunctionNode {

	public static final int i_comments = 0;
	public static final int i_header = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_toSingleFile;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoSingleFile(final Object c_comments, final Object c_header) {
		this.child_comments = c_comments;
		this.child_header = c_header;

	}

	private Object child_comments;
	public final common.ConsCell getChild_comments() {
		return (common.ConsCell) (child_comments = common.Util.demand(child_comments));
	}

	private Object child_header;
	public final common.StringCatter getChild_header() {
		return (common.StringCatter) (child_header = common.Util.demand(child_header));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_comments: return getChild_comments();
			case i_header: return getChild_header();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_comments: return child_comments;
			case i_header: return child_header;

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
		return "silver:extension:doc:core:toSingleFile";
	}

	public static core.NPair invoke(final Object c_comments, final Object c_header) {
		try {
		final common.DecoratedNode context = new PtoSingleFile(c_comments, c_header).decorate();
		//pair("index.md", header ++ commentMarkdown)
		return (core.NPair)(((core.NPair)new core.Ppair((new common.StringCatter("index.md")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.doc.core.PtoSingleFile.i_header)), (common.StringCatter)((common.StringCatter)context.localAsIs(silver.extension.doc.core.Init.commentMarkdown__ON__silver_extension_doc_core_toSingleFile))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:core:toSingleFile", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoSingleFile.invoke(children[0], children[1]);
		}
	};
}