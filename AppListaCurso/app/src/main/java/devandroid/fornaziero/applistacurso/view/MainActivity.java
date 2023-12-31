package devandroid.fornaziero.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import devandroid.fornaziero.applistacurso.R;
import devandroid.fornaziero.applistacurso.controller.PessoaController;
import devandroid.fornaziero.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {


    SharedPreferences preferences;
    public static final String NOME_PREFERENCES = "pref_listavip";

    Pessoa pessoa;

    Pessoa pessoaPreferences;
    PessoaController controller;

    String dadosPessoa;


    EditText editTextTextNome, editTextTextSobrenome , editTextTextCurso , editTextTextTelefone;

    Button buttonLimpar, buttonSalvar, buttonFinalizar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new PessoaController();

        preferences = getSharedPreferences(NOME_PREFERENCES,0);
        SharedPreferences.Editor listavip = preferences.edit();

        editTextTextNome = findViewById(R.id.editTextTextNome);
        editTextTextSobrenome = findViewById(R.id.editTextTextSobrenome);
        editTextTextCurso = findViewById(R.id.editTextTextCurso);
        editTextTextTelefone = findViewById(R.id.editTextTextTelefone);


        buttonLimpar = findViewById(R.id.buttonLimpar);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonFinalizar = findViewById(R.id.buttonFinalizar);



        pessoa = new Pessoa();

        pessoa.setPrimeiroNome(preferences.getString("primeiroNome",""));
        pessoa.setCursoDesejado(preferences.getString("curso",""));
        pessoa.setSobreNome(preferences.getString("sobrenome",""));
        pessoa.setTelefoneContato(preferences.getString("contato",""));

//        pessoa.setPrimeiroNome("Mauricio");
//        pessoa.setSobreNome("Fornaziero");
//        pessoa.setCursoDesejado("Java");
//        pessoa.setTelefoneContato("11-995866750");

        dadosPessoa = "Primeiro Nome: ";
        dadosPessoa += pessoa.getPrimeiroNome();
        dadosPessoa += "Sobrenome: ";


        Log.i("pooAndroid", pessoa.toString());

        editTextTextNome.setText(pessoa.getPrimeiroNome());
        editTextTextSobrenome.setText(pessoa.getSobreNome());
        editTextTextCurso.setText(pessoa.getCursoDesejado());
        editTextTextTelefone.setText(pessoa.getTelefoneContato());

        pessoaPreferences = new Pessoa();
        pessoaPreferences.setPrimeiroNome("PRef - Mauricio");
        pessoaPreferences.setSobreNome("PRef - Fornaziero");
        pessoaPreferences.setCursoDesejado("PRef - Java");
        pessoaPreferences.setTelefoneContato("PRef - 11-995866750");



        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextTextNome.setText("");
                editTextTextSobrenome.setText("");
                editTextTextCurso.setText("");
                editTextTextTelefone.setText("");

            }
        });

        buttonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Volte Sempre!",Toast.LENGTH_LONG).show();
                finish();
            }
        });


        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoa.setPrimeiroNome(editTextTextNome.getText().toString());
                Toast.makeText(MainActivity.this,"Salvo! "+pessoa.toString(),Toast.LENGTH_LONG).show();

                pessoaPreferences.setPrimeiroNome(pessoa.getPrimeiroNome());

                listavip.putString("primeiroNome",pessoaPreferences.getPrimeiroNome());
                listavip.putString("sobrenome",pessoaPreferences.getSobreNome());
                listavip.putString("curso",pessoaPreferences.getCursoDesejado());
                listavip.putString("contato",pessoaPreferences.getTelefoneContato());
                listavip.apply();


                controller.salvar(pessoa);
            }
        });



    }
}