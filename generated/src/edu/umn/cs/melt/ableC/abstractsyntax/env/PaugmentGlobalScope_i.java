
package edu.umn.cs.melt.ableC.abstractsyntax.env;

public final class PaugmentGlobalScope_i extends common.FunctionNode {

	public static final int i_d = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_augmentGlobalScope_i;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PaugmentGlobalScope_i(final Object c_d, final Object c_s) {
		this.child_d = c_d;
		this.child_s = c_s;

	}

	private Object child_d;
	public final common.ConsCell getChild_d() {
		return (common.ConsCell) (child_d = common.Util.demand(child_d));
	}

	private Object child_s;
	public final common.ConsCell getChild_s() {
		return (common.ConsCell) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;
			case i_s: return child_s;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:env:augmentGlobalScope_i";
	}

	public static common.ConsCell invoke(final Object c_d, final Object c_s) {
		try {
		final common.DecoratedNode context = new PaugmentGlobalScope_i(c_d, c_s).decorate();
		//case d, s of [], _ -> s | _, [_] -> augmentScope_i(d, s,) | _, h::t -> (h :: augmentGlobalScope_i(d, t,)) end
		return (common.ConsCell)(((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_834___fail_835 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_827___sv_tmp_pv_828 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_829___sv_tmp_pv_826 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_832___fail_833 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_830_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_829___sv_tmp_pv_826.eval())); } };
return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_831_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL_827___sv_tmp_pv_828.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(__SV_LOCAL_831_h, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.i_d), __SV_LOCAL_830_t)); } })); } }).eval()); } }).eval()); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentScope_i.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.i_d), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.i_s))); }return ((common.ConsCell)(__SV_LOCAL_832___fail_833.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL_829___sv_tmp_pv_826.eval()))); } }).eval()); }return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:env 'EnvPlumbing.sv', 39, 9, 43, 7, 1585, 1710\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.i_s))); } };
return new common.PatternLazy<common.ConsCell, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(scrutinee.nil()) { return (common.ConsCell)((common.ConsCell)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.i_s)); }return ((common.ConsCell)(__SV_LOCAL_834___fail_835.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.env.PaugmentGlobalScope_i.i_d))); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:env:augmentGlobalScope_i", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaugmentGlobalScope_i.invoke(children[0], children[1]);
		}
	};
}