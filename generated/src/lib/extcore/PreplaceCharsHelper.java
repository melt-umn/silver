
package lib.extcore;

public final class PreplaceCharsHelper extends common.FunctionNode {

	public static final int i_toReplace = 0;
	public static final int i_replaceWith = 1;
	public static final int i_chars = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_extcore_replaceCharsHelper;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PreplaceCharsHelper(final Object c_toReplace, final Object c_replaceWith, final Object c_chars) {
		this.child_toReplace = c_toReplace;
		this.child_replaceWith = c_replaceWith;
		this.child_chars = c_chars;

	}

	private Object child_toReplace;
	public final common.StringCatter getChild_toReplace() {
		return (common.StringCatter) (child_toReplace = common.Util.demand(child_toReplace));
	}

	private Object child_replaceWith;
	public final common.StringCatter getChild_replaceWith() {
		return (common.StringCatter) (child_replaceWith = common.Util.demand(child_replaceWith));
	}

	private Object child_chars;
	public final common.ConsCell getChild_chars() {
		return (common.ConsCell) (child_chars = common.Util.demand(child_chars));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toReplace: return getChild_toReplace();
			case i_replaceWith: return getChild_replaceWith();
			case i_chars: return getChild_chars();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toReplace: return child_toReplace;
			case i_replaceWith: return child_replaceWith;
			case i_chars: return child_chars;

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
		return "lib:extcore:replaceCharsHelper";
	}

	public static common.ConsCell invoke(final Object c_toReplace, final Object c_replaceWith, final Object c_chars) {
		try {
		final common.DecoratedNode context = new PreplaceCharsHelper(c_toReplace, c_replaceWith, c_chars).decorate();
		//if null(chars) then [] else if head(chars) == toReplace then (replaceWith :: replaceCharsHelper(toReplace, replaceWith, tail(chars))) else (head(chars) :: replaceCharsHelper(toReplace, replaceWith, tail(chars)))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_chars))) ? ((common.ConsCell)core.Pnil.invoke()) : (((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_chars))).equals(((common.StringCatter)context.childAsIs(lib.extcore.PreplaceCharsHelper.i_toReplace))) ? ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_replaceWith), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)lib.extcore.PreplaceCharsHelper.invoke(context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_toReplace), context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_replaceWith), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_chars))); } })); } })) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_chars))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)lib.extcore.PreplaceCharsHelper.invoke(context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_toReplace), context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_replaceWith), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PreplaceCharsHelper.i_chars))); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:extcore:replaceCharsHelper", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PreplaceCharsHelper.invoke(children[0], children[1], children[2]);
		}
	};
}