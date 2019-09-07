package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class ListItemController {
    private Controller listViewController;

    @FXML
    public AnchorPane listItem;

    @FXML
    private TextField textField;

    @FXML
    void Destroy(){
        System.out.println("--- Deleted list item ---");
        listViewController.RemoveListItem(this);
    }


    public String GetTextField(){
        return textField.getText();
    }

    public void SetTextField(String text){
        textField.setText(text);
    }

    public void SetListViewController(Controller ctrl){
        listViewController = ctrl;
    }
}
