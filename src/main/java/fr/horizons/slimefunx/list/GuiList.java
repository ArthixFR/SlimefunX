package fr.horizons.slimefunx.list;

import fr.horizons.slimefunx.gui.SlimefunGui;
import fr.horizons.slimefunx.gui.SlimefunGuiResource;
import fr.horizons.slimefunx.gui.guis.SlimefunGuideGui;
import fr.horizons.slimefunx.gui.resources.BigButtonSelect;
import fr.horizons.slimefunx.gui.resources.BigButtonUnselect;
import fr.horizons.slimefunx.gui.resources.GuideMain;

public class GuiList {
    // GUI
    public static SlimefunGui SLIMEFUN_GUIDE = new SlimefunGuideGui("guide");

    // GUI RESOURCES
    public static SlimefunGuiResource BIG_BUTTON_UNSELECT = new BigButtonUnselect("big_button_unselect", 1561);
    public static SlimefunGuiResource GUIDE_GROUP_SELECTED = new BigButtonSelect("big_button_select", 1560);

    public static SlimefunGuiResource GUIDE_MAIN = new GuideMain("guide_main", 1);
}
