import kotlin.system.exitProcess

const val _VERSION = "2022.0228.0"

fun main(args: Array<String>) {
    println("=================================\nShopping center security v$_VERSION\n=================================")
    println("Данная программа контролирует\nпропуск посетителей в ТЦ.")
    print("\n\n")

    entranceControl()

}

data class User(val name:String, val lastName:String, val age:Int) {}
val users = mutableListOf<User>()

fun entranceControl() {
    println("---Входной контроль посетителей---")
    print("Введите Имя посетителя: ")
    val name = readLine().toString()
    print("Введите Фамилию посетителя: ")
    val lastname = readLine().toString()
    print("Введите Возраст посетителя: ")
    val age = readLine()!!.toInt()

    users.add(User(name, lastname, age))
    //println("------------------------------------------РЕЗУЛЬТАТ------------------------------------------")
    // Не используется. Ограничения отменены.
    // qrVerify(name, lastname, age)
    // println("---------------------------------------------------------------------------------------------\n\n")

    // Спрашиваем пользователя, хочет ли он закрыть программу или продолжить работу.
    checkEndProgram()
}

private fun qrVerify(name:String, lastname:String, age: Int) {
    if (age!! < 14){
        callPolice(name, lastname)
    }else if (age!! <18){
        noEntry(name, lastname)
    }else {
        allowEntry(name, lastname)
    }
}

private fun callPolice(name:String, lastname:String){
    println("Посетитель '$name $lastname' младше 14 лет.\nВызов полиции...")
}

private fun noEntry(name: String, lastname: String){
    println("Посетитель '$name $lastname' младше 18 лет.\nВход в ТЦ воспрещён!")
}

private fun allowEntry(name: String, lastname: String) {
    println("Посетитель '$name $lastname' выполняет требования входного контроля.\nПроход разрешён!")
}

private fun checkEndProgram() {
    println("Чтобы продолжить работу, введите 1. Список посетителей за смену - нажмите 2. Для завершения работы введите 0.")
    print("> ")
    var userInput = readLine()?.toInt()
    if (userInput == 1) {
        entranceControl()
    }else if (userInput == 2) {
        users.sortBy { it.lastName }
        for ((name, lastname, age) in users){
            println("Посетитель: $name $lastname | Возраст: $age")
        }
        checkEndProgram()
    }else if (userInput == 0) {
        println("Закрытие программы.")
        exitProcess(0)
    }else{
        println("Ошибка ввода, повторите")
        checkEndProgram()
    }
}