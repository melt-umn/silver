
package silver.extension.doc.core;

// top::DocConfigs ::= 
public final class PnilConfigs extends silver.extension.doc.core.NDocConfigs {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_nilConfigs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NDocConfigs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NDocConfigs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilConfigs() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:nilConfigs erroneously claimed to forward");
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
		return "silver:extension:doc:core:nilConfigs";
	}

	static void initProductionAttributeDefinitions() {
		// top.header = ""
		silver.extension.doc.core.PnilConfigs.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.splitFiles = ""
		silver.extension.doc.core.PnilConfigs.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_splitFiles__ON__silver_extension_doc_core_DocConfigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.noDoc = false
		silver.extension.doc.core.PnilConfigs.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_noDoc__ON__silver_extension_doc_core_DocConfigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<PnilConfigs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilConfigs> {

		@Override
		public PnilConfigs invoke(final Object[] children, final Object[] annotations) {
			return new PnilConfigs();
		}
	};

}
