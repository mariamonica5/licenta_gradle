package test.view;

import domain.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import view.EditStudentViewController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.loadui.testfx.Assertions.verifyThat;

/**
 * Created by Moni on 6/1/2017.
 */
public class StudentViewControllerTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("../../view/StudentView.fxml"));
            return parent;
        } catch (IOException ex) {
            // TODO ...
        }
        return parent;
    }

    @Test
    public void setService() throws Exception {

    }

    @Test
    public void bindDataSyncroniously() throws Exception {

    }

    @Test
    public void loadDataTask() throws Exception {


    }

    @Test
    public void bindDataAsync1() throws Exception {

    }

    @Test
    public void bindDataAsync2() throws Exception {

    }

    @Test
    public void initModel() throws Exception {



    }

    @Test
    public void handleSaveStudent() throws Exception {

        click("#buttonSave");

        click("#textFieldFirstName").type("Monica");
        click("#textFieldLastName").type("Barutia");
        click("#textFieldId").type("bmig0003");
        click("#textFieldEmail").type("bm.yahoo.com");

        sleep(2, SECONDS);

        click("Save");

        sleep(2, SECONDS);


    }




    @Test
    public void handleUpdateStudent() throws Exception {



        click("First Name");
        sleep(2, SECONDS);
        click("Last Name");
        sleep(2, SECONDS);
        click("Email");
        sleep(2, SECONDS);
        click("Delete");
        sleep(2, SECONDS);
        click("#buttonUpdate");
        sleep(1, SECONDS);
        click("OK");

    }

    @Test
    public void showStudentEditDialog() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

}