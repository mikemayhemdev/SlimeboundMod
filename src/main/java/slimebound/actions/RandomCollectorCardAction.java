package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import slimebound.SlimeboundMod;
import slimebound.cards.Collect;
import slimebound.cards.SplitTorchHead;
import slimebound.cards.YouAreMine;

import java.util.Random;


public class RandomCollectorCardAction extends AbstractGameAction {
    public boolean upgradeCard;

    public RandomCollectorCardAction(boolean upgraded) {
        this.upgradeCard = upgraded;


    }


    public void update() {

        AbstractCard c;
        do {
            c = CardLibrary.getRandomColorSpecificCard(AbstractCard.CardColor.COLORLESS, AbstractDungeon.cardRandomRng).makeCopy();
        } while (!c.hasTag(SlimeboundMod.STUDY_COLLECTOR));




        if (upgradeCard) {
            c.upgrade();
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

        this.isDone = true;
    }

}



