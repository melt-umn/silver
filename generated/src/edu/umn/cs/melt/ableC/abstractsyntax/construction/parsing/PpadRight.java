
package edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing;

public final class PpadRight extends common.FunctionNode {

	public static final int i_len = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { Integer.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_padRight;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PpadRight(final Object c_len, final Object c_s) {
		this.child_len = c_len;
		this.child_s = c_s;

	}

	private Object child_len;
	public final Integer getChild_len() {
		return (Integer) (child_len = common.Util.demand(child_len));
	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_len: return getChild_len();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_len: return child_len;
			case i_s: return child_s;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:padRight";
	}

	public static common.StringCatter invoke(final Object c_len, final Object c_s) {
		try {
		final common.DecoratedNode context = new PpadRight(c_len, c_s).decorate();
		//if length(s) < len then s ++ replicate(len - length(s), " ",) else s
		return (common.StringCatter)(((Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PpadRight.i_s))).length()) < ((Integer)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PpadRight.i_len))) ? new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PpadRight.i_s)), (common.StringCatter)((common.StringCatter)core.Preplicate.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PpadRight.i_len)) - Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PpadRight.i_s))).length())); } }, (new common.StringCatter(" "))))) : ((common.StringCatter)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PpadRight.i_s))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:padRight", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpadRight.invoke(children[0], children[1]);
		}
	};
}