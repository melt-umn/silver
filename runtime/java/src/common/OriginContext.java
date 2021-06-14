package common;

import silver.core.*;
import java.util.*;
import common.exceptions.*;


/**
 * Implementation of "the stuff on the left of the turnstile" that needs to be
 *  passed along for origin tracking. This would be a value type if Java
 *  supported them.
 * 
 * @author louisg
 */
public final class OriginContext {
	public enum Variety {
	    NORMAL, MAINFUNCTION, FFI, PARSERACTION, GLOBAL
	}

	public final Variety variety;
	public final Node lhs;
	public final NOriginNote[] rules;

	private OriginContext(final Variety variety, final Node lhs, final NOriginNote[] rules) {
		this.variety = variety;
		this.lhs = lhs;
		this.rules = rules;
	}

	public static final OriginContext ENTRY_CONTEXT =
		new OriginContext(Variety.MAINFUNCTION, null, null);

	public static final OriginContext FFI_CONTEXT =
		new OriginContext(Variety.FFI, null, null);

	public static final OriginContext PARSERACTION_CONTEXT =
		new OriginContext(Variety.PARSERACTION, null, null);

	public static final OriginContext GLOBAL_CONTEXT =
		new OriginContext(Variety.GLOBAL, null, null);

	public OriginContext(final Node lhs, final NOriginNote[] rules) {
		this(Variety.NORMAL, lhs, rules);
	}

	public OriginContext(final OriginContext old, final NOriginNote[] newRules) {
		this(old.variety, old.lhs, mergeRulesArr(old.rules, newRules));
	}

	private static NOriginNote[] mergeRulesArr(final NOriginNote[] a, final NOriginNote[] b) {
		if (a==null) return b;
		if (b==null) return a;

		NOriginNote[] result = new NOriginNote[a.length + b.length];
	    System.arraycopy(a, 0, result, 0, a.length);
	    System.arraycopy(b, 0, result, a.length, b.length);
    	return result;
	}

	public ConsCell rulesAsSilverList() {
		if (this.rules==null) return ConsCell.nil;

		ConsCell res = ConsCell.nil;
		for(int i=this.rules.length-1; i!=-1; i--) {
			res = new ConsCell(this.rules[i], res);
		}
		return res;
	}

	public NOriginInfo makeNewConstructionOrigin(boolean isContractum) {
		switch (this.variety) {
			case NORMAL:
				return new silver.core.PoriginOriginInfo(OriginsUtil.SET_AT_CONSTRUCTION_OIT, this.lhs, this.rulesAsSilverList(), isContractum);

			case MAINFUNCTION:
				return new silver.core.PotherOriginInfo(OriginsUtil.SET_FROM_ENTRY_OIT, new common.StringCatter("Main Function"), this.rulesAsSilverList());
			
			case FFI:
				return new silver.core.PotherOriginInfo(OriginsUtil.SET_FROM_FFI_OIT, new common.StringCatter("Called from FFI"), this.rulesAsSilverList());

			case PARSERACTION:
				return new silver.core.PotherOriginInfo(OriginsUtil.SET_FROM_PARSER_ACTION_OIT, new common.StringCatter("Called inside a parser action block"), this.rulesAsSilverList());

			case GLOBAL:
				return new silver.core.PotherOriginInfo(OriginsUtil.SET_IN_GLOBAL_OIT, new common.StringCatter("Built in a global"), this.rulesAsSilverList());
		}
		throw new RuntimeException("Impossible state: this.variety not recognized.");
	}

	public <T extends Tracked> T attrAccessCopy(final T arg) {
		switch (this.variety) {
			case NORMAL: //We only copy if this is a 'normal' origin (i.e. it originates from a node)
				return (T)arg.copy(this.lhs, this.rulesAsSilverList());

			default:
				return arg;
		}
	}

	// Used by code that does some manipulation on a type-erased generic object that might be a nonterminal.
	public Object attrAccessCopyPoly(final Object arg) {
		if (arg instanceof Tracked) return attrAccessCopy((Tracked)arg);
		return arg;
	}

	// Same as above but preserves laziness
	public Object attrAccessCopyPolyThunk(final Object t) {
		if (t instanceof Thunk)
			return new Thunk<Object>(() -> attrAccessCopyPoly(((Thunk<Object>)t).eval()));
		else
			return attrAccessCopyPoly(t);
	}
}