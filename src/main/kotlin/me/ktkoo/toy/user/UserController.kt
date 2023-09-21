package me.ktkoo.toy.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        return try {
            val user = userService.createUser(userDto)
            ResponseEntity.ok(user)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userUpdateDto: UserUpdateDto,
    ): ResponseEntity<Any> {
	    return try {
		    val updatedUser = userService.updateUser(id, userUpdateDto)
		    ResponseEntity.ok(updatedUser)
	    } catch (e: IllegalArgumentException) {
		    ResponseEntity.badRequest().body(e.message)
	    } catch (e: NoSuchElementException) {
		    ResponseEntity.notFound().build<Any>()
	    }
    }

	@GetMapping("/{id}")
	fun getUser(@PathVariable id: Long): ResponseEntity<Any> {
		return try {
			val user = userService.getUser(id)
			ResponseEntity.ok(user)
		} catch (e: NoSuchElementException) {
			ResponseEntity.notFound().build()
		}
	}
}
