
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

public final class PapplyMaybe extends common.FunctionNode {

	public static final int i_maybeFn = 0;
	public static final int i_a = 1;


	public static final Class<?> childTypes[] = { core.NMaybe.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_applyMaybe;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_maybeFn] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PapplyMaybe(final Object c_maybeFn, final Object c_a) {
		this.child_maybeFn = c_maybeFn;
		this.child_a = c_a;

	}

	private Object child_maybeFn;
	public final core.NMaybe getChild_maybeFn() {
		return (core.NMaybe) (child_maybeFn = common.Util.demand(child_maybeFn));
	}

	private Object child_a;
	public final Object getChild_a() {
		return (Object) (child_a = common.Util.demand(child_a));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_maybeFn: return getChild_maybeFn();
			case i_a: return getChild_a();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_maybeFn: return child_maybeFn;
			case i_a: return child_a;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:applyMaybe";
	}

	public static core.NMaybe invoke(final Object c_maybeFn, final Object c_a) {
		try {
		final common.DecoratedNode context = new PapplyMaybe(c_maybeFn, c_a).decorate();
		//case maybeFn of just(fn) -> just(fn(a,),) | nothing() -> nothing(,) end
		return (core.NMaybe)(new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv904___sv_pv_903_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<Object>)scrutinee.childAsIs(0); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_905_fn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.NodeFactory<Object>)(__SV_LOCAL___pv904___sv_pv_903_fn.eval())); } };
return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((common.NodeFactory<Object>)(__SV_LOCAL_905_fn.eval())).invoke(new Object[]{context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe.i_a)}, null)); } })); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (core.NMaybe)((core.NMaybe)new core.Pnothing()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:overloadable 'OpOverload.sv', 657, 4, 660, 7, 25117, 25199\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe.i_maybeFn)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:overloadable:applyMaybe", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PapplyMaybe.invoke(children[0], children[1]);
		}
	};
}