package com.mygdx.jumphero.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    //Metod för att addera en state till stacken
    public void push(State state){
        states.push(state);
    }

    //Metod för att ta bort den state som ligger högst upp i stacken
    public void pop(){
        states.pop();
    }

    //Metod för att byta ut den state som ligger högst upp i stacken med en ny state
    public void set(State state){
        states.pop();
        states.push(state);
    }

    //Metod för att kalla på överliggande states update metod
    public void update(float dt){
        states.peek().update(dt);
    }

    //Metod för att kalla på överliggande states render metod
    public void render (SpriteBatch sb){
        states.peek().render(sb);
    }

}
