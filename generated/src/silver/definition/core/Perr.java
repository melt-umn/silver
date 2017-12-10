
package silver.definition.core;

// top::Message ::= l::Location s::String 
public final class Perr extends silver.definition.core.NMessage {

	public static final int i_l = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = {core.NLocation.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_err;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NMessage.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NMessage.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public Perr(final Object c_l, final Object c_s) {
		super();
		this.child_l = c_l;
		this.child_s = c_s;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:err erroneously claimed to forward");
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
		return "silver:definition:core:err";
	}

	static void initProductionAttributeDefinitions() {
		// top.loc = l
		silver.definition.core.Perr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_loc__ON__silver_definition_core_Message] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.core.Perr.i_l).undecorate(); } };
		// top.pp = l.filename ++ ":" ++ toString(l.line) ++ ":" ++ toString(l.column) ++ ": error: " ++ s
		silver.definition.core.Perr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Message] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.Perr.i_l).synthesized(core.Init.core_filename__ON__core_Location)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childDecorated(silver.definition.core.Perr.i_l).synthesized(core.Init.core_line__ON__core_Location)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childDecorated(silver.definition.core.Perr.i_l).synthesized(core.Init.core_column__ON__core_Location)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(": error: ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.core.Perr.i_s)))))))); } };
		// top.severity = 2
		silver.definition.core.Perr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_severity__ON__silver_definition_core_Message] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)2); } };
		// top.msg = s
		silver.definition.core.Perr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_msg__ON__silver_definition_core_Message] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.core.Perr.i_s)); } };

	}

	public static final common.NodeFactory<Perr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Perr> {

		@Override
		public Perr invoke(final Object[] children, final Object[] annotations) {
			return new Perr(children[0], children[1]);
		}
	};

}
