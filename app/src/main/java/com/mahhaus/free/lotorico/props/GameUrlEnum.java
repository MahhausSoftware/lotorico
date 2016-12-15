package com.mahhaus.free.lotorico.props;

import com.mahhaus.free.lotorico.R;

/**
 * Created by josias.soares on 14/12/16.
 */

public enum GameUrlEnum {
    MEGASENA(0, "MEGASENA", R.drawable.megasena, "http://www1.caixa.gov.br/app/loterias/mega-sena.html"),
    LOTOMANIA(1, "LOTOMANIA", R.drawable.lotomania, "http://www1.caixa.gov.br/app/loterias/lotomania.html"),
    TIMEMANIA(2, "TIMEMANIA", R.drawable.timemania, "http://www1.caixa.gov.br/app/loterias/timemania.html"),
    QUINA(3, "QUINA", R.drawable.quina, "http://www1.caixa.gov.br/app/loterias/quina.html"),
    DUPLASENA(4, "DUPLASENA", R.drawable.duplasena, "http://www1.caixa.gov.br/app/loterias/dupla-sena.html"),
    FEDERAL(5, "FEDERAL", R.drawable.federal, "http://www1.caixa.gov.br/app/loterias/federal.html"),
    INSTANTANEA(6, "INSTANTANEA", R.drawable.instantanea, "http://www1.caixa.gov.br/app/loterias/instantanea.html"),
    LOTECA(7, "LOTECA", R.drawable.loteca, "http://www1.caixa.gov.br/app/loterias/loteca.html"),
    LOTOGOL(8, "LOTOGOL", R.drawable.lotogol, "http://www1.caixa.gov.br/app/loterias/lotogol.html"),
    LOTOFACIL(9, "LOTOFACIL", R.drawable.lotofacil, "http://www1.caixa.gov.br/app/loterias/lotofacil.html");

    GameUrlEnum(int codigo, String descricao, int image, String url) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.image = image;
        this.url = url;
    }

    private int codigo;
    private String descricao;
    private int image;
    private String url;

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

    public static String getGameUrl(int position) {
        for(GameUrlEnum enumUrl : GameUrlEnum.values()) {
            if(enumUrl.getCodigo() == position) {
                return enumUrl.getUrl();
            }
        }

        return GameUrlEnum.MEGASENA.getUrl();
    }
}
