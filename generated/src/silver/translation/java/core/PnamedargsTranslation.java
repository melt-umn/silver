
package silver.translation.java.core;

public final class PnamedargsTranslation extends common.FunctionNode {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_namedargsTranslation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PnamedargsTranslation(final Object c_e) {
		this.child_e = c_e;

	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
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
		return "silver:translation:java:core:namedargsTranslation";
	}

	public static common.StringCatter invoke(final Object c_e) {
		try {
		final common.DecoratedNode context = new PnamedargsTranslation(c_e).decorate();
		//if null(e.exprs) then "null" else "new Object[]{" ++ implode(", ", map((.lazyTranslation), reorderedAnnoAppExprs(e))) ++ "}"
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsSynthesizedLazy(silver.translation.java.core.PnamedargsTranslation.i_e, silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AnnoAppExprs))) ? (new common.StringCatter("null")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("new Object[]{")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection(silver.translation.java.core.Init.silver_translation_java_core_lazyTranslation__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.core.PreorderedAnnoAppExprs.invoke(context.childAsIsLazy(silver.translation.java.core.PnamedargsTranslation.i_e))); } })); } })), (common.StringCatter)(new common.StringCatter("}"))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:namedargsTranslation", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnamedargsTranslation.invoke(children[0]);
		}
	};
}