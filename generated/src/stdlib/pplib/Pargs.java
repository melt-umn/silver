
package stdlib.pplib;

public final class Pargs extends common.FunctionNode {

	public static final int i_d1 = 0;
	public static final int i_ds = 1;
	public static final int i_dm = 2;
	public static final int i_d2 = 3;


	public static final Class<?> childTypes[] = { silver.langutil.pp.NDocument.class,common.DecoratedNode.class,silver.langutil.pp.NDocument.class,silver.langutil.pp.NDocument.class };

	public static final int num_local_attrs = Init.count_local__ON__stdlib_pplib_args;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d1] = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];
	childInheritedAttributes[i_dm] = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];
	childInheritedAttributes[i_d2] = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];

	}

	public Pargs(final Object c_d1, final Object c_ds, final Object c_dm, final Object c_d2) {
		this.child_d1 = c_d1;
		this.child_ds = c_ds;
		this.child_dm = c_dm;
		this.child_d2 = c_d2;

	}

	private Object child_d1;
	public final silver.langutil.pp.NDocument getChild_d1() {
		return (silver.langutil.pp.NDocument) (child_d1 = common.Util.demand(child_d1));
	}

	private Object child_ds;
	public final common.ConsCell getChild_ds() {
		return (common.ConsCell) (child_ds = common.Util.demand(child_ds));
	}

	private Object child_dm;
	public final silver.langutil.pp.NDocument getChild_dm() {
		return (silver.langutil.pp.NDocument) (child_dm = common.Util.demand(child_dm));
	}

	private Object child_d2;
	public final silver.langutil.pp.NDocument getChild_d2() {
		return (silver.langutil.pp.NDocument) (child_d2 = common.Util.demand(child_d2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d1: return getChild_d1();
			case i_ds: return getChild_ds();
			case i_dm: return getChild_dm();
			case i_d2: return getChild_d2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d1: return child_d1;
			case i_ds: return child_ds;
			case i_dm: return child_dm;
			case i_d2: return child_d2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "stdlib:pplib:args";
	}

	public static silver.langutil.pp.NDocument invoke(final Object c_d1, final Object c_ds, final Object c_dm, final Object c_d2) {
		try {
		final common.DecoratedNode context = new Pargs(c_d1, c_ds, c_dm, c_d2).decorate();
		//cat(cat(d1, box(ppConcat(intersperse(cat(dm, group(line(,),),), ds,),),),), d2,)
		return (silver.langutil.pp.NDocument)(((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(common.Thunk.transformUndecorate(context.childDecoratedLazy(stdlib.pplib.Pargs.i_d1)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pbox(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pintersperse.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(common.Thunk.transformUndecorate(context.childDecoratedLazy(stdlib.pplib.Pargs.i_dm)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pgroup(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pline()); } })); } })); } }, context.childAsIsLazy(stdlib.pplib.Pargs.i_ds))); } })); } })); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(stdlib.pplib.Pargs.i_d2)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function stdlib:pplib:args", t);
		}
	}

	public static final common.NodeFactory<silver.langutil.pp.NDocument> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.langutil.pp.NDocument> {
		@Override
		public silver.langutil.pp.NDocument invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pargs.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}