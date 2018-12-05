package slimebound.cards;

import basemod.abstracts.CustomCard;

import slimebound.SlimeboundMod;


public abstract class AbstractSlimeboundCard extends CustomCard {
    public AbstractSlimeboundCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color,
                               CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type,
                color, rarity, target);
    }

    public int selfDamage;
    public boolean upgradeSelfDamage;
    public boolean isSelfDamageModified;
}