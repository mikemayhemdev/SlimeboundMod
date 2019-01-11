package slimebound.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(clz=SlimeBoss.class,method = SpirePatch.CONSTRUCTOR)
public class SlimeBossMaxHPChange {

    @SpirePostfixPatch
    public static void Postfix(SlimeBoss sb) {
        if (SlimeboundMod.huntedTriggered) {
            SlimeboundMod.huntedTriggered = false;
            if (CardCrawlGame.dungeon instanceof TheCity) {
                if (AbstractDungeon.ascensionLevel >= 9) {

                    sb.currentHealth = 200;
                    if (Settings.isEndless && AbstractDungeon.player.hasBlight("ToughEnemies")) {
                        float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
                        sb.currentHealth = (int) ((float) sb.currentHealth * mod);
                    }

                    if (ModHelper.isModEnabled("MonsterHunter")) {
                        sb.currentHealth = (int) ((float) sb.currentHealth * 1.5F);
                    }

                    sb.maxHealth = sb.currentHealth;

                } else {

                    sb.currentHealth = 180;
                    if (Settings.isEndless && AbstractDungeon.player.hasBlight("ToughEnemies")) {
                        float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
                        sb.currentHealth = (int) ((float) sb.currentHealth * mod);
                    }

                    if (ModHelper.isModEnabled("MonsterHunter")) {
                        sb.currentHealth = (int) ((float) sb.currentHealth * 1.5F);
                    }

                    sb.maxHealth = sb.currentHealth;
                }
            } else if (CardCrawlGame.dungeon instanceof Exordium) {

                if (AbstractDungeon.ascensionLevel >= 9) {

                    sb.currentHealth = 250;
                    if (Settings.isEndless && AbstractDungeon.player.hasBlight("ToughEnemies")) {
                        float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
                        sb.currentHealth = (int) ((float) sb.currentHealth * mod);
                    }

                    if (ModHelper.isModEnabled("MonsterHunter")) {
                        sb.currentHealth = (int) ((float) sb.currentHealth * 1.5F);
                    }

                    sb.maxHealth = sb.currentHealth;

                } else {

                    sb.currentHealth = 220;
                    if (Settings.isEndless && AbstractDungeon.player.hasBlight("ToughEnemies")) {
                        float mod = AbstractDungeon.player.getBlight("ToughEnemies").effectFloat();
                        sb.currentHealth = (int) ((float) sb.currentHealth * mod);
                    }

                    if (ModHelper.isModEnabled("MonsterHunter")) {
                        sb.currentHealth = (int) ((float) sb.currentHealth * 1.5F);
                    }

                    sb.maxHealth = sb.currentHealth;
                }

            }
        }
    }
}