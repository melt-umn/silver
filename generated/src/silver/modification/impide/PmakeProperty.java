
package silver.modification.impide;

// top::Property ::= 'property' pname::IdLower_t ptype::TypeName options::IdePropertyOptions ';' 
public final class PmakeProperty extends silver.modification.impide.NProperty {

	public static final int i__G_4 = 0;
	public static final int i_pname = 1;
	public static final int i_ptype = 2;
	public static final int i_options = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.modification.impide.TImpIde_OptFunc_Property.class,silver.definition.core.TIdLower_t.class,silver.modification.impide.NTypeName.class,silver.modification.impide.NIdePropertyOptions.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_makeProperty;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NProperty.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NProperty.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ptype] = new common.Lazy[silver.modification.impide.NTypeName.num_inh_attrs];
	childInheritedAttributes[i_options] = new common.Lazy[silver.modification.impide.NIdePropertyOptions.num_inh_attrs];

	}

	public PmakeProperty(final Object c__G_4, final Object c_pname, final Object c_ptype, final Object c_options, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_pname = c_pname;
		this.child_ptype = c_ptype;
		this.child_options = c_options;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.modification.impide.TImpIde_OptFunc_Property getChild__G_4() {
		return (silver.modification.impide.TImpIde_OptFunc_Property) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_pname;
	public final silver.definition.core.TIdLower_t getChild_pname() {
		return (silver.definition.core.TIdLower_t) (child_pname = common.Util.demand(child_pname));
	}

	private Object child_ptype;
	public final silver.modification.impide.NTypeName getChild_ptype() {
		return (silver.modification.impide.NTypeName) (child_ptype = common.Util.demand(child_ptype));
	}

	private Object child_options;
	public final silver.modification.impide.NIdePropertyOptions getChild_options() {
		return (silver.modification.impide.NIdePropertyOptions) (child_options = common.Util.demand(child_options));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_pname: return getChild_pname();
			case i_ptype: return getChild_ptype();
			case i_options: return getChild_options();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_pname: return child_pname;
			case i_ptype: return child_ptype;
			case i_options: return child_options;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:makeProperty erroneously claimed to forward");
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
		return "silver:modification:impide:makeProperty";
	}

	static void initProductionAttributeDefinitions() {
		// optional = null(options.propRequired)
		silver.modification.impide.PmakeProperty.localAttributes[silver.modification.impide.Init.optional__ON__silver_modification_impide_makeProperty] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PmakeProperty.i_options, silver.modification.impide.Init.silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOptions))); } };
		// defaultVal = if null(options.propDefault) then "" else head(options.propDefault)
		silver.modification.impide.PmakeProperty.localAttributes[silver.modification.impide.Init.defaultVal__ON__silver_modification_impide_makeProperty] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PmakeProperty.i_options, silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOptions))) ? (new common.StringCatter("")) : ((common.StringCatter)core.Phead.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PmakeProperty.i_options, silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOptions)))); } };
		// displayName = if null(options.propDisplay) then pname.lexeme else head(options.propDisplay)
		silver.modification.impide.PmakeProperty.localAttributes[silver.modification.impide.Init.displayName__ON__silver_modification_impide_makeProperty] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PmakeProperty.i_options, silver.modification.impide.Init.silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOptions))) ? ((common.StringCatter)((silver.definition.core.TIdLower_t)context.childAsIs(silver.modification.impide.PmakeProperty.i_pname)).lexeme) : ((common.StringCatter)core.Phead.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PmakeProperty.i_options, silver.modification.impide.Init.silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOptions)))); } };
		// top.propDcls = [ makeIdeProperty(pname.lexeme, ptype.propType, optional, defaultVal, displayName) ]
		silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_Property] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.impide.spec.NIdeProperty)new silver.modification.impide.spec.PmakeIdeProperty(((common.StringCatter)((silver.definition.core.TIdLower_t)context.childAsIs(silver.modification.impide.PmakeProperty.i_pname)).lexeme), context.childDecoratedSynthesizedLazy(silver.modification.impide.PmakeProperty.i_ptype, silver.modification.impide.Init.silver_modification_impide_propType__ON__silver_modification_impide_TypeName), context.localAsIsLazy(silver.modification.impide.Init.optional__ON__silver_modification_impide_makeProperty), context.localAsIsLazy(silver.modification.impide.Init.defaultVal__ON__silver_modification_impide_makeProperty), context.localAsIsLazy(silver.modification.impide.Init.displayName__ON__silver_modification_impide_makeProperty))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.errors := []
		if(silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property] == null)
			silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property);
		((common.CollectionAttribute)silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.errors <- if length(options.propRequired) > 1 then [ err(top.location, "Multiple 'required' declarations") ] else []
		if(silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property] == null)
			silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property);
		((common.CollectionAttribute)silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PmakeProperty.i_options, silver.modification.impide.Init.silver_modification_impide_propRequired__ON__silver_modification_impide_IdePropertyOptions))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.impide.NProperty)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Multiple 'required' declarations")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if length(options.propDefault) > 1 then [ err(top.location, "Multiple 'default' declarations") ] else []
		if(silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property] == null)
			silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property);
		((common.CollectionAttribute)silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PmakeProperty.i_options, silver.modification.impide.Init.silver_modification_impide_propDefault__ON__silver_modification_impide_IdePropertyOptions))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.impide.NProperty)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Multiple 'default' declarations")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.errors <- if length(options.propDisplay) > 1 then [ err(top.location, "Multiple 'display' declarations") ] else []
		if(silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property] == null)
			silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property);
		((common.CollectionAttribute)silver.modification.impide.PmakeProperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PmakeProperty.i_options, silver.modification.impide.Init.silver_modification_impide_propDisplay__ON__silver_modification_impide_IdePropertyOptions))) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.impide.NProperty)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Multiple 'display' declarations")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PmakeProperty> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmakeProperty> {

		@Override
		public PmakeProperty invoke(final Object[] children, final Object[] annotations) {
			return new PmakeProperty(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
