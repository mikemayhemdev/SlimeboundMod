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
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.SlimeSacrificePower;

/*    */
/*    */ public class UsefulSlime extends CustomCard
        /*    */ {
    /*    */   public static final String ID = "UsefulSlime";
    /*    */   public static final String NAME;
    /*    */   private static final CardStrings cardStrings;
    /*    */   public static final String DESCRIPTION;
    /* 18 */   public static final String[] EXTENDED_DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/usefulslime.png";

    /* 17 */   private static final CardType TYPE = CardType.SKILL;
    /* 18 */   private static final CardRarity RARITY = CardRarity.SPECIAL;
    /* 19 */   private static final CardTarget TARGET = CardTarget.SELF;

    /*    */   private static final int COST = 1;

    /*    */
    /*    */
    public UsefulSlime()
    /*    */ {
        /* 27 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);

        /*    */
        /* 29 */
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 1;

        /*    */
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainEnergyAction(2));

    }


    /* 35 */

    public void triggerWhenDrawn()
        /*    */   {
        /* 46 */     if ((AbstractDungeon.player.hasPower("Evolve")) && (!AbstractDungeon.player.hasPower("No Draw")))
            /*    */     {
            /* 48 */       AbstractDungeon.player.getPower("Evolve").flash();
            /* 49 */       AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DrawCardAction(AbstractDungeon.player,
                    /*    */
                    /*    */
                    /* 52 */         AbstractDungeon.player.getPower("Evolve").amount));
            /*    */     }
        /*    */   }

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
        /* 40 */     return new UsefulSlime();
        /*    */   }
    /*    */
    /*    */   public void upgrade() {}
    /*    */ }

