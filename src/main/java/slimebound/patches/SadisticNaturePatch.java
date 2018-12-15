package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.colorless.SadisticNature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.relics.AggressiveSlimeRelic;

import java.lang.reflect.Field;

@SpirePatch(clz = SadisticNature.class,
        method = SpirePatch.CONSTRUCTOR)

public class SadisticNaturePatch {
    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public static void Postfix(SadisticNature meObj) {
        logger.info("Sadistic Nature Patch hit");
        if (AbstractDungeon.isPlayerInDungeon()) {
            if ((AbstractDungeon.player instanceof SlimeboundCharacter)) {
                meObj.cost = 2;
            }
        }

    }
}

