package paper_dc_3;

import java.util.*;

public class DinhRedex_simp2 extends common.Decorator {

public static final DinhRedex_simp2 singleton = new DinhRedex_simp2();

	public void decorate(Class production) {
		decorateAutoCopy(production, "paper_dc_3:inhRedex_simp2");
	}
}
