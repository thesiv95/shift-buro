package ru.ftc.android.shifttemple.features.cards.presentation.CardList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;

/**
 * Created: samokryl
 * Date: 02.07.18
 * Time: 0:24
 */

final class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {

    private final List<Card> cards = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectCardListener selectCardListener;

    CardAdapter(Context context, SelectCardListener selectCardListener) {
        inflater = LayoutInflater.from(context);
        this.selectCardListener = selectCardListener;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.book_item, parent, false);
        return new CardHolder(itemView, selectCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        holder.bind(cards.get(position));
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(List<Card> cardList) {
        cards.clear();
        cards.addAll(cardList);
        notifyDataSetChanged();
    }

    class CardHolder extends RecyclerView.ViewHolder {

        private final TextView CardTaskView;
        private final TextView CardStatusView;
        private final TextView CardCategoryView;
        private final TextView CardNameView;
        private final TextView CardPhoneView;
        private final TextView CardCityView;
        private final TextView CardDescriptionView;
        private final TextView CardCostView;
        private final SelectCardListener selectCardListener;

        CardHolder(View view, SelectCardListener selectCardListener) {
            super(view);
            this.selectCardListener = selectCardListener;
            CardTaskView = view.findViewById(R.id.card_item_task);
            CardStatusView = view.findViewById(R.id.card_item_status);
            CardCategoryView = view.findViewById(R.id.category_content_text_view);
            CardNameView = view.findViewById(R.id.fulname_content_text_view);
            CardPhoneView = view.findViewById(R.id.phone_content_text_view);
            CardCityView = view.findViewById(R.id.city_content_text_view);
            CardDescriptionView = view.findViewById(R.id.description_content_text_view);
            CardCostView = view.findViewById(R.id.cost_text_view);
        }

        void bind(final Card card) {
            String task = String.valueOf(card.getTask());

            String status = "Cтатус: ";
            status += card.getStatus() ? "Актуально" : "Устарело";

            String category = card.getType();
            String name = card.getOwnerName();
            String phone = card.getPhone();
            String city = card.getCity();
            String description = card.getDescription();

            String cost = String.valueOf(card.getPrice());
            cost += " баллов";

            CardTaskView.setText(task);
            CardStatusView.setText(status);
            CardCategoryView.setText(category);
            CardNameView.setText(name);
            CardPhoneView.setText(phone);
            CardCityView.setText(city);
            CardDescriptionView.setText(description);
            CardCostView.setText(cost);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectCardListener.onCardSelect(card);
                }
            });*/

            /*itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectCardListener.onCardLongClick(card);
                    return true;
                }
            });*/

        }

    }

    interface SelectCardListener {

        void onCardSelect(Card card);

        void onCardLongClick(Card card);

    }

}
