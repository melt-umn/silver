
package silver.extension.doc.core;

// top::DocDclInfo ::= id::String file::String path::String 
public final class PfunctionDocDclInfoP extends silver.extension.doc.core.NDocDclInfo {

	public static final int i_id = 0;
	public static final int i_file = 1;
	public static final int i_path = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_core_functionDocDclInfoP;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.doc.core.NDocDclInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.doc.core.NDocDclInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PfunctionDocDclInfoP(final Object c_id, final Object c_file, final Object c_path) {
		super();
		this.child_id = c_id;
		this.child_file = c_file;
		this.child_path = c_path;

	}

	private Object child_id;
	public final common.StringCatter getChild_id() {
		return (common.StringCatter) (child_id = common.Util.demand(child_id));
	}

	private Object child_file;
	public final common.StringCatter getChild_file() {
		return (common.StringCatter) (child_file = common.Util.demand(child_file));
	}

	private Object child_path;
	public final common.StringCatter getChild_path() {
		return (common.StringCatter) (child_path = common.Util.demand(child_path));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i_file: return getChild_file();
			case i_path: return getChild_path();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i_file: return child_file;
			case i_path: return child_path;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:core:functionDocDclInfoP erroneously claimed to forward");
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
		return "silver:extension:doc:core:functionDocDclInfoP";
	}

	static void initProductionAttributeDefinitions() {
		// top.id = id
		silver.extension.doc.core.PfunctionDocDclInfoP.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_id__ON__silver_extension_doc_core_DocDclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.extension.doc.core.PfunctionDocDclInfoP.i_id)); } };
		// top.file = file
		silver.extension.doc.core.PfunctionDocDclInfoP.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_file__ON__silver_extension_doc_core_DocDclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.extension.doc.core.PfunctionDocDclInfoP.i_file)); } };
		// top.path = path
		silver.extension.doc.core.PfunctionDocDclInfoP.synthesizedAttributes[silver.extension.doc.core.Init.silver_extension_doc_core_path__ON__silver_extension_doc_core_DocDclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.extension.doc.core.PfunctionDocDclInfoP.i_path)); } };

	}

	public static final common.NodeFactory<PfunctionDocDclInfoP> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfunctionDocDclInfoP> {

		@Override
		public PfunctionDocDclInfoP invoke(final Object[] children, final Object[] annotations) {
			return new PfunctionDocDclInfoP(children[0], children[1], children[2]);
		}
	};

}
