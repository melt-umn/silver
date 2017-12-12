
package silver.extension.convenience;

// top::Expr ::= '$' e::Int_t 
public final class PchildrenRef extends silver.definition.core.NExpr {

	public static final int i__G_1 = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = {silver.extension.convenience.TChildren_kwd.class,silver.definition.core.TInt_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_childrenRef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PchildrenRef(final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child__G_1;
	public final silver.extension.convenience.TChildren_kwd getChild__G_1() {
		return (silver.extension.convenience.TChildren_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final silver.definition.core.TInt_t getChild_e() {
		return (silver.definition.core.TInt_t) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i_e: return child_e;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)new silver.definition.core.PbaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.extension.convenience.Init.ref__ON__silver_extension_convenience_childrenRef))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:convenience:childrenRef";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "$" ++ e.lexeme
		silver.extension.convenience.PchildrenRef.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("$")), (common.StringCatter)((common.StringCatter)((silver.definition.core.TInt_t)context.childAsIs(silver.extension.convenience.PchildrenRef.i_e)).lexeme)); } };
		// ref = fromMaybe("$" ++ e.lexeme, if top.frame.signature.outputElement.elementName == "__SV_BOGUS_ELEM" then nothing() else findChild(toInt(e.lexeme), [ top.frame.signature.outputElement.elementName ] ++ top.frame.signature.inputNames))
		silver.extension.convenience.PchildrenRef.localAttributes[silver.extension.convenience.Init.ref__ON__silver_extension_convenience_childrenRef] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.PfromMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("$")), (common.StringCatter)((common.StringCatter)((silver.definition.core.TInt_t)context.childAsIs(silver.extension.convenience.PchildrenRef.i_e)).lexeme)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_signature__ON__silver_definition_core_BlockContext)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)).equals((new common.StringCatter("__SV_BOGUS_ELEM"))) ? ((core.NMaybe)new core.Pnothing()) : ((core.NMaybe)silver.extension.convenience.PfindChild.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.core.TInt_t)context.childAsIs(silver.extension.convenience.PchildrenRef.i_e)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_signature__ON__silver_definition_core_BlockContext)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NNamedSignature)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_core_signature__ON__silver_definition_core_BlockContext)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_inputNames__ON__silver_definition_env_NamedSignature)); } })); } }))); } })); } };

	}

	public static final common.NodeFactory<PchildrenRef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PchildrenRef> {

		@Override
		public PchildrenRef invoke(final Object[] children, final Object[] annotations) {
			return new PchildrenRef(children[0], children[1], annotations[0]);
		}
	};

}
