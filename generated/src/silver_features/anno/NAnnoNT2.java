package silver_features.anno;

import java.util.*;

public abstract class NAnnoNT2 extends common.Node implements silver_features.anno.Aanno1, silver_features.anno.Aanno2 {

	public static final int num_inh_attrs = Init.count_inh__ON__AnnoNT2;
	public static final int num_syn_attrs = Init.count_syn__ON__AnnoNT2;

	public static final String[] occurs_inh = new String[num_inh_attrs];
	public static final String[] occurs_syn = new String[num_syn_attrs];
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	public static final common.Lazy[] defaultSynthesizedAttributes = new common.Lazy[num_syn_attrs];

	protected NAnnoNT2(final Object a_silver_features_anno_anno1, final Object a_silver_features_anno_anno2) {
		this.anno_silver_features_anno_anno1 = a_silver_features_anno_anno1;
		this.anno_silver_features_anno_anno2 = a_silver_features_anno_anno2;
	}

	private Object anno_silver_features_anno_anno1;
	@Override
	public final Integer getAnno_silver_features_anno_anno1() {
		return (Integer) (anno_silver_features_anno_anno1 = common.Util.demand(anno_silver_features_anno_anno1));
	}

	private Object anno_silver_features_anno_anno2;
	@Override
	public final common.StringCatter getAnno_silver_features_anno_anno2() {
		return (common.StringCatter) (anno_silver_features_anno_anno2 = common.Util.demand(anno_silver_features_anno_anno2));
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
