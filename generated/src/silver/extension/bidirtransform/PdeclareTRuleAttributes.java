
package silver.extension.bidirtransform;

// top::AGDcl ::= tr::Decorated TransformRule nm::String ty::TypeExpr absNames::[String] cncNames::[String] 
public final class PdeclareTRuleAttributes extends silver.definition.core.NAGDcl {

	public static final int i_tr = 0;
	public static final int i_nm = 1;
	public static final int i_ty = 2;
	public static final int i_absNames = 3;
	public static final int i_cncNames = 4;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.StringCatter.class,silver.definition.type.syntax.NTypeExpr.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_declareTRuleAttributes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];

	}

	public PdeclareTRuleAttributes(final Object c_tr, final Object c_nm, final Object c_ty, final Object c_absNames, final Object c_cncNames, final Object a_core_location) {
		super(a_core_location);
		this.child_tr = c_tr;
		this.child_nm = c_nm;
		this.child_ty = c_ty;
		this.child_absNames = c_absNames;
		this.child_cncNames = c_cncNames;

	}

	private Object child_tr;
	public final common.DecoratedNode getChild_tr() {
		return (common.DecoratedNode) (child_tr = common.Util.demand(child_tr));
	}

	private Object child_nm;
	public final common.StringCatter getChild_nm() {
		return (common.StringCatter) (child_nm = common.Util.demand(child_nm));
	}

	private Object child_ty;
	public final silver.definition.type.syntax.NTypeExpr getChild_ty() {
		return (silver.definition.type.syntax.NTypeExpr) (child_ty = common.Util.demand(child_ty));
	}

	private Object child_absNames;
	public final common.ConsCell getChild_absNames() {
		return (common.ConsCell) (child_absNames = common.Util.demand(child_absNames));
	}

	private Object child_cncNames;
	public final common.ConsCell getChild_cncNames() {
		return (common.ConsCell) (child_cncNames = common.Util.demand(child_cncNames));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tr: return getChild_tr();
			case i_nm: return getChild_nm();
			case i_ty: return getChild_ty();
			case i_absNames: return getChild_absNames();
			case i_cncNames: return getChild_cncNames();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tr: return child_tr;
			case i_nm: return child_nm;
			case i_ty: return child_ty;
			case i_absNames: return child_absNames;
			case i_cncNames: return child_cncNames;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return context.localDecorated(silver.extension.bidirtransform.Init.agDcls6__ON__silver_extension_bidirtransform_declareTRuleAttributes).undecorate();
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
		return "silver:extension:bidirtransform:declareTRuleAttributes";
	}

	static void initProductionAttributeDefinitions() {
		// tName = nm ++ toString(tr.trIndex)
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_declareTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PdeclareTRuleAttributes.i_nm)), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PdeclareTRuleAttributes.i_tr)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_trIndex__ON__silver_extension_bidirtransform_TransformRule))))); } };
		// inhRedexName = inhRedexNm(tName)
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.inhRedexName__ON__silver_extension_bidirtransform_declareTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PinhRedexNm.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_declareTRuleAttributes))); } };
		// agDcls1 = autocAttr(inhRedexName, mkMaybeString("Origin",location=top.location),location=top.location)
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_declareTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PautocAttr(context.localAsIsLazy(silver.extension.bidirtransform.Init.inhRedexName__ON__silver_extension_bidirtransform_declareTRuleAttributes), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.extension.bidirtransform.PmkMaybeString((new common.StringCatter("Origin")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } };
		// agDcls2 = appendAGDcl(synAttr(tName, ty,location=top.location), agDcls1,location=top.location)
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_declareTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PsynAttr(context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_declareTRuleAttributes), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PdeclareTRuleAttributes.i_ty)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_declareTRuleAttributes)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } };
		// agDcls3 = appendAGDcl(synAttr(transformNm(tName), mkBoolTypeExpr(location=top.location),location=top.location), agDcls2,location=top.location)
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_declareTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PsynAttr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PtransformNm.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_declareTRuleAttributes))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NTypeExpr)new silver.extension.bidirtransform.PmkBoolTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_declareTRuleAttributes)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } };
		// agDcls4 = appendAGDcl(attrOn(inhRedexName, absNames ++ cncNames,location=top.location), agDcls3,location=top.location)
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls4__ON__silver_extension_bidirtransform_declareTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PattrOn(context.localAsIsLazy(silver.extension.bidirtransform.Init.inhRedexName__ON__silver_extension_bidirtransform_declareTRuleAttributes), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PdeclareTRuleAttributes.i_absNames), context.childAsIsLazy(silver.extension.bidirtransform.PdeclareTRuleAttributes.i_cncNames))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_declareTRuleAttributes)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } };
		// agDcls5 = appendAGDcl(attrOn(transformNm(tName), absNames,location=top.location), agDcls4,location=top.location)
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls5__ON__silver_extension_bidirtransform_declareTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PattrOn(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.extension.bidirtransform.PtransformNm.invoke(context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_declareTRuleAttributes))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PdeclareTRuleAttributes.i_absNames), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls4__ON__silver_extension_bidirtransform_declareTRuleAttributes)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } };
		// agDcls6 = appendAGDcl(attrOn(tName, absNames,location=top.location), agDcls5,location=top.location)
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localAttributes[silver.extension.bidirtransform.Init.agDcls6__ON__silver_extension_bidirtransform_declareTRuleAttributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PattrOn(context.localAsIsLazy(silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_declareTRuleAttributes), context.childAsIsLazy(silver.extension.bidirtransform.PdeclareTRuleAttributes.i_absNames), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.agDcls5__ON__silver_extension_bidirtransform_declareTRuleAttributes)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } };
		// agDcls6.compiledGrammars = top.compiledGrammars
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls6__ON__silver_extension_bidirtransform_declareTRuleAttributes][silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.definition.core.Init.silver_definition_env_compiledGrammars__ON__silver_definition_core_AGDcl)); } };
		// agDcls6.grammarName = top.grammarName
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls6__ON__silver_extension_bidirtransform_declareTRuleAttributes][silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl)); } };
		// agDcls6.flowEnv = top.flowEnv
		silver.extension.bidirtransform.PdeclareTRuleAttributes.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls6__ON__silver_extension_bidirtransform_declareTRuleAttributes][silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.flow.env.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_core_AGDcl)); } };

	}

	public static final common.NodeFactory<PdeclareTRuleAttributes> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdeclareTRuleAttributes> {

		@Override
		public PdeclareTRuleAttributes invoke(final Object[] children, final Object[] annotations) {
			return new PdeclareTRuleAttributes(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
