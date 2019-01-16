/*    */ package slimebound.actions;
/*    */ 
/*    */

/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import slimebound.powers.SlimedPower;

/*    */
/*    */ public class TendrilFlailAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.01F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.2F;
/*    */   private int numTimes;
/*    */   
/*    */   public TendrilFlailAction(AbstractCreature target, DamageInfo info, int numTimes)
/*    */   {
/* 17 */     this.info = info;
/* 18 */     this.target = target;
/* 19 */     this.actionType = ActionType.POWER;
/* 20 */     this.attackEffect = AttackEffect.POISON;
/* 21 */     this.duration = 0.01F;
/* 22 */     this.numTimes = numTimes;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 27 */     if (this.target == null) {
/* 28 */       this.isDone = true;
/* 29 */       return;
/*    */     }
/*    */     
/* 32 */     if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
/* 33 */       AbstractDungeon.actionManager.clearPostCombatActions();
/* 34 */       this.isDone = true;
/* 35 */       return;
/*    */     }
/*    */     
/* 38 */     if (this.target.currentHealth > 0) {
/* 39 */      //this.info.applyPowers(this.info.owner, this.target);
/* 43 */      // this.target.damage(this.info);
/*    */       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.info.owner, new SlimedPower(this.target, this.info.owner, 2), 2, true, AttackEffect.POISON));
/*    */ 
/* 46 */       if ((this.numTimes > 1) && (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
/* 47 */         this.numTimes -= 1;
/* 48 */         AbstractDungeon.actionManager.addToTop(new TendrilFlailAction(
/* 49 */           AbstractDungeon.getMonsters().getRandomMonster(true), this.info, this.numTimes));
/*    */       }
/*    */       
/* 52 */       AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.2F));
/*    */     }
/*    */     
/* 55 */     this.isDone = true;
/*    */   }
/*    */ }

