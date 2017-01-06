package com.teste.teste2.teste2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    String result;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Roda animação
        setContentView(R.layout.activity_search_result);

        resultText = (TextView)findViewById(R.id.resultText);

        getWindow().getAttributes().windowAnimations = R.style.Fade;

        // Recebe a variavel da activity anterior
        Intent intent = getIntent();
        result = intent.getStringExtra("resultSend"); //if it's a string you stored.

        resultText.setText(result);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Roda animação
        overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
    }
}
