package fr.poslovitch.slimefunx.api.recipes;

import fr.poslovitch.slimefunx.SlimefunX;
import fr.poslovitch.slimefunx.api.machines.SlimefunMachine;

public class MachineRecipe extends Recipe {

    private String machineId;
    private SlimefunMachine machine;

    protected MachineRecipe(String machineId, String[][] recipe) {
        super(Type.MACHINE, recipe);
        this.machineId = machineId;
    }

    public String getMachineId() {
        return machineId;
    }

    public SlimefunMachine getMachine() {
        if (machine == null) machine = SlimefunX.getInstance().getItemsManager().getMachineById(machineId).orElse(null);
        return machine;
    }
}
