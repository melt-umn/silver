
package silver.modification.let_fix.java;

public final class PmakeSpecialLocalBinding extends common.FunctionNode {

	public static final int i_fn = 0;
	public static final int i_et = 1;
	public static final int i_ty = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_java_makeSpecialLocalBinding;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmakeSpecialLocalBinding(final Object c_fn, final Object c_et, final Object c_ty) {
		this.child_fn = c_fn;
		this.child_et = c_et;
		this.child_ty = c_ty;

	}

	private Object child_fn;
	public final common.StringCatter getChild_fn() {
		return (common.StringCatter) (child_fn = common.Util.demand(child_fn));
	}

	private Object child_et;
	public final common.StringCatter getChild_et() {
		return (common.StringCatter) (child_et = common.Util.demand(child_et));
	}

	private Object child_ty;
	public final common.StringCatter getChild_ty() {
		return (common.StringCatter) (child_ty = common.Util.demand(child_ty));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fn: return getChild_fn();
			case i_et: return getChild_et();
			case i_ty: return getChild_ty();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fn: return child_fn;
			case i_et: return child_et;
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
		return "silver:modification:let_fix:java:makeSpecialLocalBinding";
	}

	public static common.StringCatter invoke(final Object c_fn, final Object c_et, final Object c_ty) {
		try {
		final common.DecoratedNode context = new PmakeSpecialLocalBinding(c_fn, c_et, c_ty).decorate();
		//"final common.Thunk<Object> " ++ makeLocalValueName(fn) ++ " = " ++ wrapThunkText("context", et, "Object") ++ ";\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("final common.Thunk<Object> ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.let_fix.java.PmakeLocalValueName.invoke(context.childAsIsLazy(silver.modification.let_fix.java.PmakeSpecialLocalBinding.i_fn))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.translation.java.core.PwrapThunkText.invoke((new common.StringCatter("context")), context.childAsIsLazy(silver.modification.let_fix.java.PmakeSpecialLocalBinding.i_et), (new common.StringCatter("Object")))), (common.StringCatter)(new common.StringCatter(";\n")))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:let_fix:java:makeSpecialLocalBinding", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeSpecialLocalBinding.invoke(children[0], children[1], children[2]);
		}
	};
}