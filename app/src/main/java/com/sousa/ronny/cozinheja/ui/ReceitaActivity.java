package com.sousa.ronny.cozinheja.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sousa.ronny.cozinheja.adapters.AdapterItensReceita;
import com.sousa.ronny.cozinheja.formaters.AsyncTaskLoadImage;
import com.sousa.ronny.cozinheja.R;
import com.sousa.ronny.cozinheja.model.Receita;

public class ReceitaActivity extends AppCompatActivity {
    private Receita receita;

    TextView txtNome;
    TextView txtPreparo;
    ListView lstItensReceita;
    ImageView imgReceita;
    LinearLayout linhaIngrediente;
    ScrollView scrollReceita;
    ImageView imgExpandir;
    int visao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);
        receita = (Receita) getIntent().getSerializableExtra("receita");
        visao =0;

        //Preenche campos
        txtNome = findViewById(R.id.txtNome);
        txtPreparo = findViewById(R.id.txtPreparo);
        lstItensReceita = findViewById(R.id.lstItensReceita);
        imgReceita = findViewById(R.id.imgReceita);
        linhaIngrediente = findViewById(R.id.linhaIngrediente);
        scrollReceita = findViewById(R.id.scrollReceita);
        imgExpandir = findViewById(R.id.imgExpandir);


        //
        if(receita.getFoto()!=null && receita.getFoto()!="") {
            AsyncTaskLoadImage taskLoadImage = new AsyncTaskLoadImage(imgReceita);
            taskLoadImage.execute(receita.getFoto());
        }
        txtNome.setText(receita.getNome());
        txtPreparo.setText(receita.getPreparo());

        AdapterItensReceita adapter=new AdapterItensReceita(this,receita.getItensReceita());
        lstItensReceita.setAdapter(adapter);

        linhaIngrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visao==0)
                {
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,0);
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,63);
                    visao =1;
                    scrollReceita.setLayoutParams(params1);
                    lstItensReceita.setLayoutParams(params2);
                    imgExpandir.setImageResource(R.drawable.ic_expand_more_black_24dp);
                }
                else if(visao==1)
                {
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,63);
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,0);
                    visao =2;
                    scrollReceita.setLayoutParams(params1);
                    lstItensReceita.setLayoutParams(params2);
                    imgExpandir.setImageResource(R.drawable.ic_unfold_more_black_24dp);
                }
                else if(visao ==2)
                {
                    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,35);
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0,28);
                    visao =0;
                    scrollReceita.setLayoutParams(params1);
                    lstItensReceita.setLayoutParams(params2);
                    imgExpandir.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }


            }
        });



    }
}
