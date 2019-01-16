/*    */ package slimebound.cards;
/*    */

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.PotencyPower;
import slimebound.powers.SlimeSacrificePower;

/*    */
/*    */ public class AbsorbAllPotency extends CustomCard
        /*    */ {
    /*    */   public static final String ID = "AbsorbAllPotency";
    /*    */   public static final String NAME;
    /*    */   public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    /*    */   public static final String IMG_PATH = "cards/knowledgepool.png";

    /* 17 */   private static final CardType TYPE = CardType.SKILL;
    /* 18 */   private static final CardRarity RARITY = CardRarity.RARE;
    /* 19 */   private static final CardTarget TARGET = CardTarget.SELF;
    /*    */                private static final CardStrings cardStrings;

    /*    */   private static final int COST = 2;
    /*    */   private static final int BLOCK = 5;
    /*    */   private static final int UPGRADE_BONUS = 3;
    /*    */
    /*    */   public AbsorbAllPotency()
    /*    */   {
        /* 27 */     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

        /*    */    this.magicNumber = this.baseMagicNumber = 1;
        /* 29 */    this.exhaust = true;


        /*    */   }
    /*    */
    /*    */   public void use(AbstractPlayer p, AbstractMonster m)
    /*    */ {

        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new BorderFlashEffect(Color.GREEN, true), 0.05F, true));

        AbstractDungeon.effectsQueue.add(new HealEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 5));
        if (!AbstractDungeon.player.orbs.isEmpty()) {
            int slimeCount = 0;
            for (AbstractOrb o : AbstractDungeon.player.orbs) {

                if (o.ID == "TorchHeadSlime" ||
                        o.ID == "AttackSlime" ||
                        o.ID == "PoisonSlime" ||
                        o.ID == "SlimingSlime" ||
                        o.ID == "BronzeSlime" ||
                        o.ID == "DebuffSlime" ||
                        o.ID == "CultistSlime"||
                        o.ID == "HexSlime") { // when equipped (picked up) this relic counts how many ethereal cards are in the player's deck

                    slimeCount++;
                    AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.defect.EvokeOrbAction(1));


                }


            }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PotencyPower(p, p, slimeCount), slimeCount, true));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, slimeCount), slimeCount, true));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, slimeCount), slimeCount, true));

        }
    }
    /*    */
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
    /*    */   public AbstractCard makeCopy()
    /*    */   {
        /* 40 */     return new AbsorbAllPotency();
        /*    */   }
    /*    */
    /*    */   public void upgrade()
    /*    */   {
        /* 45 */     if (!this.upgraded)
            /*    */     {
            /* 47 */       upgradeName();
            upgradeBaseCost(1);


            /*    */     }
        /*    */   }
    /*    */ }

