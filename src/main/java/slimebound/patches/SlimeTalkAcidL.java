package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_L;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_S;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(clz= AcidSlime_L.class,method="takeTurn")
public class SlimeTalkAcidL {

    public static void Prefix(AcidSlime_L sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedAcidL == false) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "~What~ ~is~ ~it~ ~doing...~", 1.0F, 2.0F));
            SlimeboundMod.slimeTalkedAcidL = true;
        }
    }
}