/*    */ package slimebound.actions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.Random;

/*    */

/*    */
/*    */ public class RandomLickCardAction extends AbstractGameAction {
        public boolean upgradeCard;
    /*    */
    public RandomLickCardAction(boolean upgraded)
    /*     */ {
        this.upgradeCard = upgraded;
        /*  25 */
        /*     */
    }

    /*    */
    /*    */
    /*    */
    public void update()
    /*    */ {
        /* 19 */
        AbstractCard c = null;
        Random random = new Random();
        Integer chosenRand = random.nextInt(6) + 1;

        switch(chosenRand){
            case 1: c = CardLibrary.getCard("Lick").makeCopy(); break;
            case 2: c = CardLibrary.getCard("PoisonLick").makeCopy(); break;
            case 3: c = CardLibrary.getCard("FocusedLick").makeCopy(); break;
            case 4: c = CardLibrary.getCard("SlimedLick").makeCopy(); break;
            case 5: c = CardLibrary.getCard("MegaLick").makeCopy(); break;
            case 6: c = CardLibrary.getCard("SamplingLick").makeCopy(); break;
        }



            if(upgradeCard){c.upgrade();}
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

            this.isDone = true;  /*    */
        }
        /*    */
    }



/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\actions\RandomBasicSlimeCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */