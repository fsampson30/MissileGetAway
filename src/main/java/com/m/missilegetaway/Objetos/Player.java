package com.m.missilegetaway.Objetos;

import com.m.missilegetaway.Assets.Assets;
import com.m.missilegetaway.Interfaces.MotorTiroFuncao;
import com.m.missilegetaway.R;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;

import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.larguraTela;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.alturaTela;

public class Player extends CCSprite {

    float x = larguraTela()/2;
    float y = 120;
    private MotorTiroFuncao funcao;

    public Player(){
        super(Assets.PLAYER);
        setPosition(x,y);
    }

    public void atira(){
        funcao.criaTiro(new Tiro(x,y));
    }

    public void setFuncao(MotorTiroFuncao funcao){
        this.funcao = funcao;
    }

    public void moveEsquerda(){
        if(x > 30){
            x -=20;
        }
        setPosition(x,y);
    }

    public void moveDireita(){
        if(x <larguraTela() - 30){
            x +=20;
        }
        setPosition(x,y);
    }

    public void explodePlayer(){
        SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), R.raw.over);
        SoundEngine.sharedEngine().pauseSound();
        this.unschedule("update");

        float dt = 0.2f;
        CCScaleBy a1 = CCScaleBy.action(dt, 0.5f);
        CCFadeOut a2 = CCFadeOut.action(dt);
        CCSpawn s1 = CCSpawn.actions(a1,a2);

        CCCallFunc c1 = CCCallFunc.action(this, "removeMe");
        this.runAction(CCSequence.actions(s1,c1));
    }


}
