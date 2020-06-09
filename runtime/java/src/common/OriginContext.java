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
	    NORMAL, MAINFUNCTION, FFI, PARSERACTION, GLOBAL, OTHER
	}

	public final Variety variety;
	public final Node lhs;
	public final List<NOriginNote> rules;

	private OriginContext(Variety variety, Node lhs, List<NOriginNote> rules) {
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

	public static final OriginContext BOGUS_CONTEXT =
		new OriginContext(Variety.OTHER, null, new ArrayList<NOriginNote>());

	public OriginContext(Node lhs, List<NOriginNote> rules) {
		this(Variety.NORMAL, lhs, rules);
	}

	public OriginContext(OriginContext old, List<NOriginNote> newRules) {
		this(old.variety, old.lhs, mergeRules(old.rules, newRules));
	}

	public OriginContext(OriginContext old, NOriginNote newRule) {
		this(old.variety, old.lhs, mergeRule(old.rules, newRule));
	}

	public OriginContext(OriginContext old, NOriginNote[] newRules) {
		this(old.variety, old.lhs, mergeRulesArr(old.rules, newRules));
	}

	private static List<NOriginNote> mergeRules(List<NOriginNote> a, List<NOriginNote> b) {
		List<NOriginNote> rules = new ArrayList<NOriginNote>();
		rules.addAll(a);
		rules.addAll(b);
		return rules;
	}

	private static List<NOriginNote> mergeRulesArr(List<NOriginNote> a, NOriginNote[] b) {
		List<NOriginNote> rules = new ArrayList<NOriginNote>();
		rules.addAll(a);
		Collections.addAll(rules, b);
		return rules;
	}

	private static List<NOriginNote> mergeRule(List<NOriginNote> a, NOriginNote b) {
		List<NOriginNote> rules = new ArrayList<NOriginNote>();
		rules.addAll(a);
		rules.add(b);
		return rules;
	}

	public ConsCell rulesAsSilverList() {
		return ConsCell.fromList(this.rules);
	}

	public NOriginInfo makeNewConstructionOrigin(boolean isContractum) {
		switch (this.variety) {
			case NORMAL:
				return new core.PoriginOriginInfo(null, OriginsUtil.SET_AT_CONSTRUCTION_OIT, this.lhs, this.rulesAsSilverList(), isContractum);

			case MAINFUNCTION:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_FROM_ENTRY_OIT, new common.StringCatter("Main Function"), ConsCell.nil);
			
			case FFI:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_FROM_FFI_OIT, new common.StringCatter("Called from FFI"), ConsCell.nil);

			case PARSERACTION:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_FROM_PARSER_ACTION_OIT, new common.StringCatter("Called inside a parser action block"), ConsCell.nil);

			case GLOBAL:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_IN_GLOBAL_OIT, new common.StringCatter("Built in a global"), ConsCell.nil);

			default:
				return new core.PotherOriginInfo(null, OriginsUtil.OTHER_BOGUS_OIT, new common.StringCatter("??? Unknown variety in OriginContext.makeNewConstructionOrigin: "+this.variety.toString()), ConsCell.nil);
		}
	}

	public <T extends Node> T attrAccessCopy(T arg) {
		switch (this.variety) {
			case NORMAL:
				return (T)arg.copy(this.lhs, this.rulesAsSilverList());

			default:
				return arg;
		}
	}

	public Object attrAccessCopyPoly(Object arg) {
		if (arg instanceof Node) return attrAccessCopy((Node)arg);
		return arg;
	}

	public Object attrAccessCopyPolyThunk(final Object t) {
		if (t instanceof Thunk)
			return new Thunk<Object>(() -> attrAccessCopyPoly(((Thunk<Object>)t).eval()));
		else
			return attrAccessCopyPoly(t);
	}
}