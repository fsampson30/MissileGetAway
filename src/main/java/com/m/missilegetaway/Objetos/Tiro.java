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
import org.cocos2d.types.CGPoint;

public class Tiro extends CCSprite {

    private MotorTiroFuncao funcao;

    float x,y;

    public Tiro(float x, float y){
        super(Assets.TIRO);
        this.x = x;
        this.y = y;
        setPosition(this.x, this.y);
        this.schedule("update");
    }

    public void update(float dt){
        y +=2;
        this.setPosition(CGPoint.ccp(x,y));
    }

    public void setFuncao(MotorTiroFuncao funcao){
        this.funcao = funcao;
    }

    public void start(){
        SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), R.raw.shoot);
    }

    public void explosao(){
        this.funcao.removeTiro(this);
        this.unschedule("update");

        float dt = 0.2f;
        CCScaleBy a1 = CCScaleBy.action(dt, 0.5f);
        CCFadeOut a2 = CCFadeOut.action(dt);
        CCSpawn s1 = CCSpawn.actions(a1,a2);

        CCCallFunc c1 = CCCallFunc.action(this, "removeMe");
        this.runAction(CCSequence.actions(s1,c1));
    }

    public void removeMe(){
        this.removeFromParentAndCleanup(true);
    }
}
