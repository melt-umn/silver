
package silver.translation.java.driver;

// top::DriverAction ::= buildFileLocation::String buildXml::String 
public final class PgenBuild extends silver.driver.util.NDriverAction {

	public static final int i_buildFileLocation = 0;
	public static final int i_buildXml = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_driver_genBuild;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgenBuild(final Object c_buildFileLocation, final Object c_buildXml) {
		super();
		this.child_buildFileLocation = c_buildFileLocation;
		this.child_buildXml = c_buildXml;

	}

	private Object child_buildFileLocation;
	public final common.StringCatter getChild_buildFileLocation() {
		return (common.StringCatter) (child_buildFileLocation = common.Util.demand(child_buildFileLocation));
	}

	private Object child_buildXml;
	public final common.StringCatter getChild_buildXml() {
		return (common.StringCatter) (child_buildXml = common.Util.demand(child_buildXml));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_buildFileLocation: return getChild_buildFileLocation();
			case i_buildXml: return getChild_buildXml();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_buildFileLocation: return child_buildFileLocation;
			case i_buildXml: return child_buildXml;

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
		throw new common.exceptions.SilverInternalError("Production silver:translation:java:driver:genBuild erroneously claimed to forward");
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
		return "silver:translation:java:driver:genBuild";
	}

	static void initProductionAttributeDefinitions() {
		// top.io = writeFile(buildFileLocation, buildXml, top.ioIn)
		silver.translation.java.driver.PgenBuild.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PwriteFile.invoke(context.childAsIsLazy(silver.translation.java.driver.PgenBuild.i_buildFileLocation), context.childAsIsLazy(silver.translation.java.driver.PgenBuild.i_buildXml), context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } };
		// top.code = 0
		silver.translation.java.driver.PgenBuild.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.order = 6
		silver.translation.java.driver.PgenBuild.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)6); } };

	}

	public static final common.NodeFactory<PgenBuild> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgenBuild> {

		@Override
		public PgenBuild invoke(final Object[] children, final Object[] annotations) {
			return new PgenBuild(children[0], children[1]);
		}
	};

}
