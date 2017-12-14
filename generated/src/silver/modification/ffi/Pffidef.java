
package silver.modification.ffi;

// top::FFIDef ::= name::String_t ':' 'return' code::String_t ';' 
public final class Pffidef extends silver.modification.ffi.NFFIDef {

	public static final int i_name = 0;
	public static final int i__G_3 = 1;
	public static final int i__G_2 = 2;
	public static final int i_code = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.definition.core.TString_t.class,silver.definition.core.TColon_t.class,silver.definition.core.TReturn_kwd.class,silver.definition.core.TString_t.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_ffi_ffidef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.ffi.NFFIDef.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.ffi.NFFIDef.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pffidef(final Object c_name, final Object c__G_3, final Object c__G_2, final Object c_code, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_name = c_name;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_code = c_code;
		this.child__G_0 = c__G_0;

	}

	private Object child_name;
	public final silver.definition.core.TString_t getChild_name() {
		return (silver.definition.core.TString_t) (child_name = common.Util.demand(child_name));
	}

	private Object child__G_3;
	public final silver.definition.core.TColon_t getChild__G_3() {
		return (silver.definition.core.TColon_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TReturn_kwd getChild__G_2() {
		return (silver.definition.core.TReturn_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_code;
	public final silver.definition.core.TString_t getChild_code() {
		return (silver.definition.core.TString_t) (child_code = common.Util.demand(child_code));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_code: return getChild_code();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_code: return child_code;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:ffi:ffidef erroneously claimed to forward");
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
		return "silver:modification:ffi:ffidef";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = name.lexeme ++ ": return " ++ code.lexeme ++ ";\n"
		silver.modification.ffi.Pffidef.synthesizedAttributes[silver.modification.ffi.Init.silver_definition_env_pp__ON__silver_modification_ffi_FFIDef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.ffi.Pffidef.i_name)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(": return ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.TString_t)context.childAsIs(silver.modification.ffi.Pffidef.i_code)).lexeme), (common.StringCatter)(new common.StringCatter(";\n"))))); } };
		// top.errors := []
		if(silver.modification.ffi.Pffidef.synthesizedAttributes[silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDef] == null)
			silver.modification.ffi.Pffidef.synthesizedAttributes[silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDef] = new silver.definition.core.CAerrors(silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDef);
		((common.CollectionAttribute)silver.modification.ffi.Pffidef.synthesizedAttributes[silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDef]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<Pffidef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pffidef> {

		@Override
		public Pffidef invoke(final Object[] children, final Object[] annotations) {
			return new Pffidef(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
