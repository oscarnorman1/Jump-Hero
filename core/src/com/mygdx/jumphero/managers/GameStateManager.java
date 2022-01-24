package com.mygdx.jumphero.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    //a private Stack object for states
    private Stack<State> states;

    //constructor for GameStateManager
    public GameStateManager(){
        states = new Stack<State>();
    }

    //Method push to a new State onto this stack.
    public void push(State state){
        states.push(state);
    }

    //This method call returns the object at the top of this stack.
    public void pop(){
        states.pop();
    }

    //New method when we want to pop some state and instantly push a new state
    public void set(State state){
        states.pop();
        states.push(state);

    }

    //dt is delta time , i.e. change in time between 2 renders
    public void update(float dt){
        states.peek().update(dt);
    }

    //Method to render SpriteBatch on the screen .It look at the top of the stack using peek method and
    //then it renders it on the screen
    public void render (SpriteBatch sb){
        states.peek().render(sb);
    }

}
