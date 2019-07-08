package ru.ftc.android.shifttemple.features.cards.data;

import java.util.List;


import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;



public interface CardsDataSource {

    void getCards(Carry<List<Card>> carry);

    void getCard(String id, Carry<Card> carry);

    void createCard(Card card, Carry<Card> carry);

    void deleteCard(String id, Carry<Success> carry);
}
