package slimeboundclassic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_M;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.characters.SlimeboundCharacter;

@SpirePatch(clz= SpikeSlime_M.class,method="takeTurn")
public class SlimeTalkSpikeM {
    private static final EventStrings eventStrings;
    public static final String[] DESCRIPTIONS;

    public static void Prefix(SpikeSlime_M sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedSpikeM == false) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, DESCRIPTIONS[9], 1.0F, 2.0F));
            SlimeboundMod.slimeTalkedSpikeM = true;
        }
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("SlimeboundClassic:SlimeTalk");
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    }
}