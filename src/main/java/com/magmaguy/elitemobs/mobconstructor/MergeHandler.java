package com.magmaguy.elitemobs.mobconstructor;

import com.magmaguy.elitemobs.config.ValidWorldsConfig;
import com.magmaguy.elitemobs.mobconstructor.mobdata.passivemobs.SuperMobProperties;
import com.magmaguy.elitemobs.mobs.passive.PassiveEliteMobDeathHandler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * Created by MagmaGuy on 15/07/2017.
 */
public class MergeHandler implements Listener {

    @EventHandler
    public void onDamageMerge(EntityDamageEvent event) {

        if (!ValidWorldsConfig.getFileConfiguration().getBoolean("Valid worlds." + event.getEntity().getWorld().getName()))
            return;
        validateEntityType(event.getEntity());

    }

    @EventHandler
    public void onSpawnMerge(EntitySpawnEvent event) {

        if (!ValidWorldsConfig.getFileConfiguration().getBoolean("Valid worlds." + event.getEntity().getWorld().getName()))
            return;
        validateEntityType(event.getEntity());

    }

    private void validateEntityType(Entity eventEntity) {

        if (eventEntity == null) return;
        if (!(eventEntity instanceof LivingEntity)) return;

        if (SuperMobProperties.isValidSuperMobType(eventEntity))
            PassiveEliteMobDeathHandler.SuperMobScanner.newSuperMobScan((LivingEntity) eventEntity);

    }

}
