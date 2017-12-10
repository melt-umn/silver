
package silver.definition.type;

public final class PtyVarEqual extends common.FunctionNode {

	public static final int i_tv1 = 0;
	public static final int i_tv2 = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NTyVar.class,silver.definition.type.NTyVar.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_tyVarEqual;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_tv1] = new common.Lazy[silver.definition.type.NTyVar.num_inh_attrs];
	childInheritedAttributes[i_tv2] = new common.Lazy[silver.definition.type.NTyVar.num_inh_attrs];

	}

	public PtyVarEqual(final Object c_tv1, final Object c_tv2) {
		this.child_tv1 = c_tv1;
		this.child_tv2 = c_tv2;

	}

	private Object child_tv1;
	public final silver.definition.type.NTyVar getChild_tv1() {
		return (silver.definition.type.NTyVar) (child_tv1 = common.Util.demand(child_tv1));
	}

	private Object child_tv2;
	public final silver.definition.type.NTyVar getChild_tv2() {
		return (silver.definition.type.NTyVar) (child_tv2 = common.Util.demand(child_tv2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tv1: return getChild_tv1();
			case i_tv2: return getChild_tv2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tv1: return child_tv1;
			case i_tv2: return child_tv2;

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
		return "silver:definition:type:tyVarEqual";
	}

	public static Boolean invoke(final Object c_tv1, final Object c_tv2) {
		try {
		final common.DecoratedNode context = new PtyVarEqual(c_tv1, c_tv2).decorate();
		//tv1.extractTyVarRep == tv2.extractTyVarRep
		return (Boolean)(((Integer)context.childDecorated(silver.definition.type.PtyVarEqual.i_tv1).synthesized(silver.definition.type.Init.silver_definition_type_extractTyVarRep__ON__silver_definition_type_TyVar)).equals(((Integer)context.childDecorated(silver.definition.type.PtyVarEqual.i_tv2).synthesized(silver.definition.type.Init.silver_definition_type_extractTyVarRep__ON__silver_definition_type_TyVar))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:tyVarEqual", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtyVarEqual.invoke(children[0], children[1]);
		}
	};
}