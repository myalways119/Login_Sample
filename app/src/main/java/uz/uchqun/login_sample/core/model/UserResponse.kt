package uz.uchqun.login_sample.core.model

import java.io.Serializable

data class UserResponse
(
    val phone_no: String,
    val name: String,
    val gender: String,
    val berth: String,
    val profile_pic: String,
    val target_city: String,
    val rec_question: String,
    val rec_answer: String,
    val android_id: String
) :Serializable
