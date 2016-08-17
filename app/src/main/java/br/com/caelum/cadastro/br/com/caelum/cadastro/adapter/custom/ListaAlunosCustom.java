package br.com.caelum.cadastro.br.com.caelum.cadastro.adapter.custom;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.bean.Aluno;

/**
 * Created by android6384 on 16/08/16.
 */
public class ListaAlunosCustom extends BaseAdapter {


    private Activity activity;
    private List<Aluno> listaAluno;

    public ListaAlunosCustom(Activity activity, List<Aluno> listaAluno) {
        this.activity = activity;
        this.listaAluno = listaAluno;


    }


    @Override
    public int getCount() {
        return this.listaAluno.size();
    }

    @Override
    public Aluno getItem(int i) {
        return this.listaAluno.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup parent) {

        View viewXML = activity.getLayoutInflater().inflate(R.layout.listaalunocustom,parent,false);

        TextView textView = (TextView) viewXML.findViewById(R.id.txtListaNome);
        textView.setText(listaAluno.get(posicao).getNome().toString());

        ImageView imageView = (ImageView) viewXML.findViewById(R.id.imgFotoLista);
        Bitmap bitmap = null;

        if(listaAluno.get(posicao).getCaminhoFoto() == null){
            bitmap = BitmapFactory.decodeResource(activity.getResources(),R.drawable.ic_no_image);

        }else{
            bitmap = BitmapFactory.decodeFile(listaAluno.get(posicao).getCaminhoFoto())
                     .createScaledBitmap(bitmap,100,100,true);
        }

        imageView.setImageBitmap(bitmap);

        return imageView;
    }
}
