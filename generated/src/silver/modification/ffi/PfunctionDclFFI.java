
package silver.modification.ffi;

// top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}' 
public final class PfunctionDclFFI extends silver.definition.core.NAGDcl {

	public static final int i__G_7 = 0;
	public static final int i_id = 1;
	public static final int i_ns = 2;
	public static final int i_body = 3;
	public static final int i__G_3 = 4;
	public static final int i__G_2 = 5;
	public static final int i_ffidefs = 6;
	public static final int i__G_0 = 7;


	public static final Class<?> childTypes[] = {silver.definition.core.TFunction_kwd.class,silver.definition.core.NName.class,silver.definition.core.NFunctionSignature.class,silver.definition.core.NProductionBody.class,silver.modification.ffi.TFFI_kwd.class,silver.definition.core.TLCurly_t.class,silver.modification.ffi.NFFIDefs.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_ffi_functionDclFFI;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.core.NFunctionSignature.num_inh_attrs];
	childInheritedAttributes[i_body] = new common.Lazy[silver.definition.core.NProductionBody.num_inh_attrs];
	childInheritedAttributes[i_ffidefs] = new common.Lazy[silver.modification.ffi.NFFIDefs.num_inh_attrs];

	}

	public PfunctionDclFFI(final Object c__G_7, final Object c_id, final Object c_ns, final Object c_body, final Object c__G_3, final Object c__G_2, final Object c_ffidefs, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_7 = c__G_7;
		this.child_id = c_id;
		this.child_ns = c_ns;
		this.child_body = c_body;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_ffidefs = c_ffidefs;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_7;
	public final silver.definition.core.TFunction_kwd getChild__G_7() {
		return (silver.definition.core.TFunction_kwd) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child_ns;
	public final silver.definition.core.NFunctionSignature getChild_ns() {
		return (silver.definition.core.NFunctionSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_body;
	public final silver.definition.core.NProductionBody getChild_body() {
		return (silver.definition.core.NProductionBody) (child_body = common.Util.demand(child_body));
	}

	private Object child__G_3;
	public final silver.modification.ffi.TFFI_kwd getChild__G_3() {
		return (silver.modification.ffi.TFFI_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ffidefs;
	public final silver.modification.ffi.NFFIDefs getChild_ffidefs() {
		return (silver.modification.ffi.NFFIDefs) (child_ffidefs = common.Util.demand(child_ffidefs));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_7: return getChild__G_7();
			case i_id: return getChild_id();
			case i_ns: return getChild_ns();
			case i_body: return getChild_body();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_ffidefs: return getChild_ffidefs();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i_id: return child_id;
			case i_ns: return child_ns;
			case i_body: return child_body;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_ffidefs: return child_ffidefs;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 8;
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
		return ((silver.definition.core.NAGDcl)new silver.definition.core.PfunctionDcl(context.childAsIsLazy(silver.modification.ffi.PfunctionDclFFI.i__G_7), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.ffi.PfunctionDclFFI.i_id)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.ffi.PfunctionDclFFI.i_ns)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.ffi.PfunctionDclFFI.i_body)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:ffi:functionDclFFI";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp ++ " foreign {\n" ++ ffidefs.pp ++ "}"
		silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("function ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_FunctionSignature)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_body).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionBody)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" foreign {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_ffidefs).synthesized(silver.modification.ffi.Init.silver_definition_env_pp__ON__silver_modification_ffi_FFIDefs)), (common.StringCatter)(new common.StringCatter("}")))))))))); } };
		// fName = top.grammarName ++ ":" ++ id.name
		silver.modification.ffi.PfunctionDclFFI.localAttributes[silver.modification.ffi.Init.fName__ON__silver_modification_ffi_functionDclFFI] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// namedSig = ns.namedSignature
		silver.modification.ffi.PfunctionDclFFI.localAttributes[silver.modification.ffi.Init.namedSig__ON__silver_modification_ffi_functionDclFFI] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_FunctionSignature)); } };
		// ns.signatureName = fName
		silver.modification.ffi.PfunctionDclFFI.childInheritedAttributes[silver.modification.ffi.PfunctionDclFFI.i_ns][silver.definition.core.Init.silver_definition_core_signatureName__ON__silver_definition_core_FunctionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.localAsIs(silver.modification.ffi.Init.fName__ON__silver_modification_ffi_functionDclFFI)); } };
		// top.errors <- ffidefs.errors
		if(silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.ffi.PfunctionDclFFI.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_ffidefs).synthesized(silver.modification.ffi.Init.silver_definition_core_errors__ON__silver_modification_ffi_FFIDefs)); } });

	}

	public static final common.NodeFactory<PfunctionDclFFI> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfunctionDclFFI> {

		@Override
		public PfunctionDclFFI invoke(final Object[] children, final Object[] annotations) {
			return new PfunctionDclFFI(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], annotations[0]);
		}
	};

}
