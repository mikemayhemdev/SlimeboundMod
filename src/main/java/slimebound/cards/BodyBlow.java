/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HbBlockBrokenEffect;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
/*    */

/*    */
/*    */ public class BodyBlow extends CustomCard
/*    */ {
    /*    */   public static final String ID = "BodyBlow";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADE_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/bodyblow.png";
    /* 19 */   private static final CardType TYPE = CardType.ATTACK;
    /* 20 */   private static final CardRarity RARITY = CardRarity.UNCOMMON;
    /* 21 */   private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final CardStrings cardStrings;
    /*    */   private static final int COST = 1;
    /*    */   private static final int UPGRADE_BONUS = 3;

    /*    */
    public BodyBlow()
    /*    */ {
        /* 29 */
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
        /*    */
        /* 31 */
        this.baseDamage = 9;

        /* 33 */
        /*    */
    }

    /*    */
    /*    */
    public void use(AbstractPlayer p, AbstractMonster m)
    /*    */   {


                    AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));
        /* 47 */     if ((p != null) && (m != null)) {
            /* 48 */       AbstractDungeon.actionManager.addToBottom(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
            /*    */     }
        /* 42 */     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        /*    */
                    AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction(p, p));
                    AbstractDungeon.effectsQueue.add(new HbBlockBrokenEffect(p.hb.cX, p.hb.cY));

        /*    */   }
    /*    */


    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp)
        /*    */   {


        /* 54 */     int bonus = 0;

        if (upgraded) {
            bonus = player.currentBlock * 2;
        } else
        {
            bonus = player.currentBlock;
        }

        if (bonus > 0) {
            /* 56 */       this.isDamageModified = true;
            /*    */     }
        /* 58 */     return tmp + bonus;
        /*    */   }

    /*    */



    /*    */
    /*    */
    /*    */
    public AbstractCard makeCopy()
    /*    */ {
        /* 44 */
        return new BodyBlow();
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
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
            /*    */
        }
        /*    */
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        /*    */
    }
}


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\cards\Strike_Slimebound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */