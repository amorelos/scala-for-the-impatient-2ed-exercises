import java.io.File
import java.util
import java.util.Scanner
import scala.collection.JavaConverters._
import java.util.Calendar._

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

//4- It's like the previous exercises, just change the type to SortedMap, page 50.
val inD = new Scanner(new File("C:\\Users\\amorelos\\.IntelliJIdea2018.2\\config\\scratches\\chult.txt"))
var sortedChultMap = mutable.SortedMap[String, Int]()
while(inD.hasNext()) {
  val word = inD.next
  val currValue = sortedChultMap.get(word)
  currValue match {
    case Some(existingValue) => sortedChultMap(word) = existingValue+1
    case None => sortedChultMap(word) = 1
  }
}
sortedChultMap.foreach{case (word: String, count: Int) => print(s"$word was found $count times. ")}
sortedChultMap.keySet.size //Number of keys

//5 - Using java.util.TreeMap, we need to use asScala, check page 44. The code is again pretty close to the previous exercises.
val inTreeMap = new Scanner(new File("C:\\Users\\amorelos\\.IntelliJIdea2018.2\\config\\scratches\\chult.txt"))
var sortedTreeMap = (new util.TreeMap[String, Int]()).asScala
while(inTreeMap.hasNext()) {
  val word = inTreeMap.next
  val currValue = sortedTreeMap.get(word)
  currValue match {
    case Some(existingValue) => sortedTreeMap(word) = existingValue+1
    case None => sortedTreeMap(word) = 1
  }
}
sortedTreeMap.foreach{case (word: String, count: Int) => print(s"$word was found $count times. ")}
sortedTreeMap.keySet.size //Number of keys

//6 - imported java.util.Calendar._ , the exercise asks only for weekdays and MONDAY is defined as 2.
val calendayDays = List(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)
var linkedHashMap = new java.util.LinkedHashMap[Int, Int]().asScala
for(day <- calendayDays) linkedHashMap.put(day, day)
val insertionOrderPreserved = linkedHashMap.forall{ case (dayKey, dayValue) => linkedHashMap.getOrElse(dayKey, -1) == calendayDays(dayKey - MONDAY)}
println(s"Elements matched: $insertionOrderPreserved")

//7- Print table of all Java properties reported by System.getProperties
val propertiesMap = System.getProperties.asScala
val maxKeyLen = propertiesMap.keySet.maxBy( key => key.length).length
//A couple of different ways to do it
propertiesMap.foreach{case (key, value) => println(key.padTo(maxKeyLen, ' ') + " | " + value)}
propertiesMap.foreach{case (key, value) => println(key + (" "  * (maxKeyLen - key.length)) + " | " + value)}


// 8 - minMax(values: Array[Int]) - return a pair containing the smallest and the largest values in the array.
// Used a couple of different ways
def minMax(values: Array[Int]) : Option[(Int, Int)] = {
  if(values.length == 0 )
    return None
  var min, max = values(0)
  for(value <- values) { if(value > max) max = value else if (value < min) min = value}
  return Some(min, max)
}

def minMaxB(values: Array[Int]) : Option[(Int, Int)] = {
  if(values.length == 0 )
    return None
  var minMax = (values(0), values(0))
  for(value <- values) { if(value > minMax._2) minMax = (minMax._1, value) else if (value < minMax._1) minMax = (value, minMax._2)}
  Some(minMax)
}

minMax(Array(2, 4, 1, 9, 8))
minMax(Array(2))
minMax(Array())
minMaxB(Array(2, 4, 1, 9, 8))
minMaxB(Array(2))
minMaxB(Array())

// 9 - lteggt(values: Array[Int], v: Int)
def lteggt(values: Array[Int], v: Int):Option[(Int, Int, Int)] = {
  if(values.length == 0 )
    return None
  var triple = (0, 0 ,0)
  values.foreach{ value => triple = if(value < v ) (triple._1 + 1, triple._2, triple._3)
                                    else if (value == v) (triple._1, triple._2 + 1, triple._3)
                                    else (triple._1, triple._2, triple._3 + 1)}
  Some(triple)
}

lteggt(Array(1,2,5,8,9,11,5,7), 6)
lteggt(Array(1,2,5,8,9,11,5,7,6), 6)
lteggt(Array(), 6)
lteggt(Array(4), 6)
lteggt(Array(6), 6)
lteggt(Array(7), 6)

//10 - What happens when you zip two strings? Come up with a plausible use case.
"Hello".zip("World")