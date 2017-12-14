
package silver.extension.doc.core;

public final class PmakeIndexFile extends common.FunctionNode {

	public static final int i_sortedComments = 0;
	public static final int i_header = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_makeIndexFile;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmakeIndexFile(final Object c_sortedComments, final Object c_header) {
		this.child_sortedComments = c_sortedComments;
		this.child_header = c_header;

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
			case i_sortedComments: return getChild_sortedComments();
			case i_header: return getChild_header();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sortedComments: return child_sortedComments;
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
		return "silver:extension:doc:core:makeIndexFile";
	}

	public static common.StringCatter invoke(final Object c_sortedComments, final Object c_header) {
		try {
		final common.DecoratedNode context = new PmakeIndexFile(c_sortedComments, c_header).decorate();
		//case sortedComments of pair(f, _)::rest -> makeIndexFile(rest, header) ++ "\n" ++ f ++ "\n" | [] -> header end
		return (common.StringCatter)(new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_11316___sv_tmp_pv_11315 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_11317___sv_pv_11318_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Ppair) { final common.Thunk<Object> __SV_LOCAL___pv11320___sv_pv_11321_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv11322___sv_tmp_pv_11323 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11324_rest = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_11317___sv_pv_11318_rest.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11325_f = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv11320___sv_pv_11321_f.eval())); } };
return new common.StringCatter((common.StringCatter)((common.StringCatter)silver.extension.doc.core.PmakeIndexFile.invoke(__SV_LOCAL_11324_rest, context.childAsIsLazy(silver.extension.doc.core.PmakeIndexFile.i_header))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11325_f.eval())), (common.StringCatter)(new common.StringCatter("\n"))))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'RootSpec.sv', 55, 9, 58, 11, 1536, 1653\n"))));}}.eval(context, (common.DecoratedNode)((core.NPair)(__SV_LOCAL_11316___sv_tmp_pv_11315.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee.nil()) { return (common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.doc.core.PmakeIndexFile.i_header)); }return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'RootSpec.sv', 55, 9, 58, 11, 1536, 1653\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.extension.doc.core.PmakeIndexFile.i_sortedComments))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:core:makeIndexFile", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeIndexFile.invoke(children[0], children[1]);
		}
	};
}