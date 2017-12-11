
package silver.extension.bidirtransform;

// top::Expr ::= toFill::Expr appExps::AppExprs annoExps::AnnoAppExprs toInject::AnnoAppExprs needAnnos::[String] 
public final class PinjectApplication extends silver.definition.core.NExpr {

	public static final int i_toFill = 0;
	public static final int i_appExps = 1;
	public static final int i_annoExps = 2;
	public static final int i_toInject = 3;
	public static final int i_needAnnos = 4;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.core.NAppExprs.class,silver.definition.core.NAnnoAppExprs.class,silver.definition.core.NAnnoAppExprs.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_injectApplication;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_appExps] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];
	childInheritedAttributes[i_annoExps] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];
	childInheritedAttributes[i_toInject] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];

	}

	public PinjectApplication(final Object c_toFill, final Object c_appExps, final Object c_annoExps, final Object c_toInject, final Object c_needAnnos, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_appExps = c_appExps;
		this.child_annoExps = c_annoExps;
		this.child_toInject = c_toInject;
		this.child_needAnnos = c_needAnnos;

	}

	private Object child_toFill;
	public final silver.definition.core.NExpr getChild_toFill() {
		return (silver.definition.core.NExpr) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_appExps;
	public final silver.definition.core.NAppExprs getChild_appExps() {
		return (silver.definition.core.NAppExprs) (child_appExps = common.Util.demand(child_appExps));
	}

	private Object child_annoExps;
	public final silver.definition.core.NAnnoAppExprs getChild_annoExps() {
		return (silver.definition.core.NAnnoAppExprs) (child_annoExps = common.Util.demand(child_annoExps));
	}

	private Object child_toInject;
	public final silver.definition.core.NAnnoAppExprs getChild_toInject() {
		return (silver.definition.core.NAnnoAppExprs) (child_toInject = common.Util.demand(child_toInject));
	}

	private Object child_needAnnos;
	public final common.ConsCell getChild_needAnnos() {
		return (common.ConsCell) (child_needAnnos = common.Util.demand(child_needAnnos));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_appExps: return getChild_appExps();
			case i_annoExps: return getChild_annoExps();
			case i_toInject: return getChild_toInject();
			case i_needAnnos: return getChild_needAnnos();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_appExps: return child_appExps;
			case i_annoExps: return child_annoExps;
			case i_toInject: return child_toInject;
			case i_needAnnos: return child_needAnnos;

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
		return ((silver.definition.core.NExpr)new silver.definition.core.Papplication(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectApplication.i_toFill)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TLParen_t((new common.StringCatter("(")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.baseAppExprs__ON__silver_extension_bidirtransform_injectApplication)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)silver.util.Pcontains.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PinjectApplication.i_toFill, silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr), context.childAsIsLazy(silver.extension.bidirtransform.PinjectApplication.i_needAnnos))) ? ((silver.definition.core.NAnnoAppExprs)new silver.extension.bidirtransform.PconsAnnoAppExprs(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.baseAnnoExprs__ON__silver_extension_bidirtransform_injectApplication)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectApplication.i_toInject)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectApplication.i_toFill).undecorate()).getAnno_core_location()); } })) : context.localDecorated(silver.extension.bidirtransform.Init.baseAnnoExprs__ON__silver_extension_bidirtransform_injectApplication).undecorate()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TRParen_t((new common.StringCatter(")")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectApplication.i_toFill).undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:injectApplication";
	}

	static void initProductionAttributeDefinitions() {
		// baseAppExprs = injectAppExprs(appExps, toInject, needAnnos,location=toFill.location)
		silver.extension.bidirtransform.PinjectApplication.localAttributes[silver.extension.bidirtransform.Init.baseAppExprs__ON__silver_extension_bidirtransform_injectApplication] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAppExprs)new silver.extension.bidirtransform.PinjectAppExprs(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectApplication.i_appExps)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectApplication.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectApplication.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectApplication.i_toFill).undecorate()).getAnno_core_location()); } })); } };
		// baseAnnoExprs = injectAnnoExprs(annoExps, toInject, needAnnos,location=toFill.location)
		silver.extension.bidirtransform.PinjectApplication.localAttributes[silver.extension.bidirtransform.Init.baseAnnoExprs__ON__silver_extension_bidirtransform_injectApplication] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NAnnoAppExprs)new silver.extension.bidirtransform.PinjectAnnoExprs(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectApplication.i_annoExps)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PinjectApplication.i_toInject)), context.childAsIsLazy(silver.extension.bidirtransform.PinjectApplication.i_needAnnos), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PinjectApplication.i_toFill).undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PinjectApplication> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PinjectApplication> {

		@Override
		public PinjectApplication invoke(final Object[] children, final Object[] annotations) {
			return new PinjectApplication(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
