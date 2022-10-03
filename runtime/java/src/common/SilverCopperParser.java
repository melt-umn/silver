package common;

import java.util.List;
import edu.umn.cs.melt.copper.runtime.engines.CopperParser;
import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;

/**
 * Interface for Copper parser classes generated from Silver,
 * having a getTokens method for accessing the parsed terminals.
 * 
 * @author krame505
 */
public interface SilverCopperParser<ROOT> extends CopperParser<ROOT, CopperParserException> {
    /**
     * Get the list of terminals that were parsed in the most recent invocation
     * of this parser.
     * 
     * @return The parsed terminals, or null if this parser has not been run.
     */
    List<Terminal> getTokens();

    void setTabStop(int tabStop);
}
