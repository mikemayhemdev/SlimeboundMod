package slimebound.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimebound.orbs.SpawnedSlime;

public class AbsorbEndCombat extends CustomRelic {
    public static final String ID = "AbsorbEndCombat";
    public static final String IMG_PATH = "relics/heartofgoo.png";
    public static final String OUTLINE_IMG_PATH = "relics/heartofgooOutline.png";
    private static final int HP_PER_CARD = 1;

    public AbsorbEndCombat() {
        super(ID, new Texture(slimebound.SlimeboundMod.getResourcePath(IMG_PATH)), new Texture(slimebound.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.STARTER, LandingSound.MAGICAL);
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

                if (o instanceof SpawnedSlime) {
                    slimeCount++;
                }


            }
            p.heal(slimeCount * 3);

        }
    }

    @Override
    public void onUnequip() {
        for (AbstractRelic relicInBossPool : RelicLibrary.bossList) {
            if (relicInBossPool instanceof AbsorbEndCombatUpgraded) {
                RelicLibrary.bossList.remove(relicInBossPool);
                break;
            }
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AbsorbEndCombat();
    }

}