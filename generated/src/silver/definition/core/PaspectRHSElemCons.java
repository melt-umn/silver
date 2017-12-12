
package silver.definition.core;

// top::AspectRHS ::= h::AspectRHSElem t::AspectRHS 
public final class PaspectRHSElemCons extends silver.definition.core.NAspectRHS {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NAspectRHSElem.class,silver.definition.core.NAspectRHS.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_aspectRHSElemCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAspectRHS.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAspectRHS.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.core.NAspectRHSElem.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.core.NAspectRHS.num_inh_attrs];

	}

	public PaspectRHSElemCons(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.core.NAspectRHSElem getChild_h() {
		return (silver.definition.core.NAspectRHSElem) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.core.NAspectRHS getChild_t() {
		return (silver.definition.core.NAspectRHS) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:aspectRHSElemCons erroneously claimed to forward");
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
		return "silver:definition:core:aspectRHSElemCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ " " ++ t.pp
		silver.definition.core.PaspectRHSElemCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PaspectRHSElemCons.i_h).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectRHSElem)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PaspectRHSElemCons.i_t).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectRHS)))); } };
		// top.defs = h.defs ++ t.defs
		silver.definition.core.PaspectRHSElemCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AspectRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemCons.i_h, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AspectRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemCons.i_t, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_AspectRHS))); } };
		// top.errors := h.errors ++ t.errors
		if(silver.definition.core.PaspectRHSElemCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHS] == null)
			silver.definition.core.PaspectRHSElemCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHS] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHS);
		((common.CollectionAttribute)silver.definition.core.PaspectRHSElemCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHS]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemCons.i_h, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemCons.i_t, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AspectRHS))); } });
		// top.inputElements = h.inputElements ++ t.inputElements
		silver.definition.core.PaspectRHSElemCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_AspectRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemCons.i_h, silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_AspectRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemCons.i_t, silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_AspectRHS))); } };
		// h.deterministicCount = length(t.inputElements)
		silver.definition.core.PaspectRHSElemCons.childInheritedAttributes[silver.definition.core.PaspectRHSElemCons.i_h][silver.definition.core.Init.silver_definition_core_deterministicCount__ON__silver_definition_core_AspectRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PaspectRHSElemCons.i_t, silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_AspectRHS))); } };
		// h.realSignature = if null(top.realSignature) then [] else [ head(top.realSignature) ]
		silver.definition.core.PaspectRHSElemCons.childInheritedAttributes[silver.definition.core.PaspectRHSElemCons.i_h][silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHS))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHS))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } };
		// t.realSignature = if null(top.realSignature) then [] else tail(top.realSignature)
		silver.definition.core.PaspectRHSElemCons.childInheritedAttributes[silver.definition.core.PaspectRHSElemCons.i_t][silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHS))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Ptail.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectRHS)))); } };

	}

	public static final common.NodeFactory<PaspectRHSElemCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectRHSElemCons> {

		@Override
		public PaspectRHSElemCons invoke(final Object[] children, final Object[] annotations) {
			return new PaspectRHSElemCons(children[0], children[1], annotations[0]);
		}
	};

}
