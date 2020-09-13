package com.m.missilegetaway.Controller;

import com.m.missilegetaway.Assets.Assets;
import com.m.missilegetaway.Interfaces.MotorMissilFuncao;
import com.m.missilegetaway.Objetos.Missil;

import org.cocos2d.layers.CCLayer;

import java.util.Random;

public class MotorMissil extends CCLayer {
    private MotorMissilFuncao funcao;

    public MotorMissil() {
        this.schedule("missilMotor",1.0f/10f);
    }

    public void missilMotor(float dt){
        if(new Random().nextInt(10) == 0){
            this.getFuncao().criaMissil(new Missil(Assets.MISSIL));
        }
    }

    public void setFuncao(MotorMissilFuncao funcao){
        this.funcao = funcao;
    }

    public MotorMissilFuncao getFuncao(){
        return funcao;
    }
}
