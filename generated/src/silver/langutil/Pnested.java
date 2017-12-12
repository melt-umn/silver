
package silver.langutil;

// top::Message ::= l::Location m::String others::[Message] 
public final class Pnested extends silver.langutil.NMessage {

	public static final int i_l = 0;
	public static final int i_m = 1;
	public static final int i_others = 2;


	public static final Class<?> childTypes[] = {core.NLocation.class,common.StringCatter.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_nested;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.langutil.NMessage.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.langutil.NMessage.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public Pnested(final Object c_l, final Object c_m, final Object c_others) {
		super();
		this.child_l = c_l;
		this.child_m = c_m;
		this.child_others = c_others;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_m;
	public final common.StringCatter getChild_m() {
		return (common.StringCatter) (child_m = common.Util.demand(child_m));
	}

	private Object child_others;
	public final common.ConsCell getChild_others() {
		return (common.ConsCell) (child_others = common.Util.demand(child_others));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_m: return getChild_m();
			case i_others: return getChild_others();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_m: return child_m;
			case i_others: return child_others;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:langutil:nested erroneously claimed to forward");
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
		return "silver:langutil:nested";
	}

	static void initProductionAttributeDefinitions() {
		// top.where = l
		silver.langutil.Pnested.synthesizedAttributes[silver.langutil.Init.silver_langutil_where__ON__silver_langutil_Message] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.langutil.Pnested.i_l).undecorate(); } };
		// top.message = m
		silver.langutil.Pnested.synthesizedAttributes[silver.langutil.Init.silver_langutil_message__ON__silver_langutil_Message] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.langutil.Pnested.i_m)); } };
		// header = l.unparse ++ ": " ++ m ++ "\n"
		silver.langutil.Pnested.localAttributes[silver.langutil.Init.header__ON__silver_langutil_nested] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.langutil.Pnested.i_l).synthesized(silver.langutil.Init.silver_langutil_unparse__ON__core_Location)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(": ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.langutil.Pnested.i_m)), (common.StringCatter)(new common.StringCatter("\n"))))); } };
		// top.output = header ++ implode("\n", map(nestedOutputHelper(header, _), others))
		silver.langutil.Pnested.synthesizedAttributes[silver.langutil.Init.silver_langutil_output__ON__silver_langutil_Message] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.langutil.Init.header__ON__silver_langutil_nested)), (common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.langutil.PnestedOutputHelper.factory.invokePartial(new int[]{0}, new Object[]{context.localAsIsLazy(silver.langutil.Init.header__ON__silver_langutil_nested)}); } }, context.childAsIsLazy(silver.langutil.Pnested.i_others))); } }))); } };
		// top.severity = foldr(max, 0, map((.severity), others))
		silver.langutil.Pnested.synthesizedAttributes[silver.langutil.Init.silver_langutil_severity__ON__silver_langutil_Message] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.Pfoldr.invoke(core.Pmax.factory, Integer.valueOf((int)0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.langutil.Init.silver_langutil_severity__ON__silver_langutil_Message, context), context.childAsIsLazy(silver.langutil.Pnested.i_others))); } })); } };

	}

	public static final common.NodeFactory<Pnested> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pnested> {

		@Override
		public Pnested invoke(final Object[] children, final Object[] annotations) {
			return new Pnested(children[0], children[1], children[2]);
		}
	};

}
