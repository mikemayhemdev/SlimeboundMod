package slimeboundclassic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.characters.SlimeboundCharacter;
import slimeboundclassic.relics.StudyCardRelic;

@SpirePatch(clz=SlimeBoss.class,method="takeTurn")
public class SlimeTalk {
    private static final EventStrings eventStrings;
    public static final String[] DESCRIPTIONS;

    public static void Prefix(SlimeBoss sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalked == false) {
            if (AbstractDungeon.player.hasRelic(StudyCardRelic.ID)){
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(sb, DESCRIPTIONS[11], 1.0F, 2.0F));

            } else {
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(sb, DESCRIPTIONS[0], 1.0F, 2.0F));
            }
            SlimeboundMod.slimeTalked = true;
        }
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("SlimeboundClassic:SlimeTalk");
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    }
}