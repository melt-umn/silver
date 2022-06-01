package common;

import java.util.List;

/**
 * Interface for generated Copper parser classes that have a getTokens method
 * for accessing the parsed terminals.
 * 
 * @author krame505
 */
public interface HasTokens {
    /**
     * Get the list of terminals that were parsed in the most recent invocation
     * of this parser.
     * 
     * @return The parsed terminals, or null if this parser has not been run.
     */
    List<Terminal> getTokens();
}
