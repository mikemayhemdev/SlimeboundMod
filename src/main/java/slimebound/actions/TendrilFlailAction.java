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

/*    */   private static final float DURATION = 0.01F;
private AbstractCreature owner;
/*    */   private static final float POST_ATTACK_WAIT_DUR = 0.2F;
/*    */   private int numTimes;
private int slimed;
/*    */   
/*    */   public TendrilFlailAction(AbstractCreature player, AbstractCreature target, int numTimes, int slimed)
/*    */   {

        this.owner = player;
/* 18 */     this.target = target;
/* 19 */     this.actionType = ActionType.POWER;
/* 20 */     this.attackEffect = AttackEffect.POISON;
/* 21 */     this.duration = 0.01F;
this.slimed=slimed;
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
/*    */       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.owner, new SlimedPower(this.target, this.owner, slimed), slimed, true, AttackEffect.POISON));
/*    */ 
/* 46 */       if ((this.numTimes > 1) && (!AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
/* 47 */         this.numTimes -= 1;
/* 48 */         AbstractDungeon.actionManager.addToTop(new TendrilFlailAction(this.owner,
/* 49 */           AbstractDungeon.getMonsters().getRandomMonster(true), this.numTimes,this.slimed));
/*    */       }
/*    */       
/* 52 */       AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.2F));
/*    */     }
/*    */     
/* 55 */     this.isDone = true;
/*    */   }
/*    */ }

