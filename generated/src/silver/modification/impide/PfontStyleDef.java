
package silver.modification.impide;

// top::Def ::= d::EnvItem 
public final class PfontStyleDef extends silver.definition.env.NDef {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.NEnvItem.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_fontStyleDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NEnvItem.num_inh_attrs];

	}

	public PfontStyleDef(final Object c_d) {
		super();
		this.child_d = c_d;

	}

	private Object child_d;
	public final silver.definition.env.NEnvItem getChild_d() {
		return (silver.definition.env.NEnvItem) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:fontStyleDef erroneously claimed to forward");
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
		return "silver:modification:impide:fontStyleDef";
	}

	static void initProductionAttributeDefinitions() {
		// top.dcl = d.dcl
		silver.modification.impide.PfontStyleDef.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)context.childDecorated(silver.modification.impide.PfontStyleDef.i_d).synthesized(silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_EnvItem)); } };
		// top.fontDefList = [ d ]
		silver.modification.impide.PfontStyleDef.synthesizedAttributes[silver.modification.impide.Init.silver_modification_impide_fontDefList__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.impide.PfontStyleDef.i_d)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PfontStyleDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfontStyleDef> {

		@Override
		public PfontStyleDef invoke(final Object[] children, final Object[] annotations) {
			return new PfontStyleDef(children[0]);
		}
	};

}
