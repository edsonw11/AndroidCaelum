package br.com.caelum.cadastro.thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.caelum.cadastro.webclient.Webclient;

/**
 * Created by android6384 on 17/08/16.
 */

public class EnviarNotasAlunosTask extends AsyncTask<Void, Integer, String> {

    private final Context ctx;
    private final String json;
    private ProgressDialog progress;

    public EnviarNotasAlunosTask(Context ctx, String json){
           this.ctx = ctx;
           this.json = json;

       }
    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx,"calculando notas","Aguarde...",true,true);
    }

    @Override
    protected void onPostExecute(String resposta) {
        Toast.makeText(ctx, resposta, Toast.LENGTH_LONG).show();
        progress.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Webclient webclient = new Webclient();

        String resposta = webclient.post(json);

        return resposta;

    }


}
