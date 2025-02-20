package com.magmaguy.elitemobs.api;

import com.magmaguy.elitemobs.config.QuestsConfig;
import com.magmaguy.elitemobs.playerdata.database.PlayerData;
import com.magmaguy.elitemobs.quests.Quest;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class QuestRewardEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    @Getter
    private final Player player;
    @Getter
    private final Quest quest;

    public QuestRewardEvent(Player player, Quest quest) {
        this.player = player;
        this.quest = quest;

        quest.getQuestObjectives().setTurnedIn(true);

        doMessages();
        doRewards();

        completeQuestData();
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private void doMessages() {
        player.sendMessage(QuestsConfig.getQuestCompleteMessage().replace("$questName", quest.getQuestName()));
        if (QuestsConfig.isUseQuestCompleteTitles())
            player.sendTitle(
                    QuestsConfig.getQuestCompleteTitle().replace("$questName", quest.getQuestName()),
                    QuestsConfig.getQuestCompleteSubtitle().replace("$questName", quest.getQuestName()),
                    20, 60, 20);
    }

    private void doRewards() {
        quest.getQuestObjectives().getQuestReward().doRewards();
    }

    private void completeQuestData() {
        PlayerData.removeQuest(player.getUniqueId(), quest);
        PlayerData.incrementQuestsCompleted(player.getUniqueId());
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
