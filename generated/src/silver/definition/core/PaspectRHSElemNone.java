
package silver.definition.core;

// top::AspectRHSElem ::= '_' 
public final class PaspectRHSElemNone extends silver.definition.core.NAspectRHSElem {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TUnderScore_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_aspectRHSElemNone;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAspectRHSElem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAspectRHSElem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PaspectRHSElemNone(final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.definition.core.TUnderScore_t getChild__G_0() {
		return (silver.definition.core.TUnderScore_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAspectRHSElem)new silver.definition.core.PaspectRHSElemFull(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("p_")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.inherited(silver.definition.core.Init.silver_definition_core_deterministicCount__ON__silver_definition_core_AspectRHSElem))))); } }, ((core.NLocation)((silver.definition.core.TUnderScore_t)context.childAsIs(silver.definition.core.PaspectRHSElemNone.i__G_0)).location))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.rType__ON__silver_definition_core_aspectRHSElemNone)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAspectRHSElem)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:aspectRHSElemNone";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "_"
		silver.definition.core.PaspectRHSElemNone.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("_")); } };
		// rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep
		silver.definition.core.PaspectRHSElemNone.localAttributes[silver.definition.core.Init.rType__ON__silver_definition_core_aspectRHSElemNone] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHSElem))) ? ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()) : ((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHSElem))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement))); } };

	}

	public static final common.NodeFactory<PaspectRHSElemNone> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectRHSElemNone> {

		@Override
		public PaspectRHSElemNone invoke(final Object[] children, final Object[] annotations) {
			return new PaspectRHSElemNone(children[0], annotations[0]);
		}
	};

}
