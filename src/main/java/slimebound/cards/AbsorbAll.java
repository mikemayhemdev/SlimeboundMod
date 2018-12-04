 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.ShieldParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.SlimeSacrificePower;


 public class AbsorbAll extends CustomCard
         {
       public static final String ID = "AbsorbAll";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
       public static final String IMG_PATH = "cards/absorball.png";

       private static final CardType TYPE = CardType.SKILL;
       private static final CardRarity RARITY = CardRarity.COMMON;
       private static final CardTarget TARGET = CardTarget.SELF;
                    private static final CardStrings cardStrings;

       private static final int COST = 1;
       private static final int BLOCK = 5;
       private static final int UPGRADE_BONUS = 3;

       public AbsorbAll()
       {
             super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);





           }

       public void use(AbstractPlayer p, AbstractMonster m)
     {
        AbstractDungeon.effectsQueue.add(new HealEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 5));
       int blockgain = 0;
        if (!AbstractDungeon.player.orbs.isEmpty()) {
            for (AbstractOrb o : AbstractDungeon.player.orbs) {

                if (o.ID == "TorchHeadSlime" ||
                        o.ID == "AttackSlime" ||
                        o.ID == "PoisonSlime" ||
                        o.ID == "SlimingSlime" ||
                        o.ID == "BronzeSlime" ||
                        o.ID == "DebuffSlime" ||
                        o.ID == "CultistSlime" ||
                        o.ID == "HexSlime") {


                    com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.defect.EvokeOrbAction(1));
                    blockgain = blockgain + 3;

                }
            }
            if (blockgain > 0){
                     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, blockgain));

            }
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
       public AbstractCard makeCopy()
       {
             return new AbsorbAll();
           }

       public void upgrade()
       {
             if (!this.upgraded)
                 {
                   upgradeName();
            upgradeBaseCost(0);


                 }
           }
     }

