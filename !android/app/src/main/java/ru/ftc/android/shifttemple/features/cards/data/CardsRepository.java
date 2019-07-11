package ru.ftc.android.shifttemple.features.cards.data;

import java.util.List;

import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface CardsRepository {

    void loadCards(Carry<List<Card>> carry);

    void loadInvitationCards(Carry<List<Card>> carry);

    void loadRequestCards(Carry<List<Card>> carry);

    void loadCard(String id, Carry<Card> carry);

    void createCard(Card card, Carry<Card> carry);

    void deleteCard(String id, Carry<Success> carry);
}