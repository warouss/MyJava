package com.warouss.io.serialization;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
//		new SerializationTest().test1();
		
//		new SerializationTest().test2();
		
		new Main().testHeritage();
		
//		new SerializationTest().testProxy();

	}
	
	/**
	 * 1) Test avec Employee.serialVersionUID & Employee.password commentés
	 * permet de tester transient
	 * 2) ensuite on décommente Employee.password et on execute juste deseralize une exception est lancée
	 * 3) en décommentant Employee.serialVersionUID avec Employee.password et on execute serialize 
	 * puis on refais l'étape 2) cette fois pas d'exception
	 */
	private void test1() {
		String fileName="employee.ser";
        Employee emp = new Employee();
        emp.setId(100);
        emp.setName("Pankaj");
        emp.setSalary(5000);
         
        //serialize to file
        try {
            SerializationUtil.serialize(emp, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
         
        Employee empNew = null;
        try {
            empNew = (Employee) SerializationUtil.deserialize(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        System.out.println("emp Object::"+emp);
        System.out.println("empNew Object::"+empNew);
	}
	
	/**
	 * Test de implements Externalizable
	 */
	private void test2() {
		String fileName = "person.ser";
        Person person = new Person();
        person.setId(1);
        person.setName("Pankaj");
        person.setGender("Male");
         
        try {
            SerializationUtil.serialize(person, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
         
        try {
            Person p = (Person) SerializationUtil.deserialize(fileName);
            System.out.println("Person Object Read="+p);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Test héritage
	 * en commentant les méthodes SubClass.readObject & SubClass.writeObject 
	 * on ne récupère pas les attributs de la classe parente 
	 */
	private void testHeritage() {
		String fileName = "subclass.ser";
        
        SubClass subClass = new SubClass();
        subClass.setId(10);
        subClass.setValue("Data");
        subClass.setName("Pankaj");
         
        try {
            SerializationUtil.serialize(subClass, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
         
        try {
            SubClass subNew = (SubClass) SerializationUtil.deserialize(fileName);
            System.out.println("SubClass read = "+subNew);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * If you will open the data.ser file, you can see that DataProxy object is saved as stream in the file. 
	 */
	private void testProxy() {
		String fileName = "data.ser";
        
        Data data = new Data("Pankaj");
         
        try {
            SerializationUtil.serialize(data, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        try {
            Data newData = (Data) SerializationUtil.deserialize(fileName);
            System.out.println(newData);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
