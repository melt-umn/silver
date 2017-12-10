
package patt;

public final class PtypeEquals extends common.FunctionNode {

	public static final int i_ta = 0;
	public static final int i_tb = 1;


	public static final Class<?> childTypes[] = { patt.NType.class,patt.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_typeEquals;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ta] = new common.Lazy[patt.NType.num_inh_attrs];
	childInheritedAttributes[i_tb] = new common.Lazy[patt.NType.num_inh_attrs];

	}

	public PtypeEquals(final Object c_ta, final Object c_tb) {
		this.child_ta = c_ta;
		this.child_tb = c_tb;

	}

	private Object child_ta;
	public final patt.NType getChild_ta() {
		return (patt.NType) (child_ta = common.Util.demand(child_ta));
	}

	private Object child_tb;
	public final patt.NType getChild_tb() {
		return (patt.NType) (child_tb = common.Util.demand(child_tb));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ta: return getChild_ta();
			case i_tb: return getChild_tb();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ta: return child_ta;
			case i_tb: return child_tb;

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
		return "patt:typeEquals";
	}

	public static core.NMaybe invoke(final Object c_ta, final Object c_tb) {
		try {
		final common.DecoratedNode context = new PtypeEquals(c_ta, c_tb).decorate();
		//match ta return Maybe<Eq<a b>> with unitT() -> match tb return Maybe<Eq<a b>> with unitT() -> just(eq(,),) else -> nothing(,)end | arrow(aa, ab) -> match tb return Maybe<Eq<a b>> with arrow(ba, bb) -> match decorate typeEquals(aa, ba,) with {} return Maybe<Eq<a b>> with just(lrn1) -> match decorate lrn1 with {} return Maybe<Eq<a b>> with eq() -> match decorate typeEquals(ab, bb,) with {} return Maybe<Eq<a b>> with just(lrn2) -> match decorate lrn2 with {} return Maybe<Eq<a b>> with eq() -> just(eq(,),) else -> nothing(,)end else -> nothing(,)end else -> nothing(,)end else -> nothing(,)end else -> nothing(,)end else -> nothing(,)end
		return (core.NMaybe)(new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.PunitT) {  return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.PunitT) {  return (core.NMaybe)((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NEq)new patt.Peq()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)new core.Pnothing());}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PtypeEquals.i_tb)); }
else if(scrutineeNode instanceof patt.Parrow) { final common.Thunk<Object> __SV_LOCAL___pv560_aa = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv561_ab = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Parrow) { final common.Thunk<Object> __SV_LOCAL___pv565_ba = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv566_bb = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv567_lrn1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (patt.NEq)scrutinee.childAsIs(0); } };
 return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Peq) {  return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv568_lrn2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (patt.NEq)scrutinee.childAsIs(0); } };
 return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Peq) {  return (core.NMaybe)((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NEq)new patt.Peq()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)new core.Pnothing());}}.eval(context, (common.DecoratedNode)((patt.NEq)(__SV_LOCAL___pv568_lrn2.eval())).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)new core.Pnothing());}}.eval(context, (common.DecoratedNode)((core.NMaybe)patt.PtypeEquals.invoke(common.Thunk.transformUndecorate(__SV_LOCAL___pv561_ab), common.Thunk.transformUndecorate(__SV_LOCAL___pv566_bb))).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)new core.Pnothing());}}.eval(context, (common.DecoratedNode)((patt.NEq)(__SV_LOCAL___pv567_lrn1.eval())).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)new core.Pnothing());}}.eval(context, (common.DecoratedNode)((core.NMaybe)patt.PtypeEquals.invoke(common.Thunk.transformUndecorate(__SV_LOCAL___pv560_aa), common.Thunk.transformUndecorate(__SV_LOCAL___pv565_ba))).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)new core.Pnothing());}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PtypeEquals.i_tb)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)new core.Pnothing());}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PtypeEquals.i_ta)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:typeEquals", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtypeEquals.invoke(children[0], children[1]);
		}
	};
}