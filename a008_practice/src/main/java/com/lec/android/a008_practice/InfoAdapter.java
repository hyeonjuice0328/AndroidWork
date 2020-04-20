package com.lec.android.a008_practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
// 정보와
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    List<Information> items = new ArrayList<Information>();

    static InfoAdapter adapter;
    //Adapter 생성자
    public InfoAdapter() {this.adapter = this;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Information item = items.get(position); // List<Information> items 의 get()사용하기
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {return items.size();}

    //ViewHolder class 정의
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvAge, tvAdd;
        ImageButton btnDelItem;

        //생성자 필수
        public ViewHolder(@NonNull View itemView) { //item 레이아웃의 View 객체가 전달
            super(itemView);

            // View 객체 가져오기 (item.xml)
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAdd = itemView.findViewById(R.id.tvAdd);

            btnDelItem = itemView.findViewById(R.id.btnDelItem);

            // 삭제 버튼 누르면 item 삭제
            btnDelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.removeItem(getAdapterPosition());
                    adapter.notifyDataSetChanged();
                }
            });

        } // end ViewHolder 생성자

        // 입력한 정보 set 하기
        public void setItem(Information item) {
            tvName.setText(item.getName());
            tvAge.setText(item.getAge());
            tvAdd.setText(item.getAddress());
        }
    } // end ViewHolder class

    // 추가하는 동작, 지우는 동작
    public void addItem(Information item) { items.add(item);}
    public void removeItem(int position) { items.remove(position);}

} // end Adapter
