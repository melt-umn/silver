
package edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing;

public final class PparseDecl extends common.FunctionNode {

	public static final int i_text = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PparseDecl(final Object c_text) {
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:parseDecl";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl invoke(final Object c_text) {
		try {
		final common.DecoratedNode context = new PparseDecl(c_text).decorate();
		//if result.parseSuccess then result.parseTree.ast else error("Syntax errors in parseDecl string:\n" ++ result.parseErrors,)
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl)((((Boolean)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl).synthesized(core.Init.core_parseSuccess__ON__core_ParseResult)) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl)((edu.umn.cs.melt.ableC.concretesyntax.NExternalDeclaration_c)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl).synthesized(core.Init.core_parseTree__ON__core_ParseResult)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_ExternalDeclaration_c)) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl)core.Perror.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Syntax errors in parseDecl string:\n")), (common.StringCatter)((common.StringCatter)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.construction.parsing.Init.result__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_parsing_parseDecl).synthesized(core.Init.core_parseErrors__ON__core_ParseResult))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:construction:parsing:parseDecl", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NDecl invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PparseDecl.invoke(children[0]);
		}
	};
}