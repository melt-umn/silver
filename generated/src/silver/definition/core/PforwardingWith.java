
package silver.definition.core;

// top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';' 
public final class PforwardingWith extends silver.definition.core.NProductionStmt {

	public static final int i__G_5 = 0;
	public static final int i__G_4 = 1;
	public static final int i__G_3 = 2;
	public static final int i_inh = 3;
	public static final int i__G_1 = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.definition.core.TForwarding_kwd.class,silver.definition.core.TWith_kwd.class,silver.definition.core.TLCurly_t.class,silver.definition.core.NForwardInhs.class,silver.definition.core.TRCurly_t.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_forwardingWith;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_inh] = new common.Lazy[silver.definition.core.NForwardInhs.num_inh_attrs];

	}

	public PforwardingWith(final Object c__G_5, final Object c__G_4, final Object c__G_3, final Object c_inh, final Object c__G_1, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child__G_3 = c__G_3;
		this.child_inh = c_inh;
		this.child__G_1 = c__G_1;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.definition.core.TForwarding_kwd getChild__G_5() {
		return (silver.definition.core.TForwarding_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final silver.definition.core.TWith_kwd getChild__G_4() {
		return (silver.definition.core.TWith_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child__G_3;
	public final silver.definition.core.TLCurly_t getChild__G_3() {
		return (silver.definition.core.TLCurly_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_inh;
	public final silver.definition.core.NForwardInhs getChild_inh() {
		return (silver.definition.core.NForwardInhs) (child_inh = common.Util.demand(child_inh));
	}

	private Object child__G_1;
	public final silver.definition.core.TRCurly_t getChild__G_1() {
		return (silver.definition.core.TRCurly_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i__G_3: return getChild__G_3();
			case i_inh: return getChild_inh();
			case i__G_1: return getChild__G_1();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i__G_3: return child__G_3;
			case i_inh: return child_inh;
			case i__G_1: return child__G_1;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:forwardingWith erroneously claimed to forward");
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
		return "silver:definition:core:forwardingWith";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "\tforwarding with {" ++ inh.pp ++ "};"
		silver.definition.core.PforwardingWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\tforwarding with {")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PforwardingWith.i_inh).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ForwardInhs)), (common.StringCatter)(new common.StringCatter("};")))); } };
		// fwdDcls = getValueDcl("forward", top.env)
		silver.definition.core.PforwardingWith.localAttributes[silver.definition.core.Init.fwdDcls__ON__silver_definition_core_forwardingWith] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDcl.invoke((new common.StringCatter("forward")), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionStmt))); } };
		// top.errors := inh.errors
		if(silver.definition.core.PforwardingWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.definition.core.PforwardingWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.definition.core.PforwardingWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PforwardingWith.i_inh).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ForwardInhs)); } });
		// top.errors <- if null(fwdDcls) then [ err(top.location, "'forwarding with' clause for a production that does not forward!") ] else []
		if(silver.definition.core.PforwardingWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] == null)
			silver.definition.core.PforwardingWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt);
		((common.CollectionAttribute)silver.definition.core.PforwardingWith.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionStmt]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.core.Init.fwdDcls__ON__silver_definition_core_forwardingWith))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("'forwarding with' clause for a production that does not forward!")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });

	}

	public static final common.NodeFactory<PforwardingWith> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PforwardingWith> {

		@Override
		public PforwardingWith invoke(final Object[] children, final Object[] annotations) {
			return new PforwardingWith(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
