package slimebound.orbs;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import slimebound.actions.SlimeAutoAttacking;
import slimebound.actions.SlimeAutoSliming;
import slimebound.powers.SlimedPower;
import slimebound.vfx.SlimeFlareEffect;


public class SlimingSlime
        extends SpawnedSlime {
    public SlimingSlime() {
        super("SlimingSlime", 2, true, new Color(.6F, .47F, .59F, 1), SlimeFlareEffect.OrbFlareColor.SLIMING, new Texture("SlimeboundImages/orbs/debuff2.png"), "SlimeboundImages/orbs/sliming.png");
    }


    public void updateDescription() {
        this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1];
    }


    public void activateEffectUnique() {

        AbstractDungeon.actionManager.addToBottom(new SlimeAutoSliming(AbstractDungeon.player,this.passiveAmount,this));

         }


    public AbstractOrb makeCopy() {
        return new SlimingSlime();
    }
}


