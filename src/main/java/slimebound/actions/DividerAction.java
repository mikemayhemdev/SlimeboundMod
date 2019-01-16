/*    */ package slimebound.actions;
/*    */ 
/*    */

/*    */

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import slimebound.powers.SearingPower;
import slimebound.powers.SlimedPower;

/*    */
/*    */

/*    */
/*    */ public class DividerAction extends AbstractGameAction
/*    */ {
/*    */   private DamageInfo info;
/*    */   private static final float DURATION = 0.01F;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.2F;
/*    */   private int numTimes;
/*    */
/*    */   public DividerAction(AbstractCreature target, DamageInfo info, int numTimes)
/*    */   {
/* 17 */     this.info = info;
/* 18 */     this.target = target;
/* 19 */     this.actionType = ActionType.DAMAGE;
/* 20 */     this.attackEffect = AttackEffect.FIRE;
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
/* 39 */       this.target.damageFlash = true;
/* 40 */       this.target.damageFlashFrames = 4;
/* 41 */       AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.combat.GhostIgniteEffect(this.target.hb.cX, this.target.hb.cY));
/* 42 */      if (MathUtils.randomBoolean()) {
            /* 146 */           AbstractDungeon.actionManager.addToBottom(new SFXAction("GHOST_ORB_IGNITE_1", 0.3F));
            /*     */         } else {
            /* 148 */           AbstractDungeon.actionManager.addToBottom(new SFXAction("GHOST_ORB_IGNITE_2", 0.3F));
            /*     */         }
                this.info.applyPowers(this.info.owner, this.target);
/* 43 */       this.target.damage(this.info);
/*    */       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.info.owner, new SearingPower(this.target, this.info.owner, 1), 1, true, AttackEffect.NONE));
/*    */ 
/* 46 */       if ((this.numTimes > 1) && (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
/* 47 */         this.numTimes -= 1;
/* 48 */         AbstractDungeon.actionManager.addToTop(new DividerAction(
/* 49 */           AbstractDungeon.getMonsters().getRandomMonster(true), this.info, this.numTimes));
/*    */       }
/*    */       
/* 52 */       AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.1F));
/*    */     }
/*    */     
/* 55 */     this.isDone = true;
/*    */   }
/*    */ }

