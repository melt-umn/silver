
package silver.extension.bidirtransform;

// top::Expr ::= rules::[TransformRule] ns::Decorated NamedSignature absGroup::Decorated NonterminalList cncGroup::Decorated NonterminalList 
public final class PapplyTrans extends silver.definition.core.NExpr {

	public static final int i_rules = 0;
	public static final int i_ns = 1;
	public static final int i_absGroup = 2;
	public static final int i_cncGroup = 3;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_applyTrans;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PapplyTrans(final Object c_rules, final Object c_ns, final Object c_absGroup, final Object c_cncGroup, final Object a_core_location) {
		super(a_core_location);
		this.child_rules = c_rules;
		this.child_ns = c_ns;
		this.child_absGroup = c_absGroup;
		this.child_cncGroup = c_cncGroup;

	}

	private Object child_rules;
	public final common.ConsCell getChild_rules() {
		return (common.ConsCell) (child_rules = common.Util.demand(child_rules));
	}

	private Object child_ns;
	public final common.DecoratedNode getChild_ns() {
		return (common.DecoratedNode) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_absGroup;
	public final common.DecoratedNode getChild_absGroup() {
		return (common.DecoratedNode) (child_absGroup = common.Util.demand(child_absGroup));
	}

	private Object child_cncGroup;
	public final common.DecoratedNode getChild_cncGroup() {
		return (common.DecoratedNode) (child_cncGroup = common.Util.demand(child_cncGroup));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rules: return getChild_rules();
			case i_ns: return getChild_ns();
			case i_absGroup: return getChild_absGroup();
			case i_cncGroup: return getChild_cncGroup();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rules: return child_rules;
			case i_ns: return child_ns;
			case i_absGroup: return child_absGroup;
			case i_cncGroup: return child_cncGroup;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return ((silver.definition.core.NExpr)((common.NodeFactory<silver.definition.core.NExpr>)context.localDecorated(silver.extension.bidirtransform.Init.trans__ON__silver_extension_bidirtransform_applyTrans).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_outputStmt__ON__silver_extension_bidirtransform_TransformRule)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PnsApply(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.localDecorated(silver.extension.bidirtransform.Init.trans__ON__silver_extension_bidirtransform_applyTrans).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_TransformRule)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }}, null));
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
		return "silver:extension:bidirtransform:applyTrans";
	}

	static void initProductionAttributeDefinitions() {
		// trans = getTrans(rules, ns, absGroup, cncGroup,location=top.location)
		silver.extension.bidirtransform.PapplyTrans.localAttributes[silver.extension.bidirtransform.Init.trans__ON__silver_extension_bidirtransform_applyTrans] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NTransformRule)new silver.extension.bidirtransform.PgetTrans(context.childAsIsLazy(silver.extension.bidirtransform.PapplyTrans.i_rules), context.childAsIsLazy(silver.extension.bidirtransform.PapplyTrans.i_ns), context.childAsIsLazy(silver.extension.bidirtransform.PapplyTrans.i_absGroup), context.childAsIsLazy(silver.extension.bidirtransform.PapplyTrans.i_cncGroup), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } };
		// trans.absGroup = absGroup
		silver.extension.bidirtransform.PapplyTrans.localInheritedAttributes[silver.extension.bidirtransform.Init.trans__ON__silver_extension_bidirtransform_applyTrans][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PapplyTrans.i_absGroup)); } };
		// trans.cncGroup = cncGroup
		silver.extension.bidirtransform.PapplyTrans.localInheritedAttributes[silver.extension.bidirtransform.Init.trans__ON__silver_extension_bidirtransform_applyTrans][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PapplyTrans.i_cncGroup)); } };

	}

	public static final common.NodeFactory<PapplyTrans> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PapplyTrans> {

		@Override
		public PapplyTrans invoke(final Object[] children, final Object[] annotations) {
			return new PapplyTrans(children[0], children[1], children[2], children[3], annotations[0]);
		}
	};

}
