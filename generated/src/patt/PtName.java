
package patt;

public final class PtName extends common.FunctionNode {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = { patt.NT.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_tName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_t] = new common.Lazy[patt.NT.num_inh_attrs];

	}

	public PtName(final Object c_t) {
		this.child_t = c_t;

	}

	private Object child_t;
	public final patt.NT getChild_t() {
		return (patt.NT) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

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
		return "patt:tName";
	}

	public static common.StringCatter invoke(final Object c_t) {
		try {
		final common.DecoratedNode context = new PtName(c_t).decorate();
		//match t return String with a() -> "a" | b() -> "b" else -> "unknown!"end
		return (common.StringCatter)(new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Pa) {  return (common.StringCatter)(new common.StringCatter("a")); }
else if(scrutineeNode instanceof patt.Pb) {  return (common.StringCatter)(new common.StringCatter("b")); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return (new common.StringCatter("unknown!"));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PtName.i_t)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:tName", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtName.invoke(children[0]);
		}
	};
}