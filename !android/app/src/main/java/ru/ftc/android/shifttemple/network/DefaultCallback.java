package ru.ftc.android.shifttemple.network;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.ftc.android.shifttemple.exception.DataBaseException;

public final class DefaultCallback<T> implements Callback<T> {

    private final Carry<T> carry;

    public DefaultCallback(final Carry<T> carry) {
        this.carry = carry;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        if (response.isSuccessful()) {
            carry.onSuccess(body);
        }
        else {
            try {
                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                String  errorMessage = jsonObject.getString("message");
                carry.onFailure(new DataBaseException(errorMessage));
            } catch (Exception e) {
                carry.onFailure(e);
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        carry.onFailure(t);
    }

}