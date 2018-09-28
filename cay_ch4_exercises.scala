import java.io.File
import java.util.Scanner
import scala.collection.mutable

//1
val gizmos = Map( "PsVita" -> 200.00, "DDR3 RAM" -> 150.00, "Coffee" -> 5.99)
val discGizmos = for((gizmoName, price) <- gizmos) yield (gizmoName -> price * 0.9)

//2- I copied the text from http://forgottenrealms.wikia.com/wiki/Chult, Chult is not my favorite place but a fun one nonetheless.
val in = new Scanner(new File("C:\\Users\\amorelos\\.IntelliJIdea2018.2\\config\\scratches\\chult.txt"))
var chultMap = mutable.Map[String, Int]()
while(in.hasNext()) {
  val word = in.next
  val currValue = chultMap.getOrElse(word, -1)
  if(currValue != -1) chultMap(word) = currValue + 1 else chultMap(word) = 1
}
for((word, count) <- chultMap) print(s"$word was found $count times. ")
chultMap.keySet.size //Number of keys

//2- Using pattern matching
val inB = new Scanner(new File("C:\\Users\\amorelos\\.IntelliJIdea2018.2\\config\\scratches\\chult.txt"))
var chultMapB = mutable.Map[String, Int]()
while(inB.hasNext()) {
  val word = inB.next
  val currValue = chultMapB.get(word)
  currValue match {
    case Some(existingValue) => chultMapB(word) = existingValue + 1
    case None => chultMapB(word) = 1
  }
}
chultMapB.foreach{case (word, count) => print(s"$word was found $count times. ")}
chultMapB.keySet.size //Number of keys


//3- Using immutable map, check page 49
val inC = new Scanner(new File("C:\\Users\\amorelos\\.IntelliJIdea2018.2\\config\\scratches\\chult.txt"))
var chultMapC = Map[String, Int]()

while(inC.hasNext()) {
  val word = inC.next
  val currValue = chultMapC.get(word)
  currValue match {
    case Some(existingValue) => chultMapC = chultMapC + (word -> (existingValue+1))
    case None => chultMapC = chultMapC + (word -> 1)
  }
}
chultMapC.foreach(wordCountTuple => print(wordCountTuple._1 + " was found " + wordCountTuple._2 + " times. "))
chultMapC.keySet.size //Number of keys
