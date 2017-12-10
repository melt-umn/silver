
package patt;

public final class Pbasic1 extends common.FunctionNode {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = { core.NMaybe.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_basic1;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_s] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public Pbasic1(final Object c_s) {
		this.child_s = c_s;

	}

	private Object child_s;
	public final core.NMaybe getChild_s() {
		return (core.NMaybe) (child_s = common.Util.demand(child_s));
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
		return "patt:basic1";
	}

	public static Integer invoke(final Object c_s) {
		try {
		final common.DecoratedNode context = new Pbasic1(c_s).decorate();
		//case s of just(v) -> 1 | nothing() -> 2 end
		return (Integer)(new common.PatternLazy<common.DecoratedNode, Integer>() { public final Integer eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv575___sv_pv_574_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(0); } };
 return (Integer)((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2412_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)(__SV_LOCAL___pv575___sv_pv_574_v.eval())); } };
return Integer.valueOf((int)1); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (Integer)Integer.valueOf((int)2); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Integer)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 6, 9, 9, 12, 89, 159\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.Pbasic1.i_s)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:basic1", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pbasic1.invoke(children[0]);
		}
	};
}