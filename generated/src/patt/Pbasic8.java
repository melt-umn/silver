
package patt;

public final class Pbasic8 extends common.FunctionNode {

	public static final int i_p = 0;


	public static final Class<?> childTypes[] = { core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_basic8;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_p] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public Pbasic8(final Object c_p) {
		this.child_p = c_p;

	}

	private Object child_p;
	public final core.NPair getChild_p() {
		return (core.NPair) (child_p = common.Util.demand(child_p));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;

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
		return "patt:basic8";
	}

	public static Integer invoke(final Object c_p) {
		try {
		final common.DecoratedNode context = new Pbasic8(c_p).decorate();
		//case p of pair(1, 2) -> 1 | core:pair(1, _) -> 2 | pair(2, 1) -> 3 | core:pair(_, 1) -> 4 end
		return (Integer)(new common.PatternLazy<common.DecoratedNode, Integer>() { public final Integer eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Ppair) { final common.Thunk<Object> __SV_LOCAL___pv737___sv_tmp_pv_738 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv739___sv_tmp_pv_736 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(1); } };
 return (Integer)((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_743___fail_744 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<Integer, Integer>() { public final Integer eval(final common.DecoratedNode context, Integer scrutineeIter) {final Integer scrutinee = scrutineeIter; if(scrutinee == 1) { return (Integer)Integer.valueOf((int)4); }return ((Integer)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 126, 7, 131, 3, 4421, 4512\n"))));}}.eval(context, (Integer)((Integer)(__SV_LOCAL___pv739___sv_tmp_pv_736.eval()))); } };
return new common.PatternLazy<Integer, Integer>() { public final Integer eval(final common.DecoratedNode context, Integer scrutineeIter) {final Integer scrutinee = scrutineeIter; if(scrutinee == 1) { return (Integer)((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_747___fail_748 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)2); } };
return new common.PatternLazy<Integer, Integer>() { public final Integer eval(final common.DecoratedNode context, Integer scrutineeIter) {final Integer scrutinee = scrutineeIter; if(scrutinee == 2) { return (Integer)Integer.valueOf((int)1); }return ((Integer)(__SV_LOCAL_747___fail_748.eval()));}}.eval(context, (Integer)((Integer)(__SV_LOCAL___pv739___sv_tmp_pv_736.eval()))); } }).eval()); }
else if(scrutinee == 2) { return (Integer)new common.PatternLazy<Integer, Integer>() { public final Integer eval(final common.DecoratedNode context, Integer scrutineeIter) {final Integer scrutinee = scrutineeIter; if(scrutinee == 1) { return (Integer)Integer.valueOf((int)3); }return ((Integer)(__SV_LOCAL_743___fail_744.eval()));}}.eval(context, (Integer)((Integer)(__SV_LOCAL___pv739___sv_tmp_pv_736.eval()))); }return ((Integer)(__SV_LOCAL_743___fail_744.eval()));}}.eval(context, (Integer)((Integer)(__SV_LOCAL___pv737___sv_tmp_pv_738.eval()))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Integer)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 126, 7, 131, 3, 4421, 4512\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.Pbasic8.i_p)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:basic8", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pbasic8.invoke(children[0]);
		}
	};
}