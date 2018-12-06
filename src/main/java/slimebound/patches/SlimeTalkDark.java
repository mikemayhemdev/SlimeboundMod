package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.beyond.Darkling",method="takeTurn")
public class SlimeTalkDark {

    public static void Prefix(Darkling sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedDark < 3) {
            String speech = "";
            switch (SlimeboundMod.slimeTalkedDark){
                case 0: speech = "~What~ ~is...~"; break;
                case 1: speech = "~...it~ ~doing...~"; break;
                case 2: speech = "~...in~ ~the~ ~Beyond...~"; break;
            }
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, speech, 0.5F, 2.0F));
            SlimeboundMod.slimeTalkedDark++;
        }
    }
}