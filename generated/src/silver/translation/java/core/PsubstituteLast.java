
package silver.translation.java.core;

public final class PsubstituteLast extends common.FunctionNode {

	public static final int i_r = 0;
	public static final int i_s = 1;
	public static final int i_str = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_substituteLast;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsubstituteLast(final Object c_r, final Object c_s, final Object c_str) {
		this.child_r = c_r;
		this.child_s = c_s;
		this.child_str = c_str;

	}

	private Object child_r;
	public final common.StringCatter getChild_r() {
		return (common.StringCatter) (child_r = common.Util.demand(child_r));
	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_str;
	public final common.StringCatter getChild_str() {
		return (common.StringCatter) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();
			case i_s: return getChild_s();
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;
			case i_s: return child_s;
			case i_str: return child_str;

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
		return "silver:translation:java:core:substituteLast";
	}

	public static common.StringCatter invoke(final Object c_r, final Object c_s, final Object c_str) {
		try {
		final common.DecoratedNode context = new PsubstituteLast(c_r, c_s, c_str).decorate();
		//if i == -1 then str else substring(0, i, str) ++ s ++ substring(i + length(r), length(str), str)
		return (common.StringCatter)((((Integer)context.localAsIs(silver.translation.java.core.Init.i__ON__silver_translation_java_core_substituteLast)).equals(Integer.valueOf((int)-1)) ? ((common.StringCatter)context.childAsIs(silver.translation.java.core.PsubstituteLast.i_str)) : new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), context.localAsIsLazy(silver.translation.java.core.Init.i__ON__silver_translation_java_core_substituteLast), context.childAsIsLazy(silver.translation.java.core.PsubstituteLast.i_str))), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.core.PsubstituteLast.i_s)), (common.StringCatter)((common.StringCatter)core.Psubstring.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.localAsIs(silver.translation.java.core.Init.i__ON__silver_translation_java_core_substituteLast)) + Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.core.PsubstituteLast.i_r))).length())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.core.PsubstituteLast.i_str))).length()); } }, context.childAsIsLazy(silver.translation.java.core.PsubstituteLast.i_str)))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:substituteLast", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsubstituteLast.invoke(children[0], children[1], children[2]);
		}
	};
}