//CHAPTER 6: Objects

// 1 - Write an object Conversions with methods inchesToCentimeters, gallonsToLitters, milesToKilometers.
object Conversions {
  def inchesToCentimeters(inches: Double): Double = inches * 2.54
  def gallonsToLitters(gallons: Double) : Double = gallons * 3.78541
  def milesToKilometers(miles: Double) : Double = miles * 1.60934
}

Conversions.inchesToCentimeters(2.7)
Conversions.gallonsToLitters(3)
Conversions.milesToKilometers(181)

// 2- I went a bit ahead with this exercise and checked how to implement abstract classes in Scala.
abstract class UnitConversion {
  def convert(value: Double):Double
}

object InchesToCentimeters extends UnitConversion {
  override def convert(value: Double): Double = value * 2.54
}

object GallonsToLiters extends UnitConversion {
  override def convert(value: Double): Double = value * 3.78541
}

object MilesToKilometers extends UnitConversion {
  override def convert(value: Double): Double = value * 1.60934
}

InchesToCentimeters.convert(4)
GallonsToLiters.convert(3)
MilesToKilometers.convert(270)

// 3 - Define an Origin object that extends java.awt.Point. Why is a bad idea?
object Origin extends java.awt.Point {

}
Origin.x = 7
Origin.y = 6

//The problem is that the x and y fields are not encapsulated so you can change them directly.

// 4 - Define a Point class with a companion object so that you can construct Point instances as Point(3,4)
// without using new.
class Point (val x: Int, val y: Int) {

}

object Point {
  def apply(x: Int, y: Int): Point = new Point(x ,y)
}

val pointA = Point(3, 4)
pointA.x
pointA.y
val pointB = Point(8,9)
pointB.y

// 5 - Write a Scala application, using the App trait, that prints command-line arguments in reverse order,
// separated by spaces.
object ReverseArgs extends App {
  println(args.reverse.mkString(sep = " "))
}

ReverseArgs.main(Array("Hello", "World"))

// 6 - Write an enumeration describing the four playing card suits so that the toString method returns
// ♠, ♥, ♣ or ♦.
// Copied the characters from here: https://www.alt-codes.net/suit-cards.php
object Suits extends Enumeration {
  //type Suits = Value; we would add this type alias if we don't want to use Suits.Value
  val SPADE = Value("♠")
  val HEART = Value("♥")
  val CLUB  = Value("♣")
  val DIAMOND = Value("♦")
}

Suits.SPADE.id
Suits.SPADE.toString // This character may not render nicely in your shell.
Suits.SPADE.toString.codePointAt(0) == 9824
Suits.HEART.toString.codePointAt(0) == 9829
Suits.HEART.toString
Suits.CLUB.toString.codePointAt(0) == 9827
Suits.CLUB.toString
Suits.DIAMOND.toString.codePointAt(0) == 9830
Suits.DIAMOND.toString

// 7 - Implement a function that checks whether a card suit value from the preceding exercise is red.
// I am going with the explanation provided here: https://en.wikipedia.org/wiki/Suit_(cards)#Pairing_or_ignoring_suits
//  "Color is used to denote the red suits (hearts and diamonds) and the black suits (spades and clubs)."
def isRed(suit: Suits.Value): Boolean = suit == Suits.HEART || suit == Suits.DIAMOND

val suitA = Suits.DIAMOND
isRed(suitA) == true
val suitB = Suits.SPADE
isRed(suitB) == false

// 8 - Write an enumeration describing the eight corners of the RGB color cube. As IDs, use the color values
// (for example, 0xff0000 for Red).
// I used this cube as visual reference: https://www.researchgate.net/figure/RGB-color-cube_fig1_228677646
object RGBColorCubeCorners extends Enumeration {
  val RED = Value(0xff0000)
  val GREEN = Value(0x00ff00)
  val BLUE = Value(0x0000ff)
  val YELLOW = Value(0xffff00)
  val MAGENTA = Value(0xff00ff)
  val CYAN = Value(0x00ffff)
  val BLACK = Value(0x000000)
  val WHITE = Value(0xffffff)
}

RGBColorCubeCorners.BLACK
RGBColorCubeCorners.BLACK.id == 0
RGBColorCubeCorners.CYAN
RGBColorCubeCorners.CYAN.id == 65535