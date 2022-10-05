package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int cont = 0;
    private boolean cancelar = false;
    private EditText nombre;
    private EditText apellido;
    private EditText telefono;

    private Button izquierda;
    private Button derecha;
    private Button guardar;
    private Button editar;

    private List<Persona> personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        telefono = findViewById(R.id.telefono);

        izquierda = findViewById(R.id.izquierda_button);
        derecha = findViewById(R.id.derecha_button);
        guardar = findViewById(R.id.guardar_button);
        editar = findViewById(R.id.editar_button);

        personas = new LinkedList<>();

        personas.add(new Persona("Alfonso", "García", "653789356"));
        personas.add(new Persona("Laura", "Márquez", "637845278"));
        personas.add(new Persona("Domingo", "Pérez", "649876256"));

        nombre.setText(personas.get(0).getNombre());
        apellido.setText(personas.get(0).getApellido());
        telefono.setText(personas.get(0).getTelefono());
        nombre.setEnabled(false);
        apellido.setEnabled(false);
        telefono.setEnabled(false);
        izquierda.setEnabled(false);
        guardar.setEnabled(false);

        derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar.setText("Editar");
                cancelar = false;
                cont++;
                if (cont == personas.size()) {
                    editar.setEnabled(false);
                }
                else {editar.setEnabled(true);}
                if(cont == personas.size()) {
                    guardar.setEnabled(true);
                }
                else {guardar.setEnabled(false);}
                if(cont > 0) {
                    izquierda.setEnabled(true);
                }
                if(cont == personas.size()) {
                    derecha.setEnabled(false);
                    nombre.setText("");
                    apellido.setText("");
                    telefono.setText("");
                    nombre.setEnabled(true);
                    apellido.setEnabled(true);
                    telefono.setEnabled(true);
                }
                else {
                    actualizar();
                    nombre.setEnabled(false);
                    apellido.setEnabled(false);
                    telefono.setEnabled(false);
                }
            }
        });

        derecha.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editar.setText("Editar");
                cancelar = false;
                cont = personas.size();
                izquierda.setEnabled(true);
                derecha.setEnabled(false);
                nombre.setText("");
                apellido.setText("");
                telefono.setText("");
                nombre.setEnabled(true);
                apellido.setEnabled(true);
                telefono.setEnabled(true);
                guardar.setEnabled(false);
                editar.setEnabled(false);
                return true;
            }
        });

        izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar.setText("Editar");
                cancelar = false;
                if (cont > 0) {
                    cont--;
                    if (cont == personas.size()) {
                        editar.setEnabled(false);
                        guardar.setEnabled(true);
                    }
                    else {
                        nombre.setEnabled(false);
                        apellido.setEnabled(false);
                        telefono.setEnabled(false);
                    }
                    if(cont < personas.size()) {
                        derecha.setEnabled(true);
                        editar.setEnabled(true);
                    }
                    else {guardar.setEnabled(false);}
                    actualizar();
                    if(cont == 0) {
                        izquierda.setEnabled(false);
                    }
                }

            }
        });

        izquierda.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editar.setText("Editar");
                cancelar = false;
                cont = 0;
                actualizar();
                derecha.setEnabled(true);
                izquierda.setEnabled(false);
                nombre.setEnabled(false);
                apellido.setEnabled(false);
                telefono.setEnabled(false);
                return true;
            }
        });

        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(cont < personas.size()) {
                    if (!nombre.getText().toString().equals(personas.get(cont).getNombre())) {
                        guardar.setEnabled(true);
                    }
                    else {
                        guardar.setEnabled(false);
                    }
                }
                else {
                    if(!nombre.getText().toString().equals("")) {
                        guardar.setEnabled(true);
                    }
                    else {
                        guardar.setEnabled(false);
                    }
                }
            }
        });

        apellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(cont < personas.size()) {
                    if (!apellido.getText().toString().equals(personas.get(cont).getApellido())) {
                        guardar.setEnabled(true);
                    }
                }
                else {
                    if(!nombre.getText().equals("")) {
                        guardar.setEnabled(true);
                    }
                }
            }
        });

        telefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(cont < personas.size()) {
                    if (!telefono.getText().toString().equals(personas.get(cont).getTelefono())) {
                        guardar.setEnabled(true);
                    }
                }
                else {
                    if(!nombre.getText().equals("")) {
                        guardar.setEnabled(true);
                    }
                }
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cont == personas.size()) {
                    Persona p = new Persona(nombre.getText().toString(),
                            apellido.getText().toString(), telefono.getText().toString());
                    personas.add(p);
                    actualizar();
                }
                else{
                    personas.set(cont, new Persona(nombre.getText().toString(), apellido.getText().toString(), telefono.getText().toString()));
                    actualizar();
                }
                guardar.setEnabled(false);
                nombre.setEnabled(false);
                apellido.setEnabled(false);
                telefono.setEnabled(false);
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cancelar == false) {
                    nombre.setEnabled(true);
                    apellido.setEnabled(true);
                    telefono.setEnabled(true);
                    editar.setText("CANCELAR");
                    cancelar = true;
                }
                else {
                    nombre.setText(personas.get(cont).getNombre());
                    nombre.setEnabled(false);
                    apellido.setText(personas.get(cont).getApellido());
                    apellido.setEnabled(false);
                    telefono.setText(personas.get(cont).getTelefono());
                    telefono.setEnabled(false);
                    editar.setText("EDITAR");
                    cancelar = false;
                }
            }
        });
    }

    private void actualizar() {
        nombre.setText(personas.get(cont).getNombre());
        apellido.setText(personas.get(cont).getApellido());
        telefono.setText(personas.get(cont).getTelefono());
    }

}