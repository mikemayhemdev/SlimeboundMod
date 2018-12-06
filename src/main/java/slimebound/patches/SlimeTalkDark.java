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
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedDark == false) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "~What~ ~is~ ~it~ ~doing~ ~here...~", 1.0F, 2.0F));
            SlimeboundMod.slimeTalkedDark = true;
        }
    }
}