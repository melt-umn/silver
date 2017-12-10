
package silver.translation.java.core;

public final class PmakeIndexDcls extends common.FunctionNode {

	public static final int i_i = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { Integer.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_makeIndexDcls;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmakeIndexDcls(final Object c_i, final Object c_s) {
		this.child_i = c_i;
		this.child_s = c_s;

	}

	private Object child_i;
	public final Integer getChild_i() {
		return (Integer) (child_i = common.Util.demand(child_i));
	}

	private Object child_s;
	public final common.ConsCell getChild_s() {
		return (common.ConsCell) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;
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
		return "silver:translation:java:core:makeIndexDcls";
	}

	public static common.StringCatter invoke(final Object c_i, final Object c_s) {
		try {
		final common.DecoratedNode context = new PmakeIndexDcls(c_i, c_s).decorate();
		//if null(s) then "" else "\tpublic static final int i_" ++ head(s).elementName ++ " = " ++ toString(i) ++ ";\n" ++ makeIndexDcls(i + 1, tail(s))
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.translation.java.core.PmakeIndexDcls.i_s))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("\tpublic static final int i_")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsLazy(silver.translation.java.core.PmakeIndexDcls.i_s))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(silver.translation.java.core.PmakeIndexDcls.i_i)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n")), (common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeIndexDcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.translation.java.core.PmakeIndexDcls.i_i)) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.translation.java.core.PmakeIndexDcls.i_s))); } })))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:makeIndexDcls", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeIndexDcls.invoke(children[0], children[1]);
		}
	};
}