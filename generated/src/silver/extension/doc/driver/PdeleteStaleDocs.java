
package silver.extension.doc.driver;

public final class PdeleteStaleDocs extends common.FunctionNode {

	public static final int i_iIn = 0;
	public static final int i_outputLoc = 1;
	public static final int i_gram = 2;


	public static final Class<?> childTypes[] = { Object.class,common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_driver_deleteStaleDocs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PdeleteStaleDocs(final Object c_iIn, final Object c_outputLoc, final Object c_gram) {
		this.child_iIn = c_iIn;
		this.child_outputLoc = c_outputLoc;
		this.child_gram = c_gram;

	}

	private Object child_iIn;
	public final Object getChild_iIn() {
		return (Object) (child_iIn = common.Util.demand(child_iIn));
	}

	private Object child_outputLoc;
	public final common.StringCatter getChild_outputLoc() {
		return (common.StringCatter) (child_outputLoc = common.Util.demand(child_outputLoc));
	}

	private Object child_gram;
	public final common.StringCatter getChild_gram() {
		return (common.StringCatter) (child_gram = common.Util.demand(child_gram));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_iIn: return getChild_iIn();
			case i_outputLoc: return getChild_outputLoc();
			case i_gram: return getChild_gram();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_iIn: return child_iIn;
			case i_outputLoc: return child_outputLoc;
			case i_gram: return child_gram;

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
		return "silver:extension:doc:driver:deleteStaleDocs";
	}

	public static Object invoke(final Object c_iIn, final Object c_outputLoc, final Object c_gram) {
		try {
		final common.DecoratedNode context = new PdeleteStaleDocs(c_iIn, c_outputLoc, c_gram).decorate();
		//deleteDirFiles(docPath, iIn).io
		return (Object)(((Object)((core.NIOVal)core.PdeleteDirFiles.invoke(context.localAsIsLazy(silver.extension.doc.driver.Init.docPath__ON__silver_extension_doc_driver_deleteStaleDocs), context.childAsIsLazy(silver.extension.doc.driver.PdeleteStaleDocs.i_iIn))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_io__ON__core_IOVal)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:driver:deleteStaleDocs", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdeleteStaleDocs.invoke(children[0], children[1], children[2]);
		}
	};
}