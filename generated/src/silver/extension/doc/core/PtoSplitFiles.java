
package silver.extension.doc.core;

public final class PtoSplitFiles extends common.FunctionNode {

	public static final int i_comments = 0;
	public static final int i_sortedComments = 1;
	public static final int i_header = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_toSplitFiles;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoSplitFiles(final Object c_comments, final Object c_sortedComments, final Object c_header) {
		this.child_comments = c_comments;
		this.child_sortedComments = c_sortedComments;
		this.child_header = c_header;

	}

	private Object child_comments;
	public final common.ConsCell getChild_comments() {
		return (common.ConsCell) (child_comments = common.Util.demand(child_comments));
	}

	private Object child_sortedComments;
	public final common.ConsCell getChild_sortedComments() {
		return (common.ConsCell) (child_sortedComments = common.Util.demand(child_sortedComments));
	}

	private Object child_header;
	public final common.StringCatter getChild_header() {
		return (common.StringCatter) (child_header = common.Util.demand(child_header));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_comments: return getChild_comments();
			case i_sortedComments: return getChild_sortedComments();
			case i_header: return getChild_header();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_comments: return child_comments;
			case i_sortedComments: return child_sortedComments;
			case i_header: return child_header;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:extension:doc:core:toSplitFiles";
	}

	public static common.ConsCell invoke(final Object c_comments, final Object c_sortedComments, final Object c_header) {
		try {
		final common.DecoratedNode context = new PtoSplitFiles(c_comments, c_sortedComments, c_header).decorate();
		//case comments of c::rest -> toSplitFiles(rest, placeComment(c, sortedComments, header), header) | [] -> (pair("index.md", makeIndexFile(sortedComments, header)) :: sortedComments) end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_11275___sv_pv_11276_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_11277___sv_pv_11274_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11278_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_11277___sv_pv_11274_rest.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11279_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)(__SV_LOCAL_11275___sv_pv_11276_c.eval())); } };
return ((common.ConsCell)silver.extension.doc.core.PtoSplitFiles.invoke(__SV_LOCAL_11278_rest, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.doc.core.PplaceComment.invoke(__SV_LOCAL_11279_c, context.childAsIsLazy(silver.extension.doc.core.PtoSplitFiles.i_sortedComments), context.childAsIsLazy(silver.extension.doc.core.PtoSplitFiles.i_header))); } }, context.childAsIsLazy(silver.extension.doc.core.PtoSplitFiles.i_header))); } }).eval()); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("index.md")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.doc.core.PmakeIndexFile.invoke(context.childAsIsLazy(silver.extension.doc.core.PtoSplitFiles.i_sortedComments), context.childAsIsLazy(silver.extension.doc.core.PtoSplitFiles.i_header))); } })); } }, context.childAsIsLazy(silver.extension.doc.core.PtoSplitFiles.i_sortedComments))); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'RootSpec.sv', 34, 9, 37, 11, 730, 918\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.extension.doc.core.PtoSplitFiles.i_comments))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:core:toSplitFiles", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoSplitFiles.invoke(children[0], children[1], children[2]);
		}
	};
}