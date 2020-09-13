package com.m.missilegetaway.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.m.missilegetaway.R;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        CCGLSurfaceView ccglSurfaceView = new CCGLSurfaceView(this);
        setContentView(ccglSurfaceView);
        CCDirector.sharedDirector().attachInView(ccglSurfaceView);
        CCDirector.sharedDirector().setScreenSize(320,480);

        CCScene scene = new TelaInicial().scene();
        CCDirector.sharedDirector().runWithScene(scene);
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}