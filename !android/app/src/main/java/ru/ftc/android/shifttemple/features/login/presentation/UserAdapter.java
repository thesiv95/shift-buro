package ru.ftc.android.shifttemple.features.login.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.Image.CircleImageTransformWithBorder;
import ru.ftc.android.shifttemple.features.login.domain.model.User;

public final class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private final List<User> userList = new ArrayList<>();
    private final LayoutInflater inflater;
    private  LoginJumpView loginJumpView;
    private Context context;

    UserAdapter(Context context, LoginJumpView loginJumpView) {
        inflater = LayoutInflater.from(context);
        this.loginJumpView = loginJumpView;
        this.context = context;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.user_item, parent, false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.bind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    void setUserList(List<User> userList) {
        this.userList.clear();
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder {

        private final TextView userNameView;
        private final ImageView imageView;

        UserHolder(View view) {
            super(view);

            userNameView = view.findViewById(R.id.user_item_name);
            imageView = view.findViewById(R.id.user_item_image);
        }

        void bind(final User user) {
            userNameView.setText(user.getName());

            //Load image from url in imageView and change it to circle
            int borderColorId = context.getResources().getColor(R.color.colorTitle);
            Picasso.get().load(user.getPicUrl()).transform(new CircleImageTransformWithBorder(borderColorId)).into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginJumpView.onItemClick(user);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
