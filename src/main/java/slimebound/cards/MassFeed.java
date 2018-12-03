/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import slimebound.SlimeboundMod;
import slimebound.actions.MassFeedAction;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.SlimedPower;
/*    */

/*    */
/*    */ public class MassFeed extends CustomCard
/*    */ {
    /*    */   public static final String ID = "MassFeed";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/massfeed.png";
    /* 19 */   private static final CardType TYPE = CardType.ATTACK;
    /* 20 */   private static final CardRarity RARITY = CardRarity.RARE;
    /* 21 */   private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final CardStrings cardStrings;
    /*    */   private static final int COST = 2;
    /*    */   private static final int POWER = 6;
    /*    */   private static final int UPGRADE_BONUS = 3;

    /*    */
    public MassFeed()
    /*    */ {
        /* 29 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        /*    */
        /* 31 */
        this.baseDamage = 10;
        this.magicNumber = this.baseMagicNumber = 3;
        /* 33 */
        this.exhaust=true;
        /*    */
    }

    /*    */
    /*    */
    public void use(AbstractPlayer p, AbstractMonster m)
    /*    */ {
        /* 38 */



        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                flash();
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                 if ((!monster.isDead) && (!monster.isDying)) {

                     //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new SlimedPower(monster,p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                     AbstractDungeon.actionManager.addToBottom(new MassFeedAction(monster, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
                     /*    */
                     /*    */
                    }
                    /*    */
                /*    */       }
            /*    */     }


    }



    /*    */
    /*    */
    /*    */
    public AbstractCard makeCopy()
    /*    */ {
        /* 44 */
        return new MassFeed();
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
            /* 58 */       upgradeDamage(2);
            /* 59 */       upgradeMagicNumber(1);
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