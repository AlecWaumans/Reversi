package g58399.ATLIR4.designPattern.command;

import java.util.Stack;

public class CommandManages {
    private Stack<Command> undo;
    private Stack<Command> redo;

    public CommandManages(Stack<Command> undo, Stack<Command> redo){
        this.undo = undo;
        this.redo = redo;
    }

    public void doCommande(Command command){
        undo.push(command);
        redo.push(command);
        command.execute();
    }

    public void undo(){
        undo.peek().unexecute();
        undo.pop();
    }

    public void redo(){
        redo.peek().execute();
        redo.pop();
    }
}
