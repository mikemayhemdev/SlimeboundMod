package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_M;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_S;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(clz= SpikeSlime_M.class,method="takeTurn")
public class SlimeTalkSpikeM {

    public static void Prefix(SpikeSlime_M sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedSpikeM == false) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "~It's~ ~so~ ~strong...~", 1.0F, 2.0F));
            SlimeboundMod.slimeTalkedSpikeM = true;
        }
    }
}