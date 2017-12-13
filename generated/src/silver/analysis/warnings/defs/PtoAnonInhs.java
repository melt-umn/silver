
package silver.analysis.warnings.defs;

public final class PtoAnonInhs extends common.FunctionNode {

	public static final int i_v = 0;
	public static final int i_vertex = 1;
	public static final int i_env = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_toAnonInhs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoAnonInhs(final Object c_v, final Object c_vertex, final Object c_env) {
		this.child_v = c_v;
		this.child_vertex = c_vertex;
		this.child_env = c_env;

	}

	private Object child_v;
	public final common.ConsCell getChild_v() {
		return (common.ConsCell) (child_v = common.Util.demand(child_v));
	}

	private Object child_vertex;
	public final common.StringCatter getChild_vertex() {
		return (common.StringCatter) (child_vertex = common.Util.demand(child_vertex));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();
			case i_vertex: return getChild_vertex();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;
			case i_vertex: return child_vertex;
			case i_env: return child_env;

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
		return "silver:analysis:warnings:defs:toAnonInhs";
	}

	public static common.ConsCell invoke(final Object c_v, final Object c_vertex, final Object c_env) {
		try {
		final common.DecoratedNode context = new PtoAnonInhs(c_v, c_vertex, c_env).decorate();
		//case v of anonVertex(n, inh)::tl -> if vertex == n && isInherited(inh, env) then (inh :: toAnonInhs(tl, vertex, env)) else toAnonInhs(tl, vertex, env) | _::tl -> toAnonInhs(tl, vertex, env) | [] -> [] end
		return (common.ConsCell)(new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_919___sv_tmp_pv_920 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_921___sv_pv_918_tl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_923___fail_924 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_922_tl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_921___sv_pv_918_tl.eval())); } };
return ((common.ConsCell)silver.analysis.warnings.defs.PtoAnonInhs.invoke(__SV_LOCAL_922_tl, context.childAsIsLazy(silver.analysis.warnings.defs.PtoAnonInhs.i_vertex), context.childAsIsLazy(silver.analysis.warnings.defs.PtoAnonInhs.i_env))); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.flow.ast.PanonVertex) { final common.Thunk<Object> __SV_LOCAL___pv928___sv_pv_929_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv930___sv_pv_931_inh = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_932_tl = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_921___sv_pv_918_tl.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_933_inh = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv930___sv_pv_931_inh.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_934_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv928___sv_pv_929_n.eval())); } };
return ((((common.StringCatter)context.childAsIs(silver.analysis.warnings.defs.PtoAnonInhs.i_vertex)).equals(((common.StringCatter)(__SV_LOCAL_934_n.eval()))) && ((Boolean)silver.analysis.warnings.defs.PisInherited.invoke(__SV_LOCAL_933_inh, context.childAsIsLazy(silver.analysis.warnings.defs.PtoAnonInhs.i_env)))) ? ((common.ConsCell)core.Pcons.invoke(__SV_LOCAL_933_inh, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.analysis.warnings.defs.PtoAnonInhs.invoke(__SV_LOCAL_932_tl, context.childAsIsLazy(silver.analysis.warnings.defs.PtoAnonInhs.i_vertex), context.childAsIsLazy(silver.analysis.warnings.defs.PtoAnonInhs.i_env))); } })) : ((common.ConsCell)silver.analysis.warnings.defs.PtoAnonInhs.invoke(__SV_LOCAL_932_tl, context.childAsIsLazy(silver.analysis.warnings.defs.PtoAnonInhs.i_vertex), context.childAsIsLazy(silver.analysis.warnings.defs.PtoAnonInhs.i_env)))); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_923___fail_924.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.flow.ast.NFlowVertex)(__SV_LOCAL_919___sv_tmp_pv_920.eval())).decorate(context, (common.Lazy[])null)); } }).eval()); }
else if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:analysis:warnings:defs 'Inh.sv', 663, 4, 670, 7, 29227, 29475\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.analysis.warnings.defs.PtoAnonInhs.i_v))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:toAnonInhs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoAnonInhs.invoke(children[0], children[1], children[2]);
		}
	};
}