package slimebound.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.scenes.TheCityScene;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import theAct.scenes.TheJungleScene;
@SpirePatch(cls= "theAct.scenes.TheJungleScene", method=SpirePatch.CONSTRUCTOR, optional=true)
public class JungleBGPatch {

    @SpirePostfixPatch
    public static void Postfix(TheJungleScene TheJungleScene_instance) {
        SlimeboundMod.logger.info("Jungle BG patch hit");
        if (Loader.isModLoaded("TheJungle")) {
            SlimeboundMod.logger.info("Jungle is loaded");
            if (AbstractDungeon.player instanceof SlimeboundCharacter) {
                SlimeboundMod.logger.info("Jungle BG patch success");
                TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("SlimeboundImages/scenes/Junglescene.atlas"));

                ReflectionHacks.setPrivate(TheJungleScene_instance, TheJungleScene.class, "fg", atlas.findRegion("mod/fg"));


            }
        }
    }
}
