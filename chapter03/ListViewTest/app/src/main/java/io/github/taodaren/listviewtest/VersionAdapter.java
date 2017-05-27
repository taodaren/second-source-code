package io.github.taodaren.listviewtest;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 2017/5/27.
 */

public class VersionAdapter extends ArrayAdapter<AndroidVersionHistory> {
    private int resourceId;

    /**
     * @param context  上下文
     * @param resource ListView 子项布局 id
     * @param objects  数据
     */
    public VersionAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<AndroidVersionHistory> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        //获取当前项的 version 实例
        AndroidVersionHistory version = getItem(position);

        if (convertView == null) {
            /** 加载传入的布局
             * inflate() 参数作用
             * @param resourceId 要加载的布局文件 id
             * @param parent     给加载好的布局再添加一个父布局
             * @param false      让在父布局声明的 layout 生效，但不为这个 View 添加父布局
             */
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }

        ImageView imageVersion = (ImageView) view.findViewById(R.id.image_version);
        TextView textVersion = (TextView) view.findViewById(R.id.text_version);
        imageVersion.setImageResource(version.getImgId());
        textVersion.setText(version.getName());
        return view;
    }
}
