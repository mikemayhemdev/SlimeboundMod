package slimebound.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
import com.megacrit.cardcrawl.events.exordium.ScrapOoze;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.vfx.RefreshEnergyEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.relics.AggressiveSlimeRelic;
import slimebound.relics.ScrapOozeRelic;

import java.lang.reflect.Field;

@SpirePatch(clz = ScrapOoze.class,
        method = "buttonEffect")

public class ScrapOozePatch {
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public static void Prefix(ScrapOoze meObj, final int buttonPressed) {
       // logger.info("Patch hit");
        if (!(AbstractDungeon.player instanceof SlimeboundCharacter)) return;

        try {
            Field screenField = ScrapOoze.class.getDeclaredField("screenNum");
            screenField.setAccessible(true);

            switch (screenField.get(meObj).toString()) {
                case "0":
                    if (buttonPressed == 2) {
                        meObj.imageEventText.updateBodyText("Any slime who has found a different path is a friend in your cause.  With offerings of new scrap and treasure, the Scrap Ooze joins your quest.");
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, RelicLibrary.getRelic(ScrapOozeRelic.ID).makeCopy());

                        meObj.imageEventText.updateDialogOption(0, "Leave");
                        meObj.imageEventText.removeDialogOption(1);
                        meObj.imageEventText.removeDialogOption(2);

                        ReflectionHacks.setPrivate(meObj, ScrapOoze.class, "screenNum", 1);


                    }
                    if (buttonPressed == 0) {
                        meObj.imageEventText.updateDialogOption(2,"[Recruit] Locked.",true);
                    }
                    if (buttonPressed == 1) {
                        meObj.imageEventText.removeDialogOption(2);
                    }

                    break;

                case "RESULT":

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

