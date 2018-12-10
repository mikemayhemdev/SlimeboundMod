package slimebound.actions;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

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
                c = CardLibrary.getCard("Slimebound:SplitAttack").makeCopy();
                break;
            case 2:
                c = CardLibrary.getCard("Slimebound:SplitPoison").makeCopy();
                break;
            case 3:
                c = CardLibrary.getCard("Slimebound:SplitWeaken").makeCopy();
                break;
            case 4:
                c = CardLibrary.getCard("Slimebound:SplitSliming").makeCopy();
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



