package common;

import core.*;
import java.util.List;
import java.util.ArrayList;
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
	    NORMAL, MAINFUNCTION, FFI, REFLECTIVE, PARSERACTION, GLOBAL, OTHER
	}

	public final Variety variety;
	public final Node lhs;
	public final List<NOriginNote> rules;

	private OriginContext(Variety variety, Node lhs, List<NOriginNote> rules) {
		// if ((variety==Variety.NORMAL) && (lhs==null)) {
		// 	System.err.println("Origins Fail: OriginsContext/3: Just constructed an OriginContext with variety=NORMAL and lhs=null; rules="+rules);
		// 	throw new SilverInternalError("wrapInLink not overridden"); 
		// }
		this.variety = variety;
		this.lhs = lhs;
		this.rules = rules;
	}

	public static final OriginContext ENTRY_CONTEXT =
		new OriginContext(Variety.MAINFUNCTION, null, new ArrayList<NOriginNote>());

	public static final OriginContext FFI_CONTEXT =
		new OriginContext(Variety.FFI, null, new ArrayList<NOriginNote>());

	public static final OriginContext REFLECTION_CONTEXT =
		new OriginContext(Variety.REFLECTIVE, null, new ArrayList<NOriginNote>());

	public static final OriginContext PARSERACTION_CONTEXT =
		new OriginContext(Variety.PARSERACTION, null, new ArrayList<NOriginNote>());

	public static final OriginContext GLOBAL_CONTEXT =
		new OriginContext(Variety.GLOBAL, null, new ArrayList<NOriginNote>());

	public OriginContext(Node lhs, List<NOriginNote> rules) {
		this(Variety.NORMAL, lhs, rules);
	}




	public OriginContext(OriginContext old, List<NOriginNote> newRules) {
		this(old.variety, old.lhs, mergeRules(old.rules, newRules));
	}

	private static List<NOriginNote> mergeRules(List<NOriginNote> a, List<NOriginNote> b) {
		List<NOriginNote> rules = new ArrayList<NOriginNote>();
		rules.addAll(a);
		rules.addAll(b);
		return rules;
	}

	public ConsCell rulesAsSilverList() {
		return ConsCell.fromList(this.rules);
	}

	public NOriginInfo makeNewConstructionOrigin(boolean isContractum) {
		switch (this.variety) {
			case NORMAL:
				// if (this.lhs == null) {
				// 	System.err.println("Origins Warn: makeNewConstructionOrigin: variety == NORMAL but lhs == null!");
				// 	return new core.PotherOriginInfo(null, OriginsUtil.OTHER_BOGUS_OIT, new common.StringCatter("??? variety == NORMAL but lhs == null!"), ConsCell.nil);
				// }
				// if (!(this.lhs instanceof Node)) {
				// 	System.err.println("Origins Warn: attrAccessCopy: lhs not instanceof Node!");
				// 	return new core.PotherOriginInfo(null, OriginsUtil.OTHER_BOGUS_OIT, new common.StringCatter("??? lhs not instanceof Node!"), ConsCell.nil);
				// }
				return new core.PoriginOriginInfo(null, OriginsUtil.SET_AT_CONSTRUCTION_OIT, this.lhs, this.rulesAsSilverList(), false);

			case MAINFUNCTION:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_FROM_ENTRY_OIT, new common.StringCatter("Main Function"), ConsCell.nil);
			
			case FFI:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_FROM_FFI_OIT, new common.StringCatter("Called from FFI"), ConsCell.nil);

			case REFLECTIVE:
				return new core.PotherOriginInfo(null, OriginsUtil.SET_FROM_REFLECTION_OIT, new common.StringCatter("Called from Reflection"), ConsCell.nil);

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
				// if (this.lhs == null) {
				// 	System.err.println("Origins Warn: attrAccessCopy: variety == NORMAL but lhs == null!");
				// 	return arg;
				// }
				// if (!(this.lhs instanceof Node)) {
				// 	System.err.println("Origins Warn: attrAccessCopy: lhs not instanceof Node!");
				// 	return arg;
				// }
				return (T)arg.copy(this.lhs, ConsCell.nil);

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