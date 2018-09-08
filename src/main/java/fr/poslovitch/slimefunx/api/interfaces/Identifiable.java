package fr.poslovitch.slimefunx.api.interfaces;

/**
 * Provides unique identification for a {@link fr.poslovitch.slimefunx.api.base.SlimefunObject} based on the String returned by {@link #getId()}.
 *
 * @author Poslovitch
 */
public interface Identifiable {

    /**
     * Returns an alphanumeric String identifier.
     * @return an alphanumeric String identifier.
     */
    String getId();
}
