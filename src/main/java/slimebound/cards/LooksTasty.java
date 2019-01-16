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
import slimebound.actions.RandomLickCardAction;
import slimebound.patches.AbstractCardEnum;
/*    */

/*    */
/*    */ public class LooksTasty extends CustomCard
/*    */ {
    /*    */   public static final String ID = "LooksTasty";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/itlookstasty.png";
    /* 19 */   private static final CardType TYPE = CardType.ATTACK;
    /* 20 */   private static final CardRarity RARITY = CardRarity.COMMON;
    /* 21 */   private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings;
    /*    */   private static final int COST = 1;
    /*    */   private static final int POWER = 6;
    /*    */   private static final int UPGRADE_BONUS = 3;

    /*    */
    public LooksTasty()
    /*    */ {
        /* 29 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        /*    */
        /* 31 */
        this.baseDamage = 8;
        /* 33 */
        /*    */
    }

    /*    */
    /*    */
    public void use(AbstractPlayer p, AbstractMonster m)
    /*    */ {
        /* 38 */

        if (m.hasPower("SlimedPower")) {
            AbstractDungeon.actionManager.addToBottom(new RandomLickCardAction(false));
            if(upgraded){AbstractDungeon.actionManager.addToBottom(new RandomLickCardAction(false));}
 }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        /*    */
    }



    /*    */
    /*    */
    /*    */
    public AbstractCard makeCopy()
    /*    */ {
        /* 44 */
        return new LooksTasty();
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
            upgradeDamage(2);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
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