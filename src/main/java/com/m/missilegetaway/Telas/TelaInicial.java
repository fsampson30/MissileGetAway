package com.m.missilegetaway.Telas;

import com.m.missilegetaway.Assets.Assets;
import com.m.missilegetaway.Controller.BotoesMenu;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.alturaTela;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.larguraTela;

public class TelaInicial extends CCLayer {

    private TelaFundo telaFundo;

    public TelaInicial(){
        this.telaFundo = new TelaFundo(Assets.TELAFUNDO);
        this.telaFundo.setPosition(CGPoint.ccp(larguraTela() / 2.0f, alturaTela() / 2.0f));
        this.addChild(this.telaFundo);
        CCSprite logo = CCSprite.sprite(Assets.LOGO);
        logo.setPosition(CGPoint.ccp(larguraTela() / 2, alturaTela() - 100));
        this.addChild(logo);
        BotoesMenu botoesMenu = new BotoesMenu();
        this.addChild(botoesMenu);
    }

    public CCScene scene(){
        CCScene scene = CCScene.node();
        scene.addChild(this);
        return scene;
    }
}
