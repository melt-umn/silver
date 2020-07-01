package common;

import core.*;
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
	public final List<NOriginNote> rules;

	private OriginContext(final Variety variety, final Node lhs, final List<NOriginNote> rules) {
		this.variety = variety;
		this.lhs = lhs;
		this.rules = rules;
	}

	public static final OriginContext ENTRY_CONTEXT =
		new OriginContext(Variety.MAINFUNCTION, null, new ArrayList<NOriginNote>());

	public static final OriginContext FFI_CONTEXT =
		new OriginContext(Variety.FFI, null, new ArrayList<NOriginNote>());

	public static final OriginContext PARSERACTION_CONTEXT =
		new OriginContext(Variety.PARSERACTION, null, new ArrayList<NOriginNote>());

	public static final OriginContext GLOBAL_CONTEXT =
		new OriginContext(Variety.GLOBAL, null, new ArrayList<NOriginNote>());

	public OriginContext(final Node lhs, final List<NOriginNote> rules) {
		this(Variety.NORMAL, lhs, rules);
	}

	public OriginContext(final Node lhs, final NOriginNote[] rules) {
		this(lhs, listOfArray(rules));
	}

	public OriginContext(final OriginContext old, final List<NOriginNote> newRules) {
		this(old.variety, old.lhs, mergeRules(old.rules, newRules));
	}

	public OriginContext(final OriginContext old, final NOriginNote newRule) {
		this(old.variety, old.lhs, mergeRule(old.rules, newRule));
	}

	public OriginContext(final OriginContext old, final NOriginNote[] newRules) {
		this(old.variety, old.lhs, mergeRulesArr(old.rules, newRules));
	}

	private static List<NOriginNote> mergeRules(final List<NOriginNote> a, final List<NOriginNote> b) {
		List<NOriginNote> rules = new ArrayList<NOriginNote>();
		rules.addAll(a);
		rules.addAll(b);
		return rules;
	}

	private static List<NOriginNote> mergeRulesArr(final List<NOriginNote> a, final NOriginNote[] b) {
		List<NOriginNote> rules = new ArrayList<NOriginNote>();
		rules.addAll(a);
		Collections.addAll(rules, b);
		return rules;
	}

	private static List<NOriginNote> mergeRule(final List<NOriginNote> a, final NOriginNote b) {
		List<NOriginNote> rules = new ArrayList<NOriginNote>();
		rules.addAll(a);
		rules.add(b);
		return rules;
	}

	private static List<NOriginNote> listOfArray(final NOriginNote[] arr) {
		ArrayList<NOriginNote> l = new ArrayList<NOriginNote>();
		Collections.addAll(l, arr);
		return l;
	}

	public ConsCell rulesAsSilverList() {
		return ConsCell.fromList(this.rules);
	}

	public NOriginInfo makeNewConstructionOrigin(boolean isContractum) {
		switch (this.variety) {
			case NORMAL:
				return new core.PoriginOriginInfo(null, OriginsUtil.SET_AT_CONSTRUCTION_OIT, this.lhs, this.rulesAsSilverList(), isContractum);

			case MAINFUNCTION:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_FROM_ENTRY_OIT, new common.StringCatter("Main Function"), this.rulesAsSilverList());
			
			case FFI:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_FROM_FFI_OIT, new common.StringCatter("Called from FFI"), this.rulesAsSilverList());

			case PARSERACTION:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_FROM_PARSER_ACTION_OIT, new common.StringCatter("Called inside a parser action block"), this.rulesAsSilverList());

			case GLOBAL:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_IN_GLOBAL_OIT, new common.StringCatter("Built in a global"), this.rulesAsSilverList());
		}
		throw new RuntimeException("Impossible state: this.variety not recognized.");
	}

	public <T extends Node> T attrAccessCopy(final T arg) {
		switch (this.variety) {
			case NORMAL: //We only copy if this is a 'normal' origin (i.e. it originates from a node)
				return (T)arg.copy(this.lhs, this.rulesAsSilverList());

			default:
				return arg;
		}
	}

	// Used by code that does some manipulation on a type-erased generic object that might be a nonterminal.
	public Object attrAccessCopyPoly(final Object arg) {
		if (arg instanceof Node) return attrAccessCopy((Node)arg);
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