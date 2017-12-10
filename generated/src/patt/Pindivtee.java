
package patt;

public final class Pindivtee extends common.FunctionNode {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = { patt.NT.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_indivtee;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[patt.NT.num_inh_attrs];

	}

	public Pindivtee(final Object c_l) {
		this.child_l = c_l;

	}

	private Object child_l;
	public final patt.NT getChild_l() {
		return (patt.NT) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

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
		return "patt:indivtee";
	}

	public static Boolean invoke(final Object c_l) {
		try {
		final common.DecoratedNode context = new Pindivtee(c_l).decorate();
		//case l of a() -> true | b() -> false | c() -> true | d() -> false end
		return (Boolean)(new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Pa) {  return (Boolean)true; }
else if(scrutineeNode instanceof patt.Pb) {  return (Boolean)false; }
else if(scrutineeNode instanceof patt.Pc) {  return (Boolean)true; }
else if(scrutineeNode instanceof patt.Pd) {  return (Boolean)false; }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Normal.sv', 107, 9, 112, 17, 2616, 2757\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.Pindivtee.i_l)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:indivtee", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pindivtee.invoke(children[0]);
		}
	};
}