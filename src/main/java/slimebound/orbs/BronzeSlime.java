package slimebound.orbs;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import slimebound.actions.SlimeAutoBronze;
import slimebound.actions.SlimeAutoCultist;
import slimebound.vfx.SlimeFlareEffect;


public class BronzeSlime
        extends SpawnedSlime {
    public static final String ID = "Slimebound:BronzeSlime";

    public BronzeSlime() {

        super(ID, "images/monsters/theBottom/slimeAltM/skeleton.atlas","images/monsters/theBottom/slimeAltM/skeleton.json","idle",1.5F,new Color(1F,150/255F,0F,2F),6, 0,false, new Color(.63F, .58F, .41F, 1), SlimeFlareEffect.OrbFlareColor.BRONZE, new Texture("SlimeboundImages/orbs/attackDefend.png"), "SlimeboundImages/orbs/bronzeslime.png");
    }


    public void updateDescription() {
        this.description = this.descriptions[0] + this.passiveAmount + this.descriptions[1] + this.passiveAmount + this.descriptions[2];
    }


    public void activateEffectUnique() {

        AbstractDungeon.actionManager.addToBottom(new SlimeAutoBronze(AbstractDungeon.player,this.passiveAmount,this));

    }

    public AbstractOrb makeCopy() {
        return new BronzeSlime();
    }
}


