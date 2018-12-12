package slimebound.actions;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import slimebound.cards.SplitAttack;
import slimebound.cards.SplitPoison;
import slimebound.cards.SplitSliming;
import slimebound.cards.SplitWeaken;

import java.util.Random;


public class RandomBasicSlimeCardAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
    public boolean upgradeCard;
    public boolean zeroCost;

    public RandomBasicSlimeCardAction(boolean upgraded, boolean zerocost) {
        this.upgradeCard = upgraded;
        this.zeroCost = zerocost;


    }


    public void update() {

        AbstractCard c = null;
        Random random = new Random();
        Integer chosenRand = random.nextInt(4) + 1;

        switch (chosenRand) {
            case 1:
                c = CardLibrary.getCard(SplitAttack.ID).makeCopy();
                break;
            case 2:
                c = CardLibrary.getCard(SplitPoison.ID).makeCopy();
                break;
            case 3:
                c = CardLibrary.getCard(SplitWeaken.ID).makeCopy();
                break;
            case 4:
                c = CardLibrary.getCard(SplitSliming.ID).makeCopy();
                break;
        }


        if (zeroCost) {
            c.setCostForTurn(-9);
        }

        if (upgradeCard) {
            c.upgrade();
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

        this.isDone = true;
    }

}



