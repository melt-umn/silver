
package silver.extension.bidirtransform;

// top::BracketedOptTypeExprs ::= s::String 
public final class PbotlOneString extends silver.definition.type.syntax.NBracketedOptTypeExprs {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_botlOneString;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PbotlOneString(final Object c_s, final Object a_core_location) {
		super(a_core_location);
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		return ((silver.definition.type.syntax.NBracketedOptTypeExprs)new silver.extension.bidirtransform.PbotlOneTyExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.extension.bidirtransform.PsTyExpr(context.childAsIsLazy(silver.extension.bidirtransform.PbotlOneString.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NBracketedOptTypeExprs)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NBracketedOptTypeExprs)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:botlOneString";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PbotlOneString> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbotlOneString> {

		@Override
		public PbotlOneString invoke(final Object[] children, final Object[] annotations) {
			return new PbotlOneString(children[0], annotations[0]);
		}
	};

}
