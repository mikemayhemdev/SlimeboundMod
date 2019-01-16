/*    */ package slimebound.actions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

/*    */

/*    */
/*    */ public class CheckForSixHexAction extends AbstractGameAction {
        public boolean upgradeCard;
        public AbstractPlayer p;
    /*    */
    public CheckForSixHexAction(AbstractPlayer p)
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
        int hexslimecount=0;
        for (AbstractOrb o : AbstractDungeon.player.orbs){

            if (o.ID == "HexSlime") { // when equipped (picked up) this relic counts how many ethereal cards are in the player's deck
                hexslimecount++;

            }
        }
        if (hexslimecount>5) {
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3F));
            for (AbstractOrb o : AbstractDungeon.player.orbs) {


                com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.defect.EvokeOrbAction(1));




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