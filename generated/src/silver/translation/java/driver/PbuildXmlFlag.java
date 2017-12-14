
package silver.translation.java.driver;

// top::CmdArgs ::= s::String rest::CmdArgs 
public final class PbuildXmlFlag extends silver.util.cmdargs.NCmdArgs {

	public static final int i_s = 0;
	public static final int i_rest = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.util.cmdargs.NCmdArgs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_driver_buildXmlFlag;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_rest] = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_inh_attrs];

	}

	public PbuildXmlFlag(final Object c_s, final Object c_rest) {
		super();
		this.child_s = c_s;
		this.child_rest = c_rest;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_rest;
	public final silver.util.cmdargs.NCmdArgs getChild_rest() {
		return (silver.util.cmdargs.NCmdArgs) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_rest: return child_rest;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return context.childDecorated(silver.translation.java.driver.PbuildXmlFlag.i_rest).undecorate();
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
		return "silver:translation:java:driver:buildXmlFlag";
	}

	static void initProductionAttributeDefinitions() {
		// top.buildXmlLocation = (s :: forward.buildXmlLocation)
		silver.translation.java.driver.PbuildXmlFlag.synthesizedAttributes[silver.translation.java.driver.Init.silver_translation_java_driver_buildXmlLocation__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.translation.java.driver.PbuildXmlFlag.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.forward().synthesized(silver.translation.java.driver.Init.silver_translation_java_driver_buildXmlLocation__ON__silver_util_cmdargs_CmdArgs)); } })); } };

	}

	public static final common.NodeFactory<PbuildXmlFlag> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbuildXmlFlag> {

		@Override
		public PbuildXmlFlag invoke(final Object[] children, final Object[] annotations) {
			return new PbuildXmlFlag(children[0], children[1]);
		}
	};

}
