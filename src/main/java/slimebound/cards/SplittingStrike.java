/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.SlimeboundMod;
import slimebound.actions.RandomBasicSlimeCardAction;
import slimebound.actions.SlimeSpawnAction;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.TackleBuffPower;

import java.util.Random;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.THORNS;
/*    */

/*    */
/*    */ public class SplittingStrike extends CustomCard
/*    */ {
    /*    */   public static final String ID = "SplittingStrike";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/splittingstrike.png";
    /* 19 */   private static final CardType TYPE = CardType.ATTACK;
    /* 20 */   private static final CardRarity RARITY = CardRarity.COMMON;
    /* 21 */   private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings;
    /*    */   private static final int COST = 2;
    /*    */   private static int baseSelfDamage;
    /*    */   public static int originalDamage;
    /*    */   public static int originalBlock;
    /*    */   public static int upgradeDamage;
    /*    */   public static int upgradeSelfDamage;
    /*    */   private static final int POWER = 6;
    /*    */   private static final int UPGRADE_BONUS = 3;

    /*    */
    public SplittingStrike()
    /*    */ {
        /* 29 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        /*    */
        /* 31 */
        this.baseDamage = originalDamage = 12;
        upgradeDamage = 3;
        this.magicNumber = this.baseMagicNumber = 1;

        /* 33 */
        this.exhaust=true;
        /*    */
    }

    /*    */
    /*    */
    public void use(AbstractPlayer p, AbstractMonster m)
    /*    */ {
        /* 38 */
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction(AbstractDungeon.player));



        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));


        Random random = new Random();
        Integer chosenRand = random.nextInt(4);


        if (chosenRand == 0) {
            AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.AttackSlime(),false,true));
        } else if (chosenRand == 1) {
            AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.DebuffSlime(),false,true));
        } else if (chosenRand == 2) {
            AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.PoisonSlime(),false,true));
        } else {
            AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SlimingSlime(), false, true));
        }

        if (upgraded) {
            chosenRand = random.nextInt(4);


            if (chosenRand == 0) {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.AttackSlime(), false, true));
            } else if (chosenRand == 1) {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.DebuffSlime(), false, true));
            } else if (chosenRand == 2) {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.PoisonSlime(), false, true));
            } else {
                AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimebound.orbs.SlimingSlime(), false, true));


            }
        }
        /*    */
    }



    /*    */
    /*    */
    /*    */
    public AbstractCard makeCopy()
    /*    */ {
        /* 44 */
        return new SplittingStrike();
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
            upgradeDamage(upgradeDamage);
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