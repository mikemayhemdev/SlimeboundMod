package slimebound.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import slimebound.actions.SlimeSpawnAction;
import slimebound.powers.SlimeSacrificePower;

public class SlimedTailRelic extends CustomRelic {
    public static final String ID = "SlimedTailRelic";
    public static final String IMG_PATH = "relics/slimedTail.png";
    public static final String OUTLINE_IMG_PATH = "relics/slimedTailOutline.png";
    private static final int HP_PER_CARD = 1;
    private boolean isActive = false;

    public SlimedTailRelic() {
        super(ID, new Texture(slimebound.SlimeboundMod.getResourcePath(IMG_PATH)), new Texture(slimebound.SlimeboundMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.isActive = true;
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            public void update() {
                if (SlimedTailRelic.this.isActive && AbstractDungeon.player.isBloodied) {
                    SlimedTailRelic.this.flash();


                    AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, SlimedTailRelic.this));
                    AbstractDungeon.actionManager.addToTop(new SlimeSpawnAction(new slimebound.orbs.PoisonSlime(), false, false));
                    AbstractDungeon.actionManager.addToTop(new SlimeSpawnAction(new slimebound.orbs.PoisonSlime(), false, false));
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SlimeSacrificePower(AbstractDungeon.player, 2), 2, true));

                    SlimedTailRelic.this.isActive = false;
                    AbstractDungeon.player.hand.applyPowers();
                }

                this.isDone = true;
            }
        });
    }

    public void onBloodied() {


        if (this.isActive && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractPlayer p = AbstractDungeon.player;
            SlimedTailRelic.this.flash();
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(p, this));
            AbstractDungeon.actionManager.addToTop(new SlimeSpawnAction(new slimebound.orbs.PoisonSlime(), false, false));
            AbstractDungeon.actionManager.addToTop(new SlimeSpawnAction(new slimebound.orbs.PoisonSlime(), false, false));
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new SlimeSacrificePower(AbstractDungeon.player, 2), 2, true));

            this.isActive = false;
            AbstractDungeon.player.hand.applyPowers();
        }

    }


    public void onVictory() {

        this.isActive = true;
    }


    @Override
    public AbstractRelic makeCopy() {
        return new SlimedTailRelic();
    }

}