
package silver.extension.bidirtransform;

// top::AGDcl ::= name::String onNames::[String] 
public final class PannoOn extends silver.definition.core.NAGDcl {

	public static final int i_name = 0;
	public static final int i_onNames = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_annoOn;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PannoOn(final Object c_name, final Object c_onNames, final Object a_core_location) {
		super(a_core_location);
		this.child_name = c_name;
		this.child_onNames = c_onNames;

	}

	private Object child_name;
	public final common.StringCatter getChild_name() {
		return (common.StringCatter) (child_name = common.Util.demand(child_name));
	}

	private Object child_onNames;
	public final common.ConsCell getChild_onNames() {
		return (common.ConsCell) (child_onNames = common.Util.demand(child_onNames));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i_onNames: return getChild_onNames();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i_onNames: return child_onNames;

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
		return ((silver.definition.core.NAGDcl)silver.extension.convenience.PmakeOccursDclsHelp.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.bidirtransform.Init.qntlName__ON__silver_extension_bidirtransform_annoOn)), context.localAsIsLazy(silver.extension.bidirtransform.Init.qntOnNames__ON__silver_extension_bidirtransform_annoOn)));
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
		return "silver:extension:bidirtransform:annoOn";
	}

	static void initProductionAttributeDefinitions() {
		// qntlName = qNameWithTL(qName(top.location, name), botlNone(location=top.location))
		silver.extension.bidirtransform.PannoOn.localAttributes[silver.extension.bidirtransform.Init.qntlName__ON__silver_extension_bidirtransform_annoOn] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.convenience.NQNameWithTL)new silver.extension.convenience.PqNameWithTL(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, context.childAsIsLazy(silver.extension.bidirtransform.PannoOn.i_name))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NBracketedOptTypeExprs)new silver.definition.type.syntax.PbotlNone(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } })); } };
		// qntOnNames = map(\ s::String  -> qNameWithTL(qName(top.location, s), botlNone(location=top.location)), onNames)
		silver.extension.bidirtransform.PannoOn.localAttributes[silver.extension.bidirtransform.Init.qntOnNames__ON__silver_extension_bidirtransform_annoOn] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke((new common.NodeFactory<silver.extension.convenience.NQNameWithTL>() {
  public final silver.extension.convenience.NQNameWithTL invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_10482_s = args[0];

    return ((silver.extension.convenience.NQNameWithTL)new silver.extension.convenience.PqNameWithTL(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, ((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_10482_s)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NBracketedOptTypeExprs)new silver.definition.type.syntax.PbotlNone(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } })); } }));
  }
}), context.childAsIsLazy(silver.extension.bidirtransform.PannoOn.i_onNames))); } };

	}

	public static final common.NodeFactory<PannoOn> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PannoOn> {

		@Override
		public PannoOn invoke(final Object[] children, final Object[] annotations) {
			return new PannoOn(children[0], children[1], annotations[0]);
		}
	};

}
