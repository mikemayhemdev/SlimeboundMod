package slimebound.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.Cutscene;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import slimebound.orbs.SpawnedSlime;

import java.util.ArrayList;

@SpirePatch(clz= EmptyOrbSlot.class,method="updateDescription"
   )
public class EmptyOrbSlotGraphicsPatch {
	static Texture NORMAL_ORB = ImageMaster.ORB_SLOT_1;
	static Texture SLIME_ORB = ImageMaster.loadImage("SlimeboundImages/orbs/empty1.png");
    public static void Postfix(EmptyOrbSlot EmptyOrbSlot_instance) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter) {
            ImageMaster.ORB_SLOT_1 = SLIME_ORB;
        } else {
        	ImageMaster.ORB_SLOT_1 = NORMAL_ORB;
        }
    }
}


