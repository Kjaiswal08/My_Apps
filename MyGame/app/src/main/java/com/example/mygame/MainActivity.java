package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer=1;
    int gamestate[]={2,2,2,2,2,2,2,2,2};
    //State meaning:-0=X ,1=O,2=blank
    int win[][]={{0,1,2},{3,4,5},
            {6,7,8},{0,3,6},{1,4,7},
            {2,5,8},{0,4,8},{2,4,6}};
    boolean active=true;
    public void gamereset(View view)
    {
        active=true;
        activeplayer=1;
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView29)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView28)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView27)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView26)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView25)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView24)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView23)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView22)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView21)).setImageResource(0);
        TextView status=findViewById(R.id.Status);
        status.setText("X turn to play");
    }
    public void Playerclick(View view)
    {
        ImageView img=(ImageView) view;
        int tappedimage=Integer.parseInt(img.getTag().toString());
        TextView status=findViewById(R.id.Status);
        if(!active)
        {   gamereset(view);}
        if(gamestate[tappedimage]==2 && active) {
            gamestate[tappedimage] = activeplayer;

            img.setTranslationY(-1000f);
            if (activeplayer == 1) {
                img.setImageResource((R.drawable.oss));
                activeplayer = 0;
                status.setText("X turn to play");
                img.animate().translationYBy(1000f).setDuration(300);
            } else {
                img.setImageResource((R.drawable.xss));
                activeplayer = 1;
                status.setText("O turn to play");
                img.animate().translationYBy(1000f).setDuration(300);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] wins:win)
        {
            if(gamestate[wins[0]]==gamestate[wins[1]]&&gamestate[wins[2]]==gamestate[wins[1]]&& gamestate[wins[0]]!=2)
            {
                String winner;
                active=false;
                if(gamestate[wins[0]]==0)
                    winner="X has won";
                else
                    winner="O has won";
                status.setText(winner);
            }
        }
        int f=0;
        for(int x:gamestate)
        {if(x==2) {f=1;break;}}
        if(f==0)
        {
            status.setText("It's A draw");active=false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}