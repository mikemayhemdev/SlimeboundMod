/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
/*    */

/*    */
/*    */ public class AllTogetherNow extends CustomCard
/*    */ {
    /*    */   public static final String ID = "AllTogetherNow";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/alltogether.png";
    /* 19 */   private static final CardType TYPE = CardType.SKILL;
    /* 20 */   private static final CardRarity RARITY = CardRarity.UNCOMMON;
    /* 21 */   private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final CardStrings cardStrings;
    /*    */   private static final int COST = 3;
    /*    */   private static final int POWER = 6;
    /*    */   private static final int UPGRADE_BONUS = 3;

    /*    */
    public AllTogetherNow()
    /*    */ {
        /* 29 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        /*    */
        /* 31 */
        this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 2;
        /* 33 */
        this.exhaust=true;
        /*    */
    }

    /*    */
    /*    */
    public void use(AbstractPlayer p, AbstractMonster m)
    /*    */ {
        /* 38 */
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRng), true));
        {

            for (AbstractOrb o : p.orbs) {
                if (o.ID == "TorchHeadSlime" ||
                        o.ID == "AttackSlime" ||
                        o.ID == "PoisonSlime" ||
                        o.ID == "SlimingSlime" ||
                        o.ID == "BronzeSlime" ||
                        o.ID == "DebuffSlime" ||
                        o.ID == "CultistSlime" ||
                        o.ID == "Hex") {
                    AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.PlayTopCardAction(
                            /*    */
                            /* 36 */       AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRng), true));
                    /*    */
                }
            }
        }
    }



    /*    */
    /*    */
    /*    */
    public AbstractCard makeCopy()
    /*    */ {
        /* 44 */
        return new AllTogetherNow();
        /*    */
    }

    /*    */
    /*    */
    public void upgrade()
    /*    */ {
        /* 49 */
        if (!this.upgraded)
            /*    */ {
            /* 51 */
            upgradeName();
            /* 52 */
            upgradeBaseCost(2);
            /*    */
        }
        /*    */
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        /*    */
    }
}


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\cards\Strike_Slimebound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */