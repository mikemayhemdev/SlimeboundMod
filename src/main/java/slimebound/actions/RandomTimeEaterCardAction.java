package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import slimebound.cards.HeadSlam;
import slimebound.cards.ManipulateTime;
import slimebound.cards.TimeRipple;

import java.util.Random;


public class RandomTimeEaterCardAction extends AbstractGameAction {
    public boolean upgradeCard;

    public RandomTimeEaterCardAction(boolean upgraded) {
        this.upgradeCard = upgraded;


    }


    public void update() {

        AbstractCard c = null;
        Random random = new Random();
        Integer chosenRand = random.nextInt(3) + 1;

        switch (chosenRand) {
            case 1:
                c = CardLibrary.getCard(TimeRipple.ID).makeCopy();
                break;
            case 2:
                c = CardLibrary.getCard(ManipulateTime.ID).makeCopy();
                break;
            case 3:
                c = CardLibrary.getCard(HeadSlam.ID).makeCopy();
                break;
        }


        if (upgradeCard) {
            c.upgrade();
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

        this.isDone = true;
    }

}



