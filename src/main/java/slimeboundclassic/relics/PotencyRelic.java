package slimeboundclassic.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import slimeboundclassic.actions.SlimeSpawnAction;
import slimeboundclassic.characters.SlimeboundCharacter;
import slimeboundclassic.powers.PotencyPower;

public class PotencyRelic extends CustomRelic {
    public static final String ID = "SlimeboundClassic:PotencyRelic";
    public static final String IMG_PATH = "relics/oozeStone.png";
    public static final String IMG_PATH_LARGE = "relics/oozeStonearge.png";
    public static final String OUTLINE_IMG_PATH = "relics/oozeStoneOutline.png";
    private static final int HP_PER_CARD = 1;

    public PotencyRelic() {
        super(ID, new Texture(slimeboundclassic.SlimeboundMod.getResourcePath(IMG_PATH)), new Texture(slimeboundclassic.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.RARE, LandingSound.MAGICAL);
        this.largeImg = ImageMaster.loadImage(slimeboundclassic.SlimeboundMod.getResourcePath(IMG_PATH_LARGE));

    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new SlimeSpawnAction(new slimeboundclassic.orbs.SlimingSlime(), false, true));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PotencyPower(AbstractDungeon.player, AbstractDungeon.player, 1), 1));

    }
    public boolean canSpawn() {
        return AbstractDungeon.player instanceof SlimeboundCharacter;
    }


    @Override
    public AbstractRelic makeCopy() {
        return new PotencyRelic();
    }

}