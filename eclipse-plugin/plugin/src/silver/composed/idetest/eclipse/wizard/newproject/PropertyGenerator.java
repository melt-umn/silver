
package silver.composed.idetest.eclipse.wizard.newproject;

import java.util.ArrayList;
import java.util.List;

public class PropertyGenerator {
    
    private static String properties = null;
    
    public static String getAll() {
        if(properties==null) {
            StringBuilder sb = new StringBuilder();
    
    sb.append("grammar_to_compile/string = ");sb.append(escape(""));sb.append("\n");

    
            properties = sb.toString();
        }
    
        return properties;
    }
    

    private static String escape(String str) {
        char[] orig = str.toCharArray();
        List<Character> list = new ArrayList<Character>();
        for(char c : orig) {
            if(c == '=' || c == '#' || c == '\\' || c == ':') {
               list.add('\\');
            }
            list.add(c);
        }
        
        //Convert to a char array
        char[] mod = new char[list.size()];
        for(int i = 0; i < mod.length; i++) {
            mod[i] = list.get(i);
        }
            
        return new String(mod);
    }
}
