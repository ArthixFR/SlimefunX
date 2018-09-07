package fr.poslovitch.slimefunx.api.interfaces;

/**
 * Provides reliable uniquely generated identification for a {@link fr.poslovitch.slimefunx.api.base.SlimefunObject} based on the String returned by {@link #getId()}.
 * It uses {@link fr.poslovitch.slimefunx.api.hashing.ItemHash} to generate a statistically unique identifier ({@code hash}), however persistent through server restarts.
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
     * By default, it provides its own implementation using {@link fr.poslovitch.slimefunx.api.hashing.ItemHash}.
     *
     * @return a unique String identifier.
     */
    default String getHash() {
        return "";
    }
}
