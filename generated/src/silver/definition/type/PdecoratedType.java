
package silver.definition.type;

// top::Type ::= te::Type 
public final class PdecoratedType extends silver.definition.type.NType {

	public static final int i_te = 0;


	public static final Class<?> childTypes[] = {silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_decoratedType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PdecoratedType(final Object c_te) {
		super();
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:decoratedType erroneously claimed to forward");
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
		return "silver:definition:type:decoratedType";
	}

	static void initProductionAttributeDefinitions() {
		// top.freeVariables = te.freeVariables
		silver.definition.type.PdecoratedType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.type.PdecoratedType.i_te).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); } };

	}

	public static final common.NodeFactory<PdecoratedType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdecoratedType> {

		@Override
		public PdecoratedType invoke(final Object[] children, final Object[] annotations) {
			return new PdecoratedType(children[0]);
		}
	};

}
