
package edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax;

public final class PmatchConstructorName extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_cnst = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_exts_ableC_algebraicDataTypes_patternmatching_abstractsyntax_matchConstructorName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_cnst] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PmatchConstructorName(final Object c_n, final Object c_cnst) {
		this.child_n = c_n;
		this.child_cnst = c_cnst;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_cnst;
	public final core.NPair getChild_cnst() {
		return (core.NPair) (child_cnst = common.Util.demand(child_cnst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_cnst: return getChild_cnst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_cnst: return child_cnst;

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
		return "edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:matchConstructorName";
	}

	public static Boolean invoke(final Object c_n, final Object c_cnst) {
		try {
		final common.DecoratedNode context = new PmatchConstructorName(c_n, c_cnst).decorate();
		//n == cnst.fst
		return (Boolean)(((common.StringCatter)context.childAsIs(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmatchConstructorName.i_n)).equals(((common.StringCatter)context.childDecorated(edu.umn.cs.melt.exts.ableC.algebraicDataTypes.patternmatching.abstractsyntax.PmatchConstructorName.i_cnst).synthesized(core.Init.core_fst__ON__core_Pair))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:exts:ableC:algebraicDataTypes:patternmatching:abstractsyntax:matchConstructorName", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmatchConstructorName.invoke(children[0], children[1]);
		}
	};
}