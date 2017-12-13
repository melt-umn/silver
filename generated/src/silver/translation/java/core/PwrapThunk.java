
package silver.translation.java.core;

public final class PwrapThunk extends common.FunctionNode {

	public static final int i_exp = 0;
	public static final int i_beLazy = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,Boolean.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_wrapThunk;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PwrapThunk(final Object c_exp, final Object c_beLazy) {
		this.child_exp = c_exp;
		this.child_beLazy = c_beLazy;

	}

	private Object child_exp;
	public final common.StringCatter getChild_exp() {
		return (common.StringCatter) (child_exp = common.Util.demand(child_exp));
	}

	private Object child_beLazy;
	public final Boolean getChild_beLazy() {
		return (Boolean) (child_beLazy = common.Util.demand(child_beLazy));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_exp: return getChild_exp();
			case i_beLazy: return getChild_beLazy();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_exp: return child_exp;
			case i_beLazy: return child_beLazy;

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
		return "silver:translation:java:core:wrapThunk";
	}

	public static common.StringCatter invoke(final Object c_exp, final Object c_beLazy) {
		try {
		final common.DecoratedNode context = new PwrapThunk(c_exp, c_beLazy).decorate();
		//if beLazy then wrapThunkText("context", exp, "Object") else exp
		return (common.StringCatter)((((Boolean)context.childAsIs(silver.translation.java.core.PwrapThunk.i_beLazy)) ? ((common.StringCatter)silver.translation.java.core.PwrapThunkText.invoke((new common.StringCatter("context")), context.childAsIsLazy(silver.translation.java.core.PwrapThunk.i_exp), (new common.StringCatter("Object")))) : ((common.StringCatter)context.childAsIs(silver.translation.java.core.PwrapThunk.i_exp))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:wrapThunk", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PwrapThunk.invoke(children[0], children[1]);
		}
	};
}