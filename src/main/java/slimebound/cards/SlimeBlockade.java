 package slimebound.cards;


import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.ShieldParticleEffect;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.LoseThornsPower;


 public class SlimeBlockade extends CustomCard
 {
   public static final String ID = "SlimeBlockade";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
   public static final String IMG_PATH = "cards/formablockade.png";

   private static final CardType TYPE = CardType.SKILL;
   private static final CardRarity RARITY = CardRarity.COMMON;
   private static final CardTarget TARGET = CardTarget.SELF;
                    private static final CardStrings cardStrings;

   private static final int COST = 1;
   private static final int BLOCK = 5;
   private static final int UPGRADE_BONUS = 3;

   public SlimeBlockade()
   {
     super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);


     this.baseBlock = 5;
            this.magicNumber = this.baseMagicNumber = 2;
     this.tags.add(BaseModCardTags.BASIC_DEFEND);
   }

   public void use(AbstractPlayer p, AbstractMonster m)
   {
     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.block));
    for (AbstractOrb o : p.orbs) {

        if (o.ID == "TorchHeadSlime" ||
                o.ID == "AttackSlime" ||
                o.ID == "PoisonSlime" ||
                o.ID == "SlimingSlime" ||
                o.ID == "BronzeSlime" ||
                o.ID == "DebuffSlime" ||
                o.ID == "CultistSlime" ||
                o.ID == "HexSlime") {
            com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new VFXAction(new ShieldParticleEffect(o.cX, o.cY)));
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.magicNumber));

        }
    }
   }

   public AbstractCard makeCopy()
   {
     return new SlimeBlockade();
   }

   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();
       upgradeBlock(1);
                upgradeMagicNumber(1);
     }
   }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
 }


