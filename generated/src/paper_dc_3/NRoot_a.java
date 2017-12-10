package paper_dc_3;

import java.util.*;

public abstract class NRoot_a extends common.Node implements silver.extension.bidirtransform.Alabels, silver.extension.bidirtransform.Aorigin, silver.extension.bidirtransform.Aredex {

	public static final int num_inh_attrs = Init.count_inh__ON__Root_a;
	public static final int num_syn_attrs = Init.count_syn__ON__Root_a;

	public static final String[] occurs_inh = new String[num_inh_attrs];
	public static final String[] occurs_syn = new String[num_syn_attrs];
	public static final LinkedList<common.Decorator> decorators = new LinkedList<common.Decorator>();

	public static final common.Lazy[] defaultSynthesizedAttributes = new common.Lazy[num_syn_attrs];

	protected NRoot_a(final Object a_silver_extension_bidirtransform_labels, final Object a_silver_extension_bidirtransform_origin, final Object a_silver_extension_bidirtransform_redex) {
		this.anno_silver_extension_bidirtransform_labels = a_silver_extension_bidirtransform_labels;
		this.anno_silver_extension_bidirtransform_origin = a_silver_extension_bidirtransform_origin;
		this.anno_silver_extension_bidirtransform_redex = a_silver_extension_bidirtransform_redex;
	}

	private Object anno_silver_extension_bidirtransform_labels;
	@Override
	public final common.ConsCell getAnno_silver_extension_bidirtransform_labels() {
		return (common.ConsCell) (anno_silver_extension_bidirtransform_labels = common.Util.demand(anno_silver_extension_bidirtransform_labels));
	}

	private Object anno_silver_extension_bidirtransform_origin;
	@Override
	public final silver.extension.bidirtransform.NOrigin getAnno_silver_extension_bidirtransform_origin() {
		return (silver.extension.bidirtransform.NOrigin) (anno_silver_extension_bidirtransform_origin = common.Util.demand(anno_silver_extension_bidirtransform_origin));
	}

	private Object anno_silver_extension_bidirtransform_redex;
	@Override
	public final core.NMaybe getAnno_silver_extension_bidirtransform_redex() {
		return (core.NMaybe) (anno_silver_extension_bidirtransform_redex = common.Util.demand(anno_silver_extension_bidirtransform_redex));
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
