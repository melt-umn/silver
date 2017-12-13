
package lib.extcore;

public final class PaddLineNums extends common.FunctionNode {

	public static final int i_next = 0;
	public static final int i_width = 1;
	public static final int i_lines = 2;


	public static final Class<?> childTypes[] = { Integer.class,Integer.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_extcore_addLineNums;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PaddLineNums(final Object c_next, final Object c_width, final Object c_lines) {
		this.child_next = c_next;
		this.child_width = c_width;
		this.child_lines = c_lines;

	}

	private Object child_next;
	public final Integer getChild_next() {
		return (Integer) (child_next = common.Util.demand(child_next));
	}

	private Object child_width;
	public final Integer getChild_width() {
		return (Integer) (child_width = common.Util.demand(child_width));
	}

	private Object child_lines;
	public final common.ConsCell getChild_lines() {
		return (common.ConsCell) (child_lines = common.Util.demand(child_lines));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_next: return getChild_next();
			case i_width: return getChild_width();
			case i_lines: return getChild_lines();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_next: return child_next;
			case i_width: return child_width;
			case i_lines: return child_lines;

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
		return "lib:extcore:addLineNums";
	}

	public static common.StringCatter invoke(final Object c_next, final Object c_width, final Object c_lines) {
		try {
		final common.DecoratedNode context = new PaddLineNums(c_next, c_width, c_lines).decorate();
		//if null(lines) then "" else pad ++ ln ++ ": " ++ head(lines) ++ "\n" ++ addLineNums(next + 1, width, tail(lines))
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(lib.extcore.PaddLineNums.i_lines))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(lib.extcore.Init.pad__ON__lib_extcore_addLineNums)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(lib.extcore.Init.ln__ON__lib_extcore_addLineNums)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(": ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(lib.extcore.PaddLineNums.i_lines))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)lib.extcore.PaddLineNums.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(lib.extcore.PaddLineNums.i_next)) + Integer.valueOf((int)1)); } }, context.childAsIsLazy(lib.extcore.PaddLineNums.i_width), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PaddLineNums.i_lines))); } })))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:extcore:addLineNums", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaddLineNums.invoke(children[0], children[1], children[2]);
		}
	};
}