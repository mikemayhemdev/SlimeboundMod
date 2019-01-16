/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
/*    */ import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import slimebound.vfx.SlimeDripsEffect;
import slimebound.vfx.SlimeWaterDropEffect;
/*    */

/*    */
/*    */ public class SlimeSpawnAction extends AbstractGameAction
/*    */ {
    /*    */   private AbstractOrb orbType;
    private boolean SelfDamage = true;
    /* 16 */   private boolean autoEvoke = false;
    private int currentAmount;

    /*    */
    /*    */
    public SlimeSpawnAction(AbstractOrb newOrbType, boolean selfDamage)
    /*    */ {
        /* 20 */
        this(newOrbType, true, selfDamage);
        /*    */
    }

    /*    */
    /*    */
    public SlimeSpawnAction(AbstractOrb newOrbType, boolean autoEvoke, boolean SelfDamage)
    /*    */ {
        /* 25 */
        this.duration = Settings.ACTION_DUR_FAST;
        /* 26 */
        this.orbType = newOrbType;
        /* 27 */
        this.autoEvoke = autoEvoke;
        this.SelfDamage = SelfDamage;
        this.currentAmount = currentAmount;
        /*    */
    }

    /*    */
    /*    */
    public void update()
    /*    */ {
        /* 32 */
        if (SelfDamage) {
            if (AbstractDungeon.player.hasPower("SplitForLessPower")) {
                currentAmount = 3 - AbstractDungeon.player.getPower("SplitForLessPower").amount;
            } else {
                currentAmount = 3;
            }
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, currentAmount, AttackEffect.NONE));
            /* 54 */


            /*    */
        }
        AbstractDungeon.effectsQueue.add(new SlimeDripsEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY,0));
        /*    */
        AbstractDungeon.player.channelOrb(this.orbType);

        tickDuration();
        /* 55 */
        this.isDone = true;
        /*    */
    }
}


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\TheDisciple.jar!\chronomuncher\actions\SlimeSpawnAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */