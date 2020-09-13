package com.m.missilegetaway.Controller;

import com.m.missilegetaway.Assets.Assets;
import com.m.missilegetaway.Interfaces.FuncoesBotao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;

import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.larguraTela;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.alturaTela;

public class BotoesJogo extends CCLayer implements FuncoesBotao {

    private Botao btnEsquerda;
    private Botao btnDireita;
    private Botao btnAtira;
    private Jogo jogo;

    public static BotoesJogo botoesJogo(){
        return new BotoesJogo();
    }

    public void setFuncao(Jogo jogo){
        this.jogo = jogo;
    }

    public BotoesJogo(){
        this.setIsTouchEnabled(true);
        this.btnEsquerda = new Botao(Assets.BTNESQUERDA);
        this.btnDireita = new Botao(Assets.BTNDIREITA);
        this.btnAtira = new Botao(Assets.BTNATIRA);

        this.btnEsquerda.setFuncoes(this);
        this.btnDireita.setFuncoes(this);
        this.btnAtira.setFuncoes(this);

        posicionaBotoes();

        addChild(btnEsquerda);
        addChild(btnDireita);
        addChild(btnAtira);
    }

    @Override
    public void botaoPressionado(Botao botao) {
        if(botao.equals(this.btnEsquerda)){
            this.jogo.moveEsquerda();
        }
        if(botao.equals(this.btnDireita)){
            this.jogo.moveDireita();
        }
        if(botao.equals(this.btnAtira)){
            this.jogo.tiro();
        }
    }

    public void posicionaBotoes(){
        btnEsquerda.setPosition(CGPoint.ccp(40,40));
        btnDireita.setPosition(CGPoint.ccp(larguraTela()-40,40));
        btnAtira.setPosition(CGPoint.ccp(larguraTela()/2,40));
    }


}
