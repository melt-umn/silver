
package silver.extension.doc.core;

public final class PtoMarkdownExtension extends common.FunctionNode {

	public static final int i_filename = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_toMarkdownExtension;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoMarkdownExtension(final Object c_filename) {
		this.child_filename = c_filename;

	}

	private Object child_filename;
	public final common.StringCatter getChild_filename() {
		return (common.StringCatter) (child_filename = common.Util.demand(child_filename));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_filename: return getChild_filename();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_filename: return child_filename;

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
		return "silver:extension:doc:core:toMarkdownExtension";
	}

	public static common.StringCatter invoke(final Object c_filename) {
		try {
		final common.DecoratedNode context = new PtoMarkdownExtension(c_filename).decorate();
		//substitute(".sv", ".md", filename)
		return (common.StringCatter)(((common.StringCatter)core.Psubstitute.invoke((new common.StringCatter(".sv")), (new common.StringCatter(".md")), context.childAsIsLazy(silver.extension.doc.core.PtoMarkdownExtension.i_filename))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:doc:core:toMarkdownExtension", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoMarkdownExtension.invoke(children[0]);
		}
	};
}