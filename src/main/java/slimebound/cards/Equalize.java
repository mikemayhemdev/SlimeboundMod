package slimebound.cards;



import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
import slimebound.patches.AbstractCardEnum;


public class Equalize extends AbstractSlimeboundCard {
    public static final String ID = "Slimebound:Equalize";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/retaliate.png";
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardStrings cardStrings;

    private static final int COST = 2;
    private static final int POWER = 6;
    public int missingHealth;
    private static final int UPGRADE_BONUS = 3;

    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());

    public Equalize() {

        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

        if (AbstractDungeon.player != null) {
            this.baseDamage = (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) / 2;
        } else{
            this.baseDamage = 0;
        }

    }

    public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {


        int bonus = (player.maxHealth - player.currentHealth) / 2;


        if (bonus > 0) {
            this.isDamageModified = true;
        }
        return tmp + bonus;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = 0;

        logger.info("max health: " + p.maxHealth + ", current health: " + p.currentHealth);

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

    }


    public AbstractCard makeCopy() {

        return new Equalize();

    }


    public void upgrade() {

        if (!this.upgraded) {

            upgradeName();
            upgradeBaseCost(1);


        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    }
}


