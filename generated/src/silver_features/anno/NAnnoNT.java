package silver_features.anno;

import java.util.*;

public abstract class NAnnoNT extends common.Node implements silver_features.anno.Awhat {

	public static final int num_inh_attrs = Init.count_inh__ON__AnnoNT;
	public static final int num_syn_attrs = Init.count_syn__ON__AnnoNT;

	public static final String[] occurs_inh = new String[num_inh_attrs];
	public static final String[] occurs_syn = new String[num_syn_attrs];
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	public static final common.Lazy[] defaultSynthesizedAttributes = new common.Lazy[num_syn_attrs];

	protected NAnnoNT(final Object a_silver_features_anno_what) {
		this.anno_silver_features_anno_what = a_silver_features_anno_what;
	}

	private Object anno_silver_features_anno_what;
	@Override
	public final Integer getAnno_silver_features_anno_what() {
		return (Integer) (anno_silver_features_anno_what = common.Util.demand(anno_silver_features_anno_what));
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
