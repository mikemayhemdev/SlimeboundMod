package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.Random;


public class RandomLickCardAction extends AbstractGameAction {
    public boolean upgradeCard;

    public RandomLickCardAction(boolean upgraded) {
        this.upgradeCard = upgraded;


    }


    public void update() {

        AbstractCard c = null;
        Random random = new Random();
        Integer chosenRand = random.nextInt(5) + 1;

        switch (chosenRand) {
            case 1:
                c = CardLibrary.getCard("Slimebound:Lick").makeCopy();
                break;
            case 2:
                c = CardLibrary.getCard("Slimebound:PoisonLick").makeCopy();
                break;
            case 3:
                c = CardLibrary.getCard("Slimebound:HauntingLick").makeCopy();
                break;
            case 4:
                c = CardLibrary.getCard("Slimebound:MegaLick").makeCopy();
                break;
            case 5:
                c = CardLibrary.getCard("Slimebound:QuickLick").makeCopy();
                break;
        }


        if (upgradeCard) {
            c.upgrade();
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

        this.isDone = true;
    }

}



