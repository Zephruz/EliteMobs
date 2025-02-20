package com.magmaguy.elitemobs.config.npcs.premade;

import com.magmaguy.elitemobs.config.npcs.NPCsConfigFields;
import com.magmaguy.elitemobs.npcs.NPCInteractions;
import org.bukkit.entity.Villager;

import java.util.List;

public class WoodLeagueArenaMasterConfig extends NPCsConfigFields {
    public WoodLeagueArenaMasterConfig() {
        super("wood_league_arena_master",
                true,
                "Gladius",
                "<Arena Master>",
                Villager.Profession.ARMORER,
                "em_adventurers_guild,240.5,92,270,-108,0",
                List.of("Welcome to the\\nArena!"),
                List.of("Prepared to face the arena?"),
                List.of("Return with your shield, or on it!"),
                true,
                3,
                NPCInteractions.NPCInteractionType.ARENA_MASTER);
        setArenaFilename("wood_league.yml");
    }
}
