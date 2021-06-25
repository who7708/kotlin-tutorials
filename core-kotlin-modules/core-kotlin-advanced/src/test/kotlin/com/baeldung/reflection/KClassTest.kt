package com.baeldung.reflection

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberExtensionFunctions
import kotlin.reflect.full.memberExtensionProperties
import kotlin.reflect.full.primaryConstructor

//定义注解
annotation class Anno

//使用3个注解修饰该类
@Anno
@Deprecated("该类已经不推荐使用")
@Suppress("UNCHECKED_CAST")
class KClassTest(age: Int) {
    var name: String = "Kotlin"

    //为该类定义一个私有的构造器
    private constructor() : this(20) {}

    //定义一个有参数的构造器
    constructor(name: String) : this(15) {
        println("执行有参数的构造器：${name}")
    }

    //定义一个无参数的info方法
    fun info() {
        println("执行无参数的info方法")
    }

    //定义一个有参数的info方法
    fun info(str: String) {
        println("执行有参数的info方法，其str参数值：${str}")
    }

    //定义一个测试用的嵌套类
    class Inner
}

//为KClassTest定义扩展方法
fun KClassTest.bar() {
    println("扩展的bar方法")
}

//为KClassTest定义扩展属性
val KClassTest.foo: Double
    get() = 2.4

fun main(args: Array<String>) {
    //获取KClassTest对应的KClass
    val clazz = KClassTest::class
    //通过constructors属性获取KClass对象锁对应的全部构造器
    val ctors = clazz.constructors
    println("KClassTes的全部构造器如下：")
    ctors.forEach { println(it) }
    println("ClassTest的主构造器如下：")
    println(clazz.primaryConstructor)

    //通过functions属性获取该KClass对象所对应类的全部方法
    var funs = clazz.functions
    println("KClassTest的全部方法如下：")
    funs.forEach { println(it) }

    //通过declaredFunctions属性获取该KClass对象声明的全部方法
    var funs2 = clazz.declaredFunctions
    println("KClassTest本身声明的全部方法如下：")
    funs2.forEach { println(it) }

    //通过declaredMemberFunctions属性获取全部成员方法
    var memberFunctions = clazz.declaredMemberFunctions
    println("KClassTest本身声明的成员方法如下：")
    memberFunctions.forEach { println(it) }

    //通过memberExtensionFunctions属性获取全部扩展方法
    var exetensionFunctions = clazz.memberExtensionFunctions
    println("KClassTest声明的扩展方法如下：")
    exetensionFunctions.forEach { println(it) }

    //通过decaredMemberProperties获取全部成员属性
    var memberProperties = clazz.declaredMemberProperties
    println("KClassTest本身声明的成员属性如下：")
    memberProperties.forEach { println(it) }

    //通过memberExtensionProperties属性获取该KClass对象的全部扩展属性
    var exProperties = clazz.memberExtensionProperties
    println("KClassTest本身声明的扩展属性如下：")
    exProperties.forEach { println(it) }

    //通过annotations属性获取该KClass对象所对应类的全部注解
    val anns = clazz.annotations
    println("KClassTest的全部注解如下：")
    anns.forEach { println(it) }
    println("该KClass元素上的@Annot注解为：${clazz.findAnnotation<Anno>()}")

    //通过nestedClasses属性获取所对应的全部嵌套类
    val inners = clazz.nestedClasses
    println("KClassTest的全部内部类如下：")
    inners.forEach { println(it) }

    //通过supertypes属性获取该类的所有父类型
    println("KClassTest的父类型为：${clazz.supertypes}")
}