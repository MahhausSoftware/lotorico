package com.mahhaus.free.lotorico.props;

import android.app.Fragment;

import com.mahhaus.free.lotorico.R;
import com.mahhaus.free.lotorico.fragment.LotomaniaFragment;
import com.mahhaus.free.lotorico.fragment.MegaSenaFragment;

/**
 * Created by josias.soares on 14/12/16.
 */

public enum GameUrlEnum {
    MEGASENA(0, "MEGASENA", R.drawable.megasena, "http://www1.caixa.gov.br/app/loterias/mega-sena.html", new MegaSenaFragment()),
    LOTOMANIA(1, "LOTOMANIA", R.drawable.lotomania, "http://www1.caixa.gov.br/app/loterias/lotomania.html", new LotomaniaFragment()),
    TIMEMANIA(2, "TIMEMANIA", R.drawable.timemania, "http://www1.caixa.gov.br/app/loterias/timemania.html", new MegaSenaFragment()),
    QUINA(3, "QUINA", R.drawable.quina, "http://www1.caixa.gov.br/app/loterias/quina.html", new MegaSenaFragment()),
    DUPLASENA(4, "DUPLASENA", R.drawable.duplasena, "http://www1.caixa.gov.br/app/loterias/dupla-sena.html", new MegaSenaFragment()),
    FEDERAL(5, "FEDERAL", R.drawable.federal, "http://www1.caixa.gov.br/app/loterias/federal.html", new MegaSenaFragment()),
    INSTANTANEA(6, "INSTANTANEA", R.drawable.instantanea, "http://www1.caixa.gov.br/app/loterias/instantanea.html", new MegaSenaFragment()),
    LOTECA(7, "LOTECA", R.drawable.loteca, "http://www1.caixa.gov.br/app/loterias/loteca.html", new MegaSenaFragment()),
    LOTOGOL(8, "LOTOGOL", R.drawable.lotogol, "http://www1.caixa.gov.br/app/loterias/lotogol.html", new MegaSenaFragment()),
    LOTOFACIL(9, "LOTOFACIL", R.drawable.lotofacil, "http://www1.caixa.gov.br/app/loterias/lotofacil.html", new MegaSenaFragment());

    GameUrlEnum(int codigo, String descricao, int image, String url, Fragment fragment) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.image = image;
        this.url = url;
        this.fragment = fragment;
    }

    private int codigo;
    private String descricao;
    private int image;
    private String url;
    private Fragment fragment;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public static Fragment getFragmentPos(int position){
        for(GameUrlEnum enumUrl : GameUrlEnum.values()) {
            if(enumUrl.getCodigo() == position) {
                return enumUrl.getFragment();
            }
        }
        return null;
    }

    public static String getGameUrl(int position) {
        for(GameUrlEnum enumUrl : GameUrlEnum.values()) {
            if(enumUrl.getCodigo() == position) {
                return enumUrl.getUrl();
            }
        }

        return "";
    }
}
