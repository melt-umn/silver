
package patt;

public final class PretExpr extends common.FunctionNode {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = { patt.NExpr.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_retExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_e] = new common.Lazy[patt.NExpr.num_inh_attrs];

	}

	public PretExpr(final Object c_e) {
		this.child_e = c_e;

	}

	private Object child_e;
	public final patt.NExpr getChild_e() {
		return (patt.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;

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
		return "patt:retExpr";
	}

	public static Object invoke(final Object c_e) {
		try {
		final common.DecoratedNode context = new PretExpr(c_e).decorate();
		//match e return a with etrue() -> true | eone() -> 1 else -> error("no value!",)end
		return (Object)(new common.PatternLazy<common.DecoratedNode, Object>() { public final Object eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Petrue) {  return (Object)true; }
else if(scrutineeNode instanceof patt.Peone) {  return (Object)Integer.valueOf((int)1); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Object)core.Perror.invoke((new common.StringCatter("no value!"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PretExpr.i_e)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:retExpr", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PretExpr.invoke(children[0]);
		}
	};
}