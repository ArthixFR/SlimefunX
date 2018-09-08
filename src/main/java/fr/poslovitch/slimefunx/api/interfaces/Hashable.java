package fr.poslovitch.slimefunx.api.interfaces;

/**
 * Provides reliable uniquely generated identification for a {@link fr.poslovitch.slimefunx.api.base.SlimefunObject} based on the String returned by {@link #getId()}.
 *
 * @author Poslovitch
 */
public interface Hashable {

    /**
     * Returns an alphanumeric String identifier that will be used to generate the hash.
     * @return an alphanumeric String identifier.
     */
    String getId();

    /**
     * Returns a statistically unique identifier generated using the value returned by {@link #getId()}.
     *
     * @return a unique String identifier.
     */
    default String getHash() {
        return "";
    }
}
