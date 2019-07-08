package ru.ftc.android.shifttemple.features.cards.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.domain.model.Success;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public interface CardsApi {

    @GET("books")
    Call<List<Card>> getCardList();

    @GET("books/{id}")
    Call<Card> getCard(@Path("id") String id);

    @POST("books")
    Call<Card> createCard(@Body Card card);

    @DELETE("books/{id}")
    Call<Success> deleteCard(@Path("id") String id);

}
