import scala.beans.BeanProperty

// 1 - Improve Counter class in Section 5.1 on page 55 so that it doesn't turn negative at Int.MaxValue
//Before
class Counter {
  private var value = 0
  def increment { value+=1 }
  def current() = value
}

val myCounter = new Counter
myCounter.increment
myCounter.current()

//After
class BigCounter {
  private var value:BigInt = 0
  def increment { value+=1 }
  def current() = value
}

/*
This test may take a while, enable this code if you want to see it in action.
val bigCounter = new BigCounter
while(bigCounter.current() <= Int.MaxValue) {
  bigCounter.increment
}
bigCounter.current()
bigCounter.current() > 0
bigCounter.current() > Int.MaxValue
*/

// 2 -Write a class BankAccount with methods deposit and withdraw and a read-only property balance.
class BankAccount {
  private var myBalance:Double = 0
  def deposit(amount:Double){ myBalance += amount}
  def withdraw(amount:Double) {if (amount < myBalance) myBalance -= amount}
  def balance = myBalance
}

val accountA = new BankAccount
accountA.deposit(12)
accountA.balance
accountA.withdraw(7)
accountA.balance
accountA.withdraw(89)
accountA.balance
accountA.balance == 5

// 3 - Write a class Time with read-only properties hours and minutes and a method before(other: Time): Boolean
// that checks wheter this time comes before the other, A Time object should be constructed as new Time(hrs, min)
// where hrs is in military time format (between 0 and 23).
class Time (val hrs: Int, val min:Int) {
  if(hrs < 0 || hrs > 23 || min < 0 || min > 59)
    throw new IllegalArgumentException(f"Invalid input for time $hrs:$min")

   def before (other: Time): Boolean = {
     hrs < other.hrs || hrs == other.hrs && min < other.min
   }
}

val timeA = new Time(3, 6)
timeA.before(new Time(14, 30))
timeA.before(new Time(2, 45))
timeA.before(new Time(3, 6))
timeA.hrs
timeA.min
//These two should throw an exception
//val timeB = new Time(3, 66)//Invalid min
//val timeC = new Time(33, 59)//Invalid hrs


// 4 - Reimplement the Time class annd change the internal implementation to store the time as
// minutes since midnight but keeping the same interface.
class TimeB {
  private var minutes = 0
  def this(hrs: Int, min:Int) {
    this()
    if (hrs < 0 || hrs > 23 || min < 0 || min > 59)
      throw new IllegalArgumentException(f"Invalid input for time $hrs:$min")
    minutes = hrs * 60 + min
  }

  def before (other: TimeB): Boolean = {
    hrs < other.hrs || hrs == other.hrs && min < other.min
  }
  def hrs = minutes / 60
  def min = minutes % 60
}

val timeB = new TimeB(3, 6)
timeB.before(new TimeB(14, 30))
timeB.before(new TimeB(2, 45))
timeB.before(new TimeB(3, 6))
timeB.hrs
timeB.min

// 5 - Make a class Student with read-write JavaBeans properties name (String) and id (Long).
///Start Student.scala
import scala.beans.BeanProperty

class Student {
  @BeanProperty var name: String = _
  @BeanProperty var id: Long = _
}
//End Student.scala

//save previous class and import in its own Student.scala file andexecute these commands:
//scalac Student.scala
//javap Student
/*You should receive an output like this:
Compiled from "Student.scala"
public class Student {
  public java.lang.String name();
  public void name_$eq(java.lang.String);
  public long id();
  public void id_$eq(long);
  public java.lang.String getName();
  public void setName(java.lang.String);
  public long getId();
  public void setId(long);
  public Student();
}
*
* */

// 6 - Change Person class from section 5.2 on page 56, provide a primary constructor that turns
// negative ages to 0
class Person(var age: Int) {
  if(age < 0) age = 0
}

val personA = new Person(12)
personA.age = 42
personA.age == 42
val personB = new Person(-34)
personB.age == 0


// 7 - Write a class Person with a primary constructor that accepts a string containing a first name,
// a space, and a last name, such as new Person("Fred Smith"). Supply read-only properties firstName
// and lastName.
class PersonB(val name: String) {
  if (!name.matches("\\w+\\s\\w+"))
    throw new IllegalArgumentException("Input name does not conform to the expected format 'FIRST_NAME LAST_NAME'")
  val firstName = name.substring(0, name.indexOf(' '))
  val lastName = name.substring(name.indexOf(' ') + 1)
}

val myPersonB = new PersonB("Fred Smith")
"'" + myPersonB.firstName + "'"
"'" + myPersonB.lastName + "'"
"'" + myPersonB.name + "'"
//This won't work -> myPersonB.firstName = "Alex"

// 8 - Make a class Car.
class Car (val manufacturer: String, val modelName: String){
  private var modelYear: Int = -1
  var licensePlate: String = ""

  def year = this.modelYear

  def this(manufacturer: String, modelName: String, modelYear: Int) {
    this(manufacturer, modelName)
    this.modelYear = modelYear
  }

  def this(manufacturer: String, modelName: String, licensePlate: String) {
    this(manufacturer, modelName)
    this.licensePlate = licensePlate
  }

  def this(manufacturer: String, modelName: String, modelYear: Int, licensePlate: String) {
    this(manufacturer, modelName, modelYear)
    this.licensePlate = licensePlate
  }
}

val carA = new Car("VW", "Passat")
carA.licensePlate == ""
carA.year == -1
carA.manufacturer == "VW"
carA.modelName == "Passat"
carA.licensePlate = "ABC-1234"
carA.licensePlate == "ABC-1234"
//This shouldn't work carA.year = 2017
val carB  = new Car(manufacturer = "Ford", modelName = "Fiesta", modelYear = 2015, licensePlate = "DEF-567")
carB.year
carB.licensePlate

// 9 - Reimplement the Car class in Java, C# or C++.
// You basically save yourself the code for the getters and setters, so the Scala code can be around 50% shorter.
// I am not implementing this exercise.

// 10 -Rewrite the following class to use explicit fields and a default primary constructor.
// Which form do you prefer? Why?
class Employee(val name: String, var salary: Double) {
  def this() {this("John Q. Public", 0.0)}
}

class EmployeeB {
  private var myName: String = "John Q. Public"
  private var mySalary: Double = 0.0

  def name = myName
  def salary = mySalary
}

val empA = new Employee
empA.name
empA.salary
val empB = new EmployeeB
empB.name
empB.salary

//I guess is a matter of personal preference, I can see how the first mode is more concise and could
//be more attractive to some people, personally I prefer the second style since I think it makes the
//contract more explicit and can be easier to read for people who is switching back and forward between
//Scala and other C-like languages (Java, C#, C/C++, etc.). Both styles are more concise than their
//respective C-like counterparts.