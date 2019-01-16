/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.MegaSpeechBubble;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import slimebound.SlimeboundMod;

/*    */
/*    */ public class LastStand extends CustomCard
        /*    */ {
    /*    */   public static final String ID = "LastStand";
    /*    */
                private static final CardStrings cardStrings;
                public static final String NAME;
                public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /* 18 */   public static final String[] EXTENDED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/laststand.png";
    /* 17 */   private static final CardType TYPE = CardType.POWER;
    /* 18 */   private static final CardRarity RARITY = CardRarity.SPECIAL;
    /* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */
    /*    */   private static final int COST = 1;

    /*    */   private static int upgradedamount = 1;
    /*    */
    /*    */   public LastStand()
    /*    */   {
        /* 27 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);
                    this.magicNumber = this.baseMagicNumber = 6;
        this.isEthereal = true;


        /*    */   }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
        /*    */   {
        /* 50 */    double currentPct = p.currentHealth * 1.001 / p.maxHealth * 1.001;
        /* 51 */      if(currentPct > 0.5){

            /*    */    this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;} else {return true;}

        /* 61
        /*    */   }



    /*    */   public void use(AbstractPlayer p, AbstractMonster m)
    /*    */ {

        AbstractDungeon.effectList.add(new MegaSpeechBubble(p.hb.cX,  p.hb.cY, 1.0F, "~DIE~ ~.~ ~.~ ~.~", true));

        //AbstractDungeon.actionManager.addToBottom(new ShoutAction(p, , 1.0F, 1.0F));
        AbstractDungeon.actionManager.addToBottom(new ShakeScreenAction(0.3F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.LOW));

        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.15F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.15F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.15F));
         AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(p));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));


    }

    /*    */
    /*    */   public AbstractCard makeCopy()
    /*    */   {
        /* 40 */     return new LastStand();
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

