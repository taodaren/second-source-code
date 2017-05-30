package io.github.taodaren.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Dell on 2017/5/30.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<RecyclerBean> beanList;

    /**
     * 把展示的数据源传进来
     */
    public RecyclerAdapter(List<RecyclerBean> beanList) {
        //赋值给全局变量
        this.beanList = beanList;
    }

    /**
     * 创建 ViewHolder 实例
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_android, parent, false);
        //创建 ViewHolder 实例
        final ViewHolder holder = new ViewHolder(view);
        //设置点击事件
        holder.rlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                RecyclerBean bean = beanList.get(position);
                Toast.makeText(v.getContext(), "布局被点击" + bean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                RecyclerBean bean = beanList.get(position);
                Toast.makeText(v.getContext(), "图片被点击" + bean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    /**
     * 用于对 RecyclerView 子项数据进行赋值
     * 会在每个子项被滚到屏幕内的时候执行
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //得到当前的 RecyclerBean 实例
        RecyclerBean bean = beanList.get(position);
        //设置数据
        holder.imageView.setImageResource(bean.getImgId());
        holder.textView.setText(bean.getName());
    }

    /**
     * 用于告诉 RecyclerView 一共有多少子项
     *
     * @return 数据源长度
     */
    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //保存子项最外层布局的实例
        View rlView;
        ImageView imageView;
        TextView textView;

        /**
         * @param itemView RecyclerView 子项最外层布局
         */
        public ViewHolder(View itemView) {
            super(itemView);
            rlView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.image_android);
            textView = (TextView) itemView.findViewById(R.id.text_android);
        }
    }
}
