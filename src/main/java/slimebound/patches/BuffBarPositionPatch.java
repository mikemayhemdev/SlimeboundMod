package slimebound.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import slimebound.characters.SlimeboundCharacter;
import slimebound.orbs.SpawnedSlime;

@SpirePatch(clz= AbstractCreature.class,method="renderPowerIcons",
        paramtypez = {
        SpriteBatch.class,
                float.class,
        float.class})
public class BuffBarPositionPatch {


    public static SpireReturn<Void> Prefix(AbstractCreature abstractCreature_instance, SpriteBatch sb, float x, float y) {

        if (abstractCreature_instance instanceof SlimeboundCharacter) {
            ((SlimeboundCharacter) abstractCreature_instance).renderPowerIcons(sb, x,y);
            return SpireReturn.Return(null);
        } else {

            return SpireReturn.Continue();

        }

    }

    }

