
package silver.support.monto.negotiation;

public final class PpairMaybe extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_f = 1;
	public static final int i_b = 2;


	public static final Class<?> childTypes[] = { Object.class,common.NodeFactory.class,core.NMaybe.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_negotiation_pairMaybe;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_b] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PpairMaybe(final Object c_a, final Object c_f, final Object c_b) {
		this.child_a = c_a;
		this.child_f = c_f;
		this.child_b = c_b;

	}

	private Object child_a;
	public final Object getChild_a() {
		return (Object) (child_a = common.Util.demand(child_a));
	}

	private Object child_f;
	public final common.NodeFactory<Object> getChild_f() {
		return (common.NodeFactory<Object>) (child_f = common.Util.demand(child_f));
	}

	private Object child_b;
	public final core.NMaybe getChild_b() {
		return (core.NMaybe) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_f: return getChild_f();
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_f: return child_f;
			case i_b: return child_b;

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
		return "silver:support:monto:negotiation:pairMaybe";
	}

	public static core.NMaybe invoke(final Object c_a, final Object c_f, final Object c_b) {
		try {
		final common.DecoratedNode context = new PpairMaybe(c_a, c_f, c_b).decorate();
		//case b of just(x) -> just(pair(a, f(x))) | nothing() -> nothing() end
		return (core.NMaybe)(new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv149___sv_pv_148_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_150_x = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv149___sv_pv_148_x.eval())); } };
return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.support.monto.negotiation.PpairMaybe.i_a), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((common.NodeFactory<Object>)context.childAsIs(silver.support.monto.negotiation.PpairMaybe.i_f)).invoke(new Object[]{__SV_LOCAL_150_x}, null)); } })); } })); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (core.NMaybe)((core.NMaybe)new core.Pnothing()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:support:monto:negotiation 'Versions.sv', 42, 9, 45, 5, 1232, 1309\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.support.monto.negotiation.PpairMaybe.i_b)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:support:monto:negotiation:pairMaybe", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpairMaybe.invoke(children[0], children[1], children[2]);
		}
	};
}