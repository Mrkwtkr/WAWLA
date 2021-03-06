package net.darkhax.wawla.modules;

import java.util.ArrayList;
import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.darkhax.wawla.util.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public class ModulePets extends Module {

    public static ArrayList<String> nbtNames = new ArrayList<String>();

    public ModulePets(boolean enabled) {

        super(enabled);
        nbtNames.add("Owner");
        nbtNames.add("OwnerName");
        nbtNames.add("owner");
        nbtNames.add("ownerName");
    }

    @Override
    public void onWailaEntityDescription(Entity entity, List<String> tooltip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {

        if (config.getConfig("wawla.pet.showOwner")) {

            NBTTagCompound tag = Utilities.convertEntityToNbt(entity);
            NBTTagCompound extTag = entity.getEntityData();
            for (String currentKey : nbtNames) {

                if (tag.hasKey(currentKey) && !tag.getString(currentKey).isEmpty())
                    tooltip.add(StatCollector.translateToLocal("tooltip.wawla.owner") + ": " + tag.getString(currentKey));

                if (extTag.hasKey(currentKey) && !extTag.getString(currentKey).isEmpty())
                    tooltip.add(StatCollector.translateToLocal("tooltip.wawla.owner") + ": " + extTag.getString(currentKey));
            }
        }
    }

    @Override
    public void onWailaRegistrar(IWailaRegistrar register) {

        register.addConfig("Wawla", "wawla.pet.showOwner");
    }
}