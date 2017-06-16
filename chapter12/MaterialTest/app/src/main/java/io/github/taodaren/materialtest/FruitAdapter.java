package io.github.taodaren.materialtest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 水果适配器
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<FruitBean> fruitBeanList;

    public FruitAdapter(List<FruitBean> fruitBeanList) {
        this.fruitBeanList = fruitBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fruit, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FruitBean fruitBean = fruitBeanList.get(position);
        holder.nameFruit.setText(fruitBean.getName());
        //加载水果图片
        Glide.with(mContext).load(fruitBean.getImgId()).into(holder.imgFruit);
    }

    @Override
    public int getItemCount() {
        return fruitBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgFruit;
        TextView nameFruit;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imgFruit = (ImageView) itemView.findViewById(R.id.img_fruit);
            nameFruit = (TextView) itemView.findViewById(R.id.text_fruit_name);
        }
    }
}
