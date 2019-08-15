package common.javainterop;

import java.util.Comparator;
import core.NOriginInfo;

import common.NodeFactory;

/**
 * Turns a Silver function into a Comparator.
 * 
 * Note that this Comparator cannot be "consistent with equals."
 * This is okay for, e.g. TreeSet, but not Set in general.
 * 
 * @param <T> The type being compared (probably just Object, for Silver data)
 * @author tedinski
 */
public class SilverComparator<T> implements Comparator<T> {

	private NodeFactory<Integer> cmpFunction;
	private common.OriginContext originCtx;
	
	public SilverComparator(final common.OriginContext originCtx, NodeFactory<Integer> cmp) {
		this.cmpFunction = cmp;
		this.originCtx = originCtx;
	}
	
	@Override
	public int compare(T arg0, T arg1) {
		return cmpFunction.invoke(originCtx, new Object[] { arg0, arg1 }, null);
	}

}
