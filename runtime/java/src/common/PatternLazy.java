package common;

import common.exceptions.PatternMatchFailure;

public abstract class PatternLazy {

	public abstract Object eval(DecoratedNode context, DecoratedNode scrutinee);
	
	public static Object runPattern(DecoratedNode context, DecoratedNode scrutinee, PatternLazy pl, Lazy otherwise) {
		
		while(scrutinee != null) {
			try {
				return pl.eval(context, scrutinee);
			} catch(PatternMatchFailure e) {
				scrutinee = scrutinee.forward();
			}
		}
		
		return otherwise.eval(context);
	}
}
