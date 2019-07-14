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

public interface CardsApi {

    @GET("/cards/getAllCards")
    Call<List<Card>> getCardList();

    @GET("/cards/getTypedCards/{type}")
    Call<List<Card>> getTypedCardList(@Path("type") String type);

    @GET("cards/{id}")
    Call<Card> getCard(@Path("id") String id);

    @POST("/cards/addCard")
    Call<Void> addCard(@Body Card card);

    @POST("/cards/updateStatus/{cardId}/{userId}")
    Call<Void> updateCardStatus(@Path("cardId") Integer cardId, @Path("userId") Integer userid);

    @DELETE("cards/deleteCard/{id}")
    Call<Success> deleteCard(@Path("id") String id);

}
