package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.city.TheCollector;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_S;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(cls="com.megacrit.cardcrawl.monsters.city.TheCollector",method="takeTurn")
public class SlimeTalkCollector {

    public static void Prefix(TheCollector sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedCollector == false && AbstractDungeon.player.hasRelic("StudyCardRelic")) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, "That hat... NL #r~I~ ~must~ ~have~ ~it!~", 1.0F, 2.0F));
            SlimeboundMod.slimeTalkedCollector = true;
        }
    }
}