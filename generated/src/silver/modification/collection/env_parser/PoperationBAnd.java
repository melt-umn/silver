
package silver.modification.collection.env_parser;

// top::IOperation ::= '&&' 
public final class PoperationBAnd extends silver.modification.collection.env_parser.NIOperation {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.modification.collection.env_parser.TPAndTerm.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_collection_env_parser_operationBAnd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.collection.env_parser.NIOperation.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.collection.env_parser.NIOperation.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PoperationBAnd(final Object c__G_0) {
		super();
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.modification.collection.env_parser.TPAndTerm getChild__G_0() {
		return (silver.modification.collection.env_parser.TPAndTerm) (child__G_0 = common.Util.demand(child__G_0));
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:collection:env_parser:operationBAnd erroneously claimed to forward");
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
		return "silver:modification:collection:env_parser:operationBAnd";
	}

	static void initProductionAttributeDefinitions() {
		// top.operation = bandOperation()
		silver.modification.collection.env_parser.PoperationBAnd.synthesizedAttributes[silver.modification.collection.env_parser.Init.silver_modification_collection_operation__ON__silver_modification_collection_env_parser_IOperation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.modification.collection.NOperation)new silver.modification.collection.PbandOperation()); } };

	}

	public static final common.NodeFactory<PoperationBAnd> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoperationBAnd> {

		@Override
		public PoperationBAnd invoke(final Object[] children, final Object[] annotations) {
			return new PoperationBAnd(children[0]);
		}
	};

}
