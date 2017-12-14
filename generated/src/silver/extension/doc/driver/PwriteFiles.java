
package silver.extension.doc.driver;

public final class PwriteFiles extends common.FunctionNode {

	public static final int i_path = 0;
	public static final int i_s = 1;
	public static final int i_i = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_driver_writeFiles;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PwriteFiles(final Object c_path, final Object c_s, final Object c_i) {
		this.child_path = c_path;
		this.child_s = c_s;
		this.child_i = c_i;

	}

	private Object child_path;
	public final common.StringCatter getChild_path() {
		return (common.StringCatter) (child_path = common.Util.demand(child_path));
	}

	private Object child_s;
	public final common.ConsCell getChild_s() {
		return (common.ConsCell) (child_s = common.Util.demand(child_s));
	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_path: return getChild_path();
			case i_s: return getChild_s();
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_path: return child_path;
			case i_s: return child_s;
			case i_i: return child_i;

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
		return "silver:extension:doc:driver:writeFiles";
	}

	public static Object invoke(final Object c_path, final Object c_s, final Object c_i) {
		try {
		final common.DecoratedNode context = new PwriteFiles(c_path, c_s, c_i).decorate();
		//if null(s) then i else writeFile(path ++ head(s).fst, head(s).snd, writeFiles(path, tail(s), i))
		return (Object)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.doc.driver.PwriteFiles.i_s))) ? ((Object)context.childAsIs(silver.extension.doc.driver.PwriteFiles.i_i)) : ((Object)core.PwriteFile.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.doc.driver.PwriteFiles.i_path)), (common.StringCatter)((common.StringCatter)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.extension.doc.driver.PwriteFiles.i_s))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.extension.doc.driver.PwriteFiles.i_s))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.extension.doc.driver.PwriteFiles.invoke(context.childAsIsLazy(silver.extension.doc.driver.PwriteFiles.i_path), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.doc.driver.PwriteFiles.i_s))); } }, context.childAsIsLazy(silver.extension.doc.driver.PwriteFiles.i_i))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:driver:writeFiles", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PwriteFiles.invoke(children[0], children[1], children[2]);
		}
	};
}