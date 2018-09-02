package com.sousa.ronny.cozinheja.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);
        receita = (Receita) getIntent().getSerializableExtra("receita");

        //Preenche campos
        txtNome = findViewById(R.id.txtNome);
        txtPreparo = findViewById(R.id.txtPreparo);
        lstItensReceita = findViewById(R.id.lstItensReceita);
        imgReceita = findViewById(R.id.imgReceita);

        //
        if(receita.getFoto()!=null && receita.getFoto()!="") {
            AsyncTaskLoadImage taskLoadImage = new AsyncTaskLoadImage(imgReceita);
            taskLoadImage.execute(receita.getFoto());
        }
        txtNome.setText(receita.getNome());
        txtPreparo.setText(receita.getPreparo());
        AdapterItensReceita adapter=new AdapterItensReceita(this,receita.getItensReceita());
        lstItensReceita.setAdapter(adapter);



    }
}
