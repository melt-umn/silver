
package silver.extension.doc.driver;

// top::DriverAction ::= a::Decorated CmdArgs specs::[Decorated RootSpec] outputLoc::String 
public final class PgenDoc extends silver.driver.util.NDriverAction {

	public static final int i_a = 0;
	public static final int i_specs = 1;
	public static final int i_outputLoc = 2;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_doc_driver_genDoc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgenDoc(final Object c_a, final Object c_specs, final Object c_outputLoc) {
		super();
		this.child_a = c_a;
		this.child_specs = c_specs;
		this.child_outputLoc = c_outputLoc;

	}

	private Object child_a;
	public final common.DecoratedNode getChild_a() {
		return (common.DecoratedNode) (child_a = common.Util.demand(child_a));
	}

	private Object child_specs;
	public final common.ConsCell getChild_specs() {
		return (common.ConsCell) (child_specs = common.Util.demand(child_specs));
	}

	private Object child_outputLoc;
	public final common.StringCatter getChild_outputLoc() {
		return (common.StringCatter) (child_outputLoc = common.Util.demand(child_outputLoc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_specs: return getChild_specs();
			case i_outputLoc: return getChild_outputLoc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_specs: return child_specs;
			case i_outputLoc: return child_outputLoc;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:doc:driver:genDoc erroneously claimed to forward");
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
		return "silver:extension:doc:driver:genDoc";
	}

	static void initProductionAttributeDefinitions() {
		// pr = print("Generating Documentation.\n", top.ioIn)
		silver.extension.doc.driver.PgenDoc.localAttributes[silver.extension.doc.driver.Init.pr__ON__silver_extension_doc_driver_genDoc] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke((new common.StringCatter("Generating Documentation.\n")), context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } };
		// top.io = writeAll(pr, a, specs, outputLoc)
		silver.extension.doc.driver.PgenDoc.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)silver.extension.doc.driver.PwriteAll.invoke(context.localAsIsLazy(silver.extension.doc.driver.Init.pr__ON__silver_extension_doc_driver_genDoc), context.childAsIsLazy(silver.extension.doc.driver.PgenDoc.i_a), context.childAsIsLazy(silver.extension.doc.driver.PgenDoc.i_specs), context.childAsIsLazy(silver.extension.doc.driver.PgenDoc.i_outputLoc))); } };
		// top.code = 0
		silver.extension.doc.driver.PgenDoc.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.order = 4
		silver.extension.doc.driver.PgenDoc.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)4); } };

	}

	public static final common.NodeFactory<PgenDoc> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgenDoc> {

		@Override
		public PgenDoc invoke(final Object[] children, final Object[] annotations) {
			return new PgenDoc(children[0], children[1], children[2]);
		}
	};

}
