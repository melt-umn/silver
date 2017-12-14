package paper_dc_3;

import java.util.*;

public class DinhRedex_simp1 extends common.Decorator {

public static final DinhRedex_simp1 singleton = new DinhRedex_simp1();

	public void decorate(Class production) {
		decorateAutoCopy(production, "paper_dc_3:inhRedex_simp1");
	}
}
