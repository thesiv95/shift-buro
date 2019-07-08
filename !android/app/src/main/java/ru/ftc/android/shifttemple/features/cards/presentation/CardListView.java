package ru.ftc.android.shifttemple.features.cards.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:22
 */

interface CardListView extends MvpView {

    void showProgress();

    void hideProgress();

    void showCardList(List<Card> list);

    void showError(String message);

}
