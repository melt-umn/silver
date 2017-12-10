
package copper_features;

// top::AOrB ::= 'a' 
public final class Paorb_a extends copper_features.NAOrB {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {copper_features.TA.class};

	public static final int num_local_attrs = Init.count_local__ON__copper_features_aorb_a;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[copper_features.NAOrB.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[copper_features.NAOrB.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Paorb_a(final Object c__G_0) {
		super();
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final copper_features.TA getChild__G_0() {
		return (copper_features.TA) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production copper_features:aorb_a erroneously claimed to forward");
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
		return "copper_features:aorb_a";
	}

	static void initProductionAttributeDefinitions() {
		// top.semResult = "(" ++ implode(",", [ $1.lexeme, $1.filename, toString($1.line), toString($1.column) ],) ++ " [" ++ toString($1.location.index) ++ "])"
		copper_features.Paorb_a.synthesizedAttributes[copper_features.Init.copper_features_semResult__ON__copper_features_AOrB] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(",")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(((common.StringCatter)((copper_features.TA)context.childAsIs(copper_features.Paorb_a.i__G_0)).lexeme), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(((common.StringCatter)((copper_features.TA)context.childAsIs(copper_features.Paorb_a.i__G_0)).getFilename()), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)((copper_features.TA)context.childAsIs(copper_features.Paorb_a.i__G_0)).getLine()))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)((copper_features.TA)context.childAsIs(copper_features.Paorb_a.i__G_0)).getColumn()))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" [")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((core.NLocation)((copper_features.TA)context.childAsIs(copper_features.Paorb_a.i__G_0)).location).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_index__ON__core_Location)))), (common.StringCatter)(new common.StringCatter("])")))))); } };

	}

	public static final common.NodeFactory<Paorb_a> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Paorb_a> {

		@Override
		public Paorb_a invoke(final Object[] children, final Object[] annotations) {
			return new Paorb_a(children[0]);
		}
	};

}
