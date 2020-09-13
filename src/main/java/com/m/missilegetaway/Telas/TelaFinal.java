package com.m.missilegetaway.Telas;

import com.m.missilegetaway.Assets.Assets;
import com.m.missilegetaway.Controller.Botao;
import com.m.missilegetaway.Controller.Jogo;
import com.m.missilegetaway.Interfaces.FuncoesBotao;
import com.m.missilegetaway.R;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.larguraTela;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.alturaTela;

public class TelaFinal extends CCLayer implements FuncoesBotao {

    private TelaFundo telaFundo;
    private Botao btnPlay;
    private Botao btnSair;

    public TelaFinal(){
        this.telaFundo = new TelaFundo(Assets.TELAFUNDO);
        this.telaFundo.setPosition(CGPoint.ccp(larguraTela() / 2.0f, alturaTela() / 2.0f));
        this.addChild(telaFundo);

        CCSprite titulo = CCSprite.sprite(Assets.LOGOFINAL);
        titulo.setPosition(CGPoint.ccp(larguraTela() / 2, alturaTela() - 130));
        this.addChild(titulo);
        this.setIsTouchEnabled(true);

        this.btnPlay = new Botao(Assets.BOTAOJOGAR);
        this.btnPlay.setPosition(CGPoint.ccp(larguraTela() / 2, alturaTela() - 300));
        this.btnPlay.setFuncoes(this);
        addChild(this.btnPlay);

        this.btnSair = new Botao(Assets.BOTAOSAIR);
        this.btnSair.setPosition(CGPoint.ccp(larguraTela() / 2, alturaTela() - 400));
        this.btnSair.setFuncoes(this);
        addChild(this.btnSair);


    }

    public CCScene scene(){
        CCScene scene = CCScene.node();
        scene.addChild(this);
        return scene;
    }


    @Override
    public void botaoPressionado(Botao botao) {
        if(botao.equals(this.btnPlay)){
            SoundEngine.sharedEngine().pauseSound();
            CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(1.0f, Jogo.criaJogo()));
        }
        if (botao.equals(this.btnSair)){
            System.exit(0);
        }

    }
}
