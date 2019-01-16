/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.PotencyPower;
import slimebound.powers.SelfFormingGooPower;

/*    */
/*    */ public class Spiked extends CustomCard
        /*    */ {
    /*    */   public static final String ID = "Spiked";
    /*    */
                private static final CardStrings cardStrings;
                public static final String NAME;
                public static final String DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/spiked.png";
    /* 17 */   private static final CardType TYPE = CardType.POWER;
    /* 18 */   private static final CardRarity RARITY = CardRarity.UNCOMMON;
    /* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */
    /*    */   private static final int COST = 2;

    /*    */   private static int upgradedamount = 1;
    /*    */
    /*    */   public Spiked()
    /*    */   {
        /* 27 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
                    this.magicNumber = this.baseMagicNumber = 3;


        /*    */   }
    /*    */
    /*    */   public void use(AbstractPlayer p, AbstractMonster m)
    /*    */   {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SelfFormingGooPower(p, p, this.magicNumber), this.magicNumber));
        /* 35 */     }
    /*    */
    /*    */   public AbstractCard makeCopy()
    /*    */   {
        /* 40 */     return new Spiked();
        /*    */   }
    /*    */
    /*    */   public void upgrade()
    /*    */   {
        /* 45 */     if (!this.upgraded)
            /*    */     {
            /* 47 */       upgradeName();
            this.upgradeMagicNumber(1);


            /*    */     }
        /*    */   }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }
    /*    */ }

