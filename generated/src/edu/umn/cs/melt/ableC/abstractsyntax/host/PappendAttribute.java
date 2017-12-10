
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PappendAttribute extends common.FunctionNode {

	public static final int i_l1 = 0;
	public static final int i_l2 = 1;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_appendAttribute;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l1] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.num_inh_attrs];
	childInheritedAttributes[i_l2] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.num_inh_attrs];

	}

	public PappendAttribute(final Object c_l1, final Object c_l2) {
		this.child_l1 = c_l1;
		this.child_l2 = c_l2;

	}

	private Object child_l1;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes getChild_l1() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes) (child_l1 = common.Util.demand(child_l1));
	}

	private Object child_l2;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes getChild_l2() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes) (child_l2 = common.Util.demand(child_l2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l1: return getChild_l1();
			case i_l2: return getChild_l2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l1: return child_l1;
			case i_l2: return child_l2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:appendAttribute";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes invoke(final Object c_l1, final Object c_l2) {
		try {
		final common.DecoratedNode context = new PappendAttribute(c_l1, c_l2).decorate();
		//case l1 of nilAttribute() -> l2 | consAttribute(h, t) -> consAttribute(h, appendAttribute(t, l2,),) end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)(new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttribute) { final common.Thunk<Object> __SV_LOCAL___pv6804___sv_pv_6805_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv6806___sv_pv_6803_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6807_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv6806___sv_pv_6803_t.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6808_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv6804___sv_pv_6805_h.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttribute(common.Thunk.transformUndecorate(__SV_LOCAL_6808_h), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)edu.umn.cs.melt.ableC.abstractsyntax.host.PappendAttribute.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_6807_t), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PappendAttribute.i_l2)))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttribute) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PappendAttribute.i_l2).undecorate(); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Attribute.sv', 18, 4, 21, 7, 318, 433\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PappendAttribute.i_l1)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:appendAttribute", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PappendAttribute.invoke(children[0], children[1]);
		}
	};
}