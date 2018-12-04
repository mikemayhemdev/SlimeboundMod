package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;


public class TriggerSlimeAttacksAction extends AbstractGameAction {
    public boolean upgradeCard;
    public AbstractPlayer p;

    public TriggerSlimeAttacksAction(AbstractPlayer p) {
        this.p = p;


    }


    public void update() {


        for (AbstractOrb o : p.orbs) {

            if (o.ID == "TorchHeadSlime" ||
                    o.ID == "AttackSlime" ||
                    o.ID == "PoisonSlime" ||
                    o.ID == "SlimingSlime" ||
                    o.ID == "BronzeSlime" ||
                    o.ID == "DebuffSlime" ||
                    o.ID == "CultistSlime" ||
                    o.ID == "HexSlime") {
                o.onStartOfTurn();

            }
        }


        this.isDone = true;
    }

}



