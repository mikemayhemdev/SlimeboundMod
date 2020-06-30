package slimeboundclassic.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.ScrapOoze;
import com.megacrit.cardcrawl.localization.EventStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeboundclassic.SlimeboundMod;
import slimeboundclassic.characters.SlimeboundCharacter;

@SpirePatch(clz = ScrapOoze.class,
        method = "onEnterRoom")

public class ScrapOozePatchPre {
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    private static final EventStrings eventStrings;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;

    public static void Postfix(ScrapOoze meObj) {
       // logger.info("Patch hit");
        if (!(AbstractDungeon.player instanceof SlimeboundCharacter)) return;

        meObj.imageEventText.setDialogOption(OPTIONS[0]);
    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("SlimeboundClassic:ScrapOozePatch");
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
    }
}

