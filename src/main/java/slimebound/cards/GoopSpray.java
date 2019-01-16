package slimebound.cards;



import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.SlimedPower;
import slimebound.vfx.SlimeProjectileEffect;


public class GoopSpray extends AbstractSlimeboundCard {
    public static final String ID = "Slimebound:GoopSpray";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/goopspray.png";
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final CardStrings cardStrings;
    private static final int COST = 2;
    private static final int POWER = 6;
    private static final int UPGRADE_BONUS = 3;


    public GoopSpray() {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

        this.magicNumber = this.baseMagicNumber = 3;
        this.slimed = this.baseSlimed = 8;
        upgradeSlimed(0);


        this.exhaust = true;

    }


    public void use(AbstractPlayer p, AbstractMonster m) {


        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new SlimeProjectileEffect(p.hb.cX, p.hb.cY, monster.hb.cX, monster.hb.cY,3F,false,0.6F), 0.01F));


                }
                AbstractDungeon.actionManager.addToBottom(new WaitAction(0.2F));
            }
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {

                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new SlimedPower(monster, p, this.slimed ), this.slimed , true, AbstractGameAction.AttackEffect.NONE));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new PoisonPower(monster, p, this.magicNumber ), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));


                }

            }
        }


    }


    public AbstractCard makeCopy() {

        return new GoopSpray();

    }


    public void upgrade() {

        if (!this.upgraded) {

            upgradeName();

            upgradeSlimed(2);
            upgradeMagicNumber(2);

        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    }
}


