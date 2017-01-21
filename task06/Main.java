package task06;

public class Main {
    public static void main(String[] args) {

        String filePaht = "c:\\dataofperson.txt";
        DataOfPerson dtp = new DataOfPerson();

        dtp.setName("John");
        dtp.setSurname("Brown");
        dtp.setAge(34);
        dtp.setWeight(8.56);
        dtp.setHeight(1.80);

        System.out.println("Перед сериализацией:");
        System.out.println(dtp);

        dtp.serialize(filePaht);

        DataOfPerson newDtp = DataOfPerson.deSerialize(filePaht);

        System.out.println("После сериализации:");
        System.out.println(newDtp);
    }
}
