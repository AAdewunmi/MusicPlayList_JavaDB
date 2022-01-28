package com.musicplaylist;

import com.musicplaylist.model.Album;
import com.musicplaylist.model.Artist;
import com.musicplaylist.model.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    private TableView artistTable;

    @FXML
    public void listArtists(){
        Task<ObservableList<Artist>> task = new GetAllArtistTask();
        artistTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    @FXML
    public void listAlbumsForArtist(){
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if(artist == null){
            System.out.println("N0 ARTIST SELECTED!");
            return;
        }
        Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
            @Override
            protected ObservableList<Album> call() throws Exception {
                return FXCollections.observableArrayList(
                        Datasource.getInstance().queryAlbumsForArtistId(artist.getId()));
            }
        };
        artistTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }
}

class GetAllArtistTask extends Task{
    @Override
    public ObservableList<Artist> call() {
        return FXCollections.observableArrayList(
                Datasource.getInstance().queryArtists(Datasource.ORDER_BY_ASC));
    }
}