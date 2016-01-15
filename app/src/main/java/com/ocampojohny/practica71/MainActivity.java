package com.ocampojohny.practica71;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {



        CallbackManager callbackManager;

        TextView details;
        LoginButton loginButton;
        Button btregistrar;
        ShareDialog shareDialog;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.activity_main);

            loginButton = (LoginButton) findViewById(R.id.login_button);
//           profilePictureView = (ProfilePictureView) findViewById(R.id.picture);
            details = (TextView) findViewById(R.id.details);
            btregistrar=(Button) findViewById(R.id.btregistro);

            callbackManager = CallbackManager.Factory.create();

            shareDialog = new ShareDialog(this);
            loginButton.setReadPermissions("public_profile email");

            details.setVisibility(View.INVISIBLE);

            btregistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Registro_Usuarios.class);
                    startActivity(i);

                }
            });

            if (AccessToken.getCurrentAccessToken() != null) {
                RequestData();

                details.setVisibility(View.VISIBLE);
            }
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (AccessToken.getCurrentAccessToken() != null) {

                        details.setVisibility(View.INVISIBLE);

                    }
                }
            });



            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        RequestData();

                        details.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancel() {

                }


                @Override
                public void onError(FacebookException error) {

                }
            });
        }







        public void RequestData() {
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    JSONObject json = response.getJSONObject();
                    try {
                        if (json != null) {

                            String text = "<b>BIENVENIDO :</b> " + json.getString("name") + "<br><br><b>Ya haces parte de mercandoApp</b> ";
                            details.setText(Html.fromHtml(text));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,email,picture");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }


