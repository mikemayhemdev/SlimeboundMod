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
/*    */ public class SlimeTap extends CustomCard
        /*    */ {
    /*    */   public static final String ID = "SlimeTap";
    /*    */   public static final String NAME;
    /*    */   private static final CardStrings cardStrings;
    /*    */   public static final String DESCRIPTION;
    /* 18 */   public static final String[] EXTENDED_DESCRIPTION;
                public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/slimetap.png";

    /* 17 */   private static final CardType TYPE = CardType.SKILL;
    /* 18 */   private static final CardRarity RARITY = CardRarity.UNCOMMON;
    /* 19 */   private static final CardTarget TARGET = CardTarget.SELF;

    /*    */   private static final int COST = 0;
                private int numEaten = 0;
    /*    */   private static final int BLOCK = 5;
    /*    */   private static final int UPGRADE_BONUS = 3;

    /*    */
    /*    */
    public SlimeTap()
    /*    */ {
        /* 27 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

        /*    */
        /* 29 */
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 1;

        /*    */
    }

    /*    */
    /*    */
    public boolean canUse(AbstractPlayer p, AbstractMonster m)
    /*    */   {
        /* 50 */     boolean canUse = super.canUse(p, m);
        /* 51 */
        /* 54 */     for (AbstractOrb o : p.orbs) {
            /* 55 */       if (o.ID == "TorchHeadSlime" ||
                    o.ID == "AttackSlime" ||
                    o.ID == "PoisonSlime" ||
                    o.ID == "SlimingSlime" ||
                    o.ID == "BronzeSlime" ||
                    o.ID == "DebuffSlime" ||
                    o.ID == "CultistSlime" ||
                    o.ID == "HexSlime") {
                /* 56 */         return canUse=true;}
            /*    */     }
        /*    */    this.cantUseMessage = EXTENDED_DESCRIPTION[0];
        /* 61 */     return canUse=false;
        /*    */   }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (!AbstractDungeon.player.orbs.isEmpty()) {
            for (AbstractOrb o : AbstractDungeon.player.orbs) {

                if (o.ID == "TorchHeadSlime" ||
                        o.ID == "AttackSlime" ||
                        o.ID == "PoisonSlime" ||
                        o.ID == "SlimingSlime" ||
                        o.ID == "BronzeSlime" ||
                        o.ID == "DebuffSlime" ||
                        o.ID == "CultistSlime" ||
                        o.ID == "HexSlime") { // when equipped (picked up) this relic counts how many ethereal cards are in the player's deck



                    numEaten = numEaten +1;
                    com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.defect.EvokeOrbAction(1));
                    AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DrawCardAction(AbstractDungeon.player,1));
                    AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainEnergyAction(1));
                    if (!upgraded){return;} else
                        if (numEaten == 2){return;}



                }
            }
        }
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
        /* 40 */     return new SlimeTap();
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

