package ru.ftc.android.shifttemple.features.cards.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.domain.model.Success;
import ru.ftc.android.shifttemple.network.Carry;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface CardsInteractor {

    void loadCards(Carry<List<Card>> carry);

    void loadInvitationCards(Carry<List<Card>> carry);

    void loadRequestCards(Carry<List<Card>> carry);

    void loadCard(String id, Carry<Card> carry);

    void createCard(Card card, Carry<Void> carry);

    void deleteCard(String id, Carry<Success> carry);

    void updateCardStatus(Integer id, Integer userID,  Carry<Void> carry);
}