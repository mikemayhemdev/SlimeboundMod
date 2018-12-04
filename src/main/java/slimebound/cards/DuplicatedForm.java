 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.EchoPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.DuplicatedFormEnergyPower;
import slimebound.powers.DuplicatedFormNoHealPower;
import slimebound.powers.DuplicatedFormPower;
import slimebound.powers.SelfFormingGooPower;
import slimebound.vfx.SlimeDripsEffect;


 public class DuplicatedForm extends CustomCard
         {
       public static final String ID = "DuplicatedForm";

                private static final CardStrings cardStrings;
                public static final String NAME;
                public static final String DESCRIPTION;
       public static final String[] EXTENDED_DESCRIPTION;
       public static final String IMG_PATH = "cards/duplicatedform.png";
       private static final CardType TYPE = CardType.POWER;
       private static final CardRarity RARITY = CardRarity.RARE;
       private static final CardTarget TARGET = CardTarget.SELF;

       private static final int COST = 3;

       private static int upgradedamount = 1;

       public DuplicatedForm()
       {
             super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);
                    this.magicNumber = this.baseMagicNumber = 1;


           }

    public boolean canUse(AbstractPlayer p, AbstractMonster m)
           {
            double currentPct = p.currentHealth * 1.001 / p.maxHealth * 1.001;
              if(currentPct > 0.5){

                this.cantUseMessage = EXTENDED_DESCRIPTION[0];
                        return false;} else {return true;}

           }

       public void use(AbstractPlayer p, AbstractMonster m)
       {
        AbstractDungeon.effectsQueue.add(new SlimeDripsEffect(p.hb.cX,p.hb.cY,0));
        AbstractDungeon.effectsQueue.add(new SlimeDripsEffect(p.hb.cX,p.hb.cY,0));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new BorderFlashEffect(Color.GREEN, true), 0.05F, true));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new IntenseZoomEffect(p.hb.cX,p.hb.cY,false), 0.05F));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DuplicatedFormPower(p, p, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DuplicatedFormNoHealPower(p, p, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DuplicatedFormEnergyPower(p, p, this.magicNumber), this.magicNumber));

             }

       public AbstractCard makeCopy()
       {
             return new DuplicatedForm();
           }

       public void upgrade()
       {
             if (!this.upgraded)
                 {
                   upgradeName();
            upgradeMagicNumber(1);


                 }
           }



    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }
     }

