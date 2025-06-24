package common;

import silver.core.NLocation;
import common.javainterop.ConsCellCollection;

/**
 * Representation of silver:langutil:Message values when returned to CodeProber.
 * 
 * @author krame505
 */
public class CodeProberDiagnostic {
    private final String humanReadable;
    private final String diagnostic;
    private CodeProberDiagnostic(String humanReadable, String diagnostic) {
      this.humanReadable = humanReadable;
      this.diagnostic = diagnostic;
    }

    public String cpr_getOutput() { return humanReadable; }
    public String cpr_getDiagnostic() { return diagnostic; }

    public static CodeProberDiagnostic fromMessage(DataNode msg) {
        NLocation where = null;
        String output = null;
        String noLocOutput = null;
        int severity = -1;
        for(int i = 0; i < msg.getNumberOfSynAttrs(); i++) {
            switch(msg.getNameOfSynAttr(i)) {
            case "silver:langutil:where":
                where = (NLocation) msg.synthesized(i); break;
            case "silver:langutil:output":
                output = (String) msg.synthesized(i).toString(); break;
            case "silver:langutil:noLocOutput":
                noLocOutput = (String) msg.synthesized(i).toString(); break;
            case "silver:langutil:severity":
                severity = (Integer) msg.synthesized(i); break;
            }
        }
        int line = where.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location);
        int column = where.synthesized(silver.core.Init.silver_core_column__ON__silver_core_Location);
        int endLine = where.synthesized(silver.core.Init.silver_core_endLine__ON__silver_core_Location);
        int endColumn = where.synthesized(silver.core.Init.silver_core_endColumn__ON__silver_core_Location);
        String sevString = severity == 0? "INFO" : severity == 1? "WARN" : severity == 2? "ERR" : "HINT";
        return new CodeProberDiagnostic(
            output,
            String.format("%s@%d;%d;%s", sevString, (line << 12) + column, (endLine << 12) + endColumn, noLocOutput)
        );
    }
    public static java.util.List<CodeProberDiagnostic> fromMessageList(ConsCell msgs) {
        return new ConsCellCollection<>(msgs).stream()
            .map(m -> fromMessage((DataNode)m))
            .collect(java.util.stream.Collectors.toList());
    }
}
