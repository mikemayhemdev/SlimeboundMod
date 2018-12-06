package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_S;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.exordium.AcidSlime_S",method="takeTurn")
public class SlimeTalkAcidS {

    public static void Prefix(AcidSlime_S sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedAcidS == false) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "~Tell~ ~the~ ~Boss...~", 1.0F, 2.0F));
            SlimeboundMod.slimeTalkedAcidS = true;
        }
    }
}