
package core.monad;

public final class PbindMaybe extends common.FunctionNode {

	public static final int i_m = 0;
	public static final int i_fn = 1;


	public static final Class<?> childTypes[] = { core.NMaybe.class,common.NodeFactory.class };

	public static final int num_local_attrs = Init.count_local__ON__core_monad_bindMaybe;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_m] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PbindMaybe(final Object c_m, final Object c_fn) {
		this.child_m = c_m;
		this.child_fn = c_fn;

	}

	private Object child_m;
	public final core.NMaybe getChild_m() {
		return (core.NMaybe) (child_m = common.Util.demand(child_m));
	}

	private Object child_fn;
	public final common.NodeFactory<core.NMaybe> getChild_fn() {
		return (common.NodeFactory<core.NMaybe>) (child_fn = common.Util.demand(child_fn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_m: return getChild_m();
			case i_fn: return getChild_fn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_m: return child_m;
			case i_fn: return child_fn;

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
		return "core:monad:bindMaybe";
	}

	public static core.NMaybe invoke(final Object c_m, final Object c_fn) {
		try {
		final common.DecoratedNode context = new PbindMaybe(c_m, c_fn).decorate();
		//case m of just(x) -> fn(x) | nothing() -> nothing() end
		return (core.NMaybe)(new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv5402___sv_pv_5401_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5403_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv5402___sv_pv_5401_x.eval())); } };
return ((core.NMaybe)((common.NodeFactory<core.NMaybe>)context.childAsIs(core.monad.PbindMaybe.i_fn)).invoke(new Object[]{__SV_LOCAL_5403_x}, null)); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (core.NMaybe)((core.NMaybe)new core.Pnothing()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at core:monad 'Maybe.sv', 6, 9, 9, 5, 97, 160\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(core.monad.PbindMaybe.i_m)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:monad:bindMaybe", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbindMaybe.invoke(children[0], children[1]);
		}
	};
}