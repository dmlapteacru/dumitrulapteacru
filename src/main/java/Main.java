

import static service.Service.*;

public class Main {
    public static void main(String[] args) {

        getAllInterns().stream().forEach(System.out::println);
        br();
        getEmployeeAndSkills().stream().forEach(System.out::println);
    }

}
