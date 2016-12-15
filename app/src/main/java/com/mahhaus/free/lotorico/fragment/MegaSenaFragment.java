package com.mahhaus.free.lotorico.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.GridView;
import android.widget.TextView;

import com.mahhaus.free.lotorico.R;
import com.mahhaus.free.lotorico.adapter.ResultAdpater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by josias.soares on 15/12/16.
 */

public class MegaSenaFragment extends GenericsFragment {
    private static final String FRAGMENT_TITLE = "Megasena";

    private String mUrl;
    private Context mContext;

    private GridView mGridviewNumbers;

    private ArrayList<String> mNumbers;
    private TextView mTextViewConcurso;
    private TextView mTextViewEstimDesc;
    private TextView mTextViewEstimValue;
    private TextView mTextViewAcumDesc;
    private TextView mTextViewAcumValue;
    private WebView mWebView;
    private String mWinners;
    private TextView mTextViewAcumFinalDesc;
    private TextView mTextViewAcumFinalValue;
    private TextView mTextViewAcumViradaDesc;
    private TextView mTextViewAcumViradaValue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_megasena, container, false);
        mContext = getActivity();

        initComponents(view);
        actionComponents();

        setTitleMain(FRAGMENT_TITLE);

        return view;
    }

    public MegaSenaFragment() {
    }

    @Override
    void initComponents(View view) {
        mTextViewConcurso = (TextView) view.findViewById(R.id.textViewConcurso);
        mGridviewNumbers = (GridView) view.findViewById(R.id.gridViewResult);
        mTextViewEstimDesc = (TextView) view.findViewById(R.id.textViewEstimDesc);
        mTextViewEstimValue = (TextView) view.findViewById(R.id.textViewEstimValue);
        mTextViewAcumDesc = (TextView) view.findViewById(R.id.textViewAcumDesc);
        mTextViewAcumValue = (TextView) view.findViewById(R.id.textViewAcumValue);
        mWebView = (WebView) view.findViewById(R.id.webView);
        mTextViewAcumFinalDesc = (TextView) view.findViewById(R.id.textViewAcumFinalDesc);
        mTextViewAcumFinalValue = (TextView) view.findViewById(R.id.textViewAcumFinalValue);
        mTextViewAcumViradaDesc = (TextView) view.findViewById(R.id.textViewAcumViradaDesc);
        mTextViewAcumViradaValue = (TextView) view.findViewById(R.id.textViewAcumViradaValue);
    }

    @Override
    void actionComponents() {
        Bundle bundle = getArguments();
        if (bundle.containsKey("URL")){
            mUrl = bundle.getString("URL");
        }
        new getElementsHTML().execute();
    }

    private void loadGameUrl() {

        WebSettings ws =  mWebView.getSettings();

        ws.setJavaScriptEnabled(false);
        ws.setSupportZoom(false);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        mWebView.loadData(mWinners.replace("Veja o detalhamento", ""),  "text/html; charset=utf-8",  "utf-8");
    }

    private class getElementsHTML extends AsyncTask<Void, Integer, Document> {
        protected Document doInBackground(Void... params) {
            Document doc = null;
            try {
                doc = Jsoup.connect(mUrl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return doc;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Document doc) {
            Element body = doc.body();


            // NUMERO DO CONCURSO
            String numberConcurso = body.getElementsByClass("title-bar").first().children().get(1).children().text();

            // CONCURSO ATUAL
            Elements elementResult = body.getElementsByClass("resultado-loteria");

            String epsilon = elementResult.select("p.epsilon").text();
            String description = elementResult.select("p.description").text();

            Elements tabelaResul = elementResult.first().getElementsByClass("numbers");
            Elements liNumbers = tabelaResul.first().children();

            mNumbers = new ArrayList<>();
            for (Element li : liNumbers){
                mNumbers.add(li.text());
            }

            // ESTIMATIVA PROXIMO CONCURSO
            Element eleEstNextResult = elementResult.first().getElementsByClass("next-prize").first();
            String estimativaDescription = eleEstNextResult.getElementsByTag("p").first().text();
            String estimativaValue = eleEstNextResult.getElementsByClass("value").first().text();

            // ACUMULADO PROXIMO CONCURSO
            Elements eleEstimNextResult = elementResult.first().getElementsByClass("totals").first().children();

            String acumuladoDescription = eleEstimNextResult.get(0).children().get(0).text();
            String acumuladoValue = eleEstimNextResult.get(0).children().get(1).text();

            String acumuladoFinalDescription = eleEstimNextResult.get(1).children().get(0).text();
            String acumuladoFinalValue = eleEstimNextResult.get(1).children().get(1).text();

            String acumuladoViradaDescription = eleEstimNextResult.get(2).children().get(0).text();
            String acumuladoViradaValue = eleEstimNextResult.get(2).children().get(1).text();

            // GANHADORES
            mWinners = body.getElementsByClass("related-box").outerHtml();

            mTextViewConcurso.setText(numberConcurso);

            mTextViewEstimDesc.setText(estimativaDescription);
            mTextViewEstimValue.setText(estimativaValue);

            mTextViewAcumDesc.setText(acumuladoDescription);
            mTextViewAcumValue.setText(acumuladoValue);
            mTextViewAcumFinalDesc.setText(acumuladoFinalDescription);
            mTextViewAcumFinalValue.setText(acumuladoFinalValue);
            mTextViewAcumViradaDesc.setText(acumuladoViradaDescription);
            mTextViewAcumViradaValue.setText(acumuladoViradaValue);

            mGridviewNumbers.setAdapter(new ResultAdpater(mContext, mNumbers));
            loadGameUrl();
        }
    }
}
