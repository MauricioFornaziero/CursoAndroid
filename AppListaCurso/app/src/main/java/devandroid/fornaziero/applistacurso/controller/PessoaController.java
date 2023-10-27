package devandroid.fornaziero.applistacurso.controller;

import android.content.SharedPreferences;

import devandroid.fornaziero.applistacurso.model.Pessoa;
import devandroid.fornaziero.applistacurso.view.MainActivity;

public class PessoaController {

    SharedPreferences preferences;
    SharedPreferences.Editor listavip;
    public static final String NOME_PREFERENCES = "pref_listavip";


    public PessoaController(MainActivity mainActivity) {
        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES,0);
        listavip = preferences.edit();
    }

    public void salvar(Pessoa pessoa) {
        listavip.putString("primeiroNome",pessoa.getPrimeiroNome());
        listavip.putString("sobrenome",pessoa.getSobreNome());
        listavip.putString("curso",pessoa.getCursoDesejado());
        listavip.putString("contato",pessoa.getTelefoneContato());
        listavip.apply();
    }
    public Pessoa buscar(Pessoa pessoa){
        pessoa.setPrimeiroNome(preferences.getString("primeiroNome","NA"));
        pessoa.setSobreNome(preferences.getString("curso","NA"));
        pessoa.setCursoDesejado(preferences.getString("sobrenome","NA"));
        pessoa.setTelefoneContato(preferences.getString("contato","NA"));
        return pessoa;
    }

    public void limpar(){
        listavip.clear();
        listavip.apply();



    }
}
