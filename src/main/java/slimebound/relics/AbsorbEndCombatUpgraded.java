package slimebound.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class AbsorbEndCombatUpgraded extends CustomRelic {
    public static final String ID = "AbsorbEndCombatUpgraded";
    public static final String IMG_PATH = "relics/heartofgooblack.png";
    public static final String OUTLINE_IMG_PATH = "relics/heartofgooOutline.png";
    private static final int HP_PER_CARD = 1;

    public AbsorbEndCombatUpgraded() {
        super(ID, new Texture(slimebound.SlimeboundMod.getResourcePath(IMG_PATH)),new Texture(slimebound.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)), // you could create the texture in this class if you wanted too
                RelicTier.BOSS, LandingSound.MAGICAL); // this relic is uncommon and sounds magic when you click it
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onVictory() {
        this.flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        int slimeCount = 0;
        if (p.orbs.get(0) != null) {
            for (AbstractOrb o : AbstractDungeon.player.orbs) {

                if (o.ID == "TorchHeadSlime" ||
                        o.ID == "AttackSlime" ||
                        o.ID == "PoisonSlime" ||
                        o.ID == "SlimingSlime" ||
                        o.ID == "BronzeSlime" ||
                        o.ID == "DebuffSlime" ||
                        o.ID == "CultistSlime" ||
                        o.ID == "HexSlime") {
                    slimeCount++;
                }


            }
            p.heal(slimeCount);
        }
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }


    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic("AbsorbEndCombatUpgraded");
    }


    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new AbsorbEndCombatUpgraded();
    }

}