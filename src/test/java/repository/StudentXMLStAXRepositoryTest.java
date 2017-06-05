package test.repository;

import domain.Student;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import repository.StudentXMLStAXRepository;

import javax.xml.stream.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Moni on 6/2/2017.
 */
public class StudentXMLStAXRepositoryTest {

    StudentXMLStAXRepository repo;
    Validator<Student> v;
    List<Student> students;
    Student student1;
    Student student2;
    XMLOutputFactory output;
    XMLStreamWriter writer;
    XMLInputFactory factory;
    XMLStreamReader streamReader;
    InputStream is,is2;

    @Before
    public void setUp() throws Exception {

        v = new Validator<Student>() {
            @Override
            public void validate(Student entity) throws ValidatorException {

            }
        };
        repo = new StudentXMLStAXRepository(v,"D:\\Licenta\\C8\\src\\test\\repository\\test.xml");
        students = new ArrayList<Student>();
        student1 = new Student("1", "Pop", "Vasile", "pv@yahoo.com");
        student2 = new Student("2", "Gaga", "Alex", "ga@yahoo.com");
        students.add(student1);
        students.add(student2);
        output = XMLOutputFactory.newInstance();
        writer = output.createXMLStreamWriter(new FileWriter("D:\\Licenta\\C8\\src\\test\\repository\\write.xml"));
        factory = XMLInputFactory.newInstance();

        streamReader = factory.createXMLStreamReader(new FileReader("D:\\Licenta\\C8\\src\\test\\repository\\read.xml"));
        is=new FileInputStream("D:\\Licenta\\C8\\src\\test\\repository\\read.xml");
        is2=new FileInputStream("D:\\Licenta\\C8\\src\\test\\repository\\write.xml");

    }


    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void loadData() throws Exception {
        List<Student> stud=new ArrayList<Student>();

        stud=repo.loadData("D:\\Licenta\\C8\\src\\test\\repository\\read.xml", stud);
        assertEquals(students.size(),stud.size());
        assertEquals(student1.getFirstName(),stud.get(0).getFirstName());
        assertEquals(student1.getLastName(),stud.get(0).getLastName());
        assertEquals(student1.getEmail(),stud.get(0).getEmail());
        assertEquals(student2.getFirstName(),stud.get(1).getFirstName());
        assertEquals(student2.getLastName(),stud.get(1).getLastName());
        assertEquals(student2.getEmail(),stud.get(1).getEmail());

        repo.loadData();

   


    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void readFromXMLFail() throws Exception {

        thrown.expect(XMLStreamException.class);
        thrown.expectMessage("Premature end of file");

        List<Student> stud= new ArrayList<Student>();


        stud=repo.readFromXML(is2);

        //assertEquals(stud.size(),students.size());

    }


    @Test
    public void readFromXML()throws Exception{

        List<Student> stud= new ArrayList<Student>();

        stud=repo.readFromXML(is);

        assertEquals(students.size(),stud.size());
        assertEquals(student1.getFirstName(),stud.get(0).getFirstName());
        assertEquals(student1.getLastName(),stud.get(0).getLastName());
        assertEquals(student1.getEmail(),stud.get(0).getEmail());
    }

    @Test
    public void writeToFileFail() throws FileNotFoundException {

        repo.writeToFile("file.xml");
        repo.writeToFile();


    }

    @Test
    public void writeToFile() throws XMLStreamException {

        repo.writeToFile("D:\\Licenta\\C8\\src\\test\\repository\\write.xml");
        repo.writeToFile("D:\\Licenta\\C8\\src\\test\\repository\\test.xml");
        repo.writeToFile();

    }

    @Test
    public void writeStudent() throws Exception {

        repo.writeStudent(student1, writer);

    }



}