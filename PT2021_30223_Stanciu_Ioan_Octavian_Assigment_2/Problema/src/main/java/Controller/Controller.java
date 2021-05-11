package Controller;

import Model.*;
import View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Controller {
    private Model model;
    private View view;

    public Controller(View view, Model model){
        this.model = model;
        this.view = view;
        this.view.addActionListener(new AcListener());
    }

    class AcListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            model.startSimulation(view,view.getMinProcessingTime(),view.getMaxProcessingTime(),view.getMinServiceTime(),view.getMaxServiceTime(),view.getNrOfQueues()
                    ,view.getNrOfClients(), view.getTimeLimit());
        }
    }

    public static void main(String[] args) {
        new Controller(new View(), new Model());
    }

}
