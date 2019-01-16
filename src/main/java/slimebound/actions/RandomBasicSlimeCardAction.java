/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import slimebound.cards.AttackSlime;

import java.util.Random;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/*    */
/*    */ public class RandomBasicSlimeCardAction extends com.megacrit.cardcrawl.actions.AbstractGameAction {
        public boolean upgradeCard;
    public boolean zeroCost;
    /*    */
    public RandomBasicSlimeCardAction(boolean upgraded, boolean zerocost)
    /*     */ {
        this.upgradeCard = upgraded;
        this.zeroCost = zerocost;
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
        Integer chosenRand = random.nextInt(4) + 1;

        switch(chosenRand){
            case 1: c = CardLibrary.getCard("AttackSlime").makeCopy(); break;
            case 2: c = CardLibrary.getCard("PoisonSlime").makeCopy(); break;
            case 3: c = CardLibrary.getCard("DebuffSlime").makeCopy(); break;
            case 4: c = CardLibrary.getCard("SlimingSlime").makeCopy(); break;
        }


        if(zeroCost) {c.setCostForTurn(-9);}
           //
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