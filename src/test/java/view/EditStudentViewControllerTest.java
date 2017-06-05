package test.view;

import domain.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import view.EditStudentViewController;
import view.MessageAlert;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;

/**
 * Created by Moni on 6/1/2017.
 */
public class EditStudentViewControllerTest extends GuiTest {

    Student student;
    Student student2;
    Student student3;

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("../../view/EditStudentView.fxml"));
            return parent;
        } catch (IOException ex) {
            // TODO ...
        }
        return parent;
    }

    @Before
    public void setUp() throws Exception {

        student=new Student("bmig0003","Monica","Barutia","bm@yahoo.com");
        student2= new Student("nmig0020", "Madalina", "Nemes", "nm@yahoo.com");
        student3=null;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void setService() throws Exception {

        TextField firstname = find("#textFieldFirstName");
        firstname.setText(student2.getFirstName());
        verifyThat("#textFieldFirstName", hasText("Madalina"));

        TextField lastname= find("#textFieldLastName");
        lastname.setText(student2.getLastName());
        verifyThat("#textFieldLastName", hasText("Nemes"));

        TextField id= find("#textFieldId");
        id.setText(student2.getId());
        verifyThat("#textFieldId", hasText("nmig0020"));

        TextField email= find("#textFieldEmail");
        email.setText(student2.getEmail());
        verifyThat("#textFieldEmail", hasText("nm@yahoo.com"));



        sleep(2, SECONDS);
    }

    @Test
    public void handleSave() throws Exception {

        click("#textFieldFirstName").type("Monica");
        click("#textFieldLastName").type("Barutia");
        click("#textFieldId").type("bmig0003");
        click("#textFieldEmail").type("bm.yahoo.com");

        click("Save");

        sleep(2, SECONDS);


    }

    @Test
    public void handleCancel() throws Exception {

        click("#textFieldFirstName").type("Monica");
        click("#textFieldLastName").type("Barutia");

        click("Cancel");

        sleep(2, SECONDS);

    }


}