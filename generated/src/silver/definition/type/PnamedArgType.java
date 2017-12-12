
package silver.definition.type;

// top::NamedArgType ::= s::String ty::Type 
public final class PnamedArgType extends silver.definition.type.NNamedArgType {

	public static final int i_s = 0;
	public static final int i_ty = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_namedArgType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.NNamedArgType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.NNamedArgType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PnamedArgType(final Object c_s, final Object c_ty) {
		super();
		this.child_s = c_s;
		this.child_ty = c_ty;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_ty;
	public final silver.definition.type.NType getChild_ty() {
		return (silver.definition.type.NType) (child_ty = common.Util.demand(child_ty));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_ty: return getChild_ty();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_ty: return child_ty;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:type:namedArgType erroneously claimed to forward");
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
		return "silver:definition:type:namedArgType";
	}

	static void initProductionAttributeDefinitions() {
		// top.typepp = "; " ++ s ++ "::" ++ ty.typepp
		silver.definition.type.PnamedArgType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_typepp__ON__silver_definition_type_NamedArgType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("; ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.type.PnamedArgType.i_s)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("::")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.type.PnamedArgType.i_ty).synthesized(silver.definition.type.Init.silver_definition_type_typepp__ON__silver_definition_type_Type))))); } };
		// top.argName = s
		silver.definition.type.PnamedArgType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_argName__ON__silver_definition_type_NamedArgType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.type.PnamedArgType.i_s)); } };
		// top.argType = ty
		silver.definition.type.PnamedArgType.synthesizedAttributes[silver.definition.type.Init.silver_definition_type_argType__ON__silver_definition_type_NamedArgType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.type.PnamedArgType.i_ty).undecorate(); } };

	}

	public static final common.NodeFactory<PnamedArgType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnamedArgType> {

		@Override
		public PnamedArgType invoke(final Object[] children, final Object[] annotations) {
			return new PnamedArgType(children[0], children[1]);
		}
	};

}
