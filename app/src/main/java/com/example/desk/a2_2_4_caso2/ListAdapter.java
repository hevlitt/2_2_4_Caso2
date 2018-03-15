package com.example.desk.a2_2_4_caso2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter{
    Context context;
    String[] ini;
    String[] pri;
    String[] sec;
    LayoutInflater inflater;

    public ListAdapter(Context context, String[] pri,String[] sec){
        this.context=context;
        this.pri=pri;
        this.sec=sec;
        ini=new String[getCount()];
        getIni();
    }

    public void getIni(){
        for (int i=0;i<pri.length;i++){
            ini[i]=pri[i].substring(0,1).toUpperCase();
        }
    }

    @Override
    public int getCount() {
        return pri.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Declare Variables
        TextView inicial;
        TextView principal;
        TextView secundario;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.item, viewGroup, false);

        // Locate the TextViews in listview_item.xml
        //inicial = (TextView) itemView.findViewById(R.id.ini);
        principal = (TextView) itemView.findViewById(R.id.principal);
        secundario = (TextView) itemView.findViewById(R.id.secundario);

        // Capture position and set to the TextViews
       // inicial.setText(ini[i]);
        principal.setText(pri[i]);
        secundario.append(sec[i]);

        return itemView;
    }
}
