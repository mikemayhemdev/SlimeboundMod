package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;


public class CheckForSixHexAction extends AbstractGameAction {
    public boolean upgradeCard;
    public AbstractPlayer p;

    public CheckForSixHexAction(AbstractPlayer p) {
        this.p = p;


    }


    public void update() {

        int hexslimecount = 0;
        for (AbstractOrb o : AbstractDungeon.player.orbs) {

            if (o.ID == "HexSlime") {
                hexslimecount++;

            }
        }
        if (hexslimecount > 5) {
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.3F));
            for (AbstractOrb o : AbstractDungeon.player.orbs) {


                com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.defect.EvokeOrbAction(1));


            }
        }


        this.isDone = true;
    }

}



