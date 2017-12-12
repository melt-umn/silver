
package silver.extension.doc.core;

public final class PnameToPathHelp extends common.FunctionNode {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_nameToPathHelp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PnameToPathHelp(final Object c_s) {
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.ConsCell getChild_s() {
		return (common.ConsCell) (child_s = common.Util.demand(child_s));
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
		return "silver:extension:doc:core:nameToPathHelp";
	}

	public static common.StringCatter invoke(final Object c_s) {
		try {
		final common.DecoratedNode context = new PnameToPathHelp(c_s).decorate();
		//case s of [] -> "" | x::[] -> "#" ++ x | x1::x2::[] -> x1 ++ "#" ++ x2 | x::xs -> x ++ "/" ++ nameToPathHelp(xs) end
		return (common.StringCatter)(new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_11659___sv_pv_11660_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_11661___sv_tmp_pv_11658 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11667___fail_11668 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11662_xs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_11661___sv_tmp_pv_11658.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11663_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL_11659___sv_pv_11660_x.eval())); } };
return new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11663_x.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/")), (common.StringCatter)((common.StringCatter)silver.extension.doc.core.PnameToPathHelp.invoke(__SV_LOCAL_11662_xs)))); } }).eval()); } }).eval()); } };
return new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_11670___sv_pv_11671_x2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_11672___sv_tmp_pv_11669 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.ConsCell, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(scrutinee.nil()) { return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11673_x2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL_11670___sv_pv_11671_x2.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11674_x1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL_11659___sv_pv_11660_x.eval())); } };
return new common.StringCatter((common.StringCatter)((common.StringCatter)(__SV_LOCAL_11674_x1.eval())), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("#")), (common.StringCatter)((common.StringCatter)(__SV_LOCAL_11673_x2.eval())))); } }).eval()); } }).eval()); }return ((common.StringCatter)(__SV_LOCAL_11667___fail_11668.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL_11672___sv_tmp_pv_11669.eval()))); }
else if(scrutinee.nil()) { return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11679_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL_11659___sv_pv_11660_x.eval())); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("#")), (common.StringCatter)((common.StringCatter)(__SV_LOCAL_11679_x.eval()))); } }).eval()); }return ((common.StringCatter)(__SV_LOCAL_11667___fail_11668.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL_11661___sv_tmp_pv_11658.eval()))); } }).eval()); }
else if(scrutinee.nil()) { return (common.StringCatter)(new common.StringCatter("")); }return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:doc:core 'Paths.sv', 12, 9, 17, 20, 188, 329\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.extension.doc.core.PnameToPathHelp.i_s))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:core:nameToPathHelp", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnameToPathHelp.invoke(children[0]);
		}
	};
}