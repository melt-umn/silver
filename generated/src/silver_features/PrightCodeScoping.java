
package silver_features;

// top::Scope ::= s::String 
public final class PrightCodeScoping extends silver_features.NScope {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_rightCodeScoping;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NScope.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NScope.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PrightCodeScoping(final Object c_s) {
		super();
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:rightCodeScoping erroneously claimed to forward");
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
		return "silver_features:rightCodeScoping";
	}

	static void initProductionAttributeDefinitions() {
		// hi1 = let barAgain :: String = "asdf" in barAgain end
		silver_features.PrightCodeScoping.localAttributes[silver_features.Init.hi1__ON__silver_features_rightCodeScoping] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_184_barAgain = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("asdf")); } };
return ((common.StringCatter)(__SV_LOCAL_184_barAgain.eval())); } }).eval()); } };
		// hi2 = case rightCodeScoping("Huh",) of rightCodeScoping(barAgain) -> barAgain | _ -> "oh" end
		silver_features.PrightCodeScoping.localAttributes[silver_features.Init.hi2__ON__silver_features_rightCodeScoping] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_185___fail_186 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (new common.StringCatter("oh")); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver_features.PrightCodeScoping) { final common.Thunk<Object> __SV_LOCAL___pv188___sv_pv_187_barAgain = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_189_barAgain = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv188___sv_pv_187_barAgain.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_189_barAgain.eval())); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)(__SV_LOCAL_185___fail_186.eval()));}}.eval(context, (common.DecoratedNode)((silver_features.NScope)new silver_features.PrightCodeScoping((new common.StringCatter("Huh")))).decorate(context, (common.Lazy[])null)); } }).eval()); } };

	}

	public static final common.NodeFactory<PrightCodeScoping> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrightCodeScoping> {

		@Override
		public PrightCodeScoping invoke(final Object[] children, final Object[] annotations) {
			return new PrightCodeScoping(children[0]);
		}
	};

}
