
package silver.modification.ffi;

// top::FFIDefs ::= one::FFIDef more::FFIDefs 
public final class PffidefsMany extends silver.modification.ffi.NFFIDefs {

	public static final int i_one = 0;
	public static final int i_more = 1;


	public static final Class<?> childTypes[] = {silver.modification.ffi.NFFIDef.class,silver.modification.ffi.NFFIDefs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_ffi_ffidefsMany;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.ffi.NFFIDefs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.ffi.NFFIDefs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_one] = new common.Lazy[silver.modification.ffi.NFFIDef.num_inh_attrs];
	childInheritedAttributes[i_more] = new common.Lazy[silver.modification.ffi.NFFIDefs.num_inh_attrs];

	}

	public PffidefsMany(final Object c_one, final Object c_more, final Object a_core_location) {
		super(a_core_location);
		this.child_one = c_one;
		this.child_more = c_more;

	}

	private Object child_one;
	public final silver.modification.ffi.NFFIDef getChild_one() {
		return (silver.modification.ffi.NFFIDef) (child_one = common.Util.demand(child_one));
	}

	private Object child_more;
	public final silver.modification.ffi.NFFIDefs getChild_more() {
		return (silver.modification.ffi.NFFIDefs) (child_more = common.Util.demand(child_more));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_one: return getChild_one();
			case i_more: return getChild_more();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_one: return child_one;
			case i_more: return child_more;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:ffi:ffidefsMany erroneously claimed to forward");
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
		return "silver:modification:ffi:ffidefsMany";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = one.pp ++ more.pp
		silver.modification.ffi.PffidefsMany.synthesizedAttributes[silver.modification.ffi.Init.silver_definition_env_pp__ON__silver_modification_ffi_FFIDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.ffi.PffidefsMany.i_one).synthesized(silver.modification.ffi.Init.silver_definition_env_pp__ON__silver_modification_ffi_FFIDef)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.ffi.PffidefsMany.i_more).synthesized(silver.modification.ffi.Init.silver_definition_env_pp__ON__silver_modification_ffi_FFIDefs))); } };
		// top.errors := one.errors ++ more.errors
		if(silver.modification.ffi.PffidefsMany.synthesizedAttributes[silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDefs] == null)
			silver.modification.ffi.PffidefsMany.synthesizedAttributes[silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDefs] = new silver.definition.core.CAerrors(silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDefs);
		((common.CollectionAttribute)silver.modification.ffi.PffidefsMany.synthesizedAttributes[silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDefs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.ffi.PffidefsMany.i_one, silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDef), context.childDecoratedSynthesizedLazy(silver.modification.ffi.PffidefsMany.i_more, silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDefs))); } });

	}

	public static final common.NodeFactory<PffidefsMany> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PffidefsMany> {

		@Override
		public PffidefsMany invoke(final Object[] children, final Object[] annotations) {
			return new PffidefsMany(children[0], children[1], annotations[0]);
		}
	};

}
