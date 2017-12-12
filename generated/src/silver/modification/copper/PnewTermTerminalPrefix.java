
package silver.modification.copper;

// top::TerminalPrefix ::= r::RegExpr 
public final class PnewTermTerminalPrefix extends silver.modification.copper.NTerminalPrefix {

	public static final int i_r = 0;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.NRegExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_newTermTerminalPrefix;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefix.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTerminalPrefix.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.concrete_syntax.NRegExpr.num_inh_attrs];

	}

	public PnewTermTerminalPrefix(final Object c_r, final Object a_core_location) {
		super(a_core_location);
		this.child_r = c_r;

	}

	private Object child_r;
	public final silver.definition.concrete_syntax.NRegExpr getChild_r() {
		return (silver.definition.concrete_syntax.NRegExpr) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;

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
		return ((silver.modification.copper.NTerminalPrefix)new silver.modification.copper.PnewTermModifiersTerminalPrefix(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PnewTermTerminalPrefix.i_r)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.NTerminalModifiers)new silver.definition.concrete_syntax.PterminalModifiersNone(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTerminalPrefix)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:copper:newTermTerminalPrefix";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = r.pp
		silver.modification.copper.PnewTermTerminalPrefix.synthesizedAttributes[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefix] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.copper.PnewTermTerminalPrefix.i_r).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_RegExpr)); } };

	}

	public static final common.NodeFactory<PnewTermTerminalPrefix> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnewTermTerminalPrefix> {

		@Override
		public PnewTermTerminalPrefix invoke(final Object[] children, final Object[] annotations) {
			return new PnewTermTerminalPrefix(children[0], annotations[0]);
		}
	};

}
