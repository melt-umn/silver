
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

public final class PapplyMaybe3 extends common.FunctionNode {

	public static final int i_maybeFn = 0;
	public static final int i_a1 = 1;
	public static final int i_a2 = 2;
	public static final int i_a3 = 3;


	public static final Class<?> childTypes[] = { core.NMaybe.class,Object.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe3;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_maybeFn] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PapplyMaybe3(final Object c_maybeFn, final Object c_a1, final Object c_a2, final Object c_a3) {
		this.child_maybeFn = c_maybeFn;
		this.child_a1 = c_a1;
		this.child_a2 = c_a2;
		this.child_a3 = c_a3;

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



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_maybeFn: return getChild_maybeFn();
			case i_a1: return getChild_a1();
			case i_a2: return getChild_a2();
			case i_a3: return getChild_a3();

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

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:applyMaybe3";
	}

	public static core.NMaybe invoke(final Object c_maybeFn, final Object c_a1, final Object c_a2, final Object c_a3) {
		try {
		final common.DecoratedNode context = new PapplyMaybe3(c_maybeFn, c_a1, c_a2, c_a3).decorate();
		//case maybeFn of just(fn) -> just(fn(a1, a2, a3,),) | nothing() -> nothing(,) end
		return (core.NMaybe)(new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv931___sv_pv_930_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<Object>)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_932_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.NodeFactory<Object>)(__SV_LOCAL___pv931___sv_pv_930_fn.eval())); } };
return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((common.NodeFactory<Object>)(__SV_LOCAL_932_fn.eval())).invoke(new Object[]{context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe3.i_a1), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe3.i_a2), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe3.i_a3)}, null)); } })); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (core.NMaybe)((core.NMaybe)new core.Pnothing()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:overloadable 'OpOverload.sv', 677, 4, 680, 7, 25482, 25573\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe3.i_maybeFn)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:overloadable:applyMaybe3", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PapplyMaybe3.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}