
package silver.extension.doc.core;

// top::DocConfig ::= 'no-doc' ':' value::ConfigValue_t 
public final class PnoDocConfig extends silver.extension.doc.core.NDocConfig {

	public static final int i__G_2 = 0;
	public static final int i__G_1 = 1;
	public static final int i_value = 2;


	public static final Class<?> childTypes[] = {silver.extension.doc.core.TConfigNoDoc_t.class,silver.definition.core.TColon_t.class,silver.extension.doc.core.TConfigValue_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_noDocConfig;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NDocConfig.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NDocConfig.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnoDocConfig(final Object c__G_2, final Object c__G_1, final Object c_value) {
		super();
		this.child__G_2 = c__G_2;
		this.child__G_1 = c__G_1;
		this.child_value = c_value;

	}

	private Object child__G_2;
	public final silver.extension.doc.core.TConfigNoDoc_t getChild__G_2() {
		return (silver.extension.doc.core.TConfigNoDoc_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child__G_1;
	public final silver.definition.core.TColon_t getChild__G_1() {
		return (silver.definition.core.TColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_value;
	public final silver.extension.doc.core.TConfigValue_t getChild_value() {
		return (silver.extension.doc.core.TConfigValue_t) (child_value = common.Util.demand(child_value));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i__G_1: return getChild__G_1();
			case i_value: return getChild_value();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i__G_1: return child__G_1;
			case i_value: return child_value;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:noDocConfig erroneously claimed to forward");
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
		return "silver:extension:doc:core:noDocConfig";
	}

	static void initProductionAttributeDefinitions() {
		// top.header = ""
		silver.extension.doc.core.PnoDocConfig.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_header__ON__silver_extension_doc_core_DocConfig] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.splitFiles = ""
		silver.extension.doc.core.PnoDocConfig.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_splitFiles__ON__silver_extension_doc_core_DocConfig] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.noDoc = if "true" == value.lexeme then true else false
		silver.extension.doc.core.PnoDocConfig.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_noDoc__ON__silver_extension_doc_core_DocConfig] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((new common.StringCatter("true")).equals(((common.StringCatter)((silver.extension.doc.core.TConfigValue_t)context.childAsIs(silver.extension.doc.core.PnoDocConfig.i_value)).lexeme)) ? true : false); } };

	}

	public static final common.NodeFactory<PnoDocConfig> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnoDocConfig> {

		@Override
		public PnoDocConfig invoke(final Object[] children, final Object[] annotations) {
			return new PnoDocConfig(children[0], children[1], children[2]);
		}
	};

}
