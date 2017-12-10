
package silver.support.monto.products;

// top::DirectoryEntry ::= name::String absolutePath::String entryType::DirectoryEntryType 
public final class PdirectoryEntry extends silver.support.monto.products.NDirectoryEntry {

	public static final int i_name = 0;
	public static final int i_absolutePath = 1;
	public static final int i_entryType = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,silver.support.monto.products.NDirectoryEntryType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_products_directoryEntry;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.products.NDirectoryEntry.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.products.NDirectoryEntry.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_entryType] = new common.Lazy[silver.support.monto.products.NDirectoryEntryType.num_inh_attrs];

	}

	public PdirectoryEntry(final Object c_name, final Object c_absolutePath, final Object c_entryType) {
		super();
		this.child_name = c_name;
		this.child_absolutePath = c_absolutePath;
		this.child_entryType = c_entryType;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_absolutePath;
	public final common.StringCatter getChild_absolutePath() {
		return (common.StringCatter) (child_absolutePath = common.Util.demand(child_absolutePath));
	}

	private Object child_entryType;
	public final silver.support.monto.products.NDirectoryEntryType getChild_entryType() {
		return (silver.support.monto.products.NDirectoryEntryType) (child_entryType = common.Util.demand(child_entryType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_absolutePath: return getChild_absolutePath();
			case i_entryType: return getChild_entryType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_absolutePath: return child_absolutePath;
			case i_entryType: return child_entryType;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:products:directoryEntry erroneously claimed to forward");
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
		return "silver:support:monto:products:directoryEntry";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = jsonObject(obj)
		silver.support.monto.products.PdirectoryEntry.synthesizedAttributes[silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_DirectoryEntry] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(context.localAsIsLazy(silver.support.monto.products.Init.obj__ON__silver_support_monto_products_directoryEntry))); } };
		// obj = [ pair("name", jsonString(name)), pair("absolute_path", jsonString(absolutePath)), pair("type", entryType.json) ]
		silver.support.monto.products.PdirectoryEntry.localAttributes[silver.support.monto.products.Init.obj__ON__silver_support_monto_products_directoryEntry] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("name")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.childAsIsLazy(silver.support.monto.products.PdirectoryEntry.i_name))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("absolute_path")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.childAsIsLazy(silver.support.monto.products.PdirectoryEntry.i_absolutePath))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("type")), context.childDecoratedSynthesizedLazy(silver.support.monto.products.PdirectoryEntry.i_entryType, silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_DirectoryEntryType))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PdirectoryEntry> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdirectoryEntry> {

		@Override
		public PdirectoryEntry invoke(final Object[] children, final Object[] annotations) {
			return new PdirectoryEntry(children[0], children[1], children[2]);
		}
	};

}
