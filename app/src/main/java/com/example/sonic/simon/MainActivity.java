package com.example.sonic.simon;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.BitSet;

public class MainActivity extends AppCompatActivity {
    ColorSimon coloresImg[];
    TextView puntuacion,estado,tiempo;
    ArrayList<String> secuencia = new ArrayList<String>();
    String color[];
    int nivel;
    boolean activo;
    Context a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        coloresImg = new ColorSimon[4];
        coloresImg[0] = new ColorSimon((ImageView)findViewById(R.id.rojo),R.mipmap.rojooff,R.mipmap.rojoon);
        coloresImg[1] = new ColorSimon((ImageView)findViewById(R.id.azul),R.mipmap.azuloff,R.mipmap.azulon);
        coloresImg[2] = new ColorSimon((ImageView)findViewById(R.id.verde),R.mipmap.verdeoff,R.mipmap.verdeon);
        coloresImg[3] = new ColorSimon((ImageView)findViewById(R.id.amarillo),R.mipmap.amarillooff,R.mipmap.amarilloon);
        puntuacion = (TextView)findViewById(R.id.puntuacion);
        estado = (TextView)findViewById(R.id.estado);
        tiempo = (TextView)findViewById(R.id.tiempo);
        color = new String[4];
        color[0]="rojo";
        color[1]="azul";
        color[2]="verde";
        color[3]="amarillo";
        activo = false;
        a = this;
        coloresImg[0].elemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                juego("rojo");
            }
        });
        coloresImg[1].elemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                juego("azul");
            }
        });
        coloresImg[2].elemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                juego("verde");
            }
        });
        coloresImg[3].elemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                juego("amarillo");
            }
        });

    }
    private void juego(String color){
        if(activo){
            activo = false;

        }else{
            nivel =3;
            activo = true;

            llenarLista();
        }
        estado.setText(activo+"");
    }
    private void llenarLista(){
        int random = 0;
        for(int i =0;i<nivel;i++){
            random = (int)(Math.random() * 4);
            secuencia.add(color[random]);
            coloresImg[random].encender();
            coloresImg[random].apagar();
        }
    }
    private class ColorSimon {
        public ImageView elemento;
        public int apagado, encendido;

        public ColorSimon(ImageView elemento, int apagado, int encendido) {
            this.elemento = elemento;
            this.apagado = apagado;
            this.encendido = encendido;
        }
        public void encender()  {
            elemento.setImageResource(encendido);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Toast.makeText(a,"ERROR SLEEP",Toast.LENGTH_LONG);
            }
        }

        public void apagar(){
            elemento.setImageResource(apagado);

        }
    }
}
