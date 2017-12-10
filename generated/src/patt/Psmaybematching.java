
package patt;

public final class Psmaybematching extends common.FunctionNode {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = { core.NMaybe.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_smaybematching;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_s] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public Psmaybematching(final Object c_s) {
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
		return "patt:smaybematching";
	}

	public static Boolean invoke(final Object c_s) {
		try {
		final common.DecoratedNode context = new Psmaybematching(c_s).decorate();
		//case s of just("true") -> true | just("false") -> false | nothing() -> false end
		return (Boolean)(new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv439___sv_tmp_pv_438 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (Boolean)new common.PatternLazy<common.StringCatter, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.StringCatter scrutineeIter) {final common.StringCatter scrutinee = scrutineeIter; if(scrutinee.equals("false")) { return (Boolean)false; }
else if(scrutinee.equals("true")) { return (Boolean)true; }return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Normal.sv', 27, 9, 31, 12, 512, 630\n"))));}}.eval(context, (common.StringCatter)((common.StringCatter)(__SV_LOCAL___pv439___sv_tmp_pv_438.eval()))); }
else if(scrutineeNode instanceof core.Pnothing) {  return (Boolean)false; }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Normal.sv', 27, 9, 31, 12, 512, 630\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.Psmaybematching.i_s)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:smaybematching", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Psmaybematching.invoke(children[0]);
		}
	};
}