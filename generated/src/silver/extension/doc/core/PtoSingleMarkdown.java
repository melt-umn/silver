
package silver.extension.doc.core;

public final class PtoSingleMarkdown extends common.FunctionNode {

	public static final int i_comments = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_toSingleMarkdown;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoSingleMarkdown(final Object c_comments) {
		this.child_comments = c_comments;

	}

	private Object child_comments;
	public final common.ConsCell getChild_comments() {
		return (common.ConsCell) (child_comments = common.Util.demand(child_comments));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_comments: return getChild_comments();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_comments: return child_comments;

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
		return "silver:extension:doc:core:toSingleMarkdown";
	}

	public static common.StringCatter invoke(final Object c_comments) {
		try {
		final common.DecoratedNode context = new PtoSingleMarkdown(c_comments).decorate();
		//case comments of c::rest -> toMarkdown(c) ++ toSingleMarkdown(rest) | [] -> "" end
		return (common.StringCatter)(new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_11331___sv_pv_11332_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_11333___sv_pv_11330_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11334_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_11333___sv_pv_11330_rest.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11335_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.doc.core.NCommentItem)(__SV_LOCAL_11331___sv_pv_11332_c.eval())); } };
return new common.StringCatter((common.StringCatter)((common.StringCatter)silver.extension.doc.core.PtoMarkdown.invoke(__SV_LOCAL_11335_c)), (common.StringCatter)((common.StringCatter)silver.extension.doc.core.PtoSingleMarkdown.invoke(__SV_LOCAL_11334_rest))); } }).eval()); } }).eval()); }
else if(scrutinee.nil()) { return (common.StringCatter)(new common.StringCatter("")); }return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'RootSpec.sv', 72, 9, 75, 12, 1937, 2029\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.extension.doc.core.PtoSingleMarkdown.i_comments))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:core:toSingleMarkdown", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoSingleMarkdown.invoke(children[0]);
		}
	};
}