
package silver.definition.concrete_syntax.ast;

public final class PconvertAssocNXML extends common.FunctionNode {

	public static final int i_opassoc = 0;


	public static final Class<?> childTypes[] = { core.NMaybe.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_convertAssocNXML;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_opassoc] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PconvertAssocNXML(final Object c_opassoc) {
		this.child_opassoc = c_opassoc;

	}

	private Object child_opassoc;
	public final core.NMaybe getChild_opassoc() {
		return (core.NMaybe) (child_opassoc = common.Util.demand(child_opassoc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_opassoc: return getChild_opassoc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_opassoc: return child_opassoc;

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
		return "silver:definition:concrete_syntax:ast:convertAssocNXML";
	}

	public static common.StringCatter invoke(final Object c_opassoc) {
		try {
		final common.DecoratedNode context = new PconvertAssocNXML(c_opassoc).decorate();
		//if assoc == "left" then "<LeftAssociative/>" else if assoc == "right" then "<RightAssociative/>" else "<NonAssociative/>"
		return (common.StringCatter)((((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.ast.Init.assoc__ON__silver_definition_concrete_syntax_ast_convertAssocNXML)).equals((new common.StringCatter("left"))) ? (new common.StringCatter("<LeftAssociative/>")) : (((common.StringCatter)context.localAsIs(silver.definition.concrete_syntax.ast.Init.assoc__ON__silver_definition_concrete_syntax_ast_convertAssocNXML)).equals((new common.StringCatter("right"))) ? (new common.StringCatter("<RightAssociative/>")) : (new common.StringCatter("<NonAssociative/>")))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:concrete_syntax:ast:convertAssocNXML", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PconvertAssocNXML.invoke(children[0]);
		}
	};
}