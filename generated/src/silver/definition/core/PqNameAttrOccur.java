
package silver.definition.core;

// top::QNameAttrOccur ::= at::QName 
public final class PqNameAttrOccur extends silver.definition.core.NQNameAttrOccur {

	public static final int i_at = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_qNameAttrOccur;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NQNameAttrOccur.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NQNameAttrOccur.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_at] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PqNameAttrOccur(final Object c_at, final Object a_core_location) {
		super(a_core_location);
		this.child_at = c_at;

	}

	private Object child_at;
	public final silver.definition.core.NQName getChild_at() {
		return (silver.definition.core.NQName) (child_at = common.Util.demand(child_at));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_at: return getChild_at();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_at: return child_at;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:qNameAttrOccur erroneously claimed to forward");
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
		return "silver:definition:core:qNameAttrOccur";
	}

	static void initProductionAttributeDefinitions() {
		// top.name = at.name
		silver.definition.core.PqNameAttrOccur.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)); } };
		// top.pp = at.pp
		silver.definition.core.PqNameAttrOccur.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// narrowed = map(getOccursDcl(_, top.attrFor.typeName, top.env), map((.fullName), at.lookupAttribute.dcls))
		silver.definition.core.PqNameAttrOccur.localAttributes[silver.definition.core.Init.narrowed__ON__silver_definition_core_qNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PgetOccursDcl.factory.invokePartial(new int[]{1, 2}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.definition.core.Init.silver_definition_core_attrFor__ON__silver_definition_core_QNameAttrOccur)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QNameAttrOccur)}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo, context), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })); } })); } };
		// dclsNarrowed = concat(narrowed)
		silver.definition.core.PqNameAttrOccur.localAttributes[silver.definition.core.Init.dclsNarrowed__ON__silver_definition_core_qNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pconcat.invoke(context.localAsIsLazy(silver.definition.core.Init.narrowed__ON__silver_definition_core_qNameAttrOccur))); } };
		// attrsNarrowed = zipFilterDcls(at.lookupAttribute.dcls, narrowed)
		silver.definition.core.PqNameAttrOccur.localAttributes[silver.definition.core.Init.attrsNarrowed__ON__silver_definition_core_qNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.core.PzipFilterDcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } }, context.localAsIsLazy(silver.definition.core.Init.narrowed__ON__silver_definition_core_qNameAttrOccur))); } };
		// top.errors := if null(at.lookupAttribute.dcls) then at.lookupAttribute.errors else if null(dclsNarrowed) then [ err(at.location, "Attribute '" ++ at.name ++ "' does not occur on '" ++ prettyType(top.attrFor) ++ "'. Looked at:\n" ++ printPossibilities(at.lookupAttribute.dcls)) ] else if length(attrsNarrowed) > 1 then [ err(at.location, "Ambiguous reference to attribute occurring on '" ++ at.name ++ "'. Possibilities are:\n" ++ printPossibilities(attrsNarrowed)) ] else []
		if(silver.definition.core.PqNameAttrOccur.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur] == null)
			silver.definition.core.PqNameAttrOccur.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur);
		((common.CollectionAttribute)silver.definition.core.PqNameAttrOccur.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)) : (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.core.Init.dclsNarrowed__ON__silver_definition_core_qNameAttrOccur))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Attribute '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("' does not occur on '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.type.PprettyType.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_attrFor__ON__silver_definition_core_QNameAttrOccur))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'. Looked at:\n")), (common.StringCatter)((common.StringCatter)silver.definition.core.PprintPossibilities.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } }))))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.definition.core.Init.attrsNarrowed__ON__silver_definition_core_qNameAttrOccur))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Ambiguous reference to attribute occurring on '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'. Possibilities are:\n")), (common.StringCatter)((common.StringCatter)silver.definition.core.PprintPossibilities.invoke(context.localAsIsLazy(silver.definition.core.Init.attrsNarrowed__ON__silver_definition_core_qNameAttrOccur)))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())))); } });
		// top.typerep = if null(top.errors) then determineAttributeType(head(dclsNarrowed), top.attrFor) else errorType()
		silver.definition.core.PqNameAttrOccur.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur))) ? ((silver.definition.type.NType)silver.definition.env.PdetermineAttributeType.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.dclsNarrowed__ON__silver_definition_core_qNameAttrOccur))); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_attrFor__ON__silver_definition_core_QNameAttrOccur))) : ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke())); } };
		// top.dcl = if null(top.errors) then head(dclsNarrowed) else error("INTERNAL ERROR: Accessing dcl of occurrence " ++ at.name ++ " at " ++ top.grammarName ++ " " ++ top.location.unparse)
		silver.definition.core.PqNameAttrOccur.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur))) ? ((silver.definition.env.NDclInfo)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.dclsNarrowed__ON__silver_definition_core_qNameAttrOccur))) : ((silver.definition.env.NDclInfo)core.Perror.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("INTERNAL ERROR: Accessing dcl of occurrence ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" at ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)((core.NLocation)((silver.definition.core.NQNameAttrOccur)context.undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location))))))); } }))); } };
		// top.attrDcl = if length(attrsNarrowed) == 1 then head(attrsNarrowed) else error("INTERNAL ERROR: Accessing dcl of attribute " ++ at.name ++ " at " ++ top.grammarName ++ " " ++ top.location.unparse)
		silver.definition.core.PqNameAttrOccur.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_attrDcl__ON__silver_definition_core_QNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.definition.core.Init.attrsNarrowed__ON__silver_definition_core_qNameAttrOccur))).equals(Integer.valueOf((int)1)) ? ((silver.definition.env.NDclInfo)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.attrsNarrowed__ON__silver_definition_core_qNameAttrOccur))) : ((silver.definition.env.NDclInfo)core.Perror.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("INTERNAL ERROR: Accessing dcl of attribute ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PqNameAttrOccur.i_at).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" at ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)((core.NLocation)((silver.definition.core.NQNameAttrOccur)context.undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location))))))); } }))); } };

	}

	public static final common.NodeFactory<PqNameAttrOccur> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqNameAttrOccur> {

		@Override
		public PqNameAttrOccur invoke(final Object[] children, final Object[] annotations) {
			return new PqNameAttrOccur(children[0], annotations[0]);
		}
	};

}
