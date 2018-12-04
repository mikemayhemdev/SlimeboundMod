 package slimebound.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
 import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.UIStrings;
 import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimebound.SlimeboundMod;
 import java.util.ArrayList;
 import java.util.Iterator;

 public class OverexertionAction extends AbstractGameAction
 {
       private AbstractPlayer p;
       private final boolean upgrade;

    public static final Logger logger = LogManager.getLogger(SlimeboundMod.class.getName());



    public OverexertionAction(boolean upgrade) {

        this.upgrade = upgrade;

        this.p = AbstractDungeon.player;

        setValues(this.p, AbstractDungeon.player, this.amount);

        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;

        this.duration = Settings.ACTION_DUR_FAST;

    }



    public void update() {


        if (this.duration == Settings.ACTION_DUR_FAST)
             {

            if (this.p.exhaustPile.isEmpty()) {

                logger.info("Exhaust is empty");
                this.isDone = true;
                return;

            }

            int exhaustSize = p.exhaustPile.size();
            int healthLoss = exhaustSize;
            CardGroup cardsToReturn = AbstractDungeon.player.exhaustPile;
            logger.info("Exhaust size:" + exhaustSize);

            for (AbstractCard c : cardsToReturn.group) {

                if (c.cardID!="Overexertion") {



                    logger.info("Add to discard");
                    this.p.drawPile.addToRandomSpot(c);



                    logger.info("Modify Cost");
                    c.modifyCostForCombat(-1);










                } else {healthLoss--;}
            }
            for (int x = 0; x <= exhaustSize - 1; x++){
                logger.info("Exhausting card");
                AbstractCard cardReturning = p.exhaustPile.getBottomCard();
                if (cardReturning.cardID!="Overexertion") {
                    this.p.exhaustPile.removeCard(cardReturning);

                    logger.info("Unfadeout");
                    cardReturning.unfadeOut();

                    logger.info("Unhover");
                    cardReturning.unhover();


                    logger.info("Fading out false");
                    cardReturning.fadingOut = false;

                }

        }


            logger.info("Losing HP");
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player,  AbstractDungeon.player, healthLoss));

        this.isDone = true;

            return;

        }

        tickDuration();
    }
}



