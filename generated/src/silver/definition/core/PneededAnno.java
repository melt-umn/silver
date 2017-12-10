
package silver.definition.core;

public final class PneededAnno extends common.FunctionNode {

	public static final int i_namedTypes = 0;
	public static final int i_anno = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_neededAnno;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PneededAnno(final Object c_namedTypes, final Object c_anno) {
		this.child_namedTypes = c_namedTypes;
		this.child_anno = c_anno;

	}

	private Object child_namedTypes;
	public final common.ConsCell getChild_namedTypes() {
		return (common.ConsCell) (child_namedTypes = common.Util.demand(child_namedTypes));
	}

	private Object child_anno;
	public final common.StringCatter getChild_anno() {
		return (common.StringCatter) (child_anno = common.Util.demand(child_anno));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_namedTypes: return getChild_namedTypes();
			case i_anno: return getChild_anno();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_namedTypes: return child_namedTypes;
			case i_anno: return child_anno;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:definition:core:neededAnno";
	}

	public static Boolean invoke(final Object c_namedTypes, final Object c_anno) {
		try {
		final common.DecoratedNode context = new PneededAnno(c_namedTypes, c_anno).decorate();
		//if null(namedTypes) then false else if head(namedTypes).argName == anno then true else neededAnno(tail(namedTypes), anno)
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.PneededAnno.i_namedTypes))) ? false : (((common.StringCatter)((silver.definition.type.NNamedArgType)core.Phead.invoke(context.childAsIsLazy(silver.definition.core.PneededAnno.i_namedTypes))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_argName__ON__silver_definition_type_NamedArgType)).equals(((common.StringCatter)context.childAsIs(silver.definition.core.PneededAnno.i_anno))) ? true : ((Boolean)silver.definition.core.PneededAnno.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PneededAnno.i_namedTypes))); } }, context.childAsIsLazy(silver.definition.core.PneededAnno.i_anno))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:neededAnno", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PneededAnno.invoke(children[0], children[1]);
		}
	};
}