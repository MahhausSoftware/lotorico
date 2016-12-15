package com.mahhaus.free.lotorico.fragment;

import android.annotation.SuppressLint;
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
import com.mahhaus.free.lotorico.adapter.ImageAdapter;
import com.mahhaus.free.lotorico.adapter.ResultAdpater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by josias.soares on 14/12/16.
 */

public class ResultFragment extends GenericsFragment {
    private String mUrl;
    private Context mContext;

    private GridView mGridview;

    private ArrayList<String> mNumbers;
    private TextView mTextViewConcurso;
    private TextView mTextViewEstimDesc;
    private TextView mTextViewEstimValue;
    private TextView mTextViewAcumDesc;
    private TextView mTextViewAcumValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        mContext = getActivity();

        initComponents(view);
        actionComponents();

        return view;
    }

    public ResultFragment() {
    }

    @Override
    void initComponents(View view) {
        mTextViewConcurso = (TextView) view.findViewById(R.id.textViewConcurso);
        mGridview = (GridView) view.findViewById(R.id.gridViewResult);
        mTextViewEstimDesc = (TextView) view.findViewById(R.id.textViewEstimDesc);
        mTextViewEstimValue = (TextView) view.findViewById(R.id.textViewEstimValue);
        mTextViewAcumDesc = (TextView) view.findViewById(R.id.textViewAcumDesc);
        mTextViewAcumValue = (TextView) view.findViewById(R.id.textViewAcumValue);
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

//        WebSettings ws =  mWebView.getSettings();
//
//        ws.setJavaScriptEnabled(true);
//        ws.setSupportZoom(false);
//
//        // Force links and redirects to open in the WebView instead of in a browser
//        mWebView.setWebViewClient(new WebViewClient());
//
//        String strHtml ="";
//        mWebView.loadDataWithBaseURL(mUrl, strHtml, "text/html", "utf-8", "about:blank");
//        //mWebView.loadUrl(url);
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

            // CONCURSO ATUAL
            Elements elementResult = body.getElementsByClass("resultado-loteria");

            String epsilon = elementResult.select("p.epsilon").text();
            String description = elementResult.select("p.description").text();

            Elements tabelaResul = elementResult.first().getElementsByClass("simple-table");
            Elements tbRows = tabelaResul.first().getElementsByTag("tbody").first().children();

            mNumbers = new ArrayList<>();
            for (Element row : tbRows){
                for (Element colNumber : row.children()){
                    mNumbers.add(colNumber.text());
                }
            }

            // ESTIMATIVA PROXIMO CONCURSO
            Element eleEstNextResult = elementResult.first().getElementsByClass("next-prize").first();
            String estimativaDescription = eleEstNextResult.getElementsByTag("p").first().text();
            String estimativaValue = eleEstNextResult.getElementsByClass("value").first().text();

            // ACUMULADO PROXIMO CONCURSO
            Element eleEstimNextResult = elementResult.first().getElementsByClass("totals").first().getElementsByTag("p").first();
            String acumuladoDescription = eleEstimNextResult.getElementsByTag("span").first().text();
            String acumuladoValue = eleEstimNextResult.getElementsByTag("span").select("span.value").text();

            mTextViewConcurso.setText("LOTOMANIA");
            mTextViewEstimDesc.setText(estimativaDescription);
            mTextViewEstimValue.setText(estimativaValue);
            mTextViewAcumDesc.setText(acumuladoDescription);
            mTextViewAcumValue.setText(acumuladoValue);
            mGridview.setAdapter(new ResultAdpater(mContext, mNumbers));
        }
    }

//    public void getElementsHTML(){
//        Thread thread = new Thread(new Runnable(){
//
//            public void run() {
//                try {
//                    Document doc = Jsoup.connect(mUrl).get();
//                    Element body = doc.body();
//
//                    // CONCURSO ATUAL
//                    Elements elementResult = body.getElementsByClass("resultado-loteria");
//
//                    String epsilon = elementResult.select("p.epsilon").text();
//                    String description = elementResult.select("p.description").text();
//
//                    Elements tabelaResul = elementResult.first().getElementsByClass("simple-table");
//                    Elements tbRows = tabelaResul.first().getElementsByTag("tbody").first().children();
//
//                    mNumbers = new ArrayList<>();
//                    for (Element row : tbRows){
//                        for (Element colNumber : row.children()){
//                            mNumbers.add(colNumber.text());
//                        }
//                    }
//
//                    // ESTIMATIVA PROXIMO CONCURSO
//                    Element eleEstNextResult = elementResult.first().getElementsByClass("next-prize").first();
//                    String estimativaDescription = eleEstNextResult.getElementsByTag("p").first().text();
//                    String estimativaValue = eleEstNextResult.getElementsByClass("value").first().text();
//
//                    // ACUMULADO PROXIMO CONCURSO
//                    Element eleEstimNextResult = elementResult.first().getElementsByClass("totals").first().getElementsByTag("p").first();
//                    String acumuladoDescription = eleEstimNextResult.getElementsByTag("span").first().text();
//                    String acumuladoValue = eleEstimNextResult.getElementsByTag("span").select("span.value").text();
//
//                    mTextViewConcurso.setText("LOTOMANIA");
//                    mTextViewEstimDesc.setText(estimativaDescription);
//                    mTextViewEstimValue.setText(estimativaValue);
//                    mTextViewAcumDesc.setText(acumuladoDescription);
//                    mTextViewAcumValue.setText(acumuladoValue);
//                    mGridview.setAdapter(new ResultAdpater(mContext, mNumbers));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();
//    }
}