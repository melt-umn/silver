
package silver.definition.core;

// top::AspectRHSElem ::= id::Name t::Type 
public final class PaspectRHSElemFull extends silver.definition.core.NAspectRHSElem {

	public static final int i_id = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NName.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_aspectRHSElemFull;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAspectRHSElem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAspectRHSElem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PaspectRHSElemFull(final Object c_id, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;
		this.child_t = c_t;

	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_t;
	public final silver.definition.type.NType getChild_t() {
		return (silver.definition.type.NType) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:aspectRHSElemFull erroneously claimed to forward");
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
		return "silver:definition:core:aspectRHSElemFull";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = id.pp ++ "::" ++ prettyType(t)
		silver.definition.core.PaspectRHSElemFull.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PaspectRHSElemFull.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("::")), (common.StringCatter)((common.StringCatter)silver.definition.type.PprettyType.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PaspectRHSElemFull.i_t)))))); } };
		// fName = if null(top.realSignature) then id.name else head(top.realSignature).elementName
		silver.definition.core.PaspectRHSElemFull.localAttributes[silver.definition.core.Init.fName__ON__silver_definition_core_aspectRHSElemFull] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHSElem))) ? ((common.StringCatter)context.childDecorated(silver.definition.core.PaspectRHSElemFull.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)) : ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHSElem))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement))); } };
		// rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep
		silver.definition.core.PaspectRHSElemFull.localAttributes[silver.definition.core.Init.rType__ON__silver_definition_core_aspectRHSElemFull] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHSElem))) ? ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()) : ((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHSElem))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement))); } };
		// top.inputElements = [ namedSignatureElement(id.name, t) ]
		silver.definition.core.PaspectRHSElemFull.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_AspectRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)new silver.definition.env.PnamedSignatureElement(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemFull.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PaspectRHSElemFull.i_t)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.defs = [ aliasedChildDef(top.grammarName, id.location, fName, t, id.name) ]
		silver.definition.core.PaspectRHSElemFull.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AspectRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PaliasedChildDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AspectRHSElem), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PaspectRHSElemFull.i_id).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.definition.core.Init.fName__ON__silver_definition_core_aspectRHSElemFull), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PaspectRHSElemFull.i_t)), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemFull.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := if length(getValueDclInScope(id.name, top.env)) > 1 then [ err(id.location, "Value '" ++ id.name ++ "' is already bound.") ] else []
		if(silver.definition.core.PaspectRHSElemFull.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHSElem] == null)
			silver.definition.core.PaspectRHSElemFull.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHSElem] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHSElem);
		((common.CollectionAttribute)silver.definition.core.PaspectRHSElemFull.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHSElem]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDclInScope.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemFull.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_AspectRHSElem))); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.childDecorated(silver.definition.core.PaspectRHSElemFull.i_id).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Value '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PaspectRHSElemFull.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)(new common.StringCatter("' is already bound.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PaspectRHSElemFull> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectRHSElemFull> {

		@Override
		public PaspectRHSElemFull invoke(final Object[] children, final Object[] annotations) {
			return new PaspectRHSElemFull(children[0], children[1], annotations[0]);
		}
	};

}
