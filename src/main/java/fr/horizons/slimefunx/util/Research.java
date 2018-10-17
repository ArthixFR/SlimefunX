package fr.horizons.slimefunx.util;

import fr.horizons.slimefunx.base.SlimefunObject;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class Research {
    private SlimefunObject slimefunObject;
    private UUID uuid;
    private Calendar startTime;
    private int xpCost;

    public Research(SlimefunObject slimefunObject, UUID uuid) {
        this.slimefunObject = slimefunObject;
        this.uuid = uuid;
        this.startTime = Calendar.getInstance();
        this.xpCost = slimefunObject.getResearchCost();
    }

    public boolean isResearched() {
        return getRemainingTimeInt() <= 0;
    }

    public String getRemainingTime() {
        if (isResearched()) return "§a§lComplété !";
        DecimalFormat format = new DecimalFormat("00");

        long time = startTime.getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();

        long difference = currentTime - time;

        long daysRemaining = difference / 86400;
        difference = difference % 86400;

        long hoursRemaining = difference / 3600;
        difference = difference % 3600;

        long minutesRemaining = difference / 60;
        difference = difference % 60;

        return (daysRemaining > 0 ? format.format(daysRemaining) + "j " : "") + format.format(hoursRemaining) + "h " + format.format(minutesRemaining) + "m " + format.format(difference) + "s";
    }

    public long getRemainingTimeInt() {
        return Calendar.getInstance().getTimeInMillis() - startTime.getTimeInMillis();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public SlimefunObject getSlimefunObject() {
        return slimefunObject;
    }

    public int getXpCost() {
        return xpCost;
    }
}
