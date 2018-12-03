/*     */ package slimebound.actions;
/*     */ 
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class ExhumeToDrawAction extends AbstractGameAction
/*     */ {
/*     */   private AbstractPlayer p;
/*     */   private final boolean upgrade;
/*  21 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhumeAction");
/*  22 */   public static final String[] TEXT = uiStrings.TEXT;
/*  23 */   private ArrayList<AbstractCard> exhumes = new ArrayList();
/*     */   
/*     */   public ExhumeToDrawAction(boolean upgrade) {
/*  26 */     this.upgrade = upgrade;
/*  27 */     this.p = AbstractDungeon.player;
/*  28 */     setValues(this.p, AbstractDungeon.player, this.amount);
/*  29 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/*  30 */     this.duration = Settings.ACTION_DUR_FAST;
/*     */   }
/*     */   
/*     */   public void update() {
/*     */     Iterator<AbstractCard> card;
/*  35 */     if (this.duration == Settings.ACTION_DUR_FAST)
/*     */     {
/*  37 */       if (AbstractDungeon.player.hand.size() == 10) {
/*  38 */         AbstractDungeon.player.createHandIsFullDialog();
/*  39 */         this.isDone = true;
/*  40 */         return;
/*     */       }
/*     */       
/*     */ 
/*  44 */       if (this.p.exhaustPile.isEmpty()) {
/*  45 */         this.isDone = true; return;
/*     */       }
/*     */       AbstractCard c;
/*  48 */       if (this.p.exhaustPile.size() == 1)
/*     */       {
/*     */ 
/*  51 */         if (((AbstractCard)this.p.exhaustPile.group.get(0)).cardID.equals("Recollect")) {
/*  52 */           this.isDone = true;
/*  53 */           return;
/*     */         }
/*     */         
/*  56 */         c = this.p.exhaustPile.getTopCard();
/*  57 */         c.unfadeOut();
/*  58 */         this.p.hand.addToHand(c);
/*  59 */         if ((AbstractDungeon.player.hasPower("Corruption")) && (c.type == AbstractCard.CardType.SKILL))
/*     */         {
/*  61 */           c.setCostForTurn(-9);
/*     */         }
/*  63 */         this.p.exhaustPile.removeCard(c);
/*  64 */         if ((this.upgrade) && (c.canUpgrade())) {
/*  65 */           c.upgrade();
/*     */         }
/*  67 */         c.unhover();
/*  68 */         c.fadingOut = false;
/*  69 */         this.isDone = true;
/*  70 */         return;
/*     */       }
/*     */       
/*     */ 
/*  74 */       for (AbstractCard c2 : this.p.exhaustPile.group) {
/*  75 */         c2.stopGlowing();
/*  76 */         c2.unhover();
/*  77 */         c2.unfadeOut();
/*     */       }
/*     */       
/*     */ 

/*     */       
/*  89 */       if (this.p.exhaustPile.isEmpty()) {
/*  90 */         this.p.exhaustPile.group.addAll(this.exhumes);
/*  91 */         this.exhumes.clear();
/*  92 */         this.isDone = true;
/*  93 */         return;
/*     */       }
/*     */       
/*  96 */       AbstractDungeon.gridSelectScreen.open(this.p.exhaustPile, 1, TEXT[0], false);
/*  97 */       tickDuration();
/*  98 */       return;
/*     */     }
/*     */     
/*     */ 
/* 102 */     if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 103 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 104 */         this.p.drawPile.addToRandomSpot(c);
/* 105 */         if ((AbstractDungeon.player.hasPower("Corruption")) && (c.type == AbstractCard.CardType.SKILL)) {
/* 106 */           c.setCostForTurn(-9);
/*     */         }
/* 108 */         this.p.exhaustPile.removeCard(c);
/* 109 */         if ((this.upgrade) && (c.canUpgrade())) {
/* 110 */           c.upgrade();
/*     */         }
/* 112 */         c.unhover();
/*     */       }
/* 114 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 115 */       this.p.hand.refreshHandLayout();
/*     */       
/*     */ 
/* 118 */       this.p.exhaustPile.group.addAll(this.exhumes);
/* 119 */       this.exhumes.clear();
/*     */       
/* 121 */       for (AbstractCard c : this.p.exhaustPile.group) {
/* 122 */         c.unhover();
/* 123 */         c.target_x = CardGroup.DISCARD_PILE_X;
/* 124 */         c.target_y = 0.0F;
/*     */       }
/*     */     }
/*     */     
/* 128 */     tickDuration();
/*     */   }
/*     */ }


