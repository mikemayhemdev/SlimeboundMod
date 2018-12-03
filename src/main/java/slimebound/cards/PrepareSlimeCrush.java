/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.utility.ShakeScreenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.vfx.MegaSpeechBubble;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.EnergizedSlimeboundPower;
import slimebound.powers.NextTurnGainSlimeCrush;
import slimebound.powers.NextTurnGainStrengthPower;

/*    */
/*    */ public class PrepareSlimeCrush extends CustomCard
/*    */ {
/*    */   public static final String ID = "PrepareSlimeCrush";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
/*    */   public static final String IMG_PATH = "cards/preparingcrush.png";

/* 17 */   private static final CardType TYPE = CardType.SKILL;
/* 18 */   private static final CardRarity RARITY = CardRarity.RARE;
/* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */                private static final CardStrings cardStrings;
/*    */
/*    */   private static final int COST = 2;
/*    */   private static final int BLOCK = 5;
/*    */   private static final int UPGRADE_BONUS = 3;
/*    */
/*    */   public PrepareSlimeCrush()
/*    */   {
/* 27 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

/*    */

         this.exhaust = true;
            this.magicNumber = this.baseMagicNumber = 3;
    this.exhaust = true;
/*    */   }
/*    */
/*    */   public void use(AbstractPlayer p, AbstractMonster m)
/*    */   {
    AbstractDungeon.effectList.add(new MegaSpeechBubble(p.hb.cX,  p.hb.cY, 1.0F, "~Slime...~ NL #r~CRUSH!!!~", true));

    //AbstractDungeon.actionManager.addToBottom(new ShoutAction(p, , 1.0F, 1.0F));
    AbstractDungeon.actionManager.addToBottom(new ShakeScreenAction(0.3F, ScreenShake.ShakeDur.MED, ScreenShake.ShakeIntensity.LOW));

    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnergizedSlimeboundPower(p, p,3), 3));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NextTurnGainStrengthPower(p, p, this.magicNumber), this.magicNumber));
    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NextTurnGainSlimeCrush(p, p, 1), 1));


            /*    */   }
/*    */   
/*    */   public AbstractCard makeCopy()
/*    */   {
/* 40 */     return new PrepareSlimeCrush();
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
    }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\mods\SlimeboundMod.jar!\slimboundmod\cards\Defend_Slimebound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */