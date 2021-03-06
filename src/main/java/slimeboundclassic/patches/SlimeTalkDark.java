package slimeboundclassic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.characters.SlimeboundCharacter;

@SpirePatch(clz=Darkling.class,method="takeTurn")
public class SlimeTalkDark {
    private static final EventStrings eventStrings;
    public static final String[] DESCRIPTIONS;

    public static void Prefix(Darkling sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedDark < 3) {
            String speech = "";
            switch (SlimeboundMod.slimeTalkedDark){
                case 0: speech = DESCRIPTIONS[5]; break;
                case 1: speech = DESCRIPTIONS[6]; break;
                case 2: speech = DESCRIPTIONS[7]; break;
            }
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, speech, 0.5F, 2.0F));
            SlimeboundMod.slimeTalkedDark++;
        }
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("SlimeboundClassic:SlimeTalk");
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    }
}