/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import slimebound.SlimeboundMod;
import slimebound.actions.SlimeSpawnAction;
import slimebound.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
/*    */
/*    */ public class BronzeSlime extends CustomCard
        /*    */ {
    /*    */   public static final String ID = "BronzeSlime";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/splitbronze.png";

    /* 17 */   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    /* 18 */   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
    /* 19 */   private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    /*    */                private static final CardStrings cardStrings;

    /*    */   private static final int COST = 0;
    /*    */   private static final int BLOCK = 5;
    /*    */   private static final int UPGRADE_BONUS = 3;
    /*    */
    /*    */   public BronzeSlime()
    /*    */   {
        /* 27 */     super(ID, NAME, slimebound.SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);

        /*    */
        /* 29 */     this.exhaust=true;

        /*    */   }
    /*    */
    /*    */   public void use(AbstractPlayer p, AbstractMonster m)
    /*    */   {
                     AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.BronzeSlime(),false,true));
        if (this.upgraded) { AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.BronzeSlime(),false,true));}
        /* 35 */     }
    /*    */
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
    /*    */   public AbstractCard makeCopy()
    /*    */   {
        /* 40 */     return new BronzeSlime();
        /*    */   }
    /*    */
    /*    */   public void upgrade()
    /*    */   {
        /* 45 */     if (!this.upgraded)
            /*    */     {
            /* 47 */       upgradeName();
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();


            /*    */     }
        /*    */   }
    /*    */ }

