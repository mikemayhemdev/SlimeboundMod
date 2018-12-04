 package slimebound.actions;



import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.Random;




 public class RandomChampCardAction extends AbstractGameAction {
        public boolean upgradeCard;

    public RandomChampCardAction(boolean upgraded)
     {
        this.upgradeCard = upgraded;


    }




    public void update()
     {

        AbstractCard c = null;
        Random random = new Random();
        Integer chosenRand = random.nextInt(3) + 1;

        switch(chosenRand){
            case 1: c = CardLibrary.getCard("FaceSlap").makeCopy(); break;
            case 2: c = CardLibrary.getCard("DefensiveStance").makeCopy(); break;
            case 3: c = CardLibrary.getCard("LastStand").makeCopy(); break;
        }




            if(upgradeCard){c.upgrade();}
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

            this.isDone = true;
        }

    }



