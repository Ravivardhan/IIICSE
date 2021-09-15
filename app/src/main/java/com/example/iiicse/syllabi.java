package com.example.iiicse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class syllabi extends AppCompatActivity {
    ListView ulist;
    TextView unit;

    //Syllabus unit wise
    String CN[][]=new String[][]
            {
                    {"Introduction","Applications of CN","Network H/W","Network S/W","Reference Models-OSI/ISO-TCP/IP","Guided Transmission media","physical layer","unguided T.M","PSTN","Multiplexing","Types switching Networks","Trunks","Network security","Mobile adopt Network","Wireless Sensor Network"},
                    {"Data Link Layer","DDL design issues","elementory data link protocol","sliding window Protocol","functionalities","Medium Access Control Sublayer[MAC]","types of protocols","ethernet types","wireless LANs","Protocol Stack","functionality of physical layer","frame structure of LANs"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
            };
    String LS[][]=new String[][]
            {
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
            };
    String DWDM[][]=new String[][]
            {
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
            };
    String PPL[][]=new String[][]
            {
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
            };


    String USP[][]=new String[][]
            {
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
            };
    String OOAD[][]=new String[][]
            {
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
                    {"Official syllabus not yet released"},
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabi);

        ulist=findViewById(R.id.unitlist);
        //unit=findViewById(R.id.unit);


        Intent i=getIntent();
        Bundle b=i.getExtras();
        Toast.makeText(syllabi.this, String.valueOf(b.get("subject")), Toast.LENGTH_SHORT).show();
        Toast.makeText(syllabi.this,String.valueOf(b.get("unit")),Toast.LENGTH_SHORT).show();

        //unit.setText(String.valueOf(b.get("unit")));
        String subject=String.valueOf(b.get("subject"));
        String un=String.valueOf(b.get("unit"));
        int uno=0;
        System.out.println(subject);

        if(un.equals("Unit-I")){
            uno=0;
        }
        if(un.equals("Unit-II")){
            uno=1;
        }
        if(un.equals("Unit-III"))
        {
            uno=2;
        }
        if(un.equals("Unit-IV"))
        {
            uno=3;
        }
        if(un.equals("Unit-V"))
        {
            uno=4;
        }
        System.out.println(uno);
        String dummy[]=new String[]{};

        if(subject.equals("CN")) {
            dummy=CN[uno];
        }
        if(subject.equals("LS")) {
            dummy=LS[uno];
        }
        if(subject.equals("PPL")) {
           dummy=PPL[uno];
        }
        if(subject.equals("DWDM")) {
           dummy=DWDM[uno];
        }
        if(subject.equals("USP")){
            dummy=USP[uno];
        }
        if(subject.equals("OOAD")) {
            dummy=OOAD[uno];
        }
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dummy);

        ulist.setAdapter(adapter3);

    }
}