import java.util.*;

// ----------------- Person Class -----------------
class Person {
    private String name;
    private String gender;
    private String maritalStatus;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getMaritalStatus() { return maritalStatus; }

    @Override
    public String toString() {
        return "Person [name=" + name + ", gender=" + gender + ", maritalStatus=" + maritalStatus + "]";
    }
}

// ----------------- Criteria Interface -----------------
interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}

// ----------------- Male Criteria -----------------
class CriteriaMale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<>();
        for (Person p : persons) {
            if (p.getGender().equalsIgnoreCase("MALE")) {
                malePersons.add(p);
            }
        }
        return malePersons;
    }
}

// ----------------- Female Criteria -----------------
class CriteriaFemale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<>();
        for (Person p : persons) {
            if (p.getGender().equalsIgnoreCase("FEMALE")) {
                femalePersons.add(p);
            }
        }
        return femalePersons;
    }
}

// ----------------- And Criteria -----------------
class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }
}

// ----------------- Or Criteria -----------------
class OrCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
        List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persons);

        // add others that are not already in first list
        for (Person p : otherCriteriaItems) {
            if (!firstCriteriaItems.contains(p)) {
                firstCriteriaItems.add(p);
            }
        }
        return firstCriteriaItems;
    }
}

// ----------------- Demo -----------------
public class CriteriaPatternDemo {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new Criteria() {
            @Override
            public List<Person> meetCriteria(List<Person> persons) {
                List<Person> singlePersons = new ArrayList<>();
                for (Person p : persons) {
                    if (p.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
                        singlePersons.add(p);
                    }
                }
                return singlePersons;
            }
        };

        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("Males:");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales:");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males:");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females:");
        printPersons(singleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons) {
        for (Person p : persons) {
            System.out.println(p);
        }
    }
}
