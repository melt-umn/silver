
package silver.definition.type;

public final class PfindSubst extends common.FunctionNode {

	public static final int i_tv = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NTyVar.class,silver.definition.type.NSubstitution.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_findSubst;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_tv] = new common.Lazy[silver.definition.type.NTyVar.num_inh_attrs];
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];

	}

	public PfindSubst(final Object c_tv, final Object c_s) {
		this.child_tv = c_tv;
		this.child_s = c_s;

	}

	private Object child_tv;
	public final silver.definition.type.NTyVar getChild_tv() {
		return (silver.definition.type.NTyVar) (child_tv = common.Util.demand(child_tv));
	}

	private Object child_s;
	public final silver.definition.type.NSubstitution getChild_s() {
		return (silver.definition.type.NSubstitution) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tv: return getChild_tv();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tv: return child_tv;
			case i_s: return child_s;

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
		return "silver:definition:type:findSubst";
	}

	public static core.NMaybe invoke(final Object c_tv, final Object c_s) {
		try {
		final common.DecoratedNode context = new PfindSubst(c_tv, c_s).decorate();
		//lookupBy(tyVarEqual, tv, s.substList)
		return (core.NMaybe)(((core.NMaybe)core.PlookupBy.invoke(silver.definition.type.PtyVarEqual.factory, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PfindSubst.i_tv)), context.childDecoratedSynthesizedLazy(silver.definition.type.PfindSubst.i_s, silver.definition.type.Init.silver_definition_type_substList__ON__silver_definition_type_Substitution))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:findSubst", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindSubst.invoke(children[0], children[1]);
		}
	};
}