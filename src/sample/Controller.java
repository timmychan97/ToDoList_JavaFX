package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    // Store the listItems as classes here
    private static List<ListItemController> listItems = new ArrayList<>();


    @FXML
    Pane listView;

    @FXML
    void ButtonAdd_Clicked(){
        AddListItem();
    }

    @FXML
    void ButtonSave_Clicked(){
        System.out.println("Saving...");
        List<String> notes = new ArrayList<>();
        listItems.forEach(listItem -> {
            notes.add(listItem.GetTextField());
        });
        String result = String.join("\n", notes);
        System.out.println(result);
        SaveToFile(result);
        System.out.println("Saved");
    }

    @FXML
    void ButtonLoad_Clicked(){
        System.out.println("Loading...");
        List<String> lines = LoadFile();
        ParseLoadFile(lines);

        System.out.println("Loaded");
    }



    private ListItemController AddListItem(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listItem.fxml"));
        //fxmlLoader.setController(this);
        try
        {
            Pane listItem = fxmlLoader.load();
            ListItemController listItemController = fxmlLoader.getController();
            listItemController.SetListViewController(this);

            listItems.add(listItemController);
            listView.getChildren().add(listItem);

            System.out.println("--- Added list item ---");
            return listItemController;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public void RemoveListItem(ListItemController item){
        RemoveListItemFromScene(item);
        listItems.remove(item);
    }

    private void RemoveListItemFromScene(ListItemController item){
        listView.getChildren().remove(item.listItem);
    }




    private static void SaveToFile(String data) {
        String path = System.getProperty("user.dir") + "/save.txt";
        try {
            Files.write(Paths.get(path), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> LoadFile(){
        String path = System.getProperty("user.dir") + "/save.txt";
        try {
           return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void ParseLoadFile(List<String> lines){
        for (String line : lines) {
            ListItemController listItem = AddListItem();
            listItem.SetTextField(line);
        }
    }






}
