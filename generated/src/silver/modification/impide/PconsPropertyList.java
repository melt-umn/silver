
package silver.modification.impide;

// top::PropertyList ::= p::Property pList::PropertyList 
public final class PconsPropertyList extends silver.modification.impide.NPropertyList {

	public static final int i_p = 0;
	public static final int i_pList = 1;


	public static final Class<?> childTypes[] = {silver.modification.impide.NProperty.class,silver.modification.impide.NPropertyList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_consPropertyList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.NPropertyList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.NPropertyList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_p] = new common.Lazy[silver.modification.impide.NProperty.num_inh_attrs];
	childInheritedAttributes[i_pList] = new common.Lazy[silver.modification.impide.NPropertyList.num_inh_attrs];

	}

	public PconsPropertyList(final Object c_p, final Object c_pList) {
		super();
		this.child_p = c_p;
		this.child_pList = c_pList;

	}

	private Object child_p;
	public final silver.modification.impide.NProperty getChild_p() {
		return (silver.modification.impide.NProperty) (child_p = common.Util.demand(child_p));
	}

	private Object child_pList;
	public final silver.modification.impide.NPropertyList getChild_pList() {
		return (silver.modification.impide.NPropertyList) (child_pList = common.Util.demand(child_pList));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();
			case i_pList: return getChild_pList();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;
			case i_pList: return child_pList;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:consPropertyList erroneously claimed to forward");
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
		return "silver:modification:impide:consPropertyList";
	}

	static void initProductionAttributeDefinitions() {
		// top.propDcls = p.propDcls ++ pList.propDcls
		silver.modification.impide.PconsPropertyList.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_PropertyList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyList.i_p, silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_Property), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyList.i_pList, silver.modification.impide.Init.silver_modification_impide_propDcls__ON__silver_modification_impide_PropertyList))); } };
		// top.errors := p.errors ++ pList.errors
		if(silver.modification.impide.PconsPropertyList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList] == null)
			silver.modification.impide.PconsPropertyList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList] = new silver.definition.core.CAerrors(silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList);
		((common.CollectionAttribute)silver.modification.impide.PconsPropertyList.synthesizedAttributes[silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyList.i_p, silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_Property), context.childDecoratedSynthesizedLazy(silver.modification.impide.PconsPropertyList.i_pList, silver.modification.impide.Init.silver_definition_core_errors__ON__silver_modification_impide_PropertyList))); } });

	}

	public static final common.NodeFactory<PconsPropertyList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsPropertyList> {

		@Override
		public PconsPropertyList invoke(final Object[] children, final Object[] annotations) {
			return new PconsPropertyList(children[0], children[1]);
		}
	};

}
