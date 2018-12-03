/*    */ package slimebound.actions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import java.util.Random;

/*    */

/*    */
/*    */ public class TriggerSlimeAttacksAction extends AbstractGameAction {
        public boolean upgradeCard;
        public AbstractPlayer p;
    /*    */
    public TriggerSlimeAttacksAction(AbstractPlayer p)
    /*     */ {
        this.p=p;
        /*  25 */
        /*     */
    }

    /*    */
    /*    */
    /*    */
    public void update()
    /*    */ {
        /* 19 */

        for (AbstractOrb o : p.orbs) {
            /* 55 */
            if (o.ID == "TorchHeadSlime" ||
                    o.ID == "AttackSlime" ||
                    o.ID == "PoisonSlime" ||
                    o.ID == "SlimingSlime" ||
                    o.ID == "BronzeSlime" ||
                    o.ID == "DebuffSlime" ||
                    o.ID == "CultistSlime" ||
                    o.ID == "HexSlime") {
                o.onStartOfTurn();
                /*    */
            }
        }




            this.isDone = true;  /*    */
        }
        /*    */
    }



/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\actions\RandomBasicSlimeCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */