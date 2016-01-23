package com.example.fujitsu.lagi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {
    public static String EXTRA_MESSAGE = "com.example.fujitsu.lagi";
    public static String EXTRA_MESSAGE2 = "com.example.fujitsu.lagi";
    public static String apiUrl = "http://192.168.1.124:8080/RestPractice/crunchify/ftocservice/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void cobaClick(View view){
        Intent intents = new Intent(this,BuatDisplayMessage.class);
        EditText test = (EditText)findViewById(R.id.editText3);
        String coba = test.getText().toString();
//        String url = apiUrl;
//        new callApi().execute(url);
        intents.putExtra(EXTRA_MESSAGE,coba);
        startActivity(intents);

    }

    public void testInvoke (View view){
        EditText coba = (EditText)findViewById(R.id.editText);
        String param = coba.getText().toString();
        String url = apiUrl+param;
        new callApi().execute(url);

    }

    private class callApi extends AsyncTask<String,String,String>{
        private String content ;
        TextView uiUpdate = (TextView)findViewById(R.id.textView2);
        @Override
        protected String doInBackground(String... params)
        {

            String urlString = params[0];
            String resultToDisplay = "";

//            InputStream is = null;
//            storeStatus result = null;

            try {
                URL url = new URL(urlString);
                URLConnection conn = url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null ;

                while ((line = reader.readLine())!= null)
                {
                    sb.append(line+" ");
                }
                content = sb.toString();
//                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//                is  = urlConnection.getInputStream();
            }
            catch (Exception e){
                e.printStackTrace();
            }

//            XmlPullParserFactory pullParserFactory;
//
//            try {
//                pullParserFactory = XmlPullParserFactory.newInstance();
//                XmlPullParser parser = pullParserFactory.newPullParser();
//
//                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
//                parser.setInput(is, null);
//                result = parseXML(parser);
//
//            }
//            catch (XmlPullParserException e){
//                e.printStackTrace();
//            }
//            catch (IOException ie){
//                ie.printStackTrace();
//            }

            return resultToDisplay;


        }

        @Override
        protected void onPostExecute(String result)
        {
            uiUpdate.setText(content);
        }

    }

//    private class storeStatus{
//        public String statusNbr;
//        public String hygieneResult;
//
//    }

//    private storeStatus parseXML(XmlPullParser parser) throws XmlPullParserException, IOException
//    {
//        int eventType = parser.getEventType();
//        storeStatus result = new storeStatus();
//
//        while( eventType!= XmlPullParser.END_DOCUMENT) {
//            String name = null;
//
//            switch(eventType)
//            {
//                case XmlPullParser.START_TAG:
//                    name = parser.getName();
//
//                    if( name.equals("Error")) {
//                        System.out.println("Web API Error!");
//                    }
//                    else if ( name.equals("StatusNbr")) {
//                        result.statusNbr = parser.nextText();
//                    }
//                    else if (name.equals("HygieneResult")) {
//                        result.hygieneResult = parser.nextText();
//                    }
//
//                    break;
//
//                case XmlPullParser.END_TAG:
//                    break;
//            } // end switch
//
//            eventType = parser.next();
//        } // end while
//
//        return result;
//
//    }
}
