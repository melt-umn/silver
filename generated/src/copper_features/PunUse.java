
package copper_features;

// top::UseDcl ::= l::UnknownTerm 
public final class PunUse extends copper_features.NUseDcl {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = {copper_features.TUnknownTerm.class};

	public static final int num_local_attrs = Init.count_local__ON__copper_features_unUse;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[copper_features.NUseDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[copper_features.NUseDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PunUse(final Object c_l) {
		super();
		this.child_l = c_l;

	}

	private Object child_l;
	public final copper_features.TUnknownTerm getChild_l() {
		return (copper_features.TUnknownTerm) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

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
		throw new common.exceptions.SilverInternalError("Production copper_features:unUse erroneously claimed to forward");
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
		return "copper_features:unUse";
	}

	static void initProductionAttributeDefinitions() {
		// top.unknownsused = [ l.lexeme ]
		copper_features.PunUse.synthesizedAttributes[copper_features.Init.copper_features_unknownsused__ON__copper_features_UseDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(((common.StringCatter)((copper_features.TUnknownTerm)context.childAsIs(copper_features.PunUse.i_l)).lexeme), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PunUse> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PunUse> {

		@Override
		public PunUse invoke(final Object[] children, final Object[] annotations) {
			return new PunUse(children[0]);
		}
	};

}
