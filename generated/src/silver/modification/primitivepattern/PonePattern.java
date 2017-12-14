
package silver.modification.primitivepattern;

// top::PrimPatterns ::= p::PrimPattern 
public final class PonePattern extends silver.modification.primitivepattern.NPrimPatterns {

	public static final int i_p = 0;


	public static final Class<?> childTypes[] = {silver.modification.primitivepattern.NPrimPattern.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_onePattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPatterns.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NPrimPatterns.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_p] = new common.Lazy[silver.modification.primitivepattern.NPrimPattern.num_inh_attrs];

	}

	public PonePattern(final Object c_p, final Object a_core_location) {
		super(a_core_location);
		this.child_p = c_p;

	}

	private Object child_p;
	public final silver.modification.primitivepattern.NPrimPattern getChild_p() {
		return (silver.modification.primitivepattern.NPrimPattern) (child_p = common.Util.demand(child_p));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:onePattern erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:onePattern";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = p.pp
		silver.modification.primitivepattern.PonePattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PonePattern.i_p).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// top.errors := p.errors
		if(silver.modification.primitivepattern.PonePattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns] == null)
			silver.modification.primitivepattern.PonePattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns);
		((common.CollectionAttribute)silver.modification.primitivepattern.PonePattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPatterns]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.primitivepattern.PonePattern.i_p).synthesized(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_PrimPattern)); } });
		// top.translation = p.translation
		silver.modification.primitivepattern.PonePattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PonePattern.i_p).synthesized(silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_PrimPattern)); } };
		// p.downSubst = top.downSubst
		silver.modification.primitivepattern.PonePattern.childInheritedAttributes[silver.modification.primitivepattern.PonePattern.i_p][silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_primitivepattern_PrimPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_downSubst__ON__silver_modification_primitivepattern_PrimPatterns)); } };
		// top.upSubst = p.upSubst
		silver.modification.primitivepattern.PonePattern.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_primitivepattern_PrimPatterns] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.modification.primitivepattern.PonePattern.i_p).synthesized(silver.modification.primitivepattern.Init.silver_analysis_typechecking_core_upSubst__ON__silver_modification_primitivepattern_PrimPattern)); } };

	}

	public static final common.NodeFactory<PonePattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PonePattern> {

		@Override
		public PonePattern invoke(final Object[] children, final Object[] annotations) {
			return new PonePattern(children[0], annotations[0]);
		}
	};

}
