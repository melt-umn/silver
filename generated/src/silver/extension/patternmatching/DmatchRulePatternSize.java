package silver.extension.patternmatching;

import java.util.*;

public class DmatchRulePatternSize extends common.Decorator {

public static final DmatchRulePatternSize singleton = new DmatchRulePatternSize();

	public void decorate(Class production) {
		decorateAutoCopy(production, "silver:extension:patternmatching:matchRulePatternSize");
	}
}
