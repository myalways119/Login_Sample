package uz.uchqun.login_sample.core.model

import java.io.Serializable

data class LargeContentResponse
(
    val id: String,
    val id_desc: String,
    val content: String
) :Serializable
