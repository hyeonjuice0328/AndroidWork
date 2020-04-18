package com.lec.android.a008_recycler;

// 어댑터 객체 정의
// 데이터(phonebook) 를 받아서 각 item 별로 View 를 생성

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PhonebookAdapter extends RecyclerView.Adapter<PhonebookAdapter.ViewHolder> {
    // Adapter는 리스트에서 다룰 데이터가 필요하다
    // Adapter가 데이터에 연결되어야 하는 것은 사실이나, 데이터를 Adapter를 직접 다룰지
    // 아니면 별도의 데이터 관리는 따로 하는 구조로 만들지는 선택의 몫
    // 본 예제에서는 Adapter 안에 직접 데이터를 다루어보겠습니다.

    List<Phonebook> items = new ArrayList<Phonebook>();

    static PhonebookAdapter adapter; // ViewHolder 에서 사용할건데 여기도 Static 이기 때문에 Static 으로 선언

    // Adapter 생성자
    public PhonebookAdapter() {this.adapter = this;}

    // onCreateViewHolder() : ViewHolder 가 생성될때 호출됨
    // 각 item 을 위해 정의한 레이아웃(ex:XML) 으로 View 객체를 만들어 줍니다.
    // 이들 View객체를 새로 만들 ViewHolder 에 담아 리턴.
    //
    //  'View  타입' 을 위한 정수값이 매개변수로 넘겨진 --> viewType
    //    이를 통해 아이템별로 View를 다양하게 표현 가능.  (ListView 에는 없던 개선점)
    //    예를들면, 각각의 'View 타입' 별로 따로따로 XML레이아웃을 달리 하여 보여줄수 있는 겁니다.
    //    * 그러나, 일반적으로는 한가지만 운용함.*
    //
    //  매개변수로 전달된 ViewGroup 객체는 각 아이템을 위한 객체
    //  이로부터 Context 객체를 뽑아내어 Layout inflation 을 해야 한다.

    // inflator : xml 파일(그냥 텍스트 파일) 로부터 실제 뷰 오브젝트를 만드는 것 .

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //주어진 ViewGroup 으로부터 LayoutInflater 호출
        LayoutInflater inf = LayoutInflater.from(parent.getContext()); // 뽑아낸다.
        View itemView = inf.inflate(R.layout.item, parent, false);
        //준비된 레이아웃(xml)으로부터 View 를 만들어 ViewGroup 에 붙이고 만들어진 View 를 리턴

        // 위에서 만들어진 새로운 View 객체를 ViewHolder 에 담아 리턴
        return new ViewHolder(itemView);
    }

    // onBindViewHolder() : ViewHolder 가 '재사용' 될때 호출됨
    // View 객체는 그대로 기존것을 사용 (이것이 재사용!) 하고 데이터만 바꾸어 주면 됨.
    //  이전에 이미 만들어진. 재활용할수 있는 ViewHolder 객체  와
    //  리스트 상에 몇번째 데이터인지에 대한 정보 (position) 가 넘어온다
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // position 번째 phonebook 내용을 끌고 들어오면 된다.
        Phonebook item = items.get(position);
        holder.setItem(item);
    }

    // getItemCount() : 어댑터에서 다루는 현시점 아이템(데이터)의 개수
    //   Selection Widget 에서 수시로 getItemCount() 를 호출하여 뷰를 업데이트 한다

    @Override
    public int getItemCount() {
        return items.size(); // List<> 의 size()
    }

    // nested class (static inner) 로 ViewHolder 클래스 정의 (RecyclerView 는 ViewHolder 가 필요)
    static class ViewHolder extends RecyclerView.ViewHolder {

        // ViewHolder 에 담긴 각각의 View 들을 담을 변수
        ImageView ivPhoto;
        TextView tvName, tvPhone, tvEmail;
        ImageButton btnDelItem;
        Switch swOnOff;

        // 생성자 필수
        public ViewHolder(@NonNull View itemView) { // item.xml 의 레이아웃의 전체 뷰가 넘어온다.
            super(itemView);

            // View 객체 가져오기
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            // 이벤트 추가하기 위해..
            btnDelItem = itemView.findViewById(R.id.btnDelItem);
            swOnOff = itemView.findViewById(R.id.swOnOff);

            //리스너
            //switch 누르면 전화번호 , 이메일 숨기기 / 보이기
            swOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        tvPhone.setVisibility(View.INVISIBLE);
                        tvEmail.setVisibility(View.INVISIBLE);
                    } else {
                        tvPhone.setVisibility(View.VISIBLE);
                        tvEmail.setVisibility(View.VISIBLE);
                    }
                }
            });

            // 삭제버튼 누르면 item 삭제 되게 하기
            btnDelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // position 정보가 중요하다.
                    // 데이터가 지워지는지 확인하려면 데이터를 관리하는 Adapter 객체가 필요하다.
                    adapter.removeItem(getAdapterPosition()); // 데이터 삭제  -- > 여기까지만 하면 죽는다..
                    // 데이터 변경된 것이(삭제, 수정, 추가) 리스트에 즉 adapter에 반영이 되어야 정상 동작한다.
                    adapter.notifyDataSetChanged(); // 그래서 마지막에 이 작업을 꼭 해주어야 한다!!
                }
            });

            // 클릭 리스너 장착하기 itemView 에 !!
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition(); // 이 리스너가 장착된 아이템의 리스트상의 포지션값
                    //Toast.makeText(v.getContext(), "position:" + position, Toast.LENGTH_SHORT).show();

                    // 아이템을 클릭하면 해당 세부 정도 액티비티고 넘겨주기
                    Intent intent = new Intent(v.getContext(), PhonebookDetail.class);
                    intent.putExtra("pb", adapter.getItem(position));

                    v.getContext().startActivity(intent);
                }
            });

        } // end 생성자

        // Phonebook 데이터를 받아서 멤버변수 세팅
        public void setItem(Phonebook item) {
            ivPhoto.setImageResource(item.getPhoto());
            tvName.setText(item.getName());
            tvPhone.setText(item.getPhone());
            tvEmail.setText(item.getEmail());
        }

    } // end class ViewHolder

    // 데이터를 다루기 위한 메소드들
    // ArrayList 의 메소드들 사용
    public void addItem(Phonebook item) {  items.add(item); }
    public void addItem(int position, Phonebook item) {   items.add(position, item);}
    public void setItems(ArrayList<Phonebook> items) {   this.items = items;}
    public Phonebook getItem(int position) {   return items.get(position);}
    public void setItem(int position, Phonebook item) {   items.set(position, item); }
    public void removeItem(int position){ items.remove(position); }


} // end Adapter
