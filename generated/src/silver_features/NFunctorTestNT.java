package silver_features;

import java.util.*;

public abstract class NFunctorTestNT extends common.Node implements silver_features.AfunctorTestAnno {

	public static final int num_inh_attrs = Init.count_inh__ON__FunctorTestNT;
	public static final int num_syn_attrs = Init.count_syn__ON__FunctorTestNT;

	public static final String[] occurs_inh = new String[num_inh_attrs];
	public static final String[] occurs_syn = new String[num_syn_attrs];
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	public static final common.Lazy[] defaultSynthesizedAttributes = new common.Lazy[num_syn_attrs];

	protected NFunctorTestNT(final Object a_silver_features_functorTestAnno) {
		this.anno_silver_features_functorTestAnno = a_silver_features_functorTestAnno;
	}

	private Object anno_silver_features_functorTestAnno;
	@Override
	public final Integer getAnno_silver_features_functorTestAnno() {
		return (Integer) (anno_silver_features_functorTestAnno = common.Util.demand(anno_silver_features_functorTestAnno));
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
