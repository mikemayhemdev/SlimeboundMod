package slimebound.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import slimebound.SlimeboundMod;
import slimebound.cards.Flail;
import slimebound.cards.HyperBeamSlimedbound;
import slimebound.cards.SplitBronze;

import java.util.Random;


public class RandomAutomatonCardAction extends AbstractGameAction {
    public boolean upgradeCard;

    public RandomAutomatonCardAction(boolean upgraded) {
        this.upgradeCard = upgraded;


    }


    public void update() {

        AbstractCard c;
        do {
            c = CardLibrary.getRandomColorSpecificCard(AbstractCard.CardColor.COLORLESS, AbstractDungeon.cardRandomRng).makeCopy();
        } while (!c.hasTag(SlimeboundMod.STUDY_AUTOMATON));




        if (upgradeCard) {
            c.upgrade();
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

        this.isDone = true;
    }

}



