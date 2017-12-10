
package core;

public final class PmapMaybe extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_m = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,core.NMaybe.class };

	public static final int num_local_attrs = Init.count_local__ON__core_mapMaybe;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_m] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PmapMaybe(final Object c_f, final Object c_m) {
		this.child_f = c_f;
		this.child_m = c_m;

	}

	private Object child_f;
	public final common.NodeFactory<Object> getChild_f() {
		return (common.NodeFactory<Object>) (child_f = common.Util.demand(child_f));
	}

	private Object child_m;
	public final core.NMaybe getChild_m() {
		return (core.NMaybe) (child_m = common.Util.demand(child_m));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_m: return getChild_m();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_m: return child_m;

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
		return "core:mapMaybe";
	}

	public static core.NMaybe invoke(final Object c_f, final Object c_m) {
		try {
		final common.DecoratedNode context = new PmapMaybe(c_f, c_m).decorate();
		//case m of just(x) -> just(f(x)) | nothing() -> nothing() end
		return (core.NMaybe)(new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv4529___sv_pv_4528_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4530_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv4529___sv_pv_4528_x.eval())); } };
return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((common.NodeFactory<Object>)context.childAsIs(core.PmapMaybe.i_f)).invoke(new Object[]{__SV_LOCAL_4530_x}, null)); } })); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (core.NMaybe)((core.NMaybe)new core.Pnothing()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at core 'Maybe.sv', 82, 9, 85, 5, 1896, 1966\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(core.PmapMaybe.i_m)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:mapMaybe", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmapMaybe.invoke(children[0], children[1]);
		}
	};
}