package com.m.missilegetaway.Controller;

import com.m.missilegetaway.Assets.Assets;
import com.m.missilegetaway.Objetos.Missil;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.opengl.CCBitmapFontAtlas;
import org.cocos2d.types.ccColor3B;

import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.larguraTela;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.alturaTela;

public class Placar extends CCLayer {
    private int pontucao;
    private CCBitmapFontAtlas texto;
    private Missil missil;

    public Placar() {
        this.pontucao = 0;
        this.texto = CCBitmapFontAtlas.bitmapFontAtlas(String.valueOf(this.pontucao), "UniSansSemiBold_Numbers_240.fnt");
        this.texto.setColor(ccColor3B.ccBLACK);
        this.texto.setScale(0.5f);
        this.setPosition(larguraTela() - 50, alturaTela() - 50);
        this.addChild(this.texto);
    }

    public void ganhaPontos() {
        pontucao++;
        this.texto.setString(String.valueOf(this.pontucao));
    }

    public int recuperaPontuacao(){
        return pontucao;
    }


}


