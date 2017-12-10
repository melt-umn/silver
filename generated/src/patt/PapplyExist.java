
package patt;

public final class PapplyExist extends common.FunctionNode {

	public static final int i_v = 0;


	public static final Class<?> childTypes[] = { patt.NExistential.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_applyExist;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_v] = new common.Lazy[patt.NExistential.num_inh_attrs];

	}

	public PapplyExist(final Object c_v) {
		this.child_v = c_v;

	}

	private Object child_v;
	public final patt.NExistential getChild_v() {
		return (patt.NExistential) (child_v = common.Util.demand(child_v));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;

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
		return "patt:applyExist";
	}

	public static common.StringCatter invoke(final Object c_v) {
		try {
		final common.DecoratedNode context = new PapplyExist(c_v).decorate();
		//case v of existentialprod(arg, fun) -> fun(arg,) end
		return (common.StringCatter)(new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Pexistentialprod) { final common.Thunk<Object> __SV_LOCAL___pv751___sv_pv_752_arg = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv753___sv_pv_750_fun = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<common.StringCatter>)scrutinee.childAsIs(1); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_754_fun = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.NodeFactory<common.StringCatter>)(__SV_LOCAL___pv753___sv_pv_750_fun.eval())); } };
return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_755_arg = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv751___sv_pv_752_arg.eval())); } };
return ((common.StringCatter)((common.NodeFactory<common.StringCatter>)(__SV_LOCAL_754_fun.eval())).invoke(new Object[]{__SV_LOCAL_755_arg}, null)); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Existential.sv', 13, 9, 15, 12, 188, 259\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PapplyExist.i_v)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:applyExist", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PapplyExist.invoke(children[0]);
		}
	};
}