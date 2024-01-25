package com.example.bibliosgroup7;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {
        private Activity context;
        List<Service> services;

        public ServiceList(Activity context, List<Service> services) {
            super(context, R.layout.layout_account_list, services);//change layout name once implemented
            this.context = context;
            this.services = services;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.layout_service_list, null, true);//change layout name once implemented

            TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName); //change once we implement layout
            TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);//''

            Service service = services.get(position);
            textViewName.setText(service.getServiceName()); //to be edited later
            textViewPrice.setText(String.valueOf(service.getPrice()));//to be edited later
            return listViewItem;
        }
    }

