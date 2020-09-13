package com.m.missilegetaway.Controller;

import com.m.missilegetaway.Assets.Assets;
import com.m.missilegetaway.Interfaces.MotorMissilFuncao;
import com.m.missilegetaway.Interfaces.MotorTiroFuncao;
import com.m.missilegetaway.Objetos.Missil;
import com.m.missilegetaway.Objetos.Player;
import com.m.missilegetaway.Objetos.Tiro;
import com.m.missilegetaway.R;
import com.m.missilegetaway.Telas.TelaFinal;
import com.m.missilegetaway.Telas.TelaFundo;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.larguraTela;
import static com.m.missilegetaway.Controller.ConfiguracoesAparelho.alturaTela;

public class Jogo extends CCLayer implements MotorMissilFuncao, MotorTiroFuncao {
    private TelaFundo telaFundo;
    private MotorMissil motorMissil;
    private CCLayer camadaMissil;
    private List misseisArray;
    private CCLayer camadaPlayer;
    private Player player;
    private CCLayer camadaTiro;
    private ArrayList tirosArray;
    private List playerArray;
    private CCLayer placarLayer;
    private Placar placar;

    public Jogo() {
        this.telaFundo = new TelaFundo(Assets.TELAFUNDO);
        this.telaFundo.setPosition(CGPoint.ccp(larguraTela() / 2.0f, alturaTela() / 2.0f));
        this.addChild(this.telaFundo);
        this.camadaMissil = CCLayer.node();
        this.addChild(this.camadaMissil);
        this.camadaPlayer = CCLayer.node();
        this.addChild(this.camadaPlayer);
        BotoesJogo camadaBotoes = BotoesJogo.botoesJogo();
        camadaBotoes.setFuncao(this);
        this.addChild(camadaBotoes);
        this.camadaTiro = CCLayer.node();
        this.addChild(this.camadaTiro);
        this.placarLayer = CCLayer.node();
        this.addChild(this.placarLayer);

        carregaSons();

        //SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), R.raw.music,true);

        this.setIsTouchEnabled(true);
        this.adicionaObjetosJogo();

    }

    public static CCScene criaJogo(){
        CCScene scene = CCScene.node();
        Jogo jogo = new Jogo();
        scene.addChild(jogo);
        return scene;
    }

    @Override
    public void criaMissil(Missil missil) {
        missil.setFuncao(this);
        this.camadaMissil.addChild(missil);
        missil.start();
        this.misseisArray.add(missil);
    }

    @Override
    public void removeMissil(Missil missil) {
        this.misseisArray.remove(missil);
    }

    public void adicionaObjetosJogo(){
        this.misseisArray = new ArrayList();
        this.motorMissil = new MotorMissil();
        this.player = new Player();
        this.camadaPlayer.addChild(this.player);
        this.tirosArray = new ArrayList();
        this.player.setFuncao(this);
        this.playerArray = new ArrayList();
        this.playerArray.add(this.player);
        this.placar = new Placar();
        this.placarLayer.addChild(this.placar);
    }

    @Override
    public void onEnter() {
        super.onEnter();
        this.schedule("verificaAcertos");
        this.iniciaMotores();
    }

    private void iniciaMotores(){
        this.addChild(this.motorMissil);
        this.motorMissil.setFuncao(this);
    }

    @Override
    public void criaTiro(Tiro tiro) {
        this.camadaTiro.addChild(tiro);
        tiro.setFuncao(this);
        tiro.start();
        this.tirosArray.add(tiro);
    }

    @Override
    public void removeTiro(Tiro tiro) {
        this.tirosArray.remove(tiro);
    }

    public void missilAcertado(CCSprite missil, CCSprite tiro){
        ((Missil) missil).acertado();
        ((Tiro) tiro).explosao();
        this.placar.ganhaPontos();
    }

    public void playerHit(CCSprite missil, CCSprite player){
        ((Missil) missil).acertado();
        ((Player) player).explodePlayer();
        CCDirector.sharedDirector().replaceScene(new TelaFinal().scene());
    }

    public boolean tiro(){
        player.atira();
        return true;
    }

    public void moveEsquerda(){
        player.moveEsquerda();
    }

    public void moveDireita(){
        player.moveDireita();
    }

    public CGRect getBordas(CCSprite objeto){
        CGRect rect = objeto.getBoundingBox();
        CGPoint ponto = rect.origin;
        CGRect retorno = CGRect.make(ponto.x,ponto.y,rect.size.width,rect.size.height);
        return retorno;
    }

    private boolean verificaColisoes(List<? extends CCSprite> array1, List<? extends CCSprite> array2, Jogo jogo, String acerto ){
        boolean resultado = false;
        for(int i = 0; i< array1.size(); i++){
            CGRect rectArray1 = getBordas(array1.get(i));
            for(int j = 0; j < array2.size(); j++){
                CGRect rectArray2 = getBordas(array2.get(j));
                if(CGRect.intersects(rectArray1,rectArray2)){
                    System.out.println("BATEU");
                    resultado = true;
                    Method method;
                    try {
                        method = Jogo.class.getMethod(acerto,CCSprite.class,CCSprite.class);
                        method.invoke(jogo, array1.get(i),array2.get(j));
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return resultado;
    }

    public void verificaAcertos(float dt){
        this.verificaColisoes(this.misseisArray,this.tirosArray,this,"missilAcertado");
        this.verificaColisoes(this.misseisArray,this.playerArray,this,"playerHit");
    }

    private void carregaSons(){
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.shoot);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.bang);
        SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(), R.raw.over);
    }

    public void iniciaTelaFinal(){
        CCDirector.sharedDirector().replaceScene(new TelaFinal().scene());
    }
}
