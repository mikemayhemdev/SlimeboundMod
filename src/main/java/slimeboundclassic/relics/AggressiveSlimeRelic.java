package slimeboundclassic.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimeboundclassic.actions.SlimeSpawnAction;
import slimeboundclassic.characters.SlimeboundCharacter;

public class AggressiveSlimeRelic extends CustomRelic {
    public static final String ID = "SlimeboundClassic:AggressiveSlimeRelic";
    public static final String IMG_PATH = "relics/minion.png";
    public static final String IMG_PATH_LARGE = "relics/minionLarge.png";
    public static final String OUTLINE_IMG_PATH = "relics/minionOutline.png";
    private static final int HP_PER_CARD = 1;

    public AggressiveSlimeRelic() {
        super(ID, new Texture(slimeboundclassic.SlimeboundMod.getResourcePath(IMG_PATH)), new Texture(slimeboundclassic.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.COMMON, LandingSound.FLAT);
        this.largeImg = ImageMaster.loadImage(slimeboundclassic.SlimeboundMod.getResourcePath(IMG_PATH_LARGE));

    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimeboundclassic.orbs.AttackSlime(), false, true));

    }
    public boolean canSpawn() {
        return AbstractDungeon.player instanceof SlimeboundCharacter;
    }


    @Override
    public AbstractRelic makeCopy() {
        return new AggressiveSlimeRelic();
    }

}