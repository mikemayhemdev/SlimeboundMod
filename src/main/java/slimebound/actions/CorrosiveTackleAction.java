package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Random;


public class CorrosiveTackleAction extends AbstractGameAction {
    public boolean upgradeCard;
    private int damage;
    private AbstractPlayer p;
    private AbstractMonster m;

    public CorrosiveTackleAction(AbstractPlayer p, boolean upgraded) {
        this.upgradeCard = upgraded;


    }


    public void update() {

        AbstractCard c = null;
        Random random = new Random();
        Integer chosenRand = random.nextInt(4);

        if (p.hasPower("TackleBuffPower")) {
            if (p.getPower("TackleBuffPower").amount > 2) {
                if (chosenRand == 0) {
                    c = CardLibrary.getCard("Tackle").makeCopy();
                } else if (chosenRand == 1) {
                    c = CardLibrary.getCard("CorrosiveTackle").makeCopy();
                } else if (chosenRand == 2) {
                    c = CardLibrary.getCard("FlameTackle").makeCopy();
                } else if (chosenRand == 3) {
                    c = CardLibrary.getCard("PoisonTackle").makeCopy();
                } else {
                    c = CardLibrary.getCard("QuickTackle").makeCopy();
                }
            }

            if (upgradeCard) {
                c.upgrade();
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
        }


        this.isDone = true;
    }

}



