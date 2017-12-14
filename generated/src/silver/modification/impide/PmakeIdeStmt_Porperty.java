
package silver.modification.impide;

// top::IdeStmt ::= prop::Property 
public final class PmakeIdeStmt_Porperty extends silver.modification.impide.NIdeStmt {

	public static final int i_prop = 0;


	public static final Class<?> childTypes[] = {silver.modification.impide.NProperty.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_makeIdeStmt_Porperty;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NIdeStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_prop] = new common.Lazy[silver.modification.impide.NProperty.num_inh_attrs];

	}

	public PmakeIdeStmt_Porperty(final Object c_prop, final Object a_core_location) {
		super(a_core_location);
		this.child_prop = c_prop;

	}

	private Object child_prop;
	public final silver.modification.impide.NProperty getChild_prop() {
		return (silver.modification.impide.NProperty) (child_prop = common.Util.demand(child_prop));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prop: return getChild_prop();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prop: return child_prop;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:makeIdeStmt_Porperty erroneously claimed to forward");
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
		return "silver:modification:impide:makeIdeStmt_Porperty";
	}

	static void initProductionAttributeDefinitions() {
		// top.propDcls = prop.propDcls
		silver.modification.impide.PmakeIdeStmt_Porperty.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_IdeStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.impide.PmakeIdeStmt_Porperty.i_prop).synthesized(silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_Property)); } };
		// top.errors := prop.errors
		if(silver.modification.impide.PmakeIdeStmt_Porperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] == null)
			silver.modification.impide.PmakeIdeStmt_Porperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt);
		((common.CollectionAttribute)silver.modification.impide.PmakeIdeStmt_Porperty.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_IdeStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.impide.PmakeIdeStmt_Porperty.i_prop).synthesized(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property)); } });

	}

	public static final common.NodeFactory<PmakeIdeStmt_Porperty> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmakeIdeStmt_Porperty> {

		@Override
		public PmakeIdeStmt_Porperty invoke(final Object[] children, final Object[] annotations) {
			return new PmakeIdeStmt_Porperty(children[0], annotations[0]);
		}
	};

}
