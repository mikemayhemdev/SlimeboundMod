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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
/*    */

/*    */
/*    */ public class Retaliate extends CustomCard
/*    */ {
    /*    */   public static final String ID = "Retaliate";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
                 public static final String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/retaliate.png";
    /* 19 */   private static final CardType TYPE = CardType.ATTACK;
    /* 20 */   private static final CardRarity RARITY = CardRarity.UNCOMMON;
    /* 21 */   private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardStrings cardStrings;

    /*    */   private static final int COST = 2;
    /*    */   private static final int POWER = 6;
                public int missingHealth;
    /*    */   private static final int UPGRADE_BONUS = 3;

    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName()); // lets us log output
    /*    */
    public Retaliate()
    /*    */ {
        /* 29 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        /*    */
        /* 31 */
        /* 33 */
        /*    */
        this.baseDamage = 0;
    }

    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp)
        /*    */   {


        /* 54 */     int bonus = (player.maxHealth - player.currentHealth) / 2;


                     if (bonus > 0) {
            /* 56 */       this.isDamageModified = true;
            /*    */     }
        /* 58 */     return tmp + bonus;
        /*    */   }

    public void use(AbstractPlayer p, AbstractMonster m)
    /*    */ {
        /* 38 */
        logger.info("max health: "+ p.maxHealth +", current health: "+ p.currentHealth);

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

    }



    /*    */
    /*    */
    /*    */
    public AbstractCard makeCopy()
    /*    */ {
        /* 44 */
        return new Retaliate();
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
            upgradeBaseCost(1);
            /* 52 */
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