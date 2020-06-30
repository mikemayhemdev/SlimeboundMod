package slimeboundclassic.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeboundclassic.SlimeboundMod;

public class PreventCurrentOverMaxHealthAction extends AbstractGameAction {
    private AbstractPlayer p;

    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());


    public PreventCurrentOverMaxHealthAction() {

        this.p = AbstractDungeon.player;

        setValues(this.p, AbstractDungeon.player, this.amount);

        this.actionType = ActionType.CARD_MANIPULATION;

        this.duration = Settings.ACTION_DUR_FAST;

    }


    public void update() {
        if (p.currentHealth > p.maxHealth - this.amount){

            p.currentHealth = p.maxHealth - this.amount;

        }

            this.isDone = true;


    }
}



