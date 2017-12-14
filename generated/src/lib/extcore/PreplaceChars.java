
package lib.extcore;

public final class PreplaceChars extends common.FunctionNode {

	public static final int i_toReplace = 0;
	public static final int i_replaceWith = 1;
	public static final int i_str = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_extcore_replaceChars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PreplaceChars(final Object c_toReplace, final Object c_replaceWith, final Object c_str) {
		this.child_toReplace = c_toReplace;
		this.child_replaceWith = c_replaceWith;
		this.child_str = c_str;

	}

	private Object child_toReplace;
	public final common.StringCatter getChild_toReplace() {
		return (common.StringCatter) (child_toReplace = common.Util.demand(child_toReplace));
	}

	private Object child_replaceWith;
	public final common.StringCatter getChild_replaceWith() {
		return (common.StringCatter) (child_replaceWith = common.Util.demand(child_replaceWith));
	}

	private Object child_str;
	public final common.StringCatter getChild_str() {
		return (common.StringCatter) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toReplace: return getChild_toReplace();
			case i_replaceWith: return getChild_replaceWith();
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toReplace: return child_toReplace;
			case i_replaceWith: return child_replaceWith;
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
		return "lib:extcore:replaceChars";
	}

	public static common.StringCatter invoke(final Object c_toReplace, final Object c_replaceWith, final Object c_str) {
		try {
		final common.DecoratedNode context = new PreplaceChars(c_toReplace, c_replaceWith, c_str).decorate();
		//implode("", replaceCharsHelper(toReplace, replaceWith, explode("", str)))
		return (common.StringCatter)(((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)lib.extcore.PreplaceCharsHelper.invoke(context.childAsIsLazy(lib.extcore.PreplaceChars.i_toReplace), context.childAsIsLazy(lib.extcore.PreplaceChars.i_replaceWith), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pexplode.invoke((new common.StringCatter("")), context.childAsIsLazy(lib.extcore.PreplaceChars.i_str))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:extcore:replaceChars", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PreplaceChars.invoke(children[0], children[1], children[2]);
		}
	};
}