
package silver.translation.java.core;

public final class PmakeOthers extends common.FunctionNode {

	public static final int i_others = 0;
	public static final int i_nme = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_makeOthers;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmakeOthers(final Object c_others, final Object c_nme) {
		this.child_others = c_others;
		this.child_nme = c_nme;

	}

	private Object child_others;
	public final common.ConsCell getChild_others() {
		return (common.ConsCell) (child_others = common.Util.demand(child_others));
	}

	private Object child_nme;
	public final common.StringCatter getChild_nme() {
		return (common.StringCatter) (child_nme = common.Util.demand(child_nme));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_others: return getChild_others();
			case i_nme: return getChild_nme();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_others: return child_others;
			case i_nme: return child_nme;

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
		return "silver:translation:java:core:makeOthers";
	}

	public static common.StringCatter invoke(final Object c_others, final Object c_nme) {
		try {
		final common.DecoratedNode context = new PmakeOthers(c_others, c_nme).decorate();
		//if null(others) then "" else "\t\t" ++ makeName(head(others)) ++ ".Init." ++ nme ++ "();\n" ++ makeOthers(tail(others), nme)
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.translation.java.core.PmakeOthers.i_others))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("\t\t")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.translation.java.core.PmakeOthers.i_others))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".Init.")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.translation.java.core.PmakeOthers.i_nme)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("();\n")), (common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeOthers.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.translation.java.core.PmakeOthers.i_others))); } }, context.childAsIsLazy(silver.translation.java.core.PmakeOthers.i_nme))))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:makeOthers", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeOthers.invoke(children[0], children[1]);
		}
	};
}