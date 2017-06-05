package repository;

import domain.Student;
import domain.validators.StudentValidator;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import javax.xml.stream.*;
import java.io.*;
import java.util.List;

/**
 * Created by camelia on 12/7/2016.
 */
public class StudenXMLStAX2Repository extends AbstractFileRepository<Student, String> {

    public StudenXMLStAX2Repository(){}
    public StudenXMLStAX2Repository(Validator<Student> vali, String fName){
        super(vali,fName);
    }
    @Override
    public void loadData() {
        try(FileInputStream input = new FileInputStream("resources/StudentStAX2.xml")){
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            readFromXMLFile(reader);
        }
        catch (IOException ex){

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    /*
        read the students from XML File
     */
    private void readFromXMLFile(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext())
        {
            int eventType= reader.next();
            switch (eventType){
                case XMLStreamReader.START_ELEMENT:
                    String elemName=reader.getLocalName();
                    if (elemName.equals("student"))
                    {
                        try {
                            super.save(readStudent(reader));
                        } catch (ValidatorException e) {
                            e.printStackTrace();
                        }
                    }

            }
        }
    }

    private Student readStudent(XMLStreamReader reader) throws XMLStreamException {
        Student s=new Student(reader.getAttributeValue(null,"id"),"","","");
        StringBuilder currentProperty=new StringBuilder();
        StringBuilder currentPropertyValue=new StringBuilder();
        while (reader.hasNext())
        {
            int eventType=reader.next();
            switch (eventType){

                case XMLStreamReader.START_ELEMENT:
                    if (reader.getLocalName().equals("property"))
                        currentProperty.append(reader.getAttributeValue(null,"name"));
                    break;
                case XMLStreamReader.CHARACTERS:
                    String text=reader.getText().trim();
                    if (! text.equals(""))
                        currentPropertyValue.append(text);
                    break;
                case XMLStreamReader.END_ELEMENT:
                    String endName=reader.getLocalName();
                    if (endName.equals("property")) {
                        if (currentProperty.toString().equals("firstName"))
                            s.setFirstName(currentPropertyValue.toString());
                        if (currentProperty.toString().equals("lastName"))
                            s.setLastName(currentPropertyValue.toString());
                        if (currentProperty.toString().equals("email"))
                            s.setEmail(currentPropertyValue.toString());
                        currentPropertyValue.setLength(0);
                        currentProperty.setLength(0);
                    }
                    else if (endName.equals("student"))
                    {
                        return s;
                    }
                    break;
            }
        }
        throw new XMLStreamException("NU s-a citit studentul!");
    }

    @Override
    public void writeToFile() {
        try(FileOutputStream output = new FileOutputStream("resources/StudentStAX2.xml")){
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(output);
            writeToXMLFile(writer);
        }
        catch (IOException ex){

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }


    private void writeToXMLFile(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeStartElement("students");
        super.findAll().forEach(x -> {
            try {
                writeStudent(x, writer);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        });
        writer.writeEndElement();
    }


    private void writeStudent(Student x, XMLStreamWriter writer) throws XMLStreamException{
        writer.writeStartElement("student");
        writer.writeAttribute("id", x.getId());

        writer.writeStartElement("property");
        writer.writeAttribute("name","firstName");
        writer.writeCharacters(x.getFirstName());
        writer.writeEndElement();

        writer.writeStartElement("property");
        writer.writeAttribute("name","lastName");
        writer.writeCharacters(x.getLastName());
        writer.writeEndElement();

        writer.writeStartElement("property");
        writer.writeAttribute("name","email");
        writer.writeCharacters(x.getEmail());
        writer.writeEndElement();

        writer.writeEndElement();
    }
}
