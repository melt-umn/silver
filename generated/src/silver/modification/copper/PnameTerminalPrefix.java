
package silver.modification.copper;

// top::TerminalPrefix ::= s::QName 
public final class PnameTerminalPrefix extends silver.modification.copper.NTerminalPrefix {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_nameTerminalPrefix;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefix.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefix.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PnameTerminalPrefix(final Object c_s, final Object a_core_location) {
		super(a_core_location);
		this.child_s = c_s;

	}

	private Object child_s;
	public final silver.definition.core.NQName getChild_s() {
		return (silver.definition.core.NQName) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:nameTerminalPrefix erroneously claimed to forward");
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
		return "silver:modification:copper:nameTerminalPrefix";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = s.pp
		silver.modification.copper.PnameTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.copper.PnameTerminalPrefix.i_s).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.errors := s.lookupType.errors
		if(silver.modification.copper.PnameTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix] == null)
			silver.modification.copper.PnameTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix] = new silver.definition.core.CAerrors(silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix);
		((common.CollectionAttribute)silver.modification.copper.PnameTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PnameTerminalPrefix.i_s).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } });
		// top.liftedAGDcls = emptyAGDcl(location=top.location)
		silver.modification.copper.PnameTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_TerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } })); } };
		// top.prefixFullName = s.lookupType.fullName
		silver.modification.copper.PnameTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_prefixFullName__ON__silver_modification_copper_TerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childDecorated(silver.modification.copper.PnameTerminalPrefix.i_s).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } };

	}

	public static final common.NodeFactory<PnameTerminalPrefix> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnameTerminalPrefix> {

		@Override
		public PnameTerminalPrefix invoke(final Object[] children, final Object[] annotations) {
			return new PnameTerminalPrefix(children[0], annotations[0]);
		}
	};

}
