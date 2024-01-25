package com.example.bibliosgroup7;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceRequestList extends ArrayAdapter<ServiceRequest> {
    private Activity context;
    List<ServiceRequest> srequest;

    public ServiceRequestList(Activity context, List<ServiceRequest> srequest) {
        super(context, R.layout.layout_srequest_list, srequest);
        this.context = context;
        this.srequest = srequest;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_srequest_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);


        ServiceRequest request = srequest.get(position);
        textViewName.setText(request.getServiceName());
        textViewPrice.setText(Boolean.toString(request.getApproved()));
        return listViewItem;
    }
}
