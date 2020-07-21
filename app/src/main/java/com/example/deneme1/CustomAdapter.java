package com.example.deneme1;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements Filterable {

    ArrayList<siparis> siparisArrayListOrg = new ArrayList<>(); // Her bir satır için     arayüz seçilir.
    ArrayList<siparis> modelArrayList; //verielerin çekileceği ve çalışacağı constructer
    LayoutInflater layoutInflater;
    Context context;
    public List<String> siparisler;


    public CustomAdapter(ArrayList<siparis> modelArrayList, Context context) {
                siparisArrayListOrg =modelArrayList;
        this.modelArrayList = new ArrayList<>(siparisArrayListOrg);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        layoutInflater= LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.row_list, viewGroup, false);// satır dizaynını yaptığım layout
        ViewHolder vh = new ViewHolder(v); // row_list i çağırıp istediği şekilde kullanıyor.
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.txt_yer_adi.setText(modelArrayList.get(i).getTxt_yer_adi());
//        viewHolder.cikisTarihi.(modelArrayList.get(i).getCikisTarihi());
        viewHolder.min_fiyat.setText(modelArrayList.get(i).getMin_fiyat());

    }

    

    @Override
    public int getItemCount() {
       return modelArrayList.size(); // eklenen veri kadar döndürülecek.
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter  exampleFilter=new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List filteredList=new ArrayList();
            if(constraint==null || constraint.length()==0){
                filteredList.addAll(siparisArrayListOrg);
            }else{
                String key=constraint.toString().toLowerCase().trim();
                for (siparis item: siparisArrayListOrg) {
                   if(item.txt_yer_adi.toLowerCase().contains(key)){
                       filteredList.add(item);
                   }
                }
            }
            FilterResults result=new FilterResults();
            result.values=filteredList;

            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            modelArrayList.clear();
            modelArrayList.addAll((List<siparis>)results.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends  RecyclerView.ViewHolder
    {
        TextView txt_yer_adi,min_fiyat, cikisTarihi;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_yer_adi = itemView.findViewById(R.id.txt_yer_adi);
            cikisTarihi = itemView.findViewById(R.id.cikisTarihi);
            min_fiyat = itemView.findViewById(R.id.min_fiyat);
        }
    }
    public void updateList (List<String> newList)
    {

        siparisler= new ArrayList<>();
        siparisler.addAll(newList);
        notifyDataSetChanged();

    }
}
