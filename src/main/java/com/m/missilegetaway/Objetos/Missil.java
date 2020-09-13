package com.m.missilegetaway.Objetos;

import com.m.missilegetaway.Controller.Placar;
import com.m.missilegetaway.Interfaces.MotorMissilFuncao;
import com.m.missilegetaway.R;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

import java.util.Random;

import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.larguraTela;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.alturaTela;

public class Missil extends CCSprite {
    private float x, y;
    private float aceleracao = 2.5f;

    private MotorMissilFuncao funcao;

    public Missil(String imagem) {
        super(imagem);
        x = new Random().nextInt(Math.round(larguraTela()));
        y = alturaTela();
    }

    public void start() {
        this.schedule("update");
    }

    public void update(float dt) {
        y -= aceleracao;
        this.setPosition(CGPoint.ccp(x, y));
    }


    public void setFuncao(MotorMissilFuncao funcao) {
        this.funcao = funcao;
    }

    public void acertado() {
        SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), R.raw.bang);
        this.funcao.removeMissil(this);
        this.unschedule("update");
        float dt = 0.2f;
        CCScaleBy a1 = CCScaleBy.action(dt, 0.5f);
        CCFadeOut a2 = CCFadeOut.action(dt);
        CCSpawn s1 = CCSpawn.actions(a1, a2);

        CCCallFunc c1 = CCCallFunc.action(this, "removeMe");
        this.runAction(CCSequence.actions(s1, c1));
    }

    public void removeMe() {
        this.removeFromParentAndCleanup(true);
    }

    public void aumentaAceleracao(float aceleracao) {
        this.aceleracao = aceleracao;
    }

}
