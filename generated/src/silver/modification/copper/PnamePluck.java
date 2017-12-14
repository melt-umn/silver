
package silver.modification.copper;

// top::Name ::= 'pluck' 
public final class PnamePluck extends silver.definition.core.NName {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.modification.copper.TPluck_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_namePluck;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnamePluck(final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.modification.copper.TPluck_kwd getChild__G_0() {
		return (silver.modification.copper.TPluck_kwd) (child__G_0 = common.Util.demand(child__G_0));
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NName)silver.definition.core.Pname.invoke((new common.StringCatter("pluck")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:copper:namePluck";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PnamePluck> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnamePluck> {

		@Override
		public PnamePluck invoke(final Object[] children, final Object[] annotations) {
			return new PnamePluck(children[0], annotations[0]);
		}
	};

}
