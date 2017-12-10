
package silver.extension.bidirtransform;

// top::TransformRule ::= rules::[TransformRule] dcl::Decorated NamedSignature 
public final class PgetTrans extends silver.extension.bidirtransform.NTransformRule {

	public static final int i_rules = 0;
	public static final int i_dcl = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_getTrans;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformRule.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformRule.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgetTrans(final Object c_rules, final Object c_dcl, final Object a_core_location) {
		super(a_core_location);
		this.child_rules = c_rules;
		this.child_dcl = c_dcl;

	}

	private Object child_rules;
	public final common.ConsCell getChild_rules() {
		return (common.ConsCell) (child_rules = common.Util.demand(child_rules));
	}

	private Object child_dcl;
	public final common.DecoratedNode getChild_dcl() {
		return (common.DecoratedNode) (child_dcl = common.Util.demand(child_dcl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rules: return getChild_rules();
			case i_dcl: return getChild_dcl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rules: return child_rules;
			case i_dcl: return child_dcl;

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
		return (((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PgetTrans.i_dcl)).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature)).equals(((common.StringCatter)((common.DecoratedNode)((silver.extension.bidirtransform.NTransformRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetTrans.i_rules))).decorate(context, (common.Lazy[])null).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_TransformRule)).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_NamedSignature))) ? ((silver.extension.bidirtransform.NTransformRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetTrans.i_rules))) : ((silver.extension.bidirtransform.NTransformRule)new silver.extension.bidirtransform.PgetTrans(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetTrans.i_rules))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PgetTrans.i_dcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NTransformRule)context.undecorate()).getAnno_core_location()); } })));
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
		return "silver:extension:bidirtransform:getTrans";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PgetTrans> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgetTrans> {

		@Override
		public PgetTrans invoke(final Object[] children, final Object[] annotations) {
			return new PgetTrans(children[0], children[1], annotations[0]);
		}
	};

}
