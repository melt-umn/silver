
package silver.modification.collection.env_parser;

// top::IOperation ::= '++string' 
public final class PoperationPlusPlusStr extends silver.modification.collection.env_parser.NIOperation {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.modification.collection.env_parser.TPlusPlusStr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_collection_env_parser_operationPlusPlusStr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.collection.env_parser.NIOperation.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.collection.env_parser.NIOperation.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PoperationPlusPlusStr(final Object c__G_0) {
		super();
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.modification.collection.env_parser.TPlusPlusStr getChild__G_0() {
		return (silver.modification.collection.env_parser.TPlusPlusStr) (child__G_0 = common.Util.demand(child__G_0));
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:collection:env_parser:operationPlusPlusStr erroneously claimed to forward");
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
		return "silver:modification:collection:env_parser:operationPlusPlusStr";
	}

	static void initProductionAttributeDefinitions() {
		// top.operation = plusPlusOperationString()
		silver.modification.collection.env_parser.PoperationPlusPlusStr.synthesizedAttributes[silver.modification.collection.env_parser.Init.silver_modification_collection_operation__ON__silver_modification_collection_env_parser_IOperation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.modification.collection.NOperation)new silver.modification.collection.PplusPlusOperationString()); } };

	}

	public static final common.NodeFactory<PoperationPlusPlusStr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoperationPlusPlusStr> {

		@Override
		public PoperationPlusPlusStr invoke(final Object[] children, final Object[] annotations) {
			return new PoperationPlusPlusStr(children[0]);
		}
	};

}
