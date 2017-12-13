
package silver.modification.copper;

// top::ClassList ::= n::QName 
public final class PlexerClassesOne extends silver.modification.copper.NClassList {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_lexerClassesOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NClassList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NClassList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PlexerClassesOne(final Object c_n, final Object a_core_location) {
		super(a_core_location);
		this.child_n = c_n;

	}

	private Object child_n;
	public final silver.definition.core.NQName getChild_n() {
		return (silver.definition.core.NQName) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;

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
		return ((silver.modification.copper.NClassList)new silver.modification.copper.PlexerClassesMain(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PlexerClassesOne.i_n)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.copper.NClassList)new silver.modification.copper.PlexerClassesNull(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.modification.copper.PlexerClassesOne.i_n).undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.childDecorated(silver.modification.copper.PlexerClassesOne.i_n).undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:copper:lexerClassesOne";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PlexerClassesOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexerClassesOne> {

		@Override
		public PlexerClassesOne invoke(final Object[] children, final Object[] annotations) {
			return new PlexerClassesOne(children[0], annotations[0]);
		}
	};

}
