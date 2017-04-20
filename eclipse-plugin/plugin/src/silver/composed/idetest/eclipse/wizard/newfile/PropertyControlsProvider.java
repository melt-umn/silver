
package silver.composed.idetest.eclipse.wizard.newfile;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import edu.umn.cs.melt.ide.silver.property.ui.*;

public class PropertyControlsProvider implements IPropertyControlsProvider {

  private List<PropertyControl> controls;

  @Override
  public List<PropertyControl> getPropertyControls(Composite panel) {
    if(controls == null) {
      controls = new ArrayList<PropertyControl>();

    controls.add(new TextPropertyControl(panel, "declared_grammar", "Grammar", "", true));

    }

    return controls;
  }

  @Override
  public boolean validateAll() {
    boolean valid = true;

    if(controls != null) {
      for(PropertyControl control : controls) {
        if(!control.validate()) {
          valid = false;
        }
      }
    }

    return valid;
  }
}
