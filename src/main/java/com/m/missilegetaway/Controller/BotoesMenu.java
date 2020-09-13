package com.m.missilegetaway.Controller;

import com.m.missilegetaway.Assets.Assets;
import com.m.missilegetaway.Interfaces.FuncoesBotao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.larguraTela;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.alturaTela;

public class BotoesMenu extends CCLayer implements FuncoesBotao {

    private Botao btnJogar;
    private Botao btnSair;

    public BotoesMenu() {
        this.setIsTouchEnabled(true);

        this.btnJogar = new Botao(Assets.BOTAOJOGAR);
        this.btnSair = new Botao(Assets.BOTAOSAIR);
        this.btnJogar.setFuncoes(this);
        this.btnSair.setFuncoes(this);

        posicionaBotoes();

        addChild(btnJogar);
        addChild(btnSair);
    }

    public void posicionaBotoes(){
        btnJogar.setPosition(CGPoint.ccp(larguraTela() / 2, alturaTela() - 300));
        btnSair.setPosition(CGPoint.ccp(larguraTela() / 2, alturaTela() - 400));
    }

    @Override
    public void botaoPressionado(Botao botao) {
        if(botao.equals(this.btnJogar)){
            CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(1.0f, Jogo.criaJogo()));
        }
        if(botao.equals(btnSair)){
            System.exit(0);
        }
    }
}
