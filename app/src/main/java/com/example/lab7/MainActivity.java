package com.example.lab7;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //initiating a FrameLayout object
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        //boolean which assesses if device is a tablet or not
        boolean isTablet = frameLayout != null ? true : false;

        // main view and adapter
        Adapter adapter = new Adapter();
        ListView list = (ListView) findViewById(R.id.mainList);
        list.setAdapter(adapter);
        // asyncTask
        StarWarsChars sw = new StarWarsChars(adapter);
        sw.execute();


        list.setOnItemClickListener((parent, view, position, id) -> {
            String[] sublist = adapter.filedList.get(position);
            Bundle passedData = new Bundle();
            passedData.putString("name", sublist[0]);
            passedData.putString("height", sublist[1]);
            passedData.putString("mass", sublist[2]);

            if(isTablet){
                // Gets values from the intent

                // instantiates a new object
                DetailsFragment fragment = new DetailsFragment();

                // passes the bundled data
                fragment.setArguments( passedData );

                // begins a transaction
                getSupportFragmentManager().beginTransaction().replace((R.id.frameLayout),
                        fragment).commit();
            }else{
                // if it is a phone it will just go to a new activity
                Intent intent = new Intent(this, EmptyActivity.class);
                intent.putExtras(passedData);
                startActivity(intent);
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public class Adapter extends BaseAdapter{

        public ArrayList<String> swFields = new ArrayList<>();
        public ArrayList<String[]> filedList = new ArrayList<>();

        @Override
        public int getCount() {
            return swFields.size();
        }

        @Override
        public Object getItem(int position) {
            return swFields.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();

            View contentList = inflater.inflate(R.layout.content_list, parent, false);

            TextView content = contentList.findViewById(R.id.content);

            content.setText(getItem(position).toString());

            return contentList;
        }
    }
}
