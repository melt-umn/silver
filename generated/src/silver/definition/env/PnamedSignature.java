
package silver.definition.env;

// top::NamedSignature ::= fn::String ie::[NamedSignatureElement] oe::NamedSignatureElement np::[NamedSignatureElement] 
public final class PnamedSignature extends silver.definition.env.NNamedSignature {

	public static final int i_fn = 0;
	public static final int i_ie = 1;
	public static final int i_oe = 2;
	public static final int i_np = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class,silver.definition.env.NNamedSignatureElement.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_namedSignature;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NNamedSignature.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_oe] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];

	}

	public PnamedSignature(final Object c_fn, final Object c_ie, final Object c_oe, final Object c_np) {
		super();
		this.child_fn = c_fn;
		this.child_ie = c_ie;
		this.child_oe = c_oe;
		this.child_np = c_np;

	}

	private Object child_fn;
	public final common.StringCatter getChild_fn() {
		return (common.StringCatter) (child_fn = common.Util.demand(child_fn));
	}

	private Object child_ie;
	public final common.ConsCell getChild_ie() {
		return (common.ConsCell) (child_ie = common.Util.demand(child_ie));
	}

	private Object child_oe;
	public final silver.definition.env.NNamedSignatureElement getChild_oe() {
		return (silver.definition.env.NNamedSignatureElement) (child_oe = common.Util.demand(child_oe));
	}

	private Object child_np;
	public final common.ConsCell getChild_np() {
		return (common.ConsCell) (child_np = common.Util.demand(child_np));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fn: return getChild_fn();
			case i_ie: return getChild_ie();
			case i_oe: return getChild_oe();
			case i_np: return getChild_np();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fn: return child_fn;
			case i_ie: return child_ie;
			case i_oe: return child_oe;
			case i_np: return child_np;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:namedSignature erroneously claimed to forward");
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
		return "silver:definition:env:namedSignature";
	}

	static void initProductionAttributeDefinitions() {
		// top.fullName = fn
		silver.definition.env.PnamedSignature.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PnamedSignature.i_fn)); } };
		// top.inputElements = ie
		silver.definition.env.PnamedSignature.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.env.PnamedSignature.i_ie)); } };
		// top.outputElement = oe
		silver.definition.env.PnamedSignature.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PnamedSignature.i_oe).undecorate(); } };
		// top.namedInputElements = np
		silver.definition.env.PnamedSignature.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_namedInputElements__ON__silver_definition_env_NamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.env.PnamedSignature.i_np)); } };
		// top.inputNames = map((.elementName), ie)
		silver.definition.env.PnamedSignature.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement, context), context.childAsIsLazy(silver.definition.env.PnamedSignature.i_ie))); } };
		// top.inputTypes = map((.typerep), ie)
		silver.definition.env.PnamedSignature.synthesizedAttributes[silver.definition.env.Init.silver_definition_type_inputTypes__ON__silver_definition_env_NamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement, context), context.childAsIsLazy(silver.definition.env.PnamedSignature.i_ie))); } };
		// top.typerep = functionType(oe.typerep, top.inputTypes, map((.toNamedArgType), np))
		silver.definition.env.PnamedSignature.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PfunctionType(context.childDecoratedSynthesizedLazy(silver.definition.env.PnamedSignature.i_oe, silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement), context.contextSynthesizedLazy(silver.definition.env.Init.silver_definition_type_inputTypes__ON__silver_definition_env_NamedSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_toNamedArgType__ON__silver_definition_env_NamedSignatureElement, context), context.childAsIsLazy(silver.definition.env.PnamedSignature.i_np))); } })); } };
		// oe.boundVariables = top.boundVariables
		silver.definition.env.PnamedSignature.childInheritedAttributes[silver.definition.env.PnamedSignature.i_oe][silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignatureElement] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignature)); } };
		// top.unparse = "signature('" ++ fn ++ "', " ++ unparseSignatureElements(ie, top.boundVariables) ++ ", " ++ oe.unparse ++ ", " ++ unparseSignatureElements(np, top.boundVariables) ++ ")"
		silver.definition.env.PnamedSignature.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_NamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("signature('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PnamedSignature.i_fn)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseSignatureElements.invoke(context.childAsIsLazy(silver.definition.env.PnamedSignature.i_ie), context.contextInheritedLazy(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignature))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PnamedSignature.i_oe).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_NamedSignatureElement)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseSignatureElements.invoke(context.childAsIsLazy(silver.definition.env.PnamedSignature.i_np), context.contextInheritedLazy(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_NamedSignature))), (common.StringCatter)(new common.StringCatter(")")))))))))); } };

	}

	public static final common.NodeFactory<PnamedSignature> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnamedSignature> {

		@Override
		public PnamedSignature invoke(final Object[] children, final Object[] annotations) {
			return new PnamedSignature(children[0], children[1], children[2], children[3]);
		}
	};

}
