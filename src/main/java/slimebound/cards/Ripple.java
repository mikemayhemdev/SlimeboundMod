 package slimebound.cards;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;



 public class Ripple extends CustomCard
 {
       public static final String ID = "Ripple";
       public static final String NAME;
       public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
       public static final String IMG_PATH = "cards/ripple.png";
       private static final CardType TYPE = CardType.SKILL;
       private static final CardRarity RARITY = CardRarity.SPECIAL;
       private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final CardStrings cardStrings;
       private static final int COST = 1;
       private static final int POWER = 6;
       private static final int UPGRADE_BONUS = 3;


    public Ripple()
     {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);


        this.magicNumber = this.baseMagicNumber = 2;
        this.baseBlock = 10;

        this.exhaust=true;
        this.isEthereal=true;

    }



    public void use(AbstractPlayer p, AbstractMonster m)
     {



        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.animations.VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.75F));
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                flash();
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                 if ((!monster.isDead) && (!monster.isDying)) {
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new WeakPower(monster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new VulnerablePower(monster, this.magicNumber,false), this.magicNumber , true, AbstractGameAction.AttackEffect.NONE));



                    }

                       }
                 }

             AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, this.block));



    }






    public AbstractCard makeCopy()
     {

        return new Ripple();

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
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    }
}


