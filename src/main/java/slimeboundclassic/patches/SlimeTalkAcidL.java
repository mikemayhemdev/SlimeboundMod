package slimeboundclassic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_L;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.characters.SlimeboundCharacter;

@SpirePatch(clz= AcidSlime_L.class,method="takeTurn")
public class SlimeTalkAcidL {
    private static final EventStrings eventStrings;
    public static final String[] DESCRIPTIONS;

    public static void Prefix(AcidSlime_L sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedAcidL == false) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, DESCRIPTIONS[1], 1.0F, 2.0F));
            SlimeboundMod.slimeTalkedAcidL = true;
        }
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("SlimeboundClassic:SlimeTalk");
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    }
}