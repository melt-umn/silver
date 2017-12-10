
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

public final class PstringPairEq extends common.FunctionNode {

	public static final int i_p1 = 0;
	public static final int i_p2 = 1;


	public static final Class<?> childTypes[] = { core.NPair.class,core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_stringPairEq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_p1] = new common.Lazy[core.NPair.num_inh_attrs];
	childInheritedAttributes[i_p2] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PstringPairEq(final Object c_p1, final Object c_p2) {
		this.child_p1 = c_p1;
		this.child_p2 = c_p2;

	}

	private Object child_p1;
	public final core.NPair getChild_p1() {
		return (core.NPair) (child_p1 = common.Util.demand(child_p1));
	}

	private Object child_p2;
	public final core.NPair getChild_p2() {
		return (core.NPair) (child_p2 = common.Util.demand(child_p2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p1: return getChild_p1();
			case i_p2: return getChild_p2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p1: return child_p1;
			case i_p2: return child_p2;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:stringPairEq";
	}

	public static Boolean invoke(final Object c_p1, final Object c_p2) {
		try {
		final common.DecoratedNode context = new PstringPairEq(c_p1, c_p2).decorate();
		//p1.fst == p2.fst && p1.snd == p2.snd
		return (Boolean)((((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PstringPairEq.i_p1).synthesized(core.Init.core_fst__ON__core_Pair)).equals(((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PstringPairEq.i_p2).synthesized(core.Init.core_fst__ON__core_Pair))) && ((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PstringPairEq.i_p1).synthesized(core.Init.core_snd__ON__core_Pair)).equals(((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PstringPairEq.i_p2).synthesized(core.Init.core_snd__ON__core_Pair)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:overloadable:stringPairEq", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PstringPairEq.invoke(children[0], children[1]);
		}
	};
}