package edu.umn.cs.melt.ableC.concretesyntax;

import java.util.*;

public abstract class NAddMulNoneOp_c extends common.Node implements core.Alocation {

	public static final int num_inh_attrs = Init.count_inh__ON__AddMulNoneOp_c;
	public static final int num_syn_attrs = Init.count_syn__ON__AddMulNoneOp_c;

	public static final String[] occurs_inh = new String[num_inh_attrs];
	public static final String[] occurs_syn = new String[num_syn_attrs];
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	public static final common.Lazy[] defaultSynthesizedAttributes = new common.Lazy[num_syn_attrs];

	protected NAddMulNoneOp_c(final Object a_core_location) {
		this.anno_core_location = a_core_location;
	}

	private Object anno_core_location;
	@Override
	public final core.NLocation getAnno_core_location() {
		return (core.NLocation) (anno_core_location = common.Util.demand(anno_core_location));
	}


	@Override
	public final int getNumberOfInhAttrs() {
		return num_inh_attrs;
	}

	@Override
	public final int getNumberOfSynAttrs() {
		return num_syn_attrs;
	}

	@Override
	public final common.Lazy getDefaultSynthesized(final int index) {
		return defaultSynthesizedAttributes[index];
	}

	@Override
	public final String getNameOfInhAttr(final int index) {
		return occurs_inh[index];
	}

	@Override
	public final String getNameOfSynAttr(final int index) {
		return occurs_syn[index];
	}

}
