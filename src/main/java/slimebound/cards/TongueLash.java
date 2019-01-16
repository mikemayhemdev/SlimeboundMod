/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
/*    */

/*    */
/*    */ public class TongueLash extends CustomCard
/*    */ {
    /*    */   public static final String ID = "TongueLash";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/tonguelash.png";
    /* 19 */   private static final CardType TYPE = CardType.ATTACK;
    /* 20 */   private static final CardRarity RARITY = CardRarity.UNCOMMON;
    /* 21 */   private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings;
    /*    */   private static final int COST = 1;
    /*    */   private static final int POWER = 6;
    /*    */   private static final int UPGRADE_BONUS = 3;

    /*    */
    public TongueLash()
    /*    */ {
        /* 29 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        /*    */
        /* 31 */
        this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 3;
        /* 33 */
        /*    */
    }


    /*    */   public static int countCards() {
        /* 43 */     int count = 0;
        /* 49 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            /* 50 */       if (c.cardID.contains("Lick")) {
                /* 51 */         count++;
                /*    */       }
            /*    */     }
        /* 54 */     for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            /* 55 */       if (c.cardID.contains("Lick")) {
                /* 56 */         count++;
                /*    */       }
            /*    */     }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            /* 55 */       if (c.cardID.contains("Lick")) {
                /* 56 */         count++;
                /*    */       }
            /*    */     }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            /* 55 */       if (c.cardID.contains("Lick")) {
                /* 56 */         count++;
                /*    */       }
            /*    */     }
        /* 59 */     return count;
        /*    */   }
    /*    */
    /*    */
    public void use(AbstractPlayer p, AbstractMonster m)
    /*    */ {
        /* 38 */
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        /*    */
    }

    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp)
        /*    */   {
        int bonus = 0;
        int cards = countCards();
           bonus = cards * this.magicNumber;
        /* 58 */     return tmp + bonus;
        /*    */   }


    /*    */
    /*    */
    /*    */
    public AbstractCard makeCopy()
    /*    */ {
        /* 44 */
        return new TongueLash();
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
            upgradeDamage(1);
            upgradeMagicNumber(1);
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