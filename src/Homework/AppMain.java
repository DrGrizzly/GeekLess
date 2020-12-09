package Homework;

public class AppMain {
    public static void main(String[] args){
        Employee personArr[] = new Employee[5];
        personArr[0] = new Employee("Иванов Иван Иванович","Директор","aaa123@mail.ru","+7292191030",250500.40f,43);
        personArr[1] = new Employee("Васяпкин Вован Казанович","Технический писатель","kkkss2@mail.ru","+77783325446",15000.0f,30);
        personArr[2] = new Employee("Макаковна Лариса Лабутеновна","Специалист","makaka@gmail.com","+71188774065",1500.0f,85);
        personArr[3] = new Employee("Абрамович Рафик Хабибович","Главный бухгалтер","abram@mail.ru","+74403301597",140250.85f,26);
        personArr[4] = new Employee("Хренова Гадя Петрович","АртДизайнер","hrengape@mail.ru","+73357894562",96320.00f,21);

        for(Employee person: personArr){
            if (person.age > 40)
               person.printInfo();
        }

    }
}
