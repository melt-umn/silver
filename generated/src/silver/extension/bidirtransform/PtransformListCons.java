
package silver.extension.bidirtransform;

// top::TransformList ::= dcl::TransformDcl lst::TransformList 
public final class PtransformListCons extends silver.extension.bidirtransform.NTransformList {

	public static final int i_dcl = 0;
	public static final int i_lst = 1;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.NTransformDcl.class,silver.extension.bidirtransform.NTransformList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_transformListCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_dcl] = new common.Lazy[silver.extension.bidirtransform.NTransformDcl.num_inh_attrs];
	childInheritedAttributes[i_lst] = new common.Lazy[silver.extension.bidirtransform.NTransformList.num_inh_attrs];

	}

	public PtransformListCons(final Object c_dcl, final Object c_lst, final Object a_core_location) {
		super(a_core_location);
		this.child_dcl = c_dcl;
		this.child_lst = c_lst;

	}

	private Object child_dcl;
	public final silver.extension.bidirtransform.NTransformDcl getChild_dcl() {
		return (silver.extension.bidirtransform.NTransformDcl) (child_dcl = common.Util.demand(child_dcl));
	}

	private Object child_lst;
	public final silver.extension.bidirtransform.NTransformList getChild_lst() {
		return (silver.extension.bidirtransform.NTransformList) (child_lst = common.Util.demand(child_lst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dcl: return getChild_dcl();
			case i_lst: return getChild_lst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dcl: return child_dcl;
			case i_lst: return child_lst;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:transformListCons erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:transformListCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = dcl.pp ++ lst.pp
		silver.extension.bidirtransform.PtransformListCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PtransformListCons.i_dcl).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformDcl)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PtransformListCons.i_lst).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformList))); } };
		// top.errors := dcl.errors ++ lst.errors
		if(silver.extension.bidirtransform.PtransformListCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformList] == null)
			silver.extension.bidirtransform.PtransformListCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformList] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformList);
		((common.CollectionAttribute)silver.extension.bidirtransform.PtransformListCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PtransformListCons.i_dcl, silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformDcl), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PtransformListCons.i_lst, silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformList))); } });
		// lst.downSubst = top.downSubst
		silver.extension.bidirtransform.PtransformListCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformListCons.i_lst][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformList)); } };
		// top.upSubst = lst.upSubst
		silver.extension.bidirtransform.PtransformListCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PtransformListCons.i_lst).synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformList)); } };
		// lst.finalSubst = top.upSubst
		silver.extension.bidirtransform.PtransformListCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformListCons.i_lst][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformList)); } };
		// top.transformDcls = [ dcl ] ++ lst.transformDcls
		silver.extension.bidirtransform.PtransformListCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformDcls__ON__silver_extension_bidirtransform_TransformList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.bidirtransform.PtransformListCons.i_dcl), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PtransformListCons.i_lst, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformDcls__ON__silver_extension_bidirtransform_TransformList))); } };

	}

	public static final common.NodeFactory<PtransformListCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtransformListCons> {

		@Override
		public PtransformListCons invoke(final Object[] children, final Object[] annotations) {
			return new PtransformListCons(children[0], children[1], annotations[0]);
		}
	};

}
