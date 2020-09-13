package com.m.missilegetaway.Controller;

import android.view.MotionEvent;

import com.m.missilegetaway.Interfaces.FuncoesBotao;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

public class Botao extends CCLayer {
    private CCSprite imagemBotao;
    private FuncoesBotao funcoes;

    public Botao(String imagemBotao) {
        this.setIsTouchEnabled(true);
        this.imagemBotao = CCSprite.sprite(imagemBotao);
        addChild(this.imagemBotao);
    }

    public void setFuncoes(FuncoesBotao funcoesBotao){
        this.funcoes = funcoesBotao;
    }

    @Override
    protected void registerWithTouchDispatcher() {
        CCTouchDispatcher.sharedDispatcher().addTargetedDelegate(this,0,false);
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        CGPoint localTocado = CGPoint.make(event.getX(),event.getY());
        localTocado = CCDirector.sharedDirector().convertToGL(localTocado);
        localTocado = this.convertToNodeSpace(localTocado);
        if(CGRect.containsPoint(this.imagemBotao.getBoundingBox(), localTocado)){
            funcoes.botaoPressionado(this);
        }
        return true;
    }
}
