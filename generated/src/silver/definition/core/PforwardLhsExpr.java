
package silver.definition.core;

// top::ForwardLHSExpr ::= q::QNameAttrOccur 
public final class PforwardLhsExpr extends silver.definition.core.NForwardLHSExpr {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQNameAttrOccur.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_forwardLhsExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NForwardLHSExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NForwardLHSExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_q] = new common.Lazy[silver.definition.core.NQNameAttrOccur.num_inh_attrs];

	}

	public PforwardLhsExpr(final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;

	}

	private Object child_q;
	public final silver.definition.core.NQNameAttrOccur getChild_q() {
		return (silver.definition.core.NQNameAttrOccur) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:forwardLhsExpr erroneously claimed to forward");
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
		return "silver:definition:core:forwardLhsExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = q.pp
		silver.definition.core.PforwardLhsExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ForwardLHSExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PforwardLhsExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)); } };
		// top.errors := q.errors
		if(silver.definition.core.PforwardLhsExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardLHSExpr] == null)
			silver.definition.core.PforwardLhsExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardLHSExpr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardLHSExpr);
		((common.CollectionAttribute)silver.definition.core.PforwardLhsExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardLHSExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PforwardLhsExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur)); } });
		// top.typerep = q.typerep
		silver.definition.core.PforwardLhsExpr.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_ForwardLHSExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.childDecorated(silver.definition.core.PforwardLhsExpr.i_q).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameAttrOccur)); } };
		// q.attrFor = top.frame.signature.outputElement.typerep
		silver.definition.core.PforwardLhsExpr.childInheritedAttributes[silver.definition.core.PforwardLhsExpr.i_q][silver.definition.core.Init.silver_definition_core_attrFor__ON__silver_definition_core_QNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_ForwardLHSExpr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_signature__ON__silver_definition_core_BlockContext)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)); } };

	}

	public static final common.NodeFactory<PforwardLhsExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PforwardLhsExpr> {

		@Override
		public PforwardLhsExpr invoke(final Object[] children, final Object[] annotations) {
			return new PforwardLhsExpr(children[0], annotations[0]);
		}
	};

}
