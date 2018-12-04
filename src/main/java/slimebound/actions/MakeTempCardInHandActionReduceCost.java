/*     */ package slimebound.actions;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class MakeTempCardInHandActionReduceCost extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*     */ {
/*     */   private static final float DURATION_PER_CARD = 0.35F;
/*     */   private AbstractCard c;
/*  16 */   private static final float PADDING = 25.0F * Settings.scale;
/*  17 */   private boolean isOtherCardInCenter = true;
/*     */   
/*     */   public MakeTempCardInHandActionReduceCost(AbstractCard card, boolean isOtherCardInCenter) {
/*  20 */     com.megacrit.cardcrawl.unlock.UnlockTracker.markCardAsSeen(card.cardID);
/*  21 */     this.amount = 1;
/*  22 */     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
/*  23 */     this.duration = 0.35F;
/*  24 */     this.c = card;
                //c.modifyCostForCombat(-1);
   // card.modifyCostForCombat(-1);
/*  25 */     this.isOtherCardInCenter = isOtherCardInCenter;
/*     */   }
/*     */   
/*     */   public MakeTempCardInHandActionReduceCost(AbstractCard card) {
/*  29 */     this(card, 1);

    c.modifyCostForCombat(-1);
    //card.modifyCostForCombat(-1);
    /*  25 */
/*     */   }
/*     */   
/*     */   public MakeTempCardInHandActionReduceCost(AbstractCard card, int amount) {
/*  33 */     com.megacrit.cardcrawl.unlock.UnlockTracker.markCardAsSeen(card.cardID);
/*  34 */     this.amount = amount;
/*  35 */     this.actionType = com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType.CARD_MANIPULATION;
/*  36 */     this.duration = 0.35F;
/*  37 */     this.c = card;
    //c.modifyCostForCombat(-1);
    //card.modifyCostForCombat(-1);
/*     */   }
/*     */   
/*     */   public MakeTempCardInHandActionReduceCost(AbstractCard card, int amount, boolean isOtherCardInCenter) {
/*  41 */     this(card, amount);
/*     */   }
/*     */   
/*     */ 
/*     */   public void update()
/*     */   {
/*  47 */     if (this.amount == 0) {
/*  48 */       this.isDone = true;
/*  49 */       return;
/*     */     }
/*     */     
/*  52 */     int discardAmount = 0;
/*  53 */     int handAmount = this.amount;
/*     */     
/*     */ 
/*  56 */     if (this.amount + AbstractDungeon.player.hand.size() > 10) {
/*  57 */       AbstractDungeon.player.createHandIsFullDialog();
/*  58 */       discardAmount = this.amount + AbstractDungeon.player.hand.size() - 10;
/*  59 */       handAmount -= discardAmount;
/*     */     }
/*     */     
/*  62 */     addToHand(handAmount);
/*  63 */     addToDiscard(discardAmount);
/*     */     
/*  65 */     if (this.amount > 0) {
/*  66 */       AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.8F));
/*     */     }
/*     */     
/*  69 */     this.isDone = true;
/*     */   }
/*     */   
/*     */   private void addToHand(int handAmt) {
/*  73 */     switch (this.amount) {
/*     */     case 0: 
/*     */       break;
/*     */     case 1: 
/*  77 */       if (handAmt == 1) {
/*  78 */         if (this.isOtherCardInCenter) {
/*  79 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c
/*     */           
/*  81 */             .makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */         }
/*     */         else
/*     */         {
/*  85 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c.makeStatEquivalentCopy()));
/*     */         }
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  90 */       if (handAmt == 1) {
/*  91 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c
/*     */         
/*  93 */           .makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH * 0.5F), Settings.HEIGHT / 2.0F));
/*     */ 
/*     */       }
/*  96 */       else if (handAmt == 2) {
/*  97 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c
/*     */         
/*  99 */           .makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */         
/*     */ 
/* 102 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c
/*     */         
/* 104 */           .makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */       }
/*     */       
/*     */ 
/*     */       break;
/*     */     case 3: 
/* 110 */       if (handAmt == 1) {
/* 111 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c
/*     */         
/* 113 */           .makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */ 
/*     */       }
/* 116 */       else if (handAmt == 2) {
/* 117 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c
/*     */         
/* 119 */           .makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */         
/*     */ 
/* 122 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c
/*     */         
/* 124 */           .makeStatEquivalentCopy(), Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */ 
/*     */       }
/* 127 */       else if (handAmt == 3)
/*     */       {
/*     */ 
/* 130 */         for (int i = 0; i < this.amount; i++) {
/* 131 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c.makeStatEquivalentCopy()));
/*     */         }
/*     */       }
/*     */       break;
/*     */     default: 
/* 136 */       for (int i = 0; i < handAmt; i++) {
/* 137 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(this.c
/*     */         
/* 139 */           .makeStatEquivalentCopy(), 
/* 140 */           MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), 
/* 141 */           MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addToDiscard(int discardAmt)
/*     */   {
/* 148 */     switch (this.amount) {
/*     */     case 0: 
/*     */       break;
/*     */     case 1: 
/* 152 */       if (discardAmt == 1) {
/* 153 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 155 */           .makeStatEquivalentCopy(), Settings.WIDTH / 2.0F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */       }
/*     */       
/*     */ 
/*     */       break;
/*     */     case 2: 
/* 161 */       if (discardAmt == 1) {
/* 162 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 164 */           .makeStatEquivalentCopy(), Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH * 0.5F), Settings.HEIGHT * 0.5F));
/*     */ 
/*     */       }
/* 167 */       else if (discardAmt == 2) {
/* 168 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 170 */           .makeStatEquivalentCopy(), Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH * 0.5F), Settings.HEIGHT * 0.5F));
/*     */         
/*     */ 
/* 173 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 175 */           .makeStatEquivalentCopy(), Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH * 0.5F), Settings.HEIGHT * 0.5F));
/*     */       }
/*     */       
/*     */ 
/*     */       break;
/*     */     case 3: 
/* 181 */       if (discardAmt == 1) {
/* 182 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 184 */           .makeStatEquivalentCopy(), Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT * 0.5F));
/*     */ 
/*     */       }
/* 187 */       else if (discardAmt == 2) {
/* 188 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 190 */           .makeStatEquivalentCopy(), Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */         
/*     */ 
/* 193 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 195 */           .makeStatEquivalentCopy(), Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT * 0.5F));
/*     */ 
/*     */       }
/* 198 */       else if (discardAmt == 3) {
/* 199 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 201 */           .makeStatEquivalentCopy(), Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */         
/*     */ 
/* 204 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 206 */           .makeStatEquivalentCopy(), Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT * 0.5F));
/*     */         
/*     */ 
/* 209 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 211 */           .makeStatEquivalentCopy(), Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT * 0.5F));
/*     */       }
/*     */       
/*     */ 
/*     */       break;
/*     */     default: 
/* 217 */       for (int i = 0; i < discardAmt; i++) {
/* 218 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(this.c
/*     */         
/* 220 */           .makeStatEquivalentCopy(), 
/* 221 */           MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), 
/* 222 */           MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Computer\IdeaProjects\lib\desktop-1.0.jar!\com\megacrit\cardcrawl\actions\common\MakeTempCardInHandActionReduceCost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */