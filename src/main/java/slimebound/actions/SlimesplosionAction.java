/*    */ package slimebound.actions;
/*    */ 
/*    */

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import slimebound.powers.SlimedPower;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class SlimesplosionAction extends AbstractGameAction
/*    */ {
/* 15 */   private boolean freeToPlayOnce = false;
/*    */   private int damage;
/*    */   private AbstractPlayer p;
/*    */   private AbstractMonster m;
/*    */   private DamageType damageTypeForTurn;
/* 20 */   private int energyOnUse = -1;
    /* 20 */   private int slimed =0;

    /* 20 */   private int poison = 0;
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */   public SlimesplosionAction(AbstractPlayer p,  int slimed, int poison, boolean freeToPlayOnce, int energyOnUse)
/*    */   {
/* 29 */     this.p = p;
/* 30 */     this.m = m;
/* 31 */     //this.damage = damage;
/* 32 */     this.freeToPlayOnce = freeToPlayOnce;
/* 33 */     this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
/* 34 */     this.actionType = ActionType.DAMAGE;
/* 35 */    // this.damageTypeForTurn = damageTypeForTurn;
            this.slimed =slimed;
            this.poison=poison;
/* 36 */     this.energyOnUse = energyOnUse;
/*    */   }
/*    */
/*    */   public void update()
/*    */   {
/* 41 */     int effect = EnergyPanel.totalCount;
/* 42 */     if (this.energyOnUse != -1) {
/* 43 */       effect = this.energyOnUse;
/*    */     }
/*    */
/* 46 */     if (this.p.hasRelic("Chemical X")) {
/* 47 */       effect += 2;
/* 48 */       this.p.getRelic("Chemical X").flash();
/*    */     }
/*    */
/* 51 */     if (effect > 0) {
/* 52 */       for (int i = 0; i < effect; i++) {
/* 53 */          for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {

                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new SlimedPower(monster,p, this.slimed), this.slimed, true, AbstractGameAction.AttackEffect.NONE));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new PoisonPower(monster,p, this.poison), this.poison, true, AbstractGameAction.AttackEffect.NONE));

                    /*    */
                }
                }
  }/*    */
        /*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 60 */       if (!this.freeToPlayOnce) {
/* 61 */         this.p.energy.use(EnergyPanel.totalCount);
/*    */       }
/*    */     }
/* 64 */     this.isDone = true;
/*    */   }
/*    */ }
