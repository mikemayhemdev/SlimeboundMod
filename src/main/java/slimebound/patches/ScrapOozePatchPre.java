package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
import com.megacrit.cardcrawl.events.exordium.ScrapOoze;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;

@SpirePatch(clz = ScrapOoze.class,
        method = "onEnterRoom")

public class ScrapOozePatchPre {
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public static void Postfix(ScrapOoze meObj) {
       // logger.info("Patch hit");
        if (!(AbstractDungeon.player instanceof SlimeboundCharacter)) return;

        meObj.imageEventText.setDialogOption("[Recruit] Gain the #gScrap #gOoze. #rLocked if you Reach Inside.");
    }
}

