
package silver.translation.java.core;

public final class PwrapThunkText extends common.FunctionNode {

	public static final int i_ct = 0;
	public static final int i_exp = 1;
	public static final int i_ty = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_wrapThunkText;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PwrapThunkText(final Object c_ct, final Object c_exp, final Object c_ty) {
		this.child_ct = c_ct;
		this.child_exp = c_exp;
		this.child_ty = c_ty;

	}

	private Object child_ct;
	public final common.StringCatter getChild_ct() {
		return (common.StringCatter) (child_ct = common.Util.demand(child_ct));
	}

	private Object child_exp;
	public final common.StringCatter getChild_exp() {
		return (common.StringCatter) (child_exp = common.Util.demand(child_exp));
	}

	private Object child_ty;
	public final common.StringCatter getChild_ty() {
		return (common.StringCatter) (child_ty = common.Util.demand(child_ty));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ct: return getChild_ct();
			case i_exp: return getChild_exp();
			case i_ty: return getChild_ty();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ct: return child_ct;
			case i_exp: return child_exp;
			case i_ty: return child_ty;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:translation:java:core:wrapThunkText";
	}

	public static common.StringCatter invoke(final Object c_ct, final Object c_exp, final Object c_ty) {
		try {
		final common.DecoratedNode context = new PwrapThunkText(c_ct, c_exp, c_ty).decorate();
		//"new common.Thunk<" ++ ty ++ ">(" ++ ct ++ ") { public final " ++ ty ++ " doEval(final common.DecoratedNode context) { return " ++ exp ++ "; } }"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("new common.Thunk<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.core.PwrapThunkText.i_ty)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(">(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.core.PwrapThunkText.i_ct)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") { public final ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.core.PwrapThunkText.i_ty)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" doEval(final common.DecoratedNode context) { return ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.core.PwrapThunkText.i_exp)), (common.StringCatter)(new common.StringCatter("; } }")))))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:wrapThunkText", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PwrapThunkText.invoke(children[0], children[1], children[2]);
		}
	};
}