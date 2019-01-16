package slimebound.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
import com.megacrit.cardcrawl.events.shrines.FaceTrader;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.CultistMask;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.relics.AggressiveSlimeRelic;

import java.lang.reflect.Field;
import java.util.ArrayList;

@SpirePatch(clz= GoopPuddle.class,
        method="buttonEffect")

public class GatherOozePatch {
  public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output

  public static void Postfix(GoopPuddle meObj, final int buttonPressed) {
    logger.info("Patch hit");
    if (!(AbstractDungeon.player instanceof SlimeboundCharacter)) return;

    try {
      Field screenField = GoopPuddle.class.getDeclaredField("screen");
      screenField.setAccessible(true);

      switch (screenField.get(meObj).toString()) {
        case "INTRO":
          break;

        case "RESULT":
          if (buttonPressed == 0 && !AbstractDungeon.player.hasRelic(AggressiveSlimeRelic.ID)) {
            meObj.imageEventText.updateBodyText("Feeling the sting of the goop as the prolonged exposure starts to melt away at your skin, you manage to fish out the #ygold - and find a #gfriend in the process!");
            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, RelicLibrary.getRelic(AggressiveSlimeRelic.ID).makeCopy());
          }
          break;
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }
}

