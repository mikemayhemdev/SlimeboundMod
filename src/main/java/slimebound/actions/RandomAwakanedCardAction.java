package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import slimebound.SlimeboundMod;
import slimebound.cards.CaCaw;
import slimebound.cards.DarkVoid;
import slimebound.cards.SplitCultist;

import java.util.Random;


public class RandomAwakanedCardAction extends AbstractGameAction {
    public boolean upgradeCard;

    public RandomAwakanedCardAction(boolean upgraded) {
        this.upgradeCard = upgraded;


    }


    public void update() {

        AbstractCard c;
        do {
            c = CardLibrary.getRandomColorSpecificCard(AbstractCard.CardColor.COLORLESS, AbstractDungeon.cardRandomRng).makeCopy();
        } while (!c.hasTag(SlimeboundMod.STUDY_AWAKENEDONE));




        if (upgradeCard) {
            c.upgrade();
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

        this.isDone = true;
    }

}



