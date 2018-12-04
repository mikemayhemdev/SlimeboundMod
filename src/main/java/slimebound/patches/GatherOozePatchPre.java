package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(clz = GoopPuddle.class,
        method = "onEnterRoom")

public class GatherOozePatchPre {
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public static void Postfix(GoopPuddle meObj) {
        logger.info("Patch hit");
        if (!(AbstractDungeon.player instanceof SlimeboundCharacter)) return;

        try {
            meObj.imageEventText.updateDialogOption(0, "[Gather Gold] #gGain #g75 #gGold. #rLose 11 #rHP. Find a #gfriend.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

