/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.StunnedPower;
import slimebound.powers.TackleBuffPower;
/*    */

/*    */
/*    */ public class SlimeHyperBeam extends CustomCard
/*    */ {
    /*    */   public static final String ID = "SlimeHyperBeam";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/hyper_beam.png";
    /* 19 */   private static final CardType TYPE = CardType.ATTACK;
    /* 20 */   private static final CardRarity RARITY = CardRarity.SPECIAL;
    /* 21 */   private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final CardStrings cardStrings;
    /*    */   private static final int COST = 2;
    /*    */   private static final int POWER = 6;
    /*    */   private static final int UPGRADE_BONUS = 3;

    /*    */
    public SlimeHyperBeam()
    /*    */ {
        /* 29 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);
        /*    */
        /* 31 */
        this.baseDamage = 24;
        this.magicNumber = this.baseMagicNumber = 1;
        /* 36 */     this.isMultiDamage = true;
        this.exhaust = true;
        this.isEthereal = true;
        /* 33 */
        /*    */
    }

    /*    */
    /*    */
    public void use(AbstractPlayer p, AbstractMonster m)
    /*    */ {
        /* 38 */

        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new com.megacrit.cardcrawl.vfx.combat.MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

      //  AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StunnedPower(p, p, this.magicNumber), this.magicNumber));

    }
        /*    */




    /*    */
    /*    */
    /*    */
    public AbstractCard makeCopy()
    /*    */ {
        /* 44 */
        return new SlimeHyperBeam();
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
            upgradeDamage(10);
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