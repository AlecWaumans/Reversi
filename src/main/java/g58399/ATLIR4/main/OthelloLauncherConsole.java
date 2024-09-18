package g58399.ATLIR4.main;


import g58399.ATLIR4.controller.ControllerConsole;
import g58399.ATLIR4.designPattern.command.Command;
import g58399.ATLIR4.designPattern.command.CommandManages;
import g58399.ATLIR4.model.Game;
import g58399.ATLIR4.view.console.TextView;

import java.util.Stack;

public class OthelloLauncherConsole {
    public static void main(String[] args) {
        Stack<Command> undo = new Stack<Command>();
        Stack<Command> redo = new Stack<Command>();
        CommandManages manages = new CommandManages(undo, redo);
        Game model = new Game(manages);
        ControllerConsole controller = new ControllerConsole(model, new TextView(model));
        controller.play();
    }
}
