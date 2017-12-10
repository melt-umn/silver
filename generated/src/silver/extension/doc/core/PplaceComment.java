
package silver.extension.doc.core;

public final class PplaceComment extends common.FunctionNode {

	public static final int i_comment = 0;
	public static final int i_sortedComments = 1;
	public static final int i_header = 2;


	public static final Class<?> childTypes[] = { silver.extension.doc.core.NCommentItem.class,common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_placeComment;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_comment] = new common.Lazy[silver.extension.doc.core.NCommentItem.num_inh_attrs];

	}

	public PplaceComment(final Object c_comment, final Object c_sortedComments, final Object c_header) {
		this.child_comment = c_comment;
		this.child_sortedComments = c_sortedComments;
		this.child_header = c_header;

	}

	private Object child_comment;
	public final silver.extension.doc.core.NCommentItem getChild_comment() {
		return (silver.extension.doc.core.NCommentItem) (child_comment = common.Util.demand(child_comment));
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
			case i_comment: return getChild_comment();
			case i_sortedComments: return getChild_sortedComments();
			case i_header: return getChild_header();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_comment: return child_comment;
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
		return "silver:extension:doc:core:placeComment";
	}

	public static common.ConsCell invoke(final Object c_comment, final Object c_sortedComments, final Object c_header) {
		try {
		final common.DecoratedNode context = new PplaceComment(c_comment, c_sortedComments, c_header).decorate();
		//case sortedComments of pair(filename, contents)::rest -> if filename == toMarkdownExtension(comment.file) then (pair(filename, contents ++ markdown) :: rest) else (pair(filename, contents) :: placeComment(comment, rest, header)) | [] -> [ pair(toMarkdownExtension(comment.file), header ++ markdown) ] end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_11269___sv_tmp_pv_11268 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_11270___sv_pv_11271_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Ppair) { final common.Thunk<Object> __SV_LOCAL___pv11273___sv_pv_11274_filename = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv11275___sv_pv_11276_contents = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11277_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_11270___sv_pv_11271_rest.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11278_contents = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11275___sv_pv_11276_contents.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11279_filename = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11273___sv_pv_11274_filename.eval())); } };
return (((common.StringCatter)(__SV_LOCAL_11279_filename.eval())).equals(((common.StringCatter)silver.extension.doc.core.PtoMarkdownExtension.invoke(context.childDecoratedSynthesizedLazy(silver.extension.doc.core.PplaceComment.i_comment, silver.extension.doc.core.Init.silver_extension_doc_core_file__ON__silver_extension_doc_core_CommentItem)))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(__SV_LOCAL_11279_filename, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11278_contents.eval())), (common.StringCatter)((common.StringCatter)context.localAsIs(silver.extension.doc.core.Init.markdown__ON__silver_extension_doc_core_placeComment))); } })); } }, __SV_LOCAL_11277_rest)) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(__SV_LOCAL_11279_filename, __SV_LOCAL_11278_contents)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.doc.core.PplaceComment.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.doc.core.PplaceComment.i_comment)), __SV_LOCAL_11277_rest, context.childAsIsLazy(silver.extension.doc.core.PplaceComment.i_header))); } }))); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'RootSpec.sv', 44, 9, 49, 11, 1104, 1433\n"))));}}.eval(context, (common.DecoratedNode)((core.NPair)(__SV_LOCAL_11269___sv_tmp_pv_11268.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.doc.core.PtoMarkdownExtension.invoke(context.childDecoratedSynthesizedLazy(silver.extension.doc.core.PplaceComment.i_comment, silver.extension.doc.core.Init.silver_extension_doc_core_file__ON__silver_extension_doc_core_CommentItem))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.doc.core.PplaceComment.i_header)), (common.StringCatter)((common.StringCatter)context.localAsIs(silver.extension.doc.core.Init.markdown__ON__silver_extension_doc_core_placeComment))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'RootSpec.sv', 44, 9, 49, 11, 1104, 1433\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.extension.doc.core.PplaceComment.i_sortedComments))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:core:placeComment", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PplaceComment.invoke(children[0], children[1], children[2]);
		}
	};
}