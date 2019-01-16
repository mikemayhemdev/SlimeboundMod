/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import slimebound.SlimeboundMod;
import slimebound.actions.ExhumeToDrawAction;
import slimebound.patches.AbstractCardEnum;

/*    */
/*    */ public class Recollect extends CustomCard
        /*    */ {
    /*    */   public static final String ID = "Recollect";
    /*    */   public static final String NAME;
    /*    */   private static final CardStrings cardStrings;
    /*    */   public static final String DESCRIPTION;
    /* 18 */   public static final String[] EXTENDED_DESCRIPTION;
                public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/slimetap.png";

    /* 17 */   private static final CardType TYPE = CardType.SKILL;
    /* 18 */   private static final CardRarity RARITY = CardRarity.UNCOMMON;
    /* 19 */   private static final CardTarget TARGET = CardTarget.SELF;

    /*    */   private static final int COST = 1;
                private int numEaten = 0;
    /*    */   private static final int BLOCK = 5;
    /*    */   private static final int UPGRADE_BONUS = 3;

    /*    */
    /*    */
    public Recollect()
    /*    */ {
        /* 27 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

        /*    */
        /* 29 */
        //this.exhaust = true;
        this.baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 1;

        /*    */
    }

    /*    */
    /*    */

        /*    */

    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new ExhumeToDrawAction(false));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,this.block));

    }


            /* 35 */



    /*    */
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }
    /*    */   public AbstractCard makeCopy()
    /*    */   {
        /* 40 */     return new Recollect();
        /*    */   }
    /*    */
    /*    */   public void upgrade()
    /*    */   {
        /* 45 */     if (!this.upgraded)
            /*    */     {
            /* 47 */       upgradeName();
            upgradeBlock(3);


            /*    */     }
        /*    */   }
    /*    */ }

