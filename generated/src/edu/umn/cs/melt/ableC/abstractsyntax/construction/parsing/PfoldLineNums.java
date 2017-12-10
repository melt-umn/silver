
package edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing;

public final class PfoldLineNums extends common.FunctionNode {

	public static final int i_text = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfoldLineNums(final Object c_text) {
		this.child_text = c_text;

	}

	private Object child_text;
	public final common.StringCatter getChild_text() {
		return (common.StringCatter) (child_text = common.Util.demand(child_text));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_text: return getChild_text();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_text: return child_text;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:foldLineNums";
	}

	public static common.StringCatter invoke(final Object c_text) {
		try {
		final common.DecoratedNode context = new PfoldLineNums(c_text).decorate();
		//implode("\n", zipWith(\ lineNum::Integer line::String  -> padRight(3, toString(lineNum),) ++ line, lineNums, lines,),)
		return (common.StringCatter)(((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke((new common.NodeFactory<common.StringCatter>() {
  public final common.StringCatter invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13236_lineNum = args[0];
final Object __SV_LAMBDA_PARAM_13237_line = args[1];

    return new common.StringCatter((common.StringCatter)((common.StringCatter)edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.PpadRight.invoke(Integer.valueOf((int)3), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)common.Util.demand(__SV_LAMBDA_PARAM_13236_lineNum)))); } })), (common.StringCatter)((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_13237_line)));
  }
}), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.lineNums__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums), context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.lines__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_foldLineNums))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:foldLineNums", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfoldLineNums.invoke(children[0]);
		}
	};
}