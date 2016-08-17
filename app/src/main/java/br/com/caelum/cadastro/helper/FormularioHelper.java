package br.com.caelum.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.bean.Aluno;

/**
 * Created by android6384 on 11/08/16.
 */
public class FormularioHelper {

    private ImageView imgFoto;
    private Button    btnFoto;
    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEndereco;
    private EditText edtSite;
    private RatingBar ratingNota;
    private Aluno aluno;


    public FormularioHelper(FormularioActivity formularioActivity) {

        edtNome     = (EditText) formularioActivity.findViewById(R.id.nome);
        edtTelefone = (EditText) formularioActivity.findViewById(R.id.telefone);
        edtEndereco = (EditText) formularioActivity.findViewById(R.id.endereco);
        edtSite     = (EditText) formularioActivity.findViewById(R.id.site);
        ratingNota  = (RatingBar)formularioActivity.findViewById(R.id.nota);
        imgFoto     = (ImageView) formularioActivity.findViewById(R.id.formulario_foto);
        btnFoto     = (Button) formularioActivity.findViewById(R.id.botao_foto);


    }

    public Aluno buidl(){

        getAluno().setNome(edtNome.getText().toString());
        getAluno().setTelefone(edtTelefone.getText().toString());
        getAluno().setEndereco(edtEndereco.getText().toString());
        getAluno().setSite(edtSite.getText().toString());
        getAluno().setNota(ratingNota.getProgress());


        return getAluno();

    }
    public void doPopularAluno(Aluno aluno){

        edtNome.setText(aluno.getNome());
        edtTelefone.setText(aluno.getTelefone());
        edtEndereco.setText(aluno.getEndereco());
        edtSite.setText(aluno.getSite());
        ratingNota.setProgress(aluno.getNota());
        setAluno(aluno);


    }

    public EditText getEdtNome() {
        return edtNome;
    }

    public void setEdtNome(EditText edtNome) {
        this.edtNome = edtNome;
    }

    public EditText getEdtTelefone() {
        return edtTelefone;
    }

    public void setEdtTelefone(EditText edtTelefone) {
        this.edtTelefone = edtTelefone;
    }

    public EditText getEdtEndereco() {
        return edtEndereco;
    }

    public void setEdtEndereco(EditText edtEndereco) {
        this.edtEndereco = edtEndereco;
    }

    public EditText getEdtSite() {
        return edtSite;
    }

    public void setEdtSite(EditText edtSite) {
        this.edtSite = edtSite;
    }

    public Aluno getAluno() {
        if(aluno == null)
            aluno = new Aluno();
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public RatingBar getRatingNota() {
        return ratingNota;
    }

    public void setRatingNota(RatingBar ratingNota) {
        this.ratingNota = ratingNota;
    }


    public ImageView getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(ImageView imgFoto) {
        this.imgFoto = imgFoto;
    }

    public Button getBtnFoto() {
        return btnFoto;
    }

    public void setBtnFoto(Button btnFoto) {
        this.btnFoto = btnFoto;
    }

    public void loadImagem(String filePath) {
        Bitmap bmImg = BitmapFactory.decodeFile(filePath);
        Bitmap bmImgReduzido = Bitmap.createScaledBitmap(bmImg,400,bmImg.getWidth(),true);

        getImgFoto().setImageBitmap(bmImgReduzido);
        getImgFoto().setScaleType(ImageView.ScaleType.FIT_XY);
        getImgFoto().setTag(filePath);

    }
}
