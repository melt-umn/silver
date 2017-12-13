
package silver.definition.type;

public final class PprettyType extends common.FunctionNode {

	public static final int i_te = 0;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_prettyType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PprettyType(final Object c_te) {
		this.child_te = c_te;

	}

	private Object child_te;
	public final silver.definition.type.NType getChild_te() {
		return (silver.definition.type.NType) (child_te = common.Util.demand(child_te));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te: return getChild_te();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te: return child_te;

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
		return "silver:definition:type:prettyType";
	}

	public static common.StringCatter invoke(final Object c_te) {
		try {
		final common.DecoratedNode context = new PprettyType(c_te).decorate();
		//te.typepp
		return (common.StringCatter)(((common.StringCatter)context.childDecorated(silver.definition.type.PprettyType.i_te).synthesized(silver.definition.type.Init.silver_definition_type_typepp__ON__silver_definition_type_Type)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:prettyType", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprettyType.invoke(children[0]);
		}
	};
}