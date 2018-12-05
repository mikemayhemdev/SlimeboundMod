package slimebound.cards;



import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import slimebound.SlimeboundMod;
import slimebound.orbs.SpawnedSlime;
import slimebound.patches.AbstractCardEnum;
import slimebound.powers.PotencyPower;

import java.util.Random;


public class AbsorbAllPotency extends AbstractSlimeboundCard {
    public static final String ID = "AbsorbAllPotency";
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/knowledgepool.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings;

    private static final int COST = 2;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BONUS = 3;

    public AbsorbAllPotency() {
        super(ID, NAME, SlimeboundMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SLIMEBOUND, RARITY, TARGET);

        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;


    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new BorderFlashEffect(Color.GREEN, true), 0.05F, true));

        AbstractDungeon.effectsQueue.add(new HealEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, 5));
        if (!AbstractDungeon.player.orbs.isEmpty()) {
            Random random = new Random();
            for (AbstractOrb o : AbstractDungeon.player.orbs) {

                if (o instanceof SpawnedSlime) {

                    AbstractDungeon.actionManager.addToBottom(new EvokeSpecificOrbAction(o));

                    Integer chosenRand = random.nextInt(3) + 1;

                    switch (chosenRand) {
                        case 1:
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PotencyPower(p, p, 1), 1, true));
                            break;
                        case 2:
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1, true));
                            break;
                        case 3:
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1), 1, true));
                            break;
                    }

                }


            }



        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

    public AbstractCard makeCopy() {
        return new AbsorbAllPotency();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);


        }
    }
}

