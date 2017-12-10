
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

public final class PapplyMaybe5 extends common.FunctionNode {

	public static final int i_maybeFn = 0;
	public static final int i_a1 = 1;
	public static final int i_a2 = 2;
	public static final int i_a3 = 3;
	public static final int i_a4 = 4;
	public static final int i_a5 = 5;


	public static final Class<?> childTypes[] = { core.NMaybe.class,Object.class,Object.class,Object.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe5;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_maybeFn] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PapplyMaybe5(final Object c_maybeFn, final Object c_a1, final Object c_a2, final Object c_a3, final Object c_a4, final Object c_a5) {
		this.child_maybeFn = c_maybeFn;
		this.child_a1 = c_a1;
		this.child_a2 = c_a2;
		this.child_a3 = c_a3;
		this.child_a4 = c_a4;
		this.child_a5 = c_a5;

	}

	private Object child_maybeFn;
	public final core.NMaybe getChild_maybeFn() {
		return (core.NMaybe) (child_maybeFn = common.Util.demand(child_maybeFn));
	}

	private Object child_a1;
	public final Object getChild_a1() {
		return (Object) (child_a1 = common.Util.demand(child_a1));
	}

	private Object child_a2;
	public final Object getChild_a2() {
		return (Object) (child_a2 = common.Util.demand(child_a2));
	}

	private Object child_a3;
	public final Object getChild_a3() {
		return (Object) (child_a3 = common.Util.demand(child_a3));
	}

	private Object child_a4;
	public final Object getChild_a4() {
		return (Object) (child_a4 = common.Util.demand(child_a4));
	}

	private Object child_a5;
	public final Object getChild_a5() {
		return (Object) (child_a5 = common.Util.demand(child_a5));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_maybeFn: return getChild_maybeFn();
			case i_a1: return getChild_a1();
			case i_a2: return getChild_a2();
			case i_a3: return getChild_a3();
			case i_a4: return getChild_a4();
			case i_a5: return getChild_a5();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_maybeFn: return child_maybeFn;
			case i_a1: return child_a1;
			case i_a2: return child_a2;
			case i_a3: return child_a3;
			case i_a4: return child_a4;
			case i_a5: return child_a5;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:applyMaybe5";
	}

	public static core.NMaybe invoke(final Object c_maybeFn, final Object c_a1, final Object c_a2, final Object c_a3, final Object c_a4, final Object c_a5) {
		try {
		final common.DecoratedNode context = new PapplyMaybe5(c_maybeFn, c_a1, c_a2, c_a3, c_a4, c_a5).decorate();
		//case maybeFn of just(fn) -> just(fn(a1, a2, a3, a4, a5,),) | nothing() -> nothing(,) end
		return (core.NMaybe)(new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv962___sv_pv_961_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<Object>)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_963_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.NodeFactory<Object>)(__SV_LOCAL___pv962___sv_pv_961_fn.eval())); } };
return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((common.NodeFactory<Object>)(__SV_LOCAL_963_fn.eval())).invoke(new Object[]{context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe5.i_a1), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe5.i_a2), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe5.i_a3), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe5.i_a4), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe5.i_a5)}, null)); } })); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (core.NMaybe)((core.NMaybe)new core.Pnothing()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:overloadable 'OpOverload.sv', 697, 4, 700, 7, 25896, 25995\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe5.i_maybeFn)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:overloadable:applyMaybe5", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PapplyMaybe5.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}