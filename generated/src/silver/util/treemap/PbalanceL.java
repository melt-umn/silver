
package silver.util.treemap;

public final class PbalanceL extends common.FunctionNode {

	public static final int i_lefttree = 0;
	public static final int i_righttree = 1;
	public static final int i_label = 2;
	public static final int i_values = 3;
	public static final int i_CMP = 4;


	public static final Class<?> childTypes[] = { silver.util.treemap.NTreeMap.class,silver.util.treemap.NTreeMap.class,Object.class,common.DecoratedNode.class,common.NodeFactory.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_treemap_balanceL;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_lefttree] = new common.Lazy[silver.util.treemap.NTreeMap.num_inh_attrs];
	childInheritedAttributes[i_righttree] = new common.Lazy[silver.util.treemap.NTreeMap.num_inh_attrs];

	}

	public PbalanceL(final Object c_lefttree, final Object c_righttree, final Object c_label, final Object c_values, final Object c_CMP) {
		this.child_lefttree = c_lefttree;
		this.child_righttree = c_righttree;
		this.child_label = c_label;
		this.child_values = c_values;
		this.child_CMP = c_CMP;

	}

	private Object child_lefttree;
	public final silver.util.treemap.NTreeMap getChild_lefttree() {
		return (silver.util.treemap.NTreeMap) (child_lefttree = common.Util.demand(child_lefttree));
	}

	private Object child_righttree;
	public final silver.util.treemap.NTreeMap getChild_righttree() {
		return (silver.util.treemap.NTreeMap) (child_righttree = common.Util.demand(child_righttree));
	}

	private Object child_label;
	public final Object getChild_label() {
		return (Object) (child_label = common.Util.demand(child_label));
	}

	private Object child_values;
	public final common.ConsCell getChild_values() {
		return (common.ConsCell) (child_values = common.Util.demand(child_values));
	}

	private Object child_CMP;
	public final common.NodeFactory<Integer> getChild_CMP() {
		return (common.NodeFactory<Integer>) (child_CMP = common.Util.demand(child_CMP));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lefttree: return getChild_lefttree();
			case i_righttree: return getChild_righttree();
			case i_label: return getChild_label();
			case i_values: return getChild_values();
			case i_CMP: return getChild_CMP();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lefttree: return child_lefttree;
			case i_righttree: return child_righttree;
			case i_label: return child_label;
			case i_values: return child_values;
			case i_CMP: return child_CMP;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return "silver:util:treemap:balanceL";
	}

	public static silver.util.treemap.NTreeMap invoke(final Object c_lefttree, final Object c_righttree, final Object c_label, final Object c_values, final Object c_CMP) {
		try {
		final common.DecoratedNode context = new PbalanceL(c_lefttree, c_righttree, c_label, c_values, c_CMP).decorate();
		//case lefttree of node(false, node(false, a, b, x1, x2, _), c, y1, y2, _) -> node(false, node(true, a, b, x1, x2, CMP), node(true, c, righttree, label, values, CMP), y1, y2, CMP) | node(false, a, node(false, b, c, y1, y2, _), x1, x2, _) -> node(false, node(true, a, b, x1, x2, CMP), node(true, c, righttree, label, values, CMP), y1, y2, CMP) | a -> node(true, a, righttree, label, values, CMP) end
		return (silver.util.treemap.NTreeMap)(((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10910___fail_10911 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10909_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return context.childDecorated(silver.util.treemap.PbalanceL.i_lefttree); } };
return ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(true, common.Thunk.transformUndecorate(__SV_LOCAL_10909_a), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.PbalanceL.i_righttree)), context.childAsIsLazy(silver.util.treemap.PbalanceL.i_label), context.childAsIsLazy(silver.util.treemap.PbalanceL.i_values), context.childAsIsLazy(silver.util.treemap.PbalanceL.i_CMP))); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, silver.util.treemap.NTreeMap>() { public final silver.util.treemap.NTreeMap eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.util.treemap.Pnode) { final common.Thunk<Object> __SV_LOCAL___pv10921___sv_tmp_pv_10920 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10922___sv_tmp_pv_10923 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv10924___sv_pv_10925_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv10926___sv_pv_10927_y1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv10928___sv_pv_10929_y2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(4); } };
final common.Thunk<Object> __SV_LOCAL___pv10930___sv_tmp_pv_10931 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<Integer>)scrutinee.childAsIs(5); } };
 return (silver.util.treemap.NTreeMap)new common.PatternLazy<Boolean, silver.util.treemap.NTreeMap>() { public final silver.util.treemap.NTreeMap eval(final common.DecoratedNode context, Boolean scrutineeIter) {final Boolean scrutinee = scrutineeIter; if(scrutinee == false) { return (silver.util.treemap.NTreeMap)((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10954___fail_10955 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, silver.util.treemap.NTreeMap>() { public final silver.util.treemap.NTreeMap eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.util.treemap.Pnode) { final common.Thunk<Object> __SV_LOCAL___pv10936___sv_tmp_pv_10935 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10937___sv_pv_10938_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv10939___sv_pv_10940_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv10941___sv_pv_10942_y1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv10943___sv_pv_10944_y2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(4); } };
final common.Thunk<Object> __SV_LOCAL___pv10945___sv_tmp_pv_10946 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<Integer>)scrutinee.childAsIs(5); } };
 return (silver.util.treemap.NTreeMap)new common.PatternLazy<Boolean, silver.util.treemap.NTreeMap>() { public final silver.util.treemap.NTreeMap eval(final common.DecoratedNode context, Boolean scrutineeIter) {final Boolean scrutinee = scrutineeIter; if(scrutinee == false) { return (silver.util.treemap.NTreeMap)((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10947_x2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv10928___sv_pv_10929_y2.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10948_x1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv10926___sv_pv_10927_y1.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10949_y2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv10943___sv_pv_10944_y2.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10950_y1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv10941___sv_pv_10942_y1.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10951_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10939___sv_pv_10940_c.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10952_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10937___sv_pv_10938_b.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10953_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10922___sv_tmp_pv_10923.eval())); } };
return ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(false, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(true, common.Thunk.transformUndecorate(__SV_LOCAL_10953_a), common.Thunk.transformUndecorate(__SV_LOCAL_10952_b), __SV_LOCAL_10948_x1, __SV_LOCAL_10947_x2, context.childAsIsLazy(silver.util.treemap.PbalanceL.i_CMP))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(true, common.Thunk.transformUndecorate(__SV_LOCAL_10951_c), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.PbalanceL.i_righttree)), context.childAsIsLazy(silver.util.treemap.PbalanceL.i_label), context.childAsIsLazy(silver.util.treemap.PbalanceL.i_values), context.childAsIsLazy(silver.util.treemap.PbalanceL.i_CMP))); } }, __SV_LOCAL_10950_y1, __SV_LOCAL_10949_y2, context.childAsIsLazy(silver.util.treemap.PbalanceL.i_CMP))); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }return ((silver.util.treemap.NTreeMap)(__SV_LOCAL_10910___fail_10911.eval()));}}.eval(context, (Boolean)((Boolean)(__SV_LOCAL___pv10936___sv_tmp_pv_10935.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.util.treemap.NTreeMap)(__SV_LOCAL_10910___fail_10911.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv10924___sv_pv_10925_c.eval()))); } };
return new common.PatternLazy<common.DecoratedNode, silver.util.treemap.NTreeMap>() { public final silver.util.treemap.NTreeMap eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.util.treemap.Pnode) { final common.Thunk<Object> __SV_LOCAL___pv10980___sv_tmp_pv_10979 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv10981___sv_pv_10982_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv10983___sv_pv_10984_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv10985___sv_pv_10986_x1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Object)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv10987___sv_pv_10988_x2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(4); } };
final common.Thunk<Object> __SV_LOCAL___pv10989___sv_tmp_pv_10990 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<Integer>)scrutinee.childAsIs(5); } };
 return (silver.util.treemap.NTreeMap)new common.PatternLazy<Boolean, silver.util.treemap.NTreeMap>() { public final silver.util.treemap.NTreeMap eval(final common.DecoratedNode context, Boolean scrutineeIter) {final Boolean scrutinee = scrutineeIter; if(scrutinee == false) { return (silver.util.treemap.NTreeMap)((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10991_y2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv10928___sv_pv_10929_y2.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10992_y1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv10926___sv_pv_10927_y1.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10993_c = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10924___sv_pv_10925_c.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10994_x2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv10987___sv_pv_10988_x2.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10995_x1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL___pv10985___sv_pv_10986_x1.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10996_b = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10983___sv_pv_10984_b.eval())); } };
return ((silver.util.treemap.NTreeMap)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_10997_a = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv10981___sv_pv_10982_a.eval())); } };
return ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(false, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(true, common.Thunk.transformUndecorate(__SV_LOCAL_10997_a), common.Thunk.transformUndecorate(__SV_LOCAL_10996_b), __SV_LOCAL_10995_x1, __SV_LOCAL_10994_x2, context.childAsIsLazy(silver.util.treemap.PbalanceL.i_CMP))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.treemap.NTreeMap)new silver.util.treemap.Pnode(true, common.Thunk.transformUndecorate(__SV_LOCAL_10993_c), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.util.treemap.PbalanceL.i_righttree)), context.childAsIsLazy(silver.util.treemap.PbalanceL.i_label), context.childAsIsLazy(silver.util.treemap.PbalanceL.i_values), context.childAsIsLazy(silver.util.treemap.PbalanceL.i_CMP))); } }, __SV_LOCAL_10992_y1, __SV_LOCAL_10991_y2, context.childAsIsLazy(silver.util.treemap.PbalanceL.i_CMP))); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }return ((silver.util.treemap.NTreeMap)(__SV_LOCAL_10954___fail_10955.eval()));}}.eval(context, (Boolean)((Boolean)(__SV_LOCAL___pv10980___sv_tmp_pv_10979.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.util.treemap.NTreeMap)(__SV_LOCAL_10954___fail_10955.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv10922___sv_tmp_pv_10923.eval()))); } }).eval()); }return ((silver.util.treemap.NTreeMap)(__SV_LOCAL_10910___fail_10911.eval()));}}.eval(context, (Boolean)((Boolean)(__SV_LOCAL___pv10921___sv_tmp_pv_10920.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.util.treemap.NTreeMap)(__SV_LOCAL_10910___fail_10911.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.util.treemap.PbalanceL.i_lefttree)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:treemap:balanceL", t);
		}
	}

	public static final common.NodeFactory<silver.util.treemap.NTreeMap> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.treemap.NTreeMap> {
		@Override
		public silver.util.treemap.NTreeMap invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbalanceL.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}