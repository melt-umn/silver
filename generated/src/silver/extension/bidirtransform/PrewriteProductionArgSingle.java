
package silver.extension.bidirtransform;

// arg::RewriteProductionArgs ::= name::QName 
public final class PrewriteProductionArgSingle extends silver.extension.bidirtransform.NRewriteProductionArgs {

	public static final int i_name = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_rewriteProductionArgSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteProductionArgs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteProductionArgs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_name] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PrewriteProductionArgSingle(final Object c_name) {
		super();
		this.child_name = c_name;

	}

	private Object child_name;
	public final silver.definition.core.NQName getChild_name() {
		return (silver.definition.core.NQName) (child_name = common.Util.demand(child_name));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:rewriteProductionArgSingle erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:rewriteProductionArgSingle";
	}

	static void initProductionAttributeDefinitions() {
		// arg.pp = name.pp
		silver.extension.bidirtransform.PrewriteProductionArgSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteProductionArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteProductionArgSingle.i_name).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// arg.inputNames = [ name.name ]
		silver.extension.bidirtransform.PrewriteProductionArgSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_inputNames__ON__silver_extension_bidirtransform_RewriteProductionArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteProductionArgSingle.i_name, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// arg.errors := []
		if(silver.extension.bidirtransform.PrewriteProductionArgSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProductionArgs] == null)
			silver.extension.bidirtransform.PrewriteProductionArgSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProductionArgs] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProductionArgs);
		((common.CollectionAttribute)silver.extension.bidirtransform.PrewriteProductionArgSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProductionArgs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<PrewriteProductionArgSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrewriteProductionArgSingle> {

		@Override
		public PrewriteProductionArgSingle invoke(final Object[] children, final Object[] annotations) {
			return new PrewriteProductionArgSingle(children[0]);
		}
	};

}
