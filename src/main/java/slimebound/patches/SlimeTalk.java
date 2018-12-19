package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(clz=SlimeBoss.class,method="takeTurn")
public class SlimeTalk {

    public static void Prefix(SlimeBoss sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalked == false) {
            AbstractDungeon.actionManager.addToBottom(new ShoutAction(sb, "~MINION...~ NL #r~BETRAYER!!!~", 1.0F, 2.0F));
            SlimeboundMod.slimeTalked = true;
        }
    }
}