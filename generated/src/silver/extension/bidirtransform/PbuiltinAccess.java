
package silver.extension.bidirtransform;

// top::Expr ::= attr::String ne::NamedSignatureElement allowedTypes::[String] 
public final class PbuiltinAccess extends silver.definition.core.NExpr {

	public static final int i_attr = 0;
	public static final int i_ne = 1;
	public static final int i_allowedTypes = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.env.NNamedSignatureElement.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_builtinAccess;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ne] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];

	}

	public PbuiltinAccess(final Object c_attr, final Object c_ne, final Object c_allowedTypes, final Object a_core_location) {
		super(a_core_location);
		this.child_attr = c_attr;
		this.child_ne = c_ne;
		this.child_allowedTypes = c_allowedTypes;

	}

	private Object child_attr;
	public final common.StringCatter getChild_attr() {
		return (common.StringCatter) (child_attr = common.Util.demand(child_attr));
	}

	private Object child_ne;
	public final silver.definition.env.NNamedSignatureElement getChild_ne() {
		return (silver.definition.env.NNamedSignatureElement) (child_ne = common.Util.demand(child_ne));
	}

	private Object child_allowedTypes;
	public final common.ConsCell getChild_allowedTypes() {
		return (common.ConsCell) (child_allowedTypes = common.Util.demand(child_allowedTypes));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_attr: return getChild_attr();
			case i_ne: return getChild_ne();
			case i_allowedTypes: return getChild_allowedTypes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_attr: return child_attr;
			case i_ne: return child_ne;
			case i_allowedTypes: return child_allowedTypes;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return (((Boolean)silver.util.Pcontains.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PunFull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.childDecorated(silver.extension.bidirtransform.PbuiltinAccess.i_ne).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })); } }, context.childAsIsLazy(silver.extension.bidirtransform.PbuiltinAccess.i_allowedTypes))) ? ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PexprAccess(context.childAsIsLazy(silver.extension.bidirtransform.PbuiltinAccess.i_attr), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PbuiltinAccess.i_ne, silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })) : ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PbaseName(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PbuiltinAccess.i_ne, silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:extension:bidirtransform:builtinAccess";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PbuiltinAccess> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbuiltinAccess> {

		@Override
		public PbuiltinAccess invoke(final Object[] children, final Object[] annotations) {
			return new PbuiltinAccess(children[0], children[1], children[2], annotations[0]);
		}
	};

}
