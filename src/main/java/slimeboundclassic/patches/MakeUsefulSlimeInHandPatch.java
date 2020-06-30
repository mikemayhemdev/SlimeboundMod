package slimeboundclassic.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import slimeboundclassic.cards.UsefulSlime;
import slimeboundclassic.characters.SlimeboundCharacter;

@SpirePatch(clz= MakeTempCardInHandAction.class,method = SpirePatch.CONSTRUCTOR,
        paramtypez = {
                AbstractCard.class,
                int.class})
public class MakeUsefulSlimeInHandPatch {

    @SpirePostfixPatch
    public static void Postfix(MakeTempCardInHandAction obj, AbstractCard card, int amount) {

            if (AbstractDungeon.player instanceof SlimeboundCharacter && card instanceof Slimed) {
                ReflectionHacks.setPrivate(obj, MakeTempCardInHandAction.class, "c", new UsefulSlime());

            }
        }
    }



