package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.Random;


public class RandomStudyCardAction extends AbstractGameAction {
    public boolean upgradeCard;

    public RandomStudyCardAction(boolean upgraded) {
        this.upgradeCard = upgraded;


    }


    public void update() {

        AbstractCard c;
        Random random = new Random();
        Integer chosenRand = random.nextInt(8);


        if (chosenRand == 0) {
            c = CardLibrary.getCard("StudyAutomaton").makeCopy();
        } else if (chosenRand == 1) {
            c = CardLibrary.getCard("StudyAwakened").makeCopy();
        } else if (chosenRand == 2) {
            c = CardLibrary.getCard("StudyChamp").makeCopy();
        } else if (chosenRand == 3) {
            c = CardLibrary.getCard("StudyCollector").makeCopy();
        } else if (chosenRand == 4) {
            c = CardLibrary.getCard("StudyGuardian").makeCopy();
        } else if (chosenRand == 5) {
            c = CardLibrary.getCard("StudyHexaghost").makeCopy();
        } else if (chosenRand == 6) {
            c = CardLibrary.getCard("StudyShapes").makeCopy();
        } else {
            c = CardLibrary.getCard("StudyTimeEater").makeCopy();
        }


        if (upgradeCard) {
            c.upgrade();
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

        this.isDone = true;
    }

}



