package com.m.missilegetaway.Controller;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class ConfiguracoesAparelho {

    public static CGPoint resolucaoTela(CGPoint cgPoint){
        return cgPoint;
    }

    public static float larguraTela(){
        return tamanhoJanela().width;
    }

    public static float alturaTela(){
        return tamanhoJanela().height;
    }

    public static CGSize tamanhoJanela(){
        return CCDirector.sharedDirector().winSize();
    }
}
