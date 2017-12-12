
package silver.definition.type;

// top::Type ::= tv::TyVar 
public final class PskolemType extends silver.definition.type.NType {

	public static final int i_tv = 0;


	public static final Class<?> childTypes[] = {silver.definition.type.NTyVar.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_skolemType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_tv] = new common.Lazy[silver.definition.type.NTyVar.num_inh_attrs];

	}

	public PskolemType(final Object c_tv) {
		super();
		this.child_tv = c_tv;

	}

	private Object child_tv;
	public final silver.definition.type.NTyVar getChild_tv() {
		return (silver.definition.type.NTyVar) (child_tv = common.Util.demand(child_tv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tv: return getChild_tv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tv: return child_tv;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:skolemType erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:definition:type:skolemType";
	}

	static void initProductionAttributeDefinitions() {
		// top.freeVariables = [ tv ]
		silver.definition.type.PskolemType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PskolemType.i_tv)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PskolemType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PskolemType> {

		@Override
		public PskolemType invoke(final Object[] children, final Object[] annotations) {
			return new PskolemType(children[0]);
		}
	};

}
