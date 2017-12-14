
package silver.extension.bidirtransform;

// top::TransformList ::= dcl::TransformDcl 
public final class PtransformListSingle extends silver.extension.bidirtransform.NTransformList {

	public static final int i_dcl = 0;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.NTransformDcl.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_transformListSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_dcl] = new common.Lazy[silver.extension.bidirtransform.NTransformDcl.num_inh_attrs];

	}

	public PtransformListSingle(final Object c_dcl, final Object a_core_location) {
		super(a_core_location);
		this.child_dcl = c_dcl;

	}

	private Object child_dcl;
	public final silver.extension.bidirtransform.NTransformDcl getChild_dcl() {
		return (silver.extension.bidirtransform.NTransformDcl) (child_dcl = common.Util.demand(child_dcl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dcl: return getChild_dcl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dcl: return child_dcl;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:transformListSingle erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:transformListSingle";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = dcl.pp
		silver.extension.bidirtransform.PtransformListSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PtransformListSingle.i_dcl).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformDcl)); } };
		// top.errors := dcl.errors
		if(silver.extension.bidirtransform.PtransformListSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformList] == null)
			silver.extension.bidirtransform.PtransformListSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformList] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformList);
		((common.CollectionAttribute)silver.extension.bidirtransform.PtransformListSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PtransformListSingle.i_dcl).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformDcl)); } });
		// dcl.downSubst = top.downSubst
		silver.extension.bidirtransform.PtransformListSingle.childInheritedAttributes[silver.extension.bidirtransform.PtransformListSingle.i_dcl][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformList)); } };
		// top.upSubst = dcl.upSubst
		silver.extension.bidirtransform.PtransformListSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PtransformListSingle.i_dcl).synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformDcl)); } };
		// dcl.finalSubst = top.upSubst
		silver.extension.bidirtransform.PtransformListSingle.childInheritedAttributes[silver.extension.bidirtransform.PtransformListSingle.i_dcl][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformList)); } };
		// top.transformDcls = [ dcl ]
		silver.extension.bidirtransform.PtransformListSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformDcls__ON__silver_extension_bidirtransform_TransformList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.bidirtransform.PtransformListSingle.i_dcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PtransformListSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtransformListSingle> {

		@Override
		public PtransformListSingle invoke(final Object[] children, final Object[] annotations) {
			return new PtransformListSingle(children[0], annotations[0]);
		}
	};

}
