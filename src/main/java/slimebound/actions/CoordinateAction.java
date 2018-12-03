/*    */ package slimebound.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class CoordinateAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*    */ {
/* 15 */   private boolean freeToPlayOnce = false;
/*    */   private int damage;
/*    */   private AbstractPlayer p;
/*    */   private AbstractMonster m;
/*    */   private DamageInfo.DamageType damageTypeForTurn;
/* 20 */   private int energyOnUse = -1;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public CoordinateAction(AbstractPlayer p, AbstractMonster m, int damage, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse)
/*    */   {
/* 29 */     this.p = p;
/* 30 */     this.m = m;
/* 31 */     this.damage = damage;
/* 32 */     this.freeToPlayOnce = freeToPlayOnce;
/* 33 */     this.duration = com.megacrit.cardcrawl.core.Settings.ACTION_DUR_XFAST;
/* 34 */     this.actionType = ActionType.DAMAGE;
/* 35 */     this.damageTypeForTurn = damageTypeForTurn;
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
/* 53 */         AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.DamageAction(this.m, new DamageInfo(this.p, this.damage, this.damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */
                }

        AbstractOrb oldestOrb = null;
        for (AbstractOrb o : p.orbs) {
            if (o.ID == "TorchHeadSlime" ||
                    o.ID == "AttackSlime" ||
                    o.ID == "PoisonSlime" ||
                    o.ID == "SlimingSlime" ||
                    o.ID == "BronzeSlime" ||
                    o.ID == "DebuffSlime" ||
                    o.ID == "CultistSlime" ||
                    o.ID == "HexSlime") {
                oldestOrb = o;
                break;
            }

        }
        if (oldestOrb != null){
                for (int i = 0; i < effect; i++) {
                    com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new TrigggerSpecificSlimeAttackAction(oldestOrb));
                    //com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new WaitAction(0.2F));

                }
        com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new WaitAction(0.4F * effect));

        //KNOWN ISSUE - this will actually evoke a defect Orb if one is gained somehow.  Not sure how to evoke a specific orb.
        com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.defect.EvokeOrbAction(1));
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
