package yash.com.example.majorquizapp

class User {
    private var id = 0
    private var name: String? = null
    private var email: String? = null
    private var password: String? = null


    constructor(id: Int, name: String?, email: String?, password: String?) {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
    }

    fun getId(): Int {
        return id
    }

    fun setID(id: Int) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPass(): String? {
        return password
    }

    fun setPass(email: String) {
        this.password = password
    }


}



