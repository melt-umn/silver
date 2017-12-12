
package silver.modification.copper;

// top::TermList ::= h::QName t::TermList 
public final class PtermList extends silver.modification.copper.NTermList {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.modification.copper.NTermList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_termList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTermList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTermList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.modification.copper.NTermList.num_inh_attrs];

	}

	public PtermList(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.core.NQName getChild_h() {
		return (silver.definition.core.NQName) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.modification.copper.NTermList getChild_t() {
		return (silver.modification.copper.NTermList) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:termList erroneously claimed to forward");
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
		return "silver:modification:copper:termList";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = if t.pp == "" then h.pp else h.pp ++ ", " ++ t.pp
		silver.modification.copper.PtermList.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)context.childDecorated(silver.modification.copper.PtermList.i_t).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermList)).equals((new common.StringCatter(""))) ? ((common.StringCatter)context.childDecorated(silver.modification.copper.PtermList.i_h).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)) : new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PtermList.i_h).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PtermList.i_t).synthesized(silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermList))))); } };
		// fName = h.lookupType.dcl.fullName
		silver.modification.copper.PtermList.localAttributes[silver.modification.copper.Init.fName__ON__silver_modification_copper_termList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PtermList.i_h).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo)); } };
		// top.termList = [ fName ] ++ t.termList
		silver.modification.copper.PtermList.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_termList__ON__silver_modification_copper_TermList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_termList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PtermList.i_t, silver.modification.copper.Init.silver_modification_copper_termList__ON__silver_modification_copper_TermList))); } };
		// top.defs = if null(h.lookupType.dcls) then t.defs else (pluckTermDef(top.grammarName, h.location, fName) :: t.defs)
		silver.modification.copper.PtermList.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_TermList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PtermList.i_h).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)context.childDecorated(silver.modification.copper.PtermList.i_t).synthesized(silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_TermList)) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.copper.PpluckTermDef.invoke(context.contextInheritedLazy(silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_TermList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.modification.copper.PtermList.i_h).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_termList))); } }, context.childDecoratedSynthesizedLazy(silver.modification.copper.PtermList.i_t, silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_TermList)))); } };
		// top.errors := t.errors
		if(silver.modification.copper.PtermList.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList] == null)
			silver.modification.copper.PtermList.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList);
		((common.CollectionAttribute)silver.modification.copper.PtermList.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.copper.PtermList.i_t).synthesized(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList)); } });
		// top.errors <- if null(h.lookupType.dcls) then [ err(h.location, "Undeclared terminal '" ++ h.name ++ "'.") ] else if length(h.lookupType.dcls) > 1 then [ err(h.location, "Ambiguous reference to terminal '" ++ h.name ++ "'. Possibilities are:\n" ++ printPossibilities(h.lookupType.dcls)) ] else []
		if(silver.modification.copper.PtermList.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList] == null)
			silver.modification.copper.PtermList.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList);
		((common.CollectionAttribute)silver.modification.copper.PtermList.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PtermList.i_h).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.modification.copper.PtermList.i_h).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Undeclared terminal '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PtermList.i_h).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter("'.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PtermList.i_h).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.modification.copper.PtermList.i_h).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Ambiguous reference to terminal '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PtermList.i_h).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("'. Possibilities are:\n")), (common.StringCatter)((common.StringCatter)silver.definition.core.PprintPossibilities.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PtermList.i_h).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_dcls__ON__silver_definition_core_QNameLookup)); } }))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke()))); } });

	}

	public static final common.NodeFactory<PtermList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtermList> {

		@Override
		public PtermList invoke(final Object[] children, final Object[] annotations) {
			return new PtermList(children[0], children[1], annotations[0]);
		}
	};

}
