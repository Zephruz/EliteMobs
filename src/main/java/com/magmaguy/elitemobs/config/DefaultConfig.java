package com.magmaguy.elitemobs.config;

import com.magmaguy.elitemobs.commands.admin.ReloadCommand;
import com.magmaguy.elitemobs.utils.ConfigurationLocation;
import com.magmaguy.elitemobs.utils.WarningMessage;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Created by MagmaGuy on 08/06/2017.
 */
public class DefaultConfig {
    @Getter
    private static boolean alwaysShowNametags;
    @Getter
    private static int superMobStackAmount;
    @Getter
    private static boolean preventCreeperDamageToPassiveMobs;
    @Getter
    private static boolean doPermissionTitles;
    @Getter
    private static boolean preventEliteMobConversionOfNamedMobs;
    @Getter
    private static boolean doStrictSpawningRules;
    @Getter
    private static double nightmareWorldSpawnBonus;
    @Getter
    private static boolean emLeadsToStatusMenu;
    @Getter
    private static boolean otherCommandsLeadToEMStatusMenu;
    @Getter
    private static boolean setupDone;
    @Getter
    private static Location defaultSpawnLocation;
    @Getter
    private static boolean doExplosionRegen;
    @Getter
    private static boolean preventVanillaReinforcementsForEliteEntities;
    @Getter
    private static boolean doRegenerateContainers;
    @Getter
    private static int defaultTransitiveBlockLimiter;
    @Getter
    private static boolean onlyUseBedrockMenus;
    @Getter
    private static int characterLimitForBookMenuPages;
    @Getter
    private static boolean useGlassToFillMenuEmptySpace;
    @Getter
    private static boolean menuUnicodeFormatting;
    @Getter
    private static String language;
    @Getter
    private static String noArenaPermissionMessage;

    private static File file = null;
    private static FileConfiguration fileConfiguration = null;

    private DefaultConfig() {
    }

    public static void toggleSetupDone() {
        setupDone = !setupDone;
        fileConfiguration.set("setupDoneV3", setupDone);
        save();
    }

    public static void save() {
        ConfigurationEngine.fileSaverOnlyDefaults(fileConfiguration, file);
    }


    public static void initializeConfig() {

        file = ConfigurationEngine.fileCreator("config.yml");
        fileConfiguration = ConfigurationEngine.fileConfigurationCreator(file);
        alwaysShowNametags = ConfigurationEngine.setBoolean(fileConfiguration, "alwaysShowEliteMobNameTags", false);
        superMobStackAmount = Math.max(ConfigurationEngine.setInt(fileConfiguration, "superMobStackAmount", 50), 1);
        preventCreeperDamageToPassiveMobs = ConfigurationEngine.setBoolean(fileConfiguration, "preventEliteCreeperDamageToPassiveMobs", true);
        doPermissionTitles = ConfigurationEngine.setBoolean(fileConfiguration, "useTitlesForMissingPermissionMessages", true);
        preventEliteMobConversionOfNamedMobs = ConfigurationEngine.setBoolean(fileConfiguration, "preventEliteMobConversionOfNamedMobs", true);
        doStrictSpawningRules = ConfigurationEngine.setBoolean(fileConfiguration, "enableHighCompatibilityMode", false);
        nightmareWorldSpawnBonus = ConfigurationEngine.setDouble(fileConfiguration, "nightmareWorldSpawnBonus", 0.5);
        emLeadsToStatusMenu = ConfigurationEngine.setBoolean(fileConfiguration, "emLeadsToStatusMenu", true);
        otherCommandsLeadToEMStatusMenu = ConfigurationEngine.setBoolean(fileConfiguration, "otherCommandsLeadToEMStatusMenu", true);
        setupDone = ConfigurationEngine.setBoolean(fileConfiguration, "setupDoneV3", false);
        preventVanillaReinforcementsForEliteEntities = ConfigurationEngine.setBoolean(fileConfiguration, "preventVanillaReinforcementsForEliteEntities", true);
        try {
            defaultSpawnLocation = ConfigurationLocation.serialize(
                    ConfigurationEngine.setString(file, fileConfiguration, "defaultSpawnLocation",
                            ConfigurationLocation.deserialize(Bukkit.getWorlds().get(0).getSpawnLocation()), false));
        } catch (Exception ex) {
            new WarningMessage("There is an issue with your defaultSpawnLocation in the config.yml configuration file! Fix it!");
        }

        doExplosionRegen = ConfigurationEngine.setBoolean(fileConfiguration, "doExplosionRegen", true);
        doRegenerateContainers = ConfigurationEngine.setBoolean(fileConfiguration, "doRegenerateContainers", true);
        defaultTransitiveBlockLimiter = ConfigurationEngine.setInt(fileConfiguration, "defaultTransitiveBlockLimiter", 500);
        onlyUseBedrockMenus = ConfigurationEngine.setBoolean(fileConfiguration, "onlyUseBedrockMenus", false);
        characterLimitForBookMenuPages = ConfigurationEngine.setInt(fileConfiguration, "characterLimitForBookMenuPages", 185);
        useGlassToFillMenuEmptySpace = ConfigurationEngine.setBoolean(fileConfiguration, "useGlassToFillMenuEmptySpace", false);
        menuUnicodeFormatting = ConfigurationEngine.setBoolean(fileConfiguration, "menuUnicodeFormatting", false);
        language = ConfigurationEngine.setString(file, fileConfiguration, "language", "english", false);
        noArenaPermissionMessage = ConfigurationEngine.setString(file, fileConfiguration, "noArenaPermissionMessage", "[EliteMobs] You don't have the permission to enter this arena!", true);

        ConfigurationEngine.fileSaverOnlyDefaults(fileConfiguration, file);
    }

    public static void setLanguage(Player player, String filename) {
        language = filename;
        fileConfiguration.set("language", filename);
        ConfigurationEngine.fileSaverCustomValues(fileConfiguration, file);
        ReloadCommand.reload(player);
    }

}
