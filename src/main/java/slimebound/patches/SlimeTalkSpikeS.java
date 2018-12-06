package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_S;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_S",method="takeTurn")
public class SlimeTalkSpikeS {

    public static void Prefix(SpikeSlime_S sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedSpikeS == false) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "~It's~ ~like~ ~us...~", 1.0F, 2.0F));
            SlimeboundMod.slimeTalkedSpikeS = true;
        }
    }
}