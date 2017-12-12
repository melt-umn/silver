
package silver.definition.core;

// top::WithElem ::= n::QName 'as' newname::QName 
public final class PwithElement extends silver.definition.core.NWithElem {

	public static final int i_n = 0;
	public static final int i__G_1 = 1;
	public static final int i_newname = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TAs_kwd.class,silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_withElement;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NWithElem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NWithElem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_newname] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PwithElement(final Object c_n, final Object c__G_1, final Object c_newname, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;
		this.child__G_1 = c__G_1;
		this.child_newname = c_newname;

	}

	private Object child_n;
	public final silver.definition.core.NQName getChild_n() {
		return (silver.definition.core.NQName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_1;
	public final silver.definition.core.TAs_kwd getChild__G_1() {
		return (silver.definition.core.TAs_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_newname;
	public final silver.definition.core.NQName getChild_newname() {
		return (silver.definition.core.NQName) (child_newname = common.Util.demand(child_newname));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i__G_1: return getChild__G_1();
			case i_newname: return getChild_newname();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i__G_1: return child__G_1;
			case i_newname: return child_newname;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:withElement erroneously claimed to forward");
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
		return "silver:definition:core:withElement";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = n.pp ++ " as " ++ newname.pp
		silver.definition.core.PwithElement.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_WithElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PwithElement.i_n).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" as ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PwithElement.i_newname).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)))); } };
		// top.envMaps = [ pair(n.name, newname.name) ]
		silver.definition.core.PwithElement.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_envMaps__ON__silver_definition_core_WithElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.definition.core.PwithElement.i_n, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.childDecoratedSynthesizedLazy(silver.definition.core.PwithElement.i_newname, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PwithElement> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PwithElement> {

		@Override
		public PwithElement invoke(final Object[] children, final Object[] annotations) {
			return new PwithElement(children[0], children[1], children[2], annotations[0]);
		}
	};

}
