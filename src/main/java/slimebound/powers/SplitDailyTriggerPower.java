
package slimebound.powers;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.animations.AnimateShakeAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.unique.CannotLoseAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import slimebound.SlimeboundMod;

import java.lang.reflect.Field;

public class SplitDailyTriggerPower extends AbstractPower {
    public static final String POWER_ID = "Slimebound:SplitDailyTriggerPower";
    public static final String NAME = "Slime Sacrifice";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "powers/SplitForLessS.png";
    public static String[] DESCRIPTIONS;
    private boolean isActive;

    public SplitDailyTriggerPower(AbstractMonster owner) {
        this(owner, 1);
    }

    public SplitDailyTriggerPower(AbstractMonster owner, int amount) {

        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.updateDescription();
        this.img = new com.badlogic.gdx.graphics.Texture(slimebound.SlimeboundMod.getResourcePath(IMG));

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + FontHelper.colorString(this.owner.name, "y") + DESCRIPTIONS[1];

    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            AbstractMonster m = (AbstractMonster) this.owner;
            SlimeboundMod.logger.info(m.name + " lost HP. Current:" + m.currentHealth + ", Max: " + m.maxHealth + ", damage: " + damageAmount);
            if ((float) m.currentHealth - (float) damageAmount <= (float) m.maxHealth / 2) {
                this.isActive = true;
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(m, m, ArtifactPower.POWER_ID));

                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, m, new StunMonsterPower(m, 1), 1));
                AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(m, TextAboveCreatureAction.TextType.INTERRUPTED));
            }

        }

        return super.onAttacked(info, damageAmount);
    }



    public void atStartOfTurn() {
        if (isActive) {
            AbstractMonster m = (AbstractMonster) this.owner;

            AbstractDungeon.actionManager.addToBottom(new CannotLoseAction());
            AbstractDungeon.actionManager.addToBottom(new AnimateShakeAction(m, 1.0F, 0.1F));
            AbstractDungeon.actionManager.addToBottom(new HideHealthBarAction(m));
            AbstractDungeon.actionManager.addToBottom(new SuicideAction(m, false));
            AbstractDungeon.actionManager.addToBottom(new WaitAction(1.0F));
            AbstractDungeon.actionManager.addToBottom(new SFXAction("SLIME_SPLIT"));

            //AbstractDungeon.getMonsters().monsters.remove(m);

            if (m.currentHealth < 20) {
                AbstractMonster mini1 = new AcidSlime_S(m.hb_x - 55F, m.animX + MathUtils.random(-4.0F, 4.0F), 0);
                mini1.maxHealth = m.currentHealth;
                mini1.currentHealth = m.currentHealth;
                mini1.usePreBattleAction();
                mini1.useUniversalPreBattleAction();

                AbstractMonster mini2 = new SpikeSlime_S(m.hb_x + 55F, m.animX + MathUtils.random(-4.0F, 4.0F), 0);
                mini2.maxHealth = m.currentHealth;
                mini2.currentHealth = m.currentHealth;
                mini2.usePreBattleAction();
                mini2.useUniversalPreBattleAction();

                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(mini1, true));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(mini2, true));

            } else if (m.currentHealth < 40) {
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new AcidSlime_M(m.hb_x - 134.0F, m.animX + MathUtils.random(-4.0F, 4.0F), 0, m.currentHealth), true));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SpikeSlime_M(m.hb_x + 134.0F, m.animY + MathUtils.random(-4.0F, 4.0F), 0, m.currentHealth), true));
            } else if (m.currentHealth < 80) {
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new AcidSlime_L(m.hb_x - 134.0F, m.animX + MathUtils.random(-4.0F, 4.0F), 0, m.currentHealth), false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(new SpikeSlime_L(m.hb_x + 134.0F, m.animY + MathUtils.random(-4.0F, 4.0F), 0, m.currentHealth), false));
            } else {
                AbstractMonster boss1 = new SlimeBoss();
                boss1.hb_x = m.hb_x - 134.0F;
                boss1.maxHealth = m.currentHealth;
                boss1.currentHealth = m.currentHealth;

                AbstractMonster boss2 = new SlimeBoss();
                boss2.hb_x = m.hb_x - 134.0F;
                boss2.maxHealth = m.currentHealth;
                boss2.currentHealth = m.currentHealth;

                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(boss1, false));
                AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(boss2, false));
            }
        }


    }
}
