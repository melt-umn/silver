
package silver.extension.bidirtransform;

// top::AppExpr ::= nsElem::NamedSignatureElement 
public final class PnsElemToAppExpr extends silver.definition.core.NAppExpr {

	public static final int i_nsElem = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.NNamedSignatureElement.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_nsElemToAppExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAppExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_nsElem] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];

	}

	public PnsElemToAppExpr(final Object c_nsElem, final Object a_core_location) {
		super(a_core_location);
		this.child_nsElem = c_nsElem;

	}

	private Object child_nsElem;
	public final silver.definition.env.NNamedSignatureElement getChild_nsElem() {
		return (silver.definition.env.NNamedSignatureElement) (child_nsElem = common.Util.demand(child_nsElem));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nsElem: return getChild_nsElem();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nsElem: return child_nsElem;

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
		return ((silver.definition.core.NAppExpr)new silver.extension.bidirtransform.PpresentName(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PnsElemToAppExpr.i_nsElem, silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAppExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:nsElemToAppExpr";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PnsElemToAppExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnsElemToAppExpr> {

		@Override
		public PnsElemToAppExpr invoke(final Object[] children, final Object[] annotations) {
			return new PnsElemToAppExpr(children[0], annotations[0]);
		}
	};

}
