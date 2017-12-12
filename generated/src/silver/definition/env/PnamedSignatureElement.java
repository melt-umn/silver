
package silver.definition.env;

// top::NamedSignatureElement ::= n::String ty::Type 
public final class PnamedSignatureElement extends silver.definition.env.NNamedSignatureElement {

	public static final int i_n = 0;
	public static final int i_ty = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_namedSignatureElement;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PnamedSignatureElement(final Object c_n, final Object c_ty) {
		super();
		this.child_n = c_n;
		this.child_ty = c_ty;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_ty;
	public final silver.definition.type.NType getChild_ty() {
		return (silver.definition.type.NType) (child_ty = common.Util.demand(child_ty));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_ty: return getChild_ty();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:namedSignatureElement erroneously claimed to forward");
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
		return "silver:definition:env:namedSignatureElement";
	}

	static void initProductionAttributeDefinitions() {
		// top.unparse = "element('" ++ n ++ "', " ++ ty.unparse ++ ")"
		silver.definition.env.PnamedSignatureElement.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_NamedSignatureElement] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("element('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PnamedSignatureElement.i_n)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PnamedSignatureElement.i_ty).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)(new common.StringCatter(")")))))); } };
		// top.elementName = n
		silver.definition.env.PnamedSignatureElement.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PnamedSignatureElement.i_n)); } };
		// top.typerep = ty
		silver.definition.env.PnamedSignatureElement.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PnamedSignatureElement.i_ty).undecorate(); } };
		// annoShortName = substring(lastIndexOf(":", n) + 1, length(n), n)
		silver.definition.env.PnamedSignatureElement.localAttributes[silver.definition.env.Init.annoShortName__ON__silver_definition_env_namedSignatureElement] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)core.PlastIndexOf.invoke((new common.StringCatter(":")), context.childAsIsLazy(silver.definition.env.PnamedSignatureElement.i_n))) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PnamedSignatureElement.i_n))).length()); } }, context.childAsIsLazy(silver.definition.env.PnamedSignatureElement.i_n))); } };
		// top.toNamedArgType = namedArgType(annoShortName, ty)
		silver.definition.env.PnamedSignatureElement.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_toNamedArgType__ON__silver_definition_env_NamedSignatureElement] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NNamedArgType)new silver.definition.type.PnamedArgType(context.localAsIsLazy(silver.definition.env.Init.annoShortName__ON__silver_definition_env_namedSignatureElement), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PnamedSignatureElement.i_ty)))); } };
		// ty.boundVariables = top.boundVariables
		silver.definition.env.PnamedSignatureElement.childInheritedAttributes[silver.definition.env.PnamedSignatureElement.i_ty][silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignatureElement)); } };

	}

	public static final common.NodeFactory<PnamedSignatureElement> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnamedSignatureElement> {

		@Override
		public PnamedSignatureElement invoke(final Object[] children, final Object[] annotations) {
			return new PnamedSignatureElement(children[0], children[1]);
		}
	};

}
