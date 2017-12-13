
package silver.definition.flow.driver;

public final class PstitchEdgesFor extends common.FunctionNode {

	public static final int i_sp = 0;
	public static final int i_ntEnv = 1;
	public static final int i_prodEnv = 2;


	public static final Class<?> childTypes[] = { silver.definition.flow.driver.NStitchPoint.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_stitchEdgesFor;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sp] = new common.Lazy[silver.definition.flow.driver.NStitchPoint.num_inh_attrs];

	}

	public PstitchEdgesFor(final Object c_sp, final Object c_ntEnv, final Object c_prodEnv) {
		this.child_sp = c_sp;
		this.child_ntEnv = c_ntEnv;
		this.child_prodEnv = c_prodEnv;

	}

	private Object child_sp;
	public final silver.definition.flow.driver.NStitchPoint getChild_sp() {
		return (silver.definition.flow.driver.NStitchPoint) (child_sp = common.Util.demand(child_sp));
	}

	private Object child_ntEnv;
	public final Object getChild_ntEnv() {
		return (Object) (child_ntEnv = common.Util.demand(child_ntEnv));
	}

	private Object child_prodEnv;
	public final Object getChild_prodEnv() {
		return (Object) (child_prodEnv = common.Util.demand(child_prodEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sp: return getChild_sp();
			case i_ntEnv: return getChild_ntEnv();
			case i_prodEnv: return getChild_prodEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sp: return child_sp;
			case i_ntEnv: return child_ntEnv;
			case i_prodEnv: return child_prodEnv;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:definition:flow:driver:stitchEdgesFor";
	}

	public static common.ConsCell invoke(final Object c_sp, final Object c_ntEnv, final Object c_prodEnv) {
		try {
		final common.DecoratedNode context = new PstitchEdgesFor(c_sp, c_ntEnv, c_prodEnv).decorate();
		//sp.stitchEdges
		return (common.ConsCell)(((common.ConsCell)context.childDecorated(silver.definition.flow.driver.PstitchEdgesFor.i_sp).synthesized(silver.definition.flow.driver.Init.silver_definition_flow_driver_stitchEdges__ON__silver_definition_flow_driver_StitchPoint)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:stitchEdgesFor", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PstitchEdgesFor.invoke(children[0], children[1], children[2]);
		}
	};
}