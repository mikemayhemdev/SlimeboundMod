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
/*     */ public class MakeEchoAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
/*     */ {
/*     */   private static final float DURATION_PER_CARD = 0.35F;
/*     */   private AbstractCard c;
/*  16 */   private static final float PADDING = 25.0F * Settings.scale;
/*     */   
/*     */   public MakeEchoAction(AbstractCard card)
/*     */   {
/*  20 */     this(card, 1);
/*     */   }
/*     */   
/*     */   public MakeEchoAction(AbstractCard card, int amount)
/*     */   {
/*  25 */     com.megacrit.cardcrawl.unlock.UnlockTracker.markCardAsSeen(card.cardID);
/*  26 */     this.amount = amount;
/*  27 */     this.actionType = ActionType.CARD_MANIPULATION;
/*  28 */     this.duration = 0.35F;
/*  29 */     this.c = card;
/*     */   }
/*     */   
/*     */   private AbstractCard echoCard() {
/*  33 */     AbstractCard card = this.c.makeStatEquivalentCopy();
/*  34 */     card.name = ("Echo: " + card.name);
/*  35 */     card.exhaust = true;
/*  36 */     card.isEthereal = true;
/*  37 */     return card;
/*     */   }
/*     */   
/*     */   public void update()
/*     */   {
/*  42 */     if (this.amount == 0)
/*     */     {
/*  44 */       this.isDone = true;
/*  45 */       return;
/*     */     }
/*  47 */     int discardAmount = 0;
/*  48 */     int handAmount = this.amount;
/*  49 */     if (this.amount + AbstractDungeon.player.hand.size() > 10)
/*     */     {
/*  51 */       AbstractDungeon.player.createHandIsFullDialog();
/*  52 */       discardAmount = this.amount + AbstractDungeon.player.hand.size() - 10;
/*  53 */       handAmount -= discardAmount;
/*     */     }
/*  55 */     addToHand(handAmount);
/*  56 */     addToDiscard(discardAmount);
/*  57 */     if (this.amount > 0) {
/*  58 */       AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.utility.WaitAction(0.8F));
/*     */     }
/*  60 */     this.isDone = true;
/*     */   }
/*     */   
/*     */   private void addToHand(int handAmt)
/*     */   {
/*  65 */     switch (this.amount)
/*     */     {
/*     */     case 0: 
/*     */       break;
/*     */     case 1: 
/*  70 */       if (handAmt == 1) {
/*  71 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(echoCard()));
/*     */       }
/*     */       break;
/*     */     case 2: 
/*  75 */       if (handAmt == 1)
/*     */       {
/*  77 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(echoCard(), Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH * 0.5F), Settings.HEIGHT / 2.0F));
/*     */ 
/*     */       }
/*  80 */       else if (handAmt == 2)
/*     */       {
/*  82 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(echoCard(), Settings.WIDTH / 2.0F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */         
/*     */ 
/*  85 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(echoCard(), Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */       }
/*     */       
/*     */       break;
/*     */     case 3: 
/*  90 */       if (handAmt == 1)
/*     */       {
/*  92 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(echoCard(), Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */ 
/*     */       }
/*  95 */       else if (handAmt == 2)
/*     */       {
/*  97 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(echoCard(), Settings.WIDTH / 2.0F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */         
/*     */ 
/* 100 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(echoCard(), Settings.WIDTH / 2.0F - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */ 
/*     */       }
/* 103 */       else if (handAmt == 3)
/*     */       {
/* 105 */         for (int i = 0; i < this.amount; i++) {
/* 106 */           AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(echoCard()));
/*     */         }
/*     */       }
/*     */       break;
/*     */     default: 
/* 111 */       for (int i = 0; i < handAmt; i++) {
/* 112 */         AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(echoCard(), 
/* 113 */           MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), 
/* 114 */           MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void addToDiscard(int discardAmt)
/*     */   {
/* 121 */     switch (this.amount)
/*     */     {
/*     */     case 0: 
/*     */       break;
/*     */     case 1: 
/* 126 */       if (discardAmt == 1) {
/* 127 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH / 2.0F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT / 2.0F));
/*     */       }
/*     */       
/*     */       break;
/*     */     case 2: 
/* 132 */       if (discardAmt == 1)
/*     */       {
/* 134 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH * 0.5F), Settings.HEIGHT * 0.5F));
/*     */ 
/*     */       }
/* 137 */       else if (discardAmt == 2)
/*     */       {
/* 139 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH * 0.5F), Settings.HEIGHT * 0.5F));
/*     */         
/*     */ 
/* 142 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH * 0.5F), Settings.HEIGHT * 0.5F));
/*     */       }
/*     */       
/*     */       break;
/*     */     case 3: 
/* 147 */       if (discardAmt == 1)
/*     */       {
/* 149 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT * 0.5F));
/*     */ 
/*     */       }
/* 152 */       else if (discardAmt == 2)
/*     */       {
/* 154 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */         
/*     */ 
/* 157 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT * 0.5F));
/*     */ 
/*     */       }
/* 160 */       else if (discardAmt == 3)
/*     */       {
/* 162 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */         
/*     */ 
/* 165 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT * 0.5F));
/*     */         
/*     */ 
/* 168 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH), Settings.HEIGHT * 0.5F));
/*     */       }
/*     */       
/*     */       break;
/*     */     default: 
/* 173 */       for (int i = 0; i < discardAmt; i++) {
/* 174 */         AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(echoCard(), 
/* 175 */           MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), 
/* 176 */           MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\actions\MakeEchoAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */