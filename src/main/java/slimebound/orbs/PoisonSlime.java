package slimebound.orbs;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.PoisonPower;
import slimebound.actions.SlimeAutoAttacking;
import slimebound.actions.SlimeAutoPoisoning;
import slimebound.vfx.SlimeFlareEffect;


public class PoisonSlime
        extends SpawnedSlime {
    public static final String ID = "Slimebound:PoisonSlime";

    public PoisonSlime() {
        super(ID, "images/monsters/theBottom/slimeS/skeleton.atlas","images/monsters/theBottom/slimeS/skeleton.json","idle",.85F,new Color(0.8F,1F,.8F,2F),2, 2,true, new Color(.58F, .81F, .35F, 1), SlimeFlareEffect.OrbFlareColor.POISON, new Texture("SlimeboundImages/orbs/debuff1.png"), "SlimeboundImages/orbs/poisonous.png");
    }


    public void updateDescription() {
        this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1];
    }


    public void activateEffectUnique() {


        AbstractDungeon.actionManager.addToBottom(new SlimeAutoPoisoning(AbstractDungeon.player,this.passiveAmount,this));

    }


    public AbstractOrb makeCopy() {
        return new PoisonSlime();
    }
}


