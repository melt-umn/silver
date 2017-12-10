
package lib.extcore;

public final class PstripWhiteSpaceHelper extends common.FunctionNode {

	public static final int i_ss = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_extcore_stripWhiteSpaceHelper;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PstripWhiteSpaceHelper(final Object c_ss) {
		this.child_ss = c_ss;

	}

	private Object child_ss;
	public final common.ConsCell getChild_ss() {
		return (common.ConsCell) (child_ss = common.Util.demand(child_ss));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ss: return getChild_ss();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ss: return child_ss;

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
		return "lib:extcore:stripWhiteSpaceHelper";
	}

	public static common.ConsCell invoke(final Object c_ss) {
		try {
		final common.DecoratedNode context = new PstripWhiteSpaceHelper(c_ss).decorate();
		//if null(ss) then [] else if hd == " " || hd == "\n" || hd == "\t" then stripWhiteSpaceHelper(tail(ss)) else (hd :: stripWhiteSpaceHelper(tail(ss)))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(lib.extcore.PstripWhiteSpaceHelper.i_ss))) ? ((common.ConsCell)core.Pnil.invoke()) : (((((common.StringCatter)context.localAsIs(lib.extcore.Init.hd__ON__lib_extcore_stripWhiteSpaceHelper)).equals((new common.StringCatter(" "))) || ((common.StringCatter)context.localAsIs(lib.extcore.Init.hd__ON__lib_extcore_stripWhiteSpaceHelper)).equals((new common.StringCatter("\n")))) || ((common.StringCatter)context.localAsIs(lib.extcore.Init.hd__ON__lib_extcore_stripWhiteSpaceHelper)).equals((new common.StringCatter("\t")))) ? ((common.ConsCell)lib.extcore.PstripWhiteSpaceHelper.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PstripWhiteSpaceHelper.i_ss))); } })) : ((common.ConsCell)core.Pcons.invoke(context.localAsIsLazy(lib.extcore.Init.hd__ON__lib_extcore_stripWhiteSpaceHelper), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)lib.extcore.PstripWhiteSpaceHelper.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PstripWhiteSpaceHelper.i_ss))); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:extcore:stripWhiteSpaceHelper", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PstripWhiteSpaceHelper.invoke(children[0]);
		}
	};
}