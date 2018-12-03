/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import slimebound.SlimeboundMod;

/*    */
/*    */ public class StopTime extends CustomCard
/*    */ {
/*    */   public static final String ID = "StopTime";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /* 18 */   public static final String[] EXTENDED_DESCRIPTION;
/*    */   public static final String IMG_PATH = "cards/stoptime.png";

/* 17 */   private static final CardType TYPE = CardType.SKILL;
/* 18 */   private static final CardRarity RARITY = CardRarity.SPECIAL;
/* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */                private static final CardStrings cardStrings;
/*    */
/*    */   private static final int COST = 0;
/*    */   private static final int BLOCK = 5;
/*    */   private static final int UPGRADE_BONUS = 3;
            public static boolean canPlay = true;
/*    */
/*    */   public StopTime()
/*    */   {
/* 27 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);

/*    */
/* 29 */     this.baseBlock = 15;
            this.magicNumber = this.baseMagicNumber = 2;

    this.exhaust = true;
    this.canPlay = true;
    this.isEthereal = true;
/*    */   }
/*    */


    public boolean canUse(AbstractPlayer p, AbstractMonster m)
        /*    */   {
        /* 50 */
        /* 51 */      if(!this.canPlay){

            /*    */    this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        } else {return true;}

        /* 61
        /*    */   }

    /*    */   public void use(AbstractPlayer p, AbstractMonster m)
/*    */   {


        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));


    /* 35 */
     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
         flash();
         for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
             if ((!monster.isDead) && (!monster.isDying)) {
                 AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new StunMonsterPower(monster, 1), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));


             }
         }
     }

            /* 50 */       //AbstractDungeon.actionManager.cardQueue.clear();
            /* 51 */
            /* 54 */      //AbstractDungeon.player.limbo.group.clear();
            /* 55 */       //AbstractDungeon.player.releaseCard();
            /* 56 */       AbstractDungeon.overlayMenu.endTurnButton.disable(true);
            /*    */
            /* 58 */       CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
            /* 59 */       AbstractDungeon.effectsQueue.add(new com.megacrit.cardcrawl.vfx.BorderFlashEffect(com.badlogic.gdx.graphics.Color.GOLD, true));
            /* 60 */       AbstractDungeon.topLevelEffectsQueue.add(new com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect());
            /*    */
            /* 62 */
            /*    */
            /*    */       }
        /*    */
/*    */


    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        this.canPlay = false;
    }


    public void atTurnStart() {
        this.canPlay = true;
    }

    /*    */   public AbstractCard makeCopy()
/*    */   {
/* 40 */     return new StopTime();
/*    */   }
/*    */   
/*    */   public void upgrade()
/*    */   {
/* 45 */     if (!this.upgraded)
/*    */     {
/* 47 */       upgradeName();
upgradeMagicNumber(2);
/*    */     }
/*    */   }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\cards\Defend_Slimebound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */