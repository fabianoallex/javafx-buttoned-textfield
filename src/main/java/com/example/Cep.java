package com.example;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Cep {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public String getCep() {
        return cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public String getComplemento() {
        return complemento;
    }
    public String getBairro() {
        return bairro;
    }
    public String getLocalidade() {
        return localidade;
    }
    public String getUf() {
        return uf;
    }
    public String getIbge() {
        return ibge;
    }
    public String getGia() {
        return gia;
    }
    public String getDdd() {
        return ddd;
    }
    public String getSiafi() {
        return siafi;
    }

    public final static String CEP_CEP = "cep";
    public final static String CEP_LOGRADOURO = "logradouro";
    public final static String CEP_COMPLEMENTO = "complemento";
    public final static String CEP_BAIRRO = "bairro";
    public final static String CEP_LOCALIDADE = "localidade";
    public final static String CEP_UF = "uf";
    public final static String CEP_IBGE = "ibge";
    public final static String CEP_GIA = "gia";
    public final static String CEP_DDD = "ddd";
    public final static String CEP_SIAFI = "siafi";

    private Cep() {}

    private static Object ifHasKeyGet(JSONObject json, String key, Object elseReturn) {
        return json.has(key) ? json.get(key).toString() : elseReturn;
    }

    private static Object ifHasKeyGet(JSONObject json, String key) {
        return ifHasKeyGet(json, key, "");
    }

    public static Cep createCep(String cep) {
        String webService = "http://viacep.com.br/ws/";
        int codigoSucesso = 200;
        String urlParaChamada = webService + cep + "/json";

        HttpURLConnection conexao = null;
        try {
            URL url = new URL(urlParaChamada);
            conexao = (HttpURLConnection) url.openConnection();
            if (conexao.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            String linha, jsonEmString = "";
            while ((linha = resposta.readLine()) != null) {
                jsonEmString += linha;
            }

            JSONObject json = new JSONObject(jsonEmString);

            Cep cepObject = new Cep();

            cepObject.cep = ifHasKeyGet(json, CEP_CEP).toString();
            cepObject.logradouro = ifHasKeyGet(json, CEP_LOGRADOURO).toString();
            cepObject.complemento = ifHasKeyGet(json, CEP_COMPLEMENTO).toString();
            cepObject.bairro = ifHasKeyGet(json, CEP_BAIRRO).toString();
            cepObject.localidade = ifHasKeyGet(json, CEP_LOCALIDADE).toString();
            cepObject.uf = ifHasKeyGet(json, CEP_UF).toString();
            cepObject.ibge = ifHasKeyGet(json, CEP_IBGE).toString();
            cepObject.gia = ifHasKeyGet(json, CEP_GIA).toString();
            cepObject.ddd = ifHasKeyGet(json, CEP_DDD).toString();
            cepObject.siafi = ifHasKeyGet(json, CEP_SIAFI).toString();

            return cepObject;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
