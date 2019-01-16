/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.SlimeboundMod;
import slimebound.actions.SlimeSpawnAction;
import slimebound.patches.AbstractCardEnum;

/*    */
/*    */ public class SlimingSlime extends CustomCard
        /*    */ {
    /*    */   public static final String ID = "SlimingSlime";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/splitsludging.png";

    /* 17 */   private static final CardType TYPE = CardType.SKILL;
    /* 18 */   private static final CardRarity RARITY = CardRarity.COMMON;
    /* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */                private static final CardStrings cardStrings;

    /*    */   private static final int COST = 1;
    /*    */   private static final int BLOCK = 5;
    /*    */   private static final int UPGRADE_BONUS = 3;
    /*    */
    /*    */   public SlimingSlime()
    /*    */   {
        /* 27 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


        this.exhaust=true;
        /*    */   }
    /*    */
    /*    */   public void use(AbstractPlayer p, AbstractMonster m)
    /*    */   {
                     AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SlimingSlime(),false,true));
        if (this.upgraded) { AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SlimingSlime(),false,true));}
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
        /* 40 */     return new SlimingSlime();
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

