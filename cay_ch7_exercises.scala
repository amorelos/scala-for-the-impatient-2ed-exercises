//Exercise 1 - Write example program to demonstrate that
//package com.horstmann.impatient is not the same as
//
//package com
//package horstmann
//package impatient

//Case A - File 1
package com
package horstmann

class ClassInParentPackage(val y:Int) {

}

//Case A - File 2
package com
package horstmann
package impatient

class OtherClass(val y:Int) {
  //This line is OK since ClassInParentPackage is visible from the parent package
  val member = new ClassInParentPackage(y)
}

//Case B - File 1
package com.horstmann

class ClassInParentPackage(val x:Int) {

}

//Case B - File 2
package com.horstmann.impatient

class MyClass(val x: Int) {
  //This will give a compilation error since ClassInParentPackage is not open
  val member = new ClassInParentPackage(x)
}

//Exercise 2 - Write a puzzler that baffles your Scala friends, using a package that isn't at
//the top level.
//Not sure what Cay is asking here, maybe one of these cases?
//Case A
package com
package horstmann
package other

class ClassInOtherPackage(val y:Int) {

}


package com
package horstmann
package another

class ClassInAnotherPackage(val y:Int) {
  val a = new horstmann.other.ClassInOtherPackage(5)//No com. at the beggining
}

//Case B
//Or maybe something where we use one of the classes from the default imports
package com {
  package horstmann {
    package other {
      abstract class MyClass {
        val x: ref.SoftReference[List[String]];
      }
    }
  }
}

//Or is something else?

//Exercise 3 -
package object random {
  private val a = 1664525
  private val b = 1013904224
  private val n = 32
  private var seed = 0
  private var next: Double = 0

  private def nextRandom() = next = (next * a + b) % Math.pow(2, n)
  def setSeed(seed: Int) = this.next = seed
  def nextInt() : Int =  {nextRandom();  next.toInt}
  def nextDouble() : Double = {nextRandom(); next}
}

//Exercise 4 -
/*Cay mentioned in page 83 that this was a JVM limitation. This makes sense since everything in Java
has to be in a class or interface (nothing can be defined as non-class member) this would lead
to compromises in the desing when creating package contained elements.
 */

//Exercise 5 - What is the meaning of private[com] def giveRaise(rate: Double)? Is it useful?
/*This will declare a method that is available to all packages under the com package level. In this
* particular case assuming com is a top level package then all package like com.x, com.y, com.x.z, etc.
* will be able to access this method, the problem com is a very common occurence so chances it will
* be visible to a lot of libraries or components.*/

//Exercise 6 - Copy all elements from a Java hash map into a scala hash map. Use imports to rename
//both classes.

import scala.collection.mutable.{Map => ScalaMap}
import java.util.{HashMap => JavaMap}
val srcMap: JavaMap[String, Int] = new JavaMap();
srcMap.put("Alex", 9)
srcMap.put("Bobby", 10)
srcMap.put("Chapian", 10)
srcMap.put("Dolly", 9)

val tgtMap: ScalaMap[String, Int] = ScalaMap();
srcMap.entrySet.forEach( entry => tgtMap.put(entry.getKey, entry.getValue))

//Exercise 7 - we could do something like this...
package demo {
  package renaming {
    class MapCopier {
      def copyMap() : collection.mutable.Map[String, Int] = {
        import scala.collection.mutable.{Map => ScalaMap}
        import java.util.{HashMap => JavaMap}
        val srcMap: JavaMap[String, Int] = new JavaMap();
        srcMap.put("Alex", 9)
        srcMap.put("Bobby", 10)
        srcMap.put("Chapian", 10)
        srcMap.put("Dolly", 9)

        val tgtMap: ScalaMap[String, Int] = ScalaMap();
        srcMap.entrySet.forEach( entry => tgtMap.put(entry.getKey, entry.getValue))
        tgtMap
      }
    }
  }
}

val obj = new demo.renaming.MapCopier()
obj.copyMap

//Exercise 8 - What is the effect of
//    import java._
//    import javax._
/*
You would end importing all the second level packages from java and javax to your namespace. there are no claases
only other packages. I don't think this would be very useful since you are just making name collisions more likely to
happen and you are really importing any classes. Besides, you still would need to prefix with the package name for
anything you may want to use, for example, util.Map.
* */