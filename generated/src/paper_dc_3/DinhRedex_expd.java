package paper_dc_3.transformed;

import java.util.*;

public class DinhRedex_expd extends common.Decorator {

public static final DinhRedex_expd singleton = new DinhRedex_expd();

	public void decorate(Class production) {
		decorateAutoCopy(production, "paper_dc_3:transformed:inhRedex_expd");
	}
}
